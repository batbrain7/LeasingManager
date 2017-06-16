package tech.mohitkumar.internappdesign.Interface;

import android.support.v7.widget.CardView;

/**
 * Created by mohitkumar on 15/06/17.
 */

public interface CardAdapter {
    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}
