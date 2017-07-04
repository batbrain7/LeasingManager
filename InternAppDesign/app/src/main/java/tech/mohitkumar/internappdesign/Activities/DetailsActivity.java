package tech.mohitkumar.internappdesign.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;

import tech.mohitkumar.internappdesign.Adapters.ViewPagerAdapter;
import tech.mohitkumar.internappdesign.CustomView.CustomExoPlayerView;
import tech.mohitkumar.internappdesign.Fragments.*;
import tech.mohitkumar.internappdesign.Fragments.TextReplies;
import tech.mohitkumar.internappdesign.MainActivity;
import tech.mohitkumar.internappdesign.R;

public class DetailsActivity extends AppCompatActivity {

    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    CustomExoPlayerView exoPlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String s = getIntent().getStringExtra("link");
        final String a = getIntent().getStringExtra("name");

        final TextView tag1 = (TextView) findViewById(R.id.tags);
        final TextView iml = (TextView) findViewById(R.id.h4u);
        final ImageView heart = (ImageView) findViewById(R.id.lik_btn);

        TextView textView = (TextView) findViewById(R.id.name_vid);
        textView.setText(a);

        tag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, GroupClickActivity.class);
                intent.putExtra("Name",tag1.getText().toString());
                startActivity(intent);
            }
        });

        final boolean[] ai = {true};
        iml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ai[0] == true) {
                    iml.setBackground(getResources().getDrawable(R.drawable.circular_black_button));
                    ai[0] = false;
                } else if(ai[0] == false) {
                    iml.setBackgroundResource(0);
                    ai[0] = true;
                }
            }
        });
        final Animation anm = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.transalte_scale);

        final boolean[] b = {true};
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(b[0]) {

                    heart.startAnimation(anm);;
                    heart.startAnimation(anm);
                    heart.setImageResource(R.drawable.heart3);
                    b[0] = false;
                } else if(!b[0]) {
                    heart.setImageResource(R.drawable.hearts);
                    b[0] = true;
                }
            }
        });

        Timeline.Window window;
        DataSource.Factory mediaDataSourceFactory;
        DefaultTrackSelector trackSelector;
        final boolean shouldAutoPlay;
        int playerWindow;
        long playerPosition;
        BandwidthMeter bandwidthMeter;
        DefaultExtractorsFactory extractorsFactory;
        Handler mainHandler;
        mainHandler = new Handler();
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        exoPlayerView = (CustomExoPlayerView) collapsingToolbarLayout.findViewById(R.id.player_view_card);
        bandwidthMeter = new DefaultBandwidthMeter();
        mediaDataSourceFactory = new DefaultDataSourceFactory(DetailsActivity.this, Util.getUserAgent(DetailsActivity.this, "mediaPlayerSample"), (TransferListener<? super DataSource>) bandwidthMeter);
        window = new Timeline.Window();
        exoPlayerView.requestFocus();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        trackSelector = new DefaultTrackSelector(mainHandler, videoTrackSelectionFactory);
        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(DetailsActivity.this, trackSelector, new DefaultLoadControl(), null, false);
        exoPlayerView.setPlayer(player);
        //    holder.player.setPlayWhenReady(shouldAutoPlay);
        MediaSource mediaSource = new HlsMediaSource(Uri.parse(s),mediaDataSourceFactory, mainHandler, null);
        player.prepare(mediaSource);
        //Toolbar toolbar = (Toolbar) collapsingToolbarLayout.findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.AddFragments(new VideoReplies(),"Video");
        viewPagerAdapter.AddFragments(new TextReplies(),"Text");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
