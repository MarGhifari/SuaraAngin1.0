package com.komc.suaraanginrev.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
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
import com.komc.suaraanginrev.adapter.NewsAdapter;
import com.komc.suaraanginrev.model.NewsModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsDetail extends AppCompatActivity {

    RecyclerView recyclerView;
    RequestQueue requestQueue;
    List<NewsModel> newsList;

    private NewsAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.news_detail);

        // Find the object based on id
        TextView newsTitle = findViewById(R.id.newsDetailTitle);
        TextView newsDetail = findViewById(R.id.newsDetailContent);
        ImageView newsImage = findViewById(R.id.newsDetailImage);
        TextView newsAuthor = findViewById(R.id.newsDetailAuthor);
        TextView newsUrl = findViewById(R.id.newsLink);

//        newsUrl.setClickable(true);


        // Getting extras from previous page
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        String image = getIntent().getStringExtra("image");
        String author = getIntent().getStringExtra("author");
        String url = getIntent().getStringExtra("newsURL");

        String text = "<a href='"+ url +"'> Klik di sini </a>";

        // replacing template with content
        newsTitle.setText(title);
        newsDetail.setText(content);
        newsAuthor.setText(author);
        Picasso.get().load(image).into(newsImage);
//        newsUrl.setText(Html.fromHtml(text));
//        newsUrl.setText(Html.fromHtml("<a href=\"http://www.stackoverflow.com\">stackoverflow</a> "));
//        newsUrl.setText("ini url: " + url);

        String oldurl = getString(R.string.link);

        newsUrl.setText("link: "+ url);
        newsUrl.setMovementMethod(LinkMovementMethod.getInstance());

        // Going to previous page
        backButton();

        // To prepare content in recyclerview
        recyclerView = findViewById(R.id.readMore);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        requestQueue = com.komc.suaraanginrev.activities.MySingleton.getInstance(this).getRequestQueue();
        newsList = new ArrayList<>();

        // RecyclerView
        getData();
        setAdapter();
    }

    private void backButton() {
        ImageButton back = (ImageButton) findViewById(R.id.detailBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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
                startActivity(intent);
            }
        };
    }

    public void getData(){
        String url = "https://newsapi.org/v2/top-headlines?country=id&category=business&apiKey=9d2d1f1df59f4bc5904171d1285cb7e2";

        JsonObjectRequest request =new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response){
                String source = "";
                try {
                    JSONArray items = response.getJSONArray("articles");
                    for(int i = 0; i<3; i++){
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

                    NewsAdapter newsAdapter = new NewsAdapter(NewsDetail.this, newsList, listener);
                    recyclerView.setAdapter(newsAdapter);


                } catch (JSONException e) {
                    String errorMsg = e.getMessage();
                    Toast.makeText(NewsDetail.this, errorMsg, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if(response != null && response.data != null){
                    Toast.makeText(NewsDetail.this,"errorMessage1:"+response.statusCode, Toast.LENGTH_SHORT).show();
                }else{
                    String errorMessage=error.getClass().getSimpleName();
                    if(!TextUtils.isEmpty(errorMessage)){
                        Toast.makeText(NewsDetail.this,"errorMessage2:"+errorMessage, Toast.LENGTH_SHORT).show();
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
