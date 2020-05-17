package com.jay.java.Generic.test;


import java.util.ArrayList;
import java.util.List;


public class GenericByteCodeTest2<T> implements GenericInterfaceTest<T> {

    private List<T> items = new ArrayList<T>(10);

    public GenericByteCodeTest2() {
        GenericMainTest.GenericClass<String> stringGenericClass = new GenericMainTest.GenericClass<>();
        GenericMainTest.GenericClass<Integer> integerGenericClass = new GenericMainTest.GenericClass<>();
        compareClass(stringGenericClass, integerGenericClass);

    }

    private boolean compareClass(GenericMainTest.GenericClass<String> stringGenericClass, GenericMainTest.GenericClass<Integer> integerGenericClass) {
        System.out.println(stringGenericClass.getClass().toString());
        System.out.println(integerGenericClass.getClass().toString());
        Class<?> c1 = stringGenericClass.getClass();
        Class<?> c2 = integerGenericClass.getClass();
        System.out.println(c1);
        System.out.println(c2);
        return c1 == c2;
    }


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
 使用 javac 命令编译源文件
 javac GenericByteCodeTest.java GenericInterfaceTest.java

 使用 java -c 查看生成的字节码信息
 javap -c GenericByteCodeTest2.class

 或者使用插件 ASMPluin 选中类右键，选择 ASM Bytecode Viewer

// class version 52.0 (52)
// access flags 0x21
// signature <T:Ljava/lang/Object;>Ljava/lang/Object;Lcom/jay/java/Generic/test/GenericInterfaceTest<TT;>;
// declaration: com/jay/java/Generic/test/GenericByteCodeTest2<T> implements com.jay.java.Generic.test.GenericInterfaceTest<T>
public class com/jay/java/Generic/test/GenericByteCodeTest2 implements com/jay/java/Generic/test/GenericInterfaceTest {

  // compiled from: GenericByteCodeTest2.java

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
    PUTFIELD com/jay/java/Generic/test/GenericByteCodeTest2.items : Ljava/util/List;
   L2
    LINENUMBER 14 L2
    RETURN
   L3
    LOCALVARIABLE this Lcom/jay/java/Generic/test/GenericByteCodeTest2; L0 L3 0
    // signature Lcom/jay/java/Generic/test/GenericByteCodeTest2<TT;>;
    // declaration: this extends com.jay.java.Generic.test.GenericByteCodeTest2<T>
    MAXSTACK = 4
    MAXLOCALS = 1

  // access flags 0x1
  // signature (TT;)V
  // declaration: void set(T)
  public set(Ljava/lang/Object;)V
   L0
    LINENUMBER 18 L0
    ALOAD 0
    GETFIELD com/jay/java/Generic/test/GenericByteCodeTest2.items : Ljava/util/List;
    ALOAD 1
    INVOKEINTERFACE java/util/List.add (Ljava/lang/Object;)Z (itf)
    POP
   L1
    LINENUMBER 19 L1
    RETURN
   L2
    LOCALVARIABLE this Lcom/jay/java/Generic/test/GenericByteCodeTest2; L0 L2 0
    // signature Lcom/jay/java/Generic/test/GenericByteCodeTest2<TT;>;
    // declaration: this extends com.jay.java.Generic.test.GenericByteCodeTest2<T>
    LOCALVARIABLE t Ljava/lang/Object; L0 L2 1
    // signature TT;
    // declaration: t extends T
    MAXSTACK = 2
    MAXLOCALS = 2

  // access flags 0x1
  // signature ()TT;
  // declaration: T get()
  public get()Ljava/lang/Object;
   L0
    LINENUMBER 23 L0
    ALOAD 0
    GETFIELD com/jay/java/Generic/test/GenericByteCodeTest2.items : Ljava/util/List;
    INVOKEINTERFACE java/util/List.isEmpty ()Z (itf)
    IFNE L1
   L2
    LINENUMBER 24 L2
    ALOAD 0
    GETFIELD com/jay/java/Generic/test/GenericByteCodeTest2.items : Ljava/util/List;
    ICONST_0
    INVOKEINTERFACE java/util/List.get (I)Ljava/lang/Object; (itf)
    ARETURN
   L1
    LINENUMBER 26 L1
   FRAME SAME
    ACONST_NULL
    ARETURN
   L3
    LOCALVARIABLE this Lcom/jay/java/Generic/test/GenericByteCodeTest2; L0 L3 0
    // signature Lcom/jay/java/Generic/test/GenericByteCodeTest2<TT;>;
    // declaration: this extends com.jay.java.Generic.test.GenericByteCodeTest2<T>
    MAXSTACK = 2
    MAXLOCALS = 1

  // access flags 0x1
  public equals(Ljava/lang/Object;)Z
   L0
    LINENUMBER 32 L0
    ALOAD 0
    ALOAD 1
    INVOKESPECIAL java/lang/Object.equals (Ljava/lang/Object;)Z
    IRETURN
   L1
    LOCALVARIABLE this Lcom/jay/java/Generic/test/GenericByteCodeTest2; L0 L1 0
    // signature Lcom/jay/java/Generic/test/GenericByteCodeTest2<TT;>;
    // declaration: this extends com.jay.java.Generic.test.GenericByteCodeTest2<T>
    LOCALVARIABLE obj Ljava/lang/Object; L0 L1 1
    MAXSTACK = 2
    MAXLOCALS = 2
}
 */
