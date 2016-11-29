package com.zhl.commonadaptersample;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    public View getDataBindingRoot(Context context, ViewGroup parent) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), getLayoutResId(), parent, false);
        return mBinding.getRoot();
    }

    @Override
    public void findView(View view) {

    }
}
