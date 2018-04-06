package com.epam.androidlab.task8_fragments.fragments.dog_list;


import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epam.androidlab.task8_fragments.R;
import com.epam.androidlab.task8_fragments.fr_listener.OnDogClickListener;
import com.epam.androidlab.task8_fragments.model.Dog;

import java.util.List;

/**
 * It is an adapter for DogsListFragment. It uses OnDogClickListener's method onDogClick
 * when user taps on list item (it is represented here as static nested class DogsListVH).
 */
public class DogsListAdapter extends RecyclerView.Adapter<DogsListAdapter.DogsListVH> {

    @NonNull
    private final List<Dog> dogsList;
    @NonNull
    private OnDogClickListener listener;

    DogsListAdapter(@NonNull final List<Dog> dogsList, @NonNull final OnDogClickListener listener) {
        this.dogsList = dogsList;
        this.listener = listener;
    }

    static class DogsListVH extends RecyclerView.ViewHolder {
        CardView cvDog;
        TextView tvName;
        TextView tvYears;
        ImageView ivPhoto;

        DogsListVH(@NonNull final View itemView) {
            super(itemView);
            cvDog = itemView.findViewById(R.id.cvDog);
            tvName = itemView.findViewById(R.id.tvName);
            tvYears = itemView.findViewById(R.id.tvYears);
            ivPhoto = itemView.findViewById(R.id.ivPhoto);
        }
    }

    @Override
    public DogsListVH onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View dogView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new DogsListVH(dogView);
    }

    /**
     * Binds holder with information and sets a listener.
     *
     * @param holder   R.layout.list_item
     * @param position index of item in dogsList
     */
    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull final DogsListVH holder, final int position) {
        final Dog dog = dogsList.get(position);
        holder.tvName.setText(String.format("%s, ", dog.getName()));
        holder.tvYears.setText(String.format("%d years", dog.getAge()));
        holder.ivPhoto.setImageResource(dog.getPhotoID());
        holder.cvDog.setOnClickListener(view -> listener.onDogClick(dog.getID()));
    }

    @Override
    public int getItemCount() {
        return dogsList.size();
    }
}
