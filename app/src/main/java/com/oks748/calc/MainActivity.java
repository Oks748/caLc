package com.oks748.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private String display = "";
    private String oper = "";
    private String result = "";
    private boolean pressIs = false;
    private boolean pressSign = false;
    private boolean pressPercent = false;
    private boolean pressSqrt = false;
    private boolean press1Div = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = findViewById(R.id.textView);
        screen.setText(display);
    }

    public void onClickNumber(View v){
        if(pressIs == true) clearC();
        if (result != "") {
            Button b = (Button) v;
            display += b.getText();
            screen.setText(result + oper + display);
        } else {
            Button b = (Button) v;
            display += b.getText();
            screen.setText(display);
        }
    }

    public void onClickOperator4(View v){
        if(display == "") {
            Button b = (Button) v;
            if (b.getText() == "-"){
                display += b.getText();
                screen.setText(display);
            }
        }else {
            if(oper == "") {
                result = display;
                display = "";
                Button b = (Button) v;
                oper = b.getText().toString();
                screen.setText(String.format("%s%s", result, oper));
            }else {
                Button b = (Button) v;
                oper = b.getText().toString();
                screen.setText(String.format("%s%s", result, oper));
            }
        }
    }

   private double arithmetic(){
        switch (oper){
            case "-": return Double.valueOf(result) - Double.valueOf(display);
            case "+": return Double.valueOf(result) + Double.valueOf(display);
            case "×": return Double.valueOf(result) * Double.valueOf(display);
            case "÷":  try{
                    return Double.valueOf(result) / Double.valueOf(display);
                } catch (Exception e){
                    Log.d("Calc", e.getMessage());
                    Toast.makeText(this, "Ділення на нуль", Toast.LENGTH_LONG).show();
                }
            default: return -1;
        }
    }

   public void onClickPercent(View v){
     /*   if(pressbtnIs == true) clearC();
       if(display == "") {
            Button b = (Button) v;
            if (b.getText() == "-"){
                display += b.getText();
                screen.setText(display);
            }
        }else {
            if(oper == "") {
                result = display;
                display = "";
                Button b = (Button) v;
                oper = b.getText().toString();
                screen.setText(String.format("%s%s", result, oper));
            }else {
                Button b = (Button) v;
                oper = b.getText().toString();
                screen.setText(String.format("%s%s", result, oper));
            }
        }
        */
    }

    public void onClickSqrt(View v){
        if(pressIs == true) clearC();
        if(display != "" && result != "" && oper != "") {

        }else{
            //only display!=""

            try {
                result = String.valueOf(Math.sqrt(Double.valueOf(display)));
            } catch (Exception e){
                Log.d("Calc", e.getMessage());
                Toast.makeText(this, "Підкореневий вираз менше нуля", Toast.LENGTH_LONG).show();
            }
            screen.setText(result);

        }


    }


    public void onClickSign(View v){

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

       result = String.valueOf(arithmetic());


        result= result.indexOf(".") < 0 ? result : result.replaceAll("0*$", "").replaceAll("\\.$", "");
        screen.setText(result);



        pressIs = true;
    }

   private void clearC(){
        result = "";
        oper = "";
        display = "";
    }

   public void onClickC(View v){
        clearC();
        screen.setText(display);
    }

   public void onClickCE(View v){
        display = "";
        screen.setText(result+oper);
    }
}

