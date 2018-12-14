package com.xj.bluetooth.status;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Message;
import android.util.Log;

import com.xj.bluetooth.BlueToothInterface.DeciceConnectListener;
import com.xj.bluetooth.BlueToothInterface.DeviceStatus;
import com.xj.bluetooth.BlueToothInterface.HandlerBlueToothInterface;
import com.xj.bluetooth.model.BlueToothModel;
import com.xj.bluetooth.model.DeviceModel;
import com.xj.bluetooth.utils.BaseUtils;

import java.util.ArrayList;
import java.util.List;

public class BlueToothStatus implements DeviceStatus, HandlerBlueToothInterface {
    private List<DeviceModel> listBTs = new ArrayList<>();
    private BluetoothAdapter bluetoothAdapter;
    private boolean firstRegister = true;
    private BaseUtils.BTHander hander = new BaseUtils.BTHander(this);
    private DeciceConnectListener listener;
    private boolean startBT = false;
    private int SI=0;

    public BlueToothStatus() {
        // 获得蓝牙适配器对象
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //观察蓝牙关闭与开启
        hander.sendEmptyMessageDelayed(100, 500);
    }

    @Override
    public boolean noDevice() {
        return bluetoothAdapter == null;
    }

    @Override
    public void setDeciceConnectListener(DeciceConnectListener listener) {
        this.listener = listener;
    }

    @Override
    public void closeDevice() {
        if (noDevice()) return;
        if (bluetoothAdapter.isEnabled()) {
            closeRefureshDatas();
            bluetoothAdapter.disable();
            resetIng(false);
            if (listener != null) listener.closeDevice();
        }
    }

    @Override
    public void startSystemDevice(Context context) {
        if (noDevice()) return;
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        context.startActivity(intent);
    }

    @Override
    public void openDevice(Context context) {
        if (noDevice()) return;
        if (!bluetoothAdapter.isEnabled()) {
            startEnable(context);
        }
    }

    private void startEnable(Context context) {
        if (bluetoothAdapter.enable()) {
            resetIng(true);
            if (listener != null)
                listener.openDeviceing();
        } else {
            startSystemDevice(context);
        }
    }

    @Override
    public void refureshDeviceDatas(Context context) {
        if (noDevice()) return;
        if (bluetoothAdapter == null) return;
        if (!bluetoothAdapter.isEnabled()) {
            startEnable(context);
            return;
        }
        if (firstRegister) {
            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);//开启搜索
            filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
            context.registerReceiver(mFoundReceiver, filter);
            firstRegister = false;
        }
        listBTs.clear();
        if (listener != null)
            listener.startConnect();
        bluetoothAdapter.startDiscovery();
    }

    @Override
    public void closeRefureshDatas() {
        if (noDevice()) return;
        bluetoothAdapter.cancelDiscovery();
    }

    @Override
    public void onDestoryDevice(Context context) {
        try {
            hander.removeCallbacksAndMessages(null);
            listener = null;
            context.unregisterReceiver(mFoundReceiver);
            listBTs.clear();
            closeRefureshDatas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BroadcastReceiver mFoundReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //找到设备
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                DeviceModel model=new BlueToothModel().initData(device.getName() ,device.getAddress() ,BaseUtils.BLUE_TOOTH);
                listBTs.add(model);
                if (listener != null)
                    listener.connctCurDevice(model);
            }
            //搜索完成
            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                if (listener != null)
                    listener.connectOver(listBTs);
            }
        }
    };

    @Override
    public void handleMessage(Message msg) {
        if (noDevice()) return;
        switch (msg.what) {
            case 100:
                if (listener != null) {
                    if (!bluetoothAdapter.isEnabled()) {//蓝牙已经关闭
                        listBTs.clear();
                        if (startBT) {
                            if (SI > 6) {
                                resetIng(false);
                            }else {
                                SI++;
                            }
                        }
                        if (!startBT){
                            listener.closeDevice();
                        }
                    } else if (bluetoothAdapter.isEnabled()) {
                        //蓝牙已开启
                        listener.openDeviced();
                        resetIng(false);
                    }
                }
                hander.sendEmptyMessageDelayed(100, 500);
                break;
            default:
                break;
        }
    }
    private void resetIng(boolean is){
        startBT=is;
        SI=0;
    }
}
