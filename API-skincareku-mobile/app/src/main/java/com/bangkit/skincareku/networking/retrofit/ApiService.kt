package com.bangkit.skincareku.networking.retrofit

import com.bangkit.skincareku.networking.response.Product
import com.bangkit.skincareku.networking.response.RegisterResponse
import com.bangkit.skincareku.networking.response.UpdateUserResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("users/register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @PATCH("users/updateUser")
    fun updateProfile(
        @Field("email") email: String,
        @Field("gender") gender: String,
        @Field("skinProblem") skinProblem: String,
        @Field("allergy") allergy: String,
        @Field("birthDate") birthDate: String
    ): Call<UpdateUserResponse>

    @GET("products")
    fun getProducts(): Call<Product>

}