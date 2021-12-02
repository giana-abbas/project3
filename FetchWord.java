package com.example.project3;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchWord extends AsyncTask<String, Void, String> {
    private WeakReference<TextView> mWordText;
    private WeakReference<TextView> mDefText;

    FetchWord(TextView wordText, TextView defText) {
        this.mWordText = new WeakReference<>(wordText);
        this.mDefText = new WeakReference<>(defText);
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
        String word = null;
        String definition = null;
        JSONObject jsonObject = null;
        JSONArray itemsArray = null;
        int i = 0;
        try {
            jsonObject = new JSONObject(s);
            itemsArray = jsonObject.getJSONArray("items");
            while (i < itemsArray.length() && word == null && definition == null) {
                JSONObject dict = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = dict.getJSONObject("volumeInfo");
                word = volumeInfo.getString("word");
                definition = volumeInfo.getString("authors");
                mWordText.get().setText(word);
                mDefText.get().setText(definition);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
