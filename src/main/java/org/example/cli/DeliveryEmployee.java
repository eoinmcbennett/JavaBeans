package org.example.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// DeliveryEmployee will extend/inherit from Employee class
public class DeliveryEmployee extends Employee {

    // Constructor for delivery employee, inherits all attributes from parent Employee class
    @JsonCreator
    public DeliveryEmployee( @JsonProperty("id") int id,
                             @JsonProperty("first_name") String first_name,
                             @JsonProperty("last_name") String last_name,
                             @JsonProperty("salary") double salary,
                             @JsonProperty("bank_account_number") String bank_account_number,
                             @JsonProperty("ni_number") String ni_number) {
        super(id, first_name, last_name, salary, bank_account_number, ni_number);
    }

}
