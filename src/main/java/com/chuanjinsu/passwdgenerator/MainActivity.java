package com.chuanjinsu.passwdgenerator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText key1;
    private EditText key2;
    private EditText key3;
    private EditText num;
    private TextView passwdShow;
    private Button buttonGenerate;
    private Button buttonClear;
    private Button buttonList;
    private Button buttonHelp;
    private passwordGenerator passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        key1 = findViewById(R.id.editText1);
        key2 = findViewById(R.id.editText2);
        key3 = findViewById(R.id.editText3);
        num = findViewById(R.id.editNum);
        passwdShow = findViewById(R.id.password);
        buttonGenerate = findViewById(R.id.buttonGenerate);
        buttonClear = findViewById(R.id.buttonClear);
        buttonList = findViewById(R.id.buttonList);
        buttonHelp = findViewById(R.id.buttonHelp);

        buttonGenerate.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
        buttonList.setOnClickListener(this);

//        make password viewer be able to be copied when being touched
        passwdShow.setOnClickListener(this);
        buttonHelp.setOnClickListener(this);

        passwd = new passwordGenerator(getApplicationContext());
    }

    public void onClick(View v){
        String password;
        if (v==buttonGenerate){
            if (key1.getText().toString().length()==0){
                Toast.makeText(getApplicationContext(), "Please at least input something in the first key", Toast.LENGTH_SHORT).show();
                passwdShow.setText("Password Here!");
                return;
            }
            String text1 = key1.getText().toString();
            String text2 = key2.getText().toString();
            String text3 = key3.getText().toString();
            int lengthRequired = Integer.valueOf(num.getText().toString());

            int factor = (int)Math.ceil((double)lengthRequired / (double)(text1.length()));

            password = passwd.getPassword(text1, text2, text3, factor);
            passwdShow.setText(password);
        }
        else if (v==buttonClear){
            key1.setText("");
            key2.setText("");
            key3.setText("");
        }
        else if (v==buttonList){
            Intent i = new Intent();
            i.setClass(getApplicationContext(), ListActivity.class);
            startActivity(i);
        }
        else if (v==passwdShow){
            Context context = getApplicationContext();
            if (VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB) {
                final android.content.ClipboardManager clipboardManager = (android.content.ClipboardManager) context
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                final android.content.ClipData clipData = android.content.ClipData
                        .newPlainText("password", passwdShow.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
            } else {
                final android.text.ClipboardManager clipboardManager = (android.text.ClipboardManager) context
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setText(passwdShow.getText().toString());
            }
            Toast.makeText(getApplicationContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
        }
        else if (v==buttonHelp){
            Intent i = new Intent();
            i.setClass(getApplicationContext(), HelpPageActivity.class);
            startActivity(i);
        }
    }
}
