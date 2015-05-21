package com.hkm.dis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.hkm.disqus.api.exception.ApiException;
import com.hkm.disqus.api.model.posts.Post;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by hesk on 21/5/15.
 */
public class mainactivity extends AppCompatActivity {
    private applicationbase base;
    public static String TAG = mainactivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        base = ((applicationbase) getApplication());


    }

    private void getPost() {
        try {
            base.getComments("1008680 http://hypebeast.com/?p=1008680", new Callback<com.hkm.disqus.api.model.Response<List<Post>>>() {
                @Override
                public void success(com.hkm.disqus.api.model.Response<List<Post>> posts, Response response) {

                    com.hkm.disqus.api.model.Response<List<Post>> d = posts;

                    Log.d(TAG, "now its working now");
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.d(TAG, error.getMessage());
                }
            });
        } catch (ApiException e) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_game:
                getPost();
                return true;
            case R.id.help:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
