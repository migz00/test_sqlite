package com.example.pc002.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView lv;
    private Button send, delete;
    private ArrayList<String> names;
    private NameAdapter adapter;
    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);

        lv = (ListView) findViewById(R.id.lv_names);
        send = (Button) findViewById(R.id.btn_input);
        delete = (Button) findViewById(R.id.btn_clear);

        names = db.getNames();
        adapter = new NameAdapter(this, R.layout.student_list, names);

        delete.setOnClickListener(this);
        send.setOnClickListener(this);
        lv.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_input:
                EditText input = (EditText) findViewById(R.id.et_input);
                if (db.insertStudent(input.getText().toString())) {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                    this.recreate();
                } else {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_clear:
                db.clearData();
                this.recreate();
                break;
        }
    }
}
