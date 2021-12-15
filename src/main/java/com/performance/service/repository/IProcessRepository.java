package com.performance.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.performance.service.entity.Process;


public interface IProcessRepository extends JpaRepository<Process, Integer> {

}
