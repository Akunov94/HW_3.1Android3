package com.example.hw_31android3.ui;

import android.content.Context;
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

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostsViewHolder> {
    public List<Post> posts = new ArrayList<>();
    private Context context;
    private OnItemClick onItemClick;

    public PostAdapter(List<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_item_view, parent, false);
        return new PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        holder.bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(Post post) {
        posts.add(0, post);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        posts.remove(position);
        notifyDataSetChanged();
    }

    class PostsViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;

        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.postClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemClick.postLongClick(getAdapterPosition());
                    return true;
                }
            });
        }

        void bind(Post post) {
            tv_title.setText(post.getTitle());
        }
    }

    public interface OnItemClick {
        void postClick(int position);
        void postLongClick(int position);
    }


}
