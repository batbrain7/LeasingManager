package tech.mohitkumar.internappdesign.Models;

import java.util.ArrayList;

/**
 * Created by mohitkumar on 11/07/17.
 */

public class HorizontalList {

    ArrayList<HorizontalItems> arrayList;

    public HorizontalList(ArrayList<HorizontalItems> arrayList) {
        setArrayList(arrayList);
    }

    public ArrayList<HorizontalItems> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<HorizontalItems> arrayList) {
        this.arrayList = arrayList;
    }
}
