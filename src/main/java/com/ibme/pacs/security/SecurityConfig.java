package com.ibme.pacs.security;

import com.ibme.pacs.filter.EmployeeAuthenticationFilter;
import com.ibme.pacs.filter.EmployeeAuthorizationFilter;
import com.ibme.pacs.repository.IEmployeeRepository;
import com.ibme.pacs.repository.IRoleRepository;
import com.ibme.pacs.service.impl.EmployeeServiceImpl;
import com.ibme.pacs.service.inter.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static java.lang.invoke.VarHandle.AccessMode.GET;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

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
        EmployeeAuthenticationFilter employeeAuthenticationFilter = new EmployeeAuthenticationFilter(authenticationManagerBean(), (EmployeeServiceImpl) employeeService);
        employeeAuthenticationFilter.setFilterProcessesUrl("/api/login/**");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/login", "/api/admin/employee/token/refresh/**").permitAll();


        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/admin/department/**")
                .hasAnyAuthority(roleRepository.findById(6).get().getName(),
                        roleRepository.findById(7).get().getName());
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/admin/department/**")
                .hasAnyAuthority(roleRepository.findById(6).get().getName());

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/admin/employee/**")
                .hasAnyAuthority(roleRepository.findById(6).get().getName()
                        , roleRepository.findById(7).get().getName());
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/admin/employee/**").hasAuthority(roleRepository.findById(6).get().getName());
//        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(employeeAuthenticationFilter);
        http.addFilterBefore(new EmployeeAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
