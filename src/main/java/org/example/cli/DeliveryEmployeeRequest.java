package org.example.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// DeliveryEmployee will extend/inherit from EmployeeRequest class
public class DeliveryEmployeeRequest extends EmployeeRequest {

    // Constructor for delivery employee request, inherits attributes from parent EmployeeRequest class except ID
    @JsonCreator
    public DeliveryEmployeeRequest(@JsonProperty("firstName") String firstName,
                                   @JsonProperty("lastName") String lastName,
                                   @JsonProperty("salary") double salary,
                                   @JsonProperty("bankAccountNumber") String bankAccountNumber,
                                   @JsonProperty("niNumber") String niNumber) {
        super(firstName, lastName, salary, bankAccountNumber, niNumber);
    }

}
