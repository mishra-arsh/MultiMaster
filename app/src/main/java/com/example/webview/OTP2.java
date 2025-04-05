package com.example.webview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

        // Get the phone number from intent
        phone = getIntent().getStringExtra("mobile");

        e1 = findViewById(R.id.editTextText6);
        b1 = findViewById(R.id.button23);
        firebaseAuth = FirebaseAuth.getInstance();

        // Generate OTP
        genotp();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = e1.getText().toString();
                if (s1.isEmpty()) {
                    Toast.makeText(OTP2.this, "Please fill the details", Toast.LENGTH_SHORT).show();
                } else {
                    if (s1.length() != 6) {
                        Toast.makeText(OTP2.this, "OTP should be 6 digits", Toast.LENGTH_SHORT).show();
                    } else {
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otp, s1);
                        signInWithPhoneAuthCredential(credential);
                    }
                }
            }
        });
    }

    private void genotp() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,  // The phone number to verify
                60,     // Timeout duration
                TimeUnit.SECONDS, // Unit of timeout
                this,   // Activity for callback
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(verificationId, forceResendingToken);
                        // Store the OTP
                        otp = verificationId;
                        Log.d("OTP_RECEIVED", "OTP sent: " + otp);
                        Toast.makeText(OTP2.this, "OTP sent successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        // Automatically verify if OTP matches
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Log.e("OTP_ERROR", "Verification failed: " + e.getMessage());
                        Toast.makeText(OTP2.this, "OTP verification failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(OTP2.this, "Authentication successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OTP2.this, OTP3.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.e("SIGN_IN_ERROR", "Authentication failed: " + task.getException().getMessage());
                    Toast.makeText(OTP2.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
