package com.example.finalyear.firebase_repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.finalyear.pojos.Callories_pojos;
import com.example.finalyear.pojos.Medicine_User_pojos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Medicine_profile_repository {
    private DatabaseReference rootref;
    private DatabaseReference userref;
    private  DatabaseReference eventref;
    private FirebaseUser firebaseUser;
    public MutableLiveData<List<Medicine_User_pojos>> eventlistDB=new MutableLiveData<>();
    public MutableLiveData<Medicine_User_pojos>medicinedetails=new MutableLiveData<>();

    public Medicine_profile_repository(){
    firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
    rootref= FirebaseDatabase.getInstance().getReference();
    userref=rootref.child(firebaseUser.getUid());
    eventref=userref.child("Medicine profile");

    eventref.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            List<Medicine_User_pojos> events=new ArrayList<>();
            for (DataSnapshot d: dataSnapshot.getChildren()){
                events.add(d.getValue(Medicine_User_pojos.class));
                eventlistDB.postValue(events);
            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });


}
    public  void  addevent_to_db(Medicine_User_pojos event ){
        String EventID=eventref.push().getKey();
        event.setMedicine_profileID(EventID);
        eventref.child(EventID).setValue(event);
    }
    public  void  update(Medicine_User_pojos event){
        String EventID=event.getMedicine_profileID();
        event.setMedicine_profileID(EventID);
        eventref.child(EventID).setValue(event);


    }
    public  void delete(Medicine_User_pojos event){
        String EventID=event.getMedicine_profileID();
        event.setMedicine_profileID(EventID);
        eventref.child(EventID).removeValue();

    }
    public  MutableLiveData<Medicine_User_pojos> getevetdetailsByEventid(String eventid){


        eventref.child(eventid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Medicine_User_pojos callories_pojos=dataSnapshot.getValue(Medicine_User_pojos.class);
                medicinedetails.postValue(callories_pojos);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return  medicinedetails;
    }


}
