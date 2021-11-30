package com.example.a2053project3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mWordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWordInput = (EditText) findViewById(R.id.wordInput);
    }

    public void searchWords(View view){
        String queryString = mWordInput.getText().toString();

    }

}