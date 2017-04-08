package com.example.administrator.weather.mvp.model;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.administrator.weather.R;
import com.example.administrator.weather.mvp.model.preference.PreferenceHelper;
import com.example.administrator.weather.mvp.model.weatherbean.NowBean;
import com.example.administrator.weather.mvp.view.MainActivity;

public class MyService extends Service {

    public MyService() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService","oncreate");

    }

    NowBean mNowBean;
    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        mNowBean = (NowBean) intent.getSerializableExtra("nowbean");
        String countryName = intent.getStringExtra("country");
        String desc = mNowBean.getCond().getTxt();
        String degree = mNowBean.getTmp()+MainActivity.DEGREES;
        int iconRes = intent.getIntExtra("icon",R.mipmap.ic_launcher_round);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(iconRes);
        Intent myIntent = new Intent(this,MainActivity.class);
        RemoteViews remoteViews = new RemoteViews(this.getPackageName(),R.layout.notification_layout);
        remoteViews.setTextViewText(R.id.tmp_notification,degree);
        remoteViews.setTextViewText(R.id.country_notification,countryName);
        remoteViews.setTextViewText(R.id.cond_notification,desc);
        remoteViews.setImageViewResource(R.id.icon_notification,iconRes);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,myIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.container_notification,pendingIntent);
        builder.setContent(remoteViews);
        Notification notification=  builder.build();
        startForeground(1,notification);
        PreferenceHelper.getInstance().saveNotificationStatus(true);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

