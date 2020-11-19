package com.hfad.workout;

import android.app.FragmentTransaction;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WorkoutDetailFragment extends Fragment {
    private long workoutId;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            if(savedInstanceState != null){
                workoutId = savedInstanceState.getLong("workoutId");
            }
         else {
                androidx.fragment.app.FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                StopwatchFragment stopwatchFragment = new StopwatchFragment();
                ft.replace(R.id.stopwatch_container, stopwatchFragment);
                ft.addToBackStack(null);
                ft.setTransition(ft.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
                return inflater.inflate(R.layout.fragment_workout_detail, container, false);

    }
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {

            TextView title = (TextView) view.findViewById(R.id.textTitle);
            Workout workout = Workout.workouts[(int) workoutId];
            title.setText(workout.getName());
            TextView description = (TextView) view.findViewById(R.id.textDescription);
            description.setText(workout.getDescription());
        }
    }
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putLong("workoutId", workoutId);
    }
    public void setWorkout(long id){
        this.workoutId = id;
    }
}