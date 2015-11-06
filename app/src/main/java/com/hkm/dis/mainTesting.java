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
import android.widget.Toast;

import com.hkm.disqus.api.ApiConfig;
import com.hkm.disqus.api.AuthMgr;
import com.hkm.disqus.api.DisqusClient;
import com.hkm.disqus.api.exception.ApiException;
import com.hkm.disqus.api.model.oauth2.AccessToken;
import com.hkm.disqus.api.model.posts.Post;
import com.hkm.disqus.application.AuthorizeActivity;
import com.hkm.disqus.application.AuthorizeActivity.*;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by hesk on 21/5/15.
 */
public class mainTesting extends AppCompatActivity {

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
                postPost(ed.getText().toString(), "1008680");
            }
        });
    }

    @Override
    protected DisqusClient getClient() {
        ApiConfig conf = new ApiConfig(
                BuildConfig.DISQUS_API_KEY,
                BuildConfig.DISQUS_DEFAULT_ACCESS,
                RestAdapter.LogLevel.BASIC);
        conf.setApiSecret(BuildConfig.DISQUS_SECRET);
        conf.setRedirectUri(BuildConfig.DISQUS_REDIRECT_URI);
        return DisqusClient.getInstance(this, conf);
    }


    private void getPost() {
        try {
            getBase().getComments("1008680 http://hypebeast.com/?p=1008680", new Callback<com.hkm.disqus.api.model.Response<List<Post>>>() {
                @Override
                public void success(com.hkm.disqus.api.model.Response<List<Post>> posts, Response response) {
                    com.hkm.disqus.api.model.Response<List<Post>> d = posts;
                    Log.d(TAG, "now its working now");
                    addLine(response.getBody() + " and the " + d.data.size() + " items were found");
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.d(TAG, error.getMessage());
                    addLine(error.getBody().toString());
                }


            });
        } catch (RetrofitError e) {
            addLine(e.getMessage());
        } catch (ApiException e) {
            addLine(e.getMessage());
        }
    }

    // "1008680",
    //  "1008680 http://hypebeast.com/?p=1008680",
    private void postPost(String postmessage, String thread_id) {
        try {
            if (getManager().isAuthenticated()) {
                getBase().beginPostTransaction().create(postmessage, thread_id,
                        new Callback<com.hkm.disqus.api.model.Response<Post>>() {
                            @Override
                            public void success(com.hkm.disqus.api.model.Response<Post> postResponse, Response response) {
                                addLine(response.getBody().toString());
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                addLine(error.getUrl().toString() + "\n" + error.getMessage());
                                addLine("===============================================");
                            }
                        });
            } else {
                getClient().loginNow(login.class, this);
            }
        } catch (RetrofitError e) {
            addLine(e.getMessage() + " -- retrofit error");
        } catch (ApiException e) {
            addLine(e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == DisqusClient.authorization_intent_id) {
            // Make sure the request was successful
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, getResources().getString(com.hkm.disqus.R.string.failurelogin), Toast.LENGTH_LONG);
            }
            if (resultCode == RESULT_OK) {
                AccessToken token = (AccessToken) data.getExtras().getParcelable(AuthorizeActivity.EXTRA_ACCESS_TOKEN);
                addLine(token.accessToken);
            }
        }

    }


    protected void addLine(String newline) {
        if (tvv != null) {
            tvv.setText(tvv.getText() + "\n" + newline);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_game:
                getPost();
                return true;
            case R.id.login_page:
                getClient().loginNow(login.class, this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
