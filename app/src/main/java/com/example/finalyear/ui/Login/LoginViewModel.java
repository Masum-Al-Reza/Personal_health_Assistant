package com.example.finalyear.ui.Login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalyear.firebase_repository.Firebase_repository;

public class LoginViewModel extends ViewModel {
    private Firebase_repository firebase_repository;
    public MutableLiveData<Authenticaitionstate>statelivedata;
    public MutableLiveData<String>errmsg=new MutableLiveData<>();

  public  enum Authenticaitionstate{
      AUTHENTICATED,
      UNAUTHENTICATED

  }

public  LoginViewModel(){
    statelivedata=new MutableLiveData<>();

    firebase_repository=new Firebase_repository(statelivedata);
    errmsg=firebase_repository.getErrmsg();
    if (firebase_repository.getFirebaseUser()!=null){
        statelivedata.postValue(Authenticaitionstate.AUTHENTICATED);
    }else {
        statelivedata.postValue(Authenticaitionstate.UNAUTHENTICATED);
    }

}
public  void Login(String email,String password){
   statelivedata= firebase_repository.loginuser(email, password);
}
    public  void register(String email,String password){
       statelivedata= firebase_repository.reisteruser(email, password);
    }




}