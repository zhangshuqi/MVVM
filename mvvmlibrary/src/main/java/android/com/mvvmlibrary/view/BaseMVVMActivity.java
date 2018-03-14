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
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;

import butterknife.ButterKnife;

/**
 * @Author zhangshuqi
 * @CreateTime 2018/3/13
 * @Describe MVVM的基类, Proxy管理 View与ViewModel的生命周期
 */

public abstract class BaseMVVMActivity<V extends BaseMVVMView, VM extends BaseViewModel<V, D>, D extends ViewDataBinding> extends AppCompatActivity implements BaseViewModelProxy<V, VM, D>, ActivityLiftCycle {
    private static final String VIEW_MODEL_SAVE_KEY = "view_model_save_key";

    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private ViewModelProxyImpl<V, VM, D> mProxy = new ViewModelProxyImpl<>(ViewModelFactoryImpl.<V, VM, D>createViewModelFactory(getClass()));
    private D mBinding;


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
        if (getLayoutId() <= 0) {
            throw new NullPointerException("layout Id is null ");
        }
        if (isUseDataBinding()) {
            mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        } else {
            setContentView(getLayoutId());
            ButterKnife.bind(this);
        }
        mProxy.setViewBinding(mBinding);
        initView();
        initData();
        initEvent();
    }

    public boolean isUseDataBinding() {
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        mProxy.onResume((V) this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mProxy.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mProxy.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mProxy.onStart();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(VIEW_MODEL_SAVE_KEY, mProxy.onSaveInstanceState());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mProxy.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mProxy.onKeyDown(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (!mProxy.onBackPressed()) {
            super.onBackPressed();
        }

    }
}
