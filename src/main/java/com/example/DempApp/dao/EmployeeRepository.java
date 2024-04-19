package com.example.DempApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DempApp.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
