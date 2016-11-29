package com.zhl.commonadaptersample;

/**
 * Created by zhouhl on 2016/11/29.
 * HeadFootViewHolder
 */

public class HeadFootViewHolder extends DataViewHolder {

    private TestData mData;

    public HeadFootViewHolder(TestData testData) {
        mData = testData;
    }

    @Override
    public void updateView(TestData data, int position) {
        mBinding.setData(mData);
    }
}
