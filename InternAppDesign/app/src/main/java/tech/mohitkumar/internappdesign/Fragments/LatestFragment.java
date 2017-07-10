package tech.mohitkumar.internappdesign.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;
import tech.mohitkumar.internappdesign.Activities.TextReplies;
import tech.mohitkumar.internappdesign.Activities.UploadActivity;
import tech.mohitkumar.internappdesign.Adapters.RecyclerViewAdapter;
import tech.mohitkumar.internappdesign.Interface.VideoFinished;
import tech.mohitkumar.internappdesign.MainActivity;
import tech.mohitkumar.internappdesign.Models.CardViewData;
import tech.mohitkumar.internappdesign.Models.HorizontalItems;
import tech.mohitkumar.internappdesign.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */

public class LatestFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    CardViewData cardViewData;
    ArrayList<Object> arrayList = new ArrayList<Object>();

    public LatestFragment() {
        // Required empty public constructor
    }

    String[] name = {"Frustrated about modi ji's demonetization","I'm an engineer from Amity Noida and i'm sick and tired of being down valued at placements.","I confess-\"Fought for my Partner- Lesbian partner","Izzat chaahata thaa…","Guilty Ashamed! Just wanted to pass the exam."};
    String[] title = {"Video1","Video2","Video3","Video4","Video5"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_latest, container, false);

        for(int i=0;i<5;i++) {
            if(i%2 == 0)
                cardViewData = new CardViewData("http://playertest.longtailvideo.com/adaptive/bbbfull/bbbfull.m3u8",name[i],"","","","","","","");
            else
                cardViewData = new CardViewData("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8",name[i],"","","","","","","");
            arrayList.add(cardViewData);
            HorizontalItems horizontalItems = new HorizontalItems(title[i],R.drawable.vid_thumb);
            arrayList.add(horizontalItems);
        }

        Calligrapher calligrapher = new Calligrapher(getActivity());
        calligrapher.setFont(getActivity(),"Fonts/OpenSans-Regular.ttf",true);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(arrayList,getActivity());
        recyclerView.setAdapter(adapter);

        adapter.setVideoFinished(new VideoFinished() {
            @Override
            public void onVideoFinished(int position) {

            }

            @Override
            public void onInteraction(int position) {

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
// ...Irrelevant code for customizing the buttons and title
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.alert_dialog, null);
                dialogBuilder.setView(dialogView);
                TextView pub,pri,text;
                text = (TextView) dialogView.findViewById(R.id.text_reply);
                pub = (TextView) dialogView.findViewById(R.id.private_comment);
                pri = (TextView) dialogView.findViewById(R.id.private_comment);

                text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),TextReplies.class);
                        startActivity(intent);
                    }
                });

                pub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),UploadActivity.class);
                        startActivity(intent);
                    }
                });

                pri.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),UploadActivity.class);
                        startActivity(intent);
                    }
                });

                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();

            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

}
