package com.example.nekogains;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.nekogains.Exercise.*;

public class User implements Serializable {

    DatabaseHelper dbh;
    int id;

    private String name;

    private Exercise[] workoutPlan1 = {LUNGES, JACKS, PUSH_UPS, SIT_UPS, LEG_RAISES};
    private Exercise[] workoutPlan2 = {LUNGES, JACKS, PUSH_UPS, SIT_UPS, LEG_RAISES};
    private Exercise[] workoutPlan3 = {LUNGES, JACKS, PUSH_UPS, SIT_UPS, LEG_RAISES};

    private int daily;
    private static Pet pet;
    private static UserInventory userInventory = new UserInventory();
    //private Hashtable<String, ArrayList<Exercise>> exercisePlans = new Hashtable<>();

    public User(DatabaseHelper dbh, int id) {
        this.dbh = dbh;
        this.id = id;
        this.pet  = new Cat("temppetname");
    }

    //GETTING ATTRIBUTES
    public String getName() { return dbh.getUserData(id, "NAME"); }

    public Pet getPet() { return this.pet; }

    public float getWeight() {
        return Float.parseFloat((dbh.getUserData(id, "WEIGHT")));
    }

    public float getHeight() {
        return Float.parseFloat((dbh.getUserData(id, "HEIGHT")));
    }

    public int getMoneyAmount() {
        return Integer.parseInt((dbh.getGameData(id, "MONEY")));
    }

    public int getXp() {
        return Integer.parseInt((dbh.getGameData(id, "EXPERIENCE")));
    }

    public UserInventory getUserInventory() { return this.userInventory; }

    public int getAge() {
        return Integer.parseInt((dbh.getUserData(id, "AGE")));
    }

    public int getSex() {
        return Integer.parseInt((dbh.getUserData(id, "SEX")));
    }

    public int getHabits() {
        return Integer.parseInt((dbh.getUserData(id, "HABITS")));
    }

    public int getIntensity() {
        return Integer.parseInt((dbh.getUserData(id, "INTENSITY")));
    }

    public int getDaily() {return this.daily;}

    public Exercise[] getWorkoutPlan1() {
        return workoutPlan1;
    }

    public Exercise[] getWorkoutPlan2() {
        return workoutPlan2;
    }

    public Exercise[] getWorkoutPlan3() {
        return workoutPlan3;
    }

    public int getExerciseReps(Exercise exercise) {
        CalculateUserScore calc = new CalculateUserScore();
        float score = calc.calculate(this);
        return exercise.getUserReps(score);
    }

    //CHANGING ATTRIBUTES
    public void setName(String name) {
        dbh.updateUserData(id, "NAME", name);
    }

    public void setWeight(String weight) {
        dbh.updateUserData(id, "WEIGHT", weight);
    }

    public void setHeight(String height) {
        dbh.updateUserData(id, "HEIGHT", height);
    }

    public void setHabits(String habit) { dbh.updateUserData(id, "HABITS", habit);}

    public void setIntensity(String intensity) { dbh.updateUserData(id, "INTENSITY", intensity);}

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

    public void setDaily(int daily) {this.daily = daily;}

    //Calendar functions
    public int getLastDay() {
        return Integer.parseInt(dbh.getLastDay(id));
    }

    //Enters a new into workout calendar for current user
    public void newDay() {
        int lastday = this.getLastDay();
        dbh.insertNewDay(lastday+1, id);
    }

    //update workout or reps done for a given user on a given day
    public void updateDay(int day, String row, int contents) {
        dbh.updateDay(day, id, row, contents);
    }

    //get workout or reps done for a given user on a given day
    public String getUserCalendarData(int day, String row) {
        return dbh.getUserCalendarData(day, id, row);
    }


    //OTHER
    public float getBMI() {
        float height = this.getHeight()/100;
        return this.getWeight()/(height*height);
    }

    public boolean checkerDaily() {return this.daily == 5;}

    public void resetDaily() {this.daily = 0;}

    public void addDaily() {this.daily+=1;}

}