package com.leo.example.util;

import android.content.Context;

import com.leo.example.AppContext;
import com.leo.example.Constans;
import com.leo.example.callback.ApiCallBack;
import com.leo.example.callback.DataCallBack;
import com.leo.example.http.HttpAPI;
import com.leo.example.httpservice.DouBanService;
import com.leo.example.dto.ListDTO;
import com.leo.example.info.SubjectsInfo;
import com.leo.example.ui.dialog.LoadingDialog;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by leo on 16/5/14.
 * 网络请求类
 */
public class DouBanApiUtil {
    public static DouBanService douBanService;

    public static DouBanService getService() {
        if (douBanService == null) {
            douBanService = HttpAPI.of(DouBanService.class);
        }
        return douBanService;
    }


    /**
     * 获取正在热映影片数据
     *
     * @param action1
     * @param context
     */
    public static void LoadRepoData(final Context context, Action1<ListDTO<SubjectsInfo>> action1) {
        LoadingDialog.showLoadding(context);
        getService().getRepoData(Constans.PAGE_COUNT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        LoadingDialog.hideLoadding();
                    }
                })
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        ToastUtil.showMessage(context, throwable.getMessage());
                    }
                })
                .doOnNext(action1)
                .subscribe();
    }
}
