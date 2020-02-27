package com.example.nekogains;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFrag extends Fragment implements View.OnClickListener {

    private TextView petNameTitle;

    private TextView catFoodAmount;
    private ImageButton feedCatFood;

    private TextView blueberryAmount;
    private ImageButton feedBlueberry;

    private TextView fishAmount;
    private ImageButton feedFish;

    private TextView milkAmount;
    private ImageButton feedMilk;

    private TextView moneyAmount;
    private ProgressBar hungerAmount;
    private ProgressBar xpAmount;
    private TextView levelAmount;

    private View view;
    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_frag, container, false);
        petNameTitle = view.findViewById(R.id.PetName);

        catFoodAmount = view.findViewById(R.id.CatFood);
        feedCatFood = view.findViewById(R.id.CatFoodButton);

        blueberryAmount = view.findViewById(R.id.Blueberry);
        feedBlueberry = view.findViewById(R.id.BlueberryButton);

        fishAmount = view.findViewById(R.id.Fish);
        feedFish = view.findViewById(R.id.FishButton);

        milkAmount = view.findViewById(R.id.Milk);
        feedMilk = view.findViewById(R.id.MilkButton);

        moneyAmount = view.findViewById(R.id.Money);
        hungerAmount = view.findViewById(R.id.Hunger);
        xpAmount = view.findViewById(R.id.Experience);
        levelAmount= view.findViewById(R.id.Level);

        feedCatFood.setOnClickListener(this);
        feedBlueberry.setOnClickListener(this);
        feedFish.setOnClickListener(this);
        feedMilk.setOnClickListener(this);


        MainActivity activity = (MainActivity) getActivity();
        user = activity.user;

        setPetName(user.pet.getName());
        setCatFoodAmount(user.userInventory.numofFood("catfood"));
        setBlueberryAmount(user.userInventory.numofFood("blueberries"));
        setFishAmount(user.userInventory.numofFood("fish"));
        setMilkAmount(user.userInventory.numofFood("milk"));
        setMoneyAmount(user.userInventory.getMoneyAmount());
        setHungerAmount(user.pet.getHunger());
        setXpAmount(user.getXp() - (user.pet.getLevel()*1000));
        setLevelAmount(user.pet.getLevel());

        return view;

    }

    public void setPetName(String name){
        petNameTitle.setText(name);
    }
    public void setCatFoodAmount(int amount){
        catFoodAmount.setText("CatFood: " + amount);
    }
    public void setBlueberryAmount(int amount) {
        blueberryAmount.setText("Blueberry: " + amount);
    }
    public void setFishAmount(int amount) {
        fishAmount.setText("Fish: " + amount);
    }
    public void setMilkAmount(int amount) {
        milkAmount.setText("Milk: " + amount);
    }
    public void setMoneyAmount(int amount){
        moneyAmount.setText("$" + amount);
    }
    public void setHungerAmount(int amount) {
        hungerAmount.setProgress(amount);
    }
    public void setXpAmount(int amount) {
        xpAmount.setProgress(amount);
    }
    public void setLevelAmount(int amount) {
        levelAmount.setText("Lvl " + amount);
    }

    @Override
    public void onClick(View v) {
        if (user.pet.getHunger() < 100) {
            switch (v.getId()) {
            case R.id.CatFoodButton:
                if (user.userInventory.hasFood("catfood")) {
                    user.pet.decreaseHunger(10);
                    user.userInventory.removeFood("catfood");
                    setCatFoodAmount(user.userInventory.numofFood("catfood"));
                    setHungerAmount(user.pet.getHunger());
                    Toast.makeText(getContext(), "-1 Cat Food", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Not Enough Cat Food", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.BlueberryButton:
                if (user.userInventory.hasFood("blueberries")) {
                    if (user.pet.getHunger() < 100) {
                        user.pet.decreaseHunger(10);
                        user.userInventory.removeFood("blueberries");
                        setBlueberryAmount(user.userInventory.numofFood("blueberries"));
                        setHungerAmount(user.pet.getHunger());
                        Toast.makeText(getContext(), "-1 Blueberry", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Not Enough Blueberry", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.FishButton:
                if (user.userInventory.hasFood("fish")) {
                    if (user.pet.getHunger() < 100) {
                        user.pet.decreaseHunger(10);
                        user.userInventory.removeFood("fish");
                        setFishAmount(user.userInventory.numofFood("fish"));
                        setHungerAmount(user.pet.getHunger());
                        Toast.makeText(getContext(), "-1 Fish", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Not Enough Fish", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.MilkButton:
                if (user.userInventory.hasFood("milk")) {
                    if (user.pet.getHunger() < 100) {
                        user.pet.decreaseHunger(10);
                        user.userInventory.removeFood("milk");
                        setMilkAmount(user.userInventory.numofFood("milk"));
                        setHungerAmount(user.pet.getHunger());
                        Toast.makeText(getContext(), "-1 Milk", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Not Enough Milk", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
        else {
            Toast.makeText(getContext(), "Pet is full", Toast.LENGTH_SHORT).show();
        }
    }
}
