package com.example.btth04;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore firestore;
    private FirestoreRecyclerAdapter<Student, StudentViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firestore = FirebaseFirestore.getInstance();

        loadStudentData();

        findViewById(R.id.btnAddStudent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadStudentData() {
        Query query = firestore.collection("sinhvien");

        FirestoreRecyclerOptions<Student> options =
                new FirestoreRecyclerOptions.Builder<Student>()
                        .setQuery(query, Student.class)
                        .build();

        adapter = new FirestoreRecyclerAdapter<Student, StudentViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull StudentViewHolder holder, int position, @NonNull Student model) {
                holder.setStudentDetails(model);

                holder.itemView.findViewById(R.id.buttonEditStudent).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, EditStudentActivity.class);
                        intent.putExtra("studentId", getSnapshots().getSnapshot(position).getId());
                        startActivity(intent);
                    }
                });

                holder.itemView.findViewById(R.id.buttonDeleteStudent).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteStudent(getSnapshots().getSnapshot(position).getId());
                    }
                });
            }

            @NonNull
            @Override
            public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
                return new StudentViewHolder(view);
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    private void deleteStudent(String studentId) {
        firestore.collection("sinhvien").document(studentId).delete().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(MainActivity.this, "Xóa sinh viên thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Xóa sinh viên thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
