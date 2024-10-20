package com.example.btth03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList;
    private OnStudentClickListener listener;

    // Giao diện lắng nghe sự kiện click vào sinh viên
    public interface OnStudentClickListener {
        void onStudentClick(Student student);
    }

    public StudentAdapter(List<Student> studentList, OnStudentClickListener listener) {
        this.studentList = studentList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout cho từng item sinh viên
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        // Gán dữ liệu cho item tại vị trí hiện tại
        Student student = studentList.get(position);
        holder.bind(student, listener);
    }

    @Override
    public int getItemCount() {
        // Trả về số lượng sinh viên trong danh sách
        return studentList.size();
    }

    // Lớp ViewHolder cho mỗi item sinh viên
    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewId;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewStudentName);
            textViewId = itemView.findViewById(R.id.textViewStudentId);
        }

        // Gán dữ liệu cho item
        public void bind(final Student student, final OnStudentClickListener listener) {
            textViewName.setText(student.getName());
            textViewId.setText(student.getStudentId());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onStudentClick(student);
                }
            });
        }
    }
}
