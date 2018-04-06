package com.epam.androidlab.task8_fragments.main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.epam.androidlab.task8_fragments.R;
import com.epam.androidlab.task8_fragments.fr_listener.OnDogClickListener;
import com.epam.androidlab.task8_fragments.fragments.dog_details.DogDetailsFragment;
import com.epam.androidlab.task8_fragments.fragments.dog_list.DogsListFragment;
import com.epam.androidlab.task8_fragments.model.DogsRepository;

/**
 * MainActivity contains two fragments: DogListFragment ( which represents a list of
 * dogs that can be adopted) and DogDetailsFragment (where you can find more information
 * about liked dog). Depending on configuration you phone is, they can be shown either
 * replacing each other(in Portrait conf.) or near each other(in Landscape conf.).
 * In order to control operations inside of fragments, MainActivity implements
 * interface OnDogClickListener.
 *
 * @author Elizabeth Gavina
 * @since 06.04.2018
 */
public class MainActivity extends AppCompatActivity implements OnDogClickListener {

    private static final String FR_DETAILS_TAG = "dogDetailsFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DogsRepository.setDogList();

        setDogsListFragment();

        final Fragment dogDetailsFragment =
                getSupportFragmentManager().findFragmentByTag(FR_DETAILS_TAG);

        if (dogDetailsFragment != null) {
            switch (getResources().getConfiguration().orientation) {
                case Configuration.ORIENTATION_LANDSCAPE:
                    getSupportFragmentManager().popBackStack();
                    break;
                default:
                    break;
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(dogDetailsFragment)
                    .runOnCommit(() -> openDogDetailsScreen(dogDetailsFragment))
                    .commit();
        }
    }

    /**
     * Once sets DogListFragment, containing list of dogs.
     */
    private void setDogsListFragment() {
        Fragment dogsListFragment = getSupportFragmentManager().findFragmentById(R.id.flDogsList);
        if (dogsListFragment == null) {
            Fragment fragment = new DogsListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flDogsList, fragment)
                    .commit();
        }
    }

    @Override
    public void onDogClick(final int dogID) {
        openDogDetailsScreen(DogDetailsFragment.newInstance(dogID));
    }

    /**
     * Switches what configuration of phone is and shows DogDetailsFragment
     * in appropriate way.
     *
     * @param fragment fragment,containing details about chosen dog
     */
    private void openDogDetailsScreen(@NonNull final Fragment fragment) {
        switch (getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flDogInfo, fragment, FR_DETAILS_TAG)
                        .commit();
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flDogsList, fragment, FR_DETAILS_TAG)
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
