package com.example.kushbanbah1.comsiia;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class HelpPage extends AppCompatActivity {


    String[] helpinfo = new String[11];
    Intent launchinent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_page);
        launchinent = getIntent();
        helpinfo[0] ="Welcome to the homepage!\nThere are a few things to do here:\nThe current day can be changed if it is incorrect!, it resets the alarms so you don't miss a beat!\nThe homework button leads to the homework menus so you can add, view or edit your homework and classes!\nThe day tasks button leads to the day tasks section of the app so you can add,view or edit your tasks!\nThe enable alarms option is used to disable alarms, use this for holidays!";
        helpinfo[1] = "Welcome to the homework menu!\nThe upcoming homework is your homework thats due next! it doesn't include homework due today though!\nThe manage homework button opens all your current homework anc lets you edit or add or delete it!\nThe manage classes button lets you view, edit, add or delete your classes!";
        helpinfo[2] = "Welcome to the manage your class view!\nYou can view all your classes here!, the edit and delete buttons delete or edit the class they're under!\nThe new class button opens the page to let you add more classes!";
        helpinfo[3] = "Welcome to add classes!\nThe class name is the name of the class and the list under it is the block and day! make to set that properly or else the alarms wont work!";
        helpinfo[4] = "Welcome to edit classes!\nIt works just like add class, pop in the new classname you want and change the time if you want!";
        helpinfo[5] = "Welcome to manage homework view\nHere is all your homework ordered from most recent to least!\nThe delete and edit buttons edit the homework above it!\nThe add homework button lets you add a new homework!";
        helpinfo[6] = "Welcome to add homework view!\nThe list above is all the class the homework belongs to, if there is nothing there that means you need to add a class!\nThe homework description is the actual homework details itself!\nThe due date is, just select it on the calender and hit add homework after you're done and your homework will be added!";
        helpinfo[7] = "Welcome to the edit your homework view!\nIt works just like add homework!, select your class, write your details and choose the date and it'll all be saved when you hit edit!";
        helpinfo[8] = "Welcome to Manage Daytasks!\nYou can see all your current day tasks!, delete them or edit! the delete and edit buttons affect the task above it!\nThe new day task button is if you want to make a new task!";
        helpinfo[9] =  "Welcome to add day tasks view!\nThe clock is the time you want your alarm to go off\nThe Task is for you to put in the task details!\nThe button is to let you choose between today or tommrow! and just hit add day task when you're ready!";
        helpinfo[10] = "Welcome to edit day tasks view!\nThe page works just like add day tasks!\nJust set the time\nPut in the details\nAnd choose the day!, hit edit and it's all saved!";
        generatetext();



    }



        public void generatetext()
        {
          TextView display = findViewById(R.id.dis);
            display.setText(helpinfo[launchinent.getIntExtra("ID", -1)]);



        }




}
