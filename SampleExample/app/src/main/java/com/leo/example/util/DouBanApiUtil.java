package com.leo.example.util;

import android.content.Context;
import android.util.Log;

import com.leo.example.callback.ApiCallBack;
import com.leo.example.callback.DataCallBack;
import com.leo.example.http.HttpAPI;
import com.leo.example.httpservice.DouBanService;
import com.leo.example.info.ListDTO;
import com.leo.example.info.SubjectsInfo;
import com.leo.example.ui.dialog.LoadingDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
     * 获取豆瓣Api数据
     */
    public static void LoadRepoData(Context context, final DataCallBack<List<SubjectsInfo>> callBack) {
        LoadingDialog.showLoadding(context);
        getService().getRepoData("20").enqueue(new ApiCallBack<ListDTO<SubjectsInfo>>() {
            @Override
            public void onSuccess(ListDTO<SubjectsInfo> subjectsInfoListDTO) {
                if (callBack != null) {
                    callBack.onSuccess(subjectsInfoListDTO.getList());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (callBack != null) {
                    callBack.onFailure(t);
                }
            }

            @Override
            public void onFinish() {
                LoadingDialog.hideLoadding();
            }
        });
    }
}
