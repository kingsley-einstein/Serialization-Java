package com.serialization;

import java.util.Scanner;
//import java.util.UUID;

import com.serialization.model.*;
import com.serialization.service.EmployeeService;
import com.serialization.service.EmployerService;

public class Main {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        EmployerService employerService = new EmployerService();
        EmployeeService employeeService = new EmployeeService();

        while (true) {

            System.out.println("Choose Option\n 1 - Employer Option \n 2 - Employee Option \n");

            int choice = scanner.nextInt();

            if (choice == 1) {

                System.out.println("Enter: \n 1 - New Employer \n 2 - Find Employer By Id \n");

                int employerOption = scanner.nextInt();

                if (employerOption == 1) {
                    System.out.println("Enter employer's name\n");

                    // String name = scanner.nextLine();

                    Employer employer = new Employer(scanner.next());

                    employerService.save(employer);
                    System.out.println(employerService.findAll());
                }

                if (employerOption == 2) {
                    System.out.println("Enter employer's id\n");

                    Employer e = employerService.findById(scanner.next());

                    System.out.println(e.toJSONString());
                }
            }

            if (choice == 2) {
                System.out.println("Enter: \n 1 - New Employee \n 2 - Find Employee By Id \n");

                int employeeOption = scanner.nextInt();

                if (employeeOption == 1) {
                    System.out.println("Enter Name: \n");

                    String name = scanner.next();

                    System.out.println("Enter Address: \n");

                    String address = scanner.next();

                    System.out.println("Enter Department: \n");

                    String department = scanner.next();

                    System.out.println("Enter Employer's Id: \n");

                    String id = scanner.next();

                    Employee e = new Employee(name, address, department, employerService.findById(id));

                    employeeService.save(e);

                    System.out.println(employeeService.findAll());
                }

                if (employeeOption == 2) {
                    System.out.println("Enter Employee's Id \n");

                    Employee e = employeeService.findById(scanner.next());

                    System.out.println(e.toJSONString());
                }
            }
        }

        // System.out.printf("Enter %s", scanner.next());
    }

    public static void main(String[] args) {
        run();
    }
}