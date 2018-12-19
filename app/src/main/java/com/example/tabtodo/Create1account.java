package com.example.tabtodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Create1account extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create1account);
        btn = (Button) findViewById(R.id.btn_back);
        backLogin();
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
}
