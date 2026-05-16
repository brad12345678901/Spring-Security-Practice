package com.brad.SecurityPower.service;

import com.brad.SecurityPower.entity.Employee;
import com.brad.SecurityPower.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id) {
        Optional<Employee> dbEmployee = employeeRepository.findById(id);

        if (dbEmployee.isEmpty()) {
            throw new RuntimeException("Employee id"+id+" not found...");
        }

        return dbEmployee.get();
    }
}
