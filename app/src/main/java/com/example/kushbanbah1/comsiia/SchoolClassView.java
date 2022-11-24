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
import java.util.ArrayList;
import java.util.Random;

public class SchoolClassView extends AppCompatActivity {

    LinearLayout layout;
    NestedScrollView scroll;
    ArrayList<Integer> ID = new ArrayList<Integer>();
    utility util = new utility();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_class);
        layout =  findViewById(R.id.Holder);
       scroll =  findViewById(R.id.scrollview);

        setup();

    }
    @Override
    public void onResume() {
        super.onResume();
        setup();

    }

    public void setup()
    {

        FloatingActionButton fab;
        fab = findViewById(R.id.fab);
        final Intent start = new Intent(this, HelpPage.class);
        start.putExtra("ID", 2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(start);
            }
        });
        if((layout).getChildCount() > 0)
            (layout).removeAllViews();

        ArrayList<SchoolClass_class> list = util.ReadSchoolClass(getApplicationContext());
        if(list!= null) {
            for (int i = 0; i < list.size(); i++) {

                LinearLayout layouti = new LinearLayout(getApplicationContext());

                layouti.setOrientation(LinearLayout.VERTICAL);

                TextView itemnew = new TextView(getApplicationContext());
                String label;
                if(list.get(i).Block != 0){
               label = "Name: " + list.get(i).name + " the Day is " + list.get(i).Day_0_1_2 + " The Block is " + list.get(i).Block;}
               else
                {
                    label = "Name: " + list.get(i).name + " the time is unscheduled";
                }

                itemnew.setText(label);

                Button del_but = new Button(getApplicationContext());

                del_but.setTag(list.get(i).ID);


                del_but.setText("Delete Class");
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
                        SchoolClass_class classtest = new SchoolClass_class();
                        Button but = findViewById(v.getId());


                        delete(classtest.findclassbynameorid(Integer.parseInt(but.getTag().toString()), getApplicationContext()));




                    }
                });

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
                edit.setTag(list.get(i).ID+"");
                edit.setText("Edit Class");

                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SchoolClass_class classtest = new SchoolClass_class();
                        Button but =  findViewById(view.getId());


                       classtest = classtest.findclassbynameorid(Integer.parseInt(but.getTag().toString()), getApplicationContext());

                        Intent openclass = new Intent(view.getContext(), AddEditSchoolClass.class);
                        openclass.putExtra("ID", classtest.ID);
                        startActivity(openclass);
                    }
                });


                Typeface tfFutura = Typeface.create("casual",Typeface.NORMAL);
                itemnew.setTextSize(20);
                del_but.setTypeface(tfFutura);
                itemnew.setTypeface(tfFutura);
                edit.setTypeface(tfFutura);
                layouti.addView(itemnew);
                layouti.addView(del_but);
                layouti.addView(edit);

                Space space = new Space(getApplicationContext());
                space.setMinimumHeight(70);
                layouti.addView(space);
                layout.addView(layouti);
            }


        }
        }

        public void delete(SchoolClass_class del)
        {

            ArrayList<SchoolClass_class> list = util.ReadSchoolClass(getApplicationContext());
            ArrayList<Homework_class> listhw = util.ReadHW(getApplicationContext());
            for(int e = 0; e< listhw.size(); e++)
            {
                if(del.ID == listhw.get(e).Class_ID)
                {
                    listhw.remove(e);
                }
            }
            for (int c = 0; c < list.size(); c++) {
                if (list.get(c).ID == del.ID) {
                    list.remove(c);
                }
            }
            util.SaveSchoolClass(list,getApplicationContext());
            util.SaveHW(listhw,getApplicationContext());


            setup();


        }

    public void clicked(View v)
    {
        Intent lel = new Intent(this, AddEditSchoolClass.class);
        startActivity(lel);
    }
}
