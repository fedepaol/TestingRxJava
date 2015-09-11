package com.whiterabbit.rxjavatesting;

import android.app.Application;

import com.whiterabbit.rxjavatesting.inject.ApplicationModule;
import com.whiterabbit.rxjavatesting.inject.DaggerRxJavaComponent;
import com.whiterabbit.rxjavatesting.inject.RxJavaComponent;

public class MyApplication extends Application {
    private RxJavaComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    ApplicationModule getApplicationModule() {
        return new ApplicationModule();
    }

    public RxJavaComponent getComponent() {
        return mComponent;
    }

    void initComponent() {
        mComponent = DaggerRxJavaComponent.builder()
                .applicationModule(getApplicationModule())
                .build();
    }
}
