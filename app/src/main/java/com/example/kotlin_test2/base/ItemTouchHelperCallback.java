package com.example.kotlin_test2.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class ItemTouchHelperCallback<T> extends ItemTouchHelper.Callback {
    List<T> mList;
    RecyclerView.Adapter recyclerAdapter;

    public ItemTouchHelperCallback(RecyclerView.Adapter recyclerAdapter, List<T> list) {
        this.recyclerAdapter = recyclerAdapter;
        mList = list;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        //监控上下左右
        int swipFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int dragflag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(dragflag, swipFlag);
    }

    //移动的处理
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        recyclerAdapter.notifyItemMoved(viewHolder.getBindingAdapterPosition(), target.getBindingAdapterPosition());
        Collections.swap(mList, viewHolder.getBindingAdapterPosition(), target.getBindingAdapterPosition());
        if (itemHelperListener != null)
            itemHelperListener.onSwap(viewHolder.getBindingAdapterPosition(), target.getBindingAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        //主要是做左右拖动的回调
        if (itemHelperListener != null)
            itemHelperListener.onRemove(viewHolder.getBindingAdapterPosition());
        recyclerAdapter.notifyItemRemoved(viewHolder.getBindingAdapterPosition());
    }

    @Override
    public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder current, RecyclerView.ViewHolder target) {
        //当前item可以被拖动到目标位置后,直接落到target上,后面的item也接着落
        return true;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        //是否开启长按拖动
        //返回true  可以实现长按拖动排序和拖动动画
        return true;
    }

    ItemHelperListener itemHelperListener;

    public void setOnItemHelperListener(ItemHelperListener itemHelperListener) {
        this.itemHelperListener = itemHelperListener;
    }

    public interface ItemHelperListener {
        /**
         * 删除时的回调
         */
        void onRemove(int position);

        /**
         * 交换时的回调
         */
        void onSwap(int formPosition, int toPosition);
    }
}
