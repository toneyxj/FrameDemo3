package com.xj.bluetooth.BlueToothInterface;

import android.bluetooth.BluetoothGattCallback;
import android.content.Context;

/**
 * 设备间的连接
 */
public interface DeviceConnect {
    /**
     * 设置设备的adapter
     *
     * @param obj
     */
    void setDeviceAdapter(Object obj);

    /**
     * 连接mac对应的设备
     * @param context 上下文
     * @param mac 连接设备mac地址
     * @return 返回fase代表连接失败
     */
    boolean connectMac(Context context,String mac);

    void onDestoryConnect();

}
