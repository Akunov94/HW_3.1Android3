package com.example.hw_31android3.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw_31android3.R;
import com.example.hw_31android3.data.models.Post;

import java.util.ArrayList;
import java.util.List;

public class PostInfo extends AppCompatActivity {
    private RecyclerView postInfoRV;
    private List<Post> posts;
    private PostInfoAdapter postinfoAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_info);
        init();
    }

    private void init() {
        posts = new ArrayList<>();
        postInfoRV = findViewById(R.id.postsInfoRV);
        postinfoAdapter = new PostInfoAdapter(posts,this);
        postinfoAdapter.notifyDataSetChanged();
        postInfoRV.setAdapter(postinfoAdapter);
    }
}
