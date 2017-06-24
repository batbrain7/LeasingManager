package tech.mohitkumar.internappdesign.Fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;
import tech.mohitkumar.internappdesign.Adapters.MyVideoAdapter;
import tech.mohitkumar.internappdesign.Adapters.RecyclerNotificationAdapter;
import tech.mohitkumar.internappdesign.Interface.CardAdapter;
import tech.mohitkumar.internappdesign.Models.NotificationItem;
import tech.mohitkumar.internappdesign.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyVideos extends CardFragments {

    private CardView mCardView;
    RecyclerView recyclerView;
    MyVideoAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<NotificationItem> arrayList = new ArrayList<NotificationItem>();
    String[] text = {"Someone hearted your video","Someone replied to your video","You just recieved a new IML"};
    int[] images = {R.drawable.heart1,R.drawable.repl,R.drawable.down};

    public MyVideos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_videos, container, false);
        mCardView = (CardView) view.findViewById(R.id.cardView);
        mCardView.setMaxCardElevation(mCardView.getCardElevation()
                * CardAdapter.MAX_ELEVATION_FACTOR);
        NotificationItem item;
        int i=0;
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"Fonts/OpenSans-Regular.ttf");
        TextView textView = (TextView) view.findViewById(R.id.my_videos);
        textView.setTypeface(tf);
        arrayList.add(new NotificationItem(text[0],R.drawable.vid_thumb));
        arrayList.add(new NotificationItem(text[1],R.drawable.vid_thumb));
        arrayList.add(new NotificationItem(text[2],R.drawable.vid_thumb));
        recyclerView = (RecyclerView) view.findViewById(R.id.my_videos_recycler);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new MyVideoAdapter(arrayList,getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public CardView getCardView() {
        return mCardView;
    }
}
