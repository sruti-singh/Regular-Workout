package com.hfad.workout;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

public class DetailActivity extends Activity {
 public static final String EXTRA_WORKOUT_ID = "id";

 protected void onCreate (Bundle savedInstanceState){
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_detail);
     WorkoutDetailFragment workoutDetailFragment = (WorkoutDetailFragment) getFragmentManager().findFragmentById(R.id.detail_frag);
     int workoutId = (int) getIntent().getExtras().get(EXTRA_WORKOUT_ID);
     workoutDetailFragment.setWorkout(workoutId);
 }
}
