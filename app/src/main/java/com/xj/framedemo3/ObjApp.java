package com.xj.framedemo3;

import android.os.Debug;

import com.xj.mainframe.BaseApplication;
import com.xj.mainframe.base.InitializeService;
import com.xj.mainframe.configer.ToastUtils;

/**
 * Created by xj on 2018/10/23.
 */

public class ObjApp extends BaseApplication {
    //test哈哈哈哈66666666666666
    @Override
    public void onCreate() {
//我就是改了哈哈
        Debug.startMethodTracing("ObjApp");
        super.onCreate();
//注册提示
        InitializeService.start(this,myInitService.class);
        ToastUtils.getInstance().initToast(this);
        Debug.stopMethodTracing();
    }
}
