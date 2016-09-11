package com.microsoft.onlineid.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import com.microsoft.onlineid.ISecurityScope;
import com.microsoft.onlineid.Ticket;
import java.util.Locale;

public class ApiRequest
{
  protected final Context _applicationContext;
  protected final Intent _intent;
  
  public ApiRequest(Context paramContext, Intent paramIntent)
  {
    this._applicationContext = paramContext;
    this._intent = paramIntent;
  }
  
  private void sendResult(int paramInt, ApiResult paramApiResult)
  {
    ResultReceiver localResultReceiver = getResultReceiver();
    if (localResultReceiver != null) {
      localResultReceiver.send(paramInt, paramApiResult.asBundle());
    }
  }
  
  public ApiRequest addTicket(Ticket paramTicket)
  {
    this._intent.putExtra(getTicketKey(paramTicket.getScope()), paramTicket);
    return this;
  }
  
  public Intent asIntent()
  {
    return this._intent;
  }
  
  public void executeAsync()
  {
    getContext().startService(this._intent);
  }
  
  public String getAccountName()
  {
    return this._intent.getStringExtra(Extras.AccountName.getKey());
  }
  
  public String getAccountPuid()
  {
    return this._intent.getStringExtra(Extras.AccountPuid.getKey());
  }
  
  public String getClientPackageName()
  {
    return this._intent.getStringExtra(Extras.ClientPackageName.getKey());
  }
  
  public Bundle getClientStateBundle()
  {
    return this._intent.getBundleExtra(Extras.ClientStateBundle.getKey());
  }
  
  public String getCobrandingId()
  {
    return this._intent.getStringExtra(Extras.CobrandingId.getKey());
  }
  
  public Context getContext()
  {
    return this._applicationContext;
  }
  
  public Intent getContinuation()
  {
    return (Intent)this._intent.getParcelableExtra(Extras.Continuation.getKey());
  }
  
  public String getFlowToken()
  {
    return this._intent.getStringExtra(Extras.FlowToken.getKey());
  }
  
  public boolean getIsWebFlowTelemetryRequested()
  {
    return this._intent.getBooleanExtra(Extras.WebFlowTelemetryRequested.getKey(), false);
  }
  
  public ResultReceiver getResultReceiver()
  {
    return (ResultReceiver)this._intent.getParcelableExtra(Extras.ResultReceiver.getKey());
  }
  
  public ISecurityScope getScope()
  {
    return (ISecurityScope)this._intent.getSerializableExtra(Extras.Scope.getKey());
  }
  
  public Ticket getTicket(ISecurityScope paramISecurityScope)
  {
    if (paramISecurityScope == null) {
      return null;
    }
    return (Ticket)this._intent.getSerializableExtra(getTicketKey(paramISecurityScope));
  }
  
  protected String getTicketKey(ISecurityScope paramISecurityScope)
  {
    return TextUtils.join(".", new Object[] { "com.microsoft.msa.authenticator", "Ticket", paramISecurityScope.getTarget().toLowerCase(Locale.US), paramISecurityScope.getPolicy().toLowerCase(Locale.US) });
  }
  
  public boolean hasResultReceiver()
  {
    return getResultReceiver() != null;
  }
  
  public boolean isSdkRequest()
  {
    return this._intent.getBooleanExtra(Extras.IsSdkRequest.getKey(), false);
  }
  
  public void sendFailure(Exception paramException)
  {
    sendResult(1, new ApiResult().setException(paramException));
  }
  
  public void sendSuccess(ApiResult paramApiResult)
  {
    Intent localIntent = getContinuation();
    if (localIntent != null)
    {
      localIntent.fillIn(new Intent().putExtras(paramApiResult.asBundle()), 0);
      getContext().startService(localIntent);
      return;
    }
    sendResult(-1, paramApiResult);
  }
  
  public void sendUINeeded(PendingIntent paramPendingIntent)
  {
    sendResult(2, new ApiResult().setUINeededIntent(paramPendingIntent));
  }
  
  public void sendUserCanceled()
  {
    sendResult(0, new ApiResult());
  }
  
  public ApiRequest setAccountName(String paramString)
  {
    this._intent.putExtra(Extras.AccountName.getKey(), paramString);
    return this;
  }
  
  public ApiRequest setAccountPuid(String paramString)
  {
    this._intent.putExtra(Extras.AccountPuid.getKey(), paramString);
    return this;
  }
  
  public ApiRequest setClientPackageName(String paramString)
  {
    this._intent.putExtra(Extras.ClientPackageName.getKey(), paramString);
    return this;
  }
  
  public ApiRequest setClientStateBundle(Bundle paramBundle)
  {
    this._intent.putExtra(Extras.ClientStateBundle.getKey(), paramBundle);
    return this;
  }
  
  public ApiRequest setCobrandingId(String paramString)
  {
    this._intent.putExtra(Extras.CobrandingId.getKey(), paramString);
    return this;
  }
  
  public ApiRequest setContinuation(ApiRequest paramApiRequest)
  {
    this._intent.putExtra(Extras.Continuation.getKey(), paramApiRequest.asIntent());
    return this;
  }
  
  public ApiRequest setFlowToken(String paramString)
  {
    this._intent.putExtra(Extras.FlowToken.getKey(), paramString);
    return this;
  }
  
  public ApiRequest setIsSdkRequest(boolean paramBoolean)
  {
    this._intent.putExtra(Extras.IsSdkRequest.getKey(), paramBoolean);
    return this;
  }
  
  public ApiRequest setIsWebFlowTelemetryRequested(boolean paramBoolean)
  {
    this._intent.putExtra(Extras.WebFlowTelemetryRequested.getKey(), paramBoolean);
    return this;
  }
  
  public ApiRequest setResultReceiver(ResultReceiver paramResultReceiver)
  {
    this._intent.putExtra(Extras.ResultReceiver.getKey(), paramResultReceiver);
    return this;
  }
  
  public ApiRequest setScope(ISecurityScope paramISecurityScope)
  {
    this._intent.putExtra(Extras.Scope.getKey(), paramISecurityScope);
    return this;
  }
  
  public static enum Extras
  {
    AccountName,  AccountPuid,  ClientPackageName,  ClientStateBundle,  CobrandingId,  Continuation,  FlowToken,  IsSdkRequest,  ResultReceiver,  Scope,  WebFlowTelemetryRequested;
    
    private Extras() {}
    
    public String getKey()
    {
      return "com.microsoft.msa.authenticator." + name();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\internal\ApiRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */