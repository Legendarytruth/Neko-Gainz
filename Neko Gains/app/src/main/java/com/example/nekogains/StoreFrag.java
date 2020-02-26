package com.example.nekogains;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StoreFrag extends Fragment implements View.OnClickListener {
    private User user = new User();
    private UserInventory userInventory = new UserInventory();
    private View view;
    private TextView textView;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Integer foodcost = 10;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.store_frag, container, false);
        textView = view.findViewById(R.id.money);
        button1 = view.findViewById(R.id.button1);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);
        button4 = view.findViewById(R.id.button4);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        setMoney(user.getMoneyAmount());
        //textView.setText("Money: "+ user.getMoneyAmount());
        return view;
    }

    public void setMoney(int money){
        textView.setText("Money: " + money);
    }

    public boolean checkMoney(int amount){
        return user.getMoneyAmount() > amount;
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.button1:
                if (user.getMoneyAmount() >= foodcost){
                    if (userInventory.hasFood("blueberries")){
                        userInventory.addFood("blueberries");
                    }else{
                        userInventory.createFood("blueberries");
                    }
                    user.removeMoney(foodcost);
                    setMoney(user.getMoneyAmount());
                    Toast.makeText(getContext(), userInventory.showFood(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Not Enough Money", Toast.LENGTH_SHORT).show();}
                break;
            case R.id.button2:
                if (user.getMoneyAmount() >= foodcost){
                    if (userInventory.hasFood("catfood")){
                        userInventory.addFood("catfood");
                    }else{
                        userInventory.createFood("catfood");
                    }
                    user.removeMoney(foodcost);
                    setMoney(user.getMoneyAmount());
                    Toast.makeText(getContext(), userInventory.showFood(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Not Enough Money", Toast.LENGTH_SHORT).show();}
                break;
            case R.id.button3:
                if (user.getMoneyAmount() >= foodcost){
                    if (userInventory.hasFood("fish")){
                        userInventory.addFood("fish");
                    }else{
                        userInventory.createFood("fish");
                    }
                    user.removeMoney(foodcost);
                    setMoney(user.getMoneyAmount());
                    Toast.makeText(getContext(), userInventory.showFood(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Not Enough Money", Toast.LENGTH_SHORT).show();}
                break;
            case R.id.button4:
                if (user.getMoneyAmount() >= foodcost){
                    if (userInventory.hasFood("milk")){
                        userInventory.addFood("milk");
                    }else{
                        userInventory.createFood("milk");
                    }
                    user.removeMoney(foodcost);
                    setMoney(user.getMoneyAmount());
                    Toast.makeText(getContext(), userInventory.showFood(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Not Enough Money", Toast.LENGTH_SHORT).show();}
                break;

        }

    }


}
