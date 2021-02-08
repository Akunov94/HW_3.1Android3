package com.example.hw_31android3.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw_31android3.R;
import com.example.hw_31android3.data.models.Post;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostInfo extends AppCompatActivity {
    private View viewPostInfo;
    private TextView tvPostId,tvTitle, tvContent, tvUser, tvGroup;
    private String postId;
    private String title;
    private String content;
    private String user;
    private String group;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_info);
        init();
        setContentV();
    }

    private void setContentV() {
        tvPostId.setText(postId);
        tvTitle.setText(title);
        tvContent.setText(content);
        tvUser.setText(user);
        tvGroup.setText(group);
    }

    private void getPost() {
        Intent intent = getIntent();
        postId = String.valueOf(intent.getExtras().getInt("postId"));
        Log.d("postId",postId);
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        user = String.valueOf(intent.getExtras().getInt("user"));
        group = String.valueOf(intent.getExtras().getInt("group"));
    }

    private void init() {
        getPost();
        viewPostInfo = findViewById(R.id.CVPostInfo);
        viewPostInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int puser= Integer.parseInt(user);
                int pgroup = Integer.parseInt(group);
                Post post = new Post(title,content,puser,pgroup);
                putPost(post);
            }
        });
        tvPostId = findViewById(R.id.tv_postId_info);
        tvTitle = findViewById(R.id.tv_title_info);
        tvContent = findViewById(R.id.tv_content_info);
        tvUser = findViewById(R.id.tv_user_info);
        tvGroup = findViewById(R.id.tv_group_info);
    }

    private void putPost(Post post) {
        int id_post = Integer.parseInt(postId);
        Intent intent = new Intent(PostInfo.this,CreatePost.class);
        intent.putExtra("id_info",id_post);
        intent.putExtra("title_info", post.getTitle());
        intent.putExtra("content_info",post.getContent());
        intent.putExtra("user_info",post.getUser());
        intent.putExtra("group_info",post.getGroup());
        Log.d("info_id", postId);
        Log.d("infoT", post.getTitle());
        Log.d("infoC",post.getContent());
        Log.d("infoU", String.valueOf(post.getUser()));
        Log.d("infoG", String.valueOf(post.getGroup()));
        startActivity(intent);
    }
}
