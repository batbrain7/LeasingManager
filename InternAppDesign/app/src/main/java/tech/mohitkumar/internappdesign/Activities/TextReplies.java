package tech.mohitkumar.internappdesign.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;
import tech.mohitkumar.internappdesign.Adapters.RecyclerCommentAdapter;
import tech.mohitkumar.internappdesign.Models.CommentData;
import tech.mohitkumar.internappdesign.R;

public class TextReplies extends AppCompatActivity {

    EditText editText;
    private RecyclerView recyclerView;
    RecyclerCommentAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<CommentData> arrayList = new ArrayList<CommentData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_replies);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Fonts/OpenSans-Regular.ttf", true);
        arrayList.add(new CommentData("Good thinking","35mins ago"));
        arrayList.add(new CommentData("Good to know that","1h ago"));
        arrayList.add(new CommentData("Yeah you got this right","2h ago"));
        arrayList.add(new CommentData("Such a good message from the video","3h ago"));
        arrayList.add(new CommentData("Glad to know this","4h ago"));
        arrayList.add(new CommentData("Nice Video","5h ago"));
        recyclerView = (RecyclerView) findViewById(R.id.comt_recycler);
        editText = (EditText) findViewById(R.id.comment_edtxt);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerCommentAdapter(arrayList,getApplicationContext());
        recyclerView.setAdapter(adapter);


    }
}
