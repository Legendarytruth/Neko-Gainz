package com.example.nekogains;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

public class ProgressFrag extends Fragment implements View.OnClickListener {

    private LineGraphSeries<DataPoint> series;
    private ImageButton lungesButton;
    private ImageButton squatsButton;
    private ImageButton runButton;

    private ImageButton burpeesButton;
    private ImageButton jacksButton;
    private ImageButton pushUpsButton;

    private ImageButton chinUpsButton;
    private ImageButton benchDipsButton;
    private ImageButton sitUpsButton;

    private ImageButton planksButton;
    private ImageButton legRaisesButton;
    private ImageButton weightLossButton;

    private View view;
    private User user;
    private PopupWindow lungesPopup;
    private PopupWindow squatsPopup;
    private PopupWindow runPopup;

    private PopupWindow burpeesPopup;
    private PopupWindow jacksPopup;
    private PopupWindow pushUpsPopup;

    private PopupWindow chinUpsPopup;
    private PopupWindow benchDipsPopUp;
    private PopupWindow sitUpsPopup;

    private PopupWindow planksPopup;
    private PopupWindow legRaisesPopup;
    private PopupWindow weightLossPopup;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();
        
        view = inflater.inflate(R.layout.progress_frag, container, false);
        lungesButton = view.findViewById(R.id.lungesButton);
        squatsButton = view.findViewById(R.id.squatsButton);
        runButton = view.findViewById(R.id.runButton);

        burpeesButton = view.findViewById(R.id.burpeesButton);
        jacksButton = view.findViewById(R.id.jacksButton);
        pushUpsButton = view.findViewById(R.id.pushUpsButton);

        chinUpsButton = view.findViewById(R.id.chinUpsButton);
        benchDipsButton = view.findViewById(R.id.benchDipsButton);
        sitUpsButton = view.findViewById(R.id.sitUpsButton);

        planksButton = view.findViewById(R.id.planksButton);
        legRaisesButton = view.findViewById(R.id.legRaisesButton);
        weightLossButton = view.findViewById(R.id.weightLossButton);

        lungesButton.setOnClickListener(this);
        squatsButton.setOnClickListener(this);
        runButton.setOnClickListener(this);

        burpeesButton.setOnClickListener(this);
        jacksButton.setOnClickListener(this);
        pushUpsButton.setOnClickListener(this);

        chinUpsButton.setOnClickListener(this);
        benchDipsButton.setOnClickListener(this);
        sitUpsButton.setOnClickListener(this);

        planksButton.setOnClickListener(this);
        legRaisesButton.setOnClickListener(this);
        weightLossButton.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        LayoutInflater inflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        GraphView squatsGV = v.findViewById(R.id.squatsGraph);
        GraphView runGV = v.findViewById(R.id.runGraph);

        GraphView burpeesGV = v.findViewById(R.id.burpeesGraph);
        GraphView jacksGV = v.findViewById(R.id.jacksGraph);
        GraphView pushGV = v.findViewById(R.id.pushUpsGraph);

        GraphView chinGV = v.findViewById(R.id.chinUpsGraph);
        GraphView benchGV = v.findViewById(R.id.benchDipsGraph);
        GraphView sitGV = v.findViewById(R.id.sitUpsGraph);

        GraphView planksGV = v.findViewById(R.id.planksGraph);
        GraphView legGV = v.findViewById(R.id.legRaisesGraph);
        GraphView weightGV = v.findViewById(R.id.weightLossGraph);
        View popupView;
        Button closeButton;

