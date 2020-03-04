package com.example.nekogains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

public class User implements Serializable {

    DatabaseHelper dbh;
    int id;

    private String username;
    private String password;

    private Pet pet;
    private static UserInventory userInventory = new UserInventory();
    private Hashtable<String, ArrayList<Exercise>> exercisePlans = new Hashtable<>();

    public User(DatabaseHelper dbh, int id) {
        this.dbh = dbh;
        this.id = id;
        this.username = "newUser";
        this.password = "1234";
        this.pet  = new Cat("temppetname");
    }

    //GETTING ATTRIBUTES
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Pet getPet() { return this.pet; }

    //Inventory

    public UserInventory getUserInventory() { return this.userInventory; }

    /*public boolean hasFood(String key){ return userInventory.hasFood(key); }

    public void createFood(String key){ userInventory.createFood(key);}

    public void removeFood(String key){ userInventory.removeFood(key); }

    public void addFood(String key){ userInventory.addFood(key); }

    public int numofFood(String key) { return userInventory.numofFood(key); }

    public String showFood(){ return userInventory.showFood(); }*/


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
        return Integer.parseInt((dbh.getGameData(id, "MONEY")));
    }

    public int getXp() {
        return Integer.parseInt((dbh.getGameData(id, "EXPERIENCE")));
    }

    //CHANGING ATTRIBUTES
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWeight(String weight) {
        dbh.updateUserData(id, "WEIGHT", weight);
    }

    public void setHeight(String height) {
        dbh.updateUserData(id, "HEIGHT", height);

    }

    public void addExercisePlan(String planName, ArrayList<Exercise> exercises) {
        this.exercisePlans.put(planName, exercises);
    }

    public void addExercisetoPlan(String planName, Exercise exercise) {
        this.exercisePlans.get(planName).add(exercise);
    }

    public void addMoney(int amount) {
        dbh.updateGame(id, "MONEY", amount);
    }

    public void removeMoney(int amount){
        int balance = getMoneyAmount();
        dbh.updateGame(id, "MONEY", balance-amount);
    }

    public void increaseXp(int amount) {
        int xp = getXp();
        dbh.updateGame(id, "EXPERIENCE", xp+amount);
        this.pet.setLevel((int)Math.floor(xp/1000));
    }

    //OTHER
    public float getBMI() {
        return Float.parseFloat((dbh.getUserData(id, "BMI")));
    }

}