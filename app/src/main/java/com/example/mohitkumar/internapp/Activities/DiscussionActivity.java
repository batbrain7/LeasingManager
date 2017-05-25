package com.example.mohitkumar.internapp.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mohitkumar.internapp.Adapters.ListAdapter;
import com.example.mohitkumar.internapp.R;
import com.example.mohitkumar.internapp.utils.ListProvide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.mohitkumar.internapp.R.id.address;

public class DiscussionActivity extends AppCompatActivity {


    FloatingActionButton floatingActionButton;
    EditText editText;
    ListView listView;
    TextView heading;
    ListProvide listProvide;
    ImageView itype;
    ArrayList<ListProvide> arrayList;
    ArrayList<String> date,email,message;
    DatabaseReference databaseReference;
    AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        final String type = getIntent().getStringExtra("Type");
        final String address = getIntent().getStringExtra("Address");
        final String email = getIntent().getStringExtra("email");
        itype = (ImageView) findViewById(R.id.image_type);
        heading = (TextView) findViewById(R.id.disc_head);
        avi = (AVLoadingIndicatorView) findViewById(R.id.loaderview);
        listView = (ListView) findViewById(R.id.list_view);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        Typeface typeface = Typeface.createFromAsset(DiscussionActivity.this.getAssets(),"fonts/Lato-Regular.ttf");
        heading.setTypeface(typeface);

        if(type.equals("Electricity")) {
            itype.setImageResource(R.drawable.electricity);
            heading.setText("Electricity Discussion Room");
        } else if(type.equals("Water")) {
            itype.setImageResource(R.drawable.droplet);
            heading.setText("Water Discussion Room");
        } else if(type.equals("Rent")) {
            itype.setImageResource(R.drawable.money);
            heading.setText("Rent Discussion Room");
        } else if(type.equals("Garbage")) {
            itype.setImageResource(R.drawable.trash1);
            heading.setText("Trash Discussion Room");
        } else if(type.equals("Special Events")) {
            itype.setImageResource(R.drawable.register);
            heading.setText("Event Discussion Room");
        } else if(type.equals("Common")) {
            itype.setImageResource(R.drawable.alarm);
            heading.setText("Common Discussion Room");
        }


        editText = (EditText) findViewById(R.id.comment_edit);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.send_button);


//        ProgressDialog progressDialog = new ProgressDialog(DiscussionActivity.this);
//        progressDialog.setTitle("Loading....");
//        progressDialog.show();
//        Log.d("On","Create");
        avi.setVisibility(View.VISIBLE);
        avi.show();
        UpdateAdapter(address,type);
//        progressDialog.dismiss();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editText.getText().equals("")) {
                    Log.d("In here","inside");

                } else {
//                    ProgressDialog progressDialog = new ProgressDialog(DiscussionActivity.this);
//                    progressDialog.setTitle("Wait..");
//                    progressDialog.setMessage("Posting your Comment");
//                    progressDialog.show();
                    avi.show();

                    SharedPreferences preferences = getSharedPreferences("Email", Context.MODE_PRIVATE);
                    final String mail = preferences.getString("Email","");

//                    progressDialog.setCancelable(false);
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = simpleDateFormat.format(c.getTime());
                    databaseReference.child("Discussion").child(address).child(type).child(formattedDate).child("Email").setValue(mail);
                    databaseReference.child("Discussion").child(address).child(type).child(formattedDate).child("Message").setValue(editText.getText().toString());
                    editText.setText("");
                    UpdateAdapter(address,type);
                }
            }
        });
    }

    public void UpdateAdapter(String address, final String type) {

        SharedPreferences preferences = getSharedPreferences("Email", Context.MODE_PRIVATE);
        final String mail = preferences.getString("Email","");

        Log.d("EMAIL",mail);

        Log.d("Update","Adapter");
        databaseReference.child("Discussion").child(address).child(type).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                Log.d("Inside","Adapter");
                ListAdapter listAdapter = new ListAdapter(DiscussionActivity.this,R.layout.list_message);
                listView.setAdapter(listAdapter);
                for(DataSnapshot file : dataSnapshot.getChildren()) {
                    //date.add(file.getKey());
                    Log.d("i",String.valueOf(i));
                    String Date = (String)file.getKey();
                    String Email = (String) file.child("Email").getValue();
                    String Message = (String)file.child("Message").getValue();

                    Log.d("MAIL 2",Email);

                    Log.d("MAIL",mail);
                    if(mail.equals(Email))
                       listProvide = new ListProvide(Email,Message,Date,"m");
                    else
                        listProvide = new ListProvide(Email,Message,Date,"o");
                    //arrayList.add(listProvide);
                    listAdapter.add(listProvide);
                    i++;
                }

                avi.hide();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
