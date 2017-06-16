package tech.mohitkumar.internappdesign.Models;

/**
 * Created by mohitkumar on 15/06/17.
 */

public class CardItem {

    private int mTextResource;
    private int mTitleResource;

    public CardItem(int title, int text) {
        mTitleResource = title;
        mTextResource = text;
    }

    public int getText() {
        return mTextResource;
    }

    public int getTitle() {
        return mTitleResource;
    }

}
