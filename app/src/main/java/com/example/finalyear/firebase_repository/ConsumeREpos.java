package com.example.finalyear.firebase_repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.finalyear.pojos.Event_consume_pojos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConsumeREpos {
    private DatabaseReference rootRef;
    private DatabaseReference userRef;
    private DatabaseReference Consumeref;
    private FirebaseUser firebaseUser;
    public MutableLiveData<List<Event_consume_pojos>> consumetlistDB=new MutableLiveData<>();
    public  ConsumeREpos(){

            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            rootRef = FirebaseDatabase.getInstance().getReference();
            userRef = rootRef.child(firebaseUser.getUid());
            Consumeref = userRef.child("Consume");


    }
    public void addNewExpenseToRTDB(Event_consume_pojos expense){
        String expenseId = Consumeref.push().getKey();
        expense.setConsumeid(expenseId);
        Consumeref.child(expense.getEventId())
                .child(expenseId)
                .setValue(expense);
    }
    public MutableLiveData<List<Event_consume_pojos>> getallcallories(final String eventid){
        Consumeref.child(eventid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Event_consume_pojos> consumes=new ArrayList<>();
                for (DataSnapshot d:dataSnapshot.getChildren()){
                     consumes.add(d.getValue(Event_consume_pojos.class));
                }
                consumetlistDB.postValue(consumes);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return consumetlistDB;

    }
}
