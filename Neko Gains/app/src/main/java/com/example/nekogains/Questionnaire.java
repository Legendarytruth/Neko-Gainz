package com.example.nekogains;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        String q3_habit = q3_button.getText().toString();

        String q4_weight = ((EditText)findViewById(R.id.q4a)).getText().toString();
        String q4_height = ((EditText)findViewById(R.id.q4b)).getText().toString();

        RadioGroup q5_group = findViewById(R.id.q5);
        RadioButton q5_button = findViewById(q3_group.getCheckedRadioButtonId());
        String q5_goal = q3_button.getText().toString();

        String[] results = {q1_name, q2_age, q3_habit, q4_weight, q4_height, q5_goal};

        Intent intent = getIntent();
        //User user = new User(dbh, q1_name, q2_age, q3_habit, q4_weight, q4_height, q5_goal);

        intent = new Intent(this, MainActivity.class);
        intent.putExtra("RESULTS", results);
        setResult(MainActivity.REQUEST_CODE, intent);
        finish();
    }
}