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

public class Offline extends AppCompatActivity {
    Button b1,b2,b3,b4;
    EditText e1,e2,e3;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_offline);
        b1=(Button) findViewById(R.id.button6);
        b2=(Button) findViewById(R.id.button7);
        b3=(Button) findViewById(R.id.button15);
        b4=(Button) findViewById(R.id.button17);
        e1=(EditText) findViewById(R.id.editTextText2);
        e2=(EditText) findViewById(R.id.editTextText3);
        e3=(EditText) findViewById(R.id.editTextText4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(Offline.this, Browser.class);
                startActivity(i1);
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(Offline.this, Quiz.class);
                startActivity(i2);
                finish();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3=new Intent(Offline.this, Signup.class);
                startActivity(i3);
                finish();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=e1.getText().toString();
                String s2=e2.getText().toString().trim();
                String s3=e3.getText().toString();
                if(s1.equals("")|| s2.equals("")|| s3.equals("")){
                    Toast.makeText(Offline.this, "Fill all details", Toast.LENGTH_SHORT).show();
                }
                else {
                    SQLiteDatabase data=openOrCreateDatabase("rkgit",MODE_PRIVATE,null);
                    data.execSQL("CREATE TABLE IF NOT EXISTS net (name varchar, password varchar, email varchar)");
                    String query="select * from net where ( name ='"+s1+"' and email ='"+s2+"')";
                    Cursor cursor=data.rawQuery(query,null);
                    if(cursor.getCount()>0){
                        Toast.makeText(Offline.this, "User already exists", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        data.execSQL("insert into net values('"+s1+"','"+s2+"','"+s3+"')");
                        Toast.makeText(Offline.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent i4=new Intent(Offline.this, signinoffline.class);
                        startActivity(i4);
                        finish();
                    }
                }


            }
        });


    }
}