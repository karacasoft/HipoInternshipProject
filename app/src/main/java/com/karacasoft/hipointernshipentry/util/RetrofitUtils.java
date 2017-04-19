package com.karacasoft.hipointernshipentry.util;

import com.karacasoft.hipointernshipentry.data.FlickrService;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mahmutkaraca on 4/19/17.
 */

public class RetrofitUtils {

    private static FlickrService service;

    public static FlickrService getFlickrService() {
        if(service == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    HttpUrl originalUrl = original.url();

                    HttpUrl url = originalUrl.newBuilder()
                            .addQueryParameter("api_key", APIKeyProvider.getAPIKey())
                            .build();

                    Request newRequest = original.newBuilder()
                            .url(url)
                            .build();
                    return chain.proceed(newRequest);
                }
            });

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.flickr.com/")
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(FlickrService.class);
        }
        return service;
    }

}
