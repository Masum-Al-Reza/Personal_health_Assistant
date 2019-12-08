package com.example.finalyear.ui.User_profile;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalyear.R;
import com.example.finalyear.pojos.User_pojos;


/**
 * A simple {@link Fragment} subclass.
 */
public class User_profile extends Fragment {
     private String eventID;
    private EditText nameET,AGeEt,numberEt,addressET;


    private Button SaveBTn;
    private User_profile_viewmodel user_profile_viewmodel;

    public User_profile() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(false);
        user_profile_viewmodel= ViewModelProviders.of(this).get(User_profile_viewmodel.class);
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        nameET=view.findViewById(R.id.nameinput);
        AGeEt=view.findViewById(R.id.age_input);
        numberEt=view.findViewById(R.id.Medicine_type);
        addressET=view.findViewById(R.id.addressinput);
        SaveBTn=view.findViewById(R.id.saveBtn);



        SaveBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id",eventID);

                String name=nameET.getText().toString();
                String age=AGeEt.getText().toString();
                String number=numberEt.getText().toString();
                String address=addressET.getText().toString();

                if (name.isEmpty() && age.isEmpty() && number.isEmpty() && address.isEmpty()){
                    Toast.makeText(getActivity(), "provide info", Toast.LENGTH_SHORT).show();
                }else {
                    User_pojos user_pojos=new User_pojos(null
                            ,name,number,address,Integer.parseInt(age));
                    user_profile_viewmodel.save(user_pojos);
                    Toast.makeText(getActivity(), "saved", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(v).navigate(R.id.profile_user,bundle);
                 
                }



            }
        });


    }
}
