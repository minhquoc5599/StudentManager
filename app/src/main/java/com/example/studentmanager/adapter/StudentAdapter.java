package com.example.studentmanager.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.studentmanager.R;
import com.example.studentmanager.model.Student;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {


    @NonNull private Activity activity;
    private int resource;
    @NonNull private List<Student> objects;

    public StudentAdapter(@NonNull Activity activity, int resource, @NonNull List<Student> objects) {
        super(activity, resource, objects);
        this.activity= activity;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View view = inflater.inflate(this.resource, null);

        TextView txtName = view.findViewById(R.id.txtName);
        TextView txtEmail = view.findViewById(R.id.txtEmail);

        Student student = this.objects.get(position);
        txtName.setText(student.getName());
        txtEmail.setText(student.getEmail());
        return view;
    }
}
