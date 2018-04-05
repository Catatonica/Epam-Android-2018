package com.epam.androidlab.task7_appcomponents.fr_listener;

import android.support.v4.app.Fragment;

/**
 * Fragments use this interface in order to communication with each other
 * through the MainActivity(which implements this interface).
 */
public interface FragmentsListener {
    void replaceFragment(Fragment fragment);

    void destroyFragment(Fragment fragment);
}
