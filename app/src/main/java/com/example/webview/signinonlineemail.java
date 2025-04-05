package com.example.webview;

import android.annotation.SuppressLint;
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

public class signinonlineemail extends AppCompatActivity {
    EditText e1, e2;
    Button b1, b2;
    ProgressBar p1;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signinonlineemail);
        e1=(EditText) findViewById(R.id.editTextText10);
        e2=(EditText) findViewById(R.id.editTextText11);
        e2.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        p1=(ProgressBar) findViewById(R.id.progressBar2);
        b1=(Button) findViewById(R.id.button29);
        b2=(Button) findViewById(R.id.button30);
        firebaseAuth=FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(signinonlineemail.this,signinonline.class);
                startActivity(i1);
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = e1.getText().toString().trim();
                String s2 = e2.getText().toString();
                if(s1.isEmpty()){
                    e1.setError("Please fill email");
                    return;
                }
                else{
                    if(s2.isEmpty()){
                        e2.setError("Please fill password");
                        return;
                    }
                    else {
                        p1.setVisibility(View.VISIBLE);
                        firebaseAuth.signInWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    p1.setVisibility(View.VISIBLE);
                                    Toast.makeText(signinonlineemail.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent i3=new Intent(signinonlineemail.this,Dashboard.class);
                                    startActivity(i3);
                                    finish();
                                }
                                else {
                                    p1.setVisibility(View.INVISIBLE);
                                    Toast.makeText(signinonlineemail.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                }

            }
        });



    }
}
