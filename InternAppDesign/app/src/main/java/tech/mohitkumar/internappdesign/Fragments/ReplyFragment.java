package tech.mohitkumar.internappdesign.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tech.mohitkumar.internappdesign.Interface.CardAdapter;
import tech.mohitkumar.internappdesign.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReplyFragment extends CardFragments {

    private CardView mCardView;

    public ReplyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reply, container, false);
        mCardView = (CardView) view.findViewById(R.id.cardView);
        mCardView.setMaxCardElevation(mCardView.getCardElevation()
                * CardAdapter.MAX_ELEVATION_FACTOR);
        return view;
    }

    @Override
    public CardView getCardView() {
        return mCardView;
    }
}
