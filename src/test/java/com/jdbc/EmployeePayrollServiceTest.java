package com.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

public class EmployeePayrollServiceTest {
    /**
     * This test case will check the retrieved dataList size.
     */
    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() throws EmployeePayrollException {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData;
        employeePayrollData = employeePayrollService.readEmployeePayrollData();
        Assertions.assertEquals(3, employeePayrollData.size());
    }

    /**
     * this method will check whether the salary is updated or not.
     * @throws EmployeePayrollException
     */
    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB() throws EmployeePayrollException {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.updateEmployeeSalary("Terisa", 3000000.00);
        boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Terisa");
        Assertions.assertTrue(result);
    }

    /**
     * this method will check whether the salary is updated or not by using prepared statement.
     * @throws EmployeePayrollException
     */
    @Test
    public void givenEmployeePayroll_WhenUpdatedUsingPreparedStatement_ShouldSyncWithDB() throws EmployeePayrollException {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData;
        employeePayrollData =employeePayrollService.readEmployeePayrollData();
        employeePayrollService.updateEmployeeSalary("Terisa",3000000.00);
        boolean result=employeePayrollService.checkEmployeePayrollInSyncWithDB("Terisa");
        Assertions.assertTrue(result);
    }
    /**
     * this method will check the data is present in a given date range or not.
     * @throws EmployeePayrollException
     */
    @Test
    public void givenEmployeePayrollData_WhenRetrievedBasedOnStartDate_ShouldReturnResult() throws EmployeePayrollException {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayrollData();
        LocalDate startDate = LocalDate.parse("2018-01-01");
        LocalDate endDate = LocalDate.now();
        List<EmployeePayrollData> matchingRecords = employeePayrollService.getEmployeePayrollDataByStartDate(startDate,endDate);
        Assertions.assertEquals(matchingRecords.get(0), employeePayrollService.getEmployeePayrollData("Bill"));
    }

    /**
     * this test case will check the max salary group by gender.
     * @throws EmployeePayrollException
     */
    @Test
    public void givenEmployee_PerformedVariousOperations_ShouldGiveResult() throws EmployeePayrollException {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayrollData();
        Map<String, Double> maxSalaryByGender = employeePayrollService.performOperationByGender("salary", "MAX");
        Assertions.assertEquals(3000000.0, maxSalaryByGender.get("F"), 0.0);
    }
}
