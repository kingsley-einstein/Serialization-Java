package com.serialization.model;

import java.util.UUID;

public class Employee implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String address;

    private String department;

    private Employer employer;

    public Employee(final String name, final String address, final String department, final Employer employer) {
        setId();
        setName(name);
        setAddress(address);
        setDepartment(department);
        setEmployer(employer);
    }

    public void setId() {
        this.id = UUID.randomUUID().toString();
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setDepartment(final String department) {
        this.department = department;
    }

    public void setEmployer(final Employer employer) {
        this.employer = employer;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDepartment() {
        return department;
    }

    public Employer getEmployer() {
        return employer;
    }

    public String toJSONString() {
        return String.format("{\n id: %s, \n name: %s, \n address: %s, \n department: %s, \n employer: %s \n}\n",
                getId(), getName(), getAddress(), getDepartment(), getEmployer().toJSONString());
    }
}