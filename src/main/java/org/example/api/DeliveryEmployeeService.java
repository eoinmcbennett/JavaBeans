package org.example.api;

import org.example.cli.DeliveryEmployee;
import org.example.cli.UpdateDeliveryEmployeeRequest;
import org.example.client.*;
import org.example.core.EmployeeValidator;
import org.example.core.UpdateDeliveryEmployeeValidator;
import org.example.db.DeliveryEmployeeDAO;

public class DeliveryEmployeeService {
    private DeliveryEmployeeDAO dao;
    private EmployeeValidator employeeValidator = new EmployeeValidator();

    public DeliveryEmployeeService(DeliveryEmployeeDAO dao){
        this.dao = dao;
    }

    /**
     * Attempts to create a delivery employee using the service data access object
     * @param deliveryEmployee
     * @return the created delivery employee id
     * @throws FailedToCreateException
     */
    public int createDeliveryEmployee(DeliveryEmployee deliveryEmployee) throws FailedToCreateException, ValidationFailedException {
        String error = employeeValidator.isValidEmployee(deliveryEmployee);
        if(error != null){
            throw new ValidationFailedException(error);
        }
        return dao.createDeliveryEmployee(deliveryEmployee);
    }

    /**
     * returns specific delivery employee based on employee id
     * @param id employee id
     * @return delivery employee
     * @throws DoesNotExistException if no employee returned from database
     * @throws FailedToGetException if sql statement failed
     */
    public DeliveryEmployee getDeliveryEmployeeById(int id) throws DoesNotExistException, FailedToGetException, DeliveryEmployeeDoesNotExistException {

        // call to dao to check employee with given id is a delivery employee
        if(dao.checkEmployeeIsDeliveryEmployee(id) == null){
            throw new DeliveryEmployeeDoesNotExistException();
        }

        // call getDeliveryEmployeeById method from DeliveryEmployeeDao class
        DeliveryEmployee deliveryEmployee = dao.getDeliveryEmployeeById(id);

        // if a delivery employee not returned, throw delivery employee does not exist exception
        if(deliveryEmployee == null){
            throw new DoesNotExistException();
        }

        return deliveryEmployee;
    }

    public void updateDeliveryEmployee(int id, UpdateDeliveryEmployeeRequest deliveryEmployeeUpdate) throws FailedToUpdateDeliveryEmployee, FailedToGetException, DeliveryEmployeeDoesNotExistException, InvalidUpdateRequestException {

        // call to dao to check employee with given id is a delivery employee
        if(dao.checkEmployeeIsDeliveryEmployee(id) == null){
            throw new DeliveryEmployeeDoesNotExistException();
        }

        // if employee exists, check data is valid using employee validator
        UpdateDeliveryEmployeeValidator updateDeliveryEmployeeValidator = new UpdateDeliveryEmployeeValidator();
        if(updateDeliveryEmployeeValidator.isValidEmployeeUpdate(deliveryEmployeeUpdate) != null){
            throw new InvalidUpdateRequestException(updateDeliveryEmployeeValidator.isValidEmployeeUpdate(deliveryEmployeeUpdate));
        }

        // call on dao to update delivery employee
        dao.updateDeliveryEmployee(id, deliveryEmployeeUpdate);
    }
}
