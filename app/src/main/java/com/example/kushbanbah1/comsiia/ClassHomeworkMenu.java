package com.example.kushbanbah1.comsiia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;


public class ClassHomeworkMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_classhomework);
        setup();
    }


    public void manageHW(View v) {
        Intent open_manageHW = new Intent(this, HomeworkClassView.class);
        startActivity(open_manageHW);
    }

    public void manageClass(View v) {
        Intent open_manageClass = new Intent(this, SchoolClassView.class);
        startActivity(open_manageClass);
    }

    @SuppressLint("SetTextI18n")
    public void setup() {
        FloatingActionButton fab;
        fab = findViewById(R.id.fab);
        final Intent start = new Intent(this, HelpPage.class);
        start.putExtra("ID", 1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(start);
            }
        });
        TextView upcoming = findViewById(R.id.up_hw);
        Homework_class util = new Homework_class();

        SchoolClass_class subject = new SchoolClass_class();
        util = util.recentHW(getApplicationContext());

        upcoming.setLines(2);
        if (util.Task == null) {
            upcoming.setText("No Upcoming tasks!");
        } else

        {

            Calendar c = Calendar.getInstance();
            int diff =(int) ( (c.getTimeInMillis() - util.duedate.getTime())/ (24 * 60 * 60 * 1000));

            upcoming.setText("Upcoming Task of " + subject.findclassbynameorid(util.Class_ID, getApplicationContext()).name + " is: " + util.Task+". "+((diff*-1)+1)+" day/s left");
        }

    }

    public void onResume() {
        super.onResume();
        setup();

    }



}
