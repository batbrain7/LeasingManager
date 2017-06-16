package tech.mohitkumar.internappdesign.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import java.util.ArrayList;

import tech.mohitkumar.internappdesign.Adapters.RecyclerVideoAdapter;
import tech.mohitkumar.internappdesign.Interface.VideoFinished;
import tech.mohitkumar.internappdesign.R;

public class FullScreenVideos extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerVideoAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<String> arraylist = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_videos);

        arraylist.add("http://playertest.longtailvideo.com/adaptive/bbbfull/bbbfull.m3u8");
        arraylist.add("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8");
        arraylist.add("https://d2zihajmogu5jn.cloudfront.net/bipbop-advanced/bipbop_16x9_variant.m3u8");
        arraylist.add("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8");
        arraylist.add("http://playertest.longtailvideo.com/adaptive/bbbfull/bbbfull.m3u8");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
        layoutManager = new LinearLayoutManager(FullScreenVideos.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerVideoAdapter(arraylist,getApplicationContext());
        recyclerView.setAdapter(adapter);

        adapter.setVideoFinished(new VideoFinished() {
            @Override
            public void onVideoFinished(int position) {
                Log.d("TAG","GOT THE CALLBACk" + recyclerView.getChildCount());
                recyclerView.scrollToPosition(position+1);
            }

            @Override
            public void onInteraction(int position) {
           //     pos = position;
            //    Log.d("POSITION",Integer.toString(pos));
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public boolean top;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


                LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView
                        .getLayoutManager());

                int firstItemPos=layoutManager.findFirstVisibleItemPosition();
                View firstItemView=layoutManager.findViewByPosition(firstItemPos);
                int lastItemPos=layoutManager.findLastVisibleItemPosition();
                View lastItemView=layoutManager.findViewByPosition(lastItemPos);
              //  CustomExoPlayerView customExoPlayerView = (CustomExoPlayerView) lastItemView.findViewById(R.id.player_view);
              //  CustomExoPlayerView customExoPlayerView1 = (CustomExoPlayerView) firstItemView.findViewById(R.id.player_view);

                if (top) {
                    double perc2 = Math.abs(lastItemView.getY()) / lastItemView.getHeight();
                    Log.d("PERC2",Double.toString(perc2));
                  //  if(perc2 > 0.3) {

                    //    customExoPlayerView.controller.dispatchKeyEvent(KeyEvent.KEYCODE_MEDIA_PAUSE);
                        Log.d("P1Called","PAUSED");
                        int index = layoutManager.findFirstVisibleItemPosition();
                        recyclerView.smoothScrollToPosition(index);
                    //    customExoPlayerView1.controller.dispatchKeyEvent(KeyEvent.KEYCODE_MEDIA_PLAY);
                        Log.d("P1Called","PLAYING");
                   // }

                } else {
                   // double perc1 = Math.abs(firstItemView.getY()) / firstItemView.getHeight();
                   //    Log.d("PERC1",Double.toString(perc1));
                  //  if(perc1 > 0.3) {
                     //   customExoPlayerView1.controller.dispatchKeyEvent(KeyEvent.KEYCODE_MEDIA_PAUSE);
                        Log.d("P2Called","PAUSED");
                        int index = layoutManager.findLastVisibleItemPosition();
                        recyclerView.smoothScrollToPosition(index);
                     //   customExoPlayerView.controller.dispatchKeyEvent(KeyEvent.KEYCODE_MEDIA_PLAY);
                        Log.d("P2Called","PLAYING");
                   // }
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //  Log.d("DY",Integer.toString(dy));
                if (dy > 0) {
                    top = false;
                } else {
                    top = true;
                }

            }
        });


    }
}
