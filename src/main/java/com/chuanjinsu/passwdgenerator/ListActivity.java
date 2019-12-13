package com.chuanjinsu.passwdgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Vector;

public class ListActivity extends AppCompatActivity implements ListView.OnItemClickListener, View.OnClickListener {

    private Button buttonModify;
    private Button buttonAdd;
    private Button buttonDelete;
    private ListView listViewHints;
    private EditText editTextHint;
    private int valueId=-1;
    public static HintOperations hintOperations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        buttonModify = findViewById(R.id.buttonModifyHint);
        buttonAdd = findViewById(R.id.buttonAddHint);
        buttonDelete = findViewById(R.id.buttonDeleteHint);
        listViewHints = findViewById(R.id.listViewHints);
        editTextHint = findViewById(R.id.editTextHint);

        hintOperations = new HintOperations(this);
        showList();

        listViewHints.setOnItemClickListener(this);
        buttonAdd.setOnClickListener(this);
        buttonModify.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

    }

    private void showList(){
        hintOperations.open();
        Vector<Hint> lHints = hintOperations.listAllTheHintsReverse();
        hintOperations.close();

        Hint[] arrayHints = (Hint[]) lHints.toArray(new Hint[lHints.size()]);
        ArrayAdapter<Hint> arrayAdapter;
        arrayAdapter = new ArrayAdapter<Hint>(this, android.R.layout.simple_list_item_1, arrayHints);

        listViewHints.setAdapter(arrayAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Hint entry = (Hint) parent.getItemAtPosition(position);
        valueId = entry.getId();
        String valueHint = entry.getHint();
        editTextHint.setText(valueHint);
    }

    @Override
    public void onClick(View v) {
        if (v==buttonAdd){
            valueId=-1;
            String text = editTextHint.getText().toString();
            if (text.length()==0){
                Toast.makeText(this, "Please input something at least...", Toast.LENGTH_SHORT).show();
            }
            else {
                Hint h = new Hint(text);
                hintOperations.open();
                hintOperations.addHint(h);
                hintOperations.close();
                editTextHint.setText("");
            }
            showList();
        }
        else if (v==buttonModify){
            if (valueId==-1){
                Toast.makeText(this, "Please first select the HINT you want to modify", Toast.LENGTH_SHORT).show();
            }
            else{
                String text = editTextHint.getText().toString();
                if (text.length()==0){
                    Toast.makeText(this, "Please input something at least...", Toast.LENGTH_SHORT).show();
                }
                else {
                    Hint h = new Hint(valueId, editTextHint.getText().toString());
                    hintOperations.open();
                    boolean success = hintOperations.modifyHint(h);
                    hintOperations.close();
                    if (!success) {
                        Toast.makeText(this, "Failed to modify", Toast.LENGTH_SHORT).show();
                        editTextHint.setText("");
                    } else {
                        Toast.makeText(this, "Modified!", Toast.LENGTH_SHORT).show();
                        valueId = -1;
                        editTextHint.setText("");
                    }
                    showList();
                }
            }
        }
        else if (v==buttonDelete){
            if (valueId==-1){
                Toast.makeText(this, "Please first select the HINT you want to delete", Toast.LENGTH_SHORT).show();
            }
            else {
                hintOperations.open();
                boolean success = hintOperations.deleteHint(valueId);
                hintOperations.close();
                if (!success){
                    Toast.makeText(this, "Failed to delete...", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show();
                    valueId=-1;
                    editTextHint.setText("");
                }
                showList();
            }
        }
    }
}