package com.microsoft.xbox.service.network.managers.friendfinder;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.Sharer.Result;
import com.facebook.share.widget.ShareDialog;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderModel;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderState.FriendsFinderStateResult;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderState.LinkedAccountOptInStatus;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderState.LinkedAccountTokenStatus;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderType;
import com.microsoft.xbox.service.network.managers.xblshared.ProtectedRunnable;
import com.microsoft.xbox.toolkit.Ready;
import com.microsoft.xbox.toolkit.XLEException;
import com.microsoft.xbox.toolkit.ui.ActivityParameters;
import com.microsoft.xbox.toolkit.ui.NavigationManager;
import com.microsoft.xbox.xle.app.activity.FriendFinder.FriendFinderLinkScreen;
import com.microsoft.xbox.xle.telemetry.helpers.UTCFriendFinder;
import com.microsoft.xboxtcui.FbLoginShimActivity;
import com.microsoft.xboxtcui.FbLoginShimActivity.LoginType;
import com.microsoft.xboxtcui.FbShareShimActivity;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.util.Arrays;
import java.util.List;

public class FacebookManager
{
  private static Ready facebookManagerReady = new Ready();
  private static FacebookManager instance;
  private CallbackManager callbackManager;
  private List<String> facebookPermission;
  private boolean firstLoginWithReadOnly = false;
  private FriendFinderState.FriendsFinderStateResult friendsFinderStateResult;
  private LoginBehavior loginBehavior;
  private FacebookCallback<LoginResult> loginResult = new FacebookCallback()
  {
    public void onCancel()
    {
      UTCFriendFinder.trackFacebookLoginCancel(null);
      FacebookManager.this.loadPeopleHubFriendFinderState();
      FacebookManager.this.resetFacebookToken(true);
    }
    
    public void onError(FacebookException paramAnonymousFacebookException)
    {
      FacebookManager.this.loadPeopleHubFriendFinderState();
      FacebookManager.this.resetFacebookToken(true);
    }
    
    public void onSuccess(LoginResult paramAnonymousLoginResult)
    {
      FacebookManager.access$002(FacebookManager.this, paramAnonymousLoginResult.getAccessToken());
      if (FacebookManager.this.token != null)
      {
        if (!FacebookManager.this.firstLoginWithReadOnly) {
          break label84;
        }
        FacebookManager.access$102(FacebookManager.this, false);
        FacebookManager.access$202(FacebookManager.this, FacebookManager.this.token.getToken());
        paramAnonymousLoginResult = new ActivityParameters();
        paramAnonymousLoginResult.putFriendFinderType(FriendFinderType.FACEBOOK);
      }
      label84:
      try
      {
        NavigationManager.getInstance().PushScreen(FriendFinderLinkScreen.class, paramAnonymousLoginResult);
        return;
      }
      catch (XLEException paramAnonymousLoginResult) {}
      FacebookManager.this.showShareDialog();
      return;
    }
  };
  private FacebookCallback<Sharer.Result> shareResult = new FacebookCallback()
  {
    public void onCancel() {}
    
    public void onError(FacebookException paramAnonymousFacebookException) {}
    
    public void onSuccess(Sharer.Result paramAnonymousResult) {}
  };
  private AccessToken token;
  private String tokenString;
  
  private FacebookManager()
  {
    facebookManagerReady.reset();
    new ProtectedRunnable(new Runnable()
    {
      public void run()
      {
        try
        {
          FacebookManager.access$402(FacebookManager.this, Arrays.asList(new String[] { "public_profile", "user_friends" }));
          FacebookSdk.sdkInitialize(XboxTcuiSdk.getApplicationContext());
          FacebookManager.access$502(FacebookManager.this, CallbackManager.Factory.create());
          LoginManager.getInstance().registerCallback(FacebookManager.this.callbackManager, FacebookManager.this.loginResult);
          return;
        }
        catch (Exception localException)
        {
          Log.i("h", localException.getMessage());
        }
      }
    }).run();
    this.loginBehavior = LoginBehavior.WEB_ONLY;
    facebookManagerReady.setReady();
  }
  
  public static Ready getFacebookManagerReady()
  {
    return facebookManagerReady;
  }
  
  public static FacebookManager getInstance()
  {
    try
    {
      if (instance == null) {
        instance = new FacebookManager();
      }
      FacebookManager localFacebookManager = instance;
      return localFacebookManager;
    }
    finally {}
  }
  
  private Intent getPublishShimIntent()
  {
    Intent localIntent = new Intent(XboxTcuiSdk.getActivity(), FbLoginShimActivity.class);
    localIntent.putExtra("LoginType", FbLoginShimActivity.LoginType.PUBLISH);
    return localIntent;
  }
  
  private Intent getReadShimIntent()
  {
    Intent localIntent = new Intent(XboxTcuiSdk.getActivity(), FbLoginShimActivity.class);
    localIntent.putExtra("LoginType", FbLoginShimActivity.LoginType.READ);
    return localIntent;
  }
  
  private void showShareDialog()
  {
    XboxTcuiSdk.getActivity().startActivity(new Intent(XboxTcuiSdk.getActivity(), FbShareShimActivity.class));
  }
  
  public FriendFinderState.FriendsFinderStateResult getFacebookFriendFinderState()
  {
    return this.friendsFinderStateResult;
  }
  
  public List<String> getFacebookPermission()
  {
    return this.facebookPermission;
  }
  
  public LoginBehavior getLoginBehavior()
  {
    return this.loginBehavior;
  }
  
  public String getTokenString()
  {
    return this.tokenString;
  }
  
  public boolean isFacebookFriendFinderOptedIn()
  {
    return (this.friendsFinderStateResult != null) && (this.friendsFinderStateResult.getLinkedAccountOptInStatus() == FriendFinderState.LinkedAccountOptInStatus.OptedIn) && (this.friendsFinderStateResult.getLinkedAccountTokenStatus() == FriendFinderState.LinkedAccountTokenStatus.OK);
  }
  
  public void loadPeopleHubFriendFinderState()
  {
    FriendFinderModel.getInstance().loadAsync(true);
  }
  
  public void login()
  {
    UTCFriendFinder.trackFacebookLinkAccountView(null);
    this.firstLoginWithReadOnly = true;
    XboxTcuiSdk.getActivity().startActivity(getReadShimIntent());
  }
  
  public void onShimActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    this.callbackManager.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void registerShareCallback(ShareDialog paramShareDialog)
  {
    paramShareDialog.registerCallback(this.callbackManager, this.shareResult);
  }
  
  public void resetFacebookToken(boolean paramBoolean)
  {
    this.token = null;
    this.tokenString = null;
    if (paramBoolean) {
      LoginManager.getInstance().logOut();
    }
  }
  
  public void setFacebookFriendFinderState(FriendFinderState.FriendsFinderStateResult paramFriendsFinderStateResult)
  {
    this.friendsFinderStateResult = paramFriendsFinderStateResult;
  }
  
  public void shareToFacebook()
  {
    this.firstLoginWithReadOnly = false;
    XboxTcuiSdk.getActivity().startActivity(getPublishShimIntent());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\network\managers\friendfinder\FacebookManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */