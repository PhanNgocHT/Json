package com.example.hp.json.activity.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.json.utils.GetDataAsyncTask;
import com.example.hp.json.model.Post;
import com.example.hp.json.R;
import com.example.hp.json.activity.MainActivity;

import java.util.List;

/**
 * Created by hp on 11/2/2017.
 */

public class FragmentDetail extends Fragment{
    private TextView tvDetail;
    private List<Post> posts;
    private GetDataAsyncTask asyncTask;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        tvDetail= v.findViewById(R.id.tv_detail);
        posts=((MainActivity) getActivity()).getPosts();
        int position=((MainActivity) getActivity()).getPosition();
        tvDetail.setText(posts.get(position).getBody().toString());
        return v;
    }
}
