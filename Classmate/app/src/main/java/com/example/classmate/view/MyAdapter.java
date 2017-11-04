package com.example.classmate.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.classmate.R;
import com.example.classmate.item.Person;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 59800 on 2017/11/4.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }

    private List<Person> persons = new ArrayList<Person>();
    public MyAdapter(List<Person> persons){
        this.persons = persons;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(v, viewHolder.getAdapterPosition());
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                itemClickListener.onItemLongClick(v, viewHolder.getAdapterPosition());
                return false;
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(persons.get(position).name);
        holder.address.setText(persons.get(position).address);
        holder.phone.setText(persons.get(position).phone);
        holder.wechat.setText(persons.get(position).wechat);
        holder.email.setText(persons.get(position).email);
        System.out.println("================" + persons.get(position).email);
        holder.qq.setText(persons.get(position).qq);
        holder.message.setText(persons.get(position).message);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView address;
        TextView phone;
        TextView wechat;
        TextView email;
        TextView qq;
        TextView message;
        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.text_name);
            address = (TextView) itemView.findViewById(R.id.text_address);
            phone = (TextView) itemView.findViewById(R.id.text_phone);
            wechat = (TextView) itemView.findViewById(R.id.text_wechat);
            email = (TextView) itemView.findViewById(R.id.text_email);
            qq = (TextView) itemView.findViewById(R.id.text_qq);
            message = (TextView) itemView.findViewById(R.id.text_message);
        }
    }
}
