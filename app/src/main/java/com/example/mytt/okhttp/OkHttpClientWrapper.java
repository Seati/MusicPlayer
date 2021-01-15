package com.example.mytt.okhttp;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * OkHttpClient封装
 *
 * @author yang
 * @since 2016-8-8 上午11:42:55
 */
public class OkHttpClientWrapper {
    private static OkHttpClient sOkHttpClient;

    private OkHttpClientWrapper() {
    }

    /**
     * 获取OkHttpClient实例
     */
    protected static OkHttpClient getInstance() {
        if (sOkHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(60, TimeUnit.SECONDS);
            builder.readTimeout(60, TimeUnit.SECONDS);
            builder.writeTimeout(60, TimeUnit.SECONDS);

            /***
             * 20180627李徐添加适配https
             * https双向验证证书
             * 如果需要双向认证代码下面代码
             * 并且return HttpsUtils.isServerTrusted;
             */
            //builder.sslSocketFactory(HttpsUtils.getSslContextByCustomTrustManager(BaseApplication.getContext()).getSocketFactory());
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                   /* LogUtils.showResult("OkHttpClientWrapper", "hostname = " + hostname);
                    LogUtils.showResult("OkHttpClientWrapper", "isServerTrusted = " + HttpsUtils.isServerTrusted);
                   */ //return HttpsUtils.isServerTrusted;
                    //todo 目前仅使用单向认证
                    return true;

            }});

            // builder.cache(new
            // Cache(BaseApplication.getContext().getCacheDir(),
            // 1024 * 1024 * 10));
            // 请求结果拦截
            // builder.addNetworkInterceptor(new Interceptor() {
            //
            // @Override
            // public Response intercept(Chain chain) throws IOException {
            // Request request = chain.request();
            // Builder builder = chain.proceed(request).newBuilder();
            // builder.removeHeader("Cache-Control");// 移除服务端的缓存控制
            // builder.header("Cache-Control",
            // "max-age=" + 3600 * 24 * 365);// 自定义缓存控制
            // return builder.build();
            // }
            // });
            sOkHttpClient = builder.build();
        }
        return sOkHttpClient;
    }

}
