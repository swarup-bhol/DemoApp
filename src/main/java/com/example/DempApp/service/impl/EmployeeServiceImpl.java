package com.example.DempApp.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DempApp.dao.EmployeeRepository;
import com.example.DempApp.dto.TaxDeductionDTO;
import com.example.DempApp.model.Employee;
import com.example.DempApp.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository repository;

	@Override
	public Employee storeEmployeeDetails(Employee employee) {
		return repository.save(employee);
	}

	@Override
	public List<TaxDeductionDTO> calculateEmployeesTaxDeduction(List<Employee> employees) {
		List<TaxDeductionDTO> deductions = new ArrayList<>();

        for (Employee employee : employees) {
            double totalSalary = calculateTotalSalary(employee);

            double tax = calculateTax(totalSalary);

            double cess = calculateCess(totalSalary);
            deductions.add(new TaxDeductionDTO(
            		 employee.getEmployeeId(),
                     employee.getFirstName(),
                     employee.getLastName(),
                     totalSalary,
                     tax,
                     cess
            ));
        }
        return deductions;
    }

	private static double calculateTotalSalary(Employee employee) {
        LocalDate currentDate = LocalDate.now();
        LocalDate joiningDate = employee.getDateOfJoining();
        Period period = Period.between(joiningDate, currentDate);
        int monthsWorked = period.getYears() * 12 + period.getMonths();
        return employee.getSalary() * monthsWorked;
    }

    private static double calculateTax(double yearlySalary) {
        double tax = 0;
        if (yearlySalary > 1000000) {
            tax += (yearlySalary - 1000000) * 0.20; 
            yearlySalary = 1000000;
        }
        if (yearlySalary > 500000) {
            tax += (yearlySalary - 500000) * 0.10; 
            yearlySalary = 500000;
        }
        if (yearlySalary > 250000) {
            tax += (yearlySalary - 250000) * 0.05; 
        }
        return tax;
    }

    private static double calculateCess(double yearlySalary) {
        double cess = 0;
        if (yearlySalary > 2500000) {
            cess = (yearlySalary - 2500000) * 0.02;
        }
        return cess;
    }

}
