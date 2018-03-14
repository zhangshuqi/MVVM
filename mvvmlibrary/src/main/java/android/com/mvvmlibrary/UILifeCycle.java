package android.com.mvvmlibrary;

import android.com.mvvmlibrary.view.BaseMVVMView;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.KeyEvent;

/**
 * Created by Administrator on 2018/2/7 0007.
 */

public interface UILifeCycle<V extends BaseMVVMView,D extends ViewDataBinding> {

    void onResume(V mvpView);

    Boolean onKeyDown(int keyCode, KeyEvent event);

    void onStart();

    void onPause();

    void onDestroy();

    void onStop();

    Boolean onBackPressed();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onCreateSavedInstanceState(Bundle savedInstanceState);
    void setViewBinding(D binding);
}
