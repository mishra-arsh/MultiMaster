package com.example.webview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Quiz3 extends AppCompatActivity {
    RadioButton r1,r2,r3,r4;
    Button b1;
    TextView t1;
    static int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz3);
        r1=(RadioButton) findViewById(R.id.radioButton9);
        r2=(RadioButton) findViewById(R.id.radioButton10);
        r3=(RadioButton) findViewById(R.id.radioButton11);
        r4=(RadioButton) findViewById(R.id.radioButton12);
        t1=(TextView) findViewById(R.id.textView3);
        b1=(Button) findViewById(R.id.button10);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score=0;
                if(r3.isChecked()){
                    ++Quiz.score;
                }
                else {
                    --Quiz.score;
                }
                Intent i1=new Intent(Quiz3.this, Quiz4.class);
                startActivity(i1);
                finish();
            }
        });

    }
}