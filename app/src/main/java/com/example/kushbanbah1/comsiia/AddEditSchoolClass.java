package com.example.kushbanbah1.comsiia;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddEditSchoolClass extends AppCompatActivity {

    Spinner Daylist;

    utility util = new utility();
    EditText name;

    SchoolClass_class edit = new SchoolClass_class();
    Resources res;
    boolean editing = false;
    //Page to add homework

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_class);






      int ID = getIntent().getIntExtra("ID", 0);
      if(ID!=0)
      {
          edit =  edit.findclassbynameorid(ID,getApplicationContext());
          editing = true;
          editsetup();
      }
      else{
          Daylist = findViewById(R.id.Days);
          ArrayAdapter<CharSequence> Daysadpater = ArrayAdapter.createFromResource(this, R.array.Days,android.R.layout.simple_spinner_dropdown_item);
          Daylist.setAdapter(Daysadpater);
          FloatingActionButton fab;
          fab = findViewById(R.id.fab);
          final Intent start = new Intent(this, HelpPage.class);
          start.putExtra("ID", 3);
          fab.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  startActivity(start);
              }
          });
      }


    }
    public void addclass(View v)
    {
        if(editing)
        {

            EditText ClassName =  findViewById(R.id.classname);

            String daytime = Daylist.getSelectedItem().toString();
            if(!daytime.equals("Outside Schedule")) {
                daytime = daytime.replaceAll("\\D+", "");
                int Day = Integer.parseInt(daytime.substring(0, 1));
                int block = Integer.parseInt(daytime.substring(1));

                edit.Day_0_1_2 = block;
                edit.Block = Day;
                edit.name = ClassName.getText().toString();

            }
            else
            {
                edit.name = ClassName.getText().toString();
                edit.Block = 0;
                edit.Day_0_1_2=0;


            }

            ArrayList<SchoolClass_class> list= util.ReadSchoolClass(getApplicationContext());

            for(int e = 0; e<list.size(); e++) {
                if(list.get(e).ID == edit.ID)
                {
                    list.remove(e);
                    list.add(edit);
                }

            }





            ClassName.setText("") ;

            util.SaveSchoolClass(list, getApplicationContext());



        }
        else
        {
            EditText ClassName =  findViewById(R.id.classname);
            SchoolClass_class newclass = new SchoolClass_class();
            String daytime = Daylist.getSelectedItem().toString();
            if(!daytime.equals("Outside Schedule")) {
                daytime = daytime.replaceAll("\\D+", "");
                int Day = Integer.parseInt(daytime.substring(0, 1));
                int block = Integer.parseInt(daytime.substring(1));

                newclass.Day_0_1_2 = block;
                newclass.Block = Day;
                newclass.name = ClassName.getText().toString();

            }
            else
            {
                newclass.name = ClassName.getText().toString();
                newclass.Block = 0;
                newclass.Day_0_1_2=0;


            }

            ArrayList<SchoolClass_class> temp= util.ReadSchoolClass(getApplicationContext());
            newclass.setID(temp);
            temp.add(newclass);


            ClassName.setText("") ;

            util.SaveSchoolClass(temp, getApplicationContext());


        }

        Intent leave = new Intent(this, SchoolClassView.class);
        startActivity(leave);



    }

    public void editsetup()
    {

        FloatingActionButton fab;
        fab = findViewById(R.id.fab);
        final Intent start = new Intent(this, HelpPage.class);
        start.putExtra("ID", 4);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(start);
            }
        });
        Daylist =  findViewById(R.id.Days);
        res = getResources();
        name =  findViewById(R.id.classname);
        name.setText(edit.name);
        String[] test = res.getStringArray(R.array.Days);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);

        for(int i = 0; i<test.length; i++)
        {
            String dayblock=test[i].replaceAll("\\D+", "");
            String testing = edit.Block+""+edit.Day_0_1_2;

            if(testing.equals(dayblock)) {
                adapter.add(test[i]);
            }
        }

        for(int c = 0; c<test.length; c++)
        {
            String dayblock=   test[c].replaceAll("\\D+", "");
            String testing = edit.Day_0_1_2+""+edit.Block;
            if(!(testing.equals(dayblock))) {
                adapter.add(test[c]);
            }
        }
        Button but = findViewById(R.id.add_but);
        but.setText("Edit Class");



        Daylist.setAdapter(adapter);

    }


}
