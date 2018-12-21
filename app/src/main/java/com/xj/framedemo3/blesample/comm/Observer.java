package com.xj.framedemo3.blesample.comm;


import com.xj.bluetooth.fastble.data.BleDevice;

public interface Observer {

    void disConnected(BleDevice bleDevice);
}
