package com.example.finalyear.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalyear.firebase_repository.ConsumeREpos;
import com.example.finalyear.pojos.Event_consume_pojos;

import java.util.List;

public class ConsumeViewmodel extends ViewModel {
    private ConsumeREpos consumeREpos;
    public MutableLiveData<List<Event_consume_pojos>> consumetlistDB=new MutableLiveData<>();

    public  ConsumeViewmodel(){
      consumeREpos=new ConsumeREpos();


    }
    public  void savecallory(Event_consume_pojos consume){

        consumeREpos.addNewExpenseToRTDB(consume);
    }
    public  void addallcallories(String eventid){
      consumetlistDB=consumeREpos.getallcallories(eventid);
    }
}
