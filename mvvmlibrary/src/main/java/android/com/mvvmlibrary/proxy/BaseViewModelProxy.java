package android.com.mvvmlibrary.proxy;

import android.com.mvvmlibrary.factory.ViewModelFactory;
import android.com.mvvmlibrary.view.BaseMVVMView;
import android.com.mvvmlibrary.viewmodel.BaseViewModel;
import android.databinding.ViewDataBinding;

/**
 *  ViewModel 的代理类
 */

public interface BaseViewModelProxy<V extends BaseMVVMView, VM extends BaseViewModel<V,D>,D extends ViewDataBinding> {
    /**
     * 由于不是所有的ViewModel 都适用于注解模式生成ViewModel对象,所以传入一个基类工厂,获取到ViewModel
     * */
    void setViewModelFactory(ViewModelFactory<V, VM,D> factory);

    ViewModelFactory<V, VM,D> getViewModelFactory();
  /***/
    VM getViewModel();

}
