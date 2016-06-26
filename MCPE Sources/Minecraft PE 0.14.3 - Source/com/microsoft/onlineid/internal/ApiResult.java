package com.microsoft.onlineid.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import android.text.TextUtils;
import com.microsoft.onlineid.ISecurityScope;
import com.microsoft.onlineid.Ticket;
import java.util.Locale;

public class ApiResult
{
  public static final int ResultException = 1;
  public static final int ResultUINeeded = 2;
  private final Bundle _bundle;
  
  public ApiResult()
  {
    this(new Bundle());
  }
  
  public ApiResult(Bundle paramBundle)
  {
    this._bundle = paramBundle;
  }
  
  public ApiResult addTicket(Ticket paramTicket)
  {
    setScope(paramTicket.getScope());
    this._bundle.putSerializable(getTicketKey(paramTicket.getScope()), paramTicket);
    return this;
  }
  
  public Bundle asBundle()
  {
    return this._bundle;
  }
  
  public String getAccountPuid()
  {
    return this._bundle.getString(ApiRequest.Extras.AccountPuid.getKey());
  }
  
  public Exception getException()
  {
    return (Exception)this._bundle.getSerializable(Extras.Exception.getKey());
  }
  
  public String getFlowToken()
  {
    return this._bundle.getString(ApiRequest.Extras.FlowToken.getKey());
  }
  
  public ISecurityScope getScope()
  {
    return (ISecurityScope)this._bundle.getSerializable(ApiRequest.Extras.Scope.getKey());
  }
  
  public Ticket getTicket()
  {
    return getTicket(getScope());
  }
  
  public Ticket getTicket(ISecurityScope paramISecurityScope)
  {
    if (paramISecurityScope == null) {
      return null;
    }
    return (Ticket)this._bundle.getSerializable(getTicketKey(paramISecurityScope));
  }
  
  protected String getTicketKey(ISecurityScope paramISecurityScope)
  {
    return TextUtils.join(".", new Object[] { "com.microsoft.msa.authenticator", "Ticket", paramISecurityScope.getTarget().toLowerCase(Locale.US), paramISecurityScope.getPolicy().toLowerCase(Locale.US) });
  }
  
  public PendingIntent getUINeededIntent()
  {
    return (PendingIntent)this._bundle.getParcelable(Extras.UINeededIntent.getKey());
  }
  
  public ApiResult setAccountPuid(String paramString)
  {
    this._bundle.putString(ApiRequest.Extras.AccountPuid.getKey(), paramString);
    return this;
  }
  
  public ApiResult setException(Exception paramException)
  {
    this._bundle.putSerializable(Extras.Exception.getKey(), paramException);
    return this;
  }
  
  public ApiResult setFlowToken(String paramString)
  {
    this._bundle.putString(ApiRequest.Extras.FlowToken.getKey(), paramString);
    return this;
  }
  
  public ApiResult setScope(ISecurityScope paramISecurityScope)
  {
    this._bundle.putSerializable(ApiRequest.Extras.Scope.getKey(), paramISecurityScope);
    return this;
  }
  
  public ApiResult setUINeededIntent(PendingIntent paramPendingIntent)
  {
    this._bundle.putParcelable(Extras.UINeededIntent.getKey(), paramPendingIntent);
    return this;
  }
  
  public static enum Extras
  {
    Exception,  UINeededIntent;
    
    private Extras() {}
    
    public String getKey()
    {
      return "com.microsoft.msa.authenticator." + name();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\ApiResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */