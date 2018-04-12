package com.epam.androidlab.task9_views;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * This activity uses DrawerLayout with NavigationView, containing two menu items,
 * on click on which fragments switch. Initially, FirstFragment is chosen.
 *
 * @author Elizabeth Gavina
 * @since 10.04.2018
 */
public class MainActivity extends AppCompatActivity {

    private static final String FRAGMENT_TAG = "secondFragment";
    private static final String IMAGE_URL =
            "https://developer.android.com/images/fundamentals/fragments.png?hl=ru";

    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialFragment();

        final Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, fragment, FRAGMENT_TAG)
                    .commit();
            setTitle(R.string.fragment2);
        }

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setToggle();
        loadImage();
        setOnItemSelectedListener();
    }

    /**
     * Sets FirstFragment (as initial) and title.
     */
    private void setInitialFragment() {
        Fragment initialFragment = getSupportFragmentManager().findFragmentById(R.id.fl_container);
        if (initialFragment == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, new FirstFragment())
                    .commit();
        }
        setTitle(R.string.fragment1);
    }

    /**
     * Sets toggle as drawer listener.
     */
    private void setToggle() {
        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close);
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * Loads image from network into NV's header.
     */
    private void loadImage() {
        View header = navigationView.getHeaderView(0);
        ImageView ivFragments = header.findViewById(R.id.iv_fragments);
        Glide.with(this)
             .load(IMAGE_URL)
             .into(ivFragments);
    }

    /**
     * Sets listener to menu items. Also sets title for toolbar and closes drawer after click on item.
     * SecondFragment replaces container with tag in order to be recreated after activity destroys.
     */
    private void setOnItemSelectedListener() {
        navigationView.setNavigationItemSelectedListener(item -> {
            item.setChecked(true);
            switch (item.getItemId()) {
                case R.id.item_fragment1:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fl_container, new FirstFragment())
                            .commit();
                    break;
                case R.id.item_fragment2:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fl_container, new SecondFragment(), FRAGMENT_TAG)
                            .commit();
                    break;
            }
            setTitle(item.getTitle());
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@Nullable Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }
}
