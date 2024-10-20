package com.example.btth04;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentViewHolder extends RecyclerView.ViewHolder {

    private TextView tvName, tvMSSV, tvClass, tvGPA;

    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);

        // Khởi tạo các TextView từ item layout
        tvName = itemView.findViewById(R.id.tvName);
        tvMSSV = itemView.findViewById(R.id.tvMSSV);
        tvClass = itemView.findViewById(R.id.tvClass);
        tvGPA = itemView.findViewById(R.id.tvGPA);
    }

    public void setStudentDetails(Student student) {
        tvName.setText(student.getHoten());
        tvMSSV.setText(student.getMssv());
        tvClass.setText(student.getLop());
        tvGPA.setText(String.valueOf(student.getDiem()));
    }
}
