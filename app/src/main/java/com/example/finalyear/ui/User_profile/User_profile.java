package com.example.finalyear.ui.User_profile;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalyear.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class User_profile extends Fragment {


    public User_profile() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(false);
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }

}
