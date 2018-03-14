package android.com.mvvmlibrary.factory;

import android.com.mvvmlibrary.view.BaseMVVMView;
import android.com.mvvmlibrary.viewmodel.BaseViewModel;
import android.databinding.ViewDataBinding;
import android.view.View;

/**
 * 适用注解模式创建ViewModel
 */

public class ViewModelFactoryImpl< V extends BaseMVVMView, VM extends BaseViewModel<V, D>,D extends ViewDataBinding> implements ViewModelFactory<V, VM,D> {
    private Class<VM> mViewModel;

    public static <V extends BaseMVVMView, VM extends BaseViewModel<V,D>,D extends ViewDataBinding > ViewModelFactoryImpl createViewModelFactory(Class<?> viewClazz) {
        CreateViewModel annotation = viewClazz.getAnnotation(CreateViewModel.class);
        if (annotation != null) {
            Class<VM> viewModelClazz = (Class<VM>) annotation.value();
            try {
                return new ViewModelFactoryImpl(viewModelClazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private ViewModelFactoryImpl(Class<VM> viewModel) {
        this.mViewModel = viewModel;
    }

    @Override
    public VM createViewModel() {
        try {
            return mViewModel.newInstance();
        } catch (Exception e) {
            throw new NullPointerException("viewModel 创建失败, 请查看view 模块是否添加了@CreateViewModel(xx.class) 注解");
        }
    }
}
