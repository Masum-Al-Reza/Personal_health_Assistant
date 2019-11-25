package com.example.finalyear.ui.doctors_gallery;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.finalyear.pojos.Docotor_User_pojos;
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

public class Doctors_User_profile_repository {
    private DatabaseReference rootref;
    private DatabaseReference userref;
    private  DatabaseReference eventref;
    private FirebaseUser firebaseUser;
    public MutableLiveData<List<Docotor_User_pojos>> eventlistDB=new MutableLiveData<>();
    public MutableLiveData<Docotor_User_pojos>DoctordetailsLD=new MutableLiveData<>();

    public Doctors_User_profile_repository(){
    firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
    rootref= FirebaseDatabase.getInstance().getReference();
    userref=rootref.child(firebaseUser.getUid());
    eventref=userref.child("Doctor profile");

    eventref.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            List<Docotor_User_pojos> events=new ArrayList<>();
            for (DataSnapshot d: dataSnapshot.getChildren()){
                events.add(d.getValue(Docotor_User_pojos.class));
                eventlistDB.postValue(events);
            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });


}
    public  void  addevent_to_db(Docotor_User_pojos event ){
        String EventID=eventref.push().getKey();
        event.setDoctor_profileID(EventID);
        eventref.child(EventID).setValue(event);
    }
    public  MutableLiveData<Docotor_User_pojos>  getevetdetailsByEventid(String eventid){
        eventref.child(eventid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Docotor_User_pojos callories_pojos=dataSnapshot.getValue(Docotor_User_pojos.class);
                DoctordetailsLD.postValue(callories_pojos);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
return  DoctordetailsLD;


    }
}
