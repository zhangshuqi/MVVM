package android.com.mvvmdemo;

import android.com.mvvmdemo.databinding.ActivityMainBinding;
import android.com.mvvmlibrary.factory.CreateViewModel;
import android.com.mvvmlibrary.view.BaseMVVMActivity;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

@CreateViewModel(MainViewModel.class)
public class MainActivity extends BaseMVVMActivity<MainViewView,MainViewModel,ActivityMainBinding> implements MainViewView{


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
