package com.xj.bluetooth.Drive;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.xj.bluetooth.BlueToothInterface.DeviceConnect;
import com.xj.bluetooth.fastble.BleManager;

import java.util.UUID;

import static android.bluetooth.BluetoothDevice.TRANSPORT_LE;

public class BlueToothConnect implements DeviceConnect {
    //蓝牙设备的Service的UUID
    public final static UUID UUID_SERVICE = UUID.fromString("0003cdd0-0000-1000-8000-00805f9b0131");

    //蓝牙设备的notify的UUID
    public final static UUID UUID_NOTIFY = UUID.fromString("0003cdd1-0000-1000-8000-00805f9b0131");
    private BluetoothAdapter adapter;
    private BluetoothGatt gatt;
    private Context context;
    private String mac;

    @Override
    public void setDeviceAdapter(Object obj) {
        try {
            this.adapter = (BluetoothAdapter) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized boolean connectMac(Context context, String mac) {
        if (adapter == null||context==null||mac==null) return false;
        this.context=context.getApplicationContext();
        this.mac=mac;

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                gatt = adapter.getRemoteDevice(mac).connectGatt(context,
                        false, callback, TRANSPORT_LE);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                gatt = adapter.getRemoteDevice(mac).connectGatt(context,
                        false, callback);
            }
            if (gatt==null)return false;
            if (gatt.connect()) {
                Log.d("connectMac", "Connect succeed.");
                return true;
            } else {
                Log.d("connectMac", "Connect fail.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onDestoryConnect() {
        if (gatt != null) {
            gatt.close();
        }
    }

    private BluetoothGattCallback callback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                if (newState == BluetoothProfile.STATE_CONNECTED) {
                    //设备连接成功
                    gatt.discoverServices();
                } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                    //设备断开连接重连
                    connectMac(context,mac);
                }
            }
            super.onConnectionStateChange(gatt, status, newState);
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            //根据UUID获取Service中的Characteristic,并传入Gatt中
            BluetoothGattService bluetoothGattService = gatt.getService(UUID_SERVICE);
            BluetoothGattCharacteristic bluetoothGattCharacteristic = bluetoothGattService.getCharacteristic(UUID_NOTIFY);

            boolean isConnect = gatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
            if (isConnect) {

            } else {
                Log.i("geanwen", "onServicesDiscovered: 设备一连接notify失败");
            }
            super.onServicesDiscovered(gatt, status);
        }

        //接收设备传入的数据信息
        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
            String data = new String(characteristic.getValue());
            Log.i("geanwen", "onCharacteristicChanged: " + data);
            super.onCharacteristicChanged(gatt, characteristic);
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
        }

    };

}
