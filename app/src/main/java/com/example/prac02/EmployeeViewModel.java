package com.example.prac02;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class EmployeeViewModel extends ViewModel {
    private final MutableLiveData<List<Employee>> employees;

    public EmployeeViewModel() {
        employees = new MutableLiveData<>(new ArrayList<>());
    }

    public LiveData<List<Employee>> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        List<Employee> currentList = employees.getValue();
        if (currentList != null) {
            currentList.add(employee);
            employees.setValue(currentList);
        }
    }
}

