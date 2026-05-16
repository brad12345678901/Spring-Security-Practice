package com.brad.SecurityPower.service;

import com.brad.SecurityPower.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees ();
    Employee getEmployeeById(int id);
}
