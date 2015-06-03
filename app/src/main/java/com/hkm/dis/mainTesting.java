package com.hkm.dis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.TintImageView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hkm.disqus.api.exception.ApiException;
import com.hkm.disqus.api.model.posts.Post;
import com.hkm.disqus.application.AuthorizeActivity;
import com.hkm.disqus.application.AuthorizeActivity.*;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by hesk on 21/5/15.
 */
public class mainTesting extends AppCompatActivity {
    private applicationbase base;
    public static String TAG = "gamestarthere";
    private TextView tvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.listlayout);
        final EditText ed = (EditText) findViewById(R.id.contentfield);
        TintImageView tv = (TintImageView) findViewById(R.id.send);
        tvv = (TextView) findViewById(R.id.log);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postPost(ed.getText().toString());
            }
        });
    }

    private applicationbase getBase() {
        return ((applicationbase) getApplication());
    }

    private void getPost() {
        try {
            getBase().getComments("1008680 http://hypebeast.com/?p=1008680", new Callback<com.hkm.disqus.api.model.Response<List<Post>>>() {
                @Override
                public void success(com.hkm.disqus.api.model.Response<List<Post>> posts, Response response) {
                    com.hkm.disqus.api.model.Response<List<Post>> d = posts;
                    Log.d(TAG, "now its working now");
                    tvv.setText(tvv.getText() +
                            "\n" +
                            response.getBody() + " and the " + d.data.size() + " items were found");
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.d(TAG, error.getMessage());
                    tvv.setText(tvv.getText() + "\n" + error.getBody());
                }


            });
        } catch (RetrofitError e) {
            tvv.setText(tvv.getText() + "\n" + e.getMessage());
        } catch (ApiException e) {
            tvv.setText(tvv.getText() + "\n" + e.getMessage());
        }
    }

    // "1008680",
    private void postPost(String post) {
        try {
            getBase().beginPostTransaction().create(post, new Callback<com.hkm.disqus.api.model.Response<Post>>() {
                @Override
                public void success(com.hkm.disqus.api.model.Response<Post> postResponse, Response response) {
                    tvv.setText(tvv.getText() + "\n" + response.getBody());
                }

                @Override
                public void failure(RetrofitError error) {
                    tvv.setText(tvv.getText() + "\n" + error.getMessage());
                }
            });
        } catch (RetrofitError e) {
            tvv.setText(tvv.getText() + "\n" + e.getMessage() + " -- retrofit error");
        } catch (ApiException e) {
            tvv.setText(tvv.getText() + "\n" + e.getMessage());
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
            case R.id.login_page:
                Intent in = new Intent(this, login.class);
                Bundle b = new Bundle();
                b.putString(AuthorizeActivity.EXTRA_API_KEY, applicationbase.login_api_key);
                b.putString(AuthorizeActivity.EXTRA_SECRET, applicationbase.secret);
                b.putString(AuthorizeActivity.EXTRA_REDIRECT_URI, applicationbase.redirecturi);
                b.putStringArray(AuthorizeActivity.EXTRA_SCOPES, new String[]{
                        "read",
                        "write"
                });
                in.putExtras(b);
                startActivity(in);
                return true;
            case R.id.webviewComments:
                Intent gh = new Intent(this, webviewdisqus.class);
          /*      Bundle bh = new Bundle();
                bh.putString(AuthorizeActivity.EXTRA_API_KEY, applicationbase.login_api_key);
                bh.putString(AuthorizeActivity.EXTRA_REDIRECT_URI, applicationbase.redirecturi);
                bh.putStringArray(AuthorizeActivity.EXTRA_SCOPES, new String[]{
                        "read",
                        "write"
                });
                gh.putExtras(bh);*/
                startActivity(gh);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
