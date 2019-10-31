package com.jay.develop.java.reflection;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 通过反射实现的Hock帮助类
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-10-31 10:44
 */
public class HockHelper {

    public static final String TAG = "HockHelper";

    /**
     * 应用启动状态跟踪
     * 一般写在application里面的attachBaseContext()方法里面，因为这个方法时机最早
     *
     * @param context context
     * @throws Exception
     */
    public static void hookHandler(Context context) throws Exception {
        //反射获取ActivityThread的Class对象
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        //获取currentActivityThread私有方法
        Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
        currentActivityThreadMethod.setAccessible(true);
        //执行currentActivityThreadMethod获取主线程对象
        Object activityThread = currentActivityThreadMethod.invoke(null);
        //获取mH字段
        Field mH = activityThreadClass.getDeclaredField("mH");
        mH.setAccessible(true);
        //获取mH私有字段的值
        Handler handler = (Handler) mH.get(activityThread);
        //反射获取Handler中原始的mCallBack字段
        Field mCallBack = Handler.class.getDeclaredField("mCallback");
        mCallBack.setAccessible(true);
        //这里设置了我们自己实现了接口的CallBack对象
        mCallBack.set(handler, new CustomHandler(handler));
    }

    /**
     * 用于应用初始化异步通信Handler,可以截获发送的一系列事件
     */
    public static class CustomHandler implements Handler.Callback {
        private Handler origin;

        public CustomHandler(Handler mHandler) {
            this.origin = mHandler;
        }

        @Override
        public boolean handleMessage(Message msg) {
            Log.d(TAG, "CustomHandler-handleMessage-" + H.codeToString(msg.what));
            //这样每次启动的时候可以做些额外的事情
            origin.handleMessage(msg);
            return false;
        }
    }

    /**
     * Activity的创建的监控
     * 创建自定义的Instrumentation，然后反射替换，同时重写newActivity方法
     *
     * @throws Exception
     */
    public static void hookInstrumentation() throws Exception {
        Class<?> activityThread = Class.forName("android.app.ActivityThread");
        Method currentActivityThread = activityThread.getDeclaredMethod("currentActivityThread");
        currentActivityThread.setAccessible(true);
        //获取主线程对象
        Object activityThreadObject = currentActivityThread.invoke(null);
        //获取Instrumentation字段
        Field mInstrumentation = activityThread.getDeclaredField("mInstrumentation");
        mInstrumentation.setAccessible(true);
        //获取字段值
        Instrumentation instrumentation = (Instrumentation) mInstrumentation.get(activityThreadObject);
        //偷梁换柱，把系统的instrumentation替换为自己的Instrumentation对象
        CustomInstrumentation customInstrumentation = new CustomInstrumentation(instrumentation);
        //设置字段值
        mInstrumentation.set(activityThreadObject, customInstrumentation);
    }

    /**
     * 自定义一个Instrumentation类用于替换系统的
     */
    public static class CustomInstrumentation extends Instrumentation {
        private Instrumentation base;

        public CustomInstrumentation(Instrumentation base) {
            this.base = base;
        }

