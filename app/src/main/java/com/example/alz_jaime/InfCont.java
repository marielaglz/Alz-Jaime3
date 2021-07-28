package com.example.alz_jaime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InfCont extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf_cont);

        ImageView face = (ImageView) findViewById(R.id.FI);
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URLS.setLink("https://www.facebook.com/Alz-JaimeApp-105562278494419");
                Intent intent = new Intent (v.getContext(), Pages.class);
                startActivityForResult(intent, 0);
            }
        });
        ImageView inge = (ImageView) findViewById(R.id.II);
        inge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URLS.setLink("https://www.instagram.com/alzjaimeapp1/");
                Intent intent = new Intent (v.getContext(), Pages.class);
                startActivityForResult(intent, 0);
            }
        });
        TextView fb = (TextView) findViewById(R.id.FBPage);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URLS.setLink("https://www.facebook.com/Alz-JaimeApp-105562278494419");
                Intent intent = new Intent (v.getContext(), Pages.class);
                startActivityForResult(intent, 0);
            }
        });
        TextView ig = (TextView) findViewById(R.id.IGPage);
        ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URLS.setLink("https://www.instagram.com/alzjaimeapp1/");
                Intent intent = new Intent (v.getContext(), Pages.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}