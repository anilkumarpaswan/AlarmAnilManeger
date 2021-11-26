 package com.example.alarmanilmanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.timepicker.MaterialTimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    Button btn_timepicker, btn_cancelAlarm;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        btn_timepicker = findViewById ( R.id.btn_timepicker );
        btn_cancelAlarm = findViewById ( R.id.btn_cancelAlarm );
        txt = findViewById ( R.id.txt );
        createNotificationChannel ();



        btn_timepicker.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                DialogFragment fragment = new DialogFragment ();
                fragment.show ( getSupportFragmentManager (), "time picker" );

            }
        } );
        btn_cancelAlarm.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                cancelAlarm ();
            }
        } );


    }

    private void createNotificationChannel() {
        CharSequence c = "AnilRemanderChannel";
        String Description = "Channel for Alarm";
        int i = NotificationManager.IMPORTANCE_HIGH;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel ( "Anil", c, i );
            channel.setDescription ( Description );
            NotificationManager notificationManager = getSystemService ( NotificationManager.class );
            notificationManager.createNotificationChannel ( channel );


        }


    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance ();
        c.set ( Calendar.HOUR_OF_DAY, hourOfDay );
        c.set ( Calendar.MINUTE,minute );
        c.set ( Calendar.SECOND, 0 );

        updateTimeText ( c );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            startAlarm ( c );
        }

    }
    private void  updateTimeText(Calendar c){
        String timeText = "Alarm for set: ";
        timeText += DateFormat.getDateTimeInstance ( ).format ( c.getTime () );
        txt.setText ( timeText );
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private   void  startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager)getSystemService ( Context.ALARM_SERVICE );
        Intent i = new Intent (this,AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast ( this,1,i,0 );

        alarmManager.setExact ( AlarmManager.RTC_WAKEUP,c.getTimeInMillis (  ),pendingIntent );

    }
    private void  cancelAlarm(){
        AlarmManager alarmManager = (AlarmManager)getSystemService ( Context.ALARM_SERVICE );
        Intent i = new Intent (this,AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast ( this,1,i,0 );

        alarmManager.cancel (pendingIntent  );
        txt.setText ( "Alarm Cancel" );



    }
}
