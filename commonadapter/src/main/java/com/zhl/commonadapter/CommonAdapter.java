package com.zhl.commonadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by zhouhl on 2016/2/22.
 * CommonAdapter
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    private List<T> mDatas;

    public CommonAdapter(){

    }

    public CommonAdapter(List<T> datas) {
        this.mDatas = datas;
    }

    public void setDatas(List<T> datas) {
        if (mDatas != null) {
            mDatas.clear();
            mDatas.addAll(datas);
        } else {
            mDatas = datas;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mDatas == null){
            return 0;
        }else {
            return mDatas.size();
        }
    }

    @Override
    public T getItem(int position) {
        if (position >= 0 && position < mDatas.size()) {
            return mDatas.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder holder;

        if (convertView == null){
            holder = createViewHolder(getItemViewType(position));
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(holder.getLayoutResId(), parent, false);
            holder.bindView(convertView);
            convertView.setTag(holder);
        }else {
            holder = (BaseViewHolder) convertView.getTag();
        }

        holder.updateView(mDatas.get(position), position);

        return convertView;
    }

    public abstract BaseViewHolder<T> createViewHolder(int type);
}
