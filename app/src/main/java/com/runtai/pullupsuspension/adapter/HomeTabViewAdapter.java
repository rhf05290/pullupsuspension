package com.runtai.pullupsuspension.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.runtai.pullupsuspension.R;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */
public class HomeTabViewAdapter extends RecyclerView.Adapter<HomeTabViewAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private List<String> mDatas;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

//        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public HomeTabViewAdapter(Context context, List<String> data) {
        mInflater = LayoutInflater.from(context);
        mDatas = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                R.layout.project_tab_list_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tvProjectName.setText("好的");
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvProjectName;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvProjectName = (TextView) itemView.findViewById(R.id.tvProjectName);
        }
    }

}
