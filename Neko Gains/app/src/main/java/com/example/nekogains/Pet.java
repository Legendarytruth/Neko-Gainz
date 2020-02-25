package com.example.nekogains;

public abstract class Pet {
    private String name;
    private int hunger; //HUNGER range[0,100]
    private int level; //LEVEL range[0,100] (for every 1000xp pet will level up by 1

    public Pet(String newName) {
        this.name = newName;
        this.hunger = 0;
        this.level = 0;
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

    //CHANGING ATTRIBUTES
    public void setName(String newName) {
        this.name = newName;
    }

    public void setLevel(int newLevel) {
        if (newLevel <= 100) {
            this.level = newLevel;
        }
    }

}
