package com.example.finalyear;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyear.pojos.User_pojos;
import com.example.finalyear.ui.User_profile.User_profile_viewmodel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile_user extends Fragment {
    private TextView NameTV,EmailTV,phnnumberTV,weightTV,genderTV,AddressTV;
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
        Bundle bundle=getArguments();
        if (bundle!=null){
            Eventid=bundle.getString("id");
            user_profile_viewmodel.geteventdetails(Eventid);
            Toast.makeText(getActivity(), "size"+Eventid, Toast.LENGTH_SHORT).show();


        }
        return inflater.inflate(R.layout.fragment_profile_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        NameTV=view.findViewById(R.id.usernameTV);
        EmailTV=view.findViewById(R.id.user_emailTV);
        AddressTV=view.findViewById(R.id.user_addressTV);
        genderTV=view.findViewById(R.id.User_gender);
        weightTV=view.findViewById(R.id.userweghtTV);

        UPdateBTN=view.findViewById(R.id.updateBTN);
        user_profile_viewmodel.eventdetailsLD.observe(this, new Observer<User_pojos>() {
            @Override
            public void onChanged(User_pojos user_pojos) {
                NameTV.setText(user_pojos.getProfilename());
            }
        });



    }
}
