package com.serialization.repository;

import java.util.List;
//import java.util.UUID;
//import java.util.stream.Stream;

import com.serialization.model.Employer;

public interface EmployerRepository {
    /**
     * Serializes the object
     * 
     * @param employer The object to serialize
     */
    public void save(Employer employer);

    /**
     * Finds an employer by id
     * 
     * @param id - The id
     */
    public Employer findById(String id);

    public void deleteById(String id);

    public List<String> findAll();
}