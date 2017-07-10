package tech.mohitkumar.internappdesign.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.mohitkumar.internappdesign.Models.HorizontalItems;
import tech.mohitkumar.internappdesign.R;

/**
 * Created by mohitkumar on 10/07/17.
 */

public class HorizontalRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<HorizontalItems> arrayList = new ArrayList<HorizontalItems>();

    private int rowIndex = -1;

    public HorizontalRecyclerAdapter(Context context, ArrayList<HorizontalItems> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.horizontal_list, parent, false);
        ItemViewHolder holder = new ItemViewHolder(itemView,arrayList,context);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HorizontalItems hItems = arrayList.get(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder)holder;
        itemViewHolder.textView.setText(hItems.getTitle());
        itemViewHolder.imageView.setImageResource(hItems.getImage());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        Context context;
        TextView textView;
        ImageView imageView;
        ArrayList<HorizontalItems> arrayList = new ArrayList<HorizontalItems>();
        public ItemViewHolder(View itemView,ArrayList<HorizontalItems> arrayList,Context context) {
            super(itemView);
            this.context = context;
            this.arrayList = arrayList;
            textView = (TextView) itemView.findViewById(R.id.text_list_horizontal);
            imageView = (ImageView) itemView.findViewById(R.id.image_list_horizontal);
        }
    }

    public void setData(List<HorizontalItems> data) {
        if(arrayList != data) {
            arrayList = (ArrayList<HorizontalItems>) data;
            notifyDataSetChanged();
        }
    }

    public void setRowIndex(int index) {
        rowIndex = index;
    }

}
