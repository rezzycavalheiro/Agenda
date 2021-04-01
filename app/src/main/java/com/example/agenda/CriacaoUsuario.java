package com.example.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class CriacaoUsuario extends AppCompatActivity {

    EditText editTextNewName;
    EditText editTextNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criacao_usuario);

        editTextNewName = findViewById(R.id.editTextNewName);
        editTextNewPassword = findViewById(R.id.editTextNewPassword);
    }

    public void buttonSaveOnClick (View view) {

        String newUsername = editTextNewName.getText().toString();
        String newPassword = editTextNewPassword.getText().toString();

        if(TextUtils.isEmpty(newUsername) || TextUtils.isEmpty(newPassword)){
            AlertDialog.Builder builder = new AlertDialog.Builder(CriacaoUsuario.this);
            builder.setMessage(R.string.alert_empty_field);
            builder.create().show();
        }
        else {
            InfoUser db = InfoUser.getInstance();
            db.addUser(newUsername, newPassword);

            AlertDialog.Builder builder = new AlertDialog.Builder(CriacaoUsuario.this);
            builder.setMessage(R.string.new_user_success);
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    editTextNewName.setText("");
                    editTextNewPassword.setText("");
                }
            });
            builder.create().show();
        }
    }
}