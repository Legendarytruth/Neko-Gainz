package com.example.nekogains;

import java.util.ArrayList;
import java.util.Hashtable;

public class User {
    private String username;
    private String password;

    private float weight = 0;
    private float height = 0;
    private int money = 100;

    public Pet pet;
    private Hashtable<String, ArrayList<Exercise>> exercisePlans = new Hashtable<>();

    //GETTING ATTRIBUTES
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public float getWeight() {
        return this.weight;
    }

    public float getHeight() {
        return this.height;
    }

    public ArrayList<Exercise> getExercisePlan(String name) {
        return this.exercisePlans.get(name);
    }

    public int getMoneyAmount() {
        return this.money;
    }

    //CHANGING ATTRIBUTES
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void addExercisePlan(String planName, ArrayList<Exercise> exercises) {
        this.exercisePlans.put(planName, exercises);
    }

    public void addExercisetoPlan(String planName, Exercise exercise) {
        this.exercisePlans.get(planName).add(exercise);
    }

    public void addMoney(int amount) {
        this.money += amount;
    }

    public void removeMoney(int amount){this.money -= amount;}

    //OTHER
    public float calculateBMI() {
        return (float) (this.weight/ Math.pow(this.height, 2));
    }
}
