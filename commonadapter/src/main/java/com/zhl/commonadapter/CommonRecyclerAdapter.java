package com.zhl.commonadapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhl on 16/2/23.
 * CommonRecyclerAdapter
 */
public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter{

    private static final int DEFAULT_DELAY = 100;

    private static final int TYPE_HEADER = 10;
    private static final int TYPE_FOOTER = 11;
    private static final int TYPE_NORMAL = 12;

    private List<T> mDatas;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    private List<BaseViewHolder> mHeader = new ArrayList<>();
    private List<BaseViewHolder> mFooter = new ArrayList<>();

    private int mClickDelay = DEFAULT_DELAY;

    public CommonRecyclerAdapter(){

    }

    public CommonRecyclerAdapter(List<T> datas){
        mDatas = datas;
    }

    public void setClickDelay(int clickDelay) {
        mClickDelay = clickDelay;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mHeader.size()) {
            return TYPE_HEADER;
        } else if (position < mDatas.size() + mHeader.size()) {
            return TYPE_NORMAL;
        } else {
            return TYPE_FOOTER;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                return new ViewHolder(parent.getContext(), parent, mHeader.get(0));
            case TYPE_NORMAL:
                return new ViewHolder(parent.getContext(), parent, createViewHolder(viewType));
            case TYPE_FOOTER:
                return new ViewHolder(parent.getContext(), parent, mFooter.get(0));
            default:
                return new ViewHolder(parent.getContext(), parent, createViewHolder(viewType));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (position < mHeader.size()) {
            ((ViewHolder)holder).baseViewHolder.updateView(null, position);
        } else if (position < mDatas.size() + mHeader.size()) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        holder.itemView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mOnItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition() - mHeader.size());
                            }
                        }, mClickDelay);
                    }
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mOnItemLongClickListener != null) {
                        mOnItemLongClickListener.onItemLongClick(holder.itemView, holder.getLayoutPosition() - mHeader.size());
                    }
                    return false;
                }
            });
            ((ViewHolder)holder).baseViewHolder.updateView(mDatas.get(position - mHeader.size()),
                    position - mHeader.size());
        } else {
            ((ViewHolder)holder).baseViewHolder.updateView(null, position - mHeader.size() - mDatas.size());
        }
    }

    @Override
    public int getItemCount() {
        if (mDatas == null){
            return 0;
        }

        return mDatas.size() + mHeader.size() + mFooter.size();
    }

    public abstract BaseViewHolder<T> createViewHolder(int type);

    public void setDatas(List<T> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    public int getHeaderSize() {
        return mHeader.size();
    }

    public int getFooterSize() {
        return mFooter.size();
    }

    public void addHeader(BaseViewHolder baseViewHolder) {
        mHeader.add(baseViewHolder);
    }

    public void addFooter(BaseViewHolder baseViewHolder) {
        mFooter.add(baseViewHolder);
    }

    public T getItem(int position){
        if (position >= mHeader.size() && position < (mDatas.size() + mHeader.size())) {
            return mDatas.get(position);
        } else {
            return null;
        }
    }

    public GridLayoutManager.SpanSizeLookup createSpanSizeLookup(final int spanCount) {
        return new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position < mHeader.size()) {
                    return spanCount;
                } else if (position < mDatas.size() + mHeader.size()){
                    return 1;
                } else {
                    return spanCount;
                }
            }
        };
    }

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
