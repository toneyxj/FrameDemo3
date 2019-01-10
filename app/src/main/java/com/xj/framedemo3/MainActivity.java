package com.xj.framedemo3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xj.framedemo3.model.Person;
import com.xj.framedemo3.service.KeepAliveService;
import com.xj.framedemo3.testrefuresh.ScrollingActivity;
import com.xj.mainframe.configer.APPLog;
import com.xj.mainframe.configer.ToastUtils;
import com.xj.mainframe.download.Dinterface.DMBase;
import com.xj.mainframe.eventBus.EventManger;
import com.xj.mainframe.eventBus.EventObserver;
import com.xj.mainframe.listener.AlertInterface;
import com.xj.mainframe.listener.CameraBackListener;
import com.xj.mainframe.listener.XJOnClickListener;
import com.xj.mainframe.netState.NetChangeObserver;
import com.xj.mainframe.netState.NetWorkStateUtil;
import com.xj.mainframe.netState.NetWorkUtil;
import com.xj.mainframe.utils.CameraUtils;
import com.xj.mainframe.utils.SystemUtils;
import com.xj.mainframe.view.BaseView.XJImageView;
import com.xj.mainframe.webX5.BrowserActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import butterknife.BindView;

public class MainActivity extends Activity implements NetChangeObserver, EventObserver{

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
//    @Bind(R.id.buttlay)
//    LinearLayout buttlay;
//    @Bind(R.id.sample_text)
//    XJTextView sample_text;

    public static int mainE = 1;
    ArrayList<String> urls = new ArrayList<>();
    private CameraUtils cameraUtils;
    private TestRequest testRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        APPLog.e("onCreate");
        setContentView(R.layout.activity_main);

//        startService(new Intent(this,KeepAliveService.class));

//        urls.add("http://dlied5.myapp.com/myapp/1104466820/sgame/2017_com.tencent.tmgp.sgame_h178_1.41.2.16_5a7ef8.apk");
//        urls.add("http://gdownyf.baijincdn.cn/data/wisegame/d3cfcbced1a50905/meituan_697.apk");
//        urls.add("http://gdownyf.baijincdn.cn/data/wisegame/7755dede9585bf18/yingyongbao_7292130.apk");
//        urls.add("http://gdownyf.baijincdn.cn/data/wisegame/6b9bb3afc505fdee/qichezhijia_965.apk");
//        APPLog.e("SystemUtils.getCPUCoreNum()", SystemUtils.getCPUCoreNum());
//        for (String url:urls) {
//            DMBase.getInstance(this).addDownload(url);

//        }

        cameraUtils = new CameraUtils(this, new CameraBackListener() {
            @Override
            public void onCameraBack(String path) {
                APPLog.e("onCameraBack-path", path);
            }
        });
        testRequest = new TestRequest(this, null);

//        ButterKnife.bind(this);
        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());

//        buttlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                ((Button)findViewById(R.id.click)).setTextColor(getResources().getColor(R.color.colorAccent));
//            }
//        });
        (findViewById(R.id.click)).setOnClickListener(new XJOnClickListener() {
            @Override
            public void onclickView(View view) {
//                if (MainActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                    APPLog.e("landscape"); // 横屏
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                } else if (MainActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//                    APPLog.e( "portrait"); // 竖屏
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                }
//                testRequest.testRequest();
//                cameraUtils.startCamera();
//                new AlertDialog(MainActivity.this).builder(0)
//                        .setTitle("网络提示")
//                        .setMsg("提示").show();
//                startActivity(new Intent(MainActivity.this,TestEdite.class));
                startActivity(new Intent(MainActivity.this, ScrollingActivity.class));
//                BrowserActivity.StartBrowser(MainActivity.this,"http://soft.imtt.qq.com/browser/tes/feedback.html",false);
//                BrowserActivity.StartBrowser(MainActivity.this,"https://www.hao123.com/",false);
                }
            });

            XJImageView imag = (XJImageView) findViewById(R.id.imag);
            //        imag.loadImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540793816775&di=320911c5448aba2c236006c27e8d4024&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Ftj%2F2018-09-27%2F5baca04abc904.jpg");
//        imag.loadImage("/data/data/com.xj.framedemo/files/cache/image/1544172534400.png");
            LinkedList<String> observers = new LinkedList<>();
        observers.add("就是这么");
        observers.add("就是这么");
        observers.add("就是这么");
        observers.add("就是这么");
        APPLog.e("observers",observers.size());
            Set<String> sets = new HashSet<>();
        sets.add("就是这么");
        sets.add("就是这么");
        sets.add("就是这么");
        sets.add("就是这么1");
        APPLog.e("sets",sets.size());

        NetWorkStateUtil.getInstance(this).

            registerObserver(this);

        EventManger.getInstance().

            registerObserver(mainE,this);

            String value = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540793816775&di" +
                    "=320911c5448aba2c236006c27e8d4024&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Ftj%2F2018-09-27%2F5baca04abc904.jpg";


//        String has= Base64Util.encodeData(value);
//        APPLog.e("转换",has );
//        APPLog.e("转换",Base64Util.decodeData(value) );
//        thread.start();

//        findViewById(R.id.one).setOnClickListener(clickListener);
//        findViewById(R.id.two).setOnClickListener(clickListener);
//        findViewById(R.id.three).setOnClickListener(clickListener);
//        findViewById(R.id.four).setOnClickListener(clickListener);

