package tech.mohitkumar.internappdesign.Activities;

import android.content.Context;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    EditText editText,editText1;
    LinearLayout afraid1,sad1,frustrated1,depressed1,happy1,confused1,excited1;
    BottomSheetBehavior behavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        this.overridePendingTransition(R.anim.transition_left,
                R.anim.translation_right);
        this.overridePendingTransition(R.anim.translation_right,
                R.anim.transition_left);

        relativeLayout = (RelativeLayout) findViewById(R.id.bottom_sheet);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Fonts/OpenSans-Regular.ttf", true);
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent,VIDEO_CAPTURE_REQUEST);

        editText = (EditText) findViewById(R.id.name_video);
        editText1 = (EditText) findViewById(R.id.name_group);
        afraid1 = (LinearLayout) findViewById(R.id.afraid);
        frustrated1 = (LinearLayout) findViewById(R.id.frusted);
        depressed1 = (LinearLayout) findViewById(R.id.depressed);
        happy1 = (LinearLayout) findViewById(R.id.happy);
        confused1 = (LinearLayout) findViewById(R.id.confused);
        excited1 = (LinearLayout) findViewById(R.id.excited);
        sad1 = (LinearLayout) findViewById(R.id.sad);
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

                if (textView.getText().toString().equals("Choose an emotion") || editText.getText().toString().equals("")
                        || editText1.getText().equals("")) {
                    Toast.makeText(getApplicationContext(),"Please choose an emotion first",Toast.LENGTH_LONG).show();
                } else {

                    animatedCircleLoadingView.startDeterminate();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animatedCircleLoadingView.setPercent(10);
                            animatedCircleLoadingView.stopOk();
                        }
                    }, 1000);
                }
            }
        });
        button = (Button) findViewById(R.id.selection);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        behavior = BottomSheetBehavior.from(relativeLayout);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState){
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
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
                afraid1.setBackgroundColor(Color.parseColor("#4c76e1"));
                frustrated1.setBackgroundColor(Color.parseColor("#ffffff"));
                depressed1.setBackgroundColor(Color.parseColor("#ffffff"));
                happy1.setBackgroundColor(Color.parseColor("#ffffff"));
                confused1.setBackgroundColor(Color.parseColor("#ffffff"));
                excited1.setBackgroundColor(Color.parseColor("#ffffff"));
                sad1.setBackgroundColor(Color.parseColor("#ffffff"));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                },300);
            }
        });

        frusted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Frustrated emotion");
                afraid1.setBackgroundColor(Color.parseColor("#ffffff"));
                frustrated1.setBackgroundColor(Color.parseColor("#4c76e1"));
                depressed1.setBackgroundColor(Color.parseColor("#ffffff"));
                happy1.setBackgroundColor(Color.parseColor("#ffffff"));
                confused1.setBackgroundColor(Color.parseColor("#ffffff"));
                excited1.setBackgroundColor(Color.parseColor("#ffffff"));
                sad1.setBackgroundColor(Color.parseColor("#ffffff"));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                },300);
            }
        });

        depressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Depression emotion");
                afraid1.setBackgroundColor(Color.parseColor("#ffffff"));
                frustrated1.setBackgroundColor(Color.parseColor("#ffffff"));
                depressed1.setBackgroundColor(Color.parseColor("#4c76e1"));
                happy1.setBackgroundColor(Color.parseColor("#ffffff"));
                confused1.setBackgroundColor(Color.parseColor("#ffffff"));
                excited1.setBackgroundColor(Color.parseColor("#ffffff"));
                sad1.setBackgroundColor(Color.parseColor("#ffffff"));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                },300);
            }
        });

        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Happy emotion");
                afraid1.setBackgroundColor(Color.parseColor("#ffffff"));
                frustrated1.setBackgroundColor(Color.parseColor("#ffffff"));
                depressed1.setBackgroundColor(Color.parseColor("#ffffff"));
                happy1.setBackgroundColor(Color.parseColor("#4c76e1"));
                confused1.setBackgroundColor(Color.parseColor("#ffffff"));
                excited1.setBackgroundColor(Color.parseColor("#ffffff"));
                sad1.setBackgroundColor(Color.parseColor("#ffffff"));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                },300);
            }
        });

        confused.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Confused emotion");
                afraid1.setBackgroundColor(Color.parseColor("#ffffff"));
                frustrated1.setBackgroundColor(Color.parseColor("#ffffff"));
                depressed1.setBackgroundColor(Color.parseColor("#ffffff"));
                happy1.setBackgroundColor(Color.parseColor("#ffffff"));
                confused1.setBackgroundColor(Color.parseColor("#4c76e1"));
                excited1.setBackgroundColor(Color.parseColor("#ffffff"));
                sad1.setBackgroundColor(Color.parseColor("#ffffff"));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                },300);
            }
        });

        excited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Excited emotion");
                afraid1.setBackgroundColor(Color.parseColor("#ffffff"));
                frustrated1.setBackgroundColor(Color.parseColor("#ffffff"));
                depressed1.setBackgroundColor(Color.parseColor("#ffffff"));
                happy1.setBackgroundColor(Color.parseColor("#ffffff"));
                confused1.setBackgroundColor(Color.parseColor("#ffffff"));
                excited1.setBackgroundColor(Color.parseColor("#4c76e1"));
                sad1.setBackgroundColor(Color.parseColor("#ffffff"));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                },300);
            }
        });

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Sad emotion");
                afraid1.setBackgroundColor(Color.parseColor("#ffffff"));
                frustrated1.setBackgroundColor(Color.parseColor("#ffffff"));
                depressed1.setBackgroundColor(Color.parseColor("#ffffff"));
                happy1.setBackgroundColor(Color.parseColor("#ffffff"));
                confused1.setBackgroundColor(Color.parseColor("#ffffff"));
                excited1.setBackgroundColor(Color.parseColor("#ffffff"));
                sad1.setBackgroundColor(Color.parseColor("#4c76e1"));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                },300);
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

    public void hideKeyboard(View root) {
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    View view = v.getRootView().findFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) UploadActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    return false;
                }
                return false;
            }
        });
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
                frustrated1.setBackgroundColor(Color.parseColor("#4c76e1"));
                break;
            case R.id.depressed_emoji :
                button.setText("Depressed emoji chosen");
                afraid1.setBackgroundColor(Color.parseColor("#4c76e1"));
                break;
            case R.id.happy_emoji :
                button.setText("Happy emoji chosen");
                happy1.setBackgroundColor(Color.parseColor("#4c76e1"));
                break;
            case R.id.confused_emoji :
                button.setText("Confused emoji chosen");
                confused1.setBackgroundColor(Color.parseColor("#4c76e1"));
                break;
            case R.id.excited_emoji :
                button.setText("Excited emoji chosen");
                excited1.setBackgroundColor(Color.parseColor("#4c76e1"));
                break;
            case R.id.sad_emoji :
                button.setText("Sad emoji chosen");
                sad1.setBackgroundColor(Color.parseColor("#4c76e1"));
                break;
        }
    }
}
