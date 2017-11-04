package com.example.classmate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.classmate.item.Person;
import com.example.classmate.view.MyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 59800 on 2017/11/4.
 */

public class SelectActivity extends AppCompatActivity {

    TextView emptyView;
    RecyclerView list;
    MyAdapter myAdapter;
    List<Person> persons = new ArrayList<Person>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        String name = getIntent().getStringExtra("name");
        if (name == null) {
            emptyView.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
            initView();
        }

    }

    public void initView() {
        emptyView = (TextView) findViewById(R.id.select_null);
        list = (RecyclerView) findViewById(R.id.list_selected);

        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(myAdapter);
    }
}
