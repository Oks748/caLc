package com.oks748.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private String num1 = "";
    private String num2 = "";
    private String operator = "";
    private boolean pressIs = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = findViewById(R.id.textView);
        screen.setText(num1);
    }

    public void onClickNumber(View v) {
        if (pressIs) {
            num1 = "";
            num2 = "";
            operator = "";
            pressIs = false;
        }
        Button b = (Button) v;

        if (operator != "") {
            if (num2 == "") {
                if (b.getText().toString().equals(".")) {
                    num2 = "0.";
                    screen.setText(num1+operator+num2);
                } else {
                    num2 += b.getText();
                    screen.setText(num1+operator+num2);
                }
            } else {
                if (b.getText() == "." && num2.indexOf(".") != -1) {
                    screen.setText(num1+operator+num2);
                } else {
                    num2 += b.getText();
                    screen.setText(num1+operator+num2);
                }
            }
        } else{
            if (num1 == "") {
                if (b.getText().toString().equals(".")) {
                    num1 = "0.";
                    screen.setText(num1);
                } else {
                    num1 += b.getText();
                    screen.setText(num1);
                }
            } else {
                if (b.getText() == "." && num1.indexOf(".") != -1) {
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
           pressIs = false;
       }

       Button b = (Button) v;
       if (Objects.equals(num1, "")) {
            if (b.getText().toString().equals("-")) {
                num1 += b.getText();
                screen.setText(num1);
            }
       } else if (Objects.equals(num2, "")) {
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
       if (pressIs) {
           num2 = "";
           operator = "";
           pressIs = false;
       }

       if (!num2.equals("")) {
           num2 = String.valueOf(Double.valueOf(num1)*Double.valueOf(num2)/100);
           num2 = num2.indexOf(".") < 0 ? num2 : num2.replaceAll("0*$", "").replaceAll("\\.$", "");
           screen.setText(num1+operator+num2);
       }else if(num1 != "" && operator == "") {
           num1 = String.valueOf(Double.valueOf(num1)*Double.valueOf(num1)/100);
           num1 = num1.indexOf(".") < 0 ? num1 : num1.replaceAll("0*$", "").replaceAll("\\.$", "");
           screen.setText(num1);
       }
   }

   public void onClick1Div(View v){
       if (pressIs) {
           num2 = "";
           operator = "";
           pressIs = false;
       }

       if (num2 != "") {
           try {
               num2 = String.valueOf(1 / Double.valueOf(num2));
           } catch (Exception e) {
               Log.d("Calc", e.getMessage());
               Toast.makeText(this, "Ділення на нуль", Toast.LENGTH_LONG).show();
           }
           num2 = num2.indexOf(".") < 0 ? num2 : num2.replaceAll("0*$", "").replaceAll("\\.$", "");
           screen.setText(num1+operator+num2);
       }else if(num1 != "" && operator == ""){
           try {
               num1 = String.valueOf(1/Double.valueOf(num1));
           } catch (Exception e) {
               Log.d("Calc", e.getMessage());
               Toast.makeText(this, "Ділення на нуль", Toast.LENGTH_LONG).show();
           }
           num1 = num1.indexOf(".") < 0 ? num1 : num1.replaceAll("0*$", "").replaceAll("\\.$", "");
           screen.setText(num1);
       }
    }

   public void onClickSign(View v){
       if (pressIs) {
           num2 = "";
           operator = "";
           pressIs = false;
       }

       if(num2 != "") {
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
       if (pressIs) {
           num2 = "";
           operator = "";
           pressIs = false;
       }

       if (num2 != "") {
           try {
               num2 = String.valueOf(Math.sqrt(Double.valueOf(num2)));
           } catch (Exception e) {
               Log.d("Calc", e.getMessage());
               Toast.makeText(this, "Підкореневий вираз менше нуля", Toast.LENGTH_LONG).show();
           }
           num2 = num2.indexOf(".") < 0 ? num2 : num2.replaceAll("0*$", "").replaceAll("\\.$", "");
           screen.setText(num1+operator+num2);
       }else if(num1 != "" && operator == ""){
           try {
               num1 = String.valueOf(Math.sqrt(Double.valueOf(num1)));
           } catch (Exception e) {
               Log.d("Calc", e.getMessage());
               Toast.makeText(this, "Підкореневий вираз менше нуля", Toast.LENGTH_LONG).show();
           }
           num1 = num1.indexOf(".") < 0 ? num1 : num1.replaceAll("0*$", "").replaceAll("\\.$", "");
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

   public void onClickIs(View v){
       pressIs = true;
       if (num2 != "") {
                num1 = String.valueOf(arithmetic());
                num1 = num1.indexOf(".") < 0 ? num1 : num1.replaceAll("0*$", "").replaceAll("\\.$", "");
                screen.setText(num1);
            } else if (num1 != "" && operator == "") {
                screen.setText(num1);
            }
    }

   public void onClickBack1(View v) {
       if (pressIs) {
           num2 = "";
           operator = "";
           pressIs = false;
       }

       if (num2 != "") {
           num2 = num2.substring(0, num2.length() - 1);
           screen.setText(num1 + operator + num2);
       }
       if (operator == "" && num1 != "") {
           num1 = num1.substring(0, num1.length() - 1);
           screen.setText(num1);
       }
   }

   public void onClickC(View v){
       num1 = "";
       num2 = "";
       operator = "";
       screen.setText(num1);
    }

   public void onClickCE(View v){
        if(num2 != "") {
            num2 = "";
            screen.setText(num1 + operator);
        }
    }
}

