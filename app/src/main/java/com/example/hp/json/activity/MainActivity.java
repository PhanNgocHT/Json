package com.example.hp.json.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.hp.json.model.Post;
import com.example.hp.json.R;
import com.example.hp.json.activity.fragment.FragmentDetail;
import com.example.hp.json.activity.fragment.FragmentPost;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int position;
    private List<Post> posts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();

    }

    private void initFragment() {
        FragmentManager manager = this.getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        FragmentPost fragmentPost = new FragmentPost();

        transaction.replace(R.id.frame_layout, fragmentPost).commit();
    }

    public void gotoFragmentDetail() {
        FragmentDetail fragmentDetail = new FragmentDetail();

        FragmentManager manager = this.getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.frame_layout, fragmentDetail).addToBackStack(null).commit();
    }

    public boolean isNetworkConnected() {
        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null&&networkInfo.isConnected();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
