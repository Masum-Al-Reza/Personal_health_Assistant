package com.example.finalyear;


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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyear.viewmodel.LoginViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login_user_fragment extends Fragment {
    private EditText emalEt,passET,confirmpassET;
    private Button loginBTn,REgBTn;
    private TextView statustTV;



    private LoginViewModel loginViewModel;


    public Login_user_fragment() {
        // Required empty public constructor

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // ((AppCompatActivity) getActivity()).getSupportActionBar().hide();


        loginViewModel =
                ViewModelProviders.of(this).get(LoginViewModel.class);
        return inflater.inflate(R.layout.fragment_logiin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        view.setSystemUiVisibility(uiOptions);
        emalEt=view.findViewById(R.id.usernameinput);
        passET=view.findViewById(R.id.passwrodinput);
        REgBTn=view.findViewById(R.id.registerbutton);
        loginBTn=view.findViewById(R.id.loginbtn);
        statustTV=view.findViewById(R.id.statusTV);
        loginBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emalEt.getText().toString();
                String password=passET.getText().toString();
                loginViewModel.Login(email,password);

            }
        });
        loginViewModel.statelivedata.observe(this, new Observer<LoginViewModel.Authenticaitionstate>() {
            @Override
            public void onChanged(LoginViewModel.Authenticaitionstate authenticaitionstate) {
                switch (authenticaitionstate){
                    case AUTHENTICATED:
                        Navigation.findNavController(view).navigate(R.id.action_regestration_to_nav_home);
                        Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
                        break;
                    case UNAUTHENTICATED:
                        break;
                }
            }
        });
        loginViewModel.errmsg.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                statustTV.setText(s);
            }
        });
        REgBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_regestration_to_nav_tools);
            }
        });

    }


}
