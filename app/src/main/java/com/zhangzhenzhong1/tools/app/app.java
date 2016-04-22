package com.zhangzhenzhong1.tools.app;

import android.app.Application;
import android.content.Context;

/**
 *
 */
public class app extends Application
{

    public static Context mContext;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mContext = this;
    }


    public static Context getContext()
    {
        return mContext;
    }
}
