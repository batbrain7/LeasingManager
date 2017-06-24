package tech.mohitkumar.internappdesign.Activities;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;

import tech.mohitkumar.internappdesign.Adapters.CardPagerAdapter;
import tech.mohitkumar.internappdesign.Adapters.CardPagerFragmentAdapter;
import tech.mohitkumar.internappdesign.Adapters.RecyclerNotificationAdapter;
import tech.mohitkumar.internappdesign.CustomView.ShadowTransformer;
import tech.mohitkumar.internappdesign.Fragments.CardFragments;
import tech.mohitkumar.internappdesign.Fragments.MyVideos;
import tech.mohitkumar.internappdesign.Fragments.Notification;
import tech.mohitkumar.internappdesign.Fragments.ReplyFragment;
import tech.mohitkumar.internappdesign.Models.NotificationItem;
import tech.mohitkumar.internappdesign.R;

import static tech.mohitkumar.internappdesign.Activities.ProfileActivity.dpToPixels;

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    RecyclerNotificationAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<NotificationItem> arrayList = new ArrayList<NotificationItem>();
    String[] text = {"Someone hearted your video","Someone replied to your video","You just recieved a new IML"};
    int[] images = {R.drawable.heart1,R.drawable.repl,R.drawable.down};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        arrayList.add(new NotificationItem(text[0],R.drawable.heart1));
        arrayList.add(new NotificationItem(text[1],R.drawable.repl));
        arrayList.add(new NotificationItem(text[2],R.drawable.circular_black_button));

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(NotificationActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerNotificationAdapter(arrayList,NotificationActivity.this);
        recyclerView.setAdapter(adapter);
    }
}
