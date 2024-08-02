package com.example.leaf_disease_detection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    TextView Intro, Intro_use, IntroUse_text;
    ImageView imageView;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_200)));
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.purple_200));

        Intro=findViewById(R.id.intro_tv);
        Intro_use=findViewById(R.id.use_tv);
        IntroUse_text=findViewById(R.id.Use_text_tv);
        imageView=findViewById(R.id.leaf_bg_iv);

        dialog=new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.contact_box);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //set dialog background
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.contact_dialog_bg));
        dialog.setCancelable(false);

        Button cancel_button, mail_button;
        cancel_button=dialog.findViewById(R.id.cancel_btn);
        mail_button=dialog.findViewById(R.id.Mail_btn);



        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        mail_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                String[] recipients={"mailto@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Subject text here...");
                intent.putExtra(Intent.EXTRA_TEXT,"Body of the content here...");
//                intent.putExtra(Intent.EXTRA_CC,"mailcc@gmail.com");
                intent.setType("text/html");
//                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent, "Send mail"));

                dialog.dismiss();

            }
        });


        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case (R.id.Contact):
                        dialog.show();
                        return true;

                    case (R.id.Lang_toggle):
                        changeLanguage();
                        return true;
//                    case (R.id.eng):
//                        Locale locale_en=new Locale("en");
//                        Locale.setDefault(locale_en);
//                        Configuration configuration=new Configuration();
//                        configuration.locale=locale_en;
//                        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
//                        recreate();
//                        return true;
//                    case (R.id.hin):
//                        Locale locale_hi=new Locale("hi");
//                        Locale.setDefault(locale_hi);
//                        Configuration configuration_hi=new Configuration();
//                        configuration_hi.locale=locale_hi;
//                        getBaseContext().getResources().updateConfiguration(configuration_hi,getBaseContext().getResources().getDisplayMetrics());
//                        recreate();
//                        return true;
//                    case (R.id.pun):
//                        Locale locale_pa=new Locale("pa");
//                        Locale.setDefault(locale_pa);
//                        Configuration configuration_pa=new Configuration();
//                        configuration_pa.locale=locale_pa;
//                        getBaseContext().getResources().updateConfiguration(configuration_pa,getBaseContext().getResources().getDisplayMetrics());
//
//                        recreate();

                }

                return false;
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Instructions_activity.class));
            }
        });


    }

    private void changeLanguage() {
        if(Locale.getDefault().getLanguage().equals("pa")){
            Locale locale=new Locale("en");
            Locale.setDefault(locale);
            Configuration configuration=new Configuration();
            configuration.locale=locale;
            getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());

            recreate();
        }
        else if (Locale.getDefault().getLanguage().equals("en")){
            Locale locale=new Locale("hi");
            Locale.setDefault(locale);
            Configuration configuration=new Configuration();
            configuration.locale=locale;
            getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());

            recreate();
        }
        else if (Locale.getDefault().getLanguage().equals("hi")){
            Locale locale=new Locale("pa");
            Locale.setDefault(locale);
            Configuration configuration=new Configuration();
            configuration.locale=locale;
            getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());

            recreate();
        }



    }
}