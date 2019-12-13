package com.chuanjinsu.passwdgenerator;


import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;


public class passwordGenerator{
    private JSONObject passwdDict;

    public passwordGenerator(Context context){
        String dictString;
        dictString = ReadFromfile("passwordDict.json", context);
        try {
            passwdDict = new JSONObject(dictString);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }


    private String ReadFromfile(String fileName, Context context) {
        StringBuilder ReturnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getResources().getAssets()
                    .open(fileName);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                ReturnString.append(line);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return ReturnString.toString();
    }


    private int pow(int a, int b, int c){
        int res = 1;
        for (int i=0; i<b; i++){
            res = res*a;
            res = res%c;
        }
        return res;
    }


    public String getPassword(String key1, String key2, String key3, int num){
        int i;
        int j;
        int index;
        int key_generate = 1;
        for (i=0;i<key2.length();i++){
//            key_generate = key_generate + ((int)(Math.pow((int)(key2.charAt(i)), i+1)) % 1741);
            key_generate = key_generate + pow((int)(key2.charAt(i)), i+1, 1741) ;
            key_generate = key_generate % 1741;
        }
        for (i=0;i<key3.length();i++){
//            key_generate = key_generate + ((int)(Math.pow((int)(key3.charAt(i)), i+1)) % 1223);
            key_generate = key_generate + pow((int)(key3.charAt(i)), i+1, 1223);
            key_generate = key_generate % 2011;
        }
        String passwd = "";
        for (i=0;i<key1.length();i++){
            for (j=0;j<num;j++){
                int power = pow((int)key1.charAt(i), num, 1427);
//                int pow = (int) Math.pow((double)(int)key1.charAt(i),num) % 1427;
                index = ((j+1)*power * key_generate) % 1427;
                try{
                    passwd = passwd+this.passwdDict.getString(Character.toString(key1.charAt(i)))
                            .charAt(index);
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }

        boolean flag = false;
        String numbers = "0123456789";
        String symbols = "!@$%&*,.?-=";

        for (i=0; i<numbers.length(); i++){
            if (passwd.contains(Character.toString(numbers.charAt(i)))){
                flag = true;
            }
        }
        if (!flag){
            passwd = passwd+"0";
        }
        flag = false;
        for (i=0;i<symbols.length();i++){
            if (passwd.contains(Character.toString(symbols.charAt(i)))){
                flag = true;
            }
        }
        if (!flag){
            passwd = passwd+"!";
        }

        return passwd;
    }
}
