package com.example.hw_31android3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.hw_31android3.R;
import com.example.hw_31android3.data.RetrofitBuilder;
import com.example.hw_31android3.data.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Post post = new Post("rrrrrrrrr", "Content_Android_Updateeeeeeeeeee", 3, 5);
        createPost(post);
        updatePost(post,1);
        deletePost(1);


    }

    public void createPost(Post post) {
        RetrofitBuilder.getInstance().createPost(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("post", "Create_Success");
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });

    }

    public void updatePost(Post post, int id) {

        RetrofitBuilder.getInstance().updatePost(id,post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("post", "Update_Success");
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.d("post", t.getMessage());

            }
        });
    }

    public void deletePost(int id) {
        RetrofitBuilder.getInstance().deletePost(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    private void getPost() {
        RetrofitBuilder.getInstance().getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() == null) {
                    Log.i("post", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
}