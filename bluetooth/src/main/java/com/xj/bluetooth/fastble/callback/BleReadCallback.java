package com.xj.bluetooth.fastble.callback;


import com.xj.bluetooth.fastble.exception.BleException;

public abstract class BleReadCallback extends BleBaseCallback {

    public abstract void onReadSuccess(byte[] data);

    public abstract void onReadFailure(BleException exception);

}
