package com.example.tenzi_rechner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rechner_tenzi.R;

import java.util.Spliterator;

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


    public void calculate(View v) {
        EditText input1 = findViewById(R.id.edit_wert1);
        double value1 = Double.parseDouble(input1.getText().toString());

        Log.i("App","Value1 geholt");
        EditText input2 = findViewById(R.id.edit_wert2);
        double value2 = Double.parseDouble(input2.getText().toString());

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);

        TextView result = findViewById(R.id.textview_result);

        double result_value = 0;

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
        result.setText(String.valueOf(result_value));



    }

    public void saveValues(View view) {
        EditText input1 = findViewById(R.id.edit_wert1);
        double value1 = Double.parseDouble(input1.getText().toString());

        EditText input2 = findViewById(R.id.edit_wert2);
        double value2 = Double.parseDouble(input2.getText().toString());

        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("value1",(float)value1);
        editor.putFloat("value2",(float)value2);
        editor.commit();

        Context context = getApplicationContext();
        CharSequence text = "Gespeichert!";
        Toast toast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
        toast.show();
    }
    public void readValues(View view) {
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);

        EditText input1 = findViewById(R.id.edit_wert1);
        double value1 = sharedPreferences.getFloat("value1",0);
        input1.setText(String.valueOf(value1));

        EditText input2 = findViewById(R.id.edit_wert2);
        double value2 = sharedPreferences.getFloat("value2",0);
        input2.setText(String.valueOf(value2));
    }

}