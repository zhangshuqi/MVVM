package android.com.mvvmlibrary.viewmodel;

import android.com.mvvmlibrary.UILifeCycle;
import android.com.mvvmlibrary.proxy.BaseViewModelProxyMethod;
import android.com.mvvmlibrary.view.BaseMVVMView;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

/**
 * Created by Administrator on 2018/2/7 0007.
 */

public class BaseViewModel<V extends BaseMVVMView, D extends ViewDataBinding> implements BaseViewModelProxyMethod<V>, UILifeCycle<V,D> {


    public V mView;
    public D mBinding;

    @Override
    public void onResume(V view) {

    }

    @Override
    public Boolean onKeyDown(int keyCode, KeyEvent event) {
        return null;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public Boolean onBackPressed() {
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onCreateSavedInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onAttachView(V view) {
        this.mView = view;
    }

    @Override
    public void onDetachView() {
        this.mView = null;
    }

    @Override
    public void onDestroyViewModel() {

    }

    @Override
    public void onCreateViewModel(@Nullable Bundle savedState) {

    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {

    }
  /*  @Override
    public void setViewBinding(D viewBinding) {
        this.mBinding = viewBinding;
    }*/
  @Override
    public void setViewBinding(D mBinding) {
        this.mBinding = mBinding;
    }
}
