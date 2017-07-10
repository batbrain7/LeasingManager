package tech.mohitkumar.internappdesign.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import tech.mohitkumar.internappdesign.R;

public class SettingsProfile extends AppCompatActivity {

    TextView notifications,termsofuse,feedback,privacy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_profile);

        notifications = (TextView) findViewById(R.id.notify);
        termsofuse = (TextView) findViewById(R.id.terms_of_use);
        feedback = (TextView) findViewById(R.id.feedback);
        privacy = (TextView) findViewById(R.id.privacy_policy);


        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        termsofuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsProfile.this,TermsofUser.class));
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsProfile.this,FeedBackActivity.class));
            }
        });

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsProfile.this,PrivacyPolicy.class));
            }
        });
    }
}
