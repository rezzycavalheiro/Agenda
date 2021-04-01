package com.example.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AgendaMain extends AppCompatActivity {

    ListView listView;
    ArrayList<String> lista = new ArrayList<>();
    private Button addItem;
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_main);

        addItem = (Button) findViewById(R.id.addItemButton);
        addItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openAddItem();
            }
        });

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.containsKey("Item")) {
                boolean isNew = extras.getBoolean("Item", false);
                result = extras.getString("Item");
            }
        }

        listView = findViewById(R.id.listView);

        lista.add(result);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aux = lista.get(position);
                lista.set(position, aux);
                updateListView();
                openAddItem();
            }
        });
        updateListView();
    }

    void updateListView(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                AgendaMain.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                lista
        );
        listView.setAdapter(adapter);
    }

    public void openAddItem() {
        Intent intent = new Intent(this, AgendaItem.class);
        intent.putExtra("Item", result);
        startActivity(intent);
    }
}