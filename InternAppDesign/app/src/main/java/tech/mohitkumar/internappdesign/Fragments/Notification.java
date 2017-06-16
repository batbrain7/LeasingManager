package tech.mohitkumar.internappdesign.Fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import tech.mohitkumar.internappdesign.Interface.CardAdapter;
import tech.mohitkumar.internappdesign.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Notification extends CardFragments {

    private CardView mCardView;
    private TextView h4ugiven,h4utaken;
    private RecyclerView recyclerView;

    public Notification() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_notification, container, false);
        mCardView = (CardView) view.findViewById(R.id.cardView);
        mCardView.setMaxCardElevation(mCardView.getCardElevation()
                * CardAdapter.MAX_ELEVATION_FACTOR);
        h4ugiven = (TextView) view.findViewById(R.id.h4u_given);
        h4utaken = (TextView)view.findViewById(R.id.h4u_recieved);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_notifications);
        animateTextViews(34,h4ugiven,h4utaken,43);
        return view;
    }

    public void animateTextViews(final int finalValue1, final TextView textview,
                                 final TextView textView1, final int finalValue2) {
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(0.8f);
        int start1 = Math.min(0, finalValue1);
        int end1 = Math.max(0, finalValue1);
        int difference1 = Math.abs(finalValue1 - 0);
        int start2 = Math.min(0, finalValue2);
        int end2 = Math.max(0, finalValue2);
        int difference2 = Math.abs(finalValue2 - 0);
        Handler handler = new Handler();
        final int end;
        if(end1>end2) {
            end = end1;
        } else {
            end = end2;
        }
        for (int count = 0; count <= end; count++) {
            int time;
            int time1 = Math.round(decelerateInterpolator.getInterpolation((((float) count) / difference1)) * 100) * count;
            int time2 = Math.round(decelerateInterpolator.getInterpolation((((float) count) / difference2)) * 100) * count;
            if(time1>time2) {
                time = time1;
            } else {
                time = time2;
            }

            final int finalCount1 = ((0 > finalValue1) ? 0 - count : count);
            final int finalCount2 = ((0 > finalValue2) ? 0 - count : count);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(finalCount1<=finalValue1)
                        textview.setText(finalCount1 + "");
                    if(finalCount2<=finalValue2)
                        textView1.setText(finalCount2 + "");
                }
            }, time);
        }
    }

    @Override
    public CardView getCardView() {
        return mCardView;
    }
}
