package com.example.studentmanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentmanager.model.Student;

public class DetailStudentActivity extends AppCompatActivity {

    TextView txtMssv, txtName, txtEmail, txtPhoneNumber;
    private Toolbar toolbarDetail;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_student);
        addControl();
        actionToolbar();
    }

    private void actionToolbar() {
        setSupportActionBar(toolbarDetail);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbarDetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControl() {
        txtMssv = findViewById(R.id.txtMssv);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
        toolbarDetail = findViewById(R.id.toolbarDetail);
        toolbarDetail.inflateMenu(R.menu.menu_edit);
        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("STUDENT");
        if(student!=null){
            txtMssv.setText(student.getmssv());
            txtName.setText(student.getName());
            txtEmail.setText(student.getEmail());
            txtPhoneNumber.setText(student.getPhoneNumber());
        }else{
            Toast.makeText(DetailStudentActivity.this, "Lỗi khi load dữ liệu", Toast.LENGTH_SHORT).show();
        }
    }
}
