package com.example.finalyear.firebase_repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.finalyear.pojos.Callories_pojos;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Eventrepostitory {
    private DatabaseReference rootref;
    private DatabaseReference userref;
    private  DatabaseReference eventref;
    private FirebaseUser firebaseUser;
    public MutableLiveData<List<Callories_pojos>> eventlistDB=new MutableLiveData<>();
    public MutableLiveData<Callories_pojos>eventdetailsLD=new MutableLiveData<>();
    public   Eventrepostitory(){
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        rootref= FirebaseDatabase.getInstance().getReference();
        userref=rootref.child(firebaseUser.getUid());
        eventref=userref.child("event");
        eventref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Callories_pojos> events=new ArrayList<>();
                for (DataSnapshot d: dataSnapshot.getChildren()){
                    events.add(d.getValue(Callories_pojos.class));
                    eventlistDB.postValue(events);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
public  void  addevent_to_db(Callories_pojos event ){
        String EventID=eventref.push().getKey();
        event.setEventID(EventID);
        eventref.child(EventID).setValue(event);
}
public  MutableLiveData<Callories_pojos> getevetdetailsByEventid(String eventid){


        eventref.child(eventid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Callories_pojos callories_pojos=dataSnapshot.getValue(Callories_pojos.class);
                eventdetailsLD.postValue(callories_pojos);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return  eventdetailsLD;
}
}
