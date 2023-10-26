package org.example.db;

import org.example.cli.DeliveryEmployee;
import org.example.cli.DeliveryEmployeeRequest;
import org.example.client.FailedToCreateException;
import org.example.client.FailedToGetException;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryEmployeeDAO {

    // create instance of database connector class
    DatabaseConnector databaseConnector = new DatabaseConnector();

    /**
     * inserts a new employee into employee table, then adds employee to delivery employee table via employee id
     * @param deliveryEmployeeRequest
     * @return id of created employee in database
     * @throws SQLException
     */
    public int createDeliveryEmployee(DeliveryEmployeeRequest deliveryEmployeeRequest) throws FailedToCreateException {

        // establish connection with database
        try {
            Connection c = databaseConnector.getConnection();

            // sql statement string for creating employee
            String sqlEmployee = "INSERT INTO employee (first_name, last_name, salary, bank_account_number, national_insurance_number) VALUES (?, ?, ?, ?, ?);";

            // prepare sql statement
            PreparedStatement statementEmployee = c.prepareStatement(sqlEmployee, Statement.RETURN_GENERATED_KEYS);

            // set attributes of new employee
            statementEmployee.setString(1, deliveryEmployeeRequest.getFirstName());
            statementEmployee.setString(2, deliveryEmployeeRequest.getLastName());
            statementEmployee.setDouble(3, deliveryEmployeeRequest.getSalary());
            statementEmployee.setString(4, deliveryEmployeeRequest.getBankAccountNumber());
            statementEmployee.setString(5, deliveryEmployeeRequest.getNiNumber());

            // execute sql statement
            statementEmployee.executeUpdate();

            // get id of new delivery employee
            ResultSet resultSet = statementEmployee.getGeneratedKeys();

            if (!resultSet.next()) {
                throw new FailedToCreateException("Failed to create delivery employee " + deliveryEmployeeRequest);
            }

            int newId = resultSet.getInt(1);

            if(newId <= 0){
                throw new FailedToCreateException("Failed to create delivery employee " + deliveryEmployeeRequest);
            }

            // sql statement for inserting employee into employee delivery table
            String sqlDeliveryEmployee = "INSERT INTO delivery_employee(employee_id) VALUES (?);";

            // prepare sql statement
            PreparedStatement statementDeliveryEmployee = c.prepareStatement(sqlDeliveryEmployee);

            // set id of delivery employee
            statementDeliveryEmployee.setInt(1, newId);

            // execute sql statement
            statementDeliveryEmployee.executeUpdate();

            return newId;
        }catch(SQLException e ){
            throw new FailedToCreateException(e.getMessage() + deliveryEmployeeRequest.toString());
        }

    }


    /**
     * Gets a list of all delivery employee ids in the database
     * @return List of all delivery employees ids
     */
    public List<Integer> getDeliveryEmployeesIds() {
        try {
            Connection conn = DatabaseConnector.getConnection();
            String SQL = "SELECT employee_id, first_name, last_name, salary, bank_account_number, national_insurance_number FROM employee JOIN delivery_employee USING(employee_id)";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(SQL);

            List<Integer> employeeIds = new ArrayList<>();
            while(rs.next()){
                System.out.println(rs.getInt("employee_id"));
                employeeIds.add(rs.getInt("employee_id"));
            }

            return employeeIds;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * get delivery employee information by specified id.
     * @param id employee id of delivery employee
     * @return delivery employee object. returns null if no employee returned i.e. employee does not exist
     */
    public DeliveryEmployee getDeliveryEmployeeById(int id) throws FailedToGetException {
        System.out.println(id);
        try {
            // establish connection with database
            Connection c = databaseConnector.getConnection();

            // string sql statement
            String sqlString = "SELECT employee_id, first_name, last_name, salary, bank_account_number, national_insurance_number FROM employee WHERE employee_id = ?";

            // prepare sql statement
            PreparedStatement statementEmployee = c.prepareStatement(sqlString);

            // set employee id to specified id
            statementEmployee.setInt(1, id);

            // execute sql statement
            ResultSet resultSet = statementEmployee.executeQuery();

            // return order with returned data
            while (resultSet.next()){
                return new DeliveryEmployee(
                        resultSet.getInt("employee_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("bank_account_number"),
                        resultSet.getString("national_insurance_number")
                );
            }
            // return null if resultSet is empty
            return null;

        } catch (SQLException e) {
            throw new FailedToGetException(e.getMessage());
        }

    }
}
