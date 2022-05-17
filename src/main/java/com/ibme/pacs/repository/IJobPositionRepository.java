package com.ibme.pacs.repository;

import com.ibme.pacs.entities.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobPositionRepository extends JpaRepository<JobPosition, Integer> {
}
