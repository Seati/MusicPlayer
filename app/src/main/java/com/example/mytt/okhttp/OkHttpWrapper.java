package com.example.mytt.okhttp;


import android.content.Context;

import com.example.mytt.utils.VerifyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;


/**
 * OkHttp封装 ,封装了get,post,上传,下载
 *
 * @author yang
 * @since 2016-8-8 上午11:43:06
 */
public class OkHttpWrapper {

    /**
     * get请求
     *
     * @param api                api接口
     * @param cacheControl       缓存控制
     * @param hint               log提示
     * @param httpResultCallBack 结果回调
     * @param tag                请求标识
     */
    public static void get(Context context,String api, CacheControl cacheControl, final String hint,
                           final HttpResultCallBack httpResultCallBack, final int tag) {

        if (!VerifyUtils.isHasNet(context)) {
            httpResultCallBack.httpResultCallBack(HttpApi.TAG_NET_ERR, "", -1);
            return;
        }
        Request.Builder builder = new Request.Builder();
        builder.cacheControl(cacheControl);
        builder.url(api);
        Call call = OkHttpClientWrapper.getInstance().newCall(builder.build());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    String result = response.body().string();

                    httpResultCallBack.httpResultCallBack(tag, result, -1);
                    response.body().close();
                } else {
                    httpResultCallBack.httpResultCallBack(HttpApi.TAG_REQ_ERR, "", -1);
                }
            }

            @Override
            public void onFailure(Call call, IOException exception) {
                httpResultCallBack.httpResultCallBack(HttpApi.TAG_REQ_ERR, "", -1);
            }
        });
    }

    /**
     * post请求
     *
     * @param api                api接口
     * @param formBody           请求实体
     * @param cacheControl       缓存控制
     * @param hint               log提示
     * @param httpResultCallBack 结果回调
     * @param tag                请求标识
     */
    public static void post(Context context ,String api, FormBody formBody, CacheControl cacheControl, final String hint,
                            final HttpResultCallBack httpResultCallBack, final int tag) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < formBody.size(); i++) {
            sb.append(formBody.encodedName(i));
            sb.append("=");
            sb.append(formBody.encodedValue(i));
            sb.append("&");
        }
        if (!VerifyUtils.isHasNet(context)) {
            httpResultCallBack.httpResultCallBack(HttpApi.TAG_NET_ERR, "", -1);
            return;
        }
        Request.Builder builder = new Request.Builder();
        builder.addHeader("User-Agent", "Java");
        builder.cacheControl(cacheControl);
        builder.url(api);
        builder.post(formBody);
        Call call = OkHttpClientWrapper.getInstance().newCall(builder.build());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    String result = response.body().string();
                    httpResultCallBack.httpResultCallBack(tag, result, -1);
                    response.body().close();
                } else {
                    httpResultCallBack.httpResultCallBack(HttpApi.TAG_REQ_ERR, "", -1);
                }
            }

            @Override
            public void onFailure(Call call, IOException exception) {
                httpResultCallBack.httpResultCallBack(HttpApi.TAG_REQ_ERR, "", -1);
            }
        });
    }

    /**
     * 上传
     *
     * @param api                上传接口
     * @param body               上传实体
     * @param hint               log提示
     * @param httpResultCallBack 结果回调
     * @param tag                请求标识
     */
    public static void upLoad(Context context,String api, MultipartBody body, final String hint,
                              final HttpResultCallBack httpResultCallBack, final int tag) {
        if (!VerifyUtils.isHasNet(context)) {
            httpResultCallBack.httpResultCallBack(HttpApi.TAG_NET_ERR, "", -1);
            return;
        }
        Request.Builder builder = new Request.Builder();
     //   builder.addHeader("Cookie", "sid=" + SPAccounts.getString(SPAccounts.KEY_APP_SID, ""));
        builder.url(api);
        builder.post(body);
        Call call = OkHttpClientWrapper.getInstance().newCall(builder.build());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    String result = response.body().string();
                    httpResultCallBack.httpResultCallBack(tag, result, -1);
                    response.body().close();
                } else {
                    httpResultCallBack.httpResultCallBack(HttpApi.TAG_REQ_ERR, "", -1);
                }
            }

            @Override
            public void onFailure(Call call, IOException exception) {
                httpResultCallBack.httpResultCallBack(HttpApi.TAG_REQ_ERR, "", -1);
            }
        });
    }

    /**
     * 下载
     *
     * @param api                下载接口
     * @param path               保存文件的路径
     * @param hint               log提示
     * @param httpResultCallBack 结果回调
     * @param tag                请求标识
     */
    public static void download(Context context,String api, final String path, final String hint,
                                final HttpResultCallBack httpResultCallBack, final int tag) {

        if (!VerifyUtils.isHasNet(context)) {
            httpResultCallBack.httpResultCallBack(HttpApi.TAG_NET_ERR, "", -1);
            return;
        }
        Request request = new Request.Builder().url(api).build();
        Call call = OkHttpClientWrapper.getInstance().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    long contentSize = response.body().contentLength();
                    long contentDownLoadSize = 0;
                    InputStream is = response.body().byteStream();
                    File file = new File(path);
                    FileOutputStream fos = new FileOutputStream(file);
                    byte[] buf = new byte[1024 * 8];
                    int len = -1;
                    int lastProgress = -1;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        contentDownLoadSize = contentDownLoadSize + len;
                        int progress = (int) (contentDownLoadSize * 100 / contentSize);
                        if (progress != lastProgress) {
                            httpResultCallBack.httpResultCallBack(tag, path, progress);
                            lastProgress = progress;
                        }
                    }
                    is.close();
                    fos.flush();
                    fos.close();
                    response.body().close();
                } else {
                    httpResultCallBack.httpResultCallBack(HttpApi.TAG_REQ_ERR, "", -1);
                }
            }

            @Override
            public void onFailure(Call call, IOException exception) {
                httpResultCallBack.httpResultCallBack(HttpApi.TAG_REQ_ERR, "", -1);
            }
        });
    }

	/*http请求结果回调
 * Created by Y on 2017/7/27.
 */

    public interface HttpResultCallBack {
        /**
         * http请求结果回调
         *
         * @param tag      请求标识
         * @param result   请求结果
         * @param progress 下载进度
         */
        public void httpResultCallBack(int tag, String result, int progress);
    }


}