        switch (v.getId()) {
            case R.id.lungesButton:
                popupView = inflater.inflate(R.layout.lunges_popup, null);
                GraphView lungeGV = popupView.findViewById(R.id.lungesGraph);
                double x, y;
                LineGraphSeries<DataPointInterface> s = new LineGraphSeries<>();
                int numDataPoints = 140;
                int num = (int) Math.random();
                for (int i = 0; i < numDataPoints; i ++) {
                    x = num;
                    y = num*x + x;
                    s.appendData(new DataPoint(x, y), true, 140);
                }
                lungeGV.addSeries(s);
                lungesPopup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                lungesPopup.showAtLocation(view.findViewById(R.id.ProgressScreen), Gravity.CENTER, 0, 0);
                closeButton = popupView.findViewById(R.id.button9);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lungesPopup.dismiss();
                    }
                });
                break;
            case R.id.squatsButton:
                popupView = inflater.inflate(R.layout.squats_popup, null);
                squatsPopup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                squatsPopup.showAtLocation(view.findViewById(R.id.ProgressScreen), Gravity.CENTER, 0, 0);
                closeButton = popupView.findViewById(R.id.button9);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        squatsPopup.dismiss();
                    }
                });
                break;
            case R.id.runButton:
                popupView = inflater.inflate(R.layout.run_popup, null);
                runPopup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                runPopup.showAtLocation(view.findViewById(R.id.ProgressScreen), Gravity.CENTER, 0, 0);
                closeButton = popupView.findViewById(R.id.button9);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        runPopup.dismiss();
                    }
                });
                break;
            case R.id.burpeesButton:
                popupView = inflater.inflate(R.layout.burpees_popup, null);
                burpeesPopup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                burpeesPopup.showAtLocation(view.findViewById(R.id.ProgressScreen), Gravity.CENTER, 0, 0);
                closeButton = popupView.findViewById(R.id.button9);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        burpeesPopup.dismiss();
                    }
                });
                break;
            case R.id.jacksButton:
                popupView = inflater.inflate(R.layout.jacks_popup, null);
                jacksPopup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                jacksPopup.showAtLocation(view.findViewById(R.id.ProgressScreen), Gravity.CENTER, 0, 0);
                closeButton = popupView.findViewById(R.id.button9);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jacksPopup.dismiss();
                    }
                });
                break;
            case R.id.pushUpsButton:
                popupView = inflater.inflate(R.layout.pushups_popup, null);
                pushUpsPopup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                pushUpsPopup.showAtLocation(view.findViewById(R.id.ProgressScreen), Gravity.CENTER, 0, 0);
                closeButton = popupView.findViewById(R.id.button9);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pushUpsPopup.dismiss();
                    }
                });
                break;
            case R.id.chinUpsButton:
            popupView = inflater.inflate(R.layout.chinups_popup, null);
            chinUpsPopup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            chinUpsPopup.showAtLocation(view.findViewById(R.id.ProgressScreen), Gravity.CENTER, 0, 0);
            closeButton = popupView.findViewById(R.id.button9);
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chinUpsPopup.dismiss();
                }
            });
            break;
            case R.id.benchDipsButton:
                popupView = inflater.inflate(R.layout.benchdips_popup, null);
                benchDipsPopUp = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                benchDipsPopUp.showAtLocation(view.findViewById(R.id.ProgressScreen), Gravity.CENTER, 0, 0);
                closeButton = popupView.findViewById(R.id.button9);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        benchDipsPopUp.dismiss();
                    }
                });
                break;
            case R.id.sitUpsButton:
                popupView = inflater.inflate(R.layout.situps_popup, null);
                sitUpsPopup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                sitUpsPopup.showAtLocation(view.findViewById(R.id.ProgressScreen), Gravity.CENTER, 0, 0);
                closeButton = popupView.findViewById(R.id.button9);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sitUpsPopup.dismiss();
                    }
                });
                break;
            case R.id.planksButton:
                popupView = inflater.inflate(R.layout.planks_popup, null);
                planksPopup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                planksPopup.showAtLocation(view.findViewById(R.id.ProgressScreen), Gravity.CENTER, 0, 0);
                closeButton = popupView.findViewById(R.id.button9);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        planksPopup.dismiss();
                    }
                });
                break;
            case R.id.legRaisesButton:
                popupView = inflater.inflate(R.layout.legraises_popup, null);
                legRaisesPopup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                legRaisesPopup.showAtLocation(view.findViewById(R.id.ProgressScreen), Gravity.CENTER, 0, 0);
                closeButton = popupView.findViewById(R.id.button9);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        legRaisesPopup.dismiss();
                    }
                });
                break;
            case R.id.weightLossButton:
                popupView = inflater.inflate(R.layout.weightloss_popup, null);
                weightLossPopup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                weightLossPopup.showAtLocation(view.findViewById(R.id.ProgressScreen), Gravity.CENTER, 0, 0);
                closeButton = popupView.findViewById(R.id.button9);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        weightLossPopup.dismiss();
                    }
                });
                break;
        }
    }
}

