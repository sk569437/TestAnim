package com.jjws.testanim;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;




import java.util.ArrayList;
import java.util.List;


public class FourActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView mRecyclerview;

    private List<String> mList;
    private MyRecyclerAdapter mAdapter;
    //private PersonTypeAdapter adapter;
    private LinearLayoutManager mLayoutManager;

    private SwipeRefreshLayout refreshLayout;

    private Button mBtnAddOne, mBtnAddBatch, mBtnDelOne, mBtnDelBatch;


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1) {
                loadMore();
                mAdapter.notifyDataSetChanged();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        mRecyclerview = (RecyclerView) findViewById(R.id.recycle);

        mBtnAddOne = (Button) findViewById(R.id.btn_addone);
        mBtnAddBatch = (Button) findViewById(R.id.btn_addbatch);
        mBtnDelOne = (Button) findViewById(R.id.btn_delone);
        mBtnDelBatch = (Button) findViewById(R.id.btn_delbatch);

        mBtnAddOne.setOnClickListener(this);
        mBtnAddBatch.setOnClickListener(this);
        mBtnDelOne.setOnClickListener(this);
        mBtnDelBatch.setOnClickListener(this);


        mList = new ArrayList<>();
        getData();
        mAdapter = new MyRecyclerAdapter(this, mList);

        mRecyclerview.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setLayoutManager(mLayoutManager);

        mRecyclerview.setOnScrollListener(new RecyclerView.OnScrollListener() {

            private int mLastVisibleItem = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastVisibleItem + 1 == mAdapter.getItemCount()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000);
                            }catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            mHandler.sendEmptyMessage(1);
                        }
                    }).start();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //mLastVisibleItem = recyclerView.getLayoutManager().;
                mLastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });

        mAdapter.setRecyclerListener(new onRecycleItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }

            @Override
            public void onItemLongClick(View v, int position) {

            }
        });


        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);

    }

    private void initFooter() {

    }

    private void getData() {
        if(mList == null)
            mList = new ArrayList<>();
        mList.clear();

        for(int i=0;i<15;i++) {
            mList.add("recyclerview item " + (i+1));
        }
    }

    private void loadMore() {
        int total = mList.size();
        for(int i=total;i<10+total;i++) {
            mList.add("recyclerview item " + (i+1));
        }
    }

    private void onAddOne() {

        String one = new String("Add One");
        int pos = 3;
        mAdapter.notifyItemInserted(pos);
        mList.add(pos, one);
        mAdapter.notifyItemChanged(pos);

    }
    private void onAddbatch(){
        String one = new String("Add Batch 1");
        String two = new String("Add Batch 2");
        String three = new String("Add Batch 3");

        int pos = 1;
        mAdapter.notifyItemRangeInserted(pos, 3);
        mList.add(pos, one);
        mList.add(pos, two);
        mList.add(pos, three);
        mAdapter.notifyItemRangeChanged(pos, 3);
    }
    private void onDelOne(){

        int pos = 2;
        mAdapter.notifyItemRemoved(pos);
        mList.remove(pos);
        mAdapter.notifyItemRangeChanged(pos, mAdapter.getItemCount() - pos);
    }
    private void onDelBatch() {
        int pos = 2;
        mAdapter.notifyItemRangeRemoved(pos, 3);
        mList.remove(pos);
        mList.remove(pos);
        mList.remove(pos);
        mAdapter.notifyItemRangeChanged(pos, mAdapter.getItemCount() - pos);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addone:
                onAddOne();
                break;

            case R.id.btn_addbatch:
                onAddbatch();
                break;

            case R.id.btn_delone:
                onDelOne();
                break;

            case R.id.btn_delbatch:
                onDelBatch();
                break;
        }
    }


    private class MyHolder extends RecyclerView.ViewHolder{

        private TextView mTxt;
        private View layout_item;



        public MyHolder(View itemView) {
            super(itemView);
            mTxt = (TextView) itemView.findViewById(R.id.tv_txt);
            layout_item = (View) itemView.findViewById(R.id.layout_item);
        }

    }

    private class  MyRecyclerAdapter extends RecyclerView.Adapter<MyHolder> {
        private Context mContext;
        private List<String> list;
        private onRecycleItemClickListener mListener;

        public MyRecyclerAdapter(Context context, List<String> list) {
            this.mContext = context;
            this.list = list;
        }


        public void setRecyclerListener(onRecycleItemClickListener l) {
            this.mListener = l;
        }


        public Object getItem(int position) {
            return this.list.get(position);
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = (View) LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item_layout, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, final int position) {
            String item = (String) getItem(position);

            if(item != null) {
                holder.mTxt.setText(item);


                holder.layout_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mListener != null) {
                            mListener.onItemClick(v, position);
                        }
                    }
                });

                holder.layout_item.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if(mList != null) {
                            mListener.onItemLongClick(v, position);
                            return true;
                        }
                        return false;
                    }
                });

            }
        }

        @Override
        public int getItemCount() {
            if(this.list == null)
                return 0;

            return this.list.size();
        }
    }

    public interface onRecycleItemClickListener {
        public void onItemClick(View v, int position) ;
        public void onItemLongClick(View v, int position);
    }
}
