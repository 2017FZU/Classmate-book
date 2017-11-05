package com.example.classmate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.classmate.item.Person;

import java.util.List;

/**
 * Created by Administrator on 2017/11/4 0004.
 */

public interface ISqlmethods {
    public void Delete(SQLiteDatabase db,int id);

    public void Insert(SQLiteDatabase db, Person person);

    public void Alter(SQLiteDatabase db, int id, String mname, String maddress, String mtel,
                      String mwechat, String email, String QQ, String describe);

    public List<Person> initList(SQLiteDatabase db, List<Person> p);

}
