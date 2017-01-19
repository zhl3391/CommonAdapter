package com.zhl.commonadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhouhl on 2016/2/22.
 * BaseViewHolder
 */
public abstract class BaseViewHolder<T> {

    protected Context mContext;
    protected View mItemView;

    public void bindView(View view){
        mItemView = view;
        mContext = view.getContext();
        findView(view);
    }

    public View getDataBindingRoot(Context context, ViewGroup parent) {
        return null;
    }

    public abstract void findView(View view);

    public abstract void updateView(T data, int position);

    public abstract int getLayoutResId();

    public View getItemView() {
        return mItemView;
    }

}
