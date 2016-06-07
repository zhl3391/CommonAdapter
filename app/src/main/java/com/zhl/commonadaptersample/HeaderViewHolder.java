package com.zhl.commonadaptersample;

import android.view.View;
import android.widget.TextView;

import com.zhl.commonadapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhl on 16/6/6.
 */
public class HeaderViewHolder extends BaseViewHolder<Header>{

    @Bind(R.id.textView)
    TextView mTextView;

    private Header mHeader;

    public HeaderViewHolder(Header header) {
        mHeader = header;
    }

    @Override
    public void bindView(View view) {
        super.bindView(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void updateView(Header data, int position) {
        mTextView.setText(mHeader.text);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_test;
    }
}
