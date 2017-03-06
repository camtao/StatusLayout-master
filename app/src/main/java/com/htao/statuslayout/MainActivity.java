package com.htao.statuslayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.htao.statuslayout.BaseActivity;
import com.htao.statuslayout.R;

import manager.StatusLayoutManager;

public class MainActivity extends BaseActivity {

    protected StatusLayoutManager statusLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();

        LinearLayout mainLinearLayout = (LinearLayout) findViewById(R.id.main_rl);
        statusLayoutManager =getStatusLayoutManager();
        mainLinearLayout.addView(statusLayoutManager.getRootLayout(), 1);
        statusLayoutManager.showLoading();
    }

    @Override
    void onRetryReq() {
        statusLayoutManager.showLoading();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        statusLayoutManager.showContent();
                    }
                });
            }
        }).start();

    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_bar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("StatusLayout");
        toolbar.inflateMenu(R.menu.base_toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.action_neirong) {
                    statusLayoutManager.showContent();
                }
                if(item.getItemId() == R.id.action_emptyData) {
                    statusLayoutManager.showEmptyData();
                }
                if(item.getItemId() == R.id.action_error) {
                    statusLayoutManager.showError();
                }
                if(item.getItemId() == R.id.action_networkError) {
                    statusLayoutManager.showNetWorkError();
                }
                if(item.getItemId() == R.id.action_loading) {
                    statusLayoutManager.showLoading();
                }
                return true;
            }
        });
    }
}
