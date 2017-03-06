package com.htao.statuslayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.htao.statuslayout.R;

import manager.OnRetryListener;
import manager.OnShowHideViewListener;
import manager.StatusLayoutManager;

/**
 * Created by 10607 on 2017/3/6.
 */

public abstract class BaseActivity extends Activity implements OnRetryListener {

    protected StatusLayoutManager statusLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLinearLayout = (LinearLayout) findViewById(R.id.main_rl);
        statusLayoutManager = StatusLayoutManager.newBuilder(this)
                .contentView(R.layout.activity_content)
                .emptyDataView(R.layout.activity_emptydata)
                .errorView(R.layout.activity_error)
                .loadingView(R.layout.activity_loading)
                .netWorkErrorView(R.layout.activity_networkerror)
                .retryViewId(R.id.button_try)
                .onShowHideViewListener(new OnShowHideViewListener() {
                    @Override
                    public void onShowView(View view, int id) {
                    }

                    @Override
                    public void onHideView(View view, int id) {
                    }
                }).onRetryListener(this).build();

    }

    public StatusLayoutManager getStatusLayoutManager() {
        return statusLayoutManager;
    }

    @Override
    public void onRetry() {
        onRetryReq();
    }

   abstract void onRetryReq();


}
