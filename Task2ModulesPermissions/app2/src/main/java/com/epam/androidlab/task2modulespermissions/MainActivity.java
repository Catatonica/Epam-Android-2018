package com.epam.androidlab.task2modulespermissions;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * This activity allows user to write and send email using available email client.
 * It's protected by custom dangerous permission.
 *
 * @author Elizabeth Gavina
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

        Button btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    /**
     * Collects necessary info from views and composes email.
     * Recipients' addresses split by ';'
     */
    public void sendEmail() {
        String recipients = etRecipients.getText().toString();
        String[] addresses = recipients.split(";");
        String subject = etSubject.getText().toString();
        String message = etMessage.getText().toString();
        composeEmail(addresses, subject, message);
    }

    /**
     * Starts email client with prefilled fields. User can choose what EC to choose.
     *
     * @param addresses recipients' addresses
     * @param subject   the topic of the message
     * @param message   text
     */
    public void composeEmail(final String[] addresses, final String subject, final String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO)
                .setData(Uri.parse("mailto:"))
                .putExtra(Intent.EXTRA_EMAIL, addresses)
                .putExtra(Intent.EXTRA_SUBJECT, subject)
                .putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
