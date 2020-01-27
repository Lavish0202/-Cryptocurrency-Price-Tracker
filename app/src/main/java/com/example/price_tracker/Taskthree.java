package com.example.price_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Trigger;

import java.util.ArrayList;

public class Taskthree extends Activity implements View.OnClickListener{
    EditText editText;
    Button savebtn;
    ListView listView;
    ArrayList<String> array = new ArrayList<String>();
    Bundle bundle;
    FirebaseJobDispatcher jobDispatcher;



    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskthree);
        editText=(EditText) findViewById(R.id.etone);
        listView=(ListView) findViewById(R.id.savelist);
        savebtn=(Button) findViewById(R.id.savebtn);
        savebtn.setOnClickListener(this);

    }
    public void onClick(View v){
        if(v.getId()==R.id.savebtn) {
            String value = " ";
            Double number = 0.0;
            String getInput = editText.getText().toString();

            if (getInput == null || getInput.trim().equals("")) {
                Toast.makeText(getBaseContext(), "Fill the field it is empty", Toast.LENGTH_LONG).show();
            } else {
                array.add(0, getInput);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Taskthree.this, android.R.layout.simple_list_item_1, array);
                listView.setAdapter(adapter);
                value = (String) listView.getItemAtPosition(0);
                number = Double.parseDouble(value);
                ((EditText) findViewById(R.id.etone)).setText(" ");

            }
            bundle = new Bundle();
            bundle.putDouble("number", number);

            jobDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(Taskthree.this));
            jobDispatcher.mustSchedule(
                    jobDispatcher.newJobBuilder()
                            .setService(Set_Notification.class)
                            .setExtras(bundle)
                            .setTag("JOB_KEEPER")
                            .setConstraints(Constraint.ON_ANY_NETWORK)
                            .setRecurring(true)
                            .setReplaceCurrent(true)
                            .setTrigger(Trigger.executionWindow(3600, 3620))
                            .build()
            );
        }

    }
}
