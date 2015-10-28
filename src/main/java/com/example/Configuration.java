package com.example;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;

import java.io.IOException;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient client = new OkHttpClient();
        Logger logger = LoggerFactory.getLogger("LoggingInterceptor");

        client.interceptors().add(chain -> {
            Request request = chain.request();

            long t1 = System.nanoTime();
            logger.info(String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            com.squareup.okhttp.Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            logger.info(String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        });
        return client;
    }

    @Bean
    public Retrofit retrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("https://qrng.anu.edu.au/API/")
                .client(client)
                .build();
    }

    @Bean
    public RandomNumberService randomNumberService(Retrofit retrofit) {
        return retrofit.create(RandomNumberService.class);
    }
}
