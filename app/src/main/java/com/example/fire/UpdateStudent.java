package com.example.fire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateStudent extends AppCompatActivity {


    private EditText name, cgpa;
    private Button update, deleteBut;
    private DatabaseReference databaseReference;
    private String nameText, cgpaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        name = findViewById(R.id.std_up_Name);
        cgpa = findViewById(R.id.std_up_cgpa);
        update = findViewById(R.id.update_button);
        deleteBut = findViewById(R.id.delete_id);

        Bundle bundle = getIntent().getExtras();


        String Id = bundle.getString("id");
        nameText = bundle.getString("name");
        cgpaText = bundle.getString("cgpa");

        Log.d("Update", Id);

        name.setHint(nameText);
        cgpa.setHint(cgpaText);


        databaseReference = FirebaseDatabase.getInstance().getReference("Students");


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameText = name.getText().toString();
                cgpaText = cgpa.getText().toString();

                Students students = new Students(nameText, Id, cgpaText);
                Log.d("Update", nameText);
                Log.d("Update", cgpaText);




                databaseReference.child(Id).setValue(students).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Intent intent = new Intent(UpdateStudent.this, MainActivity.class) ;
                            startActivity(intent);

                        }
                    }
                });




            }
        });

        deleteBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(Id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(UpdateStudent.this, MainActivity.class) ;
                        startActivity(intent);
                    }
                })  ;
            }
        });


    }
}