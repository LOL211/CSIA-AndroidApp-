package com.example.kushbanbah1.comsiia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class DayTaskView extends AppCompatActivity {

    LinearLayout layout;
    NestedScrollView scroll;
    ArrayList<Integer> ID = new ArrayList<Integer>();
    utility util = new utility();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_day_tasks);
        layout =  findViewById(R.id.Holder);
        scroll = findViewById(R.id.scrollview);

        setup();
    }

    public void clicked(View v)
    {
    Intent opendaytasks = new Intent(this, AddEditDayTasks.class);
    startActivity(opendaytasks);
    }
    public void onResume() {
        super.onResume();
        setup();

    }

    @SuppressLint("SetTextI18n")
    public void setup()
    {
        FloatingActionButton fab;
        fab = findViewById(R.id.fab);
        final Intent start = new Intent(this, HelpPage.class);
        start.putExtra("ID", 8);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(start);
            }
        });

        if((layout).getChildCount() > 0)
            (layout).removeAllViews();

        ArrayList<Daytasks_class> list = util.ReadDayTask(getApplicationContext());
        if(list!= null) {
            for (int i = 0; i < list.size(); i++) {

                LinearLayout layouti = new LinearLayout(getApplicationContext());
                layouti.setOrientation(LinearLayout.VERTICAL);
                TextView itemnew = new TextView(getApplicationContext());
                String label = "";
                if(list.get(i).hour<12 &&  list.get(i).hour != 0)
                {
                   if(list.get(i).min >=10)
                    label = "You have to do "+list.get(i).task+" at "+list.get(i).hour+":"+Integer.toString(list.get(i).min)+"am";

                   else
                      label = "You have to do "+list.get(i).task+" at "+list.get(i).hour+":0"+Integer.toString(list.get(i).min)+"am";
                }

                 else if(list.get(i).hour>12)
                {
                    if(list.get(i).min >=10)
                    label = "You have to do "+list.get(i).task+" at "+(list.get(i).hour-12)+":"+Integer.toString(list.get(i).min)+"pm";

                    else
                    label = "You have to do "+list.get(i).task+" at "+(list.get(i).hour-12)+":0"+Integer.toString(list.get(i).min)+"pm";
                }
                else if(list.get(i).hour == 12)
                {
                    if(list.get(i).min>=10)
                    label = "You have to do "+list.get(i).task+" at "+list.get(i).hour+":"+Integer.toString(list.get(i).min)+"pm";

                    else
                        label = "You have to do "+list.get(i).task+" at "+list.get(i).hour+":0"+Integer.toString(list.get(i).min)+"pm";
                }
                else if (list.get(i).hour == 0)
                {
                    if(list.get(i).min >=10)
                    label = "You have to do "+list.get(i).task+" at "+12+":"+Integer.toString(list.get(i).min)+"am";

                    else
                        label = "You have to do "+list.get(i).task+" at "+12+":0"+Integer.toString(list.get(i).min)+"am";
                }


                itemnew.setText(label);
                Button delete_but;
                delete_but = new Button(getApplicationContext());
                delete_but.setTag(list.get(i).ID);
                delete_but.setText("Delete Day Task");
                Random rand = new Random();

                boolean found;
                do {
                    found = true;
                    int test = rand.nextInt(1000) + 1;

                    for (int c = 0; c < ID.size(); c++) {

                        if (test == ID.get(c)) {
                            found = false;
                            break;
                        }

                    }


                    if (found) {
                        delete_but.setId(test);
                        break;
                    }
                } while (true);


                delete_but.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        Daytasks_class classtest = new Daytasks_class();
                        Button but =  findViewById(v.getId());


                        delete(classtest.findclassbynameorid(Integer.parseInt(but.getTag().toString()), getApplicationContext()));




                    }
                });

                Button edit_but = new Button(getApplicationContext());


                do {
                    found = true;
                    int test = rand.nextInt(1000) + 1;

                    for (int c = 0; c < ID.size(); c++) {

                        if (test == ID.get(c)) {
                            found = false;
                            break;
                        }

                    }


                    if (found) {
                        edit_but.setId(test);
                        break;
                    }
                } while (true);
                edit_but.setTag(list.get(i).ID+"");
                edit_but.setText("Edit DayTask");

                edit_but.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                       Daytasks_class classtest = new Daytasks_class();
                        Button but = findViewById(view.getId());
                        Daytasks_class classfind = classtest.findclassbynameorid(Integer.parseInt(but.getTag().toString()), getApplicationContext());
                        Intent openclass = new Intent(view.getContext(), AddEditDayTasks.class);

                        openclass.putExtra("ID", classfind.ID);
                        startActivity(openclass);
                    }
                });

                Typeface tfFutura = Typeface.create("casual",Typeface.NORMAL);
                itemnew.setTypeface(tfFutura);
                itemnew.setTextSize(20);
                delete_but.setTypeface(tfFutura);
                layouti.addView(itemnew);
                layouti.addView(delete_but);
                edit_but.setTypeface(tfFutura);
                layouti.addView(edit_but);
                Space space = new Space(getApplicationContext());
                space.setMinimumHeight(40);
                layouti.addView(space);
                layout.addView(layouti);
            }

            //creates buttons and text labels dynamically and gives buttons unique IDs since they are being created dynamically and thier listeners will otherwise not work properly since .getID
            //will not work properly. Displays all the day tasks stored by the user using this dynamic system and lets them edit, add more or delete thier task
        }
    }

    public void delete(Daytasks_class del)
    {

        ArrayList<Daytasks_class> list = util.ReadDayTask(getApplicationContext());
        AlarmSetupDayTask cancel = new AlarmSetupDayTask();
        cancel.deletealarm(getApplicationContext(), del.ID); //since alarms are created the moment the user creats the task, they have to be deleted as well

        for (int c = 0; c < list.size(); c++) {
            if (list.get(c).ID == del.ID) {
                list.remove(c);
            }
        }

        util.SaveDayTask(list,getApplicationContext());

        setup();
    }
}
