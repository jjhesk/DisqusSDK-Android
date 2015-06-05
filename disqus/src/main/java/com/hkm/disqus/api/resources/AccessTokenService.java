package com.hkm.disqus.api.resources;

import com.hkm.disqus.api.model.oauth2.AccessToken;

import retrofit.http.Body;
import retrofit.http.EncodedQuery;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.Callback;
/**
 * Restful endpoint used to retrieve a users access token once the auth code has been retrieved.
 */
public interface AccessTokenService {

    @FormUrlEncoded
    @POST("/oauth/2.0/access_token/")
    public void PostToken(@Field("grant_type") String grantType,
                          @Field("client_id") String clientId,
                          @Field("client_secret") String clientSecret,
                          @Field("redirect_uri") String redirectUrl,
                          @Field("code") String authCode,
                          Callback<AccessToken> callback);

    @POST("/oauth/2.0/access_token/")
    public void PostRefreshToken(@Query("grant_type") String grantType,
                                 @Query("client_id") String clientId,
                                 @Query("client_secret") String clientSecret,
                                 @Query("refresh_token") String refreshToken,
                                 Callback<AccessToken> callback);

}
