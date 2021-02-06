package com.example.hw_31android3.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw_31android3.R;
import com.example.hw_31android3.data.models.Post;

import java.util.ArrayList;
import java.util.List;

public class PostInfo extends AppCompatActivity {
    private TextView tvTitle, tvContent, tvUser, tvGroup;
    private String title;
    private String content;
    private String user;
    private String group;
    private RecyclerView postInfoRV;
    private List<Post> posts;
    private PostInfoAdapter postinfoAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_info);
        init();
        getPost();
        tvTitle.setText(title);
        tvContent.setText(content);
        tvUser.setText(user);
        tvGroup.setText(group);
    }

    private void getPost() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        user = String.valueOf(intent.getExtras().getInt("user"));
        group = String.valueOf(intent.getExtras().getInt("group"));
    }

    private void init() {
        tvTitle = findViewById(R.id.tv_title_info);
        tvContent = findViewById(R.id.tv_content_info);
        tvUser = findViewById(R.id.tv_user_info);
        tvGroup = findViewById(R.id.tv_group_info);
//        posts = new ArrayList<>();
//        postInfoRV = findViewById(R.id.postsInfoRV);
//        postinfoAdapter = new PostInfoAdapter(posts,this);
//        postinfoAdapter.notifyDataSetChanged();
//        postInfoRV.setAdapter(postinfoAdapter);
    }
}
