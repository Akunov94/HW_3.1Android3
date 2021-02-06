package com.example.hw_31android3.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.hw_31android3.R;
import com.example.hw_31android3.data.RetrofitBuilder;
import com.example.hw_31android3.data.models.Post;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView postsRecyclerV;
    private PostAdapter postAdapter;
    private List<Post> posts;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        init();
        getPosts();
    }

    private void init() {
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreatePost.class);
            startActivity(intent);
        });
        postsRecyclerV = findViewById(R.id.postsRV);
        posts = new ArrayList<>();
        postAdapter = new PostAdapter(posts, this);
        postsRecyclerV.setAdapter(postAdapter);
        postAdapter.notifyDataSetChanged();
        postAdapter.setOnItemClick(new PostAdapter.OnItemClick() {
            @Override
            public void postClick(int position) {
                getPostId(position);
            }

            @Override
            public void postLongClick(int position) {
                Post post = postAdapter.posts.get(position);
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Удалить этот пост?");
                alert.setMessage(postAdapter.posts.get(position).getTitle());
                alert.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        postAdapter.remove(position);
                        postAdapter.notifyDataSetChanged();
                        deletePost(position);
                        Toast.makeText(MainActivity.this, "Успешно удалено", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setNegativeButton("Нет", (dialogInterface, i) ->
                        Toast.makeText(MainActivity.this, "Отмена", Toast.LENGTH_SHORT).show());
                alert.create().show();
            }
        });
    }


    private void getPostId(int position) {
        int postId = posts.get(position).getId();//postAdapter.posts.get(position).getId()
        RetrofitBuilder.getInstance().getPostId(postAdapter.posts.get(position).getId()).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Intent intent = new Intent(MainActivity.this, PostInfo.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("title",response.body().getTitle());
                    intent.putExtra("content",response.body().getContent());
                    intent.putExtra("user",response.body().getUser());
                    intent.putExtra("group",response.body().getGroup());
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
            }
        });

    }

    private void getPosts() {
        RetrofitBuilder.getInstance().getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (Post post : response.body()) {
                        postAdapter.setPosts(post);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    public void updatePost(Post post, int id) {

        RetrofitBuilder.getInstance().updatePost(id, post).enqueue(new Callback<Post>() {
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

    public void deletePost(int positionId) {
        RetrofitBuilder.getInstance().deletePost(postAdapter.posts.get(positionId).getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    private void showLog(String msg) {
        Log.d("tag", msg);
    }
}