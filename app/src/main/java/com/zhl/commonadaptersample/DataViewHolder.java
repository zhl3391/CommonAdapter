package com.zhl.commonadaptersample;

import android.view.View;
import android.widget.TextView;

import com.zhl.commonadapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhouhl on 2016/6/3.
 * DataViewHolder
 */
public class DataViewHolder extends BaseViewHolder<TestData> {

    @Bind(R.id.textView)
    TextView mTextView;

    @Override
    public void findView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void updateView(TestData data, int position) {
        if (data == null) {
            System.out.println(position + "--");
        } else {
            mTextView.setText(data.text);
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_test;
    }
}
