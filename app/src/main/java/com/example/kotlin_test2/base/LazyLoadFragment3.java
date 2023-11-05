package com.example.kotlin_test2.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * setMaxLifecycle()方式懒加载
 */
public abstract class LazyLoadFragment3 extends Fragment {

    protected Context mContext;

    protected View mRootView;

    private boolean mIsFirstLoad = true; // 是否第一次加载


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    //在Fragment每次变为可见时都会执行onResume()方法，我们可以利用这一点来实现懒加载
    @Override
    public void onResume() {
        super.onResume();
        if (mIsFirstLoad) {
            initData();
            mIsFirstLoad = false;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutID(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mIsFirstLoad = true;
    }

    protected abstract int getLayoutID();

    protected abstract void init();

    protected abstract void initData();
}

