package com.example.nekogains;

        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.fragment.app.Fragment;

public class EndWorkoutFrag extends Fragment {

    WorkoutActivity activity;
    View view;
    User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.endworkout_frag, container, false);
        Button button = view.findViewById(R.id.finishButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toMain();
            }
        });
        activity = ((WorkoutActivity)this.getActivity());
        user = activity.getUser();
        user.increaseXp(100);
        user.addMoney(100);

        return view;
    }

    private void toMain() {
        activity.finish();
    }
}
