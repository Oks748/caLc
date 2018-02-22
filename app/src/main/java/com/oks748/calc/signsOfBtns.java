package com.oks748.calc;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.util.HashMap;

import static com.oks748.calc.MainActivity.LOG_TAG;

public class signsOfBtns extends AppCompatActivity {

    public void drawBtns(HashMap<String, String> bbs){

        Button bb;

        Log.d(LOG_TAG,"bbsgetHASH_"+bbs.toString()+"_");
        Log.d(LOG_TAG,"HASH_"+bbs.get("btnSqrt")+"_");

        bb = findViewById(R.id.btnSqrt);//**********************************!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //getResources().getIdentifier("btnSqrt" , "id", getPackageName()));
        Log.d(LOG_TAG,"onfindViewId");
        bb.setText(bbs.get("btnCE"));

        /*
       for (HashMap.Entry entry : bbs.entrySet()) {

           bb = findViewById(getResources().getIdentifier(entry.getKey().toString() , "id", getPackageName()));
           Log.d(LOG_TAG,"onDataCh_3");
           bb.setText(bbs.get(entry.getValue().toString()));
           Log.d(LOG_TAG,"onDataCh_4");
       }*/


    }

}
