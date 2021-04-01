package com.example.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsername;
    EditText editTextPassword;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        signIn = (Button) findViewById(R.id.signin);
        signIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openCreateUser();
            }
        });
    }

    public void openAgendaMain() {
        Intent intent = new Intent(this, AgendaMain.class);
        startActivity(intent);
    }

    public void openCreateUser() {
        Intent intent = new Intent(this, CriacaoUsuario.class);
        startActivity(intent);
    }

    public void buttonLoginOnClick (View view) {
        String editUsername = editTextUsername.getText().toString();
        String editPassword = editTextPassword.getText().toString();
        InfoUser db = InfoUser.getInstance();

        if(TextUtils.isEmpty(editUsername) || TextUtils.isEmpty(editPassword)){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage(R.string.alert_empty_field);
            builder.create().show();
        }
        else if(db.checkUser(editUsername, editPassword)){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage(R.string.user_log_success);
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Vai para atividade agenda main
                    openAgendaMain();
                }
            });
            builder.create().show();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            // builder.setTitle(android.R.string.dialog_alert_title);
            builder.setMessage(R.string.alert_error_message);
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    editTextUsername.setText("");
                    editTextPassword.setText("");
                }
            });
            builder.create().show();
        }
    }
}