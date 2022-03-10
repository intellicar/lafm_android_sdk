package com.intellicar.dummylafproject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "https://archiver1.intellicar.in/apiplatform/";

    @POST("getlaftoken")
    Call<GetTokenResponse> getLafToken(@Body GetTokenRequest getTokenRequest);
}