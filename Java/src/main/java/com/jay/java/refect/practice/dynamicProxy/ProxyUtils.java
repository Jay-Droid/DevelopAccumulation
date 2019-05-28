package com.jay.java.refect.practice.dynamicProxy;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author：Jay On 2019/5/18 22:18
 * <p>
 * Description: 手动创建代理类的字节码文件的工具类
 */
public class ProxyUtils {
    public static void generateClassFile(Class clazz, String proxyName) {
        /*ProxyGenerator.generateProxyClass(proxyName, interfaces, accessFlags);*/
        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                proxyName, new Class[]{clazz});
        String paths = clazz.getResource(".").getPath();
        File file = new File(paths, proxyName + ".class");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(paths);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            out.write(proxyClassFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
