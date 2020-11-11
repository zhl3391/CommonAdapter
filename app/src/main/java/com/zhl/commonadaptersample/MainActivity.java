package com.zhl.commonadaptersample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_list_view).setOnClickListener(this);
        findViewById(R.id.btn_recycler_view).setOnClickListener(this);
        findViewById(R.id.btn_recycler_view_grid).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_list_view:
                startActivity(new Intent(this, ListViewActivity.class));
                break;
            case R.id.btn_recycler_view:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case R.id.btn_recycler_view_grid:
                startActivity(RecyclerViewActivity.createIntent(this, true));
                break;
        }
    }
}
