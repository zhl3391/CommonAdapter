package com.zhl.commonadaptersample;

import android.view.View;

import com.zhl.commonadapter.BaseViewHolder;
import com.zhl.commonadaptersample.databinding.ItemTestBinding;

/**
 * Created by zhouhl on 2016/6/3.
 * DataViewHolder
 */
public class DataViewHolder extends BaseViewHolder<TestData> {

    public ItemTestBinding mBinding;

    @Override
    public void updateView(TestData data, int position) {
        if (data == null) {
            System.out.println(position + "--");
        } else {
            mBinding.setData(data);
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_test;
    }

    @Override
    public void findView(View view) {
        mBinding = ItemTestBinding.bind(view);
    }
}
