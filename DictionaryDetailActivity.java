package com.example.project3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class DictionaryDetailActivity extends AppCompatActivity {
    private final LinkedList<String> mDictionaryWords = new LinkedList<>();
    private final LinkedList<String> mDictionaryDefs = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private DictionaryAdapter mAdapter;
    private TextView mDisplaySearchedWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionarydetail);
        Intent intent = getIntent();
        String wordReceived = intent.getStringExtra("WORD");
        Log.d("SECONDACTIVITY", wordReceived);
        mDisplaySearchedWord = findViewById(R.id.searchedWord);
        mDisplaySearchedWord.setText(wordReceived);

        //get example word from first activity
        //add to linked lists using API -
        //search word
        //display word
        //display definition + synonyms
        //words = first thing displayed
        //defs = second thing displayed (does not have to be word and definition)



        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DictionaryAdapter(this, mDictionaryWords, mDictionaryDefs);
        mRecyclerView.setAdapter(mAdapter);
    }
/*
    public void searchWords(View view) {
        String queryString = "test";
        FetchWord fw = new FetchWord(mDictionaryWords, mDictionaryDefs);
        fw.execute(queryString);
    }
*/
}
