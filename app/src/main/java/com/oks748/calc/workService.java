package com.oks748.calc;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import static com.oks748.calc.MainActivity.LOG_TAG;

public class workService extends Service {

    private final IBinder myBinder = new LocalBinder();
    //private signsOfBtns ma = new signsOfBtns();

    // = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
    // layout;//  = inflater.inflate(R.layout.activity_main, null);

    //Activity activity = MainActivity.this;
    //Intent intent = new Intent(this, MainActivity.class);
   // bindService(intent,

    LayoutInflater inflater;
    //LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
    View layout;
    //View layout = inflater.inflate(R.layout.activity_main, null);

     private DatabaseReference ref;
    //ValueEventListener btnListener;
    HashMap<String, String> bbs = new HashMap<>();
    static boolean calledAlready = false;

    Button bb;
    TextView txv;

    public void baseConnect(){
        if (!calledAlready)
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }

        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(R.layout.activity_main, null);

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

        Log.d(LOG_TAG,"baseConnect_end");
    }

    public void fromBase(String a, String b){
        ref = FirebaseDatabase.getInstance().getReference().child("buttons").child(a).child(b);
        ValueEventListener btnListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bbs.put(dataSnapshot.getKey(), dataSnapshot.getValue(String.class));

                bb = layout.findViewById(getResources().getIdentifier(dataSnapshot.getKey().toString() , "id", getPackageName()));
                Log.d(LOG_TAG,"onDataCh_f_"+bb.toString()+"_");
                bb.setText(bbs.get(dataSnapshot.getKey()));
                Log.d(LOG_TAG,"onDataCh_set_"+dataSnapshot.getKey()+"_"+bbs.get(dataSnapshot.getKey())+"_");


                if(bbs.size() == 23) {
                    Log.d(LOG_TAG,"hello_"+String.valueOf(bbs.size())+"_");
                    txv = layout.findViewById(R.id.textView);
                    txv.setText("fg");
                    Log.d(LOG_TAG,"bbsgetHASH_"+bbs.toString()+"_");

                    //ma.drawBtns(bbs, workBaseService.this);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.d(LOG_TAG,"onCancelled");
            }
        };
        ref.addValueEventListener(btnListener);
    }


    //***********************************************

    public class LocalBinder extends Binder {
        public workService getService() {
            return workService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "servonBind");
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "servonCreate");
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(LOG_TAG, "servonRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(LOG_TAG, "servonUnbind");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //ref.removeEventListener(btnListener);
        Log.d(LOG_TAG, "servonDestroy");
    }
}
