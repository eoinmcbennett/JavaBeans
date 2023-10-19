package org.example.cli;

// DeliveryEmployee will extend/inherit from Employee class
public class DeliveryEmployee extends Employee {

    // Constructor for delivery employee, inherits all attributes from parent Employee class
    public DeliveryEmployee(int id, String first_name, String last_name, double salary, String bank_account_number, String ni_number) {
        super(id, first_name, last_name, salary, bank_account_number, ni_number);
    }

}
