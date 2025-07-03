package com.shaon.declarative_crud_two.repository;

import com.shaon.declarative_crud_two.model.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee,Long> {
}
