package com.example.kushbanbah1.comsiia;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class SchoolClass_class {

    int ID;
    int Day_0_1_2;
    int Block;
    String name;

    public void setID(ArrayList<SchoolClass_class> SCLIST)
    {
        Random rand = new Random();
        if(SCLIST.size() == 0)
        {
            ID = 1;
        }
        else {
            boolean found;
            do {
             found = true;
                int test = rand.nextInt(1000) + 1;

                for (int c = 0; c < SCLIST.size(); c++) {

                    if (test == SCLIST.get(c).ID) {
                        found = false;
                        break;
                    }

                }


                if(found)
                {
                    ID = test;
                    break;
                }
            } while (true);
        }
    }

    public SchoolClass_class findclassbynameorid(String name, Context c)
    {
        utility util = new utility();
        SchoolClass_class classfound = new SchoolClass_class();
        ArrayList<SchoolClass_class> list = util.ReadSchoolClass(c);
        for(int i = 0; i<list.size(); i++)
        {
            if(list.get(i).name.equals(name))
            {
                classfound = list.get(i);
                break;
            }

        }



        return classfound;


    }

    public SchoolClass_class findclassbynameorid(int IDf, Context c)
    {
        utility util = new utility();
        SchoolClass_class classfound = new SchoolClass_class();
        ArrayList<SchoolClass_class> list = util.ReadSchoolClass(c);
        for(int i = 0; i<list.size(); i++)
        {
            if(list.get(i).ID ==  IDf)
            {
                classfound = list.get(i);
                break;
            }

        }



        return classfound;


    }


}
