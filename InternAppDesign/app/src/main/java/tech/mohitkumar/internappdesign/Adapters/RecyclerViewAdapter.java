package tech.mohitkumar.internappdesign.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

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

import tech.mohitkumar.internappdesign.Activities.CommentsActivity;
import tech.mohitkumar.internappdesign.Activities.DetailsActivity;
import tech.mohitkumar.internappdesign.Activities.FullScreenVideos;
import tech.mohitkumar.internappdesign.Activities.GroupClickActivity;
import tech.mohitkumar.internappdesign.Activities.TextReplies;
import tech.mohitkumar.internappdesign.Activities.UploadActivity;
import tech.mohitkumar.internappdesign.CustomView.CustomExoPlayerView;
import tech.mohitkumar.internappdesign.Interface.VideoFinished;
import tech.mohitkumar.internappdesign.Models.CardViewData;
import tech.mohitkumar.internappdesign.R;

/**
 * Created by mohitkumar on 14/06/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    Context context;
    ArrayList<CardViewData> arrayList = new ArrayList<CardViewData>();

    private VideoFinished videoFinished;

    public void setVideoFinished(VideoFinished videoFinished) {
        this.videoFinished = videoFinished;
    }

    public RecyclerViewAdapter(ArrayList<CardViewData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_card_layout,parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view,arrayList,context);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        final CardViewData cardViewData = arrayList.get(position);
        Handler mainHandler;
        Timeline.Window window;
        DataSource.Factory mediaDataSourceFactory;
        DefaultTrackSelector trackSelector;
        final boolean shouldAutoPlay;
        int playerWindow;
        long playerPosition;
        BandwidthMeter bandwidthMeter;
        DefaultExtractorsFactory extractorsFactory;

        Typeface tf = Typeface.createFromAsset(context.getAssets(),"Fonts/OpenSans-Regular.ttf");
        Typeface tff = Typeface.createFromAsset(context.getAssets(),"Fonts/SourceSansPro-Regular.ttf");

        holder.name.setTypeface(tf);
        holder.time.setTypeface(tf);
        holder.views.setTypeface(tf);
        holder.tag1.setTypeface(tf);
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
        MediaSource mediaSource = new HlsMediaSource(Uri.parse(cardViewData.getLinks()),mediaDataSourceFactory, mainHandler, null);
        holder.player.prepare(mediaSource);
        holder.iml.setTypeface(tff);
        holder.tag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GroupClickActivity.class);
                intent.putExtra("Name",holder.tag1.getText().toString());
                context.startActivity(intent);
            }
        });

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

        holder.name.setText(cardViewData.getName());

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("link",cardViewData.getLinks());
                intent.putExtra("name",cardViewData.getName());
                context.startActivity(intent);
            }
        });

        final Animation anm = AnimationUtils.loadAnimation(context,R.anim.transalte_scale);
        final Animation anmback = AnimationUtils.loadAnimation(context,R.anim.translate_back);

        final boolean[] a = {true};

        holder.iml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a[0] == true) {
                    holder.iml.setBackground(context.getResources().getDrawable(R.drawable.circular_black_button));
                    a[0] = false;
                } else if(a[0] == false) {
                    holder.iml.setBackgroundResource(0);
                    a[0] = true;
                }
            }
        });
//        logtext.startAnimation(anm);
        final boolean[] b = {true};
        holder.heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(b[0]) {

                    holder.heart.startAnimation(anm);;
                    holder.heart.startAnimation(anm);
                    holder.heart.setImageResource(R.drawable.heart3);
                    b[0] = false;
                } else if(!b[0]) {
                    holder.heart.setImageResource(R.drawable.hearts);
                    b[0] = true;
                }
            }
        });

        holder.reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoFinished.onInteraction(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView heart,reply,share,down;
        TextView name,tag1,iml,time,views,comments,h4u,likes;
        CustomExoPlayerView customExoPlayerView;
        SimpleExoPlayer player;
        CardView cardView;

        ArrayList<CardViewData> arrayList;
        Context context;

        public RecyclerViewHolder(View itemView,ArrayList<CardViewData> arrayList,Context context) {
            super(itemView);
            this.context = context;
            this.arrayList = arrayList;
            cardView = (CardView) itemView.findViewById(R.id.card_view_card);
            heart = (ImageView) itemView.findViewById(R.id.lik_btn);
            reply = (ImageView) itemView.findViewById(R.id.reply);
            share = (ImageView) itemView.findViewById(R.id.share_btn);
            down = (ImageView) itemView.findViewById(R.id.down_btn);
            name = (TextView) itemView.findViewById(R.id.name_vid);
            tag1 = (TextView) itemView.findViewById(R.id.tags);
            iml = (TextView) itemView.findViewById(R.id.h4u);
            time = (TextView) itemView.findViewById(R.id.time_elapsed);
            views = (TextView) itemView.findViewById(R.id.no_views);
            comments = (TextView) itemView.findViewById(R.id.no_reply);
            h4u = (TextView) itemView.findViewById(R.id.no_h4u);
            likes = (TextView) itemView.findViewById(R.id.no_likes);
            customExoPlayerView = (CustomExoPlayerView) itemView.findViewById(R.id.player_view_card);
        }
    }
}
