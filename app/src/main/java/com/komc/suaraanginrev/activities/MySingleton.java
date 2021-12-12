package com.komc.suaraanginrev.activities;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private static MySingleton aV;
    private RequestQueue mRequestQueue;

    private MySingleton(Context context) {
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static MySingleton getInstance(Context context) {

        if (aV == null) {

            aV = new MySingleton(context);
        }
        return aV;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }
}
