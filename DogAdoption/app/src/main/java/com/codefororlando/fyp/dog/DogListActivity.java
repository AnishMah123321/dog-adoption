package com.codefororlando.fyp.dog;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.codefororlando.fyp.R;
import com.codefororlando.fyp.adapter.DogListAdapter;
import com.codefororlando.fyp.model.UserPet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DogListActivity extends AppCompatActivity {
    private static final String TAG = "DogListActivity";
RecyclerView dogRecycler;
Bundle bundle;
DogListAdapter adapter;
List<UserPet> userPetList;
final String URL_DATA ="http://169.254.128.249/api/public/userPet/all";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dog_list_recycler);
        bundle = getIntent().getExtras();
        dogRecycler = findViewById(R.id.dog_list_recycler);
        dogRecycler.setHasFixedSize(true);
        dogRecycler.setLayoutManager(new LinearLayoutManager(this));
        userPetList = new ArrayList<>();
        loadUserPet();
    }
    private void loadUserPet() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);

                                UserPet userPet = new UserPet(obj.getString("name"),
                                        obj.getString("dog"),
                                        obj.getString("breed"),
                                        obj.getString("description"),
                                        obj.getString("number"),
                                        obj.getString("email"));

                                userPetList.add(userPet);
                            }

                            adapter = new DogListAdapter(userPetList, getApplicationContext());
                            dogRecycler.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

