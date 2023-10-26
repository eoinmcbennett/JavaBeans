package org.example.core;

import org.example.cli.Employee;
import org.example.cli.EmployeeRequest;

public class EmployeeRequestValidator {
    public String isValidEmployee(EmployeeRequest employeeRequest){
        if(employeeRequest.getSalary() < 0){
            return "Salary cannot be less than zero";
        }

        if(employeeRequest.getBankAccountNumber().length() != 8){
            return "Bank account number length must be 8 provided " + employeeRequest.getBankAccountNumber().length();
        }

        String nationalInsuranceNumber = employeeRequest.getNiNumber();

        if(nationalInsuranceNumber.length() != 9){
            return "National insurance number length must be 9 provided: " + employeeRequest.getNiNumber().length();
        }

        return null;
    }
}
