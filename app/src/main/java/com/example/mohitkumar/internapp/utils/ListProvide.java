package com.example.mohitkumar.internapp.utils;

import java.util.List;

/**
 * Created by mohitkumar on 24/05/17.
 */

public class ListProvide {

    String name,message;

    public ListProvide(String name,String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
