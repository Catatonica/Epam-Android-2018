package com.epam.androidlab.task8_fragments.model;

import android.support.annotation.NonNull;

/**
 * It is a model-class of a dog.
 */
public class Dog {

    private int id;
    @NonNull
    private String name;
    private int age;
    private int photoID;
    @NonNull
    private String desc;

    Dog(final int id, @NonNull final String name, final int age, @NonNull final String desc, int photoID) {
        this.name = name;
        this.age = age;
        this.photoID = photoID;
        this.id = id;
        this.desc = desc;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getPhotoID() {
        return photoID;
    }

    public int getID() {
        return id;
    }

    @NonNull
    public String getDesc() {
        return desc;
    }
}
