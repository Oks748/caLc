package com.oks748.calc;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private boolean bound = false;
    private workBaseService myBindService;

    private TextView screen;
    private String num1 = "";
    private String num2 = "";
    private String operator = "";
    private boolean pressIs = false;
    private boolean pressSDPS = false;
    private boolean noReadnum1 = false;
    private boolean noReadnum2 = false;
    private static final String LOG_TAG = "myLogs";

    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == 1) {//portrait
            setContentView(R.layout.activity_main);
        } else if (getResources().getConfiguration().orientation == 2) { //landscape
            setContentView(R.layout.landscap);
        }
        screen = findViewById(R.id.textView);
        screen.setText(num1);

        Log.d(LOG_TAG, "MainActivity: onCreate_End");
   }


   public void drawBtns(HashMap<String, String> bbs){

       //Log.d(LOG_TAG,"bbs_"+bbs.toString()+"_");

       Button bb;
      // bbs = signsOfBtns.getHashmap();
       Log.d(LOG_TAG,"bbsgetHASH_"+bbs.toString()+"_");
       Log.d(LOG_TAG,"HASH_"+bbs.get("btnSqrt")+"_");

       bb = findViewById(R.id.btnCE);
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

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "MainActivity: onStart()");
    }

    private ServiceConnection sConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            Log.d(LOG_TAG, "MainActivity onServiceConnected"+bound+"_");
            myBindService = ((workBaseService.LocalBinder) binder).getService();
            bound = true;
            myBindService.baseConnect();
            Log.d(LOG_TAG, "MainActivity: baseConnect");


            //drawBtns();
            //Log.d(LOG_TAG, "MainActivity: drawBtns()");


            Log.d(LOG_TAG, "MainActivity onServiceConnected"+bound+"_end");
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(LOG_TAG, "MainActivity onServiceDisconnected"+bound+"_");
            bound = false;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        screen.setText("hello");
        Intent intent = new Intent(this, workBaseService.class);
        bindService(intent, sConn, BIND_AUTO_CREATE);
        Log.d(LOG_TAG, "MainActivity: onResume_"+bound+"_");  //false ???
        if (bound) {
            //myFunc
            Log.d(LOG_TAG, "MainActivity: onResume_baseConnect()________");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "MainActivity: onPause()_"+bound+"_begin");
        if (bound){
            unbindService(sConn);
            bound = false;
        }
        Log.d(LOG_TAG, "MainActivity: onPause()_"+bound+"_end");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "MainActivity: onStop()");
    }

    @Override
   protected void onSaveInstanceState(Bundle outState) {
        outState.putString("num1", num1);
        outState.putString("num2", num2);
        outState.putString("operator", operator);
        outState.putBoolean("noReadnum1", noReadnum1);
        outState.putBoolean("noReadnum2", noReadnum2);
        outState.putBoolean("pressIs", pressIs);
        outState.putBoolean("pressSDPS", pressSDPS);
        outState.putString("screen",screen.getText().toString());
        outState.putBoolean("bound", bound);
        super.onSaveInstanceState(outState);
    }

   @Override
   protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        num1 = savedInstanceState.getString("num1");
        num2 = savedInstanceState.getString("num2");
        operator = savedInstanceState.getString("operator");
        noReadnum1 = savedInstanceState.getBoolean("noReadnum1");
        noReadnum2 = savedInstanceState.getBoolean("noReadnum2");
        pressIs = savedInstanceState.getBoolean("pressIs");
        pressSDPS = savedInstanceState.getBoolean("pressSDPS");
        screen.setText(savedInstanceState.getString("screen"));
        bound = savedInstanceState.getBoolean("bound");
    }

   public void onClickNumber(View v) {
        if (pressIs) {
            num1 = "";
            num2 = "";
            operator = "";
            noReadnum1 = false;
            noReadnum2 = false;
            pressIs = false;
        }
        Button b = (Button) v;

         if (pressSDPS) {
             num2 = "";
             noReadnum2 = false;
             pressSDPS = false;
         }

       if (!Objects.equals(operator, "")) {
           if (Objects.equals(num2, "") && !noReadnum2 &&  num2.length() <= 13) {
               if (b.getText().toString().equals(".")) {
                       num2 = "0.";
                       screen.setText(num1+operator+num2);
               } else {
                       num2 += b.getText();
                       screen.setText(num1+operator+num2);
               }
           } else {
               if (b.getText().toString().equals(".") && num2.indexOf(".") != -1) {
                       screen.setText(num1+operator+num2);
               } else {
                   num2 += b.getText();
                   screen.setText(num1+operator+num2);
               }
           }
       } else {
           if (Objects.equals(num1, "") && !noReadnum1 &&  num1.length() <= 13) {
               if (b.getText().toString().equals(".")) {
                   num1 = "0.";
                   screen.setText(num1);
               } else {
                   num1 += b.getText();
                   screen.setText(num1);
               }
           } else if(!Objects.equals(num1, "")){
               if ( b.getText().toString().equals(".") && Objects.equals(num1, "-")) {
                   num1 = "-0.";
                   screen.setText(num1);
               } else if (b.getText().toString().equals(".") && num1.indexOf(".") != -1) {
                   screen.setText(num1);
               } else {
                   num1 += b.getText();
                   screen.setText(num1);
               }
           }
       }
   }

   public void onClickOperator4(View v) {
       if (pressIs) {
           num2 = "";
           operator = "";
           noReadnum2 = false;
           pressIs = false;
       }

       Button b = (Button) v;
       if (Objects.equals(num1, "")) {
           if (b.getText().toString().equals("-")) {
               num1 += b.getText();
               screen.setText(num1);
           }
       } else if (Objects.equals(num1, "-")){
           num1 = "0";
           operator = b.getText().toString();
           screen.setText(num1 + operator);
       }else if (Objects.equals(num2, "")) {
            operator = b.getText().toString();
            screen.setText(num1 + operator);
       }else if (!Objects.equals(num2, "")) {
           num1 = String.valueOf(arithmetic());
           num1 = num1.indexOf(".") < 0 ? num1 : num1.replaceAll("0*$", "").replaceAll("\\.$", "");
           operator = b.getText().toString();
           num2 = "";
           noReadnum2 = false;
           pressIs = false;
           screen.setText(num1 + operator);
       }
    }

   private double arithmetic(){
        switch (operator){
            case "-":  return Double.valueOf(num1) - Double.valueOf(num2);
            case "+": return Double.valueOf(num1) + Double.valueOf(num2);
            case "×": return Double.valueOf(num1) * Double.valueOf(num2);
            case "÷": try{
                    return Double.valueOf(num1) / Double.valueOf(num2);
                } catch (Exception e){
                    Log.d("Calc", e.getMessage());
                    Toast.makeText(this, "Ділення на нуль", Toast.LENGTH_LONG).show();
                }
            default: return 0;
        }
    }

   public void onClickPercent(View v) {
       if (pressIs) {
           num2 = "";
           operator = "";
           noReadnum2 = false;
           pressIs = false;
       }

       if (!Objects.equals(num2, "")) {
               noReadnum2 = true;
               num2 = String.valueOf(Double.valueOf(num1)*Double.valueOf(num2)/100);
               num2 = num2.indexOf(".") < 0 ? num2 : num2.replaceAll("0*$", "").replaceAll("\\.$", "");
               screen.setText(num1+operator+num2);
               pressSDPS = true;
       }else if(!Objects.equals(num1, "") && Objects.equals(operator, "")) {
           if (Objects.equals(num1, "-") || Objects.equals(num1, "-0.") || Objects.equals(num1, "-0")) {
               num1 = "0";
               screen.setText(num1);
           } else {
               num1 = String.valueOf(Double.valueOf(num1) * Double.valueOf(num1) / 100);
               num1 = num1.indexOf(".") < 0 ? num1 : num1.replaceAll("0*$", "").replaceAll("\\.$", "");
               screen.setText(num1);
               pressIs = true;
           }
           noReadnum1 = true;
       }
   }

   public void onClick1Div(View v){
       if (pressIs) {
           num2 = "";
           operator = "";
           noReadnum2 = false;
           pressIs = false;
       }

       if (!Objects.equals(num2, "")) {
           try {
               num2 = String.valueOf(1 / Double.valueOf(num2));
           } catch (Exception e) {
               Log.d(LOG_TAG, e.getMessage());
               Toast.makeText(this, "Ділення на нуль", Toast.LENGTH_LONG).show();
           }
           noReadnum2 = true;
           num2 = num2.indexOf(".") < 0 ? num2 : num2.replaceAll("0*$", "").replaceAll("\\.$", "");
           screen.setText(num1+operator+num2);
           pressSDPS = true;
       }else if(!Objects.equals(num1, "") && Objects.equals(operator, "")){
           if (Objects.equals(num1, "-") || Objects.equals(num1, "-0.") || Objects.equals(num1, "-0")) {
               num1 = "0";
               screen.setText(num1);
           }else {
               try {
                   num1 = String.valueOf(1 / Double.valueOf(num1));
               } catch (Exception e) {
                   Log.d(LOG_TAG, e.getMessage());
                   Toast.makeText(this, "Ділення на нуль", Toast.LENGTH_LONG).show();
               }
               num1 = num1.indexOf(".") < 0 ? num1 : num1.replaceAll("0*$", "").replaceAll("\\.$", "");
               screen.setText(num1);
               pressIs = true;
           }
           noReadnum1 = true;
       }
    }

   public void onClickSign(View v){
       if (pressIs) {
           num2 = "";
           operator = "";
           noReadnum2 = false;
           pressIs = false;
       }

       if(!Objects.equals(num2, "")) {
            if(Objects.equals(num2, "-0")|| Objects.equals(num2, "0") || Objects.equals(num2, "-0.")){
                num2 = "0";
                screen.setText(String.format("%s%s%s", num1, operator, num2));
            }else {
                num2 = String.valueOf((-1) * Double.valueOf(num2));
                num2 = num2.indexOf(".") < 0 ? num2 : num2.replaceAll("0*$", "").replaceAll("\\.$", "");
                if (num2.indexOf("-") == 0) {
                    screen.setText(String.format("%s%s(%s)", num1, operator, num2));
                } else screen.setText(String.format("%s%s%s", num1, operator, num2));
            }
            pressSDPS = true;
            noReadnum2 = true;
       }else  if(!Objects.equals(num1, "") && Objects.equals(operator, "")){
           if (Objects.equals(num1, "-") || Objects.equals(num1, "0") || Objects.equals(num1, "-0")) {
               num1 = "0";
               screen.setText(num1);
           }else {
               num1 = String.valueOf((-1) * Double.valueOf(num1));
               num1 = num1.indexOf(".") < 0 ? num1 : num1.replaceAll("0*$", "").replaceAll("\\.$", "");
               screen.setText(num1);
               pressIs = true;
           }
           noReadnum1 = true;
       }
    }

   public void onClickSqrt(View v){
       if (pressIs) {
           num2 = "";
           operator = "";
           noReadnum1 = false;
           noReadnum2 = false;
           pressIs = false;
       }

       if (!Objects.equals(num2, "")) {
           try {
               num2 = String.valueOf(Math.sqrt(Double.valueOf(num2)));
           } catch (Exception e) {
               Toast.makeText(this, "Підкореневий вираз менше нуля", Toast.LENGTH_LONG).show();
           }
           noReadnum2 = true;
           num2 = num2.indexOf(".") < 0 ? num2 : num2.replaceAll("0*$", "").replaceAll("\\.$", "");
           screen.setText(String.format("%s%s%s", num1, operator, num2));
           pressSDPS = true;
       }else if(!Objects.equals(num1, "") && Objects.equals(operator, "")){
           if (Objects.equals(num1, "-") || Objects.equals(num1, "-0.") || Objects.equals(num1, "-0")) {
               num1 = "0";
               screen.setText(num1);
           }else {
               try {
                   num1 = String.valueOf(Math.sqrt(Double.valueOf(num1)));
               } catch (Exception e) {
                   Toast.makeText(this, "Підкореневий вираз менше нуля", Toast.LENGTH_LONG).show();
               }
               num1 = num1.indexOf(".") < 0 ? num1 : num1.replaceAll("0*$", "").replaceAll("\\.$", "");
               screen.setText(num1);
               pressIs = true;
           }
           noReadnum1 = true;
       }
   }

  /* operators
        / -->exeption div0
        * + -
        ------------------------------------------------------
        sqrt-->exeption inbrackets>=0
        %
        1/x-->exeption div0;   changetextonscreen
        changesign---->changetextonscreen
   */

   public void onClickIs(View v){
       if (!Objects.equals(num2, "")) {
                num1 = String.valueOf(arithmetic());
                num1 = num1.indexOf(".") < 0 ? num1 : num1.replaceAll("0*$", "").replaceAll("\\.$", "");
                screen.setText(num1);
                pressIs = true;
       } else if (!Objects.equals(num1, "") && Objects.equals(operator, "")) {
                screen.setText(num1);
       }
    }

   public void onClickBack1(View v) {
       if (pressIs) {
           num2 = "";
           operator = "";
           noReadnum2 = false;
           pressIs = false;
       }

       if (!Objects.equals(num2, "")) {
           if(Objects.equals(num2, "Infinity") || Objects.equals(num2, "NaN")){
               num2 = "";
               noReadnum2 = false;
           }else if(num2.length() == 1){
               num2 = "";
               noReadnum2 = false;
           }else {
               num2 = num2.substring(0, num2.length() - 1);
           }
           screen.setText(String.format("%s%s%s", num1, operator, num2));
       }else  if (!Objects.equals(operator, "")) {
           operator = "";
           screen.setText(num1);
       }else if (!Objects.equals(num1, "")) {
           if(Objects.equals(num1, "Infinity") || Objects.equals(num1, "NaN")){
               num1 = "";
               noReadnum1 = false;
           }else if(num1.length() == 1){
               num1 = "";
               noReadnum1 = false;
           }else {
               num1 = num1.substring(0, num1.length() - 1);
           }
           screen.setText(num1);
       }
   }

   public void onClickC(View v){
       num1 = "";
       num2 = "";
       operator = "";
       noReadnum1 = false;
       noReadnum2 = false;
       screen.setText(num1);
    }

   public void onClickCE(View v){
        if(!Objects.equals(num2, "")) {
            num2 = "";
            if(pressIs) operator = "";
            noReadnum2 = false;
            screen.setText(String.format("%s%s", num1, operator));
        }
    }
}

