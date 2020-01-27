package com.example.price_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);

        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
    }
    public void onClick(View view){
        Intent intent;
        if(view.getId()== R.id.btn1){
            intent= new Intent(this, Taskone.class);
        }else if(view.getId()== R.id.btn2){
            intent= new Intent(this, Tasktwo.class);
        }else{
            intent= new Intent(this, Taskthree.class);
        }
        startActivity(intent);

    }
}
