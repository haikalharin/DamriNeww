package com.haikalharin.damrinew.Api;

import com.haikalharin.damrinew.Api.ApiService;
import com.haikalharin.damrinew.Api.RetrofitApi;

public class Server {
        public static final String URL_API = "https://devapitiket.damri.co.id/apps/damriapps/";

        public static ApiService getApiService(){
            return RetrofitApi.getClient(URL_API).create(ApiService.class);
        }
    }

