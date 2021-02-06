package com.example.hw_31android3.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hw_31android3.R;
import com.example.hw_31android3.data.RetrofitBuilder;
import com.example.hw_31android3.data.models.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePost extends AppCompatActivity {
    private EditText etTitle;
    private EditText etContent;
    private EditText etUser;
    private EditText etGroup;
    private String title,content;
    private int user;
    private int group;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        init();
    }

    private void init() {
        etTitle = findViewById(R.id.et_title);
        etContent = ((EditText)findViewById(R.id.et_content));
        etUser = findViewById(R.id.et_user);
        etGroup = findViewById(R.id.et_group);
    }


    public void createPost(Post post) {
        RetrofitBuilder.getInstance().createPost(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("post", "Create_Success");

                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.i("errpost", t.getMessage());
            }
        });

    }

    public void OnclickSend(View view) {
        title= etTitle.getText().toString();
        content=etContent.getText().toString();
        user= Integer.parseInt(etUser.getText().toString());
        group= Integer.parseInt(etGroup.getText().toString());
        Post post = new Post(title,content,user,group);
        createPost(post);
        Intent intent = new Intent(CreatePost.this,MainActivity.class);
        startActivity(intent);
    }
}
