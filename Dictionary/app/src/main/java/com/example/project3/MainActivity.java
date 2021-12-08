package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mWordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWordInput = findViewById(R.id.wordInput);
    }

    public void searchWords(View view){
        String queryString = mWordInput.getText().toString();
        Intent intent = new Intent (this, DictionaryDetailActivity.class);
        intent.putExtra("WORD", queryString);
        startActivity(intent);
    }

}