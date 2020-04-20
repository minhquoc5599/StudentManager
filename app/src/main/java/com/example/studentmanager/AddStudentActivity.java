package com.example.studentmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmanager.model.Student;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudentActivity extends AppCompatActivity {

    private EditText txtMssv, txtName, txtEmail, txtPhoneNumber;
    Toolbar toolbarAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        addControl();
        addStudent();
    }

    private void addStudent() {
        toolbarAdd.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.item_save){
                    String mssv = txtMssv.getText().toString();
                    String name = txtName.getText().toString();
                    String email = txtEmail.getText().toString();
                    String phoneNumber = txtPhoneNumber.getText().toString();
                    Student student = new Student(mssv, name, email, phoneNumber);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("StudentDatabase");
                    String id = myRef.push().getKey();
                    myRef.child(id).setValue(student).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(), "Thêm thành công",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Thêm thất bại "+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                return false;
            }
        });
    }

    private void addControl() {
        txtMssv = findViewById(R.id.txtMssv);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
        toolbarAdd = findViewById(R.id.toolbarAdd);
        toolbarAdd.inflateMenu(R.menu.menu_save);
    }
}
