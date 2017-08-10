package com.zhl.commonadapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhl on 16/2/23.
 * CommonRecyclerAdapter
 */
public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter{

    private static final int DEFAULT_DELAY = 100;

    private static final int MAX_HEADER_FOOTER_COUNT = 10;

    private static final int TYPE_HEADER = 100;
    private static final int TYPE_FOOTER = TYPE_HEADER + MAX_HEADER_FOOTER_COUNT;
    private static final int TYPE_NORMAL = TYPE_FOOTER + MAX_HEADER_FOOTER_COUNT;

    private List<T> mDatas;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    private List<BaseViewHolder> mHeaders = new ArrayList<>();
    private List<BaseViewHolder> mFooters = new ArrayList<>();

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
        if (position < mHeaders.size()) {
            return TYPE_HEADER + position;
        } else if (position < mDatas.size() + mHeaders.size()) {
            return TYPE_NORMAL;
        } else {
            return TYPE_FOOTER + position - mHeaders.size() - mDatas.size();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder;
        if (viewType >= TYPE_HEADER && viewType < TYPE_HEADER + MAX_HEADER_FOOTER_COUNT) {
            baseViewHolder = mHeaders.get(viewType - TYPE_HEADER);
            return new ViewHolder(parent, baseViewHolder,
                    baseViewHolder.getDataBindingRoot(parent.getContext(), parent));
        } else if (viewType >= TYPE_FOOTER && viewType < TYPE_FOOTER + MAX_HEADER_FOOTER_COUNT) {
            baseViewHolder = mFooters.get(viewType - TYPE_FOOTER);
            return new ViewHolder(parent, baseViewHolder,
                    baseViewHolder.getDataBindingRoot(parent.getContext(), parent));
        } else {
            baseViewHolder = createViewHolder(viewType);
            return new ViewHolder(parent, baseViewHolder,
                    baseViewHolder.getDataBindingRoot(parent.getContext(), parent));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (position < mHeaders.size()) {
            ((ViewHolder)holder).baseViewHolder.updateView(null, position);
        } else if (position < mDatas.size() + mHeaders.size()) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        holder.itemView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                int realPosition = holder.getAdapterPosition();
                                if (realPosition >= 0) {
                                    mOnItemClickListener.onItemClick(holder.itemView, realPosition - mHeaders.size());
                                }
                            }
                        }, mClickDelay);
                    }
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mOnItemLongClickListener != null) {
                        int realPosition = holder.getAdapterPosition();
                        if (realPosition >= 0) {
                            mOnItemLongClickListener.onItemLongClick(holder.itemView, realPosition - mHeaders.size());
                        }
                    }
                    return false;
                }
            });
            ((ViewHolder)holder).baseViewHolder.updateView(mDatas.get(position - mHeaders.size()),
                    position - mHeaders.size());
        } else {
            ((ViewHolder)holder).baseViewHolder.updateView(null, position - mHeaders.size() - mDatas.size());
        }
    }

    @Override
    public int getItemCount() {
        if (mDatas == null){
            return 0;
        }

        return mDatas.size() + mHeaders.size() + mFooters.size();
    }

    public abstract BaseViewHolder<T> createViewHolder(int type);

    public void setDatas(List<T> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    public int getHeaderSize() {
        return mHeaders.size();
    }

    public int getFooterSize() {
        return mFooters.size();
    }

    public void addHeader(BaseViewHolder baseViewHolder) {
        if (mHeaders.size() < MAX_HEADER_FOOTER_COUNT) {
            mHeaders.add(baseViewHolder);
        }
    }

    public void addFooter(BaseViewHolder baseViewHolder) {
        if (mFooters.size() < MAX_HEADER_FOOTER_COUNT) {
            mFooters.add(baseViewHolder);
        }
    }

    public void removeHeader(BaseViewHolder baseViewHolder) {
        mHeaders.remove(baseViewHolder);
    }

    public void removeFooter(BaseViewHolder baseViewHolder) {
        mFooters.remove(baseViewHolder);
    }

    public T getItem(int position){
        if (position >= 0 && position < mDatas.size()) {
            return mDatas.get(position);
        } else {
            throw new IndexOutOfBoundsException("data size = " + mDatas.size() + " position = " + position);
        }
    }

    public GridLayoutManager.SpanSizeLookup createSpanSizeLookup(final int spanCount) {
        return new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position < mHeaders.size()) {
                    return spanCount;
                } else if (position < mDatas.size() + mHeaders.size()){
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

        public ViewHolder(ViewGroup parent, BaseViewHolder baseViewHolder, View root){
            super(root != null ? root : LayoutInflater.from(parent.getContext()).inflate(baseViewHolder.getLayoutResId(), parent, false));
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
