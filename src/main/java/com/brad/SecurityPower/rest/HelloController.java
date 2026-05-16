package com.brad.SecurityPower.rest;

import com.brad.SecurityPower.entity.Employee;
import com.brad.SecurityPower.service.EmployeeService;
import com.brad.SecurityPower.service.EmployeeServiceImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class HelloController {

    private final EmployeeService employeeService;

    public HelloController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/greet")
    public Mono<String> greet(@AuthenticationPrincipal Jwt jwt) {
        // This pulls the "preferred_username" claim from your Keycloak token
        String name = jwt.getClaimAsString("preferred_username");
        jwt.getClaims().forEach((key, value) -> System.out.println(key + ": " + value));
        return Mono.just("Hello, " + name + "! Your token is valid.");
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();

        return allEmployees;
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee foundEmployee = employeeService.getEmployeeById(employeeId);
        return foundEmployee;
    }
}
