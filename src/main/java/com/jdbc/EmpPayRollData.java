package com.jdbc;

import java.time.LocalDate;
import java.util.Objects;

public class EmpPayRollData {
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
    public EmpPayRollData(int id, String name, double salary, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.startDate = startDate;
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

    /**
     * this method will compare the objects and Indicates whether other object is "equal to" this one.
     * @param o
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpPayRollData that = (EmpPayRollData) o;
        return id == that.id && Double.compare(that.salary, salary) == 0 && Objects.equals(name, that.name);
    }
}