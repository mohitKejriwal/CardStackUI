package com.mohit.cardstackui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager llm =new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(llm);

        CardAdapter cardAdapter=new CardAdapter(this);
        recyclerView.setAdapter(cardAdapter);
        cardAdapter.notifyDataSetChanged();
    }
}
