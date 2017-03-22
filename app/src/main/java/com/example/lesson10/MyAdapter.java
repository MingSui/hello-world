package com.example.lesson10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/3/19 0019.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    String[] mDataSet;
    PopupWindow mPopupWindow;
    Context mContext;

    public MyAdapter(Context mContext,String[] myDataSet){
        this.mContext = mContext;
        this.mDataSet = myDataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1,parent,false);
        ViewHolder vh = new ViewHolder((TextView) v);
        vh.mTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                View contentView = View.inflate(mContext,R.layout.popup_view,null);
                mPopupWindow = new PopupWindow(contentView,200,200,true);
                mPopupWindow.setTouchable(true);
                mPopupWindow.setOutsideTouchable(true);
                mPopupWindow.setBackgroundDrawable(new BitmapDrawable(mContext.getResources(), (Bitmap) null));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    mPopupWindow.showAsDropDown(v,mContext.getResources().getDisplayMetrics().widthPixels/2,-200);
                } else {
                    mPopupWindow.showAsDropDown(v);
                }
                return true;
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataSet[position]);
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
