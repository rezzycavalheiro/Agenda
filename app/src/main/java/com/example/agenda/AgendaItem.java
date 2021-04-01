package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AgendaItem extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText editTextNameItem;
    EditText editTextAddressItem;
    EditText editTextPhoneItem;
    String phoneType;
    private Button saveItem;
    File filePath;
    int fileLength;
    ArrayList<String> lista = new ArrayList<>();
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_item);

        editTextNameItem = findViewById(R.id.editTextNameItem);
        editTextAddressItem = findViewById(R.id.editTextAddressItem);
        editTextPhoneItem = findViewById(R.id.editTextPhoneItem);
        saveItem = (Button) findViewById(R.id.buttonSaveItem);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.phone_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.containsKey("Item")) {
                boolean isNew = extras.getBoolean("Item", false);
                result = extras.getString("Item");
                List<String> items = Arrays.asList(result.split("\\s*;\\s*"));
                if(items.size() > 1){
                    editTextNameItem.setText(items.get(0));
                    editTextAddressItem.setText(items.get(1));
                    editTextPhoneItem.setText(items.get(2));
                }
            }
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        phoneType = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void buttonSaveItemOnClick(View view) {
        String itemName = editTextNameItem.getText().toString();
        String itemAddress = editTextAddressItem.getText().toString();
        String itemPhone = editTextPhoneItem.getText().toString();
        String result = itemName + "; " + itemAddress + "; " + itemPhone + "; " + phoneType;

        String filename = "agenda.txt";
        String content = result;
        writeFileOnInternalStorage(view.getContext(), filename, content);
        String fileList = DataFromFile();
        String[] items = fileList.split("\r");
        for (String item : items)
        {
            lista.add(item);
        }

        Intent intent = new Intent(this, AgendaMain.class);
        intent.putExtra("Item", result);
        startActivity(intent);

//        openListView();
    }

    public void writeFileOnInternalStorage(Context mcoContext, String sFileName, String sBody) {
        File file = new File(mcoContext.getFilesDir(), "values");
        FileWriter writer;
        if (!file.exists()) {
            file.mkdir();
        }

        try {
            File userData = new File(file, sFileName);
            filePath = userData;
            writer = new FileWriter(userData, true);
            writer.append(sBody);
            writer.append("\r");
            writer.flush();
            writer.close();
            Toast.makeText(getApplicationContext(), "Item salvo com sucesso!", Toast.LENGTH_SHORT).show();
            LoadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String LoadData() throws IOException {
        fileLength = (int) filePath.length();
        byte[] bytes = new byte[fileLength];
        FileInputStream in = null;
        try {
            in = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            in.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }

        String contents = new String(bytes);

        return contents;
    }

    public String DataFromFile() {
        String itemAdded = null;
        try {
            itemAdded = LoadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemAdded;
    }
}
