package com.jay.java.generic.demo;


import java.util.ArrayList;
import java.util.List;


public class GenericByteCodeTest<T extends Comparable> implements GenericInterfaceTest<T> {

    private List<T> items = new ArrayList<T>(10);

    public GenericByteCodeTest() {

    }

    private <T extends Comparable> void sort(T items) {
        items.toString();
    }

// todo super
//    private <T super Comparable> void sort(T items) {
//        items.toString();
//    }

    @Override
    public void set(T t) {
        items.add(t);
    }

    @Override
    public T get() {
        if (!items.isEmpty()) {
            return items.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
//擦除后的字节码类信息
/*
  查看字节吗工具：
 使用 javac 命令编译源文件
 javac GenericByteCodeTest.java GenericInterfaceTest.java
 使用 java -c 查看生成的字节码信息
 javap -c GenericByteCodeTest2.class

 或者使用插件 ASM Bytecode Viewer
 插件地址：https://plugins.jetbrains.com/plugin/10302-asm-bytecode-viewer
 选中类然后右键，选择 ASM Bytecode Viewer

 或者使用插件 jclasslib Bytecode viewer (在线查看字节码指令)
 插件地址：https://plugins.jetbrains.com/plugin/9248-jclasslib-bytecode-viewer/reviews
 选中类文件，然后选择View/view Bytecode With Jclasslib


// class version 52.0 (52)
// access flags 0x21
// signature <T::Ljava/lang/Comparable;>Ljava/lang/Object;Lcom/jay/java/Generic/test/GenericInterfaceTest<TT;>;
// declaration: com/jay/java/Generic/test/GenericByteCodeTest<T extends java.lang.Comparable> implements com.jay.java.Generic.test.GenericInterfaceTest<T>
public class com/jay/java/Generic/test/GenericByteCodeTest implements com/jay/java/Generic/test/GenericInterfaceTest {

  // compiled from: GenericByteCodeTest.java

  // access flags 0x2
  // signature Ljava/util/List<TT;>;
  // declaration: items extends java.util.List<T>
  private Ljava/util/List; items

  // access flags 0x1
  public <init>()V
   L0
    LINENUMBER 12 L0
    ALOAD 0
    INVOKESPECIAL java/lang/Object.<init> ()V
   L1
    LINENUMBER 10 L1
    ALOAD 0
    NEW java/util/ArrayList
    DUP
    BIPUSH 10
    INVOKESPECIAL java/util/ArrayList.<init> (I)V
    PUTFIELD com/jay/java/Generic/test/GenericByteCodeTest.items : Ljava/util/List;
   L2
    LINENUMBER 14 L2
    RETURN
   L3
    LOCALVARIABLE this Lcom/jay/java/Generic/test/GenericByteCodeTest; L0 L3 0
    // signature Lcom/jay/java/Generic/test/GenericByteCodeTest<TT;>;
    // declaration: this extends com.jay.java.Generic.test.GenericByteCodeTest<T>
    MAXSTACK = 4
    MAXLOCALS = 1

    //todo 泛型类中独有的标记，普通类没有，JDK5加入，标记了定义时的成员签名信息（参数列表，参数类型，返回值等）
  // access flags 0x2
  // signature <T::Ljava/lang/Comparable;>(TT;)V
  // declaration: void sort<T extends java.lang.Comparable>(T)
  private sort(Ljava/lang/Comparable;)V
   L0
    LINENUMBER 17 L0
    ALOAD 1
    INVOKEVIRTUAL java/lang/Object.toString ()Ljava/lang/String;
    POP
   L1
    LINENUMBER 18 L1
    RETURN
   L2
    LOCALVARIABLE this Lcom/jay/java/Generic/test/GenericByteCodeTest; L0 L2 0
    // signature Lcom/jay/java/Generic/test/GenericByteCodeTest<TT;>;
    // declaration: this extends com.jay.java.Generic.test.GenericByteCodeTest<T>
    LOCALVARIABLE items Ljava/lang/Comparable; L0 L2 1
    // signature TT;
    // declaration: items extends T
    MAXSTACK = 1
    MAXLOCALS = 2

  // access flags 0x1
  // signature (TT;)V
  // declaration: void set(T)
  public set(Ljava/lang/Comparable;)V
   L0
    LINENUMBER 27 L0
    ALOAD 0
    GETFIELD com/jay/java/Generic/test/GenericByteCodeTest.items : Ljava/util/List;
    ALOAD 1
    INVOKEINTERFACE java/util/List.add (Ljava/lang/Object;)Z (itf)
    POP
   L1
    LINENUMBER 28 L1
    RETURN
   L2
    LOCALVARIABLE this Lcom/jay/java/Generic/test/GenericByteCodeTest; L0 L2 0
    // signature Lcom/jay/java/Generic/test/GenericByteCodeTest<TT;>;
    // declaration: this extends com.jay.java.Generic.test.GenericByteCodeTest<T>
    LOCALVARIABLE t Ljava/lang/Comparable; L0 L2 1
    // signature TT;
    // declaration: t extends T
    MAXSTACK = 2
    MAXLOCALS = 2

  // access flags 0x1
  // signature ()TT;
  // declaration: T get()
  public get()Ljava/lang/Comparable;
   L0
    LINENUMBER 32 L0
    ALOAD 0
    GETFIELD com/jay/java/Generic/test/GenericByteCodeTest.items : Ljava/util/List;
    INVOKEINTERFACE java/util/List.isEmpty ()Z (itf)
    IFNE L1
   L2
    LINENUMBER 33 L2
    ALOAD 0
    GETFIELD com/jay/java/Generic/test/GenericByteCodeTest.items : Ljava/util/List;
    ICONST_0
    INVOKEINTERFACE java/util/List.get (I)Ljava/lang/Object; (itf)
    CHECKCAST java/lang/Comparable
    ARETURN
   L1
    LINENUMBER 35 L1
   FRAME SAME
    ACONST_NULL
    ARETURN
   L3
    LOCALVARIABLE this Lcom/jay/java/Generic/test/GenericByteCodeTest; L0 L3 0
    // signature Lcom/jay/java/Generic/test/GenericByteCodeTest<TT;>;
    // declaration: this extends com.jay.java.Generic.test.GenericByteCodeTest<T>
    MAXSTACK = 2
    MAXLOCALS = 1

  // access flags 0x1
  public equals(Ljava/lang/Object;)Z
   L0
    LINENUMBER 41 L0
    ALOAD 0
    ALOAD 1
    INVOKESPECIAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IRETURN
   L1
    LOCALVARIABLE this Lcom/jay/java/Generic/test/GenericByteCodeTest; L0 L1 0
    // signature Lcom/jay/java/Generic/test/GenericByteCodeTest<TT;>;
    // declaration: this extends com.jay.java.Generic.test.GenericByteCodeTest<T>
    LOCALVARIABLE obj Ljava/lang/Object; L0 L1 1
    MAXSTACK = 2
    MAXLOCALS = 2

  // access flags 0x1041
  public synthetic bridge get()Ljava/lang/Object;
   L0
    LINENUMBER 8 L0
    ALOAD 0
    INVOKEVIRTUAL com/jay/java/Generic/test/GenericByteCodeTest.get ()Ljava/lang/Comparable;
    ARETURN
   L1
    LOCALVARIABLE this Lcom/jay/java/Generic/test/GenericByteCodeTest; L0 L1 0
    // signature Lcom/jay/java/Generic/test/GenericByteCodeTest<TT;>;
    // declaration: this extends com.jay.java.Generic.test.GenericByteCodeTest<T>
    MAXSTACK = 1
    MAXLOCALS = 1

  // access flags 0x1041
  public synthetic bridge set(Ljava/lang/Object;)V
   L0
    LINENUMBER 8 L0
    ALOAD 0
    ALOAD 1
    CHECKCAST java/lang/Comparable
    INVOKEVIRTUAL com/jay/java/Generic/test/GenericByteCodeTest.set (Ljava/lang/Comparable;)V
    RETURN
   L1
    LOCALVARIABLE this Lcom/jay/java/Generic/test/GenericByteCodeTest; L0 L1 0
    // signature Lcom/jay/java/Generic/test/GenericByteCodeTest<TT;>;
    // declaration: this extends com.jay.java.Generic.test.GenericByteCodeTest<T>
    MAXSTACK = 2
    MAXLOCALS = 2
}


 */


/*
为什么 javac 命令编译源文件 还保留着泛型类型
泛型擦除后的残留，保留了定义的格式
存在类的常量池里面
public class GenericByteCodeTest<T extends Comparable<T>> implements GenericInterfaceTest<T> {
    private List<T> items = new ArrayList(10);

    public GenericByteCodeTest() {
    }

    public void set(T var1) {
        this.items.add(var1);
    }

    public T get() {
        return !this.items.isEmpty() ? (Comparable)this.items.get(0) : null;
    }

    public boolean equals(Object var1) {
        return super.equals(var1);
    }
}
 */
