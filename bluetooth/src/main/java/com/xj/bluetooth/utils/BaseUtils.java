package com.xj.bluetooth.utils;

import android.os.Handler;
import android.os.Message;

import com.xj.bluetooth.BlueToothInterface.HandlerBlueToothInterface;

import java.lang.ref.WeakReference;

/**
 * base操作工具类
 */

public class BaseUtils {
    /**
     * 蓝牙连接
     */
    public static final String BLUE_TOOTH="blueTooth";

    /**
     * 全部类使用handler
     */
    public static class BTHander extends Handler {
        private WeakReference<HandlerBlueToothInterface> reference;

        public BTHander(HandlerBlueToothInterface context) {
            reference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            HandlerBlueToothInterface activity = (HandlerBlueToothInterface) reference.get();
            if (activity != null) {
                activity.handleMessage(msg);
            }
        }
    }
}
