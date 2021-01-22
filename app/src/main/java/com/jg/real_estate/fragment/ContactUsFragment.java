package com.jg.real_estate.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jg.real_estate.R;

public class ContactUsFragment extends Fragment {

Button okButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmrnt_contact_us,container,false);
                okButton = view.findViewById(R.id.contact_ok_button);
                //ok button like a back button
                okButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainFragment()).commit();
    }
});
        return view;
    }
}
