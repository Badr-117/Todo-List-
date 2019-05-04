package com.example.listviewsqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listviewsqlite.Db.dbAdapter;
import com.example.listviewsqlite.ListView.CustomAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Task> tasks=new ArrayList<>();
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        lv= (ListView) findViewById(R.id.lv);
        adapter=new CustomAdapter(this,tasks);

        this.getTasks();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });

    }

    private void getTasks() {
        tasks.clear();
        dbAdapter db = new dbAdapter(this);
        db.openDB();
        Cursor c=db.retrieve();
        Task task=null;

        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String myTask=c.getString(1);
            String myDate=c.getString(2);
            String myTime=c.getString(3);

            task=new Task();
            task.setId(id);
            task.setTask(myTask);
            task.setDate(myDate);
            task.setTime(myTime);

            tasks.add(task);
        }

        db.closeDB();
        lv.setAdapter(adapter);
    }

    private void delete()
    {

        int id=adapter.getSelectedItemID();

        dbAdapter db=new dbAdapter(this);
        db.openDB();
        boolean deleted=db.delete(id);
        db.closeDB();

        if(deleted)
        {
            getTasks();
        }else {
            Toast.makeText(this,"Unable To Delete",Toast.LENGTH_SHORT).show();
        }
    }

    public void EditCall(){
        int myID = adapter.getSelectedItemID();
        Intent i = new Intent(MainActivity.this, EditActivity.class);
        i.putExtra("itemID", myID);
        startActivity(i);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        CharSequence title=item.getTitle();
        if(title=="EDIT")
        {
            EditCall();

        }else  if(title=="DELETE")
        {
            delete();
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
