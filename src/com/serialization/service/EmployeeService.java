package com.serialization.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import com.serialization.model.Employee;
import com.serialization.repository.EmployeeRepository;

public class EmployeeService implements EmployeeRepository {
    List<Employee> employees = new ArrayList<>();
    File employeeFile;
    FileOutputStream employeeFileOutputStream;
    FileInputStream employeeFileInputStream;
    ObjectOutputStream employeeObjectOutputStream;
    ObjectInputStream employeeObjectInputStream;

    @Override
    public void save(Employee employee) {

        employees.clear();

        try {
            employeeFile = new File("/java-db/employees", String.format("%s.ser", employee.getId()));
            employeeFile.getParentFile().mkdirs();
            if (!employeeFile.exists())
                employeeFile.createNewFile();
            employeeFileOutputStream = new FileOutputStream(employeeFile);
            employeeObjectOutputStream = new ObjectOutputStream(employeeFileOutputStream);
            employeeObjectOutputStream.writeObject(employee);
            employeeObjectOutputStream.close();
            employeeFileOutputStream.flush();

            File directory = new File("/java-db", "/employees");
            if (directory.isDirectory())
                for (File item : directory.listFiles()) {
                    employeeFileInputStream = new FileInputStream(item);
                    employeeObjectInputStream = new ObjectInputStream(employeeFileInputStream);

                    Employee e = (Employee) employeeObjectInputStream.readObject();
                    employees.add(e);

                    employeeObjectInputStream.close();
                    employeeFileInputStream.close();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> findAll() {
        List<String> s = new ArrayList<>();
        try {
            File directory = new File("/java-db", "/employees");
            if (directory.isDirectory())
                for (File item : directory.listFiles()) {
                    employeeFileInputStream = new FileInputStream(item);
                    employeeObjectInputStream = new ObjectInputStream(employeeFileInputStream);

                    Employee e = (Employee) employeeObjectInputStream.readObject();
                    s.add(e.toJSONString());

                    employeeObjectInputStream.close();
                    employeeFileInputStream.close();
                }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return s;
    }

    @Override
    public Employee findById(String id) {
        Employee e = null;
        try {
            File file = new File("/java-db/employees", String.format("%s.ser", id));
            employeeFileInputStream = new FileInputStream(file);
            employeeObjectInputStream = new ObjectInputStream(employeeFileInputStream);
            e = (Employee) employeeObjectInputStream.readObject();

            employeeObjectInputStream.close();
            employeeFileInputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return e;
    }

    @Override
    public void deleteById(String id) {

        try {
            File file = new File("/java-db/employees", String.format("%s.ser", id));
            if (file.exists())
                file.delete();
            else
                System.err.println("Employee does not exist");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}