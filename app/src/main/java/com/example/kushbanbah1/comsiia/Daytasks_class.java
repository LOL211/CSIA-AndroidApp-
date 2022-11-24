package com.example.kushbanbah1.comsiia;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

public class Daytasks_class {

    int hour;
    int ID;
    int min;
    Boolean today;
    String task;

    public void setID(ArrayList<Daytasks_class> DTLIST) {
        Random rand = new Random();
        if (DTLIST.size() == 0) {
            ID = 1;
        } else {
            boolean found;
            do {
                found = true;
                int test = rand.nextInt(1000) + 1;

                for (int c = 0; c < DTLIST.size(); c++) {

                    if (test == DTLIST.get(c).ID) {
                        found = false;
                        break;
                    }

                }


                if (found) {
                    ID = test;
                    break;
                }
            } while (true);
        }
    }

    public Daytasks_class findclassbynameorid(String name, Context c) {
        utility util = new utility();
        Daytasks_class classfound = new Daytasks_class();
        ArrayList<Daytasks_class> list = util.ReadDayTask(c);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).task.equals(name)) {
                classfound = list.get(i);
                break;
            }

        }


        return classfound;


    }

    public Daytasks_class findclassbynameorid(int IDf, Context c) {
        utility util = new utility();
        Daytasks_class classfound = new Daytasks_class();
        ArrayList<Daytasks_class> list = util.ReadDayTask(c);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).ID == IDf) {
                classfound = list.get(i);
                break;
            }

        }
        return classfound;
    }
    //example of overloading- both methods acheive the same goal albiet with different parameters of using those parameters to return a
}




