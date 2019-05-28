package com.jay.java.refect.practice.dynamicProxy.retrofit;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * Author：Jay On 2019/5/18 22:51
 * <p>
 * Description: Retrofit中的动态代理
 */
public class RetrofitTest {
    //Retrofit turns your HTTP API into a Java interface.
    public interface GitHubService {
        @GET("users/{user}/repos")
        Call<List<Object>> listRepos(@Path("user") String user);
    }

    public static void main(String[] args) {
        //The Retrofit class generates an implementation of the GitHubService interface.
        Retrofit retrofit = new Retrofit.Builder()
                .build();
        GitHubService service = retrofit.create(GitHubService.class);
        /**
         *       return Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new InvocationHandler() {
         *             private final Platform platform = Platform.get();
         *             private final Object[] emptyArgs = new Object[0];
         *
         *             public Object invoke(Object proxy, Method method, @Nullable Object[] args) throws Throwable {
         *                 if (method.getDeclaringClass() == Object.class) {
         *                     return method.invoke(this, args);
         *                 } else {
         *                     return this.platform.isDefaultMethod(method) ? this.platform.invokeDefaultMethod(method, service, proxy, args) : Retrofit.this.loadServiceMethod(method).invoke(args != null ? args : this.emptyArgs);
         *                 }
         *             }
         *         });
         */
    }


}
