package com.example.btth03;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity {

    private EditText editTextName, editTextId, editTextGpa;
    private Button buttonAddStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        // Ánh xạ các view
        editTextName = findViewById(R.id.editTextStudentName);
        editTextId = findViewById(R.id.editTextStudentId);
        editTextGpa = findViewById(R.id.editTextStudentGpa);
        buttonAddStudent = findViewById(R.id.buttonAddStudent);

        // Thiết lập sự kiện khi người dùng nhấn nút Thêm
        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin người dùng nhập
                String name = editTextName.getText().toString();
                String studentId = editTextId.getText().toString();
                String gpaStr = editTextGpa.getText().toString();

                if (name.isEmpty() || studentId.isEmpty() || gpaStr.isEmpty()) {
                    // Kiểm tra nếu các trường rỗng
                    Toast.makeText(AddStudentActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    // Chuyển dữ liệu lại về MainActivity
                    Intent intent = new Intent();
                    intent.putExtra("name", name);
                    intent.putExtra("studentId", studentId);
                    intent.putExtra("gpa", Double.parseDouble(gpaStr));
                    setResult(RESULT_OK, intent);
                    finish();  // Kết thúc Activity và quay lại MainActivity
                }
            }
        });
    }
}

