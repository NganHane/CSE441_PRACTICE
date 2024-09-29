package com.example.prac02;

public class Employee {
    private String staffId;
    private String fullName;
    private String birthDate;
    private String salary;

    public Employee(String staffId, String fullName, String birthDate, String salary) {
        this.staffId = staffId;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.salary = salary;
    }

    public String getStaffId() {
        return staffId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getSalary() {
        return salary;
    }
}

