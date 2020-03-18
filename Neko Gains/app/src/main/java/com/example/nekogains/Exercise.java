package com.example.nekogains;

import android.util.Pair;

public enum Exercise {
    LUNGES (0.9f, 3, new Pair(9, 15)),
    SQUATS (0.3f, 3, new Pair(10, 20)),
    RUN (15f, 1, new Pair(30, 60)),
    BURPEES (13f, 3, new Pair(3, 10)),
    JACKS (9f, 3, new Pair(5, 10)),
    PUSH_UPS (1, 3, new Pair(10, 20)),
    CHIN_UPS (1, 3, new Pair(5, 15)),
    BENCH_DIPS (3, 3, new Pair(10, 20)),
    SIT_UPS (0.3f, 3, new Pair(10, 25)),
    PLANKS (3.5f, 3, new Pair(2, 5)),
    LEG_RAISES (0.7f, 3, new Pair(10, 20))
    ;

    private final float calories;
    private final int sets;
    private final Pair<Integer, Integer> reps;

    Exercise(float calories, int sets, Pair<Integer, Integer> reps) {
        this.calories = calories;
        this.sets = sets;
        this.reps = reps;
    }

    public String getName() {
        return this.name();
    }

    public float getCalories() {
        return calories;
    }

    public int getSets() {
        return sets;
    }

    public Pair<Integer, Integer> getReps() {
        return reps;
    }

    public int getUserReps(float score) {
        int min = reps.first;
        int max = reps.second;
        return Math.round(score*(max - min) + min);
    }


}
