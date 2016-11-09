package com.xebia.controller;

import com.xebia.domain.Employee;
import com.xebia.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "api/employees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Employee>> getEmployees() {
        Collection<Employee> employees = employeeService.findAll();
        return new ResponseEntity<Collection<Employee>>(employees, HttpStatus.OK);
    }

    @RequestMapping(value = "api/employee")
    public ResponseEntity createEmployee(@RequestBody Employee employee){
        employeeService.save(employee);
        return new ResponseEntity(HttpStatus.CREATED);
    }


}
