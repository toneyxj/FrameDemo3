package com.xj.bluetooth.BlueToothInterface;

import android.bluetooth.BluetoothDevice;

import com.xj.bluetooth.model.DeviceModel;

import java.util.List;
//蓝牙状态监听
public interface DeciceConnectListener {
    /**
     * 蓝牙连接
     */
    void startConnect();

    /**
     *连接获取蓝牙结束
     * @param lists
     */
    void connectOver(List<DeviceModel> lists);
    void connctCurDevice(DeviceModel data);
    //关闭蓝牙
    void closeDevice();
    //开启蓝牙中
    void openDeviceing();
    //蓝牙已打开
    void openDeviced();
}
