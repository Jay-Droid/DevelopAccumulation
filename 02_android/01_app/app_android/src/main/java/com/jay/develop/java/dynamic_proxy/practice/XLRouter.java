package com.jay.develop.java.dynamic_proxy.practice;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * 内部通过动态代理机制实现跳转
 * <p>
 * 参考地址: https://www.cnblogs.com/whoislcj/p/5860138.html
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-12-02 18:15
 */
public class XLRouter {
    private final static String TAG = XLRouter.class.getSimpleName();
    private static IRouterUri mRouterUri;
    private static Context mContext;

    /**
     * 初始化
     */
    public static void initXLRouter(Context context) {
        mContext = context.getApplicationContext();
        mRouterUri = create(IRouterUri.class);
    }

    /**
     * 返回Api
     */
    public static IRouterUri routerUri() {
        if (mRouterUri == null) {
            throw new IllegalStateException("XLRouter还未初始化");
        }
        return mRouterUri;
    }

    /**
     * 通过动态代理生成IRouterUri接口的实例对象并解析注解参数
     *
     * @param aClass IRouterUri Class对象
     * @return 真实对象的代理对象
     */
    private static IRouterUri create(Class aClass) {
        return (IRouterUri) Proxy.newProxyInstance(aClass.getClassLoader(), new Class[]{aClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object... args) throws Throwable {
                        StringBuilder stringBuilder = new StringBuilder();
                        //获取RouterUri注解
                        RouterUri reqUrl = method.getAnnotation(RouterUri.class);
                        Log.e(TAG, "IReqApi---reqUrl->" + reqUrl.routerUri());
                        stringBuilder.append(reqUrl.routerUri());
                        //Type[] parameterTypes = method.getGenericParameterTypes();//获取注解参数类型
                        //拿到参数注解
                        Annotation[][] parameterAnnotationsArray = method.getParameterAnnotations();
                        //Annotation[] annotation = method.getDeclaredAnnotations();
                        /*
                            定义的路由接口
                            @RouterUri(routerUri = "dv://com.jay.develop/router")
                                void jumpToDynamicProxyPage(
                                        @RouterParam("info") String info,
                                        @RouterParam("des") String des
                             );
                         */
                        int pos = 0;
                        for (int i = 0; i < parameterAnnotationsArray.length; i++) {
                            Annotation[] annotations = parameterAnnotationsArray[i];
                            if (annotations != null && annotations.length != 0) {
                                if (pos == 0) {
                                    stringBuilder.append("?");
                                } else {
                                    stringBuilder.append("&");
                                }
                                pos++;
                                RouterParam reqParam = (RouterParam) annotations[0];
                                stringBuilder.append(reqParam.value());
                                stringBuilder.append("=");
                                stringBuilder.append(args[i]);
                                Log.e(TAG, "reqParam---reqParam->" + reqParam.value() + "=" + args[i]);
                            }
                        }
                        Log.e(TAG, "stringBuilder.toString()->" + stringBuilder.toString());
                        //dv://com.jay.develop/router?info=我是通过XLRouter路由框架跳转的&des=描述
                        //下面就可以执行相应的跳转操作
                        openRouterUri(stringBuilder.toString());
                        return null;
                    }
                });
    }

    /**
     * 通过uri跳转指定页面
     *
     * @param url deeplink
     */
    private static void openRouterUri(String url) {
        PackageManager packageManager = mContext.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        List activities = packageManager.queryIntentActivities(intent, 0);
        boolean isValid = !activities.isEmpty();
        if (isValid) {
            mContext.startActivity(intent);
        }
    }

    /**
     * 4.)在进行XLRouter初始化
     *
     * XLRouter.initXLRouter(this);
     *
     *
     * 5.调用方式
     *
     * XLRouter.routerUri().jumpToGoodsDetail("1000110002","goods des");
     */
}
