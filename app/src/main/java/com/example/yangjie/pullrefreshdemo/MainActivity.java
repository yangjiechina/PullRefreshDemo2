package com.example.yangjie.pullrefreshdemo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CustomCircle.RefreshCallBack{

    private RecyclerView mRecyHome = null;
    private CustomCircle reFreshView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyHome = (RecyclerView) findViewById(R.id.recy_home);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyHome.setLayoutManager(layoutManager);
        mRecyHome.setAdapter(new HomeAdapter());
        reFreshView = (CustomCircle) findViewById(R.id.refreshView);
        reFreshView.setRefreshCallBack(this);
    }

    @Override
    public void upRefresh(Scroller scroller,int y) {
        scroller.startScroll(0, y, 0, -100);
        reFreshView.invalidate();
    }

    @Override
    public void downLoad(Scroller scroller,int y) {
        scroller.startScroll(0, y, 0, 100);
        reFreshView.invalidate();
    }

    class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_bear,parent,false);
            return new HomeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((HomeViewHolder) holder).tv_name.setText("熊熊"+position);
            ((HomeViewHolder) holder).iv_picture.setBackgroundResource(R.drawable.timg2);
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        class HomeViewHolder extends RecyclerView.ViewHolder {

            public TextView tv_name = null;
            public ImageView iv_picture = null;
            public HomeViewHolder(View itemView) {
                super(itemView);
                tv_name = (TextView) itemView.findViewById(R.id.tv_name);
                iv_picture = (ImageView) itemView.findViewById(R.id.iv_picture);
            }
        }
    }

}
