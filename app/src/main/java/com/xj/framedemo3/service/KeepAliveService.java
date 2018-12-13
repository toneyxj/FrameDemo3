package com.xj.framedemo3.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;

import com.xj.framedemo3.LockActivity;
import com.xj.framedemo3.R;
import com.xj.mainframe.base.BaseUtils;
import com.xj.mainframe.configer.APPLog;


/**
 * 后台保活程序设置
 */
public class KeepAliveService extends Service{
    @SuppressLint("WrongConstant")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        setNotification();
        flags = Service.START_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }

    private void setNotification() {
        String CHANNEL_ONE_ID = "com.service.KeepAliveService";
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= 26) {
            builder= new Notification.Builder(this, CHANNEL_ONE_ID);
        } else {
            builder = new Notification.Builder(this);
        }

        builder.setTicker("Nature").
                setSmallIcon(R.mipmap.ic_launcher).
                setContentTitle(getResources().getString(R.string.app_name)).
                setContentText("已启动后台保护");

        Notification notification;
        if (Build.VERSION.SDK_INT >= 26) {
            notification=builder.build();
        }else {
            notification=builder.getNotification();
        }

        notification.flags |= Notification.FLAG_NO_CLEAR;
        startForeground(1, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(receiver, filter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    APPLog.e("KeepAliveService","alive");
                    try {
                        Thread.sleep(500l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }
    BroadcastReceiver receiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_SCREEN_OFF)) {
                Intent lockscreen = new Intent(KeepAliveService.this, LockActivity.class);
                lockscreen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(lockscreen);
            }
        }
    };
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
        unregisterReceiver(receiver);
    }
}
