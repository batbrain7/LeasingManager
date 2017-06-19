package tech.mohitkumar.internappdesign.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tech.mohitkumar.internappdesign.Models.NotificationItem;
import tech.mohitkumar.internappdesign.R;

/**
 * Created by mohitkumar on 17/06/17.
 */

public class MyVideoAdapter extends RecyclerView.Adapter<MyVideoAdapter.RecyclerViewHolder>{

    Context context;
    ArrayList<NotificationItem> arrayList = new ArrayList<NotificationItem>();

    public MyVideoAdapter(ArrayList<NotificationItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        Log.d("TAG",Integer.toString(arrayList.size()));
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_video,parent,false);
        MyVideoAdapter.RecyclerViewHolder recyclerViewHolder = new MyVideoAdapter.RecyclerViewHolder(view,arrayList,context);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyVideoAdapter.RecyclerViewHolder holder, final int position) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(),"Fonts/OpenSans-Regular.ttf");

        NotificationItem item = arrayList.get(position);
        holder.name.setText(item.getText());
        holder.heart.setImageResource(item.getRes());

        holder.name.setTypeface(tf);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView heart;
        TextView name;

        ArrayList<NotificationItem> arrayList;
        Context context;

        public RecyclerViewHolder(View itemView, ArrayList<NotificationItem> arrayList, Context context) {
            super(itemView);
            this.context = context;
            this.arrayList = arrayList;
            heart = (ImageView) itemView.findViewById(R.id.video_thumbnail);
            name = (TextView) itemView.findViewById(R.id.my_video);
        }
    }
}
