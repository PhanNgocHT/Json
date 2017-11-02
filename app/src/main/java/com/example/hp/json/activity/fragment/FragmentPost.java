package com.example.hp.json.activity.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.json.utils.GetDataAsyncTask;
import com.example.hp.json.model.Post;
import com.example.hp.json.custom.PostAdapter;
import com.example.hp.json.R;
import com.example.hp.json.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 11/2/2017.
 */

public class FragmentPost extends Fragment  implements PostAdapter.OnPostClickListener {
    private RecyclerView rvPost;
    private PostAdapter adapter;
    private List<Post> posts=new ArrayList<>();
    private GetDataAsyncTask asyncTask;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_post, container, false);
        rvPost= v.findViewById(R.id.rv_post);
        initData();
        if (((MainActivity) getActivity()).isNetworkConnected()) {
            asyncTask=new GetDataAsyncTask(rvPost, adapter, getActivity(), posts);
            asyncTask.execute();
        }
        return v;
    }

    private void initData() {
        adapter=new PostAdapter(posts);
        rvPost.setAdapter(adapter);
        rvPost.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter.setOnPostClickListener(this);
    }

    @Override
    public void clicked(int position) {
        ((MainActivity) getActivity()).gotoFragmentDetail();
        ((MainActivity) getActivity()).setPosts(posts);
        ((MainActivity) getActivity()).setPosition(position);
    }
}
