package com.example.classmate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/11/4 0004.
 */

public class MysqlCreate extends SQLiteOpenHelper {

    public static final String Create_database = "create table Classmate("
            +"id integer primary key,"
            +"name text,"
            +"address text,"
            +"tel text,"
            +"wechat text,"
            +"email text,"
            +"QQ text,"
            +"describe text)";

    private Context mcontext;

    public MysqlCreate(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory,  int version) {
        super(context, name, cursorFactory, version);
        mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_database);
        Toast.makeText(mcontext, "sucess", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        db.execSQL("drop table if exists Classmate");
        onCreate(db);
    }

}
