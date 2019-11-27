package com.example.finalyear.ui.doctors_gallery;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyear.R;
import com.example.finalyear.pojos.Docotor_User_pojos;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Doctor_details extends Fragment {
    private TextView docotorsnameTV,doctorsnumberTV,doctorsEmailTV,doctorsTypeTV;
    private Button callBTn,emailBTn,galleryBTN;
    private Doctore_User_profile_viewmodel doctore_user_profile_viewmodel;
    private String eventid=null;

    public Doctor_details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        doctore_user_profile_viewmodel= ViewModelProviders.of(this).get(Doctore_User_profile_viewmodel.class);

        Bundle bundle=new Bundle();
        if (bundle!=null){
            eventid=bundle.getString("id");
            doctore_user_profile_viewmodel.eventdetails(eventid);
            Toast.makeText(getActivity(), ""+eventid, Toast.LENGTH_SHORT).show();

        }
        return inflater.inflate(R.layout.fragment_doctor_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        docotorsnameTV=view.findViewById(R.id.doctornameTV);
        doctorsEmailTV=view.findViewById(R.id.doctoremailTV);
        doctorsnumberTV=view.findViewById(R.id.doctornumberTV);
        doctorsTypeTV=view.findViewById(R.id.doctortypeTV);
     //   callBTn=view.findViewById(R.id.DoctorcallBTN);
       // emailBTn=view.findViewById(R.id.doctoremailBTN);
      //  galleryBTN=view.findViewById(R.id.doctor_galleryBTN);
        doctore_user_profile_viewmodel.DoctordetailsLD.observe(this, new Observer<Docotor_User_pojos>() {
            @Override
            public void onChanged(Docotor_User_pojos docotor_user_pojos) {
                docotorsnameTV.setText(docotor_user_pojos.getDoctor_profilename());
                doctorsEmailTV.setText(docotor_user_pojos.getDoctor_address());
                doctorsnumberTV.setText(docotor_user_pojos.getDoctor_number());
                doctorsTypeTV.setText(docotor_user_pojos.getDoctor_type());
            }
        });

    }
}