//        //反射机制
//        try {
//            Class clazz=Class.forName("com.xj.framedemo3.model.Person");
//
//            //---------------获取所有构造方法-------------
//            Constructor[] constructors=clazz.getConstructors();
//            //遍历构造方法，获取构造方法的数据类型
//            for (int i = 0; i < constructors.length; i++) {
//                Class[] paras=constructors[i].getParameterTypes();
//                APPLog.e("第"+i+"个构造函数");
//                for (int i1 = 0; i1 < paras.length; i1++) {
//                    APPLog.e("参数值："+paras[i1].getName());
//                }
//            }
//
//            //-------获取成员变量-----------
//            //获得对象实列
//            Person per= (Person) clazz.newInstance();
//            Field field=clazz.getDeclaredField("name");
//            //因为是私有的，获得属性后，还要打开可见权限
//            field.setAccessible(true);
//            //赋值操作
//            field.set(per,"通过反射设置的值");
//            APPLog.e("name值："+field.get(per));
//
//            // 获取构造方法
//            // 获取参数为(int,String,int)的构造函数
//            Constructor c2 = clazz.getDeclaredConstructor(String.class, int.class,boolean.class);
//            // 通过有参构造函数创建对象
//            Person u2 = (Person) c2.newInstance( "乖乖", 18,true);
//
//            //-----------获得全部成员变量
//            per.setAge(27);
//            per.setName("xiajun");
//            per.setSex(true);
//            //获取所有的私有属性
//            Field[] fields=clazz.getDeclaredFields();
//            for (int i = 0; i < fields.length; i++) {
//                if(fields[i]==null)break;
//                fields[i].setAccessible(true);
//                APPLog.e("成员变量值："+fields[i].get(per));
//            }
//
//            //-------------获得方法并使用
//            //参数一是函数名，后面跟参数类型
//            Method method=clazz.getMethod("setName", String.class);
//            //调用方法
//            method.invoke(per,"guoruxia");
//            //获取私有的方法，和获取私有属性一样
//            Method priMethod=clazz.getDeclaredMethod("setSay", String.class);
//            priMethod.setAccessible(true);
//            priMethod.invoke(per,"我说的呀");
//
//            //-------------------获得所有方法
//            Method[] methods=clazz.getDeclaredMethods();
//            for (Method meth:methods){
//                meth.setAccessible(true);
//                APPLog.e("方法名："+meth.getName());
//
//                Class<?>[] parameters=meth.getParameterTypes();
//                for (int i = 0; i < parameters.length; i++) {
//                    APPLog.e("方法中的参数："+parameters[i].getName());
//                }
//            }
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
        }

        private AlertInterface anInterface = new AlertInterface() {
            @Override
            public boolean onNegative(int code) {
                ToastUtils.getInstance().showToastShort("点击onNegative code=" + code);
                return true;
            }

            @Override
            public boolean onPositive(int code) {
                ToastUtils.getInstance().showToastShort("点击onPositive code=" + code);
                return true;
            }
        };
        private XJOnClickListener clickListener = new XJOnClickListener() {
            @Override
            public void onclickView(View view) {
//        switch (view.getId()){
//            case R.id.one:
//                DMBase.getInstance(MainActivity.this).switchDownload(urls.get(0));
//                break;
//            case R.id.two:
//                DMBase.getInstance(MainActivity.this).switchDownload(urls.get(1));
//                break;
//            case R.id.three:
//                DMBase.getInstance(MainActivity.this).switchDownload(urls.get(2));
//                break;
//            case R.id.four:
//                DMBase.getInstance(MainActivity.this).switchDownload(urls.get(3));
//                break;
//            default:
//                break;
//        }
            }
        };

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            cameraUtils.onActivityResult(requestCode, resultCode, data);

        }

        @Override
        public void onConfigurationChanged (Configuration newConfig){
            super.onConfigurationChanged(newConfig);
            APPLog.e("onConfigurationChanged");
            APPLog.e(newConfig.toString());
        }

        @Override
        protected void onDestroy () {
            super.onDestroy();
            APPLog.e("onDestroy");
            NetWorkStateUtil.getInstance(this).removeObserver(this);
//        ButterKnife.unbind(this);
            EventManger.getInstance().removeObserver(mainE);
            cameraUtils.ondetory();
            testRequest.onStopRequest();
        }

        @Override
        protected void onRestart () {
            super.onRestart();
            APPLog.e("onRestart");
        }

        @Override
        protected void onResume () {
            super.onResume();
            APPLog.e("onResume");
        }

        @Override
        protected void onStart () {
            super.onStart();
            APPLog.e("onStart");
        }

        @Override
        protected void onStop () {
            super.onStop();
            APPLog.e("onStop");
        }

        @Override
        public void onConnect (NetWorkUtil.netType type){
            APPLog.d("网络连接了");
        }

        @Override
        public void onDisConnect () {
            APPLog.d("网络连接关闭了");
        }

        @Override
        public void eventUpdate ( int code, Object data){
            if (code == mainE) {
                ToastUtils.getInstance().showToastShort(data.toString());
            }
        }
//    private Thread thread=new Thread(){
//        @Override
//        public void run() {
//            super.run();
//            Log.e("thread","最开始的哟");
//            int index=0;
//            Looper.prepare();
//            Handler handler=new Handler(){
//                @Override
//                public void handleMessage(Message msg) {
//                    super.handleMessage(msg);
//                    Log.e("thread","handleMessage="+msg.what);
//                }
//            };
//
//            Log.e("thread","prepare-开始  handler="+handler.toString());
//            try {
//                sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            handler.sendEmptyMessage(index);
//            index++;
//            Looper.loop();
//            Log.e("thread","prepare-结束");
//        }
//    };

    }
