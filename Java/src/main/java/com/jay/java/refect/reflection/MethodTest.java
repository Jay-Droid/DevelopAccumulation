package com.jay.java.refect.reflection;

import com.jay.java.refect.Person;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Author：Jay On 2019/5/18 16:56
 * <p>
 * Description:反射获取方法测试类
 */
public class MethodTest {

    public static void testMethod() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String className = "com.jay.java.reflection.Person";
        Class<Person> personClass = (Class<Person>) Class.forName(className);

        //获取获取类中的所有方法，包括父类中的方法，不包括私有方法
        Method[] methods = personClass.getMethods();
        for (Method method : methods) {
            System.out.print(method.getName() + "(), ");
            //运行结果：
            //getName(), setName(), staticMethod(), getAge(), setAge(), wait(), wait(), wait(), equals(), toString(), hashCode(), getClass(), notify(), notifyAll(),
        }

        System.out.println();
        System.out.println("--------------------------------------");

        //获取当前类的所有方法，包含私有方法
        methods = personClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.print(method.getName() + "(), ");
            //运行结果：
            //getName(), setName(), privateMethod(), staticMethod(), getAge(), setAge(),
        }

        System.out.println();
        System.out.println("--------------------------------------");

        //获取指定的方法，需要传入方法名称和参数列表，无惨则不需要写
        Method setNameMethod = personClass.getDeclaredMethod("setName", String.class);
        Method getNameMethod = personClass.getDeclaredMethod("getName");
        System.out.println(setNameMethod);
        System.out.println(getNameMethod);
        //执行结果：
        //public void com.jay.java.reflection.Person.setName(java.lang.String)
        //public java.lang.String com.jay.java.reflection.Person.getName()

        System.out.println("--------------------------------------");
        //执行一个方法，需要传入要执行该方法的对象实例和实参
        Person person = personClass.newInstance();
        setNameMethod.invoke(person, "name is Jay");
        System.out.println(getNameMethod.invoke(person));
        //执行结果
        //调用了无参构造方法
        //调用了setName()方法
        //调用了getName()方法
        //name is Jay

        System.out.println("--------------------------------------");
        //执行类的私有方法，需要设置setAccessible(true)
        //否则报异常：MethodTest can not access a member of class com.jay.java.reflection.Person with modifiers "private"
        Method privateMethod = personClass.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);
        System.out.println(privateMethod);
        privateMethod.invoke(person);
        //运行结果：
        //private void com.jay.java.reflection.Person.privateMethod()
        //调用了私有方法

        System.out.println("--------------------------------------");
        //执行静态方法
        Method staticMethod = personClass.getDeclaredMethod("staticMethod");
        System.out.println(staticMethod);
        staticMethod.invoke(person);
    }

    public static void main(String[] args) {
        try {
            MethodTest.testMethod();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
