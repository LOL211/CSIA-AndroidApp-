package com.example.kushbanbah1.comsiia;

import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;


public class Homework_class implements Comparator<Homework_class> {


    String Task;
    Date duedate;
    int Class_ID;
    int Homework_ID;


    public void makeID(ArrayList<Homework_class> lol)
{
    Random rand = new Random();
    if(lol.size() == 0)
    {
      Homework_ID = 1;
    }
    else {
        boolean found;
        do {
            found = true;
            int test = rand.nextInt(1000) + 1;

            for (int c = 0; c < lol.size(); c++) {

                if (test == lol.get(c).Homework_ID) {
                    found = false;
                    break;
                }

            }


            if(found)
            {
               Homework_ID = test;
                break;
            }
        } while (true);
    }


}

public Homework_class findbyID(int id, Context c)
{
    utility util = new utility();
    Homework_class classfound = new Homework_class();
    ArrayList<Homework_class> list = util.ReadHW(c);
    for(int i = 0; i<list.size(); i++)
    {
        if(list.get(i).Homework_ID ==  id)
        {
            classfound = list.get(i);

            break;
        }

    }



    return classfound;


}

public Homework_class recentHW(Context c) {
    utility util = new utility();

    ArrayList<Homework_class> list_hw = util.ReadHW(c);
    long min = 1000;
    Date d = new Date();
    Homework_class finding = new Homework_class();



    for (int e = 0; e < list_hw.size(); e++) {
        final long days = Math.abs(list_hw.get(e).duedate.getTime() - d.getTime());
        long differenceDates = days / (24 * 60 * 60 * 1000);


        if (differenceDates < min && list_hw.get(e).duedate.after(d)) {

            min = differenceDates;
            finding = list_hw.get(e);
        }
    }
    return finding;
}

public int compare(Homework_class a, Homework_class b)
    {
        return a.duedate.compareTo(b.duedate);
    }
}








