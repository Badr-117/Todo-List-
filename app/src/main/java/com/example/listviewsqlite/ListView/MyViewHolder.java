package com.example.listviewsqlite.ListView;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.example.listviewsqlite.R;

public class MyViewHolder implements View.OnLongClickListener, View.OnCreateContextMenuListener {

    TextView tvTask, tvDate, tvTime;
    MyClickListener longClickListener;

    public MyViewHolder(View v) {
        tvTask= (TextView) v.findViewById(R.id.taskTxt);
        tvDate= (TextView) v.findViewById(R.id.dateTxt);
        tvTime= (TextView) v.findViewById(R.id.timeTxt);

        v.setOnLongClickListener(this);
        v.setOnCreateContextMenuListener(this);
    }

    @Override
    public boolean onLongClick(View v) {
        this.longClickListener.OnItemLongClick();
        return false;
    }

    public void setLongClickListener(MyClickListener clickListener) {
        this.longClickListener=clickListener;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0,1,0,"EDIT");
        menu.add(0,2,0,"DELETE");


    }
}
