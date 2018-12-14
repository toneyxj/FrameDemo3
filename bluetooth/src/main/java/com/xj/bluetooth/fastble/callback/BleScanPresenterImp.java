package com.xj.bluetooth.fastble.callback;


import com.xj.bluetooth.fastble.data.BleDevice;

public interface BleScanPresenterImp {

    void onScanStarted(boolean success);

    void onScanning(BleDevice bleDevice);

}
