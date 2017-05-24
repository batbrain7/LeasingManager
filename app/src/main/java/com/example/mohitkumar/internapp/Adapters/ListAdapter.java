package com.example.mohitkumar.internapp.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mohitkumar.internapp.R;
import com.example.mohitkumar.internapp.utils.ListProvide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohitkumar on 24/05/17.
 */

public class ListAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public ListAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    static class Data {
        TextView name;
        TextView message;
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        view = convertView;
        Data d;
        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_message,parent,false);
            d = new ListAdapter.Data();
            d.name = (TextView)view.findViewById(R.id.name_person);
            d.message = (TextView)view.findViewById(R.id.message_text);

            view.setTag(d);
        }
        else  {
            d = (ListAdapter.Data)view.getTag();
        }
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"fonts/OpenSans-Light.ttf");
        Typeface tff = Typeface.createFromAsset(getContext().getAssets(),"fonts/OpenSans-Regular.ttf");


        ListProvide listViewData = (ListProvide) this.getItem(position);

        return view;
    }
    
}
