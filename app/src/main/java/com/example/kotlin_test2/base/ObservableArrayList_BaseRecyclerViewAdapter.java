package com.example.kotlin_test2.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class ObservableArrayList_BaseRecyclerViewAdapter<T, VDB extends ViewDataBinding> extends RecyclerView.Adapter<BindingViewHolder<VDB>> {
    private LayoutInflater layoutInflater;
    private OnItemClickListener mListener; //item的点击监听器
    protected ObservableArrayList<T> data_list;

    public ObservableArrayList<T> getList() {
        return data_list;
    }

    public void delete(int position) {
        data_list.remove(position);
        //  notifyItemRangeRemoved(position,1);
    }


    public void add(T data) {
        data_list.add(data);
    }

    public void reset(int position, T data) {
        data_list.set(position, data);
    }

    public void add(int position, T data) {
        data_list.add(position, data);
    }

    public void clear() {
        data_list.clear();
    }

    public void setData_list(ObservableArrayList<T> data_list) {
        this.data_list = data_list;
        this.data_list.addOnListChangedCallback(ListFactory.getListChangedCallback(this));
    }

    public interface OnItemClickListener {
        public void onItemClickListener(Object data, int position);
    }


    public ObservableArrayList_BaseRecyclerViewAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data_list = new ObservableArrayList<>();
        data_list.addOnListChangedCallback(ListFactory.getListChangedCallback(this));
    }

    protected abstract int getRecyclerViewItemID();

    protected abstract void onBindVH(BindingViewHolder holder, int position);

    @NonNull
    @Override
    public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, getRecyclerViewItemID(), parent, false);
        return new BindingViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder holder, int position) {
        if (position < data_list.size()) {
            T data = data_list.get(position);
            onBindVH(holder, position);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClickListener(data, holder.getAdapterPosition());
                    }
                }
            });

        }
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return data_list != null ? data_list.size() : 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public void addAll(ObservableArrayList<T> datas) {
        //  data_list.clear();
        data_list.addAll(datas);
        //notifyDataSetChanged();
    }
}
