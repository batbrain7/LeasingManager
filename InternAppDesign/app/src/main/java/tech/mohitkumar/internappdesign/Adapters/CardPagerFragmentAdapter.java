package tech.mohitkumar.internappdesign.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tech.mohitkumar.internappdesign.Interface.CardAdapter;
import tech.mohitkumar.internappdesign.Fragments.CardFragments;

/**
 * Created by mohitkumar on 15/06/17.
 */

public class CardPagerFragmentAdapter extends FragmentStatePagerAdapter implements CardAdapter {

    private List<CardFragments> fragment;
    private float baseElevation;

    public CardPagerFragmentAdapter(FragmentManager fm,float baseElevation) {
        super(fm);
        fragment = new ArrayList<>();
        this.baseElevation = baseElevation;
    }

    @Override
    public float getPageWidth(int position) {
        return 1f;
    }

    @Override
    public Fragment getItem(int position) {
        return fragment.get(position);
    }

    @Override
    public float getBaseElevation() {
        return baseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return fragment.get(position).getCardView();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragments = super.instantiateItem(container, position);
        fragment.set(position, (CardFragments) fragments);
        return fragments;
    }

    @Override
    public int getCount() {
        return fragment.size();
    }

    public void addCardFragment(CardFragments fragments) {
        fragment.add(fragments);
    }
}
