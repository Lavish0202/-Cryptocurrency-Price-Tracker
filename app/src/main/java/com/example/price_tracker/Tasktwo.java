package com.example.price_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Tasktwo extends AppCompatActivity {
    private RequestQueue requestQueue;
    private RecyclerView bidview, askview;
    private ArrayList<Item_list> list1,list2;
    private Val_adapter adapter1, adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasktwo);
        bidview=(RecyclerView) findViewById(R.id.bidview);
        askview=(RecyclerView) findViewById(R.id.askview);
        bidview.setHasFixedSize(true);
        askview.setHasFixedSize(true);
        bidview.setLayoutManager(new LinearLayoutManager(this));
        askview.setLayoutManager(new LinearLayoutManager(this));

        list1=new ArrayList<>();
        list2=new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }
    private void parseJSON() {
        String url = "https://www.bitstamp.net/api/v2/order_book/btcusd/";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray1 = response.getJSONArray("bids");
                            for (int i = 0; i < jsonArray1.length(); i++) {
                                String bid=jsonArray1.getString(i);

                                list1.add(new Item_list( bid));
                            }

                            JSONArray jsonArray2 = response.getJSONArray("asks");
                            for (int i = 0; i < jsonArray2.length(); i++) {
                                String asks=jsonArray2.getString(i);

                                list2.add(new Item_list( asks));
                            }


                            adapter1 = new Val_adapter(Tasktwo.this, list1);
                            bidview.setAdapter(adapter1);

                            adapter2 = new Val_adapter(Tasktwo.this, list2);
                            askview.setAdapter(adapter2);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }
}
