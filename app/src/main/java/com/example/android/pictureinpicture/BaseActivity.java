package com.example.android.pictureinpicture;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by hendry on 2019-07-23.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;
    public static final String EXTRA_INTENT = "extra_intent";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Intent intentInside = intent.getParcelableExtra(EXTRA_INTENT);
                startActivity(intentInside);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TESTHENDRY", "onResume " + this.getClass().getSimpleName());
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                new IntentFilter("startact"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("TESTHENDRY", "onPause " + this.getClass().getSimpleName());
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }
}
