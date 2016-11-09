package com.xebia.service;

import com.xebia.domain.Employee;

import java.util.Collection;

public interface EmployeeService {
     Collection<Employee> findAll();

     void save(Employee employee);
}
