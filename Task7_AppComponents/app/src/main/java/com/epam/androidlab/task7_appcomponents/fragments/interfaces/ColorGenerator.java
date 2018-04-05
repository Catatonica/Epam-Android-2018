package com.epam.androidlab.task7_appcomponents.fragments.interfaces;

/**
 * This interface is implemented in FirstFragment, SecondFragment and ThirdFragment
 * in order to generate random color of view.
 */
public interface ColorGenerator {
    int ALPHA = 200;
    int BOUNDS = 256;

    int generateColor();
}
