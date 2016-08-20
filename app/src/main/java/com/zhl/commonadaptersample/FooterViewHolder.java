package com.zhl.commonadaptersample;

import android.view.View;
import android.widget.TextView;

import com.zhl.commonadapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhl on 16/6/6.
 */
public class FooterViewHolder extends BaseViewHolder<Footer>{

    @Bind(R.id.textView)
    TextView mTextView;

    private Footer mFooter;

    public FooterViewHolder(Footer footer) {
        mFooter = footer;
    }

    @Override
    public void bindView(View view) {
        super.bindView(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void updateView(Footer data, int position) {
        mTextView.setText(mFooter.text);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_test;
    }
}
