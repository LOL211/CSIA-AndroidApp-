package com.example.kushbanbah1.comsiia;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    AlarmManager alrmnger;
    PendingIntent pendingIntent;
    CheckBox disablealarm;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

       setup();
    }

    public void setup()
    {
        FloatingActionButton fab;
        fab = findViewById(R.id.fab);
        final Intent start = new Intent(this, HelpPage.class);
        start.putExtra("ID", 0);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(start);
            }
        });
        final EditText bob = findViewById(R.id.day_enterer);
        final utility util = new utility();

      SchoolClassAlarm a = util.ReadAlarmFile(getApplicationContext());


        String g = a.day_1_2+"";
        bob.setText(g);
        disablealarm = findViewById(R.id.radio_but);

        disablealarm.setChecked(a.enabled);

        disablealarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked) {
                        enablealarms();
                        SchoolClassAlarm alam = util.ReadAlarmFile(getApplicationContext());

                        alam.enabled = true;

                        util.SaveAlarmFile(alam, getApplicationContext());

                    } else {
                        disablealarms();
                        SchoolClassAlarm alam = util.ReadAlarmFile(getApplicationContext());

                        alam.enabled = false;

                        util.SaveAlarmFile(alam, getApplicationContext());

                    }
                }



        });



        bob.setInputType(2);

        if(a.enabled)
        {enablealarms();}



        TextWatcher watch = new TextWatcher() {
            String backup;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                backup = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()!=0) {

                    if ((Integer.parseInt(s.toString()) != 1 && Integer.parseInt(s.toString()) != 2))
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Incorrect input, only 1 or 2 is accepted",
                                Toast.LENGTH_SHORT);

                        toast.show();
                        bob.setText(backup);

                    }
                    else
                    {
                        disablealarms();

                        SchoolClassAlarm alam = util.ReadAlarmFile(getApplicationContext());
                        alam.day_1_2 = Integer.parseInt(s.toString());

                        util.SaveAlarmFile(alam, getApplicationContext());
                        if(alam.enabled)
                        {enablealarms();}


                    }
                }
            }
        };

        bob.addTextChangedListener(watch);



    }
    public void OpenHWClass(View v)
    {
        Intent openHomeworkMenu = new Intent(this, ClassHomeworkMenu.class);

        startActivity(openHomeworkMenu);
    }

    public void OpenDayTasks(View v)
    {
        Intent openDayTasks = new Intent(this,DayTaskView.class);
       startActivity(openDayTasks);

    }

    public void disablealarms()
    {
        if (alrmnger != null) {
               alrmnger.cancel(pendingIntent);
            }
            AlarmSetupSchoolClass del = new AlarmSetupSchoolClass();
            del.deletealarms();
    }

    public void enablealarms()
    {
        alrmnger = (AlarmManager)getSystemService(Context.ALARM_SERVICE);


        Intent intent = new Intent();
        intent.setAction("SETUPALRM");
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alrmnger.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,SystemClock.elapsedRealtime() + 110, AlarmManager.INTERVAL_FIFTEEN_MINUTES/240, pendingIntent);
    }


}
