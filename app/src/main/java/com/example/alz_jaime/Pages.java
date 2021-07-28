package com.example.alz_jaime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Pages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pages);

        String link = URLS.getLink();

        WebView myWebView = (WebView) findViewById(R.id.WEB);
        myWebView.loadUrl( link );
    }
}