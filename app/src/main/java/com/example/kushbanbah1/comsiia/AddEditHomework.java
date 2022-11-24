package com.example.kushbanbah1.comsiia;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class AddEditHomework extends AppCompatActivity {

    CalendarView calender;
    Spinner SubList;
    Date datedue;
      utility util = new utility();
      boolean editing = false;
      Homework_class edit = new Homework_class();
      EditText Desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_homwork);
       Desc =  findViewById(R.id.Edit);

        SubList =  findViewById(R.id.spinner);

       int ID =  getIntent().getIntExtra("ID", 0);
        if(ID!=0)
        {
            editing= true;
            edit = edit.findbyID(ID, getApplicationContext());
            editsetup();
        }
       else {
            setup();
        }

    }

    public void setup() {


        FloatingActionButton fab;
        fab = findViewById(R.id.fab);
        final Intent start = new Intent(this, HelpPage.class);
        start.putExtra("ID", 6);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(start);
            }
        });


        ArrayList<SchoolClass_class> list = util.ReadSchoolClass(getApplicationContext());


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);
        for(int i = 0; i<list.size();i++)
        {
          adapter.add(list.get(i).name);
        }


        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal= Calendar.getInstance();
        datedue = new Date();

        SubList.setAdapter(adapter);


        calender =  findViewById(R.id.duedate);
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView arg0, int year, int month, int date) { //runs whenever user selects a date, allows for date to be constantly saved
                datedue = new Date();
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                month++;

                String day, mth;
                mth = Integer.toString(month);
                day = Integer.toString(date);

                try {

                    if (month < 10) {

                        mth = "0"+Integer.toString(month);
                    }
                    if (date < 10) {


                        day = "0"+Integer.toString(date);

                    }
                    datedue = fmt.parse(year + "-" + (mth)  + "-" + day);



                } catch (Exception e) {

                }
            }
        });
    }


    public void clicked(View v)
    {
       String name =  SubList.getSelectedItem().toString();

       String desc = Desc.getText().toString();


        if(editing)
        {
            SchoolClass_class utilc = new SchoolClass_class();
           utilc = utilc.findclassbynameorid(name, getApplicationContext());

            edit.Task = desc;
            edit.Class_ID = utilc.ID;
            edit.duedate = datedue;
            ArrayList<Homework_class> list = util.ReadHW(getApplicationContext());
            for(int c = 0; c<list.size(); c++)
            {
                if(list.get(c).Homework_ID == edit.Homework_ID)
                {
                    list.remove(c);
                    list.add(edit);
                }

            }
            util.SaveHW(list, getApplicationContext());

        }
        else {
            Homework_class newhw = new Homework_class();
            newhw.Task= desc;
            newhw.duedate = datedue;

            SchoolClass_class utilc = new SchoolClass_class();
            utilc= utilc.findclassbynameorid(name, getApplicationContext());
            newhw.Class_ID = utilc.ID;


            ArrayList<Homework_class> list = util.ReadHW(getApplicationContext());
            newhw.makeID(list);
            
            list.add(newhw);

            util.SaveHW(list, getApplicationContext());
        }
       //saves all the inputs into a new obj, sets it up by giving it an ID then saves into master file
        startActivity(new Intent(this, HomeworkClassView.class));


    }

    public void editsetup()
    {   FloatingActionButton fab;
        fab = findViewById(R.id.fab);
        final Intent start = new Intent(this, HelpPage.class);
        start.putExtra("ID", 7);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(start);
            }
        });
        Desc.setText(edit.Task);
        Button add_but = findViewById(R.id.Add_HW);
        add_but.setText("Edit Homework");

        ArrayList<SchoolClass_class> list = util.ReadSchoolClass(getApplicationContext());


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);

        for(int q = 0; q<list.size(); q++)
        {
            if(list.get(q).ID ==  edit.Class_ID)
            {
                adapter.add(list.get(q).name);
            }
        }


        for(int i = 0; i<list.size();i++)
        {
            if(!(list.get(i).ID ==  edit.Class_ID)) {
                adapter.add(list.get(i).name);
            }
        }


        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        datedue = edit.duedate;
        SubList.setAdapter(adapter);


        calender = findViewById(R.id.duedate);


        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView arg0, int year, int month, int date) {
                datedue = new Date();
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                month++;

                String day, mth;
                mth = Integer.toString(month);
                day = Integer.toString(date);

                try {

                    if (month < 10) {

                        mth = "0"+Integer.toString(month);
                    }
                    if (date < 10) {


                        day = "0"+Integer.toString(date);

                    }
                    datedue = fmt.parse(year + "-" + (mth)  + "-" + day);



                } catch (Exception e) {

                }
            }
        });
    }
}
