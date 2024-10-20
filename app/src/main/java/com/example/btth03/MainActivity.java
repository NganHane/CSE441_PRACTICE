package com.example.btth03;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_STUDENT = 1;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private List<Student> studentList;
    private Button buttonOpenAddStudent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Tải danh sách sinh viên từ JSON
        studentList = loadStudentsFromJson();

        // Thiết lập adapter cho RecyclerView
        studentAdapter = new StudentAdapter(studentList, new StudentAdapter.OnStudentClickListener() {
            @Override
            public void onStudentClick(Student student) {
                Toast.makeText(MainActivity.this, "Clicked: " + student.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(studentAdapter);

        // Xử lý mở Activity thêm sinh viên
        buttonOpenAddStudent = findViewById(R.id.buttonOpenAddStudent);
        buttonOpenAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_STUDENT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_STUDENT && resultCode == RESULT_OK) {
            // Nhận dữ liệu từ AddStudentActivity
            String name = data.getStringExtra("name");
            String studentId = data.getStringExtra("studentId");
            double gpa = data.getDoubleExtra("gpa", 0.0);

            // Thêm sinh viên mới vào danh sách và cập nhật adapter
            Student newStudent = new Student(name, studentId, gpa);
            studentList.add(newStudent);
            studentAdapter.notifyItemInserted(studentList.size() - 1);
        }
    }

    // Hàm load JSON đã có trước đó
    private List<Student> loadStudentsFromJson() {
        List<Student> students = new ArrayList<>();
        try {
            InputStream is = getAssets().open("students.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String name = obj.getString("name");
                String studentId = obj.getString("studentId");
                double gpa = obj.getDouble("gpa");
                students.add(new Student(name, studentId, gpa));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return students;
    }
}
