package com.example.nekogains;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Questionnaire extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        Button button = findViewById(R.id.button_send);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                get_results();
            }
        });
    }

    public void get_results(){
        String q1_name = ((EditText)findViewById(R.id.q1a)).getText().toString();

        RadioGroup q2_group = findViewById(R.id.q2);
        RadioButton q2_button = findViewById(q2_group.getCheckedRadioButtonId());
        String q2_age = q2_button.getText().toString();

        RadioGroup q3_group = findViewById(R.id.q3);
        RadioButton q3_button = findViewById(q3_group.getCheckedRadioButtonId());
        String q3_age = q3_button.getText().toString();

        String q4_weight = ((EditText)findViewById(R.id.q4a)).getText().toString();
        String q4_height = ((EditText)findViewById(R.id.q4b)).getText().toString();

        RadioGroup q5_group = findViewById(R.id.q5);
        RadioButton q5_button = findViewById(q3_group.getCheckedRadioButtonId());
        String q5_age = q3_button.getText().toString();

    }

}
