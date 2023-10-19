package org.example.api;

import org.example.cli.DeliveryEmployee;
import org.example.client.FailedToCreateException;
import org.example.db.DeliveryEmployeeDAO;

public class DeliveryEmployeeService {
    private DeliveryEmployeeDAO dao;

    public DeliveryEmployeeService(DeliveryEmployeeDAO dao){
        this.dao = dao;
    }

    /**
     * Attempts to create a delivery employee using the service data access object
     * @param deliveryEmployee
     * @return the created delivery employee id
     * @throws FailedToCreateException
     */
    public int createDeliveryEmployee(DeliveryEmployee deliveryEmployee) throws FailedToCreateException {
        return dao.createDeliveryEmployee(deliveryEmployee);
    }
}
