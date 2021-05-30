package com.example.fire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudent extends AppCompatActivity {

    private EditText name, roll, cgpa;
    private Button addStudent;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);


        name = findViewById(R.id.std_Name);
        roll = findViewById(R.id.std_Roll);
        cgpa = findViewById(R.id.std_cgpa);
        addStudent = findViewById(R.id.add_button);


        /*------------------- Fireabase ----------------- */

        databaseReference = FirebaseDatabase.getInstance().getReference("Students") ;


        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameString = name.getText().toString();
                String rollString = roll.getText().toString();
                String cgpaString = cgpa.getText().toString();

                if (!(TextUtils.isEmpty(nameString)) && !(TextUtils.isEmpty(rollString)) && !(TextUtils.isEmpty(cgpaString))) {

                    Students students = new Students(nameString, rollString, cgpaString);

                    databaseReference.child(rollString).setValue(students).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(AddStudent.this, "New Student Added", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(AddStudent.this, MainActivity.class) ;
                            startActivity(intent);

                        }
                    });


                }

            }
        });
    }
}