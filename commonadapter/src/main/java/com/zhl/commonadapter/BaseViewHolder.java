package com.zhl.commonadapter;

import android.content.Context;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by zhouhl on 2016/2/22.
 * BaseViewHolder
 */
public abstract class BaseViewHolder<T> {

    protected Context mContext;

    public void bindView(View view){
        mContext = view.getContext();
        if (ButterKnifeJudgement.IS_SUPPORT) {
            ButterKnife.bind(this, view);
        }
    }

    public abstract void updateView(T data, int position);

    public abstract int getLayoutResId();

}
