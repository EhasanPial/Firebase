package com.example.fire;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyHolder> {

    List<Students> students;
    private final ListClick listClick;

    public Adapter(ListClick listClick) {
        this.listClick = listClick;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_layout, null, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        Students student = students.get(position);
        holder.name.setText(student.getName());
        holder.id.setText(student.getRoll());
        holder.cgpa.setText(student.getCgpa());


    }

    @Override
    public int getItemCount() {
        if (students == null)
            return 0;

        return students.size();
    }

    public void setStudents(List<Students> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    public interface ListClick {
        void onListClick(Students student);
    }

    public class MyHolder extends RecyclerView.ViewHolder  {

        private TextView id, name, cgpa;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.std_id);
            name = itemView.findViewById(R.id.std_name);
            cgpa = itemView.findViewById(R.id.std_cgpa);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listClick.onListClick(students.get(getAdapterPosition()));
                    notifyItemChanged(getAdapterPosition());
                    return false;
                }
            });


        }


    }
}
