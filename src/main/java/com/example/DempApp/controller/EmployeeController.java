package com.example.DempApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DempApp.dao.EmployeeRepository;
import com.example.DempApp.dto.TaxDeductionDTO;
import com.example.DempApp.model.Employee;
import com.example.DempApp.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	
	@Autowired
	EmployeeRepository repository;
	
	
	@PostMapping
    public ResponseEntity<Employee> storeEmployeeDetails(@Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(service.storeEmployeeDetails(employee));
    }
	
	
	@GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/tax-deduction")
    public ResponseEntity<List<TaxDeductionDTO>> calculateEmployeesTaxDeduction() {
    	List<TaxDeductionDTO> calculateEmployeesTaxDeduction = null;
    	List<Employee> employees = repository.findAll();
    	if(employees != null && employees.size() > 0) {
    		calculateEmployeesTaxDeduction = service.calculateEmployeesTaxDeduction(employees);
		}
        return ResponseEntity.ok(calculateEmployeesTaxDeduction);
    }
}
