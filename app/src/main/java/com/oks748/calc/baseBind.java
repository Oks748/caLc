package com.oks748.calc;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import static com.oks748.calc.MainActivity.LOG;

public class baseBind {
    private DatabaseReference ref;
    static boolean calledAlready = false;
    public static Map<String, String> bbs = new HashMap<>();

    Context context;
    public baseBind(Context current){
        this.context = current;
    }

    public void baseConnect(){
        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }

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
        ref = FirebaseDatabase.getInstance().getReference().child("buttons");
        ref.child(a).child(b).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Log.d(LOG,"onDataCh");
                bbs.put(dataSnapshot.getKey(), dataSnapshot.getValue(String.class));

                if(bbs.size() == 23) {
                    Log.d(LOG, "hello_bbs.size() = " + String.valueOf(bbs.size()) + "_");
                    Log.d(LOG,"bbs_"+bbs.toString()+"_");

                    Button bb;
                    for (HashMap.Entry entry : bbs.entrySet()) {
                        bb = ((Activity)context).findViewById(context.getResources().getIdentifier(entry.getKey().toString(), "id", context.getPackageName()));
                        bb.setText(bbs.get(entry.getKey().toString()));
                        Log.d(LOG, "drawcycle");
                    }
                    Log.d(LOG,"done");
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.d(LOG,"onCancelled");
            }
        });
    }


}
