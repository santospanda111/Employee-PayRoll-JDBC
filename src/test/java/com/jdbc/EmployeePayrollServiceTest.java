package com.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class EmployeePayrollServiceTest {
    /**
     * This test case will check the retrieved dataList size.
     */
    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        EmployeePayrollDBService employeePayrollDBService = new EmployeePayrollDBService();
        List<EmpPayRollData> employeePayrollDataList = employeePayrollDBService.readData();
        Assertions.assertEquals(3,employeePayrollDataList.size());
    }
}
