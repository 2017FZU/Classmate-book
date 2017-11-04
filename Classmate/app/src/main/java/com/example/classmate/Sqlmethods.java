package com.example.classmate;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.classmate.item.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/4 0004.
 */

public class Sqlmethods implements ISqlmethods {
    @Override
    public void Delete(SQLiteDatabase db, int id) {
        String strid = ((Integer)id).toString();
        db.delete("Classmate", "id = ?", new String[]{strid});
    }

    @Override
    public void Insert(SQLiteDatabase db, Person person) {
        ContentValues cv = new ContentValues();
        cv.put("name", person.name);
        cv.put("id", person.id);
        cv.put("tel", person.phone);
        cv.put("QQ", person.qq);
        cv.put("address", person.address);
        cv.put("describe", person.message);
        cv.put("wechat", person.wechat);
        cv.put("email", person.email);
        db.insert("Classmate", null, cv);
    }

    @Override
    public void Alter(SQLiteDatabase db, int mid, String mname, String maddress, String mtel,
                        String mwechat, String email, String mQQ, String describe ) {
        String strid = ((Integer)mid).toString();
        ContentValues cv = new ContentValues();
        cv.put("name", mname);
        cv.put("tel", mtel);
        cv.put("QQ", mQQ);
        cv.put("address", maddress);
        cv.put("describe", describe);
        cv.put("wechat", mwechat);
        cv.put("email", email);
        db.update("Classmate", cv, "id = ?", new String[]{strid});
    }

    @Override
    public List<Person> initList(SQLiteDatabase db, List<Person> p) {
        //p = new ArrayList<Person>();
        int i =0;
        Cursor cursor = db.query("Classmate",null,null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String tel = cursor.getString(cursor.getColumnIndex("tel"));
                String QQ = cursor.getString(cursor.getColumnIndex("QQ"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                String wechat = cursor.getString(cursor.getColumnIndex("wechat"));
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String describe = cursor.getString(cursor.getColumnIndex("describe"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
//                System.out.println("================= db " + email);
                int Intid = Integer.parseInt(id);
                Person person = new Person(
                        Intid,
                        name,
                        address,
                        tel,
                        wechat,
                        email,
                        QQ,
                        describe
                );
                p.add(person);
                /*p.get(i).name = name;
                p.get(i).address = address;
                p.get(i).email = email;
                p.get(i).wechat = wechat;
                p.get(i).message = describe;
                p.get(i).id = Intid;
                p.get(i).qq = QQ;
                p.get(i).phone = tel;*/
                i++;
            } while (cursor.moveToNext());
        } else {

        }
        return p;
    }
}
