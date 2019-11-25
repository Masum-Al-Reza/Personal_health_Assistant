package com.example.finalyear.ui.doctors_gallery;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalyear.pojos.Callories_pojos;
import com.example.finalyear.pojos.Docotor_User_pojos;
import com.example.finalyear.pojos.User_pojos;
import com.example.finalyear.ui.User_profile.User_profile_repository;

import java.util.List;

public class Doctore_User_profile_viewmodel extends ViewModel {
    private Doctors_User_profile_repository user_profile_repository;
    public MutableLiveData<List<Docotor_User_pojos>> eventlistDB=new MutableLiveData<>();
    public MutableLiveData<Docotor_User_pojos>DoctordetailsLD=new MutableLiveData<>();


    public Doctore_User_profile_viewmodel(){
        user_profile_repository=new Doctors_User_profile_repository();
        eventlistDB=user_profile_repository.eventlistDB;

    }
    public  void  save(Docotor_User_pojos event){

        user_profile_repository.addevent_to_db(event);
    }
    public  void  eventdetails(String eventid){
        DoctordetailsLD=user_profile_repository.getevetdetailsByEventid(eventid);
    }

}
