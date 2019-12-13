package com.example.finalyear;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_profile_user, container, false);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.add_menu,menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.additem:
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.action_profile_user_to_user_profile);
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        NameTV=view.findViewById(R.id.usernameTV);
        phnnumberTV=view.findViewById(R.id.user_number);
        weightTV=view.findViewById(R.id.userweihtTV);
        genderTV=view.findViewById(R.id.User_gender);
        heightTV=view.findViewById(R.id.userheihtTV);

        UPdateBTN=view.findViewById(R.id.updaeBTNuser);
        user_profile_viewmodel.userdetailsLD.observe(this, new Observer<User_pojos>() {
            @Override
            public void onChanged(User_pojos user_pojos) {
                Log.i(TAG, "onChanged: "+user_pojos.getProfilename());
                NameTV.setText("Name:"+user_pojos.getProfilename());
                phnnumberTV.setText("Sex:"+user_pojos.getGender());
                genderTV.setText("phone Number:"+user_pojos.getNumber());
                heightTV.setText("Height:"+String.valueOf(user_pojos.getHeight())+"fit");
                weightTV.setText("Weight:"+String.valueOf(user_pojos.getWeight())+"kg");

            }
        });
       UPdateBTN.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Navigation.findNavController(v).navigate(R.id.action_profile_user_to_user_profile);
           }
       });
    }
    }
