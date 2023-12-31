package com.example.kotlin_test2.base.fragment_base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.example.kotlin_test2.util.Loge;


public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {
    protected T binding;
    protected View root_view;

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    private boolean isFirstLoad = true; // 是否第一次加载

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        root_view = binding.getRoot();


        return root_view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    /**
     *
     * @param msg 生成提示框
     */
    protected void showMsg(CharSequence msg) {
        // Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        Loge.e(msg.toString() + ":::toast");
    }

    public BaseFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            onDataLazyLoad();
            isFirstLoad = false;
        }
        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.getString(this.getClass().getSimpleName(), "请赐我新名字");
            Loge.e(name);
            requireActivity().setTitle(name);
        }
    }

    protected void onDataLazyLoad() {
    }


}
