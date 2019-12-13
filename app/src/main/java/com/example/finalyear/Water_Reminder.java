package com.example.finalyear;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalyear.pojos.Water_pojos;
import com.example.finalyear.viewmodel.Water_viewmodel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Water_Reminder extends Fragment {
private TextView  initialWaterTV,firstlitrTV;
private ImageView hudredd,twohund;
private int total=1200;
private Water_viewmodel water_viewmodel;



    public Water_Reminder() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
water_viewmodel= ViewModelProviders.of(this).get(Water_viewmodel.class);
        return inflater.inflate(R.layout.fragment_water__reminder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialWaterTV=view.findViewById(R.id.initial_waterlitr);
        hudredd=view.findViewById(R.id.hundredlit);
        twohund=view.findViewById(R.id.Twohud);

        firstlitrTV=view.findViewById(R.id.firstlitr);
        twohund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        hudredd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total=1200;
                int Totalcals=100;
                int remaining = total-Totalcals;
                firstlitrTV.setText(String.valueOf(remaining));

            }
        });
        water_viewmodel.medicinedetailsLD.observe(this, new Observer<Water_pojos>() {
            @Override
            public void onChanged(Water_pojos water_pojos) {
                initialWaterTV.setText(total);

            }
        });

        }


}
