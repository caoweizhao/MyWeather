package com.example.administrator.weather.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.weather.AddressWrapper;
import com.example.administrator.weather.R;
import com.example.administrator.weather.mvp.model.locationbean.IAddress;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-3-28.
 */

public class MyAdapter<T extends IAddress> extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<T> mList = new ArrayList<>();

    public MyAdapter(List<T> list) {
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false);
        if(viewType == 1){
            view.setVisibility(View.GONE);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position < mList.size()) {
            final int p = position;
            IAddress iAddress = mList.get(position);
            holder.mTextView.setText(iAddress.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new AddressWrapper(mList.get(p)));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position < mList.size() ? 0 : 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
