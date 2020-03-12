package com.example.nekogains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

import static com.example.nekogains.Exercise.*;

public class User implements Serializable {

    DatabaseHelper dbh;
    int id;

    private String username;
    private String password;

    private Exercise[] workoutPlan1 = {LUNGES, JACKS, PUSH_UPS, SIT_UPS, LEG_RAISES};
    private Exercise[] workoutPlan2 = {SQUATS, BURPEES, PLANKS, SIT_UPS, LEG_RAISES};
    private Exercise[] workoutPlan3 = {LUNGES, CHIN_UPS, PUSH_UPS, RUN, BENCH_DIPS};

    private int daily;
    private static Pet pet;
    private static UserInventory userInventory = new UserInventory();
    private Hashtable<String, ArrayList<Exercise>> workoutplans = new Hashtable<>();
    private ArrayList<String> workoutlist = new ArrayList<>();

    public User(DatabaseHelper dbh, int id) {
        this.dbh = dbh;
        this.id = id;
        this.username = "newUser";
        this.password = "1234";
        this.pet  = new Cat("temppetname");
    }

    public void createDefaultWorkouts(){
        ArrayList<Exercise> workoutPlan1 = new ArrayList<>();
        ArrayList<Exercise> workoutPlan2 = new ArrayList<>();
        ArrayList<Exercise> workoutPlan3 = new ArrayList<>();
        for (Exercise e: getWorkoutPlan1()){
            workoutPlan1.add(e);
        }
        for (Exercise e: getWorkoutPlan2()){
            workoutPlan2.add(e);
        }
        for (Exercise e: getWorkoutPlan3()){
            workoutPlan3.add(e);
        }
        addWorkouts("workoutPlan1", workoutPlan1);
        addtoWorkoutList("workoutPlan1");
        addWorkouts("workoutPlan2", workoutPlan2);
        addtoWorkoutList("workoutPlan2");
        addWorkouts("workoutPlan3", workoutPlan3);
        addtoWorkoutList("workoutPlan3");
    }

    public void addExercise(ArrayList<Exercise> exercises,Exercise exercise){
        exercises.add(exercise);
    }

    //Arthur Code

    public ArrayList<Exercise> getWorkouts(String name){return workoutplans.get(name); }

    public Hashtable<String, ArrayList<Exercise>> getWorkoutnames(){return workoutplans;}

    public void addWorkouts(String name, ArrayList<Exercise> exercises){workoutplans.put(name,exercises);}

    public void addtoWorkoutList(String name){
        workoutlist.add(name);
    }

    public ArrayList<String> getWorkoutlist(){ return workoutlist;}


    //GETTING ATTRIBUTES
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Pet getPet() { return this.pet; }

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