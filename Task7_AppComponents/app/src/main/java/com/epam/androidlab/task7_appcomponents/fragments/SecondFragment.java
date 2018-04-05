package com.epam.androidlab.task7_appcomponents.fragments;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.epam.androidlab.task7_appcomponents.R;
import com.epam.androidlab.task7_appcomponents.fr_listener.FragmentsListener;
import com.epam.androidlab.task7_appcomponents.fragments.interfaces.ColorGenerator;
import com.epam.androidlab.task7_appcomponents.fragments.interfaces.Loggable;

import java.util.Random;

/**
 * This fragment contains button, on click on which ThirdFragment appears.
 */
public class SecondFragment extends Fragment implements View.OnClickListener, ColorGenerator, Loggable {

    private static final String FRAGMENT_NAME = " Second Fragment: ";
    private static final String COLOR_KEY = "color";
    private int color;
    private FragmentsListener replacer;

    @Override
    public void onAttach(@NonNull final Context context) {
        super.onAttach(context);
        log(State.onAttach);
        try {
            replacer = (FragmentsListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                                                 + " must implement FragmentsListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log(State.onCreate);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup parent,
                             @Nullable Bundle savedInstanceState) {
        log(State.onCreateView);
        return inflater.inflate(R.layout.fragment_second, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            color = savedInstanceState.getInt(COLOR_KEY);
            view.setBackgroundColor(color);
        } else {
            view.setBackgroundColor(generateColor());
        }
        Button btnGoToThirdFragment = view.findViewById(R.id.btnGoToThirdFragment);
        btnGoToThirdFragment.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        log(State.onActivityCreated);
    }

    @Override
    public void onStart() {
        super.onStart();
        log(State.onStart);
    }

    @Override
    public void onResume() {
        super.onResume();
        log(State.onResume);
    }

    @Override
    public void onPause() {
        super.onPause();
        log(State.onPause);
    }

    @Override
    public void onStop() {
        super.onStop();
        log(State.onStop);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        log(State.onDestroyView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        log(State.onDestroy);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        log(State.onDetach);
    }

    @Override
    public void onClick(@NonNull final View view) {
        replacer.replaceFragment(new ThirdFragment());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        log(State.onSaveInstanceState);
        outState.putInt(COLOR_KEY, color);
    }

    @Override
    public int generateColor() {
        Random rnd = new Random();
        color = Color.argb(ALPHA, rnd.nextInt(BOUNDS), rnd.nextInt(BOUNDS), rnd.nextInt(BOUNDS));
        return color;
    }

    @Override
    public void log(@NonNull final State state) {
        Log.d(TAG, FRAGMENT_NAME + state);
    }
}
