package com.example.nekogains;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ProgressFrag extends Fragment {

    private LineGraphSeries<DataPoint> series;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.progress_frag, container, false);
        GraphView graphView = view.findViewById(R.id.graph);
        double x,y;
        x = 0.0;

        series = new LineGraphSeries<>();
        int numDataPoints = 100;
        for (int i = 0; i <numDataPoints; i++){
            x = x + 5;
            y = x+ 2;
            series.appendData(new DataPoint(x,y), true, 100);
        }
        graphView.addSeries(series);
        return view;
    }
}
