package tech.mohitkumar.internappdesign.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import tech.mohitkumar.internappdesign.Models.CommentData;
import tech.mohitkumar.internappdesign.R;

/**
 * Created by mohitkumar on 18/06/17.
 */

public class RecyclerCommentAdapter extends RecyclerView.Adapter<RecyclerCommentAdapter.RecyclerViewHolder> {

    ArrayList<CommentData> arrayList = new ArrayList<CommentData>();
    Context context;

    public RecyclerCommentAdapter(ArrayList<CommentData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_layout,parent,false);
        RecyclerCommentAdapter.RecyclerViewHolder recyclerViewHolder = new RecyclerCommentAdapter.RecyclerViewHolder(view,arrayList,context);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        CommentData commentData = arrayList.get(position);
        Typeface tf = Typeface.createFromAsset(context.getAssets(),"Fonts/OpenSans-Regular.ttf");
        holder.comment.setText(commentData.getComments());
        holder.time.setText(commentData.getTime());

        holder.comment.setTypeface(tf);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        Context context;
        ArrayList<CommentData> arrayList = new ArrayList<CommentData>();
        TextView comment,time;

        public RecyclerViewHolder(View itemView,ArrayList<CommentData> arrayList,Context context) {
            super(itemView);
            this.context = context;
            this.arrayList = arrayList;
            comment = (TextView) itemView.findViewById(R.id.comment);
            time = (TextView) itemView.findViewById(R.id.time_cmt);
        }
    }
}
