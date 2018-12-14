package com.xj.bluetooth.BlueToothInterface;

import android.content.Context;

/**
 * 设备状态
 */
public interface DeviceStatus {
    /**
     * 判断是否有设备模块
     * @return 没有返回true
     */
    boolean noDevice();

    /**
     *为设备模块状态获取设置监听
     */
    void setDeciceConnectListener(DeciceConnectListener listener);

    /**
     * 关闭设备模块
     */
    void closeDevice();

    /**
     * 启动系统设置-设备模块
     * @param context
     */
    void startSystemDevice(Context context);

    /**
     * 启动设备
     */
    void openDevice(Context context);

    /**
     * 刷新设备模块的数据列表
     */
    void refureshDeviceDatas(Context context);

    /**
     * 关闭设备数据获取
     */
    void closeRefureshDatas();

    /**
     * 销毁设备关联数据
     */
    void onDestoryDevice(Context context);



}
