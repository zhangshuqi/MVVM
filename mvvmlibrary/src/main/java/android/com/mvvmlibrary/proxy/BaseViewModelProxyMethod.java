package android.com.mvvmlibrary.proxy;

import android.com.mvvmlibrary.view.BaseMVVMView;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2018/2/7 0007.
 */

public interface BaseViewModelProxyMethod <V extends BaseMVVMView>{
    void onAttachView(V view);
    void onDetachView();
    void onDestroyViewModel();
    void onCreateViewModel(@Nullable Bundle savedState);
    void onSaveInstanceState(Bundle bundle);
}
