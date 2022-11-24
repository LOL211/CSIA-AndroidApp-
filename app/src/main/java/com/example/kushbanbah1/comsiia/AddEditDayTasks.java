package com.example.kushbanbah1.comsiia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.util.ArrayList;

public class AddEditDayTasks extends AppCompatActivity {

    TimePicker timepic;
    EditText taskcontainer;
    Button addday;
    boolean editing = false;
    RadioGroup todaytom;
    boolean today = true;
    Daytasks_class idtofind = new Daytasks_class();


    //manages add and edit day tasks functions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_daytask);
        timepic = findViewById(R.id.timepick);
        taskcontainer = findViewById(R.id.task_holder);
        addday = findViewById(R.id.set_button);
        todaytom = findViewById(R.id.today);
        todaytom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int index = group.indexOfChild(findViewById(group.getCheckedRadioButtonId())); //lets the user choose if they want to set alarm for today or tom

                if(index == 0)
                {
                    today = true;

                }
                else
                {
                    today = false;

                }
            }
        });

        Intent editornot = getIntent();
        int ID = editornot.getIntExtra("ID", 0);
        if(ID != 0)
        {
            setup(ID);
            editing = true;
            FloatingActionButton fab;
            fab = findViewById(R.id.fab);
            final Intent start = new Intent(this, HelpPage.class);
            start.putExtra("ID", 10);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    startActivity(start);
                }
            });
        }
        else
        {
            FloatingActionButton fab;
            fab = findViewById(R.id.fab);
            final Intent start = new Intent(this, HelpPage.class);
            start.putExtra("ID", 9);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    startActivity(start);
                }
            });
        }



    }
    @SuppressLint("NewApi")
    public void clicked(View v) //manages andd
    {


       utility util = new utility();
        ArrayList<Daytasks_class> list = util.ReadDayTask(getApplicationContext());

        if(!editing)
       {
           Daytasks_class newobj = new Daytasks_class();

           newobj.hour = timepic.getHour();
           newobj.min = timepic.getMinute();
           newobj.today = this.today;
           newobj.task = taskcontainer.getText().toString();
           newobj.setID(list);
        list.add(newobj);
        util.SaveDayTask(list, getApplicationContext());

        AlarmSetupDayTask startal = new AlarmSetupDayTask();
        startal.setalarm(newobj,getApplicationContext());
       }
       else
        {

            idtofind.hour =  timepic.getHour();
            idtofind.min = timepic.getMinute();
            idtofind.task = taskcontainer.getText().toString();
            idtofind.today = this.today;

            for(int i = 0; i< list.size(); i++)
            {
                if(list.get(i).ID == idtofind.ID)
                {

                    list.remove(i);
                    list.add(idtofind);

                    break;
                }
            }
            util.SaveDayTask(list, getApplicationContext());
            AlarmSetupDayTask startal = new AlarmSetupDayTask();
            startal.deletealarm(getApplicationContext(), idtofind.ID);
            startal.setalarm(idtofind,getApplicationContext());
        }

        Intent opendaytasks = new Intent(this, DayTaskView.class);
        startActivity(opendaytasks);



    }
    public void setup(int id) //only runs if editdaytask instead of always
    {

        idtofind = idtofind.findclassbynameorid(id,getApplicationContext());
        timepic.setHour(idtofind.hour);
        timepic.setMinute(idtofind.min);
        taskcontainer.setText(idtofind.task);
        addday.setText("Edit Day Task");
        RadioButton today = findViewById(R.id.radio_but);

       if(idtofind.today)
       {todaytom.check(R.id.radio_but);
       }
       else
       {
           todaytom.check(R.id.radio_but2);
       }



    }


}
