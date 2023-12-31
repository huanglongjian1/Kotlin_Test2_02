package com.example.kotlin_test2.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;



//继承AndroidViewModel，是因为里面要用context时候直接可以getApplication()
public class BaseViewModel extends AndroidViewModel {
    // 加载状态
    protected Application application;
    public MutableLiveData<String> fail = new MutableLiveData<>();
    public BaseViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    @Override
    protected void onCleared() {
        super.onCleared();

    }

    public MutableLiveData<String> getFail() {
        return fail;
    }
}
