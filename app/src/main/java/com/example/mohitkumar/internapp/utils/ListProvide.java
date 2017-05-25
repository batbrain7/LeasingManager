package com.example.mohitkumar.internapp.utils;

import java.util.List;

/**
 * Created by mohitkumar on 24/05/17.
 */

public class ListProvide {

    String name,message,time;

    public ListProvide(String name,String message,String time) {
        this.name = name;
        this.message = message;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
