package com.jay.java.DynamicProxy.practice;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author wangxuejie
 * @version 1.0
 * @date 2019-11-26 17:51
 */
public class DynamicProxyPractice {

    public static void main(String[] args) {

        int demoIndex = 1;

        switch (demoIndex) {

            case 1: {
                //Demo1: Retrofit中对动态代理的使用
                Demo1();
                break;
            }
            case 2: {
                //Demo2: 动态代理组件化路由框架实战
                Demo2();
                break;
            }
        }
    }

    /**
     * Demo1:Retrofit中对动态代理的使用
     * https://github.com/square/retrofit/blob/master/retrofit/src/main/java/retrofit2/Retrofit.java
     * <p>
     * retrofit.create(GitHubService.class);
     */
    private static void Demo1() {
        System.out.println("-----Demo1-----\n\n");
        System.out.println("-----Retrofit中对动态代理的使用-----");
        //Retrofit类生成GitHubService接口的实现。
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.github.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
        //利用动态代理技术生成代理对象

        /**
         * Retrofit利用动态代理技术生成代理对象
         * retrofit.create()
         */
//        public <T> T create(final Class<T> service) {
//            Utils.validateServiceInterface(service);
//            if (validateEagerly) {
//                eagerlyValidateMethods(service);
//            }
//            return (T) Proxy.newProxyInstance(
//            service.getClassLoader(),
//            new Class<?>[] { service },
//              InvocationHandler内部实现类
//            new InvocationHandler() {
//                        private final Platform platform = Platform.get();
//                        private final Object[] emptyArgs = new Object[0];
//                        @Override public @Nullable Object invoke(Object proxy, Method method,
//                                                                 @Nullable Object[] args) throws Throwable {
//                            // 如果方法是来自对象的方法，则不做代理
//                            if (method.getDeclaringClass() == Object.class) {
//                                return method.invoke(this, args);
//                            }
//                               判断是否是默认方法，这是1.8新增的内容
//                            if (platform.isDefaultMethod(method)) {
//                                return platform.invokeDefaultMethod(method, service, proxy, args);
//                            }
//                            return loadServiceMethod(method).invoke(args != null ? args : emptyArgs);
//                        }
//                    });
//        }



//        GitHubService service = retrofit.create(GitHubService.class);
//        //从创建的GitHubService进行的每次调用都可以向远程Web服务器发出同步或异步HTTP请求。
//        Call<List<Repo>> repos = service.listRepos("Jay-Droid");
//        repos.enqueue(new Callback<List<Repo>>() {
//            @Override
//            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
//                System.out.println("-----onResponse-----");
//                //Repo{id='76014709', name='ActivityOptions', description='android ActivityOption 中的五种转场动画', url='https://api.github.com/repos/Jay-Droid/ActivityOptions'}
//                System.out.println(response.body().get(0).toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<Repo>> call, Throwable throwable) {
//                System.out.println("-----onFailure-----");
//                System.out.println(throwable.getLocalizedMessage());
//            }
//        });
    }

    /**
     * Retrofit将您的HTTP API转换为Java接口。
     * 抽象接口对象
     */
    public interface GitHubService {
        //https://api.github.com/users/Jay-Droid/repos
        @GET("users/{user}/repos")
        Call<List<Repo>> listRepos(@Path("user") String user);
    }

    /**
     * 数据接受实体类
     */
    private class Repo {
        private String id;
        private String name;
        private String description;
        private String url;

        @Override
        public String toString() {
            return "Repo{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

  /**
   * Demo2:动态代理组件化路由框架实战 关键词：URI，Scheme，注解，动态代理
   * 详见：com.jay.develop.java.dynamic_proxy.DynamicProxyActivity 实现步骤：
   * 1.定义两个注解参数，一个标示URI注解，一个标示参数 @Documented @Target(METHOD) @Retention(RUNTIME) public @interface
   * RouterUri { String routerUri() default ""; } @Documented @Target(PARAMETER) @Retention(RUNTIME)
   * public @interface RouterParam { String value() default "";
   *
   * <p>}
   */
  private static void Demo2() {
        System.out.println("-----Demo2-----\n\n");
        System.out.println("-----动态代理组件化路由框架实战 -----");
    /**
     * 1.定义两个注解参数，一个标示URI注解，一个标示参数
     *
      //请求Url地址,在AndroidManifest文件中为每个Activity添加scheme
      @RouterUri(routerUri = "dv://com.jay.develop/router")
      void jumpToDynamicProxyPage(
              @RouterParam("info") String info,
              @RouterParam("des") String des
    );

      @Documented
      @Target(PARAMETER)
      @Retention(RUNTIME)
      public @interface RouterParam {
          String value() default "";

      }

      2.定义一个URI协议接口
      public interface IRouterUri {
      //请求Url地址,在AndroidManifest文件中为每个Activity添加scheme
     @RouterUri(routerUri = "dv://com.jay.develop/router")
     void jumpToDynamicProxyPage(
         @RouterParam("info") String info,

         @RouterParam("des") String des
         );
     }

      3.定义一个单例，内部通过动态代理机制实现跳转

         XLRouter.class

      4.)在进行XLRouter初始化

         XLRouter.initXLRouter(this);

      5.调用方式

         XLRouter.routerUri().jumpToGoodsDetail("1000110002","goods des");

      **/

    }


    /**
     * Demo3:
     */
    private static void Demo3() {
        System.out.println("-----Demo3-----\n\n");

    }
}
