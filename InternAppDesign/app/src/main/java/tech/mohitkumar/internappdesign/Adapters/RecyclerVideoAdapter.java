package tech.mohitkumar.internappdesign.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
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

import java.util.ArrayList;

import tech.mohitkumar.internappdesign.Interface.VideoFinished;
import tech.mohitkumar.internappdesign.CustomView.CustomExoPlayerView;
import tech.mohitkumar.internappdesign.CustomView.CustomPlaybackControlView;
import tech.mohitkumar.internappdesign.Interface.VideoFinished;
import tech.mohitkumar.internappdesign.R;

/**
 * Created by mohitkumar on 06/06/17.
 */

public class RecyclerVideoAdapter extends RecyclerView.Adapter<RecyclerVideoAdapter.RecyclerViewHolder> {

    ArrayList<String> list = new ArrayList<String>();
    Context context;

    public void setVideoFinished(VideoFinished videoFinished) {
        this.videoFinished = videoFinished;
    }

    private VideoFinished videoFinished;

    public RecyclerVideoAdapter(ArrayList<String> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view,parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view,list,context);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        final String[] s = {holder.list.get(position)};

        //videoFinished.onInteraction(position);



        Handler mainHandler;
        Timeline.Window window;
        DataSource.Factory mediaDataSourceFactory;
        DefaultTrackSelector trackSelector;
        final boolean shouldAutoPlay;
        int playerWindow;
        long playerPosition;
        BandwidthMeter bandwidthMeter;
        DefaultExtractorsFactory extractorsFactory;

        shouldAutoPlay = true;
        bandwidthMeter = new DefaultBandwidthMeter();
        mediaDataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, "mediaPlayerSample"), (TransferListener<? super DataSource>) bandwidthMeter);
        mainHandler = new Handler();
        window = new Timeline.Window();

        holder.customExoPlayerView.requestFocus();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        trackSelector = new DefaultTrackSelector(mainHandler, videoTrackSelectionFactory);
        holder.player = ExoPlayerFactory.newSimpleInstance(context, trackSelector, new DefaultLoadControl(), null, false);
        holder.customExoPlayerView.setPlayer(holder.player);
    //    holder.player.setPlayWhenReady(shouldAutoPlay);
        MediaSource mediaSource = new HlsMediaSource(Uri.parse(s[0]),mediaDataSourceFactory, mainHandler, null);
        holder.player.prepare(mediaSource);

        Log.d("INEND",Integer.toString(holder.getAdapterPosition()));

        holder.player.addListener(new ExoPlayer.EventListener() {
            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playbackState == ExoPlayer.STATE_ENDED) {
                    Log.d("ENTERED","Entered");
                    videoFinished.onVideoFinished(position);
                }
            }

            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity() {

            }
        });

        holder.itemView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {

                holder.customExoPlayerView.getMeasuredHeight();
                Log.d("HEIGHT",Integer.toString(holder.customExoPlayerView.getMeasuredHeight()));
                holder.player.setPlayWhenReady(shouldAutoPlay);
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                CustomExoPlayerView cust = (CustomExoPlayerView) v.findViewById(R.id.player_view);
                cust.controller.dispatchKeyEvent(KeyEvent.KEYCODE_MEDIA_PAUSE);
                Log.d("Updated the button","P");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        Context context;
        ArrayList<String> list = new ArrayList<String>();
        CustomExoPlayerView customExoPlayerView;
        SimpleExoPlayer player;
        CustomPlaybackControlView customPlaybackControlView;

        public RecyclerViewHolder(View itemView,ArrayList<String> list,Context context) {
            super(itemView);
            this.context = context;
            this.list = list;
            this.customExoPlayerView = (CustomExoPlayerView) itemView.findViewById(R.id.player_view);
        }
    }
}