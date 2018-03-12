package com.epam.androidlab.task2modulespermissions;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * This activity allows user to write and send email using available email client.
 * It's protected by custom dangerous permission.
 */
public class MainActivity extends AppCompatActivity {

    private EditText etRecipients;
    private EditText etSubject;
    private EditText etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etRecipients = findViewById(R.id.etRecipients);
        etSubject = findViewById(R.id.etSubject);
        etMessage = findViewById(R.id.etMessage);
    }

    /**
     * Collects necessary info from views and composes email.
     * Recipients' addresses split by ';'
     * @param view button "Send"
     */
    public void onBtnSendClick(View view) {
        String recipients = etRecipients.getText().toString();
        String [] addresses = recipients.split(";");
        String subject = etSubject.getText().toString();
        String message = etMessage.getText().toString();
        composeEmail(addresses, subject, message);
    }

    /**
     * Starts email client with prefilled fields. User can choose what EC to choose.
     * @param addresses recipients' addresses
     * @param subject the topic of the message
     * @param message text
     */
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
