package com.example.coursedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {


    private TextView courseView;
    private TextView instructorView;
    private TextView creditsView;
    private Button editDeleteButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        courseView = findViewById(R.id.courseView);
        instructorView = findViewById(R.id.instructorView);
        creditsView = findViewById(R.id.creditsView);

        DatabaseManager dbm = new DatabaseManager(this);
        Intent i = getIntent();
        String course = i.getStringExtra("COURSE");
        String[] entry = dbm.get(course);
        courseView.setText(entry[0]);
        instructorView.setText(entry[1]);
        creditsView.setText(entry[2]);


    }

    public void deletePressed(View view) {
        DatabaseManager dbm = new DatabaseManager(this);
        Intent i = getIntent();
        String title = i.getStringExtra("COURSE");


        Intent o = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(o);

        dbm.deleteCourse(title);
    }

}
