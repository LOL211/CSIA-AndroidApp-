package com.example.kushbanbah1.comsiia;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

public class HomeworkClassView extends AppCompatActivity  {

    LinearLayout lay;
    NestedScrollView scroll;
    ArrayList<Homework_class> list;
    ArrayList<Integer> ID = new ArrayList<Integer>();
    utility util = new utility();
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_view_homework);
        lay = findViewById(R.id.holder);
        fab = findViewById(R.id.fab);
        scroll =  findViewById(R.id.scrollview);
     setup();
    }

    public void onResume() {
        super.onResume();
        setup();

    }
    public void NewHW(View v)
    {
        Intent lel = new Intent(this, AddEditHomework.class);
        startActivity(lel);




    }

    public void setup() {
         final Intent start = new Intent(this, HelpPage.class);
        start.putExtra("ID", 5);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(start);
            }
        });


        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Homework_class> list = util.ReadHW(getApplicationContext());
        SchoolClass_class idclass = new SchoolClass_class();
        Homework_class e = new Homework_class();
        Collections.sort(list ,new Homework_class());


        if(( lay).getChildCount() > 0)
            ( lay).removeAllViews();




            for (int i = 0; i < list.size(); i++) {
                LinearLayout layouti = new LinearLayout(getApplicationContext());

                layouti.setOrientation(LinearLayout.VERTICAL);

                TextView itemnew = new TextView(getApplicationContext());
                Calendar m = Calendar.getInstance();
                int diff =(int) ( (m.getTimeInMillis() - list.get(i).duedate.getTime())/ (24 * 60 * 60 * 1000));
                String label = "Class Name is: " + idclass.findclassbynameorid(list.get(i).Class_ID, getApplicationContext()).name + " the Task is " + list.get(i).Task + " The Date is " + fmt.format(list.get(i).duedate)+" and "+((-1*diff)+1)+" days left";


                itemnew.setText(label);


                Button del_but = new Button(getApplicationContext());

                del_but.setTag(list.get(i).Homework_ID);




               del_but.setText("Delete Homework");
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
                            del_but.setId(test);
                            break;
                        }
                    } while (true);




                del_but.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                      Homework_class classtest = new Homework_class();
                        Button but =  findViewById(v.getId());



                        delete(classtest.findbyID(Integer.parseInt(but.getTag().toString()), getApplicationContext()));


                    }
                });
                Typeface tfFutura = Typeface.create("casual",Typeface.NORMAL);
                itemnew.setTextSize(20);
                del_but.setTypeface(tfFutura);
                itemnew.setTypeface(tfFutura);
                layouti.addView(itemnew);

                layouti.addView(del_but);

                Button edit = new Button(getApplicationContext());

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
                        edit.setId(test);
                        break;
                    }
                } while (true);

                edit.setTag(list.get(i).Homework_ID);
                edit.setText("Edit Homework");
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                       Homework_class classtest = new Homework_class();
                       Button but = findViewById(view.getId());

                      classtest = classtest.findbyID(Integer.parseInt(but.getTag().toString()), getApplicationContext());
                        Intent openclass = new Intent(view.getContext(), AddEditHomework.class);
                        openclass.putExtra("ID", classtest.Homework_ID);

                        startActivity(openclass);
                    }
                });

                edit.setTypeface(tfFutura);
                lay.addView(layouti);
                lay.addView(edit);
                Space space = new Space(getApplicationContext());
                space.setMinimumHeight(70);
                lay.addView(space);

            }



    }



            public void delete (Homework_class del)
            {


                ArrayList<Homework_class> list = util.ReadHW(getApplicationContext());

                for (int c = 0; c < list.size(); c++) {
                    if (list.get(c).Homework_ID == del.Homework_ID) {
                        list.remove(c);
                    }
                }
                util.SaveHW(list,getApplicationContext());

                setup();



            }


        }



