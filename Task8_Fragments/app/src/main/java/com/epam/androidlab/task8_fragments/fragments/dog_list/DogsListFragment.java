package com.epam.androidlab.task8_fragments.fragments.dog_list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epam.androidlab.task8_fragments.R;
import com.epam.androidlab.task8_fragments.fr_listener.OnDogClickListener;
import com.epam.androidlab.task8_fragments.model.Dog;
import com.epam.androidlab.task8_fragments.model.DogsRepository;

import java.util.ArrayList;

/**
 * This fragment is used to show a scrollable list of dogs( on each of it items
 * you can click to view more info).
 */
public class DogsListFragment extends Fragment {

    @Nullable
    private OnDogClickListener onDogClickListener;

    @Override
    public void onAttach(@NonNull final Context context) {
        super.onAttach(context);
        try {
            onDogClickListener = (OnDogClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnDogClickListener");
        }
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup parent,
                             @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_dogs, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        ArrayList<Dog> dogsList = DogsRepository.getDogList();
        final RecyclerView rvDogsList = view.findViewById(R.id.rvDogsList);
        rvDogsList.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        rvDogsList.setLayoutManager(manager);
        DogsListAdapter adapter = new DogsListAdapter(dogsList, onDogClickListener);
        rvDogsList.setAdapter(adapter);
    }
}
