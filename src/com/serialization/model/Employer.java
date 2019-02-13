package com.serialization.model;

import java.util.UUID;

public class Employer implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    public Employer(final String name) {
        setId();
        setName(name);
    }

    public void setId() {
        this.id = UUID.randomUUID().toString();
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toJSONString() {
        return String.format("{\n id: %s, \n name: %s \n}\n", getId(), getName());
    }
}