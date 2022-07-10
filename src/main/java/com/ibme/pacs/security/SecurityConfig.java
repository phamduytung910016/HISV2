package com.ibme.pacs.security;

import com.ibme.pacs.filter.EmployeeAuthenticationFilter;
import com.ibme.pacs.filter.EmployeeAuthorizationFilter;
import com.ibme.pacs.repository.IEmployeeRepository;
import com.ibme.pacs.repository.IRoleRepository;
import com.ibme.pacs.service.impl.EmployeeServiceImpl;
import com.ibme.pacs.service.inter.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.List;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final IRoleRepository roleRepository;
    private final IEmployeeService employeeService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //cách spring security kiểm tra thông tin
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();

        //stateless
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        EmployeeAuthenticationFilter employeeAuthenticationFilter = new EmployeeAuthenticationFilter(authenticationManagerBean(), (EmployeeServiceImpl) employeeService);
        employeeAuthenticationFilter.setFilterProcessesUrl("/api/login/**");

        //http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/login/**", "/api/admin/employee/token/refresh/**").permitAll();

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/login/**").permitAll()
                .antMatchers("/api/admin/employee/token/refresh/**").permitAll();


        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/admin/department/**")
                .hasAnyAuthority(roleRepository.findById(6).get().getName(),
                        roleRepository.findById(7).get().getName());
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/admin/department/**")
                .hasAnyAuthority(roleRepository.findById(6).get().getName());

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/admin/employee/**")
                .hasAnyAuthority(roleRepository.findById(6).get().getName()
                        , roleRepository.findById(7).get().getName());
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/admin/employee/**").hasAuthority(roleRepository.findById(6).get().getName());
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/admin/jobposition/**");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/admin/role/**");


        //        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(employeeAuthenticationFilter);
        http.addFilterBefore(new EmployeeAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        // configuration.setAllowedOrigins(Arrays.asList("https://gururecharge.com","http://localhost:4200"));
        configuration.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must
        // not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(false);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
