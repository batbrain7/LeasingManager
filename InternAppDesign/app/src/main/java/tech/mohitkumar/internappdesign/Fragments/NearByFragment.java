package tech.mohitkumar.internappdesign.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;
import tech.mohitkumar.internappdesign.Adapters.RecyclerViewAdapter;
import tech.mohitkumar.internappdesign.Models.CardViewData;
import tech.mohitkumar.internappdesign.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NearByFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class NearByFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    CardViewData cardViewData;
    ArrayList<CardViewData> arrayList = new ArrayList<CardViewData>();

    public NearByFragment() {
        // Required empty public constructor
    }

    String[] name = {"Frustrated about modi ji's demonetization","I'm an engineer from Amity Noida and i'm sick and tired of being down valued at placements.","I confess-\"Fought for my Partner- Lesbian partner","Izzat chaahata thaa…","Guilty Ashamed! Just wanted to pass the exam."};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_near_by, container, false);
        for(int i=0;i<5;i++) {
            if(i%2 == 0)
                cardViewData = new CardViewData("http://playertest.longtailvideo.com/adaptive/bbbfull/bbbfull.m3u8",name[i],"","","","","","","");
            else
                cardViewData = new CardViewData("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8",name[i],"","","","","","","");
            arrayList.add(cardViewData);
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(arrayList,getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
