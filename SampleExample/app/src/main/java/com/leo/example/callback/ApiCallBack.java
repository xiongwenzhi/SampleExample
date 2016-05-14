package com.leo.example.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by leo on 16/5/14.
 * Api接口请求回调
 */
public abstract class ApiCallBack<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response != null) {
            onSuccess(response.body());
        }
        onFinish();
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailure(t);
        onFinish();
    }


    public abstract void onSuccess(T t);

    public abstract void onFailure(Throwable t);

    public abstract void onFinish();
}
