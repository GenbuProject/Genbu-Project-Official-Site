package com.facebook.login;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.app.FragmentActivity;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.internal.FacebookDialogFragment;
import com.facebook.internal.WebDialog;
import com.facebook.internal.WebDialog.Builder;
import com.facebook.internal.WebDialog.OnCompleteListener;

class WebViewLoginMethodHandler
  extends WebLoginMethodHandler
{
  public static final Parcelable.Creator<WebViewLoginMethodHandler> CREATOR = new Parcelable.Creator()
  {
    public WebViewLoginMethodHandler createFromParcel(Parcel paramAnonymousParcel)
    {
      return new WebViewLoginMethodHandler(paramAnonymousParcel);
    }
    
    public WebViewLoginMethodHandler[] newArray(int paramAnonymousInt)
    {
      return new WebViewLoginMethodHandler[paramAnonymousInt];
    }
  };
  private String e2e;
  private WebDialog loginDialog;
  
  WebViewLoginMethodHandler(Parcel paramParcel)
  {
    super(paramParcel);
    this.e2e = paramParcel.readString();
  }
  
  WebViewLoginMethodHandler(LoginClient paramLoginClient)
  {
    super(paramLoginClient);
  }
  
  void cancel()
  {
    if (this.loginDialog != null)
    {
      this.loginDialog.cancel();
      this.loginDialog = null;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  String getNameForLogging()
  {
    return "web_view";
  }
  
  AccessTokenSource getTokenSource()
  {
    return AccessTokenSource.WEB_VIEW;
  }
  
  boolean needsInternetPermission()
  {
    return true;
  }
  
  void onWebDialogComplete(LoginClient.Request paramRequest, Bundle paramBundle, FacebookException paramFacebookException)
  {
    super.onComplete(paramRequest, paramBundle, paramFacebookException);
  }
  
  boolean tryAuthorize(final LoginClient.Request paramRequest)
  {
    Bundle localBundle = getParameters(paramRequest);
    WebDialog.OnCompleteListener local1 = new WebDialog.OnCompleteListener()
    {
      public void onComplete(Bundle paramAnonymousBundle, FacebookException paramAnonymousFacebookException)
      {
        WebViewLoginMethodHandler.this.onWebDialogComplete(paramRequest, paramAnonymousBundle, paramAnonymousFacebookException);
      }
    };
    this.e2e = LoginClient.getE2E();
    addLoggingExtra("e2e", this.e2e);
    FragmentActivity localFragmentActivity = this.loginClient.getActivity();
    this.loginDialog = new AuthDialogBuilder(localFragmentActivity, paramRequest.getApplicationId(), localBundle).setE2E(this.e2e).setIsRerequest(paramRequest.isRerequest()).setOnCompleteListener(local1).build();
    paramRequest = new FacebookDialogFragment();
    paramRequest.setRetainInstance(true);
    paramRequest.setDialog(this.loginDialog);
    paramRequest.show(localFragmentActivity.getSupportFragmentManager(), "FacebookDialogFragment");
    return true;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(this.e2e);
  }
  
  static class AuthDialogBuilder
    extends WebDialog.Builder
  {
    private static final String OAUTH_DIALOG = "oauth";
    static final String REDIRECT_URI = "fbconnect://success";
    private String e2e;
    private boolean isRerequest;
    
    public AuthDialogBuilder(Context paramContext, String paramString, Bundle paramBundle)
    {
      super(paramString, "oauth", paramBundle);
    }
    
    public WebDialog build()
    {
      Bundle localBundle = getParameters();
      localBundle.putString("redirect_uri", "fbconnect://success");
      localBundle.putString("client_id", getApplicationId());
      localBundle.putString("e2e", this.e2e);
      localBundle.putString("response_type", "token,signed_request");
      localBundle.putString("return_scopes", "true");
      if (this.isRerequest) {
        localBundle.putString("auth_type", "rerequest");
      }
      return new WebDialog(getContext(), "oauth", localBundle, getTheme(), getListener());
    }
    
    public AuthDialogBuilder setE2E(String paramString)
    {
      this.e2e = paramString;
      return this;
    }
    
    public AuthDialogBuilder setIsRerequest(boolean paramBoolean)
    {
      this.isRerequest = paramBoolean;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\login\WebViewLoginMethodHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */