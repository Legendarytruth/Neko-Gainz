package com.example.nekogains;

import java.util.ArrayList;
import java.util.Hashtable;

public class User {

    DatabaseHelper dbh;
    int id;

    public User(DatabaseHelper dbh, String name, String habit, String weight, String height, String goal) {
        this.dbh = dbh;
        this.dbh.insertNewUser(name, habit, weight, height, goal);
        this.dbh.insertNewGame("0", "0");
    }
    private String username;
    private String password;



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
        return Float.parseFloat((dbh.getUserData(id, "WEIGHT")));
    }

    public float getHeight() {
        return Float.parseFloat((dbh.getUserData(id, "HEIGHT")));
    }

    public ArrayList<Exercise> getExercisePlan(String name) {
        return this.exercisePlans.get(name);
    }

    public int getMoneyAmount() {
        return Integer.parseInt((dbh.getUserData(id, "MONEY")));
    }

    public int getExperience() {
        return Integer.parseInt((dbh.getUserData(id, "EXPERIENCE")));
    }

    //CHANGING ATTRIBUTES
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWeight(String weight) {
        dbh.updateUserData(id, weight);
    }

    public void setHeight(String height) {
        dbh.updateUserData(id, height);

    }

    public void addExercisePlan(String planName, ArrayList<Exercise> exercises) {
        this.exercisePlans.put(planName, exercises);
    }

    public void addExercisetoPlan(String planName, Exercise exercise) {
        this.exercisePlans.get(planName).add(exercise);
    }

    public void addMoney(String amount) {
        dbh.updateGame(id, amount);
    }

    public void removeMoney(int amount){
        int balance = getMoneyAmount();
        dbh.updateGame(id, Integer.toString(balance-amount));
    }

    //OTHER
    public float getBMI() {
        return Float.parseFloat((dbh.getUserData(id, "BMI")));
    }
}
