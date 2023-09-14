package com.example.rechner_tenzi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        Button calculate = findViewById(R.id.button_calc);
        calculate.setBackgroundColor(Color.GREEN);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView result = findViewById(R.id.textview_result);
        result.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                result.setText(0);
                return true;
            }
        });

    }


    public void calculate(View view) {
        EditText input1 = view.findViewById(R.id.edit_wert1);
        int value1 = Integer.parseInt(input1.getText().toString());

        EditText input2 = view.findViewById(R.id.edit_wert2);
        int value2 = Integer.parseInt(input2.getText().toString());

        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);

        TextView result = view.findViewById(R.id.textview_result);

        int result_value = 0;

        switch(radioButton.getText().toString()) {
            case "+":
                result_value = value1+value2;
                break;
            case "-":
                result_value = value1-value2;
                break;
            case "*":
                result_value = value1*value2;
                break;
            case "/":
                if(value2==0) {
                    result.setText("Error");
                }
                else {
                    result_value = value1/value2;
                }
                break;
        }
        if(result_value<0) {
            result.setTextColor(Color.RED);
        }
        else {
            result.setTextColor(Color.BLACK);
        }
        result.setText(result_value);



    }

    public void saveValues(View view) {
        EditText input1 = view.findViewById(R.id.edit_wert1);
        int value1 = Integer.parseInt(input1.getText().toString());

        EditText input2 = view.findViewById(R.id.edit_wert2);
        int value2 = Integer.parseInt(input2.getText().toString());

        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("value1",value1);
        editor.putInt("value2",value2);
        editor.commit();
    }
    public void readValues(View view) {
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);

        EditText input1 = view.findViewById(R.id.edit_wert1);
        int value1 = sharedPreferences.getInt("value1",0);
        input1.setText(value1);

        EditText input2 = view.findViewById(R.id.edit_wert2);
        int value2 = sharedPreferences.getInt("value2",0);
        input2.setText(value2);
    }

}