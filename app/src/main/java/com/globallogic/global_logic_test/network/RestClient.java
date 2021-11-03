package com.globallogic.global_logic_test.network;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.globallogic.global_logic_test.BuildConfig;
import com.globallogic.global_logic_test.util.gson.AnnotationExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static Api API_SERVICE;
    private static Retrofit RETROFIT;
    private static OkHttpClient CLIENT;
    private static OkHttpClient.Builder BUILDER = new OkHttpClient.Builder()
            .readTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES);
    private static final String TAG = RestClient.class.getSimpleName();

    private RestClient() {
    }

    public static void init(final Context context) {
        if (RETROFIT != null) {
            Log.w("RestClient", "Singleton, call only one time!");
            return;
        }

        Gson gson = buildGson();
        CLIENT = buildOkHttp(context);

        RETROFIT = new Retrofit.Builder()
                .baseUrl("http://private-f0eea-mobilegllatam.apiary-mock.com/")
                .client(CLIENT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API_SERVICE = RETROFIT.create(Api.class);
    }


    public static OkHttpClient.Builder getBUILDER() {
        return BUILDER;
    }

    public static OkHttpClient buildOkHttp(Context context) {
        //if (BuildConfig.DEBUG)
        addBetterLogsToRequests(getBUILDER());
        return getBUILDER().build();
    }


    public static Gson buildGson() {
        return new GsonBuilder()
                .addSerializationExclusionStrategy(new AnnotationExclusionStrategy())
                .create();
    }


    //Interceptar request para mejores logs en debug
    private static void addBetterLogsToRequests(OkHttpClient.Builder okhttpBuilder) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okhttpBuilder.addInterceptor(interceptor);
    }

    public static Api getInstance() {
        return API_SERVICE;
    }


}
