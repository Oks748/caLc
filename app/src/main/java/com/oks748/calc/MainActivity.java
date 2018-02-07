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
    private String num1 = "";
    private String num2 = "";
    private String operator = "";
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
        screen.setText(result);
    }

    public void onClickNumber(View v){
        if(pressIs == true) {
            clearC();
            pressIs = false;
        }

        if (operator != "") {  //exist num1 and operator || empty num2 or partially
            Button b = (Button) v;
            num2 += b.getText();
            screen.setText(num1 + operator + num2);
        } else {                                        // not exist num1 or partially
            Button b = (Button) v;
            num1 += b.getText();
            screen.setText(num1);
        }
    }

    public void onClickOperator4(View v) {
        if (num1 == "") {
            Button b = (Button) v;
            if (b.getText() == "-") {
                num1 += b.getText();
                screen.setText(num1);
            }
        } else if (num2 == "" && operator != "") {
            Button b = (Button) v;
            operator = b.getText().toString();
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
       if (operator != "" && num2 != "") {
           num2 = String.valueOf(Double.valueOf(num2)/100);
           screen.setText(num1+operator+num2);
       }else if(num1 != "" && operator == "") {
           num1 = String.valueOf(Double.valueOf(num1)*Double.valueOf(num1)/100);
           screen.setText(num1);
       }
   }

   public void onClick1Div(View v){
       if (operator != "" && num2 != "") {
           try {
               num2 = String.valueOf(1 / Double.valueOf(num2));
           } catch (Exception e) {
               Log.d("Calc", e.getMessage());
               Toast.makeText(this, "Ділення на нуль", Toast.LENGTH_LONG).show();
           }
           screen.setText(num1+operator+num2);
       }else if(num1 != "" && operator == ""){
           try {
               num1 = String.valueOf(1/Double.valueOf(num1));
           } catch (Exception e) {
               Log.d("Calc", e.getMessage());
               Toast.makeText(this, "Ділення на нуль", Toast.LENGTH_LONG).show();
           }
           screen.setText(num1);
       }
    }

   public void onClickSign(View v){
       if(operator != "" && num2 != "") {
            num2 = String.valueOf((-1) * Double.valueOf(num2));
            num2 = num2.indexOf(".") < 0 ? num2 : num2.replaceAll("0*$", "").replaceAll("\\.$", "");
            if (num2.indexOf("-") == 0){
                screen.setText(num1+operator+"("+num2+")");
            }else screen.setText(num1+operator+num2);
        }else  if(num1 != "" && operator == ""){
           num1 = String.valueOf((-1)*Double.valueOf(num1));
           num1 = num1.indexOf(".") < 0 ? num1 : num1.replaceAll("0*$", "").replaceAll("\\.$", "");
           screen.setText(num1);
       }
    }

   public void onClickSqrt(View v){
       if (operator != "" && num2 != "") {
           try {
               num2 = String.valueOf(Math.sqrt(Double.valueOf(num2)));
           } catch (Exception e) {
               Log.d("Calc", e.getMessage());
               Toast.makeText(this, "Підкореневий вираз менше нуля", Toast.LENGTH_LONG).show();
           }
           screen.setText(num1+operator+num2);
       }else if(num1 != "" && operator == ""){
           try {
               num1 = String.valueOf(Math.sqrt(Double.valueOf(num1)));
           } catch (Exception e) {
               Log.d("Calc", e.getMessage());
               Toast.makeText(this, "Підкореневий вираз менше нуля", Toast.LENGTH_LONG).show();
           }
           screen.setText(num1);
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


    //if (operator != "" && num2 != "") {                num2
    // }else if(num1 != "" && operator == ""){       num1



   public void onClickIs(View v){
        if(pressIs == false) {



            if(num2 != "") {
                num1 = String.valueOf(arithmetic());
                num1 = num1.indexOf(".") < 0 ? num1 : num1.replaceAll("0*$", "").replaceAll("\\.$", "");
                screen.setText(num1); //result of arifm4
            }


        } else pressIs = true;
    }

   public void onClickBack1(View v){
       if(num2 != "") {
           num2 = num2.substring(0,num2.length()-2);;
           screen.setText(num1+operator+num2);
       }
       if(operator == "" && num1 != "") {
           num1 = num1.substring(0,num1.length()-2);
           screen.setText(num1);
       }

   }

   private void clearC(){
        num1 = "";
        num2 = "";
        operator = "";
        result = "";
    }

   public void onClickC(View v){
        clearC();
        screen.setText(result);
    }

   public void onClickCE(View v){
        if(num2 != "") {
            num2 = "";
            screen.setText(num1 + operator);
        }
    }
}

