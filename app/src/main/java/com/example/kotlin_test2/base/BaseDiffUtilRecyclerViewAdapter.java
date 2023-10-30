package com.example.kotlin_test2.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public abstract class BaseDiffUtilRecyclerViewAdapter<T, VDB extends ViewDataBinding> extends ListAdapter<T, BindingViewHolder<VDB>> {
    protected Context mContext;

    public BaseDiffUtilRecyclerViewAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback, Context context) {
        super(diffCallback);
        mContext = context;
    }

    protected abstract int getRecyclerViewItemID(int viewType);

    protected abstract void onBindVH(BindingViewHolder holder, int position);

    @NonNull
    @Override
    public BindingViewHolder<VDB> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getRecyclerViewItemID(viewType), parent, false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<VDB> holder, int position) {
        if (position < getCurrentList().size()) {
            T data = getCurrentList().get(position);
            onBindVH(holder, position);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClickListener(data, holder.getBindingAdapterPosition());
                    }
                }
            });

        }
        holder.getBinding().executePendingBindings();
    }

    public void setOnItemClickListener(BaseRecyclerViewAdapter_02.OnItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        public void onItemClickListener(Object data, int position);
    }

    private BaseRecyclerViewAdapter_02.OnItemClickListener mListener; //item的点击监听器
}
