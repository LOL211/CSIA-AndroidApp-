package com.example.kushbanbah1.comsiia;

import android.content.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class utility {


File DayF,HomeworkF,SCF,ARF;

    public void setupclass(Context c)
    {
        DayF = new File(c.getFilesDir(),"DayTasks.txt");
        HomeworkF = new File(c.getFilesDir(),"Homework.txt");
        SCF = new File(c.getFilesDir(),"SchoolClasses.txt");
        ARF = new File(c.getFilesDir(), "AlarmReminder.txt");

    }

    public ArrayList<Homework_class> ReadHW(Context c)//Reads and returns homework class
    { setupclass(c);
        ArrayList<Homework_class> list = new ArrayList<Homework_class>();


        try {
            FileInputStream fis = new FileInputStream(HomeworkF);
            InputStreamReader test = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(test);
            String nextline = br.readLine();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");


            if(!(nextline == null)) {
                do {
                    Homework_class newobj = new Homework_class();
                    newobj.Task = nextline;

                    newobj.duedate = fmt.parse(br.readLine());
                    newobj.Class_ID = Integer.parseInt(br.readLine());
                    newobj.Homework_ID = Integer.parseInt(br.readLine());
                    nextline = br.readLine();
                    list.add(newobj);
                } while (!(nextline == null));
            }

            br.close();
            test.close();
            fis.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }
    public void SaveHW(ArrayList<Homework_class> HWLIST, Context c)//recieves and writes a homework task
    {
        setupclass(c);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");


        FileOutputStream outputStream;

        try {
            outputStream = c.openFileOutput("Homework.txt", Context.MODE_PRIVATE);
            for(int i = 0; i<HWLIST.size(); i++) {

                outputStream.write(HWLIST.get(i).Task.getBytes());
                outputStream.write("\r\n".getBytes());
                outputStream.write((fmt.format(HWLIST.get(i).duedate).getBytes()));
                outputStream.write("\r\n".getBytes());
                outputStream.write(Integer.toString(HWLIST.get(i).Class_ID).getBytes());
                outputStream.write("\r\n".getBytes());
                outputStream.write(Integer.toString(HWLIST.get(i).Homework_ID).getBytes());
                outputStream.write("\r\n".getBytes());



            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public ArrayList<SchoolClass_class> ReadSchoolClass(Context c)// reads and returns a school class list
    {    setupclass(c);
        ArrayList<SchoolClass_class> list = new ArrayList<SchoolClass_class>() ;
        try {

            FileInputStream fis = new FileInputStream(SCF);
            InputStreamReader test = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(test);
            String nextline = br.readLine();
            if(!(nextline == null)) {
                do {
                    SchoolClass_class obj = new SchoolClass_class();
                    obj.name = nextline;

                    obj.ID = Integer.parseInt(br.readLine());

                    obj.Day_0_1_2 = Integer.parseInt(br.readLine());

                    obj.Block = Integer.parseInt(br.readLine());

                    list.add(obj);
                    nextline = br.readLine();

                } while (!(nextline == null));
            }

            
            br.close();
            test.close();
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

         return list;


    }
    public void SaveSchoolClass(ArrayList<SchoolClass_class> SCLIST,Context c) //recievcs a school class list and writes it
    {        setupclass(c);

        FileOutputStream outputStream;

        try {
            outputStream = c.openFileOutput("SchoolClasses.txt", Context.MODE_PRIVATE);

            for(int i = 0; i<SCLIST.size(); i++)
            {
                outputStream.write(SCLIST.get(i).name.getBytes());
                outputStream.write("\r\n".getBytes());
                outputStream.write(Integer.toString(SCLIST.get(i).ID).getBytes());
                outputStream.write("\r\n".getBytes());
                outputStream.write(Integer.toString(SCLIST.get(i).Day_0_1_2).getBytes());
                outputStream.write("\r\n".getBytes());
                outputStream.write(Integer.toString(SCLIST.get(i).Block).getBytes());
                outputStream.write("\r\n".getBytes());
            }
                outputStream.flush();
                outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }


    public ArrayList<Daytasks_class> ReadDayTask(Context c)
    { setupclass(c);


        ArrayList<Daytasks_class> list = new ArrayList<Daytasks_class>() ;
        try {

            FileInputStream fis = new FileInputStream(DayF);
            InputStreamReader test = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(test);
            String nextline = br.readLine();
            if(!(nextline == null)) {
                do {
                    Daytasks_class obj = new Daytasks_class();
                    obj.task = nextline;

                    obj.hour = Integer.parseInt(br.readLine());

                    obj.min= Integer.parseInt(br.readLine());

                    obj.ID = Integer.parseInt(br.readLine());
                    obj.today = Boolean.parseBoolean(br.readLine());
                    list.add(obj);
                    nextline = br.readLine();

                } while (!(nextline == null));
            }


            br.close();
            test.close();
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;

    }

    public void SaveDayTask(ArrayList<Daytasks_class> DTLIST ,Context c)
    { setupclass(c);

        FileOutputStream outputStream;

        try {
            outputStream = c.openFileOutput("DayTasks.txt", Context.MODE_PRIVATE);

            for(int i = 0; i<DTLIST.size(); i++)
            {
                outputStream.write(DTLIST.get(i).task.getBytes());
                outputStream.write("\r\n".getBytes());
                outputStream.write(Integer.toString(DTLIST.get(i).hour).getBytes());
                outputStream.write("\r\n".getBytes());
                outputStream.write(Integer.toString(DTLIST.get(i).min).getBytes());
                outputStream.write("\r\n".getBytes());
                outputStream.write(Integer.toString(DTLIST.get(i).ID).getBytes());
                outputStream.write("\r\n".getBytes());
                outputStream.write(String.valueOf(DTLIST.get(i).today).getBytes());
                outputStream.write("\r\n".getBytes());

            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public SchoolClassAlarm ReadAlarmFile(Context c)
    {
        setupclass(c);

        SchoolClassAlarm newobj = new SchoolClassAlarm();
        newobj.date= 0;
        newobj.day_1_2 = 1;
        newobj.enabled = true;
        try {
            FileInputStream fis = new FileInputStream(ARF);
            InputStreamReader test = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(test);
            String nextline = br.readLine();

            if(nextline != null) {
                newobj.date = Integer.parseInt(nextline);

                newobj.day_1_2 = Integer.parseInt(br.readLine());
                newobj.enabled  = Boolean.parseBoolean(br.readLine());

            }
            br.close();
            test.close();
            fis.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return newobj;


    }
    public void SaveAlarmFile(SchoolClassAlarm save, Context c) {
        setupclass(c);

        FileOutputStream outputStream;

        try {
            outputStream = c.openFileOutput("AlarmReminder.txt", Context.MODE_PRIVATE);


            outputStream.write(Integer.toString(save.date).getBytes());
            outputStream.write("\r\n".getBytes());
            outputStream.write(Integer.toString(save.day_1_2).getBytes());
            outputStream.write("\r\n".getBytes());
            outputStream.write(String.valueOf(save.enabled).getBytes());
            outputStream.write("\r\n".getBytes());



            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
