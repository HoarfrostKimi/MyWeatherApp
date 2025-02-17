package com.example.myweatherapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherapp.R;

import java.util.Collections;
import java.util.List;

public class ItemTouchHelperAdapter extends RecyclerView.Adapter<ItemTouchHelperAdapter.MyViewHolder> implements ItemTouchHelper {

    private List<WeatherItem> dataList;
    private OnItemClickListener onItemClickListener;

    // 自定义的 OnItemClickListener 接口
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public ItemTouchHelperAdapter(List<WeatherItem> dataList) {
        this.dataList = dataList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choice_rv, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        WeatherItem item = dataList.get(position);
        holder.textView.setText(item.getCityName());
        holder.textView2.setText(item.getWeatherInfo());
        holder.textView3.setText(item.getTemperature());
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // 自定义的 ViewHolder 类
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        TextView textView3;

        MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
        }
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        // 交换位置
        Collections.swap(dataList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDissmiss(int position) {
        // 移除数据
        dataList.remove(position);
        notifyItemRemoved(position);
    }
}
