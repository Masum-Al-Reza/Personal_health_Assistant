package com.example.finalyear;


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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.finalyear.pojos.User_pojos;
import com.example.finalyear.viewmodel.User_profile_viewmodel;


/**
 * A simple {@link Fragment} subclass.
 */
public class Add_user_profile_fragment extends Fragment {
     private String eventID;
    private EditText nameET,AGeEt,numberEt,heightET,weightET;
    private RadioGroup RadioRG;
    private String gender = "Male";


    private Button SaveBTn;
    private User_profile_viewmodel user_profile_viewmodel;

    public Add_user_profile_fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(false);

        user_profile_viewmodel= ViewModelProviders.of(this).get(User_profile_viewmodel.class);
        return inflater.inflate(R.layout.fragment_add_user_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RadioRG = view.findViewById(R.id.genderRG);
       RadioRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               RadioButton rb = view.findViewById(checkedId);
               gender = rb.getText().toString();
               Toast.makeText(getActivity(), gender, Toast.LENGTH_SHORT).show();
           }
       });

        nameET=view.findViewById(R.id.nameinput);
        AGeEt=view.findViewById(R.id.age_input);
        numberEt=view.findViewById(R.id.number_input);
        weightET=view.findViewById(R.id.weight_input);
        heightET=view.findViewById(R.id.height_input);
        SaveBTn=view.findViewById(R.id.saveBtn);



        SaveBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String name=nameET.getText().toString();
                String age=AGeEt.getText().toString();
                String number=numberEt.getText().toString();
                String weight=weightET.getText().toString();
                String height=heightET.getText().toString();

                if (name.isEmpty() && age.isEmpty() && number.isEmpty() && weight.isEmpty() && height.isEmpty()){
                    Toast.makeText(getActivity(), "provide info", Toast.LENGTH_SHORT).show();
                }else {
                    User_pojos user_pojos=new User_pojos(null
                            ,name
                            ,number,gender,Float.parseFloat(weight),Float.parseFloat(height),Integer.parseInt(age));
                    user_profile_viewmodel.save(user_pojos);
                    Toast.makeText(getActivity(), "saved", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(v).navigate(R.id.action_user_profile_to_profile_user);
                 
                }



            }
        });


    }
}
