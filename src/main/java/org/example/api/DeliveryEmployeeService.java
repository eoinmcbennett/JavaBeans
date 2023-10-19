package org.example.api;

import org.example.cli.DeliveryEmployee;
import org.example.client.FailedToCreateException;
import org.example.db.DeliveryEmployeeDAO;

public class DeliveryEmployeeService {
    private DeliveryEmployeeDAO dao;

    public DeliveryEmployeeService(DeliveryEmployeeDAO dao){
        this.dao = dao;
    }

    public int createDeliveryEmployee(DeliveryEmployee deliveryEmployee) throws FailedToCreateException {
        return dao.createDeliveryEmployee(deliveryEmployee);
    }
}
