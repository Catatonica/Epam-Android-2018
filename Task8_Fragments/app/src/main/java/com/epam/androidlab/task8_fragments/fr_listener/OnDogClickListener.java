package com.epam.androidlab.task8_fragments.fr_listener;

/**
 * This interface in implemented in MainActivity in order to control
 * events, happening in DogListFragment.
 */
public interface OnDogClickListener {
    void onDogClick(final int dogID);
}
