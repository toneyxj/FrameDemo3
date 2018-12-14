package com.xj.bluetooth.model;

public interface DeviceModel {
    String getDeviceName();
    String getDeviceMac();
    String getDeviceType();

    DeviceModel setDeviceName(String name);
    DeviceModel setDeviceMac(String mac);
    DeviceModel setDeviceType(String type);
    DeviceModel initData(String name,String mac,String type);

}
