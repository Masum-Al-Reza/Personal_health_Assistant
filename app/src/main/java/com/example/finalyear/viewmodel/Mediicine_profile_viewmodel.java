package com.example.finalyear.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalyear.firebase_repository.Medicine_profile_repository;
import com.example.finalyear.pojos.Medicine_User_pojos;

import java.util.List;

public class Mediicine_profile_viewmodel extends ViewModel {
    private Medicine_profile_repository user_profile_repository;
    public MutableLiveData<List<Medicine_User_pojos>> eventlistDB=new MutableLiveData<>();
    public MutableLiveData<Medicine_User_pojos>medicinedetailsLD=new MutableLiveData<>();


    public Mediicine_profile_viewmodel(){
        user_profile_repository=new Medicine_profile_repository();
        eventlistDB=user_profile_repository.eventlistDB;

    }
    public  void  save(Medicine_User_pojos event){

        user_profile_repository.addevent_to_db(event);
    }
    public  void  update(Medicine_User_pojos event){

        user_profile_repository.update(event);
    }  public  void  delete(Medicine_User_pojos event){

        user_profile_repository.delete(event);
    }
    public  void  eventdetails(String eventid){
        medicinedetailsLD=user_profile_repository.getevetdetailsByEventid(eventid);
    }




}
