package com.xswq.chess.myapplication.http.retrofitHttp;

import com.xswq.chess.myapplication.base.BaseApi;
import com.xswq.chess.myapplication.utils.ContactUrl;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitManager{

    public static volatile  RetrofitManager retrofitManager;

    public RetrofitManager(){

    }

    public static RetrofitManager getRetrofitInstance(){

        if(retrofitManager==null){
            synchronized (RetrofitManager.class){
                if(retrofitManager==null){
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    public  Retrofit getRetrofit(){


        //手动创建一个OkHttpClient并设置超时时间缓存等设置

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10000, TimeUnit.SECONDS);
        builder.readTimeout(10000, TimeUnit.SECONDS);


        /*创建retrofit对象*/
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .baseUrl(ContactUrl.BASE_PATH)
                .baseUrl(ContactUrl.BASE_PATH3)
                .build();
        return  retrofit;
    }

    public void handleHttp(Observer mObserver, BaseApi mBaseApi){

         mBaseApi.getObservable(getRetrofit())
                 .subscribeOn(Schedulers.io())
                 .unsubscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(mObserver);
    }

    public void handleHttpPlus(Observer mObserver, BaseApi mBaseApi){

        mBaseApi.getObservable(getRetrofit())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }

                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        Observable mObservable;
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }
}
