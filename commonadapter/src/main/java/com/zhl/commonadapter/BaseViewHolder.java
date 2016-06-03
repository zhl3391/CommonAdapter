package com.zhl.commonadapter;

import android.content.Context;
import android.view.View;

/**
 * Created by zhouhl on 2016/2/22.
 * BaseViewHolder
 */
public abstract class BaseViewHolder<T> {

    public Context mContext;

    public void bindView(View view){
        mContext = view.getContext();
    }

    public abstract void updateView(T data, int position);

    public abstract int getLayoutResId();

}
