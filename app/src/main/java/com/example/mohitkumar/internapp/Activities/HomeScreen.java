package com.example.mohitkumar.internapp.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohitkumar.internapp.Adapters.GridAdapter;
import com.example.mohitkumar.internapp.R;

public class HomeScreen extends AppCompatActivity {

    String[] web = {"Electricity","Water","Rent","Garbage","Special Events","Common"};
    int[] imageId = {R.drawable.electricity,R.drawable.droplet,R.drawable.money,R.drawable.trash1,R.drawable.register,
            R.drawable.alarm};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        final String address = getIntent().getStringExtra("address");
        TextView add_text = (TextView) findViewById(R.id.address);
        TextView disc = (TextView)findViewById(R.id.discuss);

        Typeface typeface = Typeface.createFromAsset(HomeScreen.this.getAssets(),"fonts/Lato-Regular.ttf");
        add_text.setTypeface(typeface);

        GridView gridView = (GridView) findViewById(R.id.grid_view);

        GridAdapter adapter = new GridAdapter(HomeScreen.this, web, imageId);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(HomeScreen.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(HomeScreen.this, DiscussionActivity.class);
                intent.putExtra("Type","" + web[+ position ]);
                intent.putExtra("Address",address);
                startActivity(intent);

            }
        });

        add_text.setText(address);
    }
}
