package com.jg.real_estate.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jg.real_estate.Adapter.MyAdapter;
import com.jg.real_estate.DB.DatabaseHelper;
import com.jg.real_estate.DetailActivity;
import com.jg.real_estate.Model.House;
import com.jg.real_estate.R;

import java.util.ArrayList;

public class AllHomeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<House> houses;
    DatabaseHelper databaseHelper;
    MyAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all_home,container,false);
        recyclerView = view.findViewById(R.id.RecycleView);
        databaseHelper = new DatabaseHelper(getActivity());
        houses= databaseHelper.getAllData();

        myAdapter = new MyAdapter(getActivity(),houses);
        //show recycle view row data
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //set row on click listener
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClickek(int position) {
                Intent intent =new Intent(getContext(), DetailActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);

            }
        });

        return view;
    }

}
