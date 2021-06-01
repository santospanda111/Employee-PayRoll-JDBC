package com.jdbc;


import java.util.*;
import java.sql.*;
import java.time.LocalDate;

public class PayrollServiceDB {
    private static PayrollServiceDB employeePayrollServiceDB;
    private PreparedStatement preparedStatement;

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

    /**
     * this method will update the salary using prepared statement.
     * @param name
     * @param salary
     * @return
     * @throws EmployeePayrollException
     */
    public int updateEmployeePayrollDataUsingPreparedStatement(String name, double salary)
            throws EmployeePayrollException {
        if (this.preparedStatement == null) {
            this.prepareStatementForEmployeePayroll();
        }
        try {
            preparedStatement.setDouble(1, salary);
            preparedStatement.setString(2, name);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            throw new EmployeePayrollException("Unable to use prepared statement");
        }
    }

    /**
     * this method will execute the statement.
     * @throws EmployeePayrollException
     */
    private void prepareStatementForEmployeePayroll() throws EmployeePayrollException {
        try {
            Connection connection = this.getConnection();
            String sql = "UPDATE employee_payroll SET salary=? WHERE name=?";
            this.preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new EmployeePayrollException("Unable to prepare statement");
        }
    }

    /**
     * this method will give result according to the sql query.
     * @param resultSet
     * @throws EmployeePayrollException
     */
    private List<EmployeePayrollData> getEmployeePayrollListFromResultset(ResultSet resultSet)
            throws EmployeePayrollException {
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String objectname = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                LocalDate startDate = resultSet.getDate("start").toLocalDate();
                employeePayrollList.add(new EmployeePayrollData(id, objectname, salary, startDate));
            }
            return employeePayrollList;
        } catch (SQLException e) {
            throw new EmployeePayrollException("Unable to use the result set!");
        }
    }

    /**
     * this method will check the data in a given date range.
     * @param startDate
     * @param endDate
     * @throws EmployeePayrollException
     */
    public List<EmployeePayrollData> getEmployeePayrollDataByStartingDate(LocalDate startDate, LocalDate endDate)
            throws EmployeePayrollException {
        String sql = String.format(
                "SELECT * FROM employee_payroll WHERE start BETWEEN cast('%s' as date) and cast('%s' as date);",
                startDate.toString(), endDate.toString());
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            return this.getEmployeePayrollListFromResultset(resultSet);
        } catch (SQLException e) {
            throw new EmployeePayrollException("Connection Failed.");
        }
    }
}