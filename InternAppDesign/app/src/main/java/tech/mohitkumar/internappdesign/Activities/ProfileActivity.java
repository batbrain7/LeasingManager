package tech.mohitkumar.internappdesign.Activities;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import me.anwarshahriar.calligrapher.Calligrapher;
import tech.mohitkumar.internappdesign.Adapters.CardPagerAdapter;
import tech.mohitkumar.internappdesign.Adapters.CardPagerFragmentAdapter;
import tech.mohitkumar.internappdesign.Fragments.CardFragments;
import tech.mohitkumar.internappdesign.Fragments.MyVideos;
import tech.mohitkumar.internappdesign.Fragments.Notification;
import tech.mohitkumar.internappdesign.Fragments.ReplyFragment;
import tech.mohitkumar.internappdesign.MainActivity;
import tech.mohitkumar.internappdesign.R;
import tech.mohitkumar.internappdesign.CustomView.ShadowTransformer;

public class ProfileActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setTitle("Activity");


        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setPageMargin(-50);
        //mViewPager.setHorizontalFadingEdgeEnabled(true);
        //mViewPager.setFadingEdgeLength(30);
//        mCardAdapter = new CardPagerAdapter();
//        mCardAdapter.addCardItem(new CardItem(R.string.title_1, R.string.text_1));
//        mCardAdapter.addCardItem(new CardItem(R.string.title_2, R.string.text_1));
//        mCardAdapter.addCardItem(new CardItem(R.string.title_3, R.string.text_1));
//        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1));
        mFragmentCardAdapter = new CardPagerFragmentAdapter(getSupportFragmentManager(),
                dpToPixels(2, this));
        mFragmentCardAdapter.addCardFragment(new MyVideos());
        mFragmentCardAdapter.addCardFragment(new ReplyFragment());
        mFragmentCardAdapter.addCardFragment(new Notification());

        // mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
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
