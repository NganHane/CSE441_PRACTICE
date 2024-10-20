package com.example.btth04;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class EditStudentActivity extends AppCompatActivity {

    private EditText editTextName, editTextMSSV, editTextClass, editTextGPA;
    private Button buttonUpdate;
    private FirebaseFirestore db;
    private String studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        editTextName = findViewById(R.id.editTextName);
        editTextMSSV = findViewById(R.id.editTextMSSV);
        editTextClass = findViewById(R.id.editTextClass);
        editTextGPA = findViewById(R.id.editTextGPA);
        buttonUpdate = findViewById(R.id.buttonUpdate);

        db = FirebaseFirestore.getInstance();

        // Lấy studentId từ Intent
        studentId = getIntent().getStringExtra("studentId");
        loadStudentData();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStudent();
            }
        });
    }

    private void loadStudentData() {
        DocumentReference docRef = db.collection("sinhvien").document(studentId);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null && task.getResult().exists()) {
                Student student = task.getResult().toObject(Student.class);
                editTextName.setText(student.getHoten());
                editTextMSSV.setText(student.getMssv());
                editTextClass.setText(student.getLop());
                editTextGPA.setText(String.valueOf(student.getDiem()));
            } else {
                Toast.makeText(EditStudentActivity.this, "Không tìm thấy sinh viên", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateStudent() {
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
        db.collection("sinhvien").document(studentId).set(student)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(EditStudentActivity.this, "Cập nhật sinh viên thành công", Toast.LENGTH_SHORT).show();
                    finish(); // Quay lại màn hình chính
                })
                .addOnFailureListener(e -> Toast.makeText(EditStudentActivity.this, "Cập nhật sinh viên thất bại", Toast.LENGTH_SHORT).show());
    }
}
