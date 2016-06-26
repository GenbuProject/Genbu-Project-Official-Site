package com.microsoft.onlineid.interop.xbox.jobs;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.util.Log;
import com.microsoft.onlineid.AccountManager;
import com.microsoft.onlineid.IAccountCallback;
import com.microsoft.onlineid.ITicketCallback;
import com.microsoft.onlineid.Ticket;
import com.microsoft.onlineid.UserAccount;
import com.microsoft.onlineid.exception.AuthenticationException;

public class JobSignOut
  extends MSAJob
{
  private static final String TAG;
  private final IAccountCallback accountCallback = new IAccountCallback()
  {
    public void onAccountAcquired(UserAccount paramAnonymousUserAccount, Bundle paramAnonymousBundle)
    {
      Log.d(JobSignOut.TAG, "accountCallback.onAccountAcquired");
      JobSignOut.this.accountManager.getSignOutIntent(paramAnonymousUserAccount, paramAnonymousBundle);
    }
    
    public void onAccountSignedOut(String paramAnonymousString, boolean paramAnonymousBoolean, Bundle paramAnonymousBundle)
    {
      Log.d(JobSignOut.TAG, "accountCallback.onAccountSignedOut");
      if (paramAnonymousBoolean) {
        JobSignOut.this.settings.addKnownSignedOutAccount(paramAnonymousString);
      }
      JobSignOut.this.settings.clearSavedId();
      JobSignOut.this.callbacks.onSignedOut(JobSignOut.this);
    }
    
    public void onFailure(AuthenticationException paramAnonymousAuthenticationException, Bundle paramAnonymousBundle)
    {
      Log.d(JobSignOut.TAG, "accountCallback.onFailure: " + paramAnonymousAuthenticationException.getMessage());
      JobSignOut.this.callbacks.onFailure(JobSignOut.this, paramAnonymousAuthenticationException);
    }
    
    public void onUINeeded(PendingIntent paramAnonymousPendingIntent, Bundle paramAnonymousBundle)
    {
      Log.d(JobSignOut.TAG, "accountCallback.onUINeeded");
      try
      {
        JobSignOut.this.activity.startIntentSenderForResult(paramAnonymousPendingIntent.getIntentSender(), 0, null, 0, 0, 0);
        return;
      }
      catch (IntentSender.SendIntentException paramAnonymousPendingIntent)
      {
        Log.d(JobSignOut.TAG, paramAnonymousPendingIntent.getMessage());
        JobSignOut.this.callbacks.onFailure(JobSignOut.this, paramAnonymousPendingIntent);
      }
    }
    
    public void onUserCancel(Bundle paramAnonymousBundle)
    {
      Log.d(JobSignOut.TAG, "accountCallback.onUserCancel");
      JobSignOut.this.callbacks.onUserCancel(JobSignOut.this);
    }
  };
  protected final Activity activity;
  private final ITicketCallback ticketCallback = new ITicketCallback()
  {
    public void onFailure(AuthenticationException paramAnonymousAuthenticationException, Bundle paramAnonymousBundle)
    {
      Log.d(JobSignOut.TAG, "ticketCallback.onFailure: " + paramAnonymousAuthenticationException.getMessage());
      JobSignOut.this.callbacks.onFailure(JobSignOut.this, paramAnonymousAuthenticationException);
    }
    
    public void onTicketAcquired(Ticket paramAnonymousTicket, UserAccount paramAnonymousUserAccount, Bundle paramAnonymousBundle)
    {
      Log.d(JobSignOut.TAG, "ticketCallback.onTicketAcquired");
      JobSignOut.this.callbacks.onTicketAcquired(JobSignOut.this, paramAnonymousTicket);
    }
    
    public void onUINeeded(PendingIntent paramAnonymousPendingIntent, Bundle paramAnonymousBundle)
    {
      Log.d(JobSignOut.TAG, "ticketCallback.onUINeeded");
      try
      {
        JobSignOut.this.activity.startIntentSenderForResult(paramAnonymousPendingIntent.getIntentSender(), 0, null, 0, 0, 0);
        return;
      }
      catch (IntentSender.SendIntentException paramAnonymousPendingIntent)
      {
        Log.d(JobSignOut.TAG, paramAnonymousPendingIntent.getMessage());
        JobSignOut.this.callbacks.onFailure(JobSignOut.this, paramAnonymousPendingIntent);
      }
    }
    
    public void onUserCancel(Bundle paramAnonymousBundle)
    {
      Log.d(JobSignOut.TAG, "ticketCallback.onUserCancel");
      JobSignOut.this.callbacks.onUserCancel(JobSignOut.this);
    }
  };
  
  static
  {
    if (!JobSignOut.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      TAG = JobSignOut.class.getSimpleName();
      return;
    }
  }
  
  public JobSignOut(Activity paramActivity, MSAJob.Callbacks paramCallbacks)
  {
    super(paramActivity.getApplicationContext(), paramCallbacks);
    this.activity = paramActivity;
    this.accountManager.setAccountCallback(this.accountCallback);
    this.accountManager.setTicketCallback(this.ticketCallback);
  }
  
  public MSAJob.Type getType()
  {
    return MSAJob.Type.SIGN_OUT;
  }
  
  public JobSignOut start()
  {
    assert (this.settings.hasSavedId());
    this.accountManager.getAccountById(this.settings.getSavedId(), null);
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\interop\xbox\jobs\JobSignOut.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */