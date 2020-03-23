package com.example.nekogains;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class CustomWorkoutFrag extends Fragment implements View.OnClickListener {
    private View view;
    private User user;
    private EditText workoutName;
    private Button save;
    private Button back;
    private Button lunges;
    private Button squats;
    private Button run;
    private Button burpees;
    private Button jacks;
    private Button pushups;
    private Button chinups;
    private Button benchdips;
    private Button situps;
    private Button planks;
    private Button legraises;
    private ArrayList<Exercise> newcustomworkouts = new ArrayList<>();






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        user = new User(DatabaseHelper.getInstance(MainActivity.getContext()), ((MainActivity)this.getActivity()).getAppUserId());
        view = inflater.inflate(R.layout.customworkout_frag, container, false);
        save = view.findViewById(R.id.customworkoutSave);
        back = view.findViewById(R.id.customworkoutBack);
        workoutName = view.findViewById(R.id.customworkoutName);
        lunges = view.findViewById(R.id.addLunges);
        squats = view.findViewById(R.id.addSquats);
        run = view.findViewById(R.id.addRun);
        burpees = view.findViewById(R.id.addBurpees);
        jacks = view.findViewById(R.id.addJacks);
        pushups = view.findViewById(R.id.addPushups);
        chinups = view.findViewById(R.id.addChinups);
        benchdips = view.findViewById(R.id.addBenchdips);
        situps = view.findViewById(R.id.addSitups);
        planks = view.findViewById(R.id.addPlanks);
        legraises = view.findViewById(R.id.addLegraises);

        save.setOnClickListener(this);
        back.setOnClickListener(this);
        lunges.setOnClickListener(this);
        squats.setOnClickListener(this);
        run.setOnClickListener(this);
        burpees.setOnClickListener(this);
        jacks.setOnClickListener(this);
        pushups.setOnClickListener(this);
        chinups.setOnClickListener(this);
        benchdips.setOnClickListener(this);
        situps.setOnClickListener(this);
        planks.setOnClickListener(this);
        legraises.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addLunges:
                if (newcustomworkouts.contains(Exercise.LUNGES)){
                    Toast.makeText(getContext(), "Lunges are already in workouts!", Toast.LENGTH_SHORT).show();
                }else{
                    newcustomworkouts.add(Exercise.LUNGES);
                }
                break;

            case R.id.addSquats:
                if (newcustomworkouts.contains(Exercise.SQUATS)){
                    Toast.makeText(getContext(), "Squats are already in workouts!", Toast.LENGTH_SHORT).show();
                }else{
                    newcustomworkouts.add(Exercise.SQUATS);
                }
                break;
            case R.id.addRun:
                if (newcustomworkouts.contains(Exercise.RUN)){
                    Toast.makeText(getContext(), "Running is already in workouts!", Toast.LENGTH_SHORT).show();
                }else{
                    newcustomworkouts.add(Exercise.RUN);
                }
                break;
            case R.id.addBurpees:
                if (newcustomworkouts.contains(Exercise.BURPEES)){
                    Toast.makeText(getContext(), "Burpees are already in workouts!", Toast.LENGTH_SHORT).show();
                }else{
                    newcustomworkouts.add(Exercise.BURPEES);
                }
                break;
            case R.id.addJacks:
                if (newcustomworkouts.contains(Exercise.JACKS)){
                    Toast.makeText(getContext(), "Jacks are already in workouts!", Toast.LENGTH_SHORT).show();
                }else{
                    newcustomworkouts.add(Exercise.JACKS);
                }
                break;
            case R.id.addPushups:
                if (newcustomworkouts.contains(Exercise.PUSH_UPS)){
                    Toast.makeText(getContext(), "Push ups are already in workouts!", Toast.LENGTH_SHORT).show();
                }else{
                    newcustomworkouts.add(Exercise.PUSH_UPS);
                }
                break;
            case R.id.addChinups:
                if (newcustomworkouts.contains(Exercise.CHIN_UPS)){
                    Toast.makeText(getContext(), "Chin ups are already in workouts!", Toast.LENGTH_SHORT).show();
                }else{
                    newcustomworkouts.add(Exercise.CHIN_UPS);
                }
                break;
            case R.id.addBenchdips:
                if (newcustomworkouts.contains(Exercise.BENCH_DIPS)){
                    Toast.makeText(getContext(), "Bench Dips are already in workouts!", Toast.LENGTH_SHORT).show();
                }else{
                    newcustomworkouts.add(Exercise.BENCH_DIPS);
                }
                break;
            case R.id.addSitups:
                if (newcustomworkouts.contains(Exercise.SIT_UPS)){
                    Toast.makeText(getContext(), "Sit ups are already in workouts!", Toast.LENGTH_SHORT).show();
                }else{
                    newcustomworkouts.add(Exercise.SIT_UPS);
                }
                break;
            case R.id.addPlanks:
                if (newcustomworkouts.contains(Exercise.PLANKS)){
                    Toast.makeText(getContext(), "Planks are already in workouts!", Toast.LENGTH_SHORT).show();
                }else{
                    newcustomworkouts.add(Exercise.PLANKS);
                }
                break;
            case R.id.addLegraises:
                if (newcustomworkouts.contains(Exercise.LEG_RAISES)){
                    Toast.makeText(getContext(), "Leg raises are already in workouts!", Toast.LENGTH_SHORT).show();
                }else{
                    newcustomworkouts.add(Exercise.LEG_RAISES);
                }
                break;

            case R.id.customworkoutSave:
                if(newcustomworkouts.isEmpty()){
                    Toast.makeText(getContext(), "You didn't add any exercises!", Toast.LENGTH_SHORT).show();
                }
                else if (user.getWorkoutnames().containsKey(workoutName.getText().toString())){
                    Toast.makeText(getContext(), "This name already exists! Change the name to something else", Toast.LENGTH_SHORT).show();
                }else{
                    user.addWorkouts(workoutName.getText().toString(), newcustomworkouts);
                    user.addtoWorkoutList(workoutName.getText().toString());
                    user.saveWorkout();
                    Toast.makeText(getContext(), "You saved your workout! You named it: "+ workoutName.getText().toString() , Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.customworkoutBack:
                openPreworkoutFrag();
                break;
        }

    }

    public void openPreworkoutFrag(){
        Fragment selectedFragment = new PreworkoutFrag();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, selectedFragment).commit();
    }

}
