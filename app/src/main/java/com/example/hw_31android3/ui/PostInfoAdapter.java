package com.example.hw_31android3.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw_31android3.R;
import com.example.hw_31android3.data.models.Post;

import java.util.ArrayList;
import java.util.List;

public class PostInfoAdapter extends RecyclerView.Adapter<PostInfoAdapter.PostInfoVH> {
    public List<Post> posts = new ArrayList<>();
    private Context context;

    public PostInfoAdapter(List<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public PostInfoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_info_item_view, parent, false);
        return new PostInfoVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostInfoVH holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class PostInfoVH extends RecyclerView.ViewHolder {
        TextView tv_title, tv_content, tv_user, tv_group;

        public PostInfoVH(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_user = itemView.findViewById(R.id.tv_user);
            tv_group = itemView.findViewById(R.id.tv_group);
        }

        public void bind() {
//            tv_title.setText(title);
//            tv_content.setText(post.getContent());
//            tv_user.setText(post.getUser());
//            tv_group.setText(post.getGroup());
        }
    }
}
