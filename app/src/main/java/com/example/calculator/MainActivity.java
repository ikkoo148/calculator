package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView result_tv,solution_tv;
    MaterialButton button_c,button_open_bracket,button_close_bracket;
    MaterialButton button_division,button_multiply,button_add,button_sub,button_equals_to;
    MaterialButton button_dot,button_all_clear;
    MaterialButton button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9,button_0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_tv = findViewById(R.id.result_textview);
        solution_tv = findViewById(R.id.solution_textview);
        assignId(button_c,R.id.button_c);
        assignId(button_open_bracket,R.id.button_open_bracket);
        assignId(button_close_bracket,R.id.button_c);
        assignId(button_c,R.id.button_close_bracket);
        assignId(button_division,R.id.button_divide);
        assignId(button_multiply,R.id.button_multiply);
        assignId(button_add,R.id.button_addition);
        assignId(button_sub,R.id.button_subtraction);
        assignId(button_equals_to,R.id.button_equals_to);
        assignId(button_dot,R.id.button_dot);
        assignId(button_all_clear,R.id.button_ac);
        assignId(button_1,R.id.button_1);
        assignId(button_2,R.id.button_2);
        assignId(button_3,R.id.button_3);
        assignId(button_4,R.id.button_4);
        assignId(button_5,R.id.button_5);
        assignId(button_6,R.id.button_6);
        assignId(button_7,R.id.button_7);
        assignId(button_8,R.id.button_8);
        assignId(button_9,R.id.button_9);
        assignId(button_0,R.id.button_0);
    }
    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
    

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution_tv.getText().toString();

        if(buttonText.equals("AC")){
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution_tv.setText(result_tv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }

        solution_tv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            result_tv.setText(finalResult);
        }

    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}