package com.example.project3;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

public class FetchWord extends AsyncTask<String, Void, String> {
    private WeakReference<LinkedList> mSpeechList;
    private WeakReference<LinkedList> mDefList;
    private DictionaryAdapter mAdapter;

    FetchWord(LinkedList speechList, LinkedList defList, DictionaryAdapter adapter) {
        this.mSpeechList = new WeakReference<>(speechList);
        this.mDefList = new WeakReference<>(defList);
        this.mAdapter = adapter;
    }

    protected String getWordInfo(String query) throws IOException {
        //Dictionary API URL
        String apiURL = "https://api.dictionaryapi.dev/api/v2/entries/en/";
        apiURL += query;

        //Make connection to API
        URL requestURL = new URL(apiURL);
        HttpURLConnection urlConnection = (HttpURLConnection) requestURL.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();

        //Receive the response
        InputStream inputStream = urlConnection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        //Create a String with the response
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
            builder.append("\n");
        }
        String jsonString = builder.toString();
        Log.d("FetchWordTagJsonString", jsonString);
        return jsonString;
    }

    @Override
    protected String doInBackground(String... strings) {
        String jsonString = null;
        try {
            jsonString = getWordInfo(strings[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String partOfSpeech = null;
        String definition = null;
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        JSONArray meaningsArray = null;

        try {
            jsonArray = new JSONArray(s);
            jsonObject = jsonArray.getJSONObject(0);
            meaningsArray = jsonObject.getJSONArray("meanings");
            for (int i = 0; i < meaningsArray.length(); i++) {
                JSONObject entry = meaningsArray.getJSONObject(i);
                partOfSpeech = entry.getString("partOfSpeech");
                JSONArray defs = entry.getJSONArray("definitions");
                for (int j = 0; j < defs.length(); j++) {
                    JSONObject dict = defs.getJSONObject(j);
                    definition = dict.getString("definition");
                    Log.e("pos", partOfSpeech);
                    Log.e("def", definition);
                    mSpeechList.get().add(partOfSpeech);
                    mDefList.get().add(definition);
                    mAdapter.notifyDataSetChanged();
                    /*if (j == defs.length() - 1) {
                        Log.e("ll", mSpeechList.get().toString());
                        Log.e("ll", mDefList.get().toString());
                    }*/
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
