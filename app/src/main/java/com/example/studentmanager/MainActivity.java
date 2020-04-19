package com.example.studentmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.studentmanager.adapter.StudentAdapter;
import com.example.studentmanager.model.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView lvStudent;
    ArrayList<Student> studentArrayList;
    StudentAdapter studentAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_add);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.item_add){
                    Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        lvStudent = findViewById(R.id.lvStudent);

        studentArrayList = new ArrayList<>();
        getData();

        studentAdapter = new StudentAdapter(this, R.layout.listview_item, studentArrayList);
        lvStudent.setAdapter(studentAdapter);
    }

    private void getData(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("StudentDatabase");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentAdapter.clear();
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    Student student = data.getValue(Student.class);
                    student.setId(data.getKey());
                    studentAdapter.add(student);
                }
                Toast.makeText(getApplicationContext(), "Load Data Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Load Data Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
