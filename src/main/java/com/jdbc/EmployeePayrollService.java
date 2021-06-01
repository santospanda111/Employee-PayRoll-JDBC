package com.jdbc;
import java.time.LocalDate;
import java.util.*;

public class EmployeePayrollService {
    /**
     * here i have created an object of PayrollServiceDB database.
     * created a list of EmployeePayrollData.
     */
    public PayrollServiceDB payrollServiceDB;
    public List<EmployeePayrollData> employeePayrollList;

    /**
     * this constructor will call object class constructor and initialise the object.
     */
    public EmployeePayrollService() {
        super();
        this.payrollServiceDB = new PayrollServiceDB();
    }

    /**
     * this method will return the read data.
     * @throws EmployeePayrollException
     */
    public List<EmployeePayrollData> readEmployeePayrollData() throws EmployeePayrollException {
        return this.payrollServiceDB.readData();
    }

    /**
     * this method will update the salary.
     * @param name
     * @param salary
     * @throws EmployeePayrollException
     */
    public void updateEmployeeSalary(String name, double salary) throws EmployeePayrollException {
        int result = new PayrollServiceDB().updateEmployeePayrollDataUsingPreparedStatement(name, salary);
        if (result == 0)
            return;
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
        if (employeePayrollData != null)
            employeePayrollData.setSalary(salary);
    }

    /**
     * this method will return the data according to name.
     * @param name
     */
    public EmployeePayrollData getEmployeePayrollData(String name) {
        return this.employeePayrollList.stream()
                .filter(employeePayrollObject -> employeePayrollObject.getName().equals(name)).findFirst().orElse(null);
    }

    public boolean checkEmployeePayrollInSyncWithDB(String name) throws EmployeePayrollException {
        List<EmployeePayrollData> employeePayrollDataList = new PayrollServiceDB().getEmployeePayrollDataFromDB(name);
        return employeePayrollDataList.get(0).equals(getEmployeePayrollData(name));
    }

    /**
     * this will get the data in a given date range.
     * @param startDate
     * @param endDate
     * @return
     * @throws EmployeePayrollException
     */
    public List<EmployeePayrollData> getEmployeePayrollDataByStartDate(LocalDate startDate, LocalDate endDate)
            throws EmployeePayrollException {
        return this.payrollServiceDB.getEmployeePayrollDataByStartingDate(startDate, endDate);
    }
}