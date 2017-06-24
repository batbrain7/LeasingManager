package tech.mohitkumar.internappdesign.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;

import me.anwarshahriar.calligrapher.Calligrapher;
import tech.mohitkumar.internappdesign.R;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int VIDEO_CAPTURE_REQUEST = 1;
    RelativeLayout relativeLayout;
    ImageView afraid,frusted,depressed,happy,confused,excited,sad;
    FloatingActionButton fab;
    AnimatedCircleLoadingView animatedCircleLoadingView;
    Button button;
    TextView textView;
    BottomSheetBehavior behavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        relativeLayout = (RelativeLayout) findViewById(R.id.bottom_sheet);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Fonts/OpenSans-Regular.ttf", true);
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent,VIDEO_CAPTURE_REQUEST);

        textView = (TextView) findViewById(R.id.emotion);
        afraid = (ImageView) findViewById(R.id.afraid_emoji);
        frusted = (ImageView) findViewById(R.id.frusted_emoji);
        depressed = (ImageView) findViewById(R.id.depressed_emoji);
        happy = (ImageView) findViewById(R.id.happy_emoji);
        confused = (ImageView)findViewById(R.id.confused_emoji);
        excited = (ImageView) findViewById(R.id.excited_emoji);
        sad = (ImageView) findViewById(R.id.sad_emoji);

        animatedCircleLoadingView = (AnimatedCircleLoadingView) findViewById(R.id.circle_loading_view);

        fab = (FloatingActionButton) findViewById(R.id.upload_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                animatedCircleLoadingView.startIndeterminate();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animatedCircleLoadingView.setPercent(100);
                        animatedCircleLoadingView.stopOk();
                    }
                },10000);
            }
        });
        button = (Button) findViewById(R.id.selection);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        behavior = BottomSheetBehavior.from(relativeLayout);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState){
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        //.setText("Slide up for choosing emoji");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                      //  textView.setText("Slide down after choosing the emoji");

                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        afraid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Afraid Emotion");
            }
        });

        frusted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Frustrated emotion");
            }
        });

        depressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Depression emotion");
            }
        });

        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Happy emotion");
            }
        });

        confused.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Confused emotion");
            }
        });

        excited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Excited emotion");
            }
        });

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Sad emotion");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == VIDEO_CAPTURE_REQUEST && resultCode == RESULT_OK && data!=null) {

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        int item = v.getId();
        Log.d("In here","INSIDE");
        switch (item) {
            case R.id.afraid_emoji :
                Log.d("Inside","IN HERE");
                button.setText("Afraid emoji chosen");
                break;
            case R.id.frusted_emoji :
                button.setText("Frustrated emoji chosen");
                break;
            case R.id.depressed_emoji :
                button.setText("Depressed emoji chosen");
                break;
            case R.id.happy_emoji :
                button.setText("Happy emoji chosen");
                break;
            case R.id.confused_emoji :
                button.setText("Confused emoji chosen");
                break;
            case R.id.excited_emoji :
                button.setText("Excited emoji chosen");
                break;
            case R.id.sad_emoji :
                button.setText("Sad emoji chosen");
                break;
        }
    }
}
