package tech.mohitkumar.internappdesign.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import tech.mohitkumar.internappdesign.Adapters.RecyclerCommentAdapter;
import tech.mohitkumar.internappdesign.Models.CommentData;
import tech.mohitkumar.internappdesign.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TextReplies extends Fragment {

    EditText editText;
    private RecyclerView recyclerView;
    RecyclerCommentAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<CommentData> arrayList = new ArrayList<CommentData>();

    public TextReplies() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_text_replies, container, false);
        arrayList.add(new CommentData("Good thinking","35mins ago"));
        arrayList.add(new CommentData("Good to know that","1h ago"));
        arrayList.add(new CommentData("Yeah you got this right","2h ago"));
        arrayList.add(new CommentData("Such a good message from the video","3h ago"));
        arrayList.add(new CommentData("Glad to know this","4h ago"));
        arrayList.add(new CommentData("Nice Video","5h ago"));
        recyclerView = (RecyclerView) view.findViewById(R.id.comt_recycler);
        editText = (EditText) view.findViewById(R.id.comment_edtxt);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerCommentAdapter(arrayList,getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }

}
