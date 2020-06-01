package com.jay.java.a_generic.demo;


public interface GenericInterfaceTest<T> {

    void set(T t);

    T get();
}
/*
字节码信息
// class version 52.0 (52)
// access flags 0x601
// signature <T:Ljava/lang/Object;>Ljava/lang/Object;
// declaration: com/jay/java/Generic/test/GenericInterfaceTest<T>
public abstract interface com/jay/java/Generic/test/GenericInterfaceTest {

  // compiled from: GenericInterfaceTest.java

  // access flags 0x401
  // signature (TT;)V
  // declaration: void set(T)
  public abstract set(Ljava/lang/Object;)V

  // access flags 0x401
  // signature ()TT;
  // declaration: T get()
  public abstract get()Ljava/lang/Object;
}
 */