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

import com.hkm.disqus.DisqusClient;
import com.hkm.disqus.api.exception.ApiException;
import com.hkm.disqus.api.model.oauth2.AccessToken;
import com.hkm.disqus.api.model.posts.Post;
import com.hkm.disqus.application.AuthorizeActivity;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by hesk on 21/5/15.
 */
public class mainTesting extends AppCompatActivity {

    public static String TAG = "gamestarthere";
    private TextView tvv;
    private String appending_post_content;
    private String post_post_id = "1008680";
    private String get_comment_id = "1008680 http://hypebeast.com/?p=1008680";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listlayout);
        final EditText edPost = (EditText) findViewById(R.id.contentfield);
        TintImageView tv = (TintImageView) findViewById(R.id.send);
        tvv = (TextView) findViewById(R.id.log);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postPost(edPost.getText().toString(), "1008680");
                edPost.setText("");
            }
        });
        getPost();
    }


    protected DisqusClient getClient() {
        return DisqusClient.getInstance(this, DqUtil.genConfig());
    }


    private Callback<com.hkm.disqus.api.model.Response<List<Post>>> response_cb = new Callback<com.hkm.disqus.api.model.Response<List<Post>>>() {
        @Override
        public void success(com.hkm.disqus.api.model.Response<List<Post>> posts, Response response) {
            addLine("==POST LISTED=================================");
            addLine(response.getBody() + " and the " + posts.data.size() + " items were found");
            addLine("==============================================");
        }

        @Override
        public void failure(RetrofitError error) {
            addLine("===ERROR_RETOFIT===============================");
            Log.d(TAG, error.getMessage());
            addLine(error.getMessage());
            addLine("==============================================");
        }
    };

    private Callback<com.hkm.disqus.api.model.Response<Post>> return_cb = new Callback<com.hkm.disqus.api.model.Response<Post>>() {
        @Override
        public void success(com.hkm.disqus.api.model.Response<Post> postResponse, Response response) {
            addLine("==Post Success ===============================");
            addLine(response.getBody().toString());
            addLine(postResponse.data.rawMessage);
            addLine("==============================================");
            getPost();
        }

        @Override
        public void failure(RetrofitError error) {
            addLine("===ERROR RETOFIT===============================");
            addLine(error.getUrl().toString() + "\n" + error.getMessage());
            addLine("===============================================");
        }
    };

    // "1008680",
    //  "1008680 http://hypebeast.com/?p=1008680",
    private void postPost(String postmessage, String thread_id) {
        if (getClient().getAuthManager().isAuthenticated()) {
            getClient().postPost(postmessage, post_post_id, return_cb);
        } else {
            appending_post_content = postmessage;
            getClient().loginNow(this);
        }

    }

    private void getPost() {
        //"1008680 http://hypebeast.com/?p=1008680"
        try {
            getClient().getComments(get_comment_id, response_cb);
        } catch (ApiException e) {
            e.printStackTrace();
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
                if (appending_post_content != null) {
                    postPost(appending_post_content, post_post_id);
                    appending_post_content = null;
                }
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
                getClient().loginNow(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
