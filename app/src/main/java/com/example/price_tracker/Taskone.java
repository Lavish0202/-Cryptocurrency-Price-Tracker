package com.example.price_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class Taskone extends AppCompatActivity {
    LineChart graph;
    ArrayList<Entry> data;
    JSONArray jsonArray;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskone);
        fetchdata updateTask= new fetchdata();
        updateTask.execute();
        try {
            data= updateTask.get();
            graph=(LineChart)findViewById(R.id.graph);


            LineDataSet lineDS = new LineDataSet(data, "Price Vs Time");
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(lineDS);
            lineDS.setColor(Color.RED);
            lineDS.setLineWidth(2.5f);
            lineDS.setCircleHoleRadius(Color.GRAY);
            lineDS.setCircleColor(Color.BLUE);
            lineDS.setValueTextSize(10);
            lineDS.setValueTextColor(Color.BLUE);
            LineData data = new LineData(dataSets);
            graph.setData(data);
            graph.getDescription().setEnabled(false);
            graph.invalidate();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }




    public class fetchdata extends AsyncTask<String, Void, ArrayList<Entry>> {
        String data ="";
        @Override
        protected ArrayList<Entry> doInBackground(String... strings) {
            ArrayList<Entry> values= new ArrayList<Entry>();
            try {
                URL url = new URL("https://www.bitstamp.net/api/v2/transactions/btcusd/");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while(line != null){
                    line = bufferedReader.readLine();
                    data = data + line;
                }
                jsonArray = new JSONArray(data);
                for(int i =0 ;i <jsonArray.length(); i++) {
                    JSONObject JO = (JSONObject) jsonArray.get(i);
                    values.add(new Entry(JO.getInt("date"), JO.getInt("price")));
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return values;
        }

        @Override
        protected void onPostExecute(ArrayList<Entry> values) {
            super.onPostExecute(values);
        }

    }

}
