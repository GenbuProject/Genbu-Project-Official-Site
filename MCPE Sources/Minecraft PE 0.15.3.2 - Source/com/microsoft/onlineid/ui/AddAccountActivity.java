package com.microsoft.onlineid.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Handler;
import com.microsoft.onlineid.RequestOptions;
import com.microsoft.onlineid.SignInOptions;
import com.microsoft.onlineid.SignUpOptions;
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
import com.microsoft.onlineid.internal.Uris;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.sso.client.BackupService;
import com.microsoft.onlineid.internal.storage.TypedStorage;
import com.microsoft.onlineid.internal.ui.WebFlowActivity;
import com.microsoft.onlineid.internal.ui.WebFlowTelemetryData;
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
  private static final String ClientFlightLabel = "client_flight";
  public static final String CobrandingIdLabel = "cobrandid";
  protected static final int NoRequest = -1;
  public static final String PlatformLabel = "platform";
  public static final String PlatformName = "android";
  private static final String PrefillUsernameLabel = "username";
  public static final String SignInOptionsLabel = AddAccountActivity.class.getName() + ".SignInOptions";
  protected static final int SignInWebFlowRequest = 0;
  public static final String SignUpFlowLabel = "fl";
  public static final String SignUpOptionsLabel = AddAccountActivity.class.getName() + ".SignUpOptions";
  private static final String UnauthenticatedSessionIdLabel = "uaid";
  private static final String WReplyLabel = "wreply";
  protected String _accountPuid;
  protected Handler _handler;
  protected int _pendingChildRequest = -1;
  private ActivityResultSender _resultSender;
  protected TypedStorage _typedStorage;
  
  private void appendOptions(RequestOptions paramRequestOptions, Uri.Builder paramBuilder)
  {
    String str = paramRequestOptions.getPrefillUsername();
    if (str != null) {
      paramBuilder.appendQueryParameter("username", str);
    }
    str = paramRequestOptions.getUnauthenticatedSessionId();
    if (str != null) {
      paramBuilder.appendQueryParameter("uaid", str);
    }
    paramRequestOptions = paramRequestOptions.getFlightConfiguration();
    if (paramRequestOptions != null) {
      paramBuilder.appendQueryParameter("client_flight", paramRequestOptions);
    }
  }
  
  public static Intent getSignInIntent(Context paramContext, SignInOptions paramSignInOptions, String paramString1, String paramString2, boolean paramBoolean, String paramString3, Bundle paramBundle)
  {
    paramContext = new Intent(paramContext, AddAccountActivity.class).setAction("com.microsoft.onlineid.internal.ADD_ACCOUNT").putExtra("fl", paramString1).putExtra("com.microsoft.onlineid.cobranding_id", paramString2).putExtra("com.microsoft.onlineid.web_telemetry_requested", paramBoolean).putExtra("com.microsoft.onlineid.client_package_name", paramString3).putExtra("com.microsoft.onlineid.client_state", paramBundle).setData(new Intents.DataBuilder().add(paramSignInOptions).add(paramString1).add(paramString2).add(paramString3).build());
    if (paramSignInOptions != null) {
      paramContext.putExtra(SignInOptionsLabel, paramSignInOptions.asBundle());
    }
    return paramContext;
  }
  
  public static Intent getSignUpIntent(Context paramContext, SignUpOptions paramSignUpOptions, String paramString1, String paramString2, boolean paramBoolean, String paramString3, Bundle paramBundle)
  {
    paramContext = new Intent(paramContext, AddAccountActivity.class).setAction("com.microsoft.onlineid.internal.SIGN_UP_ACCOUNT").putExtra("fl", paramString1).putExtra("com.microsoft.onlineid.cobranding_id", paramString2).putExtra("com.microsoft.onlineid.web_telemetry_requested", paramBoolean).putExtra("com.microsoft.onlineid.client_package_name", paramString3).putExtra("com.microsoft.onlineid.client_state", paramBundle).setData(new Intents.DataBuilder().add(paramSignUpOptions).add(paramString1).add(paramString2).add(paramString3).build());
    if (paramSignUpOptions != null) {
      paramContext.putExtra(SignUpOptionsLabel, paramSignUpOptions.asBundle());
    }
    return paramContext;
  }
  
  protected void addCommonQueryStringParams(Uri.Builder paramBuilder)
  {
    paramBuilder.appendQueryParameter("platform", "android" + Resources.getSdkVersion(getApplicationContext()));
    paramBuilder.appendQueryParameter("client_id", Applications.buildClientAppUri(getApplicationContext(), getIntent().getStringExtra("com.microsoft.onlineid.client_package_name")));
    String str = getIntent().getStringExtra("com.microsoft.onlineid.cobranding_id");
    if (str != null) {
      paramBuilder.appendQueryParameter("cobrandid", str);
    }
  }
  
  protected void addTelemetryToResult(Intent paramIntent)
  {
    if ((paramIntent != null) && (paramIntent.getExtras() != null))
    {
      paramIntent = new ApiResult(paramIntent.getExtras());
      if (paramIntent.hasWebFlowTelemetryEvents()) {
        this._resultSender.putWebFlowTelemetryFields(paramIntent).set();
      }
    }
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
  
  protected Uri getLoginUri(ServerConfig paramServerConfig, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject;
    if (paramBoolean1) {
      if (paramBoolean2) {
        localObject = ServerConfig.Endpoint.SignupWReplyMsa;
      }
    }
    for (;;)
    {
      paramServerConfig = Uri.parse(paramServerConfig.getUrl((ServerConfig.Endpoint)localObject).toExternalForm()).buildUpon();
      addCommonQueryStringParams(paramServerConfig);
      localObject = getIntent().getBundleExtra(SignInOptionsLabel);
      if (localObject != null) {
        appendOptions(new SignInOptions((Bundle)localObject), paramServerConfig);
      }
      if (!paramBoolean2) {
        break;
      }
      return Uris.appendMarketQueryString(getApplicationContext(), paramServerConfig.build());
      localObject = ServerConfig.Endpoint.ConnectMsa;
      continue;
      if (paramBoolean2) {
        localObject = ServerConfig.Endpoint.SignupWReplyPartner;
      } else {
        localObject = ServerConfig.Endpoint.ConnectPartner;
      }
    }
    return paramServerConfig.build();
  }
  
  protected Uri getSignupUri(ServerConfig paramServerConfig, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (Object localObject1 = ServerConfig.Endpoint.SignupMsa;; localObject1 = ServerConfig.Endpoint.SignupPartner)
    {
      localObject1 = Uri.parse(paramServerConfig.getUrl((ServerConfig.Endpoint)localObject1).toExternalForm()).buildUpon();
      addCommonQueryStringParams((Uri.Builder)localObject1);
      Object localObject2 = getIntent().getBundleExtra(SignUpOptionsLabel);
      if (localObject2 != null) {
        appendOptions(new SignUpOptions((Bundle)localObject2), (Uri.Builder)localObject1);
      }
      localObject2 = getIntent().getStringExtra("fl");
      if (localObject2 != null) {
        ((Uri.Builder)localObject1).appendQueryParameter("fl", (String)localObject2);
      }
      ((Uri.Builder)localObject1).appendQueryParameter("wreply", getLoginUri(paramServerConfig, paramBoolean, true).toString());
      return ((Uri.Builder)localObject1).build();
    }
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
      addTelemetryToResult(paramIntent);
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
    paramBundle = new ServerConfig(getApplicationContext());
    Object localObject = getIntent().getStringExtra("com.microsoft.onlineid.client_package_name");
    boolean bool3 = PackageInfoHelper.isAuthenticatorApp((String)localObject);
    boolean bool2 = false;
    this._resultSender = new ActivityResultSender(this, ActivityResultSender.ResultType.Account);
    String str = getIntent().getAction();
    Bundle localBundle;
    boolean bool1;
    if ("com.microsoft.onlineid.internal.SIGN_UP_ACCOUNT".equals(str))
    {
      localBundle = getIntent().getBundleExtra(SignUpOptionsLabel);
      bool1 = bool2;
      if (localBundle != null) {
        bool1 = new SignUpOptions(localBundle).getWasPrecachingEnabled();
      }
      bool2 = getIntent().getBooleanExtra("com.microsoft.onlineid.web_telemetry_requested", false);
      if (!"com.microsoft.onlineid.internal.SIGN_UP_ACCOUNT".equals(str)) {
        break label299;
      }
      paramBundle = getSignupUri(paramBundle, bool3);
      label132:
      if (!"com.microsoft.onlineid.internal.SIGN_UP_ACCOUNT".equals(str)) {
        break label311;
      }
    }
    label299:
    label311:
    for (str = "com.microsoft.onlineid.internal.SIGN_UP";; str = "com.microsoft.onlineid.internal.SIGN_IN")
    {
      localObject = new WebFlowTelemetryData().setIsWebTelemetryRequested(bool2).setCallingAppPackageName((String)localObject).setCallingAppVersionName(PackageInfoHelper.getAppVersionName(getApplicationContext(), (String)localObject)).setWasPrecachingEnabled(bool1);
      paramBundle = WebFlowActivity.getFlowRequest(getApplicationContext(), paramBundle, str, bool3, (WebFlowTelemetryData)localObject).asIntent();
      paramBundle.addFlags(65536);
      this._pendingChildRequest = 0;
      if (NetworkConnectivity.hasInternetConnectivity(getApplicationContext())) {
        break label319;
      }
      ClientAnalytics.get().logEvent("Performance", "No network connectivity", "At start of web flow");
      sendFailureResult(new NetworkException());
      return;
      bool1 = bool2;
      if (!"com.microsoft.onlineid.internal.ADD_ACCOUNT".equals(str)) {
        break;
      }
      localBundle = getIntent().getBundleExtra(SignInOptionsLabel);
      bool1 = bool2;
      if (localBundle == null) {
        break;
      }
      bool1 = new SignInOptions(localBundle).getWasPrecachingEnabled();
      break;
      paramBundle = getLoginUri(paramBundle, bool3, false);
      break label132;
    }
    label319:
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\ui\AddAccountActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */