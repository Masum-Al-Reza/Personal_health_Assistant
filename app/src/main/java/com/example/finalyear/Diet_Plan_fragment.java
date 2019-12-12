package com.example.finalyear;


import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalyear.helper.Help;
import com.example.finalyear.viewmodel.Eventviewmodel;
import com.example.finalyear.R;

import com.example.finalyear.pojos.Callories_pojos;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Diet_Plan_fragment extends Fragment {
    private EditText EventnameEt,destinationET,budgetET;
    private Button addeventBTn,DateBTn,updateBTn;
    private Eventviewmodel eventviewmodel;
    private String Callorydate;
    private  String EventID;
    private Help help;


    public Diet_Plan_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        eventviewmodel= ViewModelProviders.of(this).get(Eventviewmodel.class);
        Bundle bundle=getArguments();
        if (bundle !=null){

            EventID=bundle.getString("id");
            eventviewmodel.eventdetails(EventID);
            eventviewmodel.eventdetailsLD.observe(this, new Observer<Callories_pojos>() {
                @Override
                public void onChanged(Callories_pojos callories_pojos) {
                    EventnameEt.setText(callories_pojos.getDietname());
                    destinationET.setText(callories_pojos.getDiet_type());
                    budgetET.setText(String.valueOf(callories_pojos.getBudget()));
                  Callorydate=callories_pojos.getCallories_date();
                   addeventBTn.setVisibility(View.GONE);
                   updateBTn.setVisibility(View.VISIBLE);






                }
            });



        }
        return inflater.inflate(R.layout.fragment_diet__plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventnameEt=view.findViewById(R.id.inputeventname);
        destinationET=view.findViewById(R.id.inputdestination);

        budgetET=view.findViewById(R.id.inputbudget);
        addeventBTn=view.findViewById(R.id.eventaddBTn);
        updateBTn=view.findViewById(R.id.Update_event);
        DateBTn=view.findViewById(R.id.add_dateBTN);
        updateBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Diet_name= EventnameEt.getText().toString();
                String Diet_type=destinationET.getText().toString();
                String Target_callory=budgetET.getText().toString();
                if (Diet_name.isEmpty() && Diet_type.isEmpty()  && Target_callory.isEmpty()){
                    Toast.makeText(getActivity(), "provide all info", Toast.LENGTH_SHORT).show();

                }else {
                    Callories_pojos event=new Callories_pojos(EventID,Diet_name,Diet_type,Integer.parseInt(Target_callory), Callorydate,Help.getvurrentdate());
                    eventviewmodel.update(event);
                    Navigation.findNavController(v)
                            .navigate(R.id.action_diet_Plan_to_diet_panel);


                }

            }
        });
        addeventBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Diet_name= EventnameEt.getText().toString();
                String Diet_type=destinationET.getText().toString();
                String Target_callory=budgetET.getText().toString();
                if (Diet_name.isEmpty() && Diet_type.isEmpty()  && Target_callory.isEmpty()){
                    Toast.makeText(getActivity(), "provide all info", Toast.LENGTH_SHORT).show();

                }else {
                   Callories_pojos event=new Callories_pojos(null,Diet_name,Diet_type,Integer.parseInt(Target_callory), Callorydate,Help.getvurrentdate());
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
            Callorydate =new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
            DateBTn.setText(Callorydate);
        }
    };

}

