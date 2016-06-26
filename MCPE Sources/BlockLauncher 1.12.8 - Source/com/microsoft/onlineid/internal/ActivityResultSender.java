package com.microsoft.onlineid.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.microsoft.onlineid.Ticket;
import com.microsoft.onlineid.internal.exception.AccountNotFoundException;
import com.microsoft.onlineid.internal.sso.BundleMarshaller;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;

public class ActivityResultSender
{
  protected final Activity _activity;
  protected final Bundle _bundle;
  protected int _resultCode;
  
  public ActivityResultSender(Activity paramActivity, ResultType paramResultType)
  {
    this._activity = paramActivity;
    this._bundle = new Bundle();
    this._bundle.putString("com.microsoft.onlineid.result_type", paramResultType.getValue());
    this._bundle.putBundle("com.microsoft.onlineid.client_state", this._activity.getIntent().getBundleExtra("com.microsoft.onlineid.client_state"));
    this._resultCode = 0;
    set();
  }
  
  public ActivityResultSender putException(Exception paramException)
  {
    this._bundle.putAll(BundleMarshaller.exceptionToBundle(paramException));
    this._resultCode = -1;
    return this;
  }
  
  public ActivityResultSender putLimitedUserAccount(AuthenticatorUserAccount paramAuthenticatorUserAccount)
  {
    this._bundle.putAll(BundleMarshaller.limitedUserAccountToBundle(paramAuthenticatorUserAccount));
    this._resultCode = -1;
    return this;
  }
  
  public ActivityResultSender putSignedOutCid(String paramString, boolean paramBoolean)
  {
    this._bundle.putString("com.microsoft.onlineid.user_cid", paramString);
    this._bundle.putBoolean("com.microsoft.onlineid.signed_out_this_app_only", paramBoolean);
    return putException(new AccountNotFoundException("The account was signed out."));
  }
  
  public ActivityResultSender putTicket(Ticket paramTicket)
  {
    this._bundle.putAll(BundleMarshaller.ticketToBundle(paramTicket));
    this._resultCode = -1;
    return this;
  }
  
  public void set()
  {
    this._activity.setResult(this._resultCode, new Intent().putExtras(this._bundle));
  }
  
  public static enum ResultType
  {
    Account("account"),  Ticket("ticket");
    
    private final String _value;
    
    private ResultType(String paramString)
    {
      this._value = paramString;
    }
    
    public static ResultType fromString(String paramString)
    {
      ResultType[] arrayOfResultType = values();
      int j = arrayOfResultType.length;
      int i = 0;
      while (i < j)
      {
        ResultType localResultType = arrayOfResultType[i];
        if (localResultType.getValue().equals(paramString)) {
          return localResultType;
        }
        i += 1;
      }
      return null;
    }
    
    public String getValue()
    {
      return this._value;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\ActivityResultSender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */