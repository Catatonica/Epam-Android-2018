package com.epam.androidlab.task2modulespermissions;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etRecipients;
    EditText etSubject;
    EditText etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etRecipients= findViewById(R.id.etRecipients);
        etSubject= findViewById(R.id.etSubject);
        etMessage= findViewById(R.id.etMessage);
    }

    public void onBtnSendClick(View view) {
        String recipients=etRecipients.getText().toString();
        String [] addresses=recipients.split(";");
        String subject=etSubject.getText().toString();
        String message=etMessage.getText().toString();
        composeEmail(addresses,subject,message);
    }

    public void composeEmail(String[] addresses, String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
