package com.leo.example.httpservice;


import com.leo.example.config.APIConstants;
import com.leo.example.dto.ListDTO;
import com.leo.example.info.SubjectsInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by leo on 16/3/20.
 */
public interface DouBanService {

    /**
     * 获取正在热映影片数据
     *
     * @param count
     * @return
     */
    @GET(APIConstants.API_IN_THEATERS)
    Observable<ListDTO<SubjectsInfo>> getRepoData(@Query("count") int count);

    /**
     * 获取前250影片数据
     *
     * @param count
     * @return
     */
    @GET(APIConstants.API_TOP_250)
    Call<ListDTO<SubjectsInfo>> getTop250(@Query("count") String count);
}
