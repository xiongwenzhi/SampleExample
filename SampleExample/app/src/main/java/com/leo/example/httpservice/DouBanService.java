package com.leo.example.httpservice;


import com.leo.example.info.ListDTO;
import com.leo.example.info.SubjectsInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by leo on 16/3/20.
 */
public interface DouBanService {

    // 获取库, 获取的是数组
    @GET("v2/movie/in_theaters")
    Call<ListDTO<SubjectsInfo>> getRepoData(@Query("count") String count);

    // 获取库, 获取的是数组
    @GET("v2/movie/top250")
    Call<ListDTO<SubjectsInfo>> getTop250(@Query("count") String count);
}
