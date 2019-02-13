package com.serialization.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
//import java.util.UUID;
//import java.util.stream.Stream;

import com.serialization.model.Employer;
import com.serialization.repository.EmployerRepository;

public class EmployerService implements EmployerRepository {
    List<Employer> employers = new ArrayList<>();
    File employerFile;
    FileOutputStream employerFileOutputStream;
    FileInputStream employerFileInputStream;
    ObjectOutputStream employerObjectOutputStream;
    ObjectInputStream employerObjectInputStream;

    @Override
    public void save(Employer employer) {

        employers.clear();

        try {
            employerFile = new File("/java-db/employers", String.format("%s.ser", employer.getId()));
            employerFile.getParentFile().mkdirs();
            if (!employerFile.exists())
                employerFile.createNewFile();
            employerFileOutputStream = new FileOutputStream(employerFile);
            employerObjectOutputStream = new ObjectOutputStream(employerFileOutputStream);
            employerObjectOutputStream.writeObject(employer);
            employerObjectOutputStream.close();
            employerFileOutputStream.flush();

            File directory = new File("/java-db", "/employers");
            if (directory.isDirectory())
                for (File item : directory.listFiles()) {
                    employerFileInputStream = new FileInputStream(item);
                    employerObjectInputStream = new ObjectInputStream(employerFileInputStream);

                    Employer e = (Employer) employerObjectInputStream.readObject();
                    employers.add(e);

                    employerObjectInputStream.close();
                    employerFileInputStream.close();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> findAll() {
        List<String> s = new ArrayList<>();
        try {
            File directory = new File("/java-db", "/employers");
            if (directory.isDirectory())
                for (File item : directory.listFiles()) {
                    employerFileInputStream = new FileInputStream(item);
                    employerObjectInputStream = new ObjectInputStream(employerFileInputStream);

                    Employer e = (Employer) employerObjectInputStream.readObject();
                    s.add(e.toJSONString());

                    employerObjectInputStream.close();
                    employerFileInputStream.close();
                }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return s;
    }

    @Override
    public Employer findById(String id) {
        Employer e = null;
        try {
            File file = new File("/java-db/employers", String.format("%s.ser", id));
            employerFileInputStream = new FileInputStream(file);
            employerObjectInputStream = new ObjectInputStream(employerFileInputStream);
            e = (Employer) employerObjectInputStream.readObject();

            employerObjectInputStream.close();
            employerFileInputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return e;
    }

    @Override
    public void deleteById(String id) {

        try {
            File file = new File("/java-db/employers", String.format("%s.ser", id));
            if (file.exists())
                file.delete();
            else
                System.err.println("Employer does not exist");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}