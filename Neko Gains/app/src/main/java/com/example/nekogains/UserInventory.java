package com.example.nekogains;

import java.util.Hashtable;

public class UserInventory {
    private Hashtable<String, Integer> food = new Hashtable<>();
    private  Hashtable<String, Boolean> shirts = new Hashtable<>();
    private  Hashtable<String, Boolean> pants = new Hashtable<>();
    private String shirt = "yellow"; //The shirt that is currently being worn
    private String pant = "orange"; //The pant that is currently being worn

    //For Food Items
    public boolean hasFood(String key){ return (food.containsKey(key) && food.get(key) > 0);}

    public Integer numofFood(String key){
        if (hasFood(key)) {
            return food.get(key);
        }
        return 0;
    }

    public void createFood(String key){ food.put(key, 1);}

    public void removeFood(String key){food.put(key, numofFood(key)-1);}

    public void addFood(String key){food.put(key, numofFood(key)+1);}

    public String showFood(){return food.toString();}


    //For Clothing Items
    public String returnShirt() {return shirt;}
    public String returnPants() {return pant;}

    public boolean hasShirt(String key){return shirts.containsKey(key);}

    public boolean hasPant(String key){return pants.containsKey(key);}

    public void setShirt(String key){ shirt = key;}

    public void setPant(String key){pant = key;}

    public void addShirt(String key){shirts.put(key, true); setShirt(key);}

    public void addPant(String key){pants.put(key, true); setPant(key);}

    public void changeShirt(String key){shirts.put(shirt, false); addShirt(key);}

    public void changePant(String key){shirts.put(pant, false); addPant(key);}

}
