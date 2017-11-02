package com.example.hp.json.custom;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.json.R;
import com.example.hp.json.model.Post;

import java.util.List;

/**
 * Created by hp on 11/2/2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> posts;
    private OnPostClickListener onPostClickListener;
    public PostAdapter(List<Post> posts) {
            this.posts = posts;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(viewType, parent, false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PostViewHolder holder, int position) {
        Post post=posts.get(position);
        holder.tvUserId.setText(post.getUserId());
        holder.tvTitle.setText(post.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPostClickListener.clicked(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_post;
    }

    @Override
    public int getItemCount() {
        return null!=posts ? posts.size() : 0;
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserId;
        TextView tvTitle;

        public PostViewHolder(View itemView) {
            super(itemView);
            tvUserId = itemView.findViewById(R.id.tv_useId);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }

    public void setOnPostClickListener(OnPostClickListener listener) {
        onPostClickListener = listener;
    }

    public interface OnPostClickListener {
        void clicked(int position);
    }


}
