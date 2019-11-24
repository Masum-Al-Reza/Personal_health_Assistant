package com.example.finalyear.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Help {
    public  static  String getvurrentdate(){
        return new SimpleDateFormat("dd/mm/yyyy HH/MM/SS").format(new Date());
    }
}
