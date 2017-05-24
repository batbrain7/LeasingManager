package com.example.mohitkumar.internapp.Activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mohitkumar.internapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DiscussionActivity extends AppCompatActivity {


    FloatingActionButton floatingActionButton;
    EditText editText;
    ListView listView;
    TextView heading;
    ImageView itype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        String type = getIntent().getStringExtra("Type");
        String address = getIntent().getStringExtra("Address");

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



        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//        databaseReference.child("Discussion").child(address).child(type).setValue();
//        databaseReference.child("User").child(UID).child("last_name").setValue(mAuth.getCurrentUser().getDisplayName().toString());
//        databaseReference.child("User").child(UID).child("place_id").setValue(autoCompleteTextView.getText().toString());

    }
}
