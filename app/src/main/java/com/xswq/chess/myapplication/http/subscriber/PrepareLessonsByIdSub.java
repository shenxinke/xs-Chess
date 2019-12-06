package com.xswq.chess.myapplication.http.subscriber;

import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.PrepareLessonsByIdApi;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PrepareLessonsByIdSub implements Observer<String> {
    public HttpCallBackLisener mLoginHttpLisener;
    public PrepareLessonsByIdApi prepareLessonsApi;

    public PrepareLessonsByIdSub(PrepareLessonsByIdApi prepareLessonsApi) {
        this.prepareLessonsApi = prepareLessonsApi;
        mLoginHttpLisener = prepareLessonsApi.smHttpCallBackLisener;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String gsonString) {
        if (mLoginHttpLisener != null) {
            mLoginHttpLisener.onNext(gsonString, prepareLessonsApi.getUid());
        }

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
