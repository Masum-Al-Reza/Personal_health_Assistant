package com.example.finalyear.ui.diet;


import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalyear.Consume.Eventviewmodel;
import com.example.finalyear.R;

import com.example.finalyear.pojos.Callories_pojos;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Diet_Plan extends Fragment {
    private EditText EventnameEt,destinationET,budgetET;
    private Button addeventBTn,DateBTn;
    private Eventviewmodel eventviewmodel;
    private String Callorydate;

    public Diet_Plan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        eventviewmodel= ViewModelProviders.of(this).get(Eventviewmodel.class);
        return inflater.inflate(R.layout.fragment_diet__plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventnameEt=view.findViewById(R.id.inputeventname);
        destinationET=view.findViewById(R.id.inputdestination);

        budgetET=view.findViewById(R.id.inputbudget);
        addeventBTn=view.findViewById(R.id.eventaddBTn);
        DateBTn=view.findViewById(R.id.add_dateBTN);
        addeventBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Diet_name= EventnameEt.getText().toString();
                String Diet_type=destinationET.getText().toString();
                String Target_callory=budgetET.getText().toString();
                if (Diet_name.isEmpty() && Diet_type.isEmpty()  && Target_callory.isEmpty()){
                    Toast.makeText(getActivity(), "provide all info", Toast.LENGTH_SHORT).show();

                }else {
                   Callories_pojos event=new Callories_pojos(null,Diet_name,Diet_type,Integer.parseInt(Target_callory), Callorydate);
                   eventviewmodel.save(event);
                    Navigation.findNavController(view)
                            .navigate(R.id.action_diet_Plan_to_diet_panel);


                }


            }
        });
        DateBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datedialogshow();
            }
        });

    }
    private void datedialogshow() {
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
            Callorydate =new SimpleDateFormat("dd/mm/yyyy").format(calendar.getTime());
            DateBTn.setText(Callorydate);
        }
    };

}

