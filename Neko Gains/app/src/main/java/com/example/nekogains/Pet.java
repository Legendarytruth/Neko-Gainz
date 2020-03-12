package com.example.nekogains;

public abstract class Pet {
    private String name;
    private int hunger; //HUNGER range[0,100]
    private int level; //LEVEL range[0,100] (for every 1000xp pet will level up by 1
    private String pants;
    private String shirt;
    private String motion;

    public Pet(String newName) {
        this.name = newName;
        this.hunger = 80;
        this.level = 30;
        this.pants = "orange";
        this.shirt = "yellow";
        this.motion = "walking";
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
    }
    public void setShirt(String newShirt) {
        this.name = newShirt;
    }
    public void setPants(String newPants) {
        this.name = newPants;
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
    }

}
