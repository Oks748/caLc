package com.oks748.calc;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class workBaseService extends Service {
    final String LOG_TAG = "myLogs";
    IBinder myBinder = new LocalBinder();

    private DatabaseReference ref;
    ValueEventListener btnListener;
    private Button bb;
    private Map<String, String> bbs = new HashMap<>();

    public class LocalBinder extends Binder {
        workBaseService getService() {
            return workBaseService.this;
        }
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "servonBind");
        return null;
    }

    public void baseConnect(){
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        ref = FirebaseDatabase.getInstance().getReference().child("buttons");

        fromBase("digits", "btn0");
        fromBase("digits", "btn1");
        fromBase("digits", "btn2");
        fromBase("digits", "btn3");
        fromBase("digits", "btn4");
        fromBase("digits", "btn5");
        fromBase("digits", "btn6");
        fromBase("digits", "btn7");
        fromBase("digits", "btn8");
        fromBase("digits", "btn9");

        fromBase("dot", "btnDot");

        fromBase("operators4", "btnDiv");
        fromBase("operators4", "btnMinus");
        fromBase("operators4", "btnMult");
        fromBase("operators4", "btnPlus");

        fromBase("specOper", "btn1Div");
        fromBase("specOper", "btnBack1");
        fromBase("specOper", "btnC");
        fromBase("specOper", "btnCE");
        fromBase("specOper", "btnIs");
        fromBase("specOper", "btnPercent");
        fromBase("specOper", "btnSign");
        fromBase("specOper", "btnSqrt");
    }

    public void fromBase(String a, final String b){
        ref = ref.child(a).child(b);
        ValueEventListener btnListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(LOG_TAG,"onDataCh");
                bbs.put(dataSnapshot.getKey(), dataSnapshot.getValue(String.class));
                bb = bb.findViewById(getResources().getIdentifier(b, "id", getPackageName()));
                bb.setText(bbs.get(b));
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.d(LOG_TAG,"onCancelled");
            }
        };
        ref.addValueEventListener(btnListener);
    }

    //--------------------------
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "servonCreate");
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(LOG_TAG, "servonRebind");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(LOG_TAG, "servonUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ref.removeEventListener(btnListener);
        Log.d(LOG_TAG, "servonDestroy");
    }
}
