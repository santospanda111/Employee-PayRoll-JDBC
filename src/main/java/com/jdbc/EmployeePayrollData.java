package com.jdbc;

import java.time.LocalDate;

public class EmployeePayrollData {
    public int id;
    public String name;
    public double salary;
    public LocalDate startDate;

    /**
     * this constructor will initialise the values of the given parameters.
     * @param id
     * @param name
     * @param salary
     * @param startDate
     */
    public EmployeePayrollData(int id, String name, double salary, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.startDate = startDate;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    /**
     * this method will compare the objects and Indicates whether other object is "equal to" this one.
     * @param obj
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        EmployeePayrollData that = (EmployeePayrollData) obj;
        if (id != that.id)
            return false;
        if (name == null) {
            if (that.name != null)
                return false;
        } else if (!name.equals(that.name))
            return false;
        if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(that.salary))
            return false;
        if (startDate == null) {
            if (that.startDate != null)
                return false;
        } else if (!startDate.equals(that.startDate))
            return false;
        return true;
    }


    /**
     * this override method will print the values.
     * @return
     */
    @Override
    public String toString() {
        return "EmployeePayrollData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", startDate=" + startDate +
                '}';
    }
}