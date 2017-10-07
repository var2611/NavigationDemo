package com.example.admin.navigationdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardView extends Fragment {
    ImageView img;
    private RecyclerView recyclerView;
    private adapterrecycle mAdapter;
    ArrayList<Friends> friendist = new ArrayList<>();

    public CardView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_card_view,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle);

        img =(ImageView) view.findViewById(R.id.imageView);



        mAdapter = new adapterrecycle(friendist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareFriendsList();


        return view;
    }

    private Context getApplicationContext() {
        return null;
    }

    private void prepareFriendsList() {
        Friends friends= new Friends("Vardhan" , "var@gmail.com","131548946");
        friendist.add(friends);

        friends= new Friends("Rohit" , "var@gmail.com","651498461");
        friendist.add(friends);

    }

    




    }


