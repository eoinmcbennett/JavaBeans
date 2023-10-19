package org.example.cli;

public class Employee {
    // Variable declaration
    private int id;
    private String first_name;
    private String last_name;
    private double salary;
    private String bank_account_number;
    private String ni_number;

    // Constructor to create new employee with all required details
    public Employee(int id, String first_name, String last_name, double salary, String bank_account_number, String ni_number) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.salary = salary;
        this.bank_account_number = bank_account_number;
        this.ni_number = ni_number;
    }

    // Getters and setters for all variables
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getBank_account_number() {
        return bank_account_number;
    }

    public void setBank_account_number(String bank_account_number) {
        this.bank_account_number = bank_account_number;
    }

    public String getNi_number() {
        return ni_number;
    }

    public void setNi_number(String ni_number) {
        this.ni_number = ni_number;
    }

    // Will create string for Employee object, excludes private details (e.g. NI number and bank details)
    public String toString(){
        return "ID: " + this.id + "\n Name: " + this.first_name + " " + this.last_name + "\n";
    }
}
