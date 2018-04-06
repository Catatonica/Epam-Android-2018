package com.epam.androidlab.task8_fragments.fragments.dog_details;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epam.androidlab.task8_fragments.R;
import com.epam.androidlab.task8_fragments.model.Dog;
import com.epam.androidlab.task8_fragments.model.DogsRepository;

import java.util.ArrayList;

/**
 * Shows more detail description of a chosen dog.
 */
public class DogDetailsFragment extends Fragment {

    private static final String DOG_ID_KEY = "dogID";

    /**
     * Creates a new instance of DogDetailsFragment.
     *
     * @param dogID id of a dog
     * @return fragment with set arguments
     */
    @NonNull
    public static DogDetailsFragment newInstance(final int dogID) {
        DogDetailsFragment fragment = new DogDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(DOG_ID_KEY, dogID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup parent,
                             @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog_details, parent, false);
    }

    /**
     * Sets appropriate information about dog into view.
     *
     * @param view               R.layout.fragment_dog_details
     * @param savedInstanceState null
     */
    @SuppressLint("DefaultLocale")
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        int dogID = getArguments().getInt(DOG_ID_KEY);
        ArrayList<Dog> dogsList = DogsRepository.getDogList();
        TextView tvDogName = view.findViewById(R.id.tvDogName);
        TextView tvDogAge = view.findViewById(R.id.tvDogAge);
        TextView tvDogInfo = view.findViewById(R.id.tvDogInfo);
        ImageView ivDogPhoto = view.findViewById(R.id.ivDogPhoto);

        Dog dog = dogsList.get(dogID);
        tvDogName.setText(String.format("%s, ", dog.getName()));
        tvDogAge.setText(String.format("%d years", dog.getAge()));
        tvDogInfo.setText(dog.getDesc());
        ivDogPhoto.setImageResource(dog.getPhotoID());
    }

}
