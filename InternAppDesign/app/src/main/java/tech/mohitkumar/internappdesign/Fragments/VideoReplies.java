package tech.mohitkumar.internappdesign.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tech.mohitkumar.internappdesign.Activities.FullScreenVideos;
import tech.mohitkumar.internappdesign.Adapters.RecyclerVideoAdapter;
import tech.mohitkumar.internappdesign.Interface.VideoFinished;
import tech.mohitkumar.internappdesign.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoReplies extends Fragment {
    RecyclerView recyclerView;
    RecyclerVideoAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<String> arraylist = new ArrayList<String>();


    public VideoReplies() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        arraylist.add("https://s3.ap-south-1.amazonaws.com/yoblunthlsvideo/test/yobluntvideo.m3u8");
        arraylist.add("https://s3.ap-south-1.amazonaws.com/yoblunthlsvideo/test/yobluntvideo2.m3u8");
        arraylist.add("https://s3.ap-south-1.amazonaws.com/yoblunthlsvideo/test/yobluntvideo3.m3u8");
        arraylist.add("https://s3.ap-south-1.amazonaws.com/yoblunthlsvideo/test/yobluntvideo4.m3u8");
        arraylist.add("https://s3.ap-south-1.amazonaws.com/yoblunthlsvideo/test/yobluntvideo5.m3u8");

        View view =  inflater.inflate(R.layout.fragment_video_replies, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view1);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerVideoAdapter(arraylist,getActivity());
        recyclerView.setAdapter(adapter);
//
//        adapter.setVideoFinished(new VideoFinished() {
//            @Override
//            public void onVideoFinished(int position) {
//                Log.d("TAG","GOT THE CALLBACk" + recyclerView.getChildCount());
//                recyclerView.scrollToPosition(position+1);
//            }
//
//            @Override
//            public void onInteraction(int position) {
//                //     pos = position;
//                //    Log.d("POSITION",Integer.toString(pos));
//            }
//        });

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
                    //    double perc2 = Math.abs(lastItemView.getY()) / lastItemView.getHeight();
                    //  Log.d("PERC2",Double.toString(perc2));
                    //  if(perc2 > 0.3) {

                    //    customExoPlayerView.controller.dispatchKeyEvent(KeyEvent.KEYCODE_MEDIA_PAUSE);
                    //  Log.d("P1Called","PAUSED");
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
        return view;
    }

}
