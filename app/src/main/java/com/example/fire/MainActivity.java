package com.example.fire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText name, roll, cgpa;
    private Button addStudent;
    private DatabaseReference databaseReference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editTextTextPersonName);
        roll = findViewById(R.id.editTextTextPersonRoll);
        cgpa = findViewById(R.id.editTextTextPersonCgpa);
        addStudent = findViewById(R.id.addStudent_id);


        /*------------------- Fireabase ----------------- */

        databaseReference = FirebaseDatabase.getInstance().getReference("Students") ;


        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameString = name.getText().toString();
                String rollString = roll.getText().toString();
                String cgpaString = cgpa.getText().toString();

                if(!(TextUtils.isEmpty(nameString)) && !(TextUtils.isEmpty(rollString)) && !(TextUtils.isEmpty(cgpaString)))
                {
                        String id = databaseReference.push().getKey() ;
                        Students students = new Students(nameString, cgpaString, cgpaString) ;

                        databaseReference.child(id).setValue(students) ;

                    Toast.makeText(MainActivity.this, "New Student Added", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}