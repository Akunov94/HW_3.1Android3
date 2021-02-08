package com.example.hw_31android3.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hw_31android3.R;
import com.example.hw_31android3.data.RetrofitBuilder;
import com.example.hw_31android3.data.models.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePost extends AppCompatActivity {
    private EditText etPostId;
    private EditText etTitle;
    private EditText etContent;
    private EditText etUser;
    private EditText etGroup;
    private String p_id;
    private String title;
    private String p_title;
    private String content;
    private String p_content;
    private String p_user;
    private String p_group;
    private int postId;
    private int user;
    private int group;
    private boolean postArgs = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        init();
    }

    private void init() {
        etPostId = findViewById(R.id.et_postId);
        etTitle = findViewById(R.id.et_title);
        etContent = ((EditText) findViewById(R.id.et_content));
        etUser = findViewById(R.id.et_user);
        etGroup = findViewById(R.id.et_group);
        if (getIntent().getStringExtra("title_info") != null) {
            getPostInfo();
        } else {
            Toast.makeText(this, "ololo", Toast.LENGTH_SHORT).show();
        }

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
        if (getIntent()!=null){
            postUpdate();
        }else {
            postAdd();
        }

    }
    private void postUpdate() {
        title = etTitle.getText().toString();
        content = etContent.getText().toString();
        user = Integer.parseInt(etUser.getText().toString());
        group = Integer.parseInt(etGroup.getText().toString());
        Post pUpdate = new Post(title, content, user, group);
        updatePost(pUpdate,postId);
        Intent intent = new Intent(CreatePost.this, MainActivity.class);
        startActivity(intent);
    }

    private void postAdd() {
        title = etTitle.getText().toString();
        content = etContent.getText().toString();
        user = Integer.parseInt(etUser.getText().toString());
        group = Integer.parseInt(etGroup.getText().toString());
        Post createPost = new Post(title, content, user, group);
        createPost(createPost);
        Intent intent = new Intent(CreatePost.this, MainActivity.class);
        startActivity(intent);
    }

    private void updatePost(Post post, int postId) {
        RetrofitBuilder.getInstance().updatePost(postId, post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }


    public void getPostInfo() {
        Intent intent = getIntent();
        if (intent != null) {
            p_id = String.valueOf(intent.getExtras().getInt("id_info"));
            p_user = String.valueOf(intent.getExtras().getInt("user_info"));
            p_title = intent.getStringExtra("title_info");
            p_content = intent.getStringExtra("content_info");
            p_user = String.valueOf(intent.getExtras().getInt("user_info"));
            p_group = String.valueOf(intent.getExtras().getInt("group_info"));
            Log.d("info_id", p_id);
            Log.d("infoT", p_title);
            Log.d("infoC", p_content);
            Log.d("infoU", p_user);
            Log.d("infoG", p_group);
            postId = Integer.parseInt(p_id);
            int postUser = Integer.parseInt(p_user);
            int postGroup = Integer.parseInt(p_group);
            etPostId.setText(p_id);
            etTitle.setText(p_title);
            etContent.setText(p_content);
            etUser.setText(p_user);
            etGroup.setText(p_group);
            //Post postUpdate = new Post(title,content,postUser,postGroup);
            //updatePost(postUpdate,post_id);
        }


    }
}
