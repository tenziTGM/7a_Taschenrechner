package com.example.rechner_tenzi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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

        switch(radioButton.getText().toString()) {
            case "+":
                result.setText(value1+value2);
                break;
            case "-":
                result.setText(value1-value2);
                break;
            case "*":
                result.setText(value1*value2);
                break;
            case "/":
                if(value2==0) {
                    result.setText("Error");
                }
                else {
                    result.setText(value1/value2);
                }
                break;
        }



    }


}