package com.pi.bidamla.network;

import android.content.Context;

import com.pi.bidamla.BuildConfig;
import com.pi.bidamla.data.remote.BaseModel.BaseResponse;
import com.pi.bidamla.helper.LocalStorage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
    private static Gson gson = new GsonBuilder().create();
    private static GsonConverterFactory converterFactory = GsonConverterFactory.create(gson);

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(converterFactory);

    public static Converter<ResponseBody, BaseResponse> bodyConverter;

    public static <T> T createService(Context context, Class service) {
        final String token = LocalStorage.getToken(context);
        httpClientBuilder.interceptors().clear();

        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder()
                        .header("Accept-Language", "tr-TR,tr;q=1.0")
                        .method(original.method(), original.body());

                if (token != null) {
                    requestBuilder.addHeader("Authorization", "Bearer " + token);
                }

                return  chain.proceed(requestBuilder.build());
            }
        });

        OkHttpClient client = httpClientBuilder.writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .build();

        Retrofit retrofit = retrofitBuilder
                .client(client)
                .build();

        bodyConverter = retrofit.responseBodyConverter(BaseResponse.class, new Annotation[0]);
        return (T) retrofit.create(service);
    }

}