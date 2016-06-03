package com.zhl.commonadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhl on 16/2/23.
 * CommonRecyclerAdapter
 */
public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter{

    private List<T> mDatas;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public CommonRecyclerAdapter(List<T> datas){
        this.mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent.getContext(), parent, createViewHolder(viewType));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(holder.itemView, holder.getAdapterPosition());
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null){
                    mOnItemLongClickListener.onItemLongClick(holder.itemView, holder.getAdapterPosition());
                }
                return false;
            }
        });
        ((ViewHolder)holder).baseViewHolder.updateView(mDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (mDatas == null){
            return 0;
        }

        return mDatas.size();
    }

    public T getItem(int position){
        return mDatas.get(position);
    }

    public abstract BaseViewHolder<T> createViewHolder(int type);

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    private static class ViewHolder extends RecyclerView.ViewHolder{

        public BaseViewHolder baseViewHolder;

        public ViewHolder(Context context, ViewGroup parent, BaseViewHolder baseViewHolder){
            super(LayoutInflater.from(context).inflate(baseViewHolder.getLayoutResId(), parent, false));
            this.baseViewHolder = baseViewHolder;
            this.baseViewHolder.bindView(itemView);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener{
        boolean onItemLongClick(View view, int position);
    }

}
