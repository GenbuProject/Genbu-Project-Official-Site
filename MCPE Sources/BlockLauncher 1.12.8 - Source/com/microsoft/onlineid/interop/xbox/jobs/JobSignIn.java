package com.microsoft.onlineid.interop.xbox.jobs;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.util.Log;
import com.microsoft.onlineid.AccountManager;
import com.microsoft.onlineid.IAccountCallback;
import com.microsoft.onlineid.ITicketCallback;
import com.microsoft.onlineid.SecurityScope;
import com.microsoft.onlineid.Ticket;
import com.microsoft.onlineid.UserAccount;
import com.microsoft.onlineid.exception.AuthenticationException;

public class JobSignIn
  extends MSAJob
{
  private static final String TAG = JobSignIn.class.getSimpleName();
  private final IAccountCallback accountCallback = new IAccountCallback()
  {
    public void onAccountAcquired(UserAccount paramAnonymousUserAccount, Bundle paramAnonymousBundle)
    {
      Log.d(JobSignIn.TAG, "accountCallback.onAccountAcquired");
      JobSignIn.this.settings.setSavedId(paramAnonymousUserAccount.getCid());
      JobSignIn.this.settings.removeKnownSignedOutAccount(paramAnonymousUserAccount.getCid());
      paramAnonymousUserAccount.getTicket(new SecurityScope(JobSignIn.this.scope, JobSignIn.this.policy), paramAnonymousBundle);
    }
    
    public void onAccountSignedOut(String paramAnonymousString, boolean paramAnonymousBoolean, Bundle paramAnonymousBundle)
    {
      Log.d(JobSignIn.TAG, "accountCallback.onAccountSignedOut");
      if (paramAnonymousBoolean) {
        JobSignIn.this.settings.addKnownSignedOutAccount(paramAnonymousString);
      }
      JobSignIn.this.settings.clearSavedId();
      JobSignIn.this.callbacks.onSignedOut(JobSignIn.this);
    }
    
    public void onFailure(AuthenticationException paramAnonymousAuthenticationException, Bundle paramAnonymousBundle)
    {
      Log.d(JobSignIn.TAG, "accountCallback.onFailure: " + paramAnonymousAuthenticationException.getMessage());
      JobSignIn.this.callbacks.onFailure(JobSignIn.this, paramAnonymousAuthenticationException);
    }
    
    public void onUINeeded(PendingIntent paramAnonymousPendingIntent, Bundle paramAnonymousBundle)
    {
      Log.d(JobSignIn.TAG, "accountCallback.onUINeeded");
      try
      {
        JobSignIn.this.activity.startIntentSenderForResult(paramAnonymousPendingIntent.getIntentSender(), 0, null, 0, 0, 0);
        return;
      }
      catch (IntentSender.SendIntentException paramAnonymousPendingIntent)
      {
        Log.d(JobSignIn.TAG, paramAnonymousPendingIntent.getMessage());
        JobSignIn.this.callbacks.onFailure(JobSignIn.this, paramAnonymousPendingIntent);
      }
    }
    
    public void onUserCancel(Bundle paramAnonymousBundle)
    {
      Log.d(JobSignIn.TAG, "accountCallback.onUserCancel");
      JobSignIn.this.callbacks.onUserCancel(JobSignIn.this);
    }
  };
  private final Activity activity;
  private final String policy;
  private final String scope;
  private final ITicketCallback ticketCallback = new ITicketCallback()
  {
    public void onFailure(AuthenticationException paramAnonymousAuthenticationException, Bundle paramAnonymousBundle)
    {
      Log.d(JobSignIn.TAG, "ticketCallback.onFailure: " + paramAnonymousAuthenticationException.getMessage());
      JobSignIn.this.callbacks.onFailure(JobSignIn.this, paramAnonymousAuthenticationException);
    }
    
    public void onTicketAcquired(Ticket paramAnonymousTicket, UserAccount paramAnonymousUserAccount, Bundle paramAnonymousBundle)
    {
      Log.d(JobSignIn.TAG, "ticketCallback.onTicketAcquired");
      JobSignIn.this.callbacks.onTicketAcquired(JobSignIn.this, paramAnonymousTicket);
    }
    
    public void onUINeeded(PendingIntent paramAnonymousPendingIntent, Bundle paramAnonymousBundle)
    {
      Log.d(JobSignIn.TAG, "ticketCallback.onUINeeded");
      try
      {
        JobSignIn.this.activity.startIntentSenderForResult(paramAnonymousPendingIntent.getIntentSender(), 0, null, 0, 0, 0);
        return;
      }
      catch (IntentSender.SendIntentException paramAnonymousPendingIntent)
      {
        Log.d(JobSignIn.TAG, paramAnonymousPendingIntent.getMessage());
        JobSignIn.this.callbacks.onFailure(JobSignIn.this, paramAnonymousPendingIntent);
      }
    }
    
    public void onUserCancel(Bundle paramAnonymousBundle)
    {
      Log.d(JobSignIn.TAG, "ticketCallback.onUserCancel");
      JobSignIn.this.callbacks.onUserCancel(JobSignIn.this);
    }
  };
  
  public JobSignIn(Activity paramActivity, MSAJob.Callbacks paramCallbacks, String paramString1, String paramString2)
  {
    super(paramActivity.getApplicationContext(), paramCallbacks);
    this.activity = paramActivity;
    this.scope = paramString1;
    this.policy = paramString2;
    this.accountManager.setAccountCallback(this.accountCallback);
    this.accountManager.setTicketCallback(this.ticketCallback);
  }
  
  public MSAJob.Type getType()
  {
    return MSAJob.Type.SIGN_IN;
  }
  
  public JobSignIn start()
  {
    this.accountManager.getAccountPickerIntent(null, null);
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\interop\xbox\jobs\JobSignIn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */