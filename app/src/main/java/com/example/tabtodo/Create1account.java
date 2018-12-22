package com.example.tabtodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Create1account extends AppCompatActivity {
    dbTasks lstAccount ;
    Button btn,create;
    EditText fullname,username,passwrod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create1account);
        lstAccount = new dbTasks(this);
        Anhxa();
        backLogin();
        createAccount();
    }

    public void Anhxa(){
        btn = (Button) findViewById(R.id.btn_back);
        create = (Button) findViewById(R.id.btn_create);
        fullname = (EditText) findViewById(R.id.edit_fullname);
        username = (EditText) findViewById(R.id.edit_username);
        passwrod = (EditText) findViewById(R.id.edit_password);
    }
    public void backLogin(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Create1account.this,Main3Activity.class);
                startActivity(intent);
            }
        });
    }
    public void createAccount(){
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String full ;
                String user ;
                String pass ;
                full = String.valueOf(fullname.getText());
                user = String.valueOf(username.getText());
                pass = String.valueOf(passwrod.getText());
                lstAccount.insertAccount(full,user,pass);
                Intent intent = new Intent(Create1account.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
