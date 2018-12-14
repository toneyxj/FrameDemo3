package com.xj.framedemo3;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xj.bluetooth.BlueToothInterface.DeciceConnectListener;
import com.xj.bluetooth.BlueToothInterface.DeviceStatus;
import com.xj.bluetooth.model.DeviceModel;
import com.xj.bluetooth.status.BlueToothStatus;
import com.xj.mainframe.configer.APPLog;
import com.xj.mainframe.configer.ToastUtils;
import com.xj.mainframe.dialog.AlertDialog;

import java.util.List;

public class BlueThoothActivity extends Activity implements DeciceConnectListener{

    private DeviceStatus blueToothStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_thooth);
        blueToothStatus=new BlueToothStatus();
        if (blueToothStatus.noDevice()){
            ToastUtils.getInstance().showToastShort("无蓝牙模块无法实现功能");
            onBackPressed();
        }
        blueToothStatus.setDeciceConnectListener(this);
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blueToothStatus.refureshDeviceDatas(BlueThoothActivity.this);
            }
        });
        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blueToothStatus.closeDevice();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        blueToothStatus.onDestoryDevice(this);
    }

    @Override
    public void startConnect() {
        APPLog.d("startConnect");

    }

    @Override
    public void connectOver(List<DeviceModel> lists) {
        APPLog.d("connectOver");
    }

    @Override
    public void connctCurDevice(DeviceModel data) {

    }


    @Override
    public void closeDevice() {
        APPLog.d("closeDevice");
    }

    @Override
    public void openDeviceing() {
        APPLog.d("openDeviceing");
    }

    @Override
    public void openDeviced() {
        APPLog.d("openDeviced");
    }
}
