package com.lmm.wddog.http;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit.Ok3Client;
import com.lmm.wddog.App;
import com.lmm.wddog.http.api.FoodierApi;
import com.lmm.wddog.http.api.HealthApi;
import com.lmm.wddog.http.api.JjApi;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.converter.GsonConverter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class HttpUtils {

    private static final RestAdapter.LogLevel logLevel = RestAdapter.LogLevel.NONE;

    private RestAdapter foodieRestAdapter;
    private RestAdapter jjRestAdapter;
    private RestAdapter jj8RestAdapter;
    private RestAdapter HealthRestAdapter;
    private Gson gson;
    private Context context;
    private static HttpUtils sHttpUtils;
    private static FoodierApi sFoodierClient;
    private static JjApi sJjApiClient;
    private static JjApi sJj8ApiClient;
    private static HealthApi sHealthClient;
    //private static RetrofitHttpClient sDouBanClient;
    //private static RetrofitHttpClient sDongTingClient;
    /**
     * 分页数据，每页的数量
     */
    public static int per_page = 10;
    public static int per_page_more = 20;


    public void setContext(Context context) {
        this.context = context;
    }

    public static HttpUtils getInstance() {
        if (sHttpUtils == null) {
            sHttpUtils = new HttpUtils();
        }
        return sHttpUtils;
    }

    public FoodierApi getFoodieServer() {
        if (sFoodierClient == null) {
            sFoodierClient = getFoodieRestAdapter().create(FoodierApi.class);
        }
        return sFoodierClient;
    }

    public HealthApi getHealthServer() {
        if (sHealthClient == null) {
            sHealthClient = getHealthAdapter().create(HealthApi.class);
        }
        return sHealthClient;
    }

    public JjApi getJjServer() {
        if (sJjApiClient == null) {
            sJjApiClient = getJjAdapter().create(JjApi.class);
        }
        return sJjApiClient;
    }

    public JjApi getJj8Server(){
        if (sJj8ApiClient == null) {
            sJj8ApiClient = getJj8Adapter().create(JjApi.class);
        }
        return sJj8ApiClient;
    }

//    public RetrofitHttpClient getDouBanServer() {
//        if (sDouBanClient == null) {
//            sDouBanClient = getDouBanAdapter().create(RetrofitHttpClient.class);
//        }
//        return sDouBanClient;
//    }
//
//    public RetrofitHttpClient getDongTingServer() {
//        if (sDongTingClient == null) {
//            sDongTingClient = getDongTingAdapter().create(RetrofitHttpClient.class);
//        }
//        return sDongTingClient;
//    }

    private RestAdapter getFoodieRestAdapter() {
        if (foodieRestAdapter == null) {
            File cacheFile = new File(App.newInstance().getCacheDir().getAbsolutePath(), "HttpCache");
            int cacheSize = 10 * 1024 * 1024;
            Cache cache = new Cache(cacheFile, cacheSize);
            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
            okBuilder.cache(cache);
            okBuilder.readTimeout(20, TimeUnit.SECONDS);
            okBuilder.connectTimeout(10, TimeUnit.SECONDS);
            okBuilder.writeTimeout(20, TimeUnit.SECONDS);
            OkHttpClient client = okBuilder.build();

            RestAdapter.Builder builder = new RestAdapter.Builder();
            builder.setClient(new Ok3Client(client));
            builder.setEndpoint(FoodierApi.FOOD_URL);//设置远程地址
            builder.setConverter(new GsonConverter(getGson()));
            foodieRestAdapter = builder.build();
            foodieRestAdapter.setLogLevel(logLevel);
        }
        return foodieRestAdapter;
    }

    private RestAdapter getJj8Adapter() {
        if (jj8RestAdapter == null) {
            //File cacheFile = new File(App.newInstance().getApplicationContext().getCacheDir().getAbsolutePath(), "HttpCache");
           // int cacheSize = 10 * 1024 * 1024;
           // Cache cache = new Cache(cacheFile, cacheSize);
//            httpGet.setHeader(
//                    "User-Agent",
//                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");

            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
          //  okBuilder.cache(cache);
            okBuilder.readTimeout(20, TimeUnit.SECONDS);
            okBuilder.connectTimeout(10, TimeUnit.SECONDS);
            okBuilder.writeTimeout(20, TimeUnit.SECONDS);
            OkHttpClient client = okBuilder.build();

            RestAdapter.Builder builder = new RestAdapter.Builder();
            builder.setClient(new Ok3Client(client));
            builder.setEndpoint(JjApi.Jj_D_URL);//设置远程地址
            builder.setConverter(new GsonConverter(getGson()));
            jj8RestAdapter = builder.build();
            jj8RestAdapter.setLogLevel(logLevel);
        }
        return jj8RestAdapter;
    }

    private RestAdapter getJjAdapter() {
        if (jjRestAdapter == null) {
            File cacheFile = new File(App.newInstance().getApplicationContext().getCacheDir().getAbsolutePath(), "HttpCache");
            int cacheSize = 10 * 1024 * 1024;
            Cache cache = new Cache(cacheFile, cacheSize);
//            httpGet.setHeader(
//                    "User-Agent",
//                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");

            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
            okBuilder.cache(cache);
            okBuilder.readTimeout(20, TimeUnit.SECONDS);
            okBuilder.connectTimeout(10, TimeUnit.SECONDS);
            okBuilder.writeTimeout(20, TimeUnit.SECONDS);
            OkHttpClient client = okBuilder.build();

            RestAdapter.Builder builder = new RestAdapter.Builder();
            builder.setClient(new Ok3Client(client));
            builder.setEndpoint(JjApi.Jj_URL);//设置远程地址
            builder.setConverter(new GsonConverter(getGson()));
            jjRestAdapter = builder.build();
            jjRestAdapter.setLogLevel(logLevel);
        }
        return jjRestAdapter;
    }

    private RestAdapter getHealthAdapter() {
        if (HealthRestAdapter == null) {
            File cacheFile = new File(App.newInstance().getApplicationContext().getCacheDir().getAbsolutePath(), "HttpCache");
            int cacheSize = 10 * 1024 * 1024;
            Cache cache = new Cache(cacheFile, cacheSize);
            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
            okBuilder.cache(cache);
            okBuilder.readTimeout(20, TimeUnit.SECONDS);
            okBuilder.connectTimeout(10, TimeUnit.SECONDS);
            okBuilder.writeTimeout(20, TimeUnit.SECONDS);
            OkHttpClient client = okBuilder.build();

            RestAdapter.Builder builder = new RestAdapter.Builder();
            builder.setClient(new Ok3Client(client));
            builder.setEndpoint(HealthApi.Healt_URL);//设置远程地址
            builder.setConverter(new GsonConverter(getGson()));
            HealthRestAdapter = builder.build();
            HealthRestAdapter.setLogLevel(logLevel);
        }
        return HealthRestAdapter;
    }

    private Gson getGson() {
        if (gson == null) {
            GsonBuilder builder = new GsonBuilder();
            builder.setFieldNamingStrategy(new AnnotateNaming());
            builder.serializeNulls();
            builder.excludeFieldsWithModifiers(Modifier.TRANSIENT);
            gson = builder.create();
        }
        return gson;
    }

    private static class AnnotateNaming implements FieldNamingStrategy {
        @Override
        public String translateName(Field field) {
            ParamNames a = field.getAnnotation(ParamNames.class);
            return a != null ? a.value() : FieldNamingPolicy.IDENTITY.translateName(field);
        }
    }


    public OkHttpClient getUnsafeOkHttpClient() {

        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};
        // Install the all-trusting trust manager
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            sslContext.init(null, trustAllCerts, new SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        // Create an ssl socket factory with our all-trusting manager
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.readTimeout(20, TimeUnit.SECONDS);
        okBuilder.connectTimeout(10, TimeUnit.SECONDS);
        okBuilder.writeTimeout(20, TimeUnit.SECONDS);
        okBuilder.sslSocketFactory(sslSocketFactory);
        okBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        OkHttpClient client = okBuilder.build();
        return client;

    }

    public Client getOkClient() {
        OkHttpClient client1;
        client1 = getUnsafeOkHttpClient();
        Client _client = new Ok3Client(client1);
        return _client;
    }
}
