package com.epam.androidlab.task3_git;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This activity presents EditText, Button and TextView.
 * When you click on the button, text from the ET will be gathered,
 * reformed and set to the TV.
 *
 * @author Elizabeth Gavina
 * @since 15.03.2018
 */
public class MainActivity extends AppCompatActivity {

    private EditText etField;
    private TextView tvNewField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        etField = findViewById(R.id.etField);
        tvNewField = findViewById(R.id.tvNewField);
        Button btnSetField = findViewById(R.id.btnSetField);

        btnSetField.setOnClickListener(new View.OnClickListener() {
            /**
             * Gets field's name from EditText, reforms it and
             * sets new value to the TextView.
             * @param view button "Set"
             */
            @Override
            public void onClick(View view) {
                Git git = new Git();
                git.setField(etField.getText().toString());
                tvNewField.setText(git.getField());
            }
        });
    }
}
