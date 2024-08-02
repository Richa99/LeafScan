package com.example.leaf_disease_detection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Instructions_activity extends AppCompatActivity {

    TextView int_title, int_text;
    ImageView iv;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        getSupportActionBar().hide();

        int_title=findViewById(R.id.instn_titl);
        int_text=findViewById(R.id.instn_tv);
        next=findViewById(R.id.next_btn);
        iv=findViewById(R.id.imageView);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Instructions_activity.this, disease_detection.class));
            }
        });
    }
}