package com.example.webview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTP2 extends AppCompatActivity {
    EditText e1;
    Button b1;
    String phone;
    String otp;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp2);
        phone=getIntent().getStringExtra("mobile").toString();
        e1=(EditText) findViewById(R.id.editTextText6);
        b1=(Button) findViewById(R.id.button23);
        firebaseAuth=FirebaseAuth.getInstance();
        genotp();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=e1.getText().toString();
                if(s1.isEmpty()){
                    Toast.makeText(OTP2.this, "Please fill the details", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(s1.length()!=6){
                        Toast.makeText(OTP2.this, "Too short", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(otp,s1);
                        signInWithPhoneAuthcredential(credential);

                    }
                }

            }
        });

    }private void genotp(){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,
                60,
                TimeUnit.SECONDS,
                this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        otp=s;
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthcredential(phoneAuthCredential);

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(OTP2.this, " Otp Missmatch ", Toast.LENGTH_SHORT).show();

                    }
                }

        );
    }
    private void signInWithPhoneAuthcredential(
            PhoneAuthCredential credential
    ){
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(OTP2.this, "Database Updated", Toast.LENGTH_SHORT).show();
                    Intent j=new Intent(OTP2.this,OTP3.class);
                    startActivity(j);
                    finish();
                }
                else {
                    Toast.makeText(OTP2.this, "Sorry", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}