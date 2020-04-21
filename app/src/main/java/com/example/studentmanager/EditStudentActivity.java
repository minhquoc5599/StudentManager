package com.example.studentmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmanager.model.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditStudentActivity extends AppCompatActivity {

    EditText edtMssv, edtName, edtEmail, edtPhoneNumber;
    private Toolbar toolbarEdit;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        addControl();
        setSupportActionBar(toolbarEdit);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbarEdit.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_refresh:
                edtMssv.setText(student.getmssv());
                edtName.setText(student.getName());
                edtEmail.setText(student.getEmail());
                edtPhoneNumber.setText(student.getPhoneNumber());
                break;
            case R.id.item_save:
                String mssv = edtMssv.getText().toString().trim();
                String name = edtName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String phoneNumber = edtPhoneNumber.getText().toString().trim();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("StudentDatabase");
                myRef.child(student.getId()).child("mssv").setValue(mssv);
                myRef.child(student.getId()).child("name").setValue(name);
                myRef.child(student.getId()).child("email").setValue(email);
                myRef.child(student.getId()).child("phoneNumber").setValue(phoneNumber);
                finish();
                Toast.makeText(EditStudentActivity.this, "Sửa dữ liệu thành công", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addControl() {
        edtMssv = findViewById(R.id.edtMssv);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        toolbarEdit = findViewById(R.id.toolbarEdit);
        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("STUDENT");
        if(student!=null){
            edtMssv.setText(student.getmssv());
            edtName.setText(student.getName());
            edtEmail.setText(student.getEmail());
            edtPhoneNumber.setText(student.getPhoneNumber());
        }else{
            Toast.makeText(EditStudentActivity.this, "Lỗi khi load dữ liệu", Toast.LENGTH_SHORT).show();
        }
    }
}
