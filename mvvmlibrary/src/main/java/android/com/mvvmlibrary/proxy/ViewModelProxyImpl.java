package android.com.mvvmlibrary.proxy;

import android.com.mvvmlibrary.UILifeCycle;
import android.com.mvvmlibrary.factory.ViewModelFactory;
import android.com.mvvmlibrary.view.BaseMVVMView;
import android.com.mvvmlibrary.viewmodel.BaseViewModel;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

/**
 * ViewModel 的具体代理类,代理ViewModel 必须实现的方法(生命周期)
 */

public class ViewModelProxyImpl<V extends BaseMVVMView, VM extends BaseViewModel<V, D>, D extends ViewDataBinding> implements BaseViewModelProxy<V, VM, D>, UILifeCycle<V,D> {
    private ViewModelFactory<V, VM, D> mViewModelFactory;
    private VM mViewModel;
    private boolean mIsAttchView;
    /**
     * 获取onSaveInstanceState中bundle的key
     */
    private static final String VIEW_MODEL_KEY = "ViewModel_key";
    private Bundle mBundle;
    private D mBinding;

    /**
     * 设置工厂必须要在getViewModel之前调用, 如果ViewModel已经创建过了则不能再次创建
     */
    @Override
    public void setViewModelFactory(ViewModelFactory<V, VM, D> factory) {
        if (mViewModel != null) {
            throw new IllegalArgumentException("设置工厂必须要在getViewModel之前调用, 如果ViewModel已经创建过了则不能再次创建");
        }
        this.mViewModelFactory = factory;
    }

    public ViewModelProxyImpl(ViewModelFactory<V, VM, D> presenterMvpFactory) {
        this.mViewModelFactory = presenterMvpFactory;
    }

    @Override
    public ViewModelFactory<V, VM, D> getViewModelFactory() {
        return mViewModelFactory;
    }

    /**
     * 获取创建的ViewModel
     *
     * @return ViewModel
     * 如果之前创建过，而且是以外销毁则从Bundle中恢复
     */
    @Override
    public VM getViewModel() {
        if (mViewModelFactory != null) {
            if (mViewModel == null) {
                mViewModel = mViewModelFactory.createViewModel();
                mViewModel.onCreateViewModel(mBundle == null ? null : mBundle.getBundle(VIEW_MODEL_KEY));
                mViewModel.setViewBinding(mBinding);
            }
        }
        return mViewModel;
    }
    @Override
    public void setViewBinding(D binding) {
        this.mBinding =binding;

    }

    /**
     * 销毁ViewModel持有的View
     */
    private void onDetachMvpView() {
        Log.e("perfect-mvp", "Proxy onDetachMvpView = ");
        if (mViewModel != null && mIsAttchView) {
            mViewModel.onDetachView();
            mIsAttchView = false;
        }
    }


    /**
     * 意外销毁的时候调用
     *
     * @return Bundle，存入回调给ViewModel的Bundle和当前ViewModel的id
     */
    public Bundle onSaveInstanceState() {
        Log.e("viewModel", "Proxy onSaveInstanceState = ");
        Bundle bundle = new Bundle();
        getViewModel();
        if (mViewModel != null) {
            Bundle viewModelBundle = new Bundle();
            //回调ViewModel
            mViewModel.onSaveInstanceState(viewModelBundle);
            bundle.putBundle(VIEW_MODEL_KEY, viewModelBundle);
        }
        return bundle;
    }

    /**
     * 绑定ViewModel和view
     *
     * @param mvpView
     */
    @Override
    public void onResume(V mvpView) {
        getViewModel();
        Log.e("viewModel", "Proxy onResume");
        if (mViewModel != null && !mIsAttchView) {
            mViewModel.onAttachView(mvpView);
            mIsAttchView = true;
        }

        mViewModel.onResume(mvpView);
    }


    @Override
    public Boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void onStart() {
        getViewModel();
        mViewModel.onStart();
    }

    @Override
    public void onPause() {
        mViewModel.onPause();

    }

    /**
     * 销毁ViewModel
     */
    @Override
    public void onDestroy() {
        Log.e("viewModel", "Proxy onDestroy = ");
        if (mViewModel != null) {
            onDetachMvpView();
            mViewModel.onDestroyViewModel();
            mViewModel = null;
        }
        mViewModel.onDestroy();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
    }

    @Override
    public Boolean onBackPressed() {
        return mViewModel.onBackPressed();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mViewModel.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 意外关闭恢复ViewModel
     *
     * @param savedInstanceState 意外关闭时存储的Bundler
     */
    @Override
    public void onCreateSavedInstanceState(Bundle savedInstanceState) {
        mBundle = savedInstanceState;
        mViewModel.onCreateSavedInstanceState(savedInstanceState);
    }


}
