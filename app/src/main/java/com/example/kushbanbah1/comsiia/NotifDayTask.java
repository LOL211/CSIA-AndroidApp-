package com.example.kushbanbah1.comsiia;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotifDayTask extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Daytasks_class alarmclass = new Daytasks_class();
        alarmclass = alarmclass.findclassbynameorid(intent.getIntExtra("ID", 0), context);


        if(!(alarmclass.task == null)) {
            NotificationManager mgr = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && mgr.getNotificationChannel(alarmclass.task) == null) {
                mgr.createNotificationChannel(new NotificationChannel(alarmclass.task, alarmclass.task, NotificationManager.IMPORTANCE_HIGH));
            }
            NotificationCompat.Builder builder;
            builder = new NotificationCompat.Builder(context, alarmclass.task)
                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setContentTitle("DayTask!")
                    .setContentText("Hey do " + alarmclass.task)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText("You need to do " + alarmclass.task + " now!"))
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE);
            NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

            Intent resultIntent = new Intent(context, DayTaskView.class);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addNextIntentWithParentStack(resultIntent);
            Random rand = new Random();
            int test = rand.nextInt(1000) + 1;

            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(alarmclass.ID + test, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(resultPendingIntent);

            manager.notify(alarmclass.ID, builder.build());
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(1000 * 5);
        }
    }
}
