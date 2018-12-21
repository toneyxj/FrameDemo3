package com.xj.framedemo3.blesample.comm;


import com.xj.bluetooth.fastble.data.BleDevice;

public interface Observable {

    void addObserver(Observer obj);

    void deleteObserver(Observer obj);

    void notifyObserver(BleDevice bleDevice);
}
