package org.example.db;

import org.example.cli.DeliveryEmployee;
import org.example.client.FailedToCreateException;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryEmployeeDAO {

    // create instance of database connector class
    DatabaseConnector databaseConnector = new DatabaseConnector();

    /**
     * inserts a new employee into employee table, then adds employee to delivery employee table via employee id
     * @param deliveryEmployee
     * @return
     * @throws SQLException
     */
    public int createDeliveryEmployee(DeliveryEmployee deliveryEmployee) throws FailedToCreateException {

        // establish connection with database
        try {
            Connection c = databaseConnector.getConnection();

            // sql statement string for creating employee
            String sqlEmployee = "INSERT INTO employee (first_name, last_name, salary, bank_account_number, national_insurance_number) VALUES (?, ?, ?, ?, ?);";

            // prepare sql statement
            PreparedStatement statementEmployee = c.prepareStatement(sqlEmployee, Statement.RETURN_GENERATED_KEYS);

            // set attributes of new employee
            statementEmployee.setString(1, deliveryEmployee.getFirst_name());
            statementEmployee.setString(2, deliveryEmployee.getLast_name());
            statementEmployee.setDouble(3, deliveryEmployee.getSalary());
            statementEmployee.setString(4, deliveryEmployee.getBank_account_number());
            statementEmployee.setString(5, deliveryEmployee.getNi_number());

            // execute sql statement
            statementEmployee.executeUpdate();

            // get id of new delivery employee
            ResultSet resultSet = statementEmployee.getGeneratedKeys();

            if (!resultSet.next()) {
                return -1;
            }

            // sql statement for inserting employee into employee delivery table
            String sqlDeliveryEmployee = "INSERT INTO delivery_employee\n" +
                    "(employee_id) \n" +
                    "VALUES (?);";

            // prepare sql statement
            PreparedStatement statementDeliveryEmployee = c.prepareStatement(sqlEmployee, Statement.RETURN_GENERATED_KEYS);

            // set id of delivery employee
            statementDeliveryEmployee.setInt(1, resultSet.getInt(1));

            // execute sql statement
            statementDeliveryEmployee.executeUpdate();

            // if resultSet contains id of new employee, return it
            return resultSet.getInt(1);

        }catch(SQLException e ){
            throw new FailedToCreateException(e.getMessage() + deliveryEmployee.toString());

        }

    }



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
}
