package com.xj.bluetooth.model;

public class BlueToothModel implements DeviceModel {
    private String name;
    private String mac;
    private String type;
    @Override
    public String getDeviceName() {
        return name;
    }

    @Override
    public String getDeviceMac() {
        return mac;
    }

    @Override
    public String getDeviceType() {
        return type;
    }

    @Override
    public DeviceModel setDeviceName(String name) {
        this.name=name;
        return this;
    }

    @Override
    public DeviceModel setDeviceMac(String mac) {
        this.mac=mac;
        return this;
    }

    @Override
    public DeviceModel setDeviceType(String type) {
        this.type=type;
        return this;
    }

    @Override
    public DeviceModel initData(String name, String mac, String type) {
        this.name=name;
        this.type=type;
        this.mac=mac;
        return this;
    }
}
