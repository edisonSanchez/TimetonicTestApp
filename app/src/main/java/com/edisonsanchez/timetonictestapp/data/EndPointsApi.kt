package com.edisonsanchez.timetonictestapp.data

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path
import java.io.InputStream

interface EndPointsApi {

    @POST("api.php")
    @FormUrlEncoded
    fun registrarAplicacion(@Field("version") version: String, @Field("req") requerimiento: String,
                            @Field("appname") appname: String) : Call<ResponseOkCreateAppKey>

    @POST("api.php")
    @FormUrlEncoded
    fun login(@Field("version") version: String, @Field("req") requerimiento: String,
              @Field("login") email: String, @Field("pwd") password: String,
              @Field("appkey") appKey: String) : Call<ResponseOkLogin>

    @POST("api.php")
    @FormUrlEncoded
    fun createSessionKey(@Field("version") version: String, @Field("req") requerimiento: String,
                         @Field("o_u") idUser: String, @Field("u_c") oauthUser: String,
                         @Field("oauthkey") oauthKey: String,
                         @Field("restrictions") restrictions : String)
    : Call<ResponseOkCreateSessionKey>

    @POST("api.php")
    @FormUrlEncoded
    fun getAllBooks(@Field("version") version: String, @Field("req") requerimiento: String,
                    @Field("o_u") oauthUser: String, @Field("u_c") oauthKey: String,
                    @Field("sesskey") sessionKey: String) : Call<ResponseGetAllBooks>


}