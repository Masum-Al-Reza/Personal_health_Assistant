package com.example.finalyear.firebase_repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.finalyear.pojos.User_pojos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class User_profile_repository {
    private DatabaseReference rootref;
    private DatabaseReference userref;
    private  DatabaseReference eventref;
    private FirebaseUser firebaseUser;
    public MutableLiveData<List<User_pojos>> eventlistDB=new MutableLiveData<>();
    public MutableLiveData<User_pojos> eventdetailsLD=new MutableLiveData<>();

    public  User_profile_repository(){
    firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
    rootref= FirebaseDatabase.getInstance().getReference();
    userref=rootref.child(firebaseUser.getUid());
    eventref=userref.child("User profile");



}
    public  void  addevent_to_db(User_pojos event ){

        eventref.setValue(event);
    }



    public  MutableLiveData<User_pojos> getuserDetails(){


        eventref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User_pojos callories_pojos=dataSnapshot.getValue(User_pojos.class);
                eventdetailsLD.postValue(callories_pojos);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return  eventdetailsLD;
    }


}
