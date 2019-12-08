package com.example.finalyear.ui.medicine2_gallery;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.finalyear.R;
import com.example.finalyear.helper.Help;
import com.example.finalyear.pojos.Medicine_User_pojos;
import com.example.finalyear.viewmodel.Eventviewmodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Medicine_User_profile extends Fragment {
    private EditText Medicine_nameET, Medicine_type;
    private Button SaveBTn,MedicinedateBTN,UpdateBTN;
    private CheckBox MorningCB,noonCB,nightCB;
    private ArrayList<String>mresult;
    private  String EventID;
    private String medicinedate;
    private Mediicine_profile_viewmodel user_profile_viewmodel;

    public Medicine_User_profile() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(false);
        user_profile_viewmodel= ViewModelProviders.of(this).get(Mediicine_profile_viewmodel.class);
        Bundle bundle=getArguments();
        if (bundle!=null){
            EventID=bundle.getString("id");
            user_profile_viewmodel.eventdetails(EventID);
            user_profile_viewmodel.medicinedetailsLD.observe(this, new Observer<Medicine_User_pojos>() {
                @Override
                public void onChanged(Medicine_User_pojos medicine_user_pojos) {
                    Medicine_nameET.setText(medicine_user_pojos.getMedicine_profilename());
                    Medicine_type.setText(medicine_user_pojos.getMedicine_type());
                    medicinedate=medicine_user_pojos.getMedicine_date_limit();
                    SaveBTn.setVisibility(View.GONE);
                    UpdateBTN.setVisibility(View.VISIBLE);



                }
            });



        }
        return inflater.inflate(R.layout.fragment_doctors_user_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Medicine_nameET =view.findViewById(R.id.Medicine_nameinput);
        Medicine_type =view.findViewById(R.id.Medicine_type);
        MedicinedateBTN =view.findViewById(R.id.medicine_DAte_input);
        SaveBTn=view.findViewById(R.id.saveBtn);
        UpdateBTN=view.findViewById(R.id.updateBTN);
        MorningCB=view.findViewById(R.id.morningCB);
        noonCB=view.findViewById(R.id.NoonCB);
        nightCB=view.findViewById(R.id.NightCB);
        mresult=new ArrayList<>();
        UpdateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= Medicine_nameET.getText().toString();
                String type= Medicine_type.getText().toString();

                if (MorningCB.isChecked()){
                    String morning=MorningCB.getText().toString();
                    mresult.add(morning);

                }
                if (noonCB.isChecked()){
                    String Noon=noonCB.getText().toString();
                    mresult.add(Noon);
                }
                if (nightCB.isChecked()){
                    String Night=nightCB.getText().toString();
                    mresult.add(Night);
                }

                if (name.isEmpty() && type.isEmpty()){
                    Toast.makeText(getActivity(), "provide info", Toast.LENGTH_SHORT).show();
                }else {
                    Medicine_User_pojos user_pojos=new Medicine_User_pojos(EventID,name,medicinedate,mresult,type, Help.getvurrentdate());
                    user_profile_viewmodel.update(user_pojos);
                    Toast.makeText(getActivity(), "updated", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(v).navigate(R.id.nav_gallery);

                }


            }
        });
        MedicinedateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setdatemedicine();

            }

            private void setdatemedicine() {

                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd=new DatePickerDialog(getActivity(),datelistener,year,month,day);
                dpd.show();
            }
            private  DatePickerDialog.OnDateSetListener datelistener=new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    Calendar calendar=Calendar.getInstance();
                    calendar.set(i,i1,i2);
                    medicinedate = new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
                    MedicinedateBTN.setText(medicinedate);
                }
            };



        });


        SaveBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= Medicine_nameET.getText().toString();
                String type= Medicine_type.getText().toString();

                if (MorningCB.isChecked()){
                    String morning=MorningCB.getText().toString();
                    mresult.add(morning);
                }
                if (noonCB.isChecked()){
                    String Noon=noonCB.getText().toString();
                    mresult.add(Noon);
                }
                if (nightCB.isChecked()){
                    String Night=nightCB.getText().toString();
                    mresult.add(Night);
                }

                if (name.isEmpty() && type.isEmpty()){
                    Toast.makeText(getActivity(), "provide info", Toast.LENGTH_SHORT).show();
                }else {
                    Medicine_User_pojos user_pojos=new Medicine_User_pojos(null,name,medicinedate,mresult,type,Help.getvurrentdate());
                    user_profile_viewmodel.save(user_pojos);
                    Toast.makeText(getActivity(), "saved", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(v).navigate(R.id.nav_home);
                 
                }



            }
        });


    }
}
