package com.example.btth04;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddStudentActivity extends AppCompatActivity {

    private EditText editTextName, editTextMSSV, editTextClass, editTextGPA;
    private Button buttonSave;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        editTextName = findViewById(R.id.editTextName);
        editTextMSSV = findViewById(R.id.editTextMSSV);
        editTextClass = findViewById(R.id.editTextClass);
        editTextGPA = findViewById(R.id.editTextGPA);
        buttonSave = findViewById(R.id.buttonSave);

        db = FirebaseFirestore.getInstance();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();
            }
        });
    }

    private void addStudent() {
        String name = editTextName.getText().toString();
        String mssv = editTextMSSV.getText().toString();
        String lop = editTextClass.getText().toString();
        String gpaString = editTextGPA.getText().toString();

        if (name.isEmpty() || mssv.isEmpty() || lop.isEmpty() || gpaString.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        double gpa = Double.parseDouble(gpaString);

        Student student = new Student(name, mssv, lop, gpa);
        db.collection("sinhvien").document(mssv).set(student)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(AddStudentActivity.this, "Thêm sinh viên thành công", Toast.LENGTH_SHORT).show();
                    finish(); // Quay lại màn hình chính
                })
                .addOnFailureListener(e -> Toast.makeText(AddStudentActivity.this, "Thêm sinh viên thất bại", Toast.LENGTH_SHORT).show());
    }
}
