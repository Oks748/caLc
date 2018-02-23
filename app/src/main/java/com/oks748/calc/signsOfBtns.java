package com.oks748.calc;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Button;

import java.util.HashMap;

import static com.oks748.calc.MainActivity.LOG_TAG;

public class signsOfBtns {


    private Context context;
    //Activity activity;

   /* public signsOfBtns(){
    }

    //controller = new Controller(textView, getApplicationContext());

     CustomButton customButton = (CustomButton) getLayoutInflater().inflate(R.layout.custom_button, null);
        customButton.setValues(id, text);

    public signsOfBtns(Context context,Activity activity){
        this.context=context;
        this.activity=activity;
    }*/

    public void drawBtns(HashMap<String, String> bbs, Activity activity){

       /* if (getResources().getConfiguration().orientation == 1) {//portrait
            setContentView(R.layout.activity_main);
        } else if (getResources().getConfiguration().orientation == 2) { //landscape
            setContentView(R.layout.landscap);
        }*/
        Button bb;


        Log.d(LOG_TAG,"bbsgetHASH_"+bbs.toString()+"_");
        Log.d(LOG_TAG,"HASH_"+bbs.get("btnSqrt")+"_");


        bb = activity.findViewById(R.id.btnIs);

       /* if(findViewById(R.id.btnSqrt).isInLayout()){
            Log.d(LOG_TAG,"HASH_1");
        }else Log.d(LOG_TAG,"HASH_2");*/
        //bb = findViewById(R.id.btnIs);
        Log.d(LOG_TAG,"onDataCh_3");
        bb.setText("=");
        Log.d(LOG_TAG,"onDataCh_4");

        if(activity.findViewById(R.id.btnIs).isInLayout()){
            Log.d(LOG_TAG,"create_1_"+activity.findViewById(R.id.btnIs).isInLayout()+"_");
        }else Log.d(LOG_TAG,"create_2_"+activity.findViewById(R.id.btnIs).isInLayout()+"_");

        //**********************************!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //getResources().getIdentifier("btnSqrt" , "id", getPackageName()));
        Log.d(LOG_TAG,"onfindViewId");
        //bb.setText(bbs.get("btnCE"));

        /*
       for (HashMap.Entry entry : bbs.entrySet()) {

           bb = findViewById(getResources().getIdentifier(entry.getKey().toString() , "id", getPackageName()));
           Log.d(LOG_TAG,"onDataCh_3");
           bb.setText(bbs.get(entry.getValue().toString()));
           Log.d(LOG_TAG,"onDataCh_4");
       }*/


    }

    /*public void makeText(Activity activity) {
        String random = randomize(); //example...
        TextView textView = (TextView) activity.findViewById(R.id.textView);
        textView.setText(randomText);
    }*/

}
