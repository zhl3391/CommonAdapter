package com.zhl.commonadaptersample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_list_view, R.id.btn_recycler_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_list_view:
                startActivity(new Intent(this, ListViewActivity.class));
                break;
            case R.id.btn_recycler_view:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
        }
    }
}
