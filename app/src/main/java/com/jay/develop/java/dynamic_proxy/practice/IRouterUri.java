package com.jay.develop.java.dynamic_proxy.practice;

/**
 * 定义一个URI协议接口
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-12-02 18:18
 */
public interface IRouterUri {
    //请求Url地址,在AndroidManifest文件中为每个Activity添加scheme
    @RouterUri(routerUri = "dv://com.jay.develop/router")
    void jumpToDynamicProxyPage(
            @RouterParam("info") String info,

            @RouterParam("des") String des
    );
}
