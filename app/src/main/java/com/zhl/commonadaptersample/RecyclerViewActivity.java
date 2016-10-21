package com.zhl.commonadaptersample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.zhl.commonadapter.BaseViewHolder;
import com.zhl.commonadapter.CommonRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhouhl on 2016/6/3.
 * RecyclerViewActivity
 */
public class RecyclerViewActivity extends AppCompatActivity {

    private static final String KEY_IS_GRID = "is_grid";

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public static Intent createIntent(Context context, boolean isGrid) {
        Intent intent = new Intent(context, RecyclerViewActivity.class);
        intent.putExtra(KEY_IS_GRID, isGrid);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);

        boolean isGrid = getIntent().getBooleanExtra(KEY_IS_GRID, false);

        List<TestData> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            TestData testData = new TestData();
            testData.text = "test" + i;
            datas.add(testData);
        }

        final CommonRecyclerAdapter<TestData> adapter = new CommonRecyclerAdapter<TestData>(datas) {
            @Override
            public BaseViewHolder<TestData> createViewHolder(int type) {
                return new DataViewHolder();
            }
        };

        adapter.addHeader(new HeaderViewHolder(new Header()));
        adapter.addHeader(new HeaderViewHolder(new Header()));

        adapter.addFooter(new FooterViewHolder(new Footer()));
        adapter.addFooter(new FooterViewHolder(new Footer()));

        adapter.setOnItemClickListener(new CommonRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(createIntent(RecyclerViewActivity.this, false));
                finish();
            }
        });

        adapter.setOnItemLongClickListener(new CommonRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int position) {
                Toast.makeText(RecyclerViewActivity.this, "long click " + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        if (isGrid) {
            int spanCount = 2;
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount);
            gridLayoutManager.setSpanSizeLookup(adapter.createSpanSizeLookup(spanCount));
            mRecyclerView.setLayoutManager(gridLayoutManager);
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(linearLayoutManager);
        }
        mRecyclerView.setAdapter(adapter);
    }
}