        //重写创建Activity的方法
        @Override
        public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
            Log.d(TAG, "CustomInstrumentation-newActivity-className=" + className);
            Log.d(TAG, "CustomInstrumentation-newActivity-intent=" + intent);
            Log.d(TAG, "CustomInstrumentation-newActivity-ClassLoader=" + cl);
            return super.newActivity(cl, className, intent);
        }
    }


    public static class H {
        public static final int BIND_APPLICATION = 110;
        public static final int EXIT_APPLICATION = 111;
        public static final int RECEIVER = 113;
        public static final int CREATE_SERVICE = 114;
        public static final int SERVICE_ARGS = 115;
        public static final int STOP_SERVICE = 116;
        public static final int CONFIGURATION_CHANGED = 118;
        public static final int CLEAN_UP_CONTEXT = 119;
        public static final int GC_WHEN_IDLE = 120;
        public static final int BIND_SERVICE = 121;
        public static final int UNBIND_SERVICE = 122;
        public static final int DUMP_SERVICE = 123;
        public static final int LOW_MEMORY = 124;
        public static final int PROFILER_CONTROL = 127;
        public static final int CREATE_BACKUP_AGENT = 128;
        public static final int DESTROY_BACKUP_AGENT = 129;
        public static final int SUICIDE = 130;
        public static final int REMOVE_PROVIDER = 131;
        public static final int ENABLE_JIT = 132;
        public static final int DISPATCH_PACKAGE_BROADCAST = 133;
        public static final int SCHEDULE_CRASH = 134;
        public static final int DUMP_HEAP = 135;
        public static final int DUMP_ACTIVITY = 136;
        public static final int SLEEPING = 137;
        public static final int SET_CORE_SETTINGS = 138;
        public static final int UPDATE_PACKAGE_COMPATIBILITY_INFO = 139;
        public static final int DUMP_PROVIDER = 141;
        public static final int UNSTABLE_PROVIDER_DIED = 142;
        public static final int REQUEST_ASSIST_CONTEXT_EXTRAS = 143;
        public static final int TRANSLUCENT_CONVERSION_COMPLETE = 144;
        public static final int INSTALL_PROVIDER = 145;
        public static final int ON_NEW_ACTIVITY_OPTIONS = 146;
        public static final int ENTER_ANIMATION_COMPLETE = 149;
        public static final int START_BINDER_TRACKING = 150;
        public static final int STOP_BINDER_TRACKING_AND_DUMP = 151;
        public static final int LOCAL_VOICE_INTERACTION_STARTED = 154;
        public static final int ATTACH_AGENT = 155;
        public static final int APPLICATION_INFO_CHANGED = 156;
        public static final int RUN_ISOLATED_ENTRY_POINT = 158;
        public static final int EXECUTE_TRANSACTION = 159;
        public static final int RELAUNCH_ACTIVITY = 160;

        static String codeToString(int code) {
            switch (code) {
                case BIND_APPLICATION:
                    return "BIND_APPLICATION";
                case EXIT_APPLICATION:
                    return "EXIT_APPLICATION";
                case RECEIVER:
                    return "RECEIVER";
                case CREATE_SERVICE:
                    return "CREATE_SERVICE";
                case SERVICE_ARGS:
                    return "SERVICE_ARGS";
                case STOP_SERVICE:
                    return "STOP_SERVICE";
                case CONFIGURATION_CHANGED:
                    return "CONFIGURATION_CHANGED";
                case CLEAN_UP_CONTEXT:
                    return "CLEAN_UP_CONTEXT";
                case GC_WHEN_IDLE:
                    return "GC_WHEN_IDLE";
                case BIND_SERVICE:
                    return "BIND_SERVICE";
                case UNBIND_SERVICE:
                    return "UNBIND_SERVICE";
                case DUMP_SERVICE:
                    return "DUMP_SERVICE";
                case LOW_MEMORY:
                    return "LOW_MEMORY";
                case PROFILER_CONTROL:
                    return "PROFILER_CONTROL";
                case CREATE_BACKUP_AGENT:
                    return "CREATE_BACKUP_AGENT";
                case DESTROY_BACKUP_AGENT:
                    return "DESTROY_BACKUP_AGENT";
                case SUICIDE:
                    return "SUICIDE";
                case REMOVE_PROVIDER:
                    return "REMOVE_PROVIDER";
                case ENABLE_JIT:
                    return "ENABLE_JIT";
                case DISPATCH_PACKAGE_BROADCAST:
                    return "DISPATCH_PACKAGE_BROADCAST";
                case SCHEDULE_CRASH:
                    return "SCHEDULE_CRASH";
                case DUMP_HEAP:
                    return "DUMP_HEAP";
                case DUMP_ACTIVITY:
                    return "DUMP_ACTIVITY";
                case SLEEPING:
                    return "SLEEPING";
                case SET_CORE_SETTINGS:
                    return "SET_CORE_SETTINGS";
                case UPDATE_PACKAGE_COMPATIBILITY_INFO:
                    return "UPDATE_PACKAGE_COMPATIBILITY_INFO";
                case DUMP_PROVIDER:
                    return "DUMP_PROVIDER";
                case UNSTABLE_PROVIDER_DIED:
                    return "UNSTABLE_PROVIDER_DIED";
                case REQUEST_ASSIST_CONTEXT_EXTRAS:
                    return "REQUEST_ASSIST_CONTEXT_EXTRAS";
                case TRANSLUCENT_CONVERSION_COMPLETE:
                    return "TRANSLUCENT_CONVERSION_COMPLETE";
                case INSTALL_PROVIDER:
                    return "INSTALL_PROVIDER";
                case ON_NEW_ACTIVITY_OPTIONS:
                    return "ON_NEW_ACTIVITY_OPTIONS";
                case ENTER_ANIMATION_COMPLETE:
                    return "ENTER_ANIMATION_COMPLETE";
                case LOCAL_VOICE_INTERACTION_STARTED:
                    return "LOCAL_VOICE_INTERACTION_STARTED";
                case ATTACH_AGENT:
                    return "ATTACH_AGENT";
                case APPLICATION_INFO_CHANGED:
                    return "APPLICATION_INFO_CHANGED";
                case RUN_ISOLATED_ENTRY_POINT:
                    return "RUN_ISOLATED_ENTRY_POINT";
                case EXECUTE_TRANSACTION:
                    return "EXECUTE_TRANSACTION";
                case RELAUNCH_ACTIVITY:
                    return "RELAUNCH_ACTIVITY";
            }
            return Integer.toString(code);
        }

    }
}