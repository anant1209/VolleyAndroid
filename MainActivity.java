package com.example.volleyexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView totalCasesWorld, totalDeathsWorld, totalRecoveredWorld;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalCasesWorld = findViewById(R.id.newCasesWorld);
        totalDeathsWorld = findViewById(R.id.newDeathsWorld);
        totalRecoveredWorld = findViewById(R.id.newRecoveredWorld);

        getData();
    }

    private void getData() {
        String myUrl = "https://corona.lmao.ninja/v2/all";
        StringRequest myRequest = new StringRequest(Request.Method.GET, myUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //Create a JSON object containing information from the API.
                    JSONObject myJsonObject = new JSONObject(response);
                    totalCasesWorld.setText(myJsonObject.getString("cases"));
                    totalRecoveredWorld.setText(myJsonObject.getString("recovered"));
                    totalDeathsWorld.setText(myJsonObject.getString("deaths"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(myRequest);
    }

}