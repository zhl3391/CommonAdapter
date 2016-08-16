# CommonAdapter
CommonAdapter has two CommonAdapter for LisView, GridView, RecyclerView.

#Usage
##Step 1

####Gradle
```groovy
dependencies {
        compile 'com.zhl.commonadapter:commonadapter:0.2.2'
}
```

##Step 2
create ViewHolder extends BaseViewHolder
create CommonAapter
setAdapter
```java
public class DataViewHolder extends BaseViewHolder<TestData> {

    @Bind(R.id.textView)
    TextView mTextView;

    @Override
    public void bindView(View view) {
        super.bindView(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void updateView(TestData data, int position) {
            mTextView.setText(data.text);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_test;
    }
}

CommonAdapter<TestData> adapter = new CommonAdapter<TestData>(datas) {
        @Override
        public BaseViewHolder<TestData> createViewHolder(int type) {
            return new DataViewHolder();
        }
};

mListView.setAdapter(adapter);

CommonRecyclerAdapter<TestData> adapter = new CommonRecyclerAdapter<TestData>(datas) {
        @Override
        public BaseViewHolder<TestData> createViewHolder(int type) {
            return new DataViewHolder();
        }
};

//you can add header and footer like this.
adapter.addHeader(new HeaderViewHolder(new Header()));
adapter.addFooter(new FooterViewHolder(new Footer()));
//you can also setOnItemClickListener and setOnItemLongClickListener like this.
adapter.setOnItemClickListener(new CommonRecyclerAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Toast.makeText(RecyclerViewActivity.this, "click " + position, Toast.LENGTH_SHORT).show();
        }
});
adapter.setOnItemLongClickListener(new CommonRecyclerAdapter.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(View view, int position) {
            Toast.makeText(RecyclerViewActivity.this, "long click " + position, Toast.LENGTH_SHORT).show();
            return true;
        }
});

mRecyclerView.setAdapter(adapter);
```

如果在你的项目中使用了ButterKnife,那么可以直接在ViewHolder中使用@Bind注解，不需要调用ButterKnife.bind(this, view);
如果没有使用，那么就需要自己findView。
