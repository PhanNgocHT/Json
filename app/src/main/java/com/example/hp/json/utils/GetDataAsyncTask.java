package com.example.hp.json.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.hp.json.custom.PostAdapter;
import com.example.hp.json.model.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by hp on 11/2/2017.
 */

public class GetDataAsyncTask extends AsyncTask<Void, Void, String> {

    private RecyclerView rvPost;
    private PostAdapter adapter;
    private Context context;
    private List<Post> posts;

    public GetDataAsyncTask(RecyclerView rvPost, PostAdapter adapter, Context context, List<Post> posts) {
        this.rvPost = rvPost;
        this.adapter = adapter;
        this.context = context;
        this.posts = posts;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url=new URL(Constant.DATA_URL);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode=connection.getResponseCode();
            if (responseCode==HttpURLConnection.HTTP_OK) {
                BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder builder=new StringBuilder();
                String line;
                while ((line=br.readLine()) !=null) {
                    builder.append((line+"\n"));
                }
                br.close();
                Log.d("doInBackground: ", builder.toString());
                return builder.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s!=null) {
                String strJson = s;
                JSONArray jsonArray = new JSONArray(strJson);
                for (int i=0; i<jsonArray.length(); i++){
                    JSONObject object=jsonArray.getJSONObject(i);
                    String userId=object.getString(Constant.USER_ID);
                    String id=object.getString(Constant.ID);
                    String title=object.getString(Constant.TITLE);
                    String body=object.getString(Constant.BODY);
                    Post post=new Post(userId, id, title, body);
                    posts.add(post);
                }
                adapter.notifyDataSetChanged();
            }
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }
}
