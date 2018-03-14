package android.com.mvvmlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Author zhangshuqi
 * @CreateTime 2018/3/13
 * @Describe
 */

public interface ActivityLiftCycle {
     int getLayoutId();
    void initView();
    void initData();
    void initEvent();
}
