package com.example.prac02;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EmployeeViewModel employeeViewModel;
    private EditText staffIdInput, fullNameInput, birthDateInput, salaryInput;
    private TextView employeeListTextView;
    private Button addEmployeeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);

        staffIdInput = findViewById(R.id.staffIdInput);
        fullNameInput = findViewById(R.id.fullNameInput);
        birthDateInput = findViewById(R.id.birthDateInput);
        salaryInput = findViewById(R.id.salaryInput);
        employeeListTextView = findViewById(R.id.employeeListTextView);
        addEmployeeButton = findViewById(R.id.addEmployeeButton);

        employeeViewModel.getEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employeeList) {
                StringBuilder employeesText = new StringBuilder();
                for (Employee employee : employeeList) {
                    employeesText.append(employee.getStaffId()).append("-")
                            .append(employee.getFullName()).append("-")
                            .append(employee.getBirthDate()).append("-")
                            .append(employee.getSalary()).append("\n");
                }
                employeeListTextView.setText(employeesText.toString());
            }
        });

        addEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String staffId = staffIdInput.getText().toString();
                String fullName = fullNameInput.getText().toString();
                String birthDate = birthDateInput.getText().toString();
                String salary = salaryInput.getText().toString();

                if (!staffId.isEmpty() && !fullName.isEmpty() && !birthDate.isEmpty() && !salary.isEmpty()) {
                    Employee newEmployee = new Employee(staffId, fullName, birthDate, salary);
                    employeeViewModel.addEmployee(newEmployee);

                    staffIdInput.getText().clear();
                    fullNameInput.getText().clear();
                    birthDateInput.getText().clear();
                    salaryInput.getText().clear();
                }
            }
        });
    }
}
