package com.example.kushbanbah1.comsiia;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.Date;

public class AlarmSetupDayTask {

    private AlarmManager alrmmanager;
    private PendingIntent pendintent;
    public void setalarm(Daytasks_class alarm, Context c)
    {
       alrmmanager =  (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent();
        intent.setAction("DAYALARM");
        Calendar calendar = Calendar.getInstance();
        intent.putExtra("ID", alarm.ID);
        calendar.set(Calendar.HOUR_OF_DAY, alarm.hour);
        calendar.set(Calendar.MINUTE, alarm.min);

        if(!alarm.today) {


            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        pendintent= PendingIntent.getBroadcast(c, alarm.ID , intent, 0);
        if(alarm.task != null)
        {alrmmanager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendintent);}

    }

    public void deletealarm(Context c, int ID)
    {
        if(alrmmanager!=null)
        {
            Intent intent = new Intent();
            pendintent= PendingIntent.getBroadcast(c, ID , intent, 0);
            alrmmanager.cancel(pendintent);
            pendintent.cancel();


        }
    }


}
