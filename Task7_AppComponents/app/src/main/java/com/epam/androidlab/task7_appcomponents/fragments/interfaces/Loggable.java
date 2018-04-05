package com.epam.androidlab.task7_appcomponents.fragments.interfaces;

/**
 * This interface is implemented in FirstFragment, SecondFragment and ThirdFragment
 * in order to show log, containing the state of the fragment.
 */
public interface Loggable {
    String TAG = "Fragments Lifecycle";

    enum State {
        onAttach,
        onCreate,
        onCreateView,
        onActivityCreated,
        onStart,
        onResume,
        onPause,
        onStop,
        onDestroyView,
        onDestroy,
        onDetach,
        onSaveInstanceState
    }

    void log(State state);
}
