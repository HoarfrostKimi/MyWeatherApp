package com.example.myweatherapp.Adapter;

public interface ItemTouchHelper {
    //数据交换
    void onItemMove(int fromPosition,int toPosition);
    //数据删除
    void onItemDissmiss(int position);
}
