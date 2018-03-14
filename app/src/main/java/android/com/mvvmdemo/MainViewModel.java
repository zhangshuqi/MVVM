package android.com.mvvmdemo;

import android.com.mvvmdemo.databinding.ActivityMainBinding;
import android.com.mvvmlibrary.view.BaseMVVMView;
import android.com.mvvmlibrary.viewmodel.BaseViewModel;
import android.databinding.ViewDataBinding;

/**
 * @Author zhangshuqi
 * @CreateTime 2018/3/13
 * @Describe
 */

public class MainViewModel extends BaseViewModel<MainViewView,ActivityMainBinding>{
    @Override
    public void onAttachView(MainViewView view) {
        super.onAttachView(view);
        mBinding.tv.setText("1111");
    }
}
