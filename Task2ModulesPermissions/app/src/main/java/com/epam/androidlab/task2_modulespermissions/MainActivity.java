package com.epam.androidlab.task2_modulespermissions;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * This activity tries to launch activity from another module.
 * That module is protected by custom dangerous permission, so this activity
 * implements necessary requests and reacts appropriately.
 */
public class MainActivity extends AppCompatActivity implements OnRequestPermissionsResultCallback {

    private static final int PERMISSION_REQUEST_ACTIVITY = 0;
    private static final String PERMISSION_NAME
            = "com.epam.androidlab.task2_modulespermissions.permission.DEADLY_ACTIVITY";
    private View mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout =  findViewById(R.id.main_layout);
    }

    /**
     * Show activity from the 2nd app when button is clicked
     * @param view button "Start the 2nd app's activity"
     */
    public void onBtnStartClick(View view) {
        showActivityFromApp2();
    }

    /**
     * Check whether permission is granted and then start preview.
     * Otherwise, request permission.
     */
    private void showActivityFromApp2() {
        if (ActivityCompat.checkSelfPermission(this, PERMISSION_NAME)
                == PackageManager.PERMISSION_GRANTED) {
            startSecondActivity();
        } else {
            requestActivityPermission();
        }
    }

    /**
     * Permission is not granted.
     * Check whether we should show an explanation or not and request permission.
     * The result will be received in onRequestPermissionResult().
     */
    private void requestActivityPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, PERMISSION_NAME)) {
            Snackbar.make(mLayout, R.string.access_required,
                    Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{PERMISSION_NAME},
                                    PERMISSION_REQUEST_ACTIVITY);
                    }
            }).show();
        } else {
            Snackbar.make(mLayout, R.string.activity_permission_not_available, Snackbar.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{PERMISSION_NAME}, PERMISSION_REQUEST_ACTIVITY);
        }
    }


    /**
     * Check the result of the permission. If it has been granted, start activity, otherwise –
     * show message.
     * @param requestCode PERMISSION_REQUEST_ACTIVITY
     * @param permissions PERMISSION_NAME
     * @param grantResults PERMISSION_GRANTED / PERMISSION_DENIED
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_ACTIVITY) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Snackbar.make(mLayout, R.string.activity_permission_granted,
                        Snackbar.LENGTH_SHORT)
                        .show();
                startSecondActivity();
            } else {
                Snackbar.make(mLayout, R.string.activity_permission_denied,
                        Snackbar.LENGTH_SHORT)
                        .show();
            }
        }
    }

    /**
     * Start activity from the 2nd app.
     */
    private void startSecondActivity() {
        Intent app2Intent = new Intent();
        app2Intent
                .setAction("com.epam.androidlab.task2modulespermissions.SECOND_APP_ACTIVITY")
                .addCategory("android.intent.category.DEFAULT");
        startActivity(app2Intent);
    }
}
