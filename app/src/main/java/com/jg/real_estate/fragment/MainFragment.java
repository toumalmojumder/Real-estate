package com.jg.real_estate.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jg.real_estate.MapsActivity;
import com.jg.real_estate.R;

public class MainFragment extends Fragment {

    Button allhome,addHome,maps,contactUs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);
        allhome = view.findViewById(R.id.main_all_home);
        addHome = view.findViewById(R.id.main_add_home);
        maps = view.findViewById(R.id.main_maps);
        contactUs = view.findViewById(R.id.main_contact_us);

        //redirect to the all home recycle list view
        allhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,new AllHomeFragment()).commit();
            }
        });

        //redirect to the  home data  entry
        addHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_container,new AddHomeFragment()).commit();
            }
        });
//redirect to the map view to see all saved home location
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MapsActivity.class);
                startActivity(intent);

            }
        });
        //redirect to the developer info
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,new ContactUsFragment()).commit();
            }
        });

        return view;
    }
}
