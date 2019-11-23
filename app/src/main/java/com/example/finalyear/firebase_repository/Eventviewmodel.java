package com.example.finalyear.firebase_repository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalyear.pojos.TourmateEvent;


import java.util.List;

public class Eventviewmodel extends ViewModel {
private Eventrepostitory eventrepostitory;
    public MutableLiveData<List<TourmateEvent>> eventlistDB=new MutableLiveData<>();

public Eventviewmodel(){
    eventrepostitory=new Eventrepostitory();
    eventlistDB=eventrepostitory.eventlistDB;

}
public  void  save(TourmateEvent event){

    eventrepostitory.addevent_to_db(event);
}

}
