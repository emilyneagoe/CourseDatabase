package com.example.coursedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    private EditText courseBox;
    private EditText instructorBox;
    private EditText creditsBox;
    private Button addButton;
    private boolean add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        courseBox = findViewById(R.id.courseBox);
        instructorBox = findViewById(R.id.instructorBox);
        creditsBox = findViewById(R.id.creditsBox);
        addButton = findViewById(R.id.addButton);

        Intent i = getIntent();

        add = i.getBooleanExtra("ADD", true);
        if (add) {
            addButton.setText("ADD");
        } else {
            addButton.setText("EDIT");
            courseBox.setText(i.getStringExtra("COURSE"));
            instructorBox.setText(i.getStringExtra("INSTRUCTOR"));
            creditsBox.setText(i.getStringExtra("CREDITS"));
        }


    }

    public void addPressed(View v) {
        String course = courseBox.getText().toString();
        String instructor = instructorBox.getText().toString();
        String credits = creditsBox.getText().toString();

        DatabaseManager dbm = new DatabaseManager(this);

        dbm.insert(course, instructor, credits);

        finish();

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
