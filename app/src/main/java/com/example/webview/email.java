package com.example.webview;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class email extends AppCompatActivity {
    EditText e1,e2,e3;
    ProgressBar p2;
    Button b1,b2;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_email);
        e1=(EditText) findViewById(R.id.editTextText7);//email
        e2=(EditText) findViewById(R.id.editTextText8);//name
        e3=(EditText) findViewById(R.id.editTextText9);//password
        e3.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        p2=(ProgressBar) findViewById(R.id.progressBar);
        b1=(Button) findViewById(R.id.button25);
        b2=(Button) findViewById(R.id.button26);
        firebaseAuth=FirebaseAuth.getInstance();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(email.this, Online.class);
                startActivity(i1);
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = e1.getText().toString().trim();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                if (s1.isEmpty())
                {
                    e1.setError("Please Fill Email");
                    return;
                }
                else if (s2.isEmpty())
                {
                    e2.setError("Please Fill Name");
                    return;
                }
                else
                {
                    if (s3.isEmpty())
                    {
                        e3.setError("Please Fill Password");
                        return;
                    }
                    else
                    {
                        p2.setVisibility(View.VISIBLE);
                        firebaseAuth.createUserWithEmailAndPassword(s2,s3).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    p2.setVisibility(View.INVISIBLE);
                                    Toast.makeText(email.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    Intent i11=new Intent(email.this,signinonlineemail.class);
                                    startActivity(i11);
                                    finish();
                                }
                                else{
                                    p2.setVisibility(View.INVISIBLE);
                                    Toast.makeText(email.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });

    }
}