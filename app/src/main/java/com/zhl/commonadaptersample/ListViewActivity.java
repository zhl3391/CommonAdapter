package com.zhl.commonadaptersample;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.zhl.commonadapter.BaseViewHolder;
import com.zhl.commonadapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouhl on 2016/6/3.
 * ListViewActivity
 */
public class ListViewActivity extends AppCompatActivity {

    private CommonAdapter<TestData> mAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        mListView = findViewById(R.id.listView);

        final List<TestData> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            TestData testData = new TestData("test" + i);
            datas.add(testData);
        }

        mAdapter = new CommonAdapter<TestData>(datas) {
            @Override
            public BaseViewHolder<TestData> createViewHolder(int type) {
                return new DataViewHolder();
            }
        };

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(ListViewActivity.this, ListViewActivity.class));
                finish();
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, "long click " + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        mListView.setAdapter(mAdapter);
    }
}
