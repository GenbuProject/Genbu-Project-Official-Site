package com.microsoft.onlineid.internal.ui;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import com.microsoft.onlineid.ISecurityScope;
import com.microsoft.onlineid.Ticket;
import com.microsoft.onlineid.internal.ActivityResultSender;
import com.microsoft.onlineid.internal.ActivityResultSender.ResultType;
import com.microsoft.onlineid.internal.ApiRequest;
import com.microsoft.onlineid.internal.ApiRequestResultReceiver;
import com.microsoft.onlineid.internal.ApiResult;
import com.microsoft.onlineid.internal.exception.AccountNotFoundException;
import com.microsoft.onlineid.internal.sso.BundleMarshaller;
import com.microsoft.onlineid.internal.sso.BundleMarshallerException;
import com.microsoft.onlineid.internal.storage.TypedStorage;
import com.microsoft.onlineid.internal.sts.TicketManager;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;

public class InterruptResolutionActivity
  extends Activity
{
  private static final int PendingActivity = 1;
  private static final String TicketReceiverKey = "ticket_receiver";
  private static final String WebFlowReceiverKey = "web_flow_receiver";
  private String _accountCid;
  private String _accountPuid;
  private String _clientPackageName;
  private Bundle _clientState;
  private String _cobrandingId;
  private ISecurityScope _requestedScope;
  private ActivityResultSender _resultSender;
  private TypedStorage _storage;
  private TicketResultReceiver _ticketReceiver;
  private WebFlowResultReciever _webFlowReceiver;
  
  public static Intent getResolutionIntent(Context paramContext, Uri paramUri, AuthenticatorUserAccount paramAuthenticatorUserAccount, ISecurityScope paramISecurityScope, String paramString1, String paramString2, Bundle paramBundle)
  {
    paramContext = new Intent().setClass(paramContext, InterruptResolutionActivity.class).setData(paramUri).putExtra("com.microsoft.onlineid.user_puid", paramAuthenticatorUserAccount.getPuid()).putExtra("com.microsoft.onlineid.user_cid", paramAuthenticatorUserAccount.getCid()).putExtra("com.microsoft.onlineid.cobranding_id", paramString1).putExtra("com.microsoft.onlineid.client_package_name", paramString2).putExtra("com.microsoft.onlineid.client_state", paramBundle);
    if ((paramISecurityScope != null) && (paramString2 != null)) {
      paramContext.putExtras(BundleMarshaller.scopeToBundle(paramISecurityScope));
    }
    while ((paramISecurityScope == null) || (paramString2 != null)) {
      return paramContext;
    }
    throw new IllegalArgumentException("A ticket scope requires a client package name to make a request.");
  }
  
  protected String getAccountCid()
  {
    return this._accountCid;
  }
  
  protected String getAccountPuid()
  {
    return this._accountPuid;
  }
  
  protected String getClientPackageName()
  {
    return this._clientPackageName;
  }
  
  protected ISecurityScope getRequestedScope()
  {
    return this._requestedScope;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == 1) && (paramInt2 == 0)) {
      onUserCancel();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this._storage = new TypedStorage(getApplicationContext());
    this._resultSender = new ActivityResultSender(this, ActivityResultSender.ResultType.Ticket);
    if (paramBundle != null)
    {
      this._webFlowReceiver = ((WebFlowResultReciever)paramBundle.getParcelable("web_flow_receiver"));
      this._ticketReceiver = ((TicketResultReceiver)paramBundle.getParcelable("ticket_receiver"));
    }
    for (;;)
    {
      this._webFlowReceiver.setActivity(this);
      this._ticketReceiver.setActivity(this);
      if (BundleMarshaller.hasScope(getIntent().getExtras())) {}
      try
      {
        this._requestedScope = BundleMarshaller.scopeFromBundle(getIntent().getExtras());
        this._accountPuid = getIntent().getExtras().getString("com.microsoft.onlineid.user_puid");
        this._accountCid = getIntent().getExtras().getString("com.microsoft.onlineid.user_cid");
        this._clientPackageName = getIntent().getExtras().getString("com.microsoft.onlineid.client_package_name");
        this._cobrandingId = getIntent().getStringExtra("com.microsoft.onlineid.cobranding_id");
        this._clientState = getIntent().getBundleExtra("com.microsoft.onlineid.client_state");
        startActivity(WebFlowActivity.getFlowRequest(getApplicationContext(), getIntent().getData(), "com.microsoft.onlineid.internal.RESOLVE_INTERRUPT", true).setAccountPuid(this._accountPuid).setResultReceiver(this._webFlowReceiver).asIntent().addFlags(65536));
        return;
        this._webFlowReceiver = new WebFlowResultReciever(null);
        this._ticketReceiver = new TicketResultReceiver(null);
      }
      catch (BundleMarshallerException paramBundle)
      {
        for (;;)
        {
          onFailure(paramBundle);
        }
      }
    }
  }
  
  protected void onDestroy()
  {
    this._webFlowReceiver.setActivity(null);
    this._ticketReceiver.setActivity(null);
    super.onDestroy();
  }
  
  protected void onFailure(Exception paramException)
  {
    ApiRequest localApiRequest = new ApiRequest(getApplicationContext(), getIntent());
    if (localApiRequest.isSdkRequest()) {
      if ((paramException instanceof AccountNotFoundException)) {
        this._resultSender.putSignedOutCid(this._accountCid, false).set();
      }
    }
    for (;;)
    {
      finishActivity(1);
      finish();
      return;
      this._resultSender.putException(paramException).set();
      continue;
      localApiRequest.sendFailure(paramException);
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putParcelable("web_flow_receiver", this._webFlowReceiver);
    paramBundle.putParcelable("ticket_receiver", this._ticketReceiver);
    super.onSaveInstanceState(paramBundle);
  }
  
  protected void onTicketAcquired(Ticket paramTicket)
  {
    AuthenticatorUserAccount localAuthenticatorUserAccount = this._storage.readAccount(this._accountPuid);
    if (localAuthenticatorUserAccount == null)
    {
      onFailure(new AccountNotFoundException());
      return;
    }
    this._resultSender.putTicket(paramTicket).putLimitedUserAccount(localAuthenticatorUserAccount).set();
    finishActivity(1);
    finish();
  }
  
  protected void onUiNeeded(PendingIntent paramPendingIntent)
  {
    try
    {
      startIntentSenderForResult(paramPendingIntent.getIntentSender(), 0, null, 0, 0, 0);
      return;
    }
    catch (IntentSender.SendIntentException paramPendingIntent)
    {
      onFailure(paramPendingIntent);
    }
  }
  
  protected void onUserCancel()
  {
    ApiRequest localApiRequest = new ApiRequest(getApplicationContext(), getIntent());
    if (localApiRequest.hasResultReceiver()) {
      localApiRequest.sendUserCanceled();
    }
    finishActivity(1);
    finish();
  }
  
  protected void onWebFlowSucceeded(String paramString)
  {
    ApiRequest localApiRequest = new ApiRequest(getApplicationContext(), getIntent());
    if (localApiRequest.isSdkRequest())
    {
      if (this._requestedScope == null) {
        this._ticketReceiver.onFailure(new IllegalArgumentException("Scope must not be null for SSO ticket request."));
      }
      startService(new TicketManager(getApplicationContext()).createTicketRequest(this._accountPuid, this._requestedScope, this._clientPackageName, this._cobrandingId, this._clientState).setFlowToken(paramString).setResultReceiver(this._ticketReceiver).asIntent());
      showPendingActivity();
      return;
    }
    localApiRequest.sendSuccess(new ApiResult().setAccountPuid(this._accountPuid).setFlowToken(paramString));
    finish();
  }
  
  protected void showPendingActivity()
  {
    startActivityForResult(new Intent().setClassName(getApplicationContext(), "com.microsoft.onlineid.authenticator.AccountAddPendingActivity").addFlags(65536), 1);
  }
  
  private static abstract class DelegatedResultReceiver
    extends ApiRequestResultReceiver
  {
    protected InterruptResolutionActivity _activity;
    
    public DelegatedResultReceiver()
    {
      super();
    }
    
    protected void onFailure(Exception paramException)
    {
      if (this._activity != null) {
        this._activity.onFailure(paramException);
      }
    }
    
    protected void onUINeeded(PendingIntent paramPendingIntent)
    {
      if (this._activity != null) {
        this._activity.onUiNeeded(paramPendingIntent);
      }
    }
    
    protected void onUserCancel()
    {
      if (this._activity != null) {
        this._activity.onUserCancel();
      }
    }
    
    public void setActivity(InterruptResolutionActivity paramInterruptResolutionActivity)
    {
      this._activity = paramInterruptResolutionActivity;
    }
  }
  
  private static class TicketResultReceiver
    extends InterruptResolutionActivity.DelegatedResultReceiver
  {
    protected void onSuccess(ApiResult paramApiResult)
    {
      if (this._activity != null) {
        this._activity.onTicketAcquired(paramApiResult.getTicket());
      }
    }
  }
  
  private static class WebFlowResultReciever
    extends InterruptResolutionActivity.DelegatedResultReceiver
  {
    protected void onSuccess(ApiResult paramApiResult)
    {
      if (this._activity != null) {
        this._activity.onWebFlowSucceeded(paramApiResult.getFlowToken());
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\ui\InterruptResolutionActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */