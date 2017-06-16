package tech.mohitkumar.internappdesign.Activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tech.mohitkumar.internappdesign.CustomView.CustomExoPlayerView;
import tech.mohitkumar.internappdesign.R;

public class Secondary extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        CustomExoPlayerView customExoPlayerView;
        customExoPlayerView = (CustomExoPlayerView) findViewById(R.id.custom_player);



    }
}
