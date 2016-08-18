package com.facebook.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.FacebookServiceException;
import com.facebook.internal.Utility;
import java.util.Locale;

abstract class WebLoginMethodHandler
  extends LoginMethodHandler
{
  private static final String WEB_VIEW_AUTH_HANDLER_STORE = "com.facebook.login.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY";
  private static final String WEB_VIEW_AUTH_HANDLER_TOKEN_KEY = "TOKEN";
  private String e2e;
  
  WebLoginMethodHandler(Parcel paramParcel)
  {
    super(paramParcel);
  }
  
  WebLoginMethodHandler(LoginClient paramLoginClient)
  {
    super(paramLoginClient);
  }
  
  private static final String getRedirectUri()
  {
    return "fb" + FacebookSdk.getApplicationId() + "://authorize";
  }
  
  private String loadCookieToken()
  {
    return this.loginClient.getActivity().getSharedPreferences("com.facebook.login.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).getString("TOKEN", "");
  }
  
  private void saveCookieToken(String paramString)
  {
    this.loginClient.getActivity().getSharedPreferences("com.facebook.login.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).edit().putString("TOKEN", paramString).apply();
  }
  
  protected Bundle addExtraParameters(Bundle paramBundle, LoginClient.Request paramRequest)
  {
    paramBundle.putString("redirect_uri", getRedirectUri());
    paramBundle.putString("client_id", paramRequest.getApplicationId());
    LoginClient localLoginClient = this.loginClient;
    paramBundle.putString("e2e", LoginClient.getE2E());
    paramBundle.putString("response_type", "token,signed_request");
    paramBundle.putString("return_scopes", "true");
    if (paramRequest.isRerequest()) {
      paramBundle.putString("auth_type", "rerequest");
    }
    return paramBundle;
  }
  
  protected Bundle getParameters(LoginClient.Request paramRequest)
  {
    Bundle localBundle = new Bundle();
    if (!Utility.isNullOrEmpty(paramRequest.getPermissions()))
    {
      String str = TextUtils.join(",", paramRequest.getPermissions());
      localBundle.putString("scope", str);
      addLoggingExtra("scope", str);
    }
    localBundle.putString("default_audience", paramRequest.getDefaultAudience().getNativeProtocolAudience());
    localBundle.putString("state", getClientState(paramRequest.getAuthId()));
    paramRequest = AccessToken.getCurrentAccessToken();
    if (paramRequest != null) {}
    for (paramRequest = paramRequest.getToken(); (paramRequest != null) && (paramRequest.equals(loadCookieToken())); paramRequest = null)
    {
      localBundle.putString("access_token", paramRequest);
      addLoggingExtra("access_token", "1");
      return localBundle;
    }
    Utility.clearFacebookCookies(this.loginClient.getActivity());
    addLoggingExtra("access_token", "0");
    return localBundle;
  }
  
  abstract AccessTokenSource getTokenSource();
  
  protected void onComplete(LoginClient.Request paramRequest, Bundle paramBundle, FacebookException paramFacebookException)
  {
    this.e2e = null;
    if (paramBundle != null) {
      if (paramBundle.containsKey("e2e")) {
        this.e2e = paramBundle.getString("e2e");
      }
    }
    for (;;)
    {
      try
      {
        paramBundle = createAccessTokenFromWebBundle(paramRequest.getPermissions(), paramBundle, getTokenSource(), paramRequest.getApplicationId());
        paramRequest = LoginClient.Result.createTokenResult(this.loginClient.getPendingRequest(), paramBundle);
        CookieSyncManager.createInstance(this.loginClient.getActivity()).sync();
        saveCookieToken(paramBundle.getToken());
        if (!Utility.isNullOrEmpty(this.e2e)) {
          logWebLoginCompleted(this.e2e);
        }
        this.loginClient.completeAndValidate(paramRequest);
        return;
      }
      catch (FacebookException paramRequest)
      {
        paramRequest = LoginClient.Result.createErrorResult(this.loginClient.getPendingRequest(), null, paramRequest.getMessage());
        continue;
      }
      if ((paramFacebookException instanceof FacebookOperationCanceledException))
      {
        paramRequest = LoginClient.Result.createCancelResult(this.loginClient.getPendingRequest(), "User canceled log in.");
      }
      else
      {
        this.e2e = null;
        paramRequest = null;
        paramBundle = paramFacebookException.getMessage();
        if ((paramFacebookException instanceof FacebookServiceException))
        {
          paramBundle = ((FacebookServiceException)paramFacebookException).getRequestError();
          paramRequest = String.format(Locale.ROOT, "%d", new Object[] { Integer.valueOf(paramBundle.getErrorCode()) });
          paramBundle = paramBundle.toString();
        }
        paramRequest = LoginClient.Result.createErrorResult(this.loginClient.getPendingRequest(), null, paramBundle, paramRequest);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\login\WebLoginMethodHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */