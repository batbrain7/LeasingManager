package tech.mohitkumar.internappdesign.Activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;
import tech.mohitkumar.internappdesign.Adapters.RecyclerViewAdapter;
import tech.mohitkumar.internappdesign.Interface.VideoFinished;
import tech.mohitkumar.internappdesign.Models.CardViewData;
import tech.mohitkumar.internappdesign.Models.HorizontalItems;
import tech.mohitkumar.internappdesign.R;

public class GroupClickActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    CardViewData cardViewData;
    ArrayList<Object> arrayList = new ArrayList<Object>();
    FloatingActionButton fab;
    ArrayList<HorizontalItems> listhor = new ArrayList<HorizontalItems>();
    String[] title = {"Video1","Video2","Video3","Video4","Video5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_click);


        this.overridePendingTransition(R.anim.transition_left,
                R.anim.translation_right);
        this.overridePendingTransition(R.anim.translation_right,
                R.anim.transition_left);

        String a = getIntent().getStringExtra("Name");
        if(a!=null) {
            getSupportActionBar().setTitle(a);
        }

        for(int i=0;i<5;i++) {
            for(int j=0;j<5;j++) {
                HorizontalItems horizontalItems = new HorizontalItems(title[i], R.drawable.vid_thumb);
                listhor.add(horizontalItems);
            }
            if(i%2 == 0)
                cardViewData = new CardViewData("http://playertest.longtailvideo.com/adaptive/bbbfull/bbbfull.m3u8","Title here","","","","","","","",listhor);
            else
                cardViewData = new CardViewData("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8","Title here","","","","","","","",listhor);
            arrayList.add(cardViewData);
        }
        Calligrapher calligrapher = new Calligrapher(GroupClickActivity.this);
        calligrapher.setFont(GroupClickActivity.this,"Fonts/OpenSans-Regular.ttf",true);

        fab = (FloatingActionButton) findViewById(R.id.join_group);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(GroupClickActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(arrayList,GroupClickActivity.this);
        recyclerView.setAdapter(adapter);

        adapter.setVideoFinished(new VideoFinished() {
            @Override
            public void onVideoFinished(int position) {

            }

            @Override
            public void onInteraction(int position) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.join_menu, menu);
        return true;
    }
}
