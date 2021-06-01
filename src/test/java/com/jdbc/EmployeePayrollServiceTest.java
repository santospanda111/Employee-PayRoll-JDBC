package com.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
