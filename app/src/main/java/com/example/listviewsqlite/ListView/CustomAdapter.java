package com.example.listviewsqlite.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.listviewsqlite.R;
import com.example.listviewsqlite.Task;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Task> tasks;
    LayoutInflater inflater;
    Task task;

    public CustomAdapter(Context c, ArrayList<Task> tasks) {
        this.c = c;
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(inflater==null)
        {
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.model,parent,false);
        }

        //BIND DATA
        MyViewHolder holder=new MyViewHolder(convertView);
        holder.tvTask.setText(tasks.get(position).getTask());
        holder.tvDate.setText(tasks.get(position).getDate());
        holder.tvTime.setText(tasks.get(position).getTime());


        holder.setLongClickListener(new MyClickListener() {
            @Override
            public void OnItemLongClick() {
                task= (Task) getItem(position);
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, tasks.get(position).getTask(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }


    public int getSelectedItemID()
    {
        return task.getId();
    }
    public String getSelectedItemName()
    {
        return task.getTask();
    }

}