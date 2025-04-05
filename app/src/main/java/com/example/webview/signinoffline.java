package com.example.webview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class signinoffline extends AppCompatActivity {
    Button b1,b2,b3;
    EditText e1,e2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signinoffline);
        e1= (EditText) findViewById(R.id.editTextText12);
        e2=(EditText) findViewById(R.id.editTextText13);
        b1=(Button) findViewById(R.id.button32);
        b2=(Button) findViewById(R.id.button31);
        b3=(Button) findViewById(R.id.button33);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3=new Intent(signinoffline.this,Dashboard.class);
                startActivity(i3);
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(signinoffline.this,Signin.class);
                startActivity(i1);
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                if(s1.equals("") || s2.equals("")){
                    Toast.makeText(signinoffline.this, "Please fill all", Toast.LENGTH_SHORT).show();
                }
                else {
                    SQLiteDatabase data=openOrCreateDatabase("rkgit",MODE_PRIVATE,null);
                    data.execSQL("create table if not exists net (name varchar , password varchar , email varchar)");
                    String query="select * from net where (email='"+s1+"'and password='"+s2+"')";
                    Cursor cursor= data.rawQuery(query,null);
                    if(cursor.getCount()>0){
                        Toast.makeText(signinoffline.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent i2=new Intent(signinoffline.this,Dashboard.class);
                        startActivity(i2);
                        finish();
                    }
                    else{
                        Toast.makeText(signinoffline.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }
}