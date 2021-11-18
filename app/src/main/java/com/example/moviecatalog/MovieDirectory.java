package com.example.moviecatalog;


import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieDirectory extends AppCompatActivity {
    ListView simpleList;
    List<String[]> listData;
    List<Integer> pics;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_dir);
        showMovieDirectory();
    }

    private void createListView()
    {
        simpleList = findViewById(R.id.simpleListView);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), listData, pics, this);
        simpleList.setAdapter(customAdapter);
    }

    private void showMovieDirectory()
    {
        pics = new ArrayList<>();
        listData = new ArrayList<>();
        getDataFromAPI();
    }

    private void getDataFromAPI()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.jsonbin.io/b/618dd6084a56fb3dee0da690";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject jsonTry = new JSONObject(response);
                buildListData(jsonTry);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.d("Error response: ", error.toString()));
        queue.add(stringRequest);
    }

    private void _buildListData(JSONObject movie)
    {
        try {
            listData.add(new String[]{
                    movie.getString("title"),
                    movie.getString("overview"),
                    movie.getString("release_date"),
                    "https://themoviedb.org/t/p/w500/" + movie.getString("poster_path")
            });
            pics.add(R.drawable.ic_launcher_background);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void buildListData(JSONObject object)
    {
        try {
            if (object.get("results") instanceof JSONArray) {
                try {
                    JSONArray keys = (JSONArray) object.get("results");
                    for (int i = 0; i < keys.length(); i++)
                        _buildListData((JSONObject) keys.get(i));
                    createListView();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
