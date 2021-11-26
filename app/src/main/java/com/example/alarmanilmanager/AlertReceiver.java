package com.example.alarmanilmanager;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

       NotificationCompat.Builder builder= new NotificationCompat.Builder ( context,"Notification" );
       builder.setAutoCancel ( true );
       builder.setSmallIcon ( R.drawable.ic_anilalarm );
       builder.setContentTitle ( "Anil Alarm Manager" );
       builder.setPriority (NotificationCompat.PRIORITY_HIGH  );
       builder.setDefaults ( NotificationCompat.DEFAULT_ALL );
       builder.setContentTitle ( "Subscribe the android related context" );
       NotificationManagerCompat notificationCompat = NotificationManagerCompat.from ( context );
       notificationCompat.notify (123,builder.build ());



    }
}
