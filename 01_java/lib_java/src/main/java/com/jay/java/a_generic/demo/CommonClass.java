package com.jay.java.a_generic.demo;

/**
 * 普通类
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2020/5/10
 */
public class CommonClass {
    private String name;

    public CommonClass() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
/*
字节码信息

// class version 52.0 (52)
// access flags 0x21
public class com/jay/java/Generic/test/CommonClass {

  // compiled from: CommonClass.java

  // access flags 0x2
  private Ljava/lang/String; name

  // access flags 0x1
  public <init>()V
   L0
    LINENUMBER 11 L0
    ALOAD 0
    INVOKESPECIAL java/lang/Object.<init> ()V
   L1
    LINENUMBER 13 L1
    RETURN
   L2
    LOCALVARIABLE this Lcom/jay/java/Generic/test/CommonClass; L0 L2 0
    MAXSTACK = 1
    MAXLOCALS = 1

  // access flags 0x1
  public getName()Ljava/lang/String;
   L0
    LINENUMBER 16 L0
    ALOAD 0
    GETFIELD com/jay/java/Generic/test/CommonClass.name : Ljava/lang/String;
    ARETURN
   L1
    LOCALVARIABLE this Lcom/jay/java/Generic/test/CommonClass; L0 L1 0
    MAXSTACK = 1
    MAXLOCALS = 1

  // access flags 0x1
  public setName(Ljava/lang/String;)V
   L0
    LINENUMBER 20 L0
    ALOAD 0
    ALOAD 1
    PUTFIELD com/jay/java/Generic/test/CommonClass.name : Ljava/lang/String;
   L1
    LINENUMBER 21 L1
    RETURN
   L2
    LOCALVARIABLE this Lcom/jay/java/Generic/test/CommonClass; L0 L2 0
    LOCALVARIABLE name Ljava/lang/String; L0 L2 1
    MAXSTACK = 2
    MAXLOCALS = 2
}

 */