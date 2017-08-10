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

        final List<TestData> datas = new ArrayList<>();
        for (int i = 0; i < 40; i++){
            TestData testData = new TestData("test" + i);
            datas.add(testData);
        }

        final CommonRecyclerAdapter<TestData> adapter = new CommonRecyclerAdapter<TestData>(datas) {
            @Override
            public BaseViewHolder<TestData> createViewHolder(int type) {
                return new DataViewHolder();
            }
        };

        adapter.addHeader(new HeadFootViewHolder(new TestData("head1")));
        adapter.addHeader(new HeadFootViewHolder(new TestData("head2")));
        adapter.addHeader(new HeadFootViewHolder(new TestData("head3")));
        adapter.addHeader(new HeadFootViewHolder(new TestData("head4")));
        adapter.addHeader(new HeadFootViewHolder(new TestData("head5")));
        adapter.addHeader(new HeadFootViewHolder(new TestData("head6")));
        adapter.addHeader(new HeadFootViewHolder(new TestData("head7")));
        adapter.addHeader(new HeadFootViewHolder(new TestData("head8")));
        adapter.addHeader(new HeadFootViewHolder(new TestData("head9")));
        adapter.addHeader(new HeadFootViewHolder(new TestData("head10")));
        adapter.addHeader(new HeadFootViewHolder(new TestData("head11")));

        adapter.addFooter(new HeadFootViewHolder(new TestData("foot1")));
        adapter.addFooter(new HeadFootViewHolder(new TestData("foot2")));
        adapter.addFooter(new HeadFootViewHolder(new TestData("foot3")));
        adapter.addFooter(new HeadFootViewHolder(new TestData("foot4")));
        adapter.addFooter(new HeadFootViewHolder(new TestData("foot5")));
        adapter.addFooter(new HeadFootViewHolder(new TestData("foot6")));
        adapter.addFooter(new HeadFootViewHolder(new TestData("foot7")));

        adapter.setOnItemClickListener(new CommonRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                startActivity(createIntent(RecyclerViewActivity.this, false));
//                finish();
                Toast.makeText(RecyclerViewActivity.this, adapter.getItem(position).text, Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnItemLongClickListener(new CommonRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int position) {
                datas.remove(position);
                adapter.notifyItemRemoved(position + adapter.getHeaderSize());
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
