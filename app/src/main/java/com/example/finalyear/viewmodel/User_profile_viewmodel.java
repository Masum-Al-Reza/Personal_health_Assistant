package com.example.finalyear.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalyear.firebase_repository.User_profile_repository;
import com.example.finalyear.pojos.User_pojos;

import java.util.List;

public class User_profile_viewmodel extends ViewModel {
    private User_profile_repository user_profile_repository;
    public MutableLiveData<List<User_pojos>> eventlistDB=new MutableLiveData<>();
    public MutableLiveData<User_pojos> eventdetailsLD=new MutableLiveData<>();

    public User_profile_viewmodel(){
        user_profile_repository=new User_profile_repository();
        eventlistDB=user_profile_repository.eventlistDB;

    }
    public  void  save(User_pojos event){

        user_profile_repository.addevent_to_db(event);
    }
    public  void  geteventdetails(){
        eventdetailsLD=user_profile_repository.getuserDetails();

    }

}
