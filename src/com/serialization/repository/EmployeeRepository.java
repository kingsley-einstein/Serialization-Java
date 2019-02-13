package com.serialization.repository;

import java.util.List;
//import java.util.UUID;

import com.serialization.model.Employee;

public interface EmployeeRepository {
    /**
     * Creates an employee
     */
    public void save(Employee employee);

    /**
     * Finds employee by id
     */
    public Employee findById(String id);

    public void deleteById(String id);

    public List<String> findAll();
}