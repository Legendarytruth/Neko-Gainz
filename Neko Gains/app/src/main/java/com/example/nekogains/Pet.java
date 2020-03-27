package com.example.nekogains;

import android.content.SharedPreferences;

import java.util.Calendar;

public abstract class Pet {
    DatabaseHelper dbh;

    //private final int SATIATION_HOURS = 12;
    private final int SATIATION_HOURS = 1; //For testing purposes
    private int userId;
    private String name;
    private int hunger; //HUNGER range[0,100]
    private int level; //LEVEL range[0,100] (for every 1000xp pet will level up by 1
    private String pants;
    private String shirt;
    private long lastFed;
    private String motion;

    public Pet(DatabaseHelper dbh, String newName, int id) {
        this.userId = id;
        this.dbh = dbh;
        this.name = newName;
        this.hunger = 80;
        this.level = 30;
        this.pants = "none";
        this.shirt = "none";
        this.lastFed = -1; //Timestamp of when the pet was fed

        if(!dbh.insertNewPet(id, newName)) {
            //Inserting new pet failed
            System.out.println("WARNING: Inserting new pet failed, try using the other constructor");
        }
    }

    public Pet(DatabaseHelper dbh, int id) {
        /*
        * In this constructor assume Pet table has already been
        * established in the database.
         */
        this.dbh = dbh;
        this.userId = id;
        this.name = dbh.getPetData(id, "name");
        this.hunger = Integer.parseInt(dbh.getPetData(id, "hunger"));
        this.level = Integer.parseInt(dbh.getPetData(id, "level"));
        this.pants = dbh.getPetData(id, "pants");
        this.shirt = dbh.getPetData(id, "shirt");
        //Used to calculate hunger
        this.lastFed = MainActivity.getSettings().getLong("lastFedTime", -1);
        System.out.println("Last fed: "+this.lastFed);
    }

    //GETTING ATTRIBUTES
    public String getName() {
        return this.name;
    }

    public int getHunger() {
        /*
        * Returns the hunger that was reduced from time passing since the pet was last fed
         */
        Calendar cal = Calendar.getInstance();
        long currTime =  cal.getTimeInMillis();
        int decreaseAmount = 0;


        if(this.lastFed != -1) {
            //Number of times hunger should go down by
            decreaseAmount = (int) Math.floor((currTime - this.lastFed) / (3600000 * SATIATION_HOURS));
            //Pet reduces hunger by 5 every 12 (SATIATION_HOURS) hours

        } else {
            //Settings file has been deleted, put the new time in
            SharedPreferences.Editor editor = MainActivity.getSettings().edit();
            editor.putLong("lastFedTime", currTime);
            editor.apply();
        }
        return this.hunger - 5 * decreaseAmount;
    }

    public int getLevel() {
        return this.level;
    }

    public String getPants() {return this.pants;}
    public String getShirt() {return this.shirt;}
    public String getMotion() {return this.motion;}

    //CHANGING ATTRIBUTES
    public void setName(String newName) {
        this.name = newName;
        this.dbh.updatePet(this.userId, "name", newName);
    }
    public void setShirt(String newShirt) {
        this.name = newShirt;
        this.dbh.updatePet(this.userId, "shirt", newShirt);
    }
    public void setPants(String newPants) {
        this.name = newPants;
        this.dbh.updatePet(this.userId, "pants", newPants);
    }
    public void setMotion(String newMotion) {
        this.name = newMotion;
    }

    public void setLevel(int newLevel) {
        if (newLevel <= 100) {
            this.level = newLevel;
        }
    }

    public void decreaseHunger(int refillAmount) {
        /*
        * Uses a mix of stored hunger when the pet was fed and the reduced hunger after time
        * passed to calculate the current hunger, then replenishes it by the refillAmount.
         */
        this.hunger = getHunger() + refillAmount;
        if (this.getHunger() > 100) {
            this.hunger = 100;
        }
        else if (this.getHunger() < 0) {
            this.hunger = 0;
        }
        //Update database
        this.dbh.updatePet(this.userId, "hunger", Integer.toString(this.hunger));
    }


    public void feed(String food) {
        /*
        * Feeds the pet with food to increase their hunger bar
         */
        decreaseHunger(UserInventory.getFoodFill(food));

        //Update the time in the settings file to be the current time
        SharedPreferences.Editor editor = MainActivity.getSettings().edit();
        editor.putLong("lastFedTime", Calendar.getInstance().getTimeInMillis());
        editor.apply();
    }

}
