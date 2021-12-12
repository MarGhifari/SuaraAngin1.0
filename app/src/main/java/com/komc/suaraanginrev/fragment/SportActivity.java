package com.komc.suaraanginrev.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.komc.suaraanginrev.R;
import com.komc.suaraanginrev.activities.FragmentActivity;
import com.komc.suaraanginrev.activities.NewsDetail;
import com.komc.suaraanginrev.activities.SettingActivity;
import com.komc.suaraanginrev.adapter.NewsAdapter;
import com.komc.suaraanginrev.model.NewsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SportActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RequestQueue requestQueue;
    List<NewsModel> newsList;

    private NewsAdapter.RecyclerViewClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestQueue = com.komc.suaraanginrev.activities.MySingleton.getInstance(this).getRequestQueue();
        newsList = new ArrayList<>();
        getData();
        setAdapter();

        // Go to Setting
        ImageButton settingBtn = (ImageButton) findViewById(R.id.settingButton);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettingActivity();
            }
        });

        // Go to Fragment
        Button filterBtn = (Button) findViewById(R.id.filterButton);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragmentActivity();
            }
        });
    }

    private void openFragmentActivity() {
        Intent intent = new Intent(SportActivity.this, FragmentActivity.class);
        startActivity(intent);
    }

    private void openSettingActivity() {
        Intent intent = new Intent(SportActivity.this, SettingActivity.class);
        startActivity(intent);
    }

    private void setAdapter(){
        setOnClickListener();
    }

    private void setOnClickListener() {
        listener = new NewsAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), NewsDetail.class);
                intent.putExtra("title", newsList.get(position).getTitle());
                intent.putExtra("content", newsList.get(position).getContent());
                intent.putExtra("image", newsList.get(position).getUrlToImage());
                intent.putExtra("author", newsList.get(position).getAuthor());
                intent.putExtra("newsURL", newsList.get(position).getNewsUrl());
                startActivity(intent);
            }
        };
    }

    public void getData(){
        String url = "https://newsapi.org/v2/top-headlines?country=id&category=sports&apiKey=9d2d1f1df59f4bc5904171d1285cb7e2";

        JsonObjectRequest request =new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response){
                String source = "";
                try {
                    JSONArray items = response.getJSONArray("articles");
                    for(int i = 0; i<20; i++){
                        JSONObject item = items.getJSONObject(i);
                        String author = item.getString("author");
                        String title = item.getString("title");
                        String description = item.getString("description");
                        String newsUrl = item.getString("url");
                        String urlToImage = item.getString("urlToImage");
                        String publishedAt = item.getString("publishedAt");
                        String content = item.getString("content");

                        // Fetching JSONObject inside JSONObject
                        JSONObject newsSource = item.getJSONObject("source");
                        source = newsSource.getString("name");

                        NewsModel news = new NewsModel(author, title, description, newsUrl, urlToImage, publishedAt, content, source);
                        newsList.add(news);
                    }

                    NewsAdapter newsAdapter = new NewsAdapter(SportActivity.this, newsList, listener);
                    recyclerView.setAdapter(newsAdapter);


                } catch (JSONException e) {
                    String errorMsg = e.getMessage();
                    Toast.makeText(SportActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if(response != null && response.data != null){
                    Toast.makeText(SportActivity.this,"errorMessage1:"+response.statusCode, Toast.LENGTH_SHORT).show();
                }else{
                    String errorMessage=error.getClass().getSimpleName();
                    if(!TextUtils.isEmpty(errorMessage)){
                        Toast.makeText(SportActivity.this,"errorMessage2:"+errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }){

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("User-Agent", "Mozilla/5.0");
                return headers;
            }
        };

        // Access the RequestQueue through your singleton class.
        requestQueue.add(request);
    }
}
