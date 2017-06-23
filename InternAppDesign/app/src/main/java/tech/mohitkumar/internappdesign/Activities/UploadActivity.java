package tech.mohitkumar.internappdesign.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;

import me.anwarshahriar.calligrapher.Calligrapher;
import tech.mohitkumar.internappdesign.R;

public class UploadActivity extends AppCompatActivity {

    public static final int VIDEO_CAPTURE_REQUEST = 1;
    RelativeLayout relativeLayout;
    LinearLayout afraid,frusted,depressed,happy,confused,excited,sad;
    FloatingActionButton fab;
    AnimatedCircleLoadingView animatedCircleLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        relativeLayout = (RelativeLayout) findViewById(R.id.bottom_sheet);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Fonts/OpenSans-Regular.ttf", true);
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent,VIDEO_CAPTURE_REQUEST);

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
        final TextView textView = (TextView) findViewById(R.id.selection);
        afraid = (LinearLayout) findViewById(R.id.afraid_emoji);
        frusted = (LinearLayout) findViewById(R.id.frusted_emoji);
        depressed = (LinearLayout) findViewById(R.id.depressed_emoji);
        happy = (LinearLayout) findViewById(R.id.happy_emoji);
        confused = (LinearLayout) findViewById(R.id.confused_emoji);
        excited = (LinearLayout) findViewById(R.id.excited_emoji);
        sad = (LinearLayout) findViewById(R.id.sad_emoji);

        BottomSheetBehavior behavior = BottomSheetBehavior.from(relativeLayout);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState){
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        textView.setText("Slide up for choosing emoji");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        textView.setText("Slide down after choosing the emoji");
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
}
