package com.epam.androidlab.task8_fragments.model;


import android.support.annotation.NonNull;

import com.epam.androidlab.task8_fragments.R;

import java.util.ArrayList;

/**
 * It is a class-repository, containing information about all dogs.
 */
public class DogsRepository {
    private static ArrayList<Dog> dogList;

    private static final String[] names = {
            "Vord",
            "Chuck",
            "Micky",
            "Bobby",
            "Nicy",
            "Funny",
            "Slimy"
    };

    private static final int[] ages = {12, 2, 11, 4, 7, 6, 14};
    private static final int[] photoIDs = {
            R.drawable.puppy1,
            R.drawable.puppy2,
            R.drawable.puppy3,
            R.drawable.puppy4,
            R.drawable.puppy5,
            R.drawable.puppy6,
            R.drawable.puppy7
    };
    private static final String desc = "Needs a patient, experienced owner who will be firm with him" +
            " and take control but you’ll be rewarded by having a well-behaved loyal, loving companion.";

    /**
     * Sets a dog list.
     */
    public static void setDogList() {
        dogList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            dogList.add(new Dog(i, names[i], ages[i], desc, photoIDs[i]));
        }
    }

    /**
     * Returns dog list.
     *
     * @return list of dogs
     */
    @NonNull
    public static ArrayList<Dog> getDogList() {
        return dogList;
    }
}
