package com.jdbc;


import java.util.*;
import java.sql.*;
import java.time.LocalDate;

public class PayrollServiceDB {

    public PayrollServiceDB() {
    }

    /**
     * this will connect the database.
     * @return
     * @throws EmployeePayrollException
     */
    public Connection getConnection() throws EmployeePayrollException {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll?useSSL=false";
        String userName = "root";
        String password = "root1";
        Connection connection;
        try {
            System.out.println("Connecting to database:" + jdbcURL);
            connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection is successful!" + connection);
            return connection;
        } catch (SQLException e) {
            throw new EmployeePayrollException("Unable to connect / Wrong Entry");
        }
    }

    /**
     * this will read data from database.
     * @return
     * @throws EmployeePayrollException
     */
    public List<EmployeePayrollData> readData() throws EmployeePayrollException {
        String sql = "SELECT * FROM employee_payroll;";
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                LocalDate startDate = resultSet.getDate("start").toLocalDate();
                employeePayrollList.add(new EmployeePayrollData(id, name, salary, startDate));
            }
        } catch (SQLException e) {
            throw new EmployeePayrollException("Unable to Retrieve data From Table!");
        }
        return employeePayrollList;
    }

    /**
     * this will get the data from database by using parameter Name.
     * @param name
     * @return
     * @throws EmployeePayrollException
     */
    public List<EmployeePayrollData> getEmployeePayrollDataFromDB(String name) throws EmployeePayrollException {
        String sql = String.format("SELECT * FROM employee_payroll WHERE name='%s'", name);
        List<EmployeePayrollData> employeePayrollList = new ArrayList<EmployeePayrollData>();
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String objectname = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                LocalDate start = resultSet.getDate("start").toLocalDate();
                employeePayrollList.add(new EmployeePayrollData(id, objectname, salary, start));
            }
            return employeePayrollList;
        } catch (SQLException e) {
            throw new EmployeePayrollException("Unable to get data from database");
        }
    }

    /**
     * this method will update the salary.
     * @param name
     * @param salary
     * @return
     * @throws EmployeePayrollException
     */
    public int updateEmployeeDataUsingStatement(String name, double salary) throws EmployeePayrollException {
        String sql = String.format("UPDATE employee_payroll SET salary=%.2f WHERE name='%s'", salary, name);
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(sql);
            return rowsAffected;
        } catch (SQLException e) {
            throw new EmployeePayrollException("Unable To update data in database");
        }
    }
}