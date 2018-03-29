package com.pregbuddy.network;


import com.google.gson.GsonBuilder;
import com.pregbuddy.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.pregbuddy.network.RequestURL.API_BASE_URL;
import static com.pregbuddy.network.utils.HTTP.USER_AGENT;
import static com.pregbuddy.network.utils.HTTP.USER_AGENT_VALUE;


public class RequestHelper {

    public static <S> S createRequest(Class<S> serviceClass) {
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addCallAdapterFactory(new ErrorHandlingAdapter.ErrorHandlingCallAdapterFactory())
                        .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                                .setLenient()
                                .create()));

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(60, TimeUnit.SECONDS);
        httpClient.connectTimeout(60, TimeUnit.SECONDS);


        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder requestBuilder = chain.request().newBuilder();
                    requestBuilder.addHeader(USER_AGENT, USER_AGENT_VALUE);
                    return chain.proceed(requestBuilder.build());
                }
            }).addInterceptor(loggingInterceptor);
        } else {
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder requestBuilder = chain.request().newBuilder();
                    requestBuilder.addHeader(USER_AGENT, USER_AGENT_VALUE);
                    return chain.proceed(requestBuilder.build());
                }
            });
        }
        OkHttpClient client = httpClient.build();

        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }


}
