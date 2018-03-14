package android.com.mvvmlibrary.view;

import android.com.mvvmlibrary.ActivityLiftCycle;
import android.com.mvvmlibrary.factory.ViewModelFactory;
import android.com.mvvmlibrary.factory.ViewModelFactoryImpl;
import android.com.mvvmlibrary.proxy.BaseViewModelProxy;
import android.com.mvvmlibrary.proxy.ViewModelProxyImpl;
import android.com.mvvmlibrary.viewmodel.BaseViewModel;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * @Author zhangshuqi
 * @CreateTime 2018/3/13
 * @Describe MVVM的Fragment基类, Proxy管理 View与ViewModel的生命周期
 */

public abstract class BaseMVVMFragment<V extends BaseMVVMView, VM extends BaseViewModel<V, D>, D extends ViewDataBinding> extends Fragment implements BaseViewModelProxy<V, VM, D>, ActivityLiftCycle {
    private static final String VIEW_MODEL_SAVE_KEY = "view_model_save_key";

    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private ViewModelProxyImpl<V, VM, D> mProxy = new ViewModelProxyImpl<>(ViewModelFactoryImpl.<V, VM, D>createViewModelFactory(getClass()));
    private View mRootView;


    @Override
    public void setViewModelFactory(ViewModelFactory<V, VM, D> factory) {
        mProxy.setViewModelFactory(factory);
    }

    @Override
    public ViewModelFactory<V, VM, D> getViewModelFactory() {
        return mProxy.getViewModelFactory();
    }

    @Override
    public VM getViewModel() {
        return mProxy.getViewModel();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) mProxy.onCreateSavedInstanceState(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutId() <= 0) throw new NullPointerException("layout is null ");
        mRootView = inflater.inflate(getLayoutId(), container, false);
        if (isUseDataBinding()) {
            DataBindingUtil.bind(mRootView);
        } else {
            ButterKnife.bind(this, mRootView);
        }
        initView();
        initData();
        initEvent();
        return mRootView;
    }

    private boolean isUseDataBinding() {
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        mProxy.onResume((V) this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mProxy.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mProxy.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        mProxy.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(VIEW_MODEL_SAVE_KEY, mProxy.onSaveInstanceState());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mProxy.onActivityResult(requestCode, resultCode, data);
    }

}
