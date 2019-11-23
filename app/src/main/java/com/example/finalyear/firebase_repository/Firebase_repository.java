package com.example.finalyear.firebase_repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.finalyear.ui.Login.LoginViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Firebase_repository  {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private MutableLiveData<LoginViewModel.Authenticaitionstate>statelivedata;
    private MutableLiveData<String>errmsg=new MutableLiveData<>();
    public  Firebase_repository(MutableLiveData<LoginViewModel.Authenticaitionstate>statelivedata){
       firebaseAuth=FirebaseAuth.getInstance();
       firebaseUser=firebaseAuth.getCurrentUser();
       this.statelivedata=statelivedata;

    }

    public  MutableLiveData<LoginViewModel.Authenticaitionstate>  loginuser(String email,String password){
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                firebaseUser=firebaseAuth.getCurrentUser();
                statelivedata.postValue(LoginViewModel.Authenticaitionstate.AUTHENTICATED);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                statelivedata.postValue(LoginViewModel.Authenticaitionstate.UNAUTHENTICATED);
                errmsg.postValue(e.getLocalizedMessage());
            }

        });

    return  statelivedata;

    }
    public  MutableLiveData<LoginViewModel.Authenticaitionstate>  reisteruser(String email,String password){


        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
             firebaseUser=firebaseAuth.getCurrentUser();
             statelivedata.postValue(LoginViewModel.Authenticaitionstate.AUTHENTICATED);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                statelivedata.postValue(LoginViewModel.Authenticaitionstate.UNAUTHENTICATED);
                errmsg.postValue(e.getLocalizedMessage());

            }
        });
return  statelivedata;
    }

    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public MutableLiveData<String> getErrmsg() {
        return errmsg;
    }
}
