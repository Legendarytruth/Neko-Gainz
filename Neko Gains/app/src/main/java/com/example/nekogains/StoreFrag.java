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
    private User user;
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

        MainActivity activity = (MainActivity) getActivity();
        user = activity.user;

        setMoney(user.userInventory.getMoneyAmount());
        //textView.setText("Money: "+ user.getMoneyAmount());
        return view;
    }

    public void setMoney(int money){
        textView.setText("Money: " + money);
    }

    public boolean checkMoney(int amount){
        return user.userInventory.getMoneyAmount() > amount;
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.button1:
                if (user.userInventory.getMoneyAmount() >= foodcost){
                    if (user.userInventory.hasFood("blueberries")){
                        user.userInventory.addFood("blueberries");
                    }else{
                        user.userInventory.createFood("blueberries");
                    }
                    user.userInventory.removeMoney(foodcost);
                    setMoney(user.userInventory.getMoneyAmount());
                    Toast.makeText(getContext(), user.userInventory.showFood(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Not Enough Money", Toast.LENGTH_SHORT).show();}
                break;
            case R.id.button2:
                if (user.userInventory.getMoneyAmount() >= foodcost){
                    if (user.userInventory.hasFood("catfood")){
                        user.userInventory.addFood("catfood");
                    }else{
                        user.userInventory.createFood("catfood");
                    }
                    user.userInventory.removeMoney(foodcost);
                    setMoney(user.userInventory.getMoneyAmount());
                    Toast.makeText(getContext(), user.userInventory.showFood(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Not Enough Money", Toast.LENGTH_SHORT).show();}
                break;
            case R.id.button3:
                if (user.userInventory.getMoneyAmount() >= foodcost){
                    if (user.userInventory.hasFood("fish")){
                        user.userInventory.addFood("fish");
                    }else{
                        user.userInventory.createFood("fish");
                    }
                    user.userInventory.removeMoney(foodcost);
                    setMoney(user.userInventory.getMoneyAmount());
                    Toast.makeText(getContext(), user.userInventory.showFood(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Not Enough Money", Toast.LENGTH_SHORT).show();}
                break;
            case R.id.button4:
                if (user.userInventory.getMoneyAmount() >= foodcost){
                    if (user.userInventory.hasFood("milk")){
                        user.userInventory.addFood("milk");
                    }else{
                        user.userInventory.createFood("milk");
                    }
                    user.userInventory.removeMoney(foodcost);
                    setMoney(user.userInventory.getMoneyAmount());
                    Toast.makeText(getContext(), user.userInventory.showFood(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Not Enough Money", Toast.LENGTH_SHORT).show();}
                break;

        }

    }


}
