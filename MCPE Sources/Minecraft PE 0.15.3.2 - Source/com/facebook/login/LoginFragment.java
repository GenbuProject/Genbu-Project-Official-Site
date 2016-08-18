package com.facebook.login;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.FacebookActivity;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.R.id;
import com.facebook.R.layout;
import com.facebook.internal.Utility;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginFragment
  extends Fragment
{
  private static final int CHALLENGE_LENGTH = 20;
  static final String EXTRA_REQUEST = "request";
  private static final String NULL_CALLING_PKG_ERROR_MSG = "Cannot call LoginFragment with a null calling package. This can occur if the launchMode of the caller is singleInstance.";
  static final String RESULT_KEY = "com.facebook.LoginFragment:Result";
  private static final String SAVED_CHALLENGE = "challenge";
  private static final String SAVED_LOGIN_CLIENT = "loginClient";
  private static final String TAG = "LoginFragment";
  private String callingPackage;
  private String expectedChallenge;
  private LoginClient loginClient;
  private LoginClient.Request request;
  private boolean restarted;
  
  private void initializeCallingPackage(Activity paramActivity)
  {
    paramActivity = paramActivity.getCallingActivity();
    if (paramActivity == null) {
      return;
    }
    this.callingPackage = paramActivity.getPackageName();
  }
  
  private void onLoginClientCompleted(LoginClient.Result paramResult)
  {
    this.request = null;
    if (paramResult.code == LoginClient.Result.Code.CANCEL) {}
    for (int i = 0;; i = -1)
    {
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("com.facebook.LoginFragment:Result", paramResult);
      paramResult = new Intent();
      paramResult.putExtras(localBundle);
      if (isAdded())
      {
        getActivity().setResult(i, paramResult);
        getActivity().finish();
      }
      return;
    }
  }
  
  public String getChallengeParam()
  {
    return this.expectedChallenge;
  }
  
  LoginClient getLoginClient()
  {
    return this.loginClient;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    this.loginClient.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    boolean bool;
    if (paramBundle != null)
    {
      bool = true;
      this.restarted = bool;
      if (paramBundle == null) {
        break label81;
      }
      this.loginClient = ((LoginClient)paramBundle.getParcelable("loginClient"));
      this.loginClient.setFragment(this);
      this.expectedChallenge = paramBundle.getString("challenge");
      label51:
      this.loginClient.setOnCompletedListener(new LoginClient.OnCompletedListener()
      {
        public void onCompleted(LoginClient.Result paramAnonymousResult)
        {
          LoginFragment.this.onLoginClientCompleted(paramAnonymousResult);
        }
      });
      paramBundle = getActivity();
      if (paramBundle != null) {
        break label105;
      }
    }
    label81:
    label105:
    do
    {
      return;
      bool = false;
      break;
      this.loginClient = new LoginClient(this);
      this.expectedChallenge = Utility.generateRandomString(20);
      break label51;
      initializeCallingPackage(paramBundle);
    } while (paramBundle.getIntent() == null);
    paramBundle = paramBundle.getIntent();
    paramBundle.setExtrasClassLoader(LoginClient.Request.class.getClassLoader());
    this.request = ((LoginClient.Request)paramBundle.getParcelableExtra("request"));
  }
  
  public View onCreateView(final LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(R.layout.com_facebook_login_fragment, paramViewGroup, false);
    this.loginClient.setBackgroundProcessingListener(new LoginClient.BackgroundProcessingListener()
    {
      public void onBackgroundProcessingStarted()
      {
        paramLayoutInflater.findViewById(R.id.com_facebook_login_activity_progress_bar).setVisibility(0);
      }
      
      public void onBackgroundProcessingStopped()
      {
        paramLayoutInflater.findViewById(R.id.com_facebook_login_activity_progress_bar).setVisibility(8);
      }
    });
    return paramLayoutInflater;
  }
  
  public void onDestroy()
  {
    this.loginClient.cancelCurrentHandler();
    super.onDestroy();
  }
  
  public void onPause()
  {
    super.onPause();
    getActivity().findViewById(R.id.com_facebook_login_activity_progress_bar).setVisibility(8);
  }
  
  public void onResume()
  {
    super.onResume();
    if (this.callingPackage == null)
    {
      Log.e("LoginFragment", "Cannot call LoginFragment with a null calling package. This can occur if the launchMode of the caller is singleInstance.");
      getActivity().finish();
      return;
    }
    if (this.restarted)
    {
      FragmentActivity localFragmentActivity = getActivity();
      if (((localFragmentActivity instanceof FacebookActivity)) && ((this.loginClient.getCurrentHandler() instanceof CustomTabLoginMethodHandler))) {
        ((FacebookActivity)localFragmentActivity).sendResult(null, new FacebookOperationCanceledException());
      }
    }
    this.restarted = true;
    this.loginClient.startOrContinueAuth(this.request);
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putParcelable("loginClient", this.loginClient);
    paramBundle.putString("challenge", this.expectedChallenge);
  }
  
  public boolean validateChallengeParam(Bundle paramBundle)
  {
    try
    {
      paramBundle = paramBundle.getString("state");
      if (paramBundle == null) {
        return false;
      }
      boolean bool = new JSONObject(paramBundle).getString("7_challenge").equals(this.expectedChallenge);
      return bool;
    }
    catch (JSONException paramBundle) {}
    return false;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\login\LoginFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */