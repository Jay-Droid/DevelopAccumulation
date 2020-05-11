package com.jay.java.泛型.practice;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 泛型实践测试类 1, 泛型解析JSON数据封装 2，泛型+反射实现集合排序工具类 3，Gson库中的泛型使用-TypeToken获取泛型类型
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-11-28 16:20
 */
public class GenericPractice {

  public static void main(String[] args) {
    int demoIndex = 2;

    switch (demoIndex) {
      case 1:
        {
          // Demo1:泛型解析JSON数据封装
          Demo1();
          break;
        }
      case 2:
        {
          // Demo2:泛型+反射实现集合排序工具类
          Demo2();
          break;
        }
      case 3:
        {
          // Demo3:Gson库中的泛型使用-TypeToken获取泛型类型
          Demo3();
          break;
        }
    }
  }

  /** Demo1:泛型解析JSON数据封装 */
  private static void Demo1() {
    System.out.println("-----Demo1-----\n\n");
    System.out.println("-----泛型解析JSON数据封装-----");
    String jsonStr =
        "{\n"
            + "    \"code\":200,\n"
            + "    \"msg\":\"成功\",\n"
            + "    \"data\":{\n"
            + "        \"name\":\"JayDroid\",\n"
            + "        \"email\":\"jay_droid@163.com\"\n"
            + "    }\n"
            + "}";
    Gson gson = new Gson();
    // 解析时传入具体的data类
    DataResponse<User> userBean =
        gson.fromJson(jsonStr, new TypeToken<DataResponse<User>>() {}.getType());
    System.out.println(userBean.getData().getName());
    System.out.println(userBean.getData().getEmail());
  }

  /** 接口数据接收基类 */
  public class BaseResponse {

    private int code;

    private String msg;

    public int getCode() {
      return code;
    }

    public void setCode(int code) {
      this.code = code;
    }

    public String getMsg() {
      return msg;
    }

    public void setMsg(String msg) {
      this.msg = msg;
    }
  }

  /** 公共实体类 */
  public class DataResponse<T> extends BaseResponse {

    private T data;

    public T getData() {
      return data;
    }

    public void setData(T data) {
      this.data = data;
    }
  }

  /** 用户实体类 */
  public static class User {
    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    private Date time;

    public User(String name, Date time) {
      this.name = name;
      this.time = time;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    @Override
    public String toString() {
      return "User{" + "name='" + name + '\'' + ", email='" + email + '\'' + ", time=" + time + '}';
    }
  }

  /** Demo2:泛型+反射实现集合排序工具类 */
  private static void Demo2() {
    System.out.println("-----Demo2-----\n\n");
    System.out.println("-----泛型+反射实现集合排序工具类-----");

    List<User> movieList = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      movieList.add(new User("User" + i, new Date()));
    }
    System.out.println("排序前:" + movieList.toString());

    //        sortAnyList(movieList, "name", true);
    System.out.println("按name正序排：" + movieList.toString());

    //        sortAnyList(movieList, "name", false);
    System.out.println("按name逆序排：" + movieList.toString());
  }

  /**
   * 对任意集合的排序方法
   *
   * @param targetList 要排序的实体类List集合
   * @param sortField 排序字段
   * @param sortMode true正序，false逆序
   */
  //    public static <T> void sortAnyList(List<T> targetList, final String sortField, final boolean
  // sortMode) {
  //        if (targetList == null || targetList.size() < 2 || sortField == null ||
  // sortField.length() == 0) {
  //            return;
  //        }
  //        Collections.sort(targetList, new Comparator<Object>() {
  //            @Override
  //            public int compare(Object obj1, Object obj2) {
  //                int retVal = 0;
  //                try {
  //                    // 获取getXxx()方法名称
  //                    String methodStr = "get" + sortField.substring(0, 1).toUpperCase() +
  // sortField.substring(1);
  //                    // 获取getXxx()的Method对象
  //                    Method method1 = ((T) obj1).getClass().getMethod(methodStr, null);
  //                    Method method2 = ((T) obj2).getClass().getMethod(methodStr, null);
  //                    // 获取值进行比较
  //                    if (sortMode) {
  //                        retVal = method1.invoke(((T) obj1),
  // null).toString().compareTo(method2.invoke(((T) obj2), null).toString());
  //                    } else {
  //                        retVal = method2.invoke(((T) obj2),
  // null).toString().compareTo(method1.invoke(((T) obj1), null).toString());
  //                    }
  //                } catch (Exception e) {
  //                    System.out.println("List<" + ((T) obj1).getClass().getName() + ">排序异常！");
  //                    e.printStackTrace();
  //                }
  //                return retVal;
  //            }
  //        });
  //    }

  /** Demo3:Gson库中的泛型使用-TypeToken获取泛型类型 */
  private static void Demo3() {
    System.out.println("-----Demo3-----\n\n");
    System.out.println("-----Gson库中的泛型使用-TypeToken获取泛型类型-----");
  }
}
