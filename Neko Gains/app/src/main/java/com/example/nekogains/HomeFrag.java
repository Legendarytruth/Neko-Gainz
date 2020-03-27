package com.example.nekogains;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pl.droidsonroids.gif.GifImageView;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.view.Gravity;


public class HomeFrag extends Fragment implements View.OnClickListener {

    private TextView petNameTitle;

    private TextView catFoodAmount;
    private Button feedCatFood;

    private TextView blueberryAmount;
    private Button feedBlueberry;

    private TextView fishAmount;
    private Button feedFish;

    private TextView milkAmount;
    private Button feedMilk;

    private TextView blueShirtOwned;
    private Button putOnBlueShirt;
    private TextView yellowShirtOwned;
    private Button putOnYellowShirt;
    private TextView noShirtOwned;
    private Button putOnNoShirt;

    private TextView orangePantsOwned;
    private Button putOnOrangePants;
    private TextView redPantsOwned;
    private Button putOnRedPants;
    private TextView noPantsOwned;
    private Button putOnNoPants;


    private TextView moneyAmount;
    private ProgressBar hungerAmount;
    private ProgressBar xpAmount;
    private TextView levelAmount;

    private View view;
    private User user;

    private GifImageView catImage;
    private FloatingActionButton changeClothing;
    private PopupWindow clothingPopup;
    private Context mContext;

    private ProgressBar dailyAmount;
    private TextView dailyLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_frag, container, false);
        mContext = MainActivity.getContext();

        petNameTitle = view.findViewById(R.id.PetName);

        moneyAmount = view.findViewById(R.id.Money);
        hungerAmount = view.findViewById(R.id.Hunger);
        xpAmount = view.findViewById(R.id.Experience);
        levelAmount= view.findViewById(R.id.Level);

        catImage = view.findViewById(R.id.CatBody);
        changeClothing = view.findViewById(R.id.ClothingButton);
        dailyAmount = view.findViewById((R.id.dailyLogin));
        dailyLogin = view.findViewById((R.id.dailyProgress));

        MainActivity activity = (MainActivity) getActivity();
        user = new User(DatabaseHelper.getInstance(MainActivity.getContext()), ((MainActivity)this.getActivity()).getAppUserId());

        setPetName(user.getPet().getName());
        setCatGif(user.getUserInventory().returnShirt(), user.getUserInventory().returnPants());

        changeClothing.setOnClickListener(this);
        setMoneyAmount(user.getMoneyAmount());
        setHungerAmount(user.getPet().getHunger());
        setXpAmount(user.getXp() - (user.getPet().getLevel()*1000));
        setLevelAmount(user.getPet().getLevel());
        setDailyAmount(user.getDaily());
        setLoginAmount(user.getDaily());

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

    public void setCatGif(String shirt, String pants) {
        if (shirt == "yellow") {
            if (pants == "orange") {
                Toast.makeText(getContext(), user.getUserInventory().returnShirt(), Toast.LENGTH_SHORT).show();
                int imageResource = getResources().getIdentifier("@drawable/cat1_yellowshirt_orangepants_idle", null, getActivity().getPackageName());
                catImage.setImageResource(imageResource);
            } else if (pants == "red") {
                Toast.makeText(getContext(), "Entered setCatGif", Toast.LENGTH_SHORT).show();
                int imageResource = getResources().getIdentifier("@drawable/cat1_yellowshirt_redpants_idle", null, getActivity().getPackageName());
                catImage.setImageResource(imageResource);
            }
            else if (pants == "none") {
                Toast.makeText(getContext(), "Entered setCatGif", Toast.LENGTH_SHORT).show();
                int imageResource = getResources().getIdentifier("@drawable/cat1_yellowshirt_nopants_idle", null, getActivity().getPackageName());
                catImage.setImageResource(imageResource);
            }
        } else if (shirt == "blue") {
            if (pants == "orange") {
                Toast.makeText(getContext(), "Entered setCatGif", Toast.LENGTH_SHORT).show();
                int imageResource = getResources().getIdentifier("@drawable/cat1_blueshirt_orangepants_idle", null, getActivity().getPackageName());
                catImage.setImageResource(imageResource);
            } else if (pants == "red") {
                Toast.makeText(getContext(), "Entered setCatGif", Toast.LENGTH_SHORT).show();
                int imageResource = getResources().getIdentifier("@drawable/cat1_blueshirt_redpants_idle", null, getActivity().getPackageName());
                catImage.setImageResource(imageResource);
            }
            else if (pants == "none") {
                Toast.makeText(getContext(), "Entered setCatGif", Toast.LENGTH_SHORT).show();
                int imageResource = getResources().getIdentifier("@drawable/cat1_blueshirt_nopants_idle", null, getActivity().getPackageName());
                catImage.setImageResource(imageResource);
            }
        }
        else if (shirt == "none") {
            if (pants == "orange") {
                Toast.makeText(getContext(), "Entered setCatGif", Toast.LENGTH_SHORT).show();
                int imageResource = getResources().getIdentifier("@drawable/cat1_noshirt_orangepants_idle", null, getActivity().getPackageName());
                catImage.setImageResource(imageResource);
            } else if (pants == "red") {
                Toast.makeText(getContext(), "Entered setCatGif", Toast.LENGTH_SHORT).show();
                int imageResource = getResources().getIdentifier("@drawable/cat1_noshirt_redpants_idle", null, getActivity().getPackageName());
                catImage.setImageResource(imageResource);
            }
            else if (pants == "none") {
                Toast.makeText(getContext(), "no shirt", Toast.LENGTH_SHORT).show();
                int imageResource = getResources().getIdentifier("@drawable/cat1_noshirt_nopants_idle", null, getActivity().getPackageName());
                catImage.setImageResource(imageResource);
            }
        }
    }

    public void setDailyAmount(int amount) {dailyAmount.setProgress(amount);}
    public void setLoginAmount(int amount) {dailyLogin.setText("Bonus "+amount + "/5");}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ClothingButton:
                LayoutInflater inflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.clothing_popup, null);
                clothingPopup = new PopupWindow(popupView, 800, 1000);
                //clothingPopup = new PopupWindow(popupView, 225, 280);
                clothingPopup.showAtLocation(view.findViewById(R.id.CatScreen), Gravity.CENTER, 0, 0);
                Button closeButton = popupView.findViewById(R.id.CloseButton);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clothingPopup.dismiss();
                    }
                });
                catFoodAmount = popupView.findViewById(R.id.catFoodText);
                feedCatFood = popupView.findViewById(R.id.catFoodButton);

                blueberryAmount = popupView.findViewById(R.id.blueberryText);
                feedBlueberry = popupView.findViewById(R.id.blueberryButton);

                fishAmount = popupView.findViewById(R.id.fishText);
                feedFish = popupView.findViewById(R.id.fishButton);

                milkAmount = popupView.findViewById(R.id.milkText);
                feedMilk = popupView.findViewById(R.id.milkButton);

                blueShirtOwned = popupView.findViewById(R.id.blueShirtText);
                putOnBlueShirt = popupView.findViewById(R.id.blueShirtButton);
                yellowShirtOwned = popupView.findViewById(R.id.yellowShirtText);
                putOnYellowShirt = popupView.findViewById(R.id.yellowShirtButton);
                noShirtOwned = popupView.findViewById(R.id.noShirtText);
                putOnNoShirt = popupView.findViewById(R.id.noShirtButton);

                orangePantsOwned = popupView.findViewById(R.id.orangePantsText);
                putOnOrangePants = popupView.findViewById(R.id.orangePantsButton);
                redPantsOwned = popupView.findViewById(R.id.redPantsText);
                putOnRedPants = popupView.findViewById(R.id.redPantsButton);
                noPantsOwned = popupView.findViewById(R.id.noPantsText);
                putOnNoPants = popupView.findViewById(R.id.noPantsButton);


                feedCatFood.setOnClickListener(this);
                feedBlueberry.setOnClickListener(this);
                feedFish.setOnClickListener(this);
                feedMilk.setOnClickListener(this);
                putOnBlueShirt.setOnClickListener(this);
                putOnYellowShirt.setOnClickListener(this);
                putOnNoShirt.setOnClickListener(this);
                putOnOrangePants.setOnClickListener(this);
                putOnRedPants.setOnClickListener(this);
                putOnNoPants.setOnClickListener(this);

                setCatFoodAmount(user.getUserInventory().numofFood("catfood"));
                setBlueberryAmount(user.getUserInventory().numofFood("blueberries"));
                setFishAmount(user.getUserInventory().numofFood("fish"));
                setMilkAmount(user.getUserInventory().numofFood("milk"));
                setCatGif(user.getUserInventory().returnShirt(), user.getUserInventory().returnPants());

                break;
            case R.id.catFoodButton:
                if (user.getUserInventory().hasFood("catfood")) {
                    user.getPet().feed("catfood");
                    user.getUserInventory().removeFood("catfood");
                    setCatFoodAmount(user.getUserInventory().numofFood("catfood"));
                    setHungerAmount(user.getPet().getHunger());
                    Toast.makeText(getContext(), "-1 Cat Food", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Not Enough Cat Food", Toast.LENGTH_SHORT).show();
                }
                setCatGif(user.getUserInventory().returnShirt(), user.getUserInventory().returnPants());
                break;
            case R.id.blueberryButton:
                if (user.getUserInventory().hasFood("blueberries")) {
                    if (user.getPet().getHunger() < 100) {
                        user.getPet().feed("blueberries");
                        user.getUserInventory().removeFood("blueberries");
                        setBlueberryAmount(user.getUserInventory().numofFood("blueberries"));
                        setHungerAmount(user.getPet().getHunger());
                        Toast.makeText(getContext(), "-1 Blueberry", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Not Enough Blueberries", Toast.LENGTH_SHORT).show();
                }
                setCatGif(user.getUserInventory().returnShirt(), user.getUserInventory().returnPants());
                break;
            case R.id.fishButton:
                if (user.getUserInventory().hasFood("fish")) {
                    if (user.getPet().getHunger() < 100) {
                        user.getPet().feed("fish");
                        user.getUserInventory().removeFood("fish");
                        setFishAmount(user.getUserInventory().numofFood("fish"));
                        setHungerAmount(user.getPet().getHunger());
                        Toast.makeText(getContext(), "-1 Fish", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Not Enough Fish", Toast.LENGTH_SHORT).show();
                }
                setCatGif(user.getUserInventory().returnShirt(), user.getUserInventory().returnPants());
                break;
            case R.id.milkButton:
                if (user.getUserInventory().hasFood("milk")) {
                    if (user.getPet().getHunger() < 100) {
                        user.getPet().feed("milk");
                        user.getUserInventory().removeFood("milk");
                        setMilkAmount(user.getUserInventory().numofFood("milk"));
                        setHungerAmount(user.getPet().getHunger());
                        Toast.makeText(getContext(), "-1 Milk", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Not Enough Milk", Toast.LENGTH_SHORT).show();
                }
                setCatGif(user.getUserInventory().returnShirt(), user.getUserInventory().returnPants());
                break;
            case R.id.blueShirtButton:
                if (user.getUserInventory().hasShirt("blueshirt")) {
                    user.getUserInventory().setShirt("blue");
                    setCatGif(user.getUserInventory().returnShirt(), user.getUserInventory().returnPants());
                    Toast.makeText(getContext(), "blue shirt", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "NOT OWNED", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.yellowShirtButton:
                if (user.getUserInventory().hasShirt("yellowshirt")) {
                    user.getUserInventory().setShirt("yellow");
                    setCatGif(user.getUserInventory().returnShirt(), user.getUserInventory().returnPants());
                    Toast.makeText(getContext(), "yellow shirt", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "NOT OWNED", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.noShirtButton:
                user.getUserInventory().setShirt("none");
                setCatGif(user.getUserInventory().returnShirt(), user.getUserInventory().returnPants());
                Toast.makeText(getContext(), "shirtless", Toast.LENGTH_SHORT).show();
                break;
            case R.id.orangePantsButton:
                if (user.getUserInventory().hasPant("orangepants")) {
                    user.getUserInventory().setPant("orange");
                    setCatGif(user.getUserInventory().returnShirt(), user.getUserInventory().returnPants());
                    Toast.makeText(getContext(), "orange pants", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "NOT OWNED", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.redPantsButton:
                if (user.getUserInventory().hasPant("redpants")) {
                    user.getUserInventory().setPant("red");
                    setCatGif(user.getUserInventory().returnShirt(), user.getUserInventory().returnPants());
                    Toast.makeText(getContext(), "red pants", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "NOT OWNED", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.noPantsButton:
                user.getUserInventory().setPant("none");
                setCatGif(user.getUserInventory().returnShirt(), user.getUserInventory().returnPants());
                Toast.makeText(getContext(), "pantsless", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
