package com.example.nekogains;

public abstract class Pet {
    DatabaseHelper dbh;

    private int userId;
    private String name;
    private int hunger; //HUNGER range[0,100]
    private int level; //LEVEL range[0,100] (for every 1000xp pet will level up by 1
    private String pants;
    private String shirt;
    private String motion;

    public Pet(DatabaseHelper dbh, String newName, int id) {
        this.userId = id;
        this.dbh = dbh;
        this.name = newName;
        this.hunger = 80;
        this.level = 30;
        this.pants = "none";
        this.shirt = "none";

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
    }

    //GETTING ATTRIBUTES
    public String getName() {
        return this.name;
    }

    public int getHunger() {
        return this.hunger;
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

    public void decreaseHunger(int value) {
        this.hunger += value;
        if (this.getHunger() > 100) {
            this.hunger = 100;
        }
        else if (this.getHunger() < 0) {
            this.hunger = 0;
        }
        //Update database
        this.dbh.updatePet(this.userId, "hunger", Integer.toString(this.hunger));
    }

    public void increaseHunger(int value) {
        this.hunger -= value;
        if (this.getHunger() < 0) {
            this.hunger = 0;
        }
        //Update database
        this.dbh.updatePet(this.userId, "hunger", Integer.toString(this.hunger));
    }

}
