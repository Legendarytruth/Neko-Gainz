package com.example.nekogains;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TutorialFrag extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutorial_frag, container, false);
        Button button = view.findViewById(R.id.finishButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toTimer();
            }
        });
        return view;
    }

    public void toTimer() {
        ((WorkoutActivity) getActivity()).replaceFragments(new TimerFrag());
    }

}
