package com.edisonsanchez.timetonictestapp.data

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface EndPointsApi {

    @POST("api.php")
    @FormUrlEncoded
    fun registrarAplicacion(@Field(PARAM_VERSION) version: String, @Field(PARAM_REQ) requerimiento: String,
                            @Field(PARAM_APP_NAME) appname: String) : Call<ResponseOkCreateAppKey>

    @POST("api.php")
    @FormUrlEncoded
    fun login(@Field(PARAM_VERSION) version: String, @Field(PARAM_REQ) requerimiento: String,
              @Field(PARAM_USER_LOGIN) email: String, @Field(PARAM_PASSWORD) password: String,
              @Field(PARAM_APP_KEY) appKey: String) : Call<ResponseOkLogin>

    @POST("api.php")
    @FormUrlEncoded
    fun createSessionKey(@Field(PARAM_VERSION) version: String, @Field(PARAM_REQ) requerimiento: String,
                         @Field(PARAM_O_U) idUser: String, @Field(PARAM_U_C) oauthUser: String,
                         @Field(PARAM_OAUTH_KEY) oauthKey: String,
                         @Field(PARAM_RESTRICTIONS) restrictions : String)
    : Call<ResponseOkCreateSessionKey>

    @POST("api.php")
    @FormUrlEncoded
    fun getAllBooks(@Field(PARAM_VERSION) version: String, @Field(PARAM_REQ) requerimiento: String,
                    @Field(PARAM_O_U) oauthUser: String, @Field(PARAM_U_C) oauthKey: String,
                    @Field(PARAM_SESSKEY) sessionKey: String) : Call<ResponseGetAllBooks>


}