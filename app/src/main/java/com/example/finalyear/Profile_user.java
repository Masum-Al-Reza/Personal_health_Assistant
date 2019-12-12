package com.example.finalyear;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.finalyear.pojos.User_pojos;
import com.example.finalyear.viewmodel.User_profile_viewmodel;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile_user extends Fragment {
    private TextView NameTV,phnnumberTV,weightTV,genderTV,heightTV;
    private Button UPdateBTN;
    private User_profile_viewmodel user_profile_viewmodel;
    private  String Eventid;


    public Profile_user() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        user_profile_viewmodel= ViewModelProviders.of(this).get(User_profile_viewmodel.class);

        user_profile_viewmodel.getuserdetails();

        return inflater.inflate(R.layout.fragment_profile_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        NameTV=view.findViewById(R.id.usernameTV);
        phnnumberTV=view.findViewById(R.id.user_number);
        weightTV=view.findViewById(R.id.userweihtTV);
        genderTV=view.findViewById(R.id.User_gender);
        heightTV=view.findViewById(R.id.userheihtTV);

        UPdateBTN=view.findViewById(R.id.updateBTN);
        user_profile_viewmodel.userdetailsLD.observe(this, new Observer<User_pojos>() {
            @Override
            public void onChanged(User_pojos user_pojos) {
                Log.i(TAG, "onChanged: "+user_pojos.getProfilename());
                NameTV.setText(user_pojos.getProfilename());
                phnnumberTV.setText(user_pojos.getGender());
                genderTV.setText(user_pojos.getNumber());
                heightTV.setText(String.valueOf(user_pojos.getHeight())+"inch");
                weightTV.setText(String.valueOf(user_pojos.getWeight())+"kg");

            }
        });



    }
}
