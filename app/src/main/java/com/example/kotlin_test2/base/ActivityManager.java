package com.example.kotlin_test2.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity管理
 * @author llw
 */
public class ActivityManager {

    /**
     * 保存所有创建的Activity
     */
    private final List<Activity> activityList = new ArrayList<>();

    public static ActivityManager mInstance;

    public static ActivityManager getInstance() {
        if (mInstance == null) {
            synchronized (ActivityManager.class) {
                if (mInstance == null) {
                    mInstance = new ActivityManager();
                }
            }
        }
        return mInstance;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    /**
     * 添加Activity
     * @param activity
     */
    public void addActivity(Activity activity){
        if(activity != null){
            activityList.add(activity);
        }
    }

    /**
     * 移除Activity
     * @param activity
     */
    public void removeActivity(Activity activity){
        if(activity != null){
            activityList.remove(activity);
        }
    }

    /**
     * 关闭所有Activity
     */
    public void finishAllActivity(){
        for (Activity activity : activityList) {
            activity.finish();
        }
    }
}