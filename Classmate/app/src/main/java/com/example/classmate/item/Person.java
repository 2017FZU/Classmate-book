package com.example.classmate.item;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by 59800 on 2017/11/4.
 */

public class Person implements Serializable {
    public Integer id;
    public String name;
    public String address;
    public String phone;
    public String wechat;
    public String email;
    public String qq;
    public String message;


    public Person(
            Integer id,
            String name,
            String address,
            String phone,
            String wechat,
            String email,
            String qq,
            String message
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.wechat = wechat;
        this.email = email;
        this.qq = qq;
        this.message = message;
    }


}
