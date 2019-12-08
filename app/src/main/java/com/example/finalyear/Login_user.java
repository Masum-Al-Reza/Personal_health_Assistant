package com.example.finalyear;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finalyear.ui.Login.LoginViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login_user extends Fragment {
    private EditText emalEt,passET,confirmpassET;
    private Button loginBTn,REgBTn;
    private TextView statustTV;



    private LoginViewModel loginViewModel;


    public Login_user() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loginViewModel =
                ViewModelProviders.of(this).get(LoginViewModel.class);
        return inflater.inflate(R.layout.fragment_regestration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        loginViewModel.errmsg.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                statustTV.setText(s);
            }
        });
        REgBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.regestration);
            }
        });
    }

}
