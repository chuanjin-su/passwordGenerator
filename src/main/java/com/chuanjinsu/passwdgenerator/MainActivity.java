package com.chuanjinsu.passwdgenerator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText key1;
    private EditText key2;
    private EditText key3;
    private EditText num;
    private TextView passwdShow;
    private Button buttonGenerate;
    private Button buttonClear;
    private Button buttonList;
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

        buttonGenerate.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
        buttonList.setOnClickListener(this);

        passwd = new passwordGenerator(getApplicationContext());
    }

    public void onClick(View v){
        String password;
        if (v==buttonGenerate){
            String text1 = key1.getText().toString();
            String text2 = key2.getText().toString();
            String text3 = key3.getText().toString();
            int factor = Integer.valueOf(num.getText().toString());
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
    }
}
