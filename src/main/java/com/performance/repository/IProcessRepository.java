package com.performance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.performance.entity.Process;

public interface IProcessRepository extends JpaRepository<Process, Integer> {

}
