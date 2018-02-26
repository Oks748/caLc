package com.oks748.calc;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.oks748.calc.MainActivity.LOG;

/**
 * Created by User on 26.02.2018.
 */

public class serviceBind extends Service {
    private final IBinder myBinder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public class LocalBinder extends Binder {
        public serviceBind getService() {
            return serviceBind.this;
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG, "servonCreate");
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(LOG, "servonRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(LOG, "servonUnbind");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG, "servonDestroy");
    }

}
