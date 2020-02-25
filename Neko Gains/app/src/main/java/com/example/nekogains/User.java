package com.example.nekogains;

import java.util.ArrayList;
import java.util.Hashtable;

public class User {
    private String username;
    private String password;

    private float weight;
    private float height;

    private int xp;
    protected Pet pet;
    protected UserInventory userInventory = new UserInventory();
    private Hashtable<String, ArrayList<Exercise>> exercisePlans = new Hashtable<>();

    public User() {
        this.username = "newUser";
        this.password = "1234";
        this.xp = 0;
    }

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

    public int getXp() {
        return this.xp;
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

    public void increaseXp(int addXP) {
        this.xp += addXP;
        this.pet.setLevel((int)Math.floor(this.xp/1000));
    }

    //OTHER
    public float calculateBMI() {
        return (float) (this.weight/ Math.pow(this.height, 2));
    }
}
