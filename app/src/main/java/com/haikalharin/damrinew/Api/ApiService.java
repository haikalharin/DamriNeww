package com.haikalharin.damrinew.Api;

import com.haikalharin.damrinew.Entity.DataItem;
import com.haikalharin.damrinew.Entity.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("getOrigin")
    Call<List<DataItem>>getSemuaData(@Header("Authorization") String Authorization);
}
