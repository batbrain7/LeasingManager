package tech.mohitkumar.internappdesign.Activities;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import tech.mohitkumar.internappdesign.Adapters.CardPagerAdapter;
import tech.mohitkumar.internappdesign.Adapters.CardPagerFragmentAdapter;
import tech.mohitkumar.internappdesign.CustomView.ShadowTransformer;
import tech.mohitkumar.internappdesign.Fragments.CardFragments;
import tech.mohitkumar.internappdesign.Fragments.MyVideos;
import tech.mohitkumar.internappdesign.Fragments.Notification;
import tech.mohitkumar.internappdesign.Fragments.ReplyFragment;
import tech.mohitkumar.internappdesign.R;

import static tech.mohitkumar.internappdesign.Activities.ProfileActivity.dpToPixels;

public class NotificationActivity extends AppCompatActivity {

    private Button mButton;
    private ViewPager mViewPager;

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private CardPagerFragmentAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;

    private boolean mShowingFragments = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setPageMargin(-50);

        mFragmentCardAdapter = new CardPagerFragmentAdapter(getSupportFragmentManager(),
                dpToPixels(2, this));

        mFragmentCardAdapter.addCardFragment(new Notification());
        mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);
        mFragmentCardShadowTransformer.enableScaling();
        mViewPager.setAdapter(mFragmentCardAdapter);
        mViewPager.setPageTransformer(false, mFragmentCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
}
