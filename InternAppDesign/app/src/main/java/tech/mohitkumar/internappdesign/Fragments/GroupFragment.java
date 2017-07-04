package tech.mohitkumar.internappdesign.Fragments;

import android.animation.Animator;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.mohitkumar.internappdesign.R;

public class GroupFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private GestureDetectorCompat gestureDetectorCompat;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static final String DEBUG_TAG = "DEBUG_TAG";

    TextView mygroup,populargroup;

    ListView listView,listView1;
    public GroupFragment() {
        // Required empty public constructor
    }

    public static GroupFragment newInstance(String param1, String param2) {
        GroupFragment fragment = new GroupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view =  inflater.inflate(R.layout.fragment_group, container, false);
        mygroup = (TextView) view.findViewById(R.id.group_specifier_1);
        populargroup = (TextView) view.findViewById(R.id.group_specifier);

        listView = (ListView) view.findViewById(R.id.list_pop_groups);
        List<String> your_array_list = new ArrayList<String>();
        your_array_list.add("Social");
        your_array_list.add("Politics");
        your_array_list.add("IIT Delhi boys");
        your_array_list.add("Delhi Girls");
        your_array_list.add("Modi");
        your_array_list.add("Love");
        your_array_list.add("Indian");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_list,R.id.group_name,your_array_list );
        listView.setAdapter(arrayAdapter);

        listView1 = (ListView) view.findViewById(R.id.list_my_groups);
        List<String> your_array_list1 = new ArrayList<String>();
        your_array_list1.add("Social");
        your_array_list1.add("Politics");
        your_array_list1.add("IIT Delhi boys");
        your_array_list1.add("Delhi Girls");
        your_array_list1.add("Modi");
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getActivity(), R.layout.custom_list,R.id.group_name,your_array_list1 );
        listView1.setAdapter(arrayAdapter1);

        final View linlayout = view.findViewById(R.id.lin_my_layout);
        final View linlayout1 = view.findViewById(R.id.lin_popular_layout);
        mygroup.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                int cx = linlayout.getWidth() / 2;
                int cy = linlayout.getHeight();

                float finalRadius = (float) Math.hypot(cx, cy);

                Animator anim = ViewAnimationUtils.createCircularReveal(linlayout, cx, cy, 0, finalRadius);
                linlayout.setVisibility(View.VISIBLE);
                linlayout1.setVisibility(View.GONE);
                anim.start();
            }
        });

        populargroup.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                int cx = linlayout1.getWidth() / 2;
                int cy = linlayout1.getHeight();

                float finalRadius = (float) Math.hypot(cx, cy);

                Animator anim = ViewAnimationUtils.createCircularReveal(linlayout1, cx, cy, 0, finalRadius);
                linlayout1.setVisibility(View.VISIBLE);
                linlayout.setVisibility(View.GONE);
                anim.start();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return super.onDown(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}
