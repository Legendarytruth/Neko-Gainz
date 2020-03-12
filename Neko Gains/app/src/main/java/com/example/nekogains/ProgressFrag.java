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
    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();
        user = new User(DatabaseHelper.getInstance(MainActivity.getContext()), ((MainActivity)this.getActivity()).getUserId());
        /*int last = user.getLastDay();
        String exerciseType = user.getUserCalendarData(last, "WARMUP");
        int data = Integer.parseInt(user.getUserCalendarData(last, exerciseType));*/
        view = inflater.inflate(R.layout.progress_frag, container, false);
        //GraphView graphView = view.findViewById(R.id.graph);
        double x,y;
            series = new LineGraphSeries<>();
        int numDataPoints = 14;
        //x = last;
        //y = data;
        x = 1;
        y = 1;
        series.appendData(new DataPoint(x,y), true, 14);

        //graphView.addSeries(series);
        return view;
    }
}
