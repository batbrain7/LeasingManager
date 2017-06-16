package tech.mohitkumar.internappdesign.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import tech.mohitkumar.internappdesign.R;

public class PopularTagActivity extends AppCompatActivity {

    FloatingActionButton fab;
    private Animator mAnimator;
    private int mDuration = 500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_tag);
        fab = (FloatingActionButton) findViewById(R.id.search_btn);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //zoomFabButton(fab,R.drawable.search);
            }
        });

    }

    private void zoomFabButton(View view,int rid) {

        if(mAnimator!=null) {
            mAnimator.cancel();
        }

        final FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.search_btn1);

        final Rect startBounds = new Rect();
        Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        fab.getGlobalVisibleRect(startBounds);
        findViewById(R.id.search_btn1).getGlobalVisibleRect(finalBounds,globalOffset);
        startBounds.offset(-globalOffset.x,-globalOffset.y);
        finalBounds.offset(-globalOffset.x,-globalOffset.y);

        float startScale;

        if((float)finalBounds.width()/finalBounds.height() > (float)startBounds.width()/startBounds.height()) {
            startScale = startBounds.height()/finalBounds.height();
            float startWidth = startScale*finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        fab.setAlpha(0f);
        fab1.setVisibility(View.VISIBLE);


        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(fab1, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(fab1, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(fab1, View.SCALE_X,
                        startScale, 1f)).with(ObjectAnimator.ofFloat(fab1,
                View.SCALE_Y, startScale, 1f));
        set.setDuration(mDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mAnimator = null;
            }
        });
        set.start();
        mAnimator = set;



        final float startScaleFinal = startScale;
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnimator != null) {
                    mAnimator.cancel();
                }

                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(fab1, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(fab1,
                                        View.Y,startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(fab1,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(fab1,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(mDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        fab.setAlpha(1f);
                        fab1.setVisibility(View.GONE);
                        mAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        fab.setAlpha(1f);
                        fab1.setVisibility(View.GONE);
                        mAnimator = null;
                    }
                });
                set.start();
                mAnimator = set;
            }
        });


    }
}
