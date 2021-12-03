package com.performance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.performance.entity.Evaluated;

public interface IEvaluatedRepository extends JpaRepository<Evaluated, Integer>{

}
