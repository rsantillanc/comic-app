package comics.core.model.rest;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Renzo D. Santillán Ch. on 23/02/2017.12:52 AM
 * http://rsantillanc.pe.hu/me/
 */

public class RestAdapter {

    private final HttpUrl urlBase;
    private static final long HTTP_CONNECT_TIMEOUT = 10000;
    private static final long HTTP_READ_TIMEOUT = 6000;

    public RestAdapter(String urlBase) {
        this.urlBase = HttpUrl.parse(urlBase);
    }

    public MarvelApi createApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create())
                .client(makeOkHttpClient())
                .build();
        return retrofit.create(MarvelApi.class);
    }

    public MarvelApi createApiWithCustomConverter(@NonNull Type type, @NonNull Object deserialized) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(customConverterFactory(type, deserialized))
                .client(makeOkHttpClient())
                .build();
        return retrofit.create(MarvelApi.class);
    }

    private Converter.Factory customConverterFactory(@NonNull Type type, @NonNull Object deserialized) {
        Gson build = new GsonBuilder()
                .registerTypeAdapter(type, deserialized)
                .create();
        return GsonConverterFactory.create(build);
    }

    private OkHttpClient makeOkHttpClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(HTTP_READ_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(makeLoggingInterceptor());
        return httpClientBuilder.build();
    }


    private HttpLoggingInterceptor makeLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @Override
    public String toString() {
        return "RestAdapter{" +
                "urlBase=" + urlBase.toString() +
                '}';
    }
}
