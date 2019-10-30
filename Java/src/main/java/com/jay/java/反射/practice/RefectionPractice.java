package com.jay.java.反射.practice;


/**
 * 反射综合实践测试类
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-10-25 16:48
 */
public class RefectionPractice {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int demoIndex = 1;

        switch (demoIndex) {

            case 1: {
                //demo1.  反射与工厂设计模式的实践
                demo1();

                break;
            }
            case 2: {
                //demo2.  通过反射机制监控Activity的启动和创建
                demo2();
                break;
            }
            case 3: {
                //demo3.  反射针对非 SDK 接口的限制
                demo3();
                break;
            }
        }
    }


    /**
     * demo1：反射与工厂设计模式的实践
     * 工厂模式的特点：客户端的程序类不直接牵扯到对象的实例化管理,只与接口发生关联,通过工厂类获取接口的实例化对象
     * 实现的效果为：富士康工厂可以采用不同的生产线生产不同种类的产品而无需修改工厂类
     *
     * @throws Exception
     */
    private static void demo1() throws Exception {
        System.out.println("-----Demo1-----\n\n");
        System.out.println("-----构建手机生产线-----");
        Phone miPhone = Factory.getInstance(MiPhone.CLASS_NAME, Phone.class);
        miPhone.phoneBrand();
        Phone huaWeiPhone = Factory.getInstance(HuaWeiPhone.CLASS_NAME, Phone.class);
        huaWeiPhone.phoneBrand();
        System.out.println("-----构建笔记本电脑生产线-----");
        Computer computer = Factory.getInstance(MiComputer.CLASS_NAME, Computer.class);
        computer.computerBrand();
    }

    /**
     * 富士康代加工工厂类
     */
    static class Factory {

        /**
         * 获取接口实例化对象,生产线分配系统
         *
         * @param className      接口的子类
         * @param interfaceClass 描述的是一个接口的类型
         * @return 如果子类存在则返回指定接口
         * @throws Exception
         */
        static <T> T getInstance(String className, Class<T> interfaceClass) throws Exception {
            //反射获取各个产品类的实例
            Object object = Class.forName(className).newInstance();
            //将各个产品类转为各自的接口类型
            return interfaceClass.cast(object);
        }
    }

    /**
     * 生产手机的接口
     */
    interface Phone {
        void phoneBrand();
    }

    /**
     * 小米手机生产类
     */
    public static class MiPhone implements Phone {

        public static final String CLASS_NAME = "com.jay.java.反射.practice.RefectionPractice$MiPhone";

        @Override
        public void phoneBrand() {
            System.out.println("我是富士康代生产的小米手机");
        }
    }

    /**
     * 华为手机生产类
     */
    public static class HuaWeiPhone implements Phone {

        public static final String CLASS_NAME = "com.jay.java.反射.practice.RefectionPractice$HuaWeiPhone";

        @Override
        public void phoneBrand() {
            System.out.println("我是富士康代生产的华为手机");
        }
    }

    /**
     * 生产电脑的接口
     */
    interface Computer {
        void computerBrand();
    }

    /**
     * 小米笔记本电脑生产类
     */
    public static class MiComputer implements Computer {

        public static final String CLASS_NAME = "com.jay.java.反射.practice.RefectionPractice$MiComputer";

        @Override
        public void computerBrand() {
            System.out.println("我是富士康代生产的小米笔记本电脑");
        }
    }

    /**
     *
     */
    private static void demo2() {
        System.out.println("-----Demo2-----\n\n");

    }

    /**
     *
     */
    private static void demo3() {

    }
}
