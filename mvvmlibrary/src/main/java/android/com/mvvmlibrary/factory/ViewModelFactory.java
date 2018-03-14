package android.com.mvvmlibrary.factory;

import android.com.mvvmlibrary.view.BaseMVVMView;
import android.com.mvvmlibrary.viewmodel.BaseViewModel;
import android.databinding.ViewDataBinding;

/**
 * 创建viewModel的工厂
 */

public interface ViewModelFactory <V extends BaseMVVMView ,VM extends BaseViewModel<V,D>,D extends ViewDataBinding> {
    /**
     * 创建ViewModel 的工厂方法,(由于不是所有的ViewModel 都适用于注解模式生成ViewModel对象,所以传入一个基类工厂,获取到ViewModel)
     * @return 返回viewmodel
     * */

    VM createViewModel();
}
