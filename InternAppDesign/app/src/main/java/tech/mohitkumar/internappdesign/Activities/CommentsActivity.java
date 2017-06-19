package tech.mohitkumar.internappdesign.Activities;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

import tech.mohitkumar.internappdesign.R;

public class CommentsActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        TabHost tabHost = getTabHost();

        // Tab for Videos
        TabHost.TabSpec photospec = tabHost.newTabSpec("Video");
        // setting Title and Icon for the Tab
        photospec.setIndicator("Videos", getResources().getDrawable(R.drawable.ic_videocam_black_24dp));
        Intent photosIntent = new Intent(this, FullScreenVideos.class);
        photospec.setContent(photosIntent);

        // Tab for Songs
        TabHost.TabSpec songspec = tabHost.newTabSpec("Text");
        songspec.setIndicator("Text", getResources().getDrawable(R.drawable.ic_comment_black_24dp));
        Intent songsIntent = new Intent(this, TextReplies.class);
        songspec.setContent(songsIntent);

        // Tab for Videos

        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding photos tab
        tabHost.addTab(songspec); // Adding songs tab

        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
        {
            Typeface tf = Typeface.createFromAsset(getAssets(),"Fonts/OpenSans-Regular.ttf");
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#ffffff"));
            tv.setTypeface(tf);
        }
    }
}
