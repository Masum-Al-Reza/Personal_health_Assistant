package com.example.finalyear.Consume;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalyear.firebase_repository.Eventrepostitory;
import com.example.finalyear.pojos.Callories_pojos;
import com.example.finalyear.pojos.Event_consume_pojos;


import java.util.List;

public class Eventviewmodel extends ViewModel {
private Eventrepostitory eventrepostitory;
    public MutableLiveData<List<Callories_pojos>> eventlistDB=new MutableLiveData<>();
    public MutableLiveData<List<Event_consume_pojos>> consumetlistDB=new MutableLiveData<>();
    public MutableLiveData<Callories_pojos>eventdetailsLD=new MutableLiveData<>();
public Eventviewmodel(){
    eventrepostitory=new Eventrepostitory();
    eventlistDB=eventrepostitory.eventlistDB;

}
public  void  save(Callories_pojos event){

    eventrepostitory.addevent_to_db(event);
}
public  void  eventdetails(String eventid){
    eventdetailsLD=eventrepostitory.getevetdetailsByEventid(eventid);
}

}
