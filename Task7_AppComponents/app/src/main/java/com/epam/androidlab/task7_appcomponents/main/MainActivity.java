package com.epam.androidlab.task7_appcomponents.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.epam.androidlab.task7_appcomponents.R;
import com.epam.androidlab.task7_appcomponents.fr_listener.FragmentsListener;
import com.epam.androidlab.task7_appcomponents.fragments.FirstFragment;

/**
 * This activity contains FrameLayout that will be replaced by
 * three fragments on their buttons "Go to the n activity" click.
 * Fragment-to-Fragment communication is done through this activity
 * using FragmentsListener interface.
 *
 * @author Elizabeth Gavina
 * @since 30.03.2018
 */
public class MainActivity extends AppCompatActivity implements FragmentsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            return;
        }
        addFirstFragment();
    }

    private void addFirstFragment() {
        FirstFragment firstFragment = new FirstFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.flContainer, firstFragment)
                .commit();
    }

    /**
     * Replaces whatever is in the flContainer view with this fragment,
     * and adds the transaction to the back stack so the user can navigate back.
     *
     * @param newFragment replacing fragment
     */
    @Override
    public void replaceFragment(Fragment newFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContainer, newFragment)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Removes fragment from activity.
     *
     * @param removingFragment fragment we want to remove
     */
    @Override
    public void destroyFragment(Fragment removingFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .remove(removingFragment)
                .commit();
    }
}
