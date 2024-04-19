package com.example.DempApp.service;

import java.util.List;

import com.example.DempApp.dto.TaxDeductionDTO;
import com.example.DempApp.model.Employee;

public interface EmployeeService {
	
	 public  Employee  storeEmployeeDetails(Employee employee);
	 
	 
	 public  List<TaxDeductionDTO> calculateEmployeesTaxDeduction(List<Employee> employees);

}
