package com.chuanjinsu.passwdgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class HelpPageActivity extends AppCompatActivity {

    private WebView webViewHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_page);
        webViewHelp = findViewById(R.id.webViewHelp);
        String urlHelpPage="file:///android_asset/HelpPage.html";
        webViewHelp.loadUrl(urlHelpPage);
    }
}
