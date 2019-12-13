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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finalyear.helper.Help;
import com.example.finalyear.viewmodel.Eventviewmodel;

import com.example.finalyear.pojos.Callories_pojos;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Add_diet_Plan_fragment extends Fragment {
    private EditText EventnameEt,destinationET, target_Calorie_ET;
    private Button add_diet_BTn,DateBTn,updateBTn;
    private RadioGroup typeRG;
    private Eventviewmodel eventviewmodel;
    private String Callorydate;
    private  String dietType;
    private String calorie;
    private Spinner calorieSpinner;
    private  String EventID;



    public Add_diet_Plan_fragment() {
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


                  Callorydate=callories_pojos.getCallories_date();
                   add_diet_BTn.setVisibility(View.GONE);
                   updateBTn.setVisibility(View.VISIBLE);


                }
            });



        }
        return inflater.inflate(R.layout.fragment_diet__plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        add_diet_BTn =view.findViewById(R.id.eventaddBTn);
        updateBTn=view.findViewById(R.id.Update_event);
        DateBTn=view.findViewById(R.id.add_dateBTN);
        typeRG=view.findViewById(R.id.TYpeGRP);
        calorieSpinner = view.findViewById(R.id.callrySpin);
        String[] cities = getResources().getStringArray(R.array.calorie);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(),android.R.layout.simple_spinner_dropdown_item, cities
        );
        calorieSpinner.setAdapter(adapter);
        calorieSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calorie = adapterView.getItemAtPosition(i).toString();
               // Toast.makeText(MainActivity.this, city, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        typeRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = view.findViewById(checkedId);
                dietType = rb.getText().toString();
            }
        });

        updateBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Callories_pojos event=new Callories_pojos(EventID,dietType,Integer.parseInt(calorie), Callorydate,Help.getvurrentdate());
                    eventviewmodel.update(event);
                    Navigation.findNavController(v)
                            .navigate(R.id.action_diet_Plan_to_diet_panel);




            }
        });
        add_diet_BTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                   Callories_pojos event=new Callories_pojos(null,dietType,Integer.parseInt(calorie), Callorydate,Help.getvurrentdate());
                   eventviewmodel.save(event);
                    Navigation.findNavController(view)
                            .navigate(R.id.action_diet_Plan_to_diet_panel);





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

