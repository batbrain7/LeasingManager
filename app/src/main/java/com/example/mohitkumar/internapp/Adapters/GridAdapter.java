package com.example.mohitkumar.internapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohitkumar.internapp.R;

/**
 * Created by mohitkumar on 24/05/17.
 */

public class GridAdapter extends BaseAdapter {

    private Context context;
    private final String[] web;
    private final int[] imageid;

    public GridAdapter(Context context,String[] web,int[] Imageid ) {
        this.context = context;
        this.web = web;
        this.imageid = Imageid;
    }

    @Override
    public int getCount() {
        return web.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(context);
            grid = inflater.inflate(R.layout.grid_in_layout, null);
            TextView textView = (TextView) grid.findViewById(R.id.text_disc);
            ImageView imageView = (ImageView)grid.findViewById(R.id.image_id);
            textView.setText(web[position]);
            imageView.setImageResource(imageid[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;

    }
}
