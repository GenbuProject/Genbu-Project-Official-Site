package com.microsoft.onlineid.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Handler;
import com.microsoft.onlineid.SignInOptions;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.exception.InternalException;
import com.microsoft.onlineid.exception.NetworkException;
import com.microsoft.onlineid.internal.ActivityResultSender;
import com.microsoft.onlineid.internal.ActivityResultSender.ResultType;
import com.microsoft.onlineid.internal.ApiRequest;
import com.microsoft.onlineid.internal.ApiResult;
import com.microsoft.onlineid.internal.Applications;
import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.internal.Intents.DataBuilder;
import com.microsoft.onlineid.internal.NetworkConnectivity;
import com.microsoft.onlineid.internal.PackageInfoHelper;
import com.microsoft.onlineid.internal.Resources;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.sso.client.BackupService;
import com.microsoft.onlineid.internal.storage.TypedStorage;
import com.microsoft.onlineid.internal.ui.WebFlowActivity;
import com.microsoft.onlineid.sts.ServerConfig;
import com.microsoft.onlineid.sts.ServerConfig.Endpoint;
import java.net.URL;

public class AddAccountActivity
  extends Activity
{
  protected static final int AccountAddedRequest = 2;
  public static final String ActionAddAccount = "com.microsoft.onlineid.internal.ADD_ACCOUNT";
  public static final String ActionSignUpAccount = "com.microsoft.onlineid.internal.SIGN_UP_ACCOUNT";
  protected static final int AddPendingRequest = 1;
  private static final String AppIdLabel = "client_id";
  public static final String CobrandingIdLabel = "cobrandid";
  protected static final int NoRequest = -1;
  public static final String PlatformLabel = "platform";
  public static final String PlatformName = "android";
  private static final String PrefillUsernameLabel = "username";
  public static final String SignInOptionsLabel = AddAccountActivity.class.getName() + ".SignInOptions";
  protected static final int SignInWebFlowRequest = 0;
  public static final String SignUpFlowLabel = "fl";
  private static final String SignUpLabel = "signup";
  protected String _accountPuid;
  protected Handler _handler;
  protected int _pendingChildRequest = -1;
  private ActivityResultSender _resultSender;
  protected TypedStorage _typedStorage;
  
  public static Intent getSignInIntent(Context paramContext, SignInOptions paramSignInOptions, String paramString1, String paramString2, String paramString3, Bundle paramBundle)
  {
    paramContext = new Intent(paramContext, AddAccountActivity.class).setAction("com.microsoft.onlineid.internal.ADD_ACCOUNT").putExtra("fl", paramString1).putExtra("com.microsoft.onlineid.cobranding_id", paramString2).putExtra("com.microsoft.onlineid.client_package_name", paramString3).putExtra("com.microsoft.onlineid.client_state", paramBundle).setData(new Intents.DataBuilder().add(paramSignInOptions).add(paramString1).add(paramString2).add(paramString3).build());
    if (paramSignInOptions != null) {
      paramContext.putExtra(SignInOptionsLabel, paramSignInOptions.asBundle());
    }
    return paramContext;
  }
  
  public static Intent getSignUpIntent(Context paramContext, String paramString1, String paramString2, String paramString3, Bundle paramBundle)
  {
    return new Intent(paramContext, AddAccountActivity.class).setAction("com.microsoft.onlineid.internal.SIGN_UP_ACCOUNT").putExtra("fl", paramString1).putExtra("com.microsoft.onlineid.cobranding_id", paramString2).putExtra("com.microsoft.onlineid.client_package_name", paramString3).putExtra("com.microsoft.onlineid.client_state", paramBundle).setData(new Intents.DataBuilder().add(paramString1).add(paramString2).add(paramString3).build());
  }
  
  public void finish()
  {
    if (this._pendingChildRequest != -1)
    {
      finishActivity(this._pendingChildRequest);
      this._pendingChildRequest = -1;
    }
    super.finish();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == this._pendingChildRequest) {
      this._pendingChildRequest = -1;
    }
    switch (paramInt1)
    {
    default: 
      Logger.error("Received activity result for unknown request code: " + paramInt1);
      sendFailureResult("Received activity result for unknown request code: " + paramInt1);
    case 1: 
      return;
    case 0: 
      switch (paramInt2)
      {
      default: 
        sendFailureResult("Sign in activity finished with unexpected result code: " + paramInt2);
        return;
      case -1: 
        if ((paramIntent == null) || (paramIntent.getExtras() == null))
        {
          sendFailureResult("Sign in flow finished successfully with no extras set.");
          return;
        }
        onSetupSuccessful(new ApiResult(paramIntent.getExtras()).getAccountPuid());
        return;
      case 1: 
        sendFailureResult(new ApiResult(paramIntent.getExtras()).getException());
        return;
      }
      sendCancelledResult();
      return;
    }
    switch (paramInt2)
    {
    default: 
      sendFailureResult("Account added activity finished with unexpected result code: " + paramInt2);
      return;
    }
    sendSuccessResult(this._accountPuid);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Object localObject = new ServerConfig(getApplicationContext());
    boolean bool = PackageInfoHelper.isAuthenticatorApp(getIntent().getStringExtra("com.microsoft.onlineid.client_package_name"));
    this._resultSender = new ActivityResultSender(this, ActivityResultSender.ResultType.Account);
    String str1 = getIntent().getAction();
    if (bool)
    {
      paramBundle = ServerConfig.Endpoint.ConnectMsa;
      String str2 = "android" + Resources.getSdkVersion(getApplicationContext());
      paramBundle = Uri.parse(((ServerConfig)localObject).getUrl(paramBundle).toExternalForm()).buildUpon();
      paramBundle.appendQueryParameter("platform", str2);
      if ("com.microsoft.onlineid.internal.SIGN_UP_ACCOUNT".equals(str1)) {
        paramBundle.appendQueryParameter("signup", "1");
      }
      localObject = getIntent().getStringExtra("fl");
      if (localObject != null) {
        paramBundle.appendQueryParameter("fl", (String)localObject);
      }
      if ("com.microsoft.onlineid.internal.ADD_ACCOUNT".equals(str1))
      {
        localObject = getIntent().getBundleExtra(SignInOptionsLabel);
        if (localObject != null)
        {
          localObject = new SignInOptions((Bundle)localObject).getPrefillUsername();
          if (localObject != null) {
            paramBundle.appendQueryParameter("username", (String)localObject);
          }
        }
      }
      paramBundle.appendQueryParameter("client_id", Applications.buildClientAppUri(getApplicationContext(), getIntent().getStringExtra("com.microsoft.onlineid.client_package_name")));
      localObject = getIntent().getStringExtra("com.microsoft.onlineid.cobranding_id");
      if (localObject != null) {
        paramBundle.appendQueryParameter("cobrandid", (String)localObject);
      }
      localObject = paramBundle.build();
      if (!"com.microsoft.onlineid.internal.SIGN_UP_ACCOUNT".equals(str1)) {
        break label354;
      }
    }
    label354:
    for (paramBundle = "com.microsoft.onlineid.internal.SIGN_UP";; paramBundle = "com.microsoft.onlineid.internal.SIGN_IN")
    {
      paramBundle = WebFlowActivity.getFlowRequest(getApplicationContext(), (Uri)localObject, paramBundle, bool).asIntent();
      paramBundle.addFlags(65536);
      this._pendingChildRequest = 0;
      if (NetworkConnectivity.hasInternetConnectivity(getApplicationContext())) {
        break label361;
      }
      ClientAnalytics.get().logEvent("Performance", "No network connectivity", "At start of web flow");
      sendFailureResult(new NetworkException());
      return;
      paramBundle = ServerConfig.Endpoint.ConnectPartner;
      break;
    }
    label361:
    startActivityForResult(paramBundle, 0);
    this._handler = new Handler();
  }
  
  protected void onSetupFailure(Exception paramException)
  {
    sendFailureResult(paramException);
  }
  
  protected void onSetupSuccessful(String paramString)
  {
    BackupService.pushBackup(getApplicationContext());
    if (!isFinishing())
    {
      finishActivity(1);
      sendSuccessResult(paramString);
    }
  }
  
  protected void sendCancelledResult()
  {
    ApiRequest localApiRequest = new ApiRequest(getApplicationContext(), getIntent());
    if (localApiRequest.hasResultReceiver()) {
      localApiRequest.sendUserCanceled();
    }
    finish();
  }
  
  protected void sendFailureResult(Exception paramException)
  {
    boolean bool;
    if (paramException != null)
    {
      bool = true;
      Assertion.check(bool);
      Logger.error("Failed to add account.", paramException);
      ClientAnalytics.get().logException(paramException);
      ApiRequest localApiRequest = new ApiRequest(getApplicationContext(), getIntent());
      if (!localApiRequest.hasResultReceiver()) {
        break label65;
      }
      localApiRequest.sendFailure(paramException);
    }
    for (;;)
    {
      finish();
      return;
      bool = false;
      break;
      label65:
      this._resultSender.putException(paramException).set();
    }
  }
  
  protected void sendFailureResult(String paramString)
  {
    sendFailureResult(new InternalException(paramString));
  }
  
  protected void sendSuccessResult(String paramString)
  {
    boolean bool;
    if (paramString != null)
    {
      bool = true;
      Assertion.check(bool);
      ApiRequest localApiRequest = new ApiRequest(getApplicationContext(), getIntent());
      if (!localApiRequest.hasResultReceiver()) {
        break label58;
      }
      localApiRequest.sendSuccess(new ApiResult().setAccountPuid(paramString));
    }
    for (;;)
    {
      finish();
      return;
      bool = false;
      break;
      label58:
      paramString = new TypedStorage(getApplicationContext()).readAccount(paramString);
      if (paramString == null)
      {
        sendFailureResult(new InternalException("AddAccountActivity could not acquire newly added account."));
        return;
      }
      this._resultSender.putLimitedUserAccount(paramString).set();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\ui\AddAccountActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */