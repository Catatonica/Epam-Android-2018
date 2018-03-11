package com.epam.androidlab.task1_helloworld;

import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Gets your input string and checks if there's one in string resources.
 * If there is, then displays its content.
 */
public class MainActivity extends AppCompatActivity {

    private EditText mStringResource;
    private TextView mDisplayedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStringResource = (EditText) findViewById(R.id.et_string_resource_name);
        mDisplayedText = (TextView) findViewById(R.id.tv_displayed_text);
    }

    /**
     * Displays a string from resources if there is one
     * @param view button "Display"
     */
    public void onBtnDisplayClick(View view) {
        String resName = mStringResource.getText().toString();
        String stringFromResources = getStringResourceByName(resName);
        if (stringFromResources != null) {
            mDisplayedText.setText(stringFromResources);
        }
        else {
            mDisplayedText.setText(R.string.error_no_such_string_resource);
        }
    }

    /**
     * Searches for a string resource
     * @param resName possible name of a string resource
     * @return founded string, null otherwise
     */
    @CheckResult
    @Nullable
    private String getStringResourceByName(String resName) {
        int resId = getResources().getIdentifier(resName, "string", getPackageName());
        if (resId > 0) return getString(resId);
        return null;
    }
}
