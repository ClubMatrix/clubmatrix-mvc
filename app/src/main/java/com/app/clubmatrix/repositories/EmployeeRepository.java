package com.app.clubmatrix.repositories;

import com.app.clubmatrix.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {}
