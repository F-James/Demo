package com.fanpc.myapplication.base;

/**
 * Created by fanpc on 2017/10/12.
 */

public interface BasePresenter <T extends BaseView> {
    void onCreate(T mView);
    void onDestroy();
    void onStart();
    void onPause();
}
