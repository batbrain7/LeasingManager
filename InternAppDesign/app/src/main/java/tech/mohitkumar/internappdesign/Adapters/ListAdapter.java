package tech.mohitkumar.internappdesign.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohitkumar on 28/06/17.
 */

public class ListAdapter extends ArrayAdapter {

    List list = new ArrayList<String>();

    public ListAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    static class Data {
        TextView gname;

    }
}
