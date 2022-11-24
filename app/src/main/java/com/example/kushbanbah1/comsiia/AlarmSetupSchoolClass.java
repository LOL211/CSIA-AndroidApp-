package com.example.kushbanbah1.comsiia;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Calendar;

public class AlarmSetupSchoolClass extends BroadcastReceiver {

    utility util = new utility();
    Time_Management time = new Time_Management();
    AlarmManager[] alrmnger = new AlarmManager[4];
    PendingIntent[] pend_intents = new PendingIntent[4];

    Intent intent;

    Context c;

    @Override
    public void onReceive(Context context, Intent intent) {

        c = context;


        checkdaychanged();

    }

    public void checkdaychanged() {
        SchoolClassAlarm dateday = util.ReadAlarmFile(c);

        Calendar cal = Calendar.getInstance();

        int compare = cal.get(Calendar.DAY_OF_MONTH);
        int check = cal.get(Calendar.DAY_OF_WEEK);


        if ((check >= Calendar.MONDAY) && (check <= Calendar.FRIDAY)) {


            if (!(compare == dateday.date)) {

                dateday.date = compare;

                if (dateday.day_1_2 == 1) {
                    dateday.day_1_2 = 2;
                } else {
                    dateday.day_1_2 = 1;
                }


                util.SaveAlarmFile(dateday, c);
                setupalarms();
            }




        }


    }

    public void setupalarms() {


        for (int x = 0; x < alrmnger.length; x++) {

            if (alrmnger[x] != null) {
                alrmnger[x].cancel(pend_intents[x]);
                pend_intents[x].cancel();


            }

        }
        Calendar cal = Calendar.getInstance();
        int check = cal.get(Calendar.DAY_OF_WEEK);

        if ((check >= Calendar.MONDAY) && (check <= Calendar.FRIDAY)) {
            ArrayList<SchoolClass_class> list = util.ReadSchoolClass(c);
            SchoolClassAlarm daydate = util.ReadAlarmFile(c);


            for (int e = 0; e < list.size(); e++) {

                if (list.get(e).Day_0_1_2 == daydate.day_1_2) {

                    switch (check) {
                        case 2: {

                            createalarm(list.get(e), time.getMT());
                            break;
                        }
                        case 3: {
                            createalarm(list.get(e), time.getMT());
                            break;
                        }
                        case 4: {
                            createalarm(list.get(e), time.getWed());
                            break;
                        }
                        case 5: {
                            createalarm(list.get(e), time.getThur());
                            break;
                        }
                        case 6: {
                            createalarm(list.get(e), time.getFri());
                            break;
                        }
                        default: {

                            break;
                        }

                    }

                }

            }

        }
    }

    public void createalarm(SchoolClass_class alarmclass, int[][] timec) {
        alrmnger[alarmclass.Block - 1] = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);

        intent = new Intent();
        intent.setAction("ALARM");
        Calendar calendar = Calendar.getInstance();
        intent.putExtra("ID", alarmclass.ID);

        calendar.set(Calendar.HOUR_OF_DAY, timec[alarmclass.Block - 1][0]);
        calendar.set(Calendar.MINUTE, timec[alarmclass.Block - 1][1]);


        pend_intents[alarmclass.Block - 1] = PendingIntent.getBroadcast(c, alarmclass.ID, intent, 0);

        alrmnger[alarmclass.Block - 1].setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pend_intents[alarmclass.Block - 1]);
    }

    public void deletealarms() {

        for (int x = 0; x < alrmnger.length; x++) {

            if (alrmnger[x] != null) {
                alrmnger[x].cancel(pend_intents[x]);



            }
            if( pend_intents[x]!=null)
            {
                pend_intents[x].cancel();
            }
        }

    }
}

