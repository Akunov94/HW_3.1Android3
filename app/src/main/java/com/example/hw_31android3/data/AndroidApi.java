package com.example.hw_31android3.data;

import com.example.hw_31android3.data.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AndroidApi {
    @GET("posts")
//на получения ресурса
    Call<List<Post>> getPosts();
    @GET("posts/{id}")
    Call<Post> getPostId(@Path("id") int id);

    @PUT("posts/{id}")
//на обновление и изменение ресурса
    Call<Post> updatePost(
            @Path("id") int id,
            @Body Post post
    );

    @DELETE("posts/{id}")
//на удаление ресурса
    Call<Void> deletePost(
            @Path("id") int id
    );

    @POST("posts")
//на создание ресурса
    Call<Post> createPost(@Body Post post);
}
