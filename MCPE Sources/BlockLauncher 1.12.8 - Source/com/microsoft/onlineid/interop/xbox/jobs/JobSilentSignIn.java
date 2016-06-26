package com.microsoft.onlineid.interop.xbox.jobs;

import android.app.Activity;
import android.app.PendingIntent;
import android.os.Bundle;
import android.util.Log;
import com.microsoft.onlineid.AccountManager;
import com.microsoft.onlineid.IAccountCallback;
import com.microsoft.onlineid.ITicketCallback;
import com.microsoft.onlineid.SecurityScope;
import com.microsoft.onlineid.Ticket;
import com.microsoft.onlineid.UserAccount;
import com.microsoft.onlineid.exception.AuthenticationException;

public class JobSilentSignIn
  extends MSAJob
{
  private static final String TAG = JobSilentSignIn.class.getSimpleName();
  private final IAccountCallback accountCallback = new IAccountCallback()
  {
    public void onAccountAcquired(UserAccount paramAnonymousUserAccount, Bundle paramAnonymousBundle)
    {
      Log.d(JobSilentSignIn.TAG, "accountCallback.onAccountAcquired");
      if (JobSilentSignIn.this.settings.isKnownSignedOutAccount(paramAnonymousUserAccount.getCid()))
      {
        JobSilentSignIn.this.callbacks.onUiNeeded(JobSilentSignIn.this);
        return;
      }
      JobSilentSignIn.this.settings.setSavedId(paramAnonymousUserAccount.getCid());
      JobSilentSignIn.this.settings.removeKnownSignedOutAccount(paramAnonymousUserAccount.getCid());
      paramAnonymousUserAccount.getTicket(new SecurityScope(JobSilentSignIn.this.scope, JobSilentSignIn.this.policy), paramAnonymousBundle);
    }
    
    public void onAccountSignedOut(String paramAnonymousString, boolean paramAnonymousBoolean, Bundle paramAnonymousBundle)
    {
      Log.d(JobSilentSignIn.TAG, "accountCallback.onAccountSignedOut");
      if (paramAnonymousBoolean) {
        JobSilentSignIn.this.settings.addKnownSignedOutAccount(paramAnonymousString);
      }
      JobSilentSignIn.this.settings.clearSavedId();
      JobSilentSignIn.this.callbacks.onSignedOut(JobSilentSignIn.this);
    }
    
    public void onFailure(AuthenticationException paramAnonymousAuthenticationException, Bundle paramAnonymousBundle)
    {
      Log.d(JobSilentSignIn.TAG, "accountCallback.onFailure: " + paramAnonymousAuthenticationException.getMessage());
      JobSilentSignIn.this.callbacks.onFailure(JobSilentSignIn.this, paramAnonymousAuthenticationException);
    }
    
    public void onUINeeded(PendingIntent paramAnonymousPendingIntent, Bundle paramAnonymousBundle)
    {
      Log.d(JobSilentSignIn.TAG, "accountCallback.onUINeeded");
      JobSilentSignIn.this.callbacks.onUiNeeded(JobSilentSignIn.this);
    }
    
    public void onUserCancel(Bundle paramAnonymousBundle)
    {
      Log.d(JobSilentSignIn.TAG, "accountCallback.onUserCancel");
      JobSilentSignIn.this.callbacks.onUserCancel(JobSilentSignIn.this);
    }
  };
  private final String policy;
  private final String scope;
  private final ITicketCallback ticketCallback = new ITicketCallback()
  {
    public void onFailure(AuthenticationException paramAnonymousAuthenticationException, Bundle paramAnonymousBundle)
    {
      Log.d(JobSilentSignIn.TAG, "ticketCallback.onFailure: " + paramAnonymousAuthenticationException.getMessage());
      JobSilentSignIn.this.callbacks.onFailure(JobSilentSignIn.this, paramAnonymousAuthenticationException);
    }
    
    public void onTicketAcquired(Ticket paramAnonymousTicket, UserAccount paramAnonymousUserAccount, Bundle paramAnonymousBundle)
    {
      Log.d(JobSilentSignIn.TAG, "ticketCallback.onTicketAcquired");
      JobSilentSignIn.this.callbacks.onTicketAcquired(JobSilentSignIn.this, paramAnonymousTicket);
    }
    
    public void onUINeeded(PendingIntent paramAnonymousPendingIntent, Bundle paramAnonymousBundle)
    {
      Log.d(JobSilentSignIn.TAG, "ticketCallback.onUINeeded");
      JobSilentSignIn.this.callbacks.onUiNeeded(JobSilentSignIn.this);
    }
    
    public void onUserCancel(Bundle paramAnonymousBundle)
    {
      Log.d(JobSilentSignIn.TAG, "ticketCallback.onUserCancel");
      JobSilentSignIn.this.callbacks.onUserCancel(JobSilentSignIn.this);
    }
  };
  
  public JobSilentSignIn(Activity paramActivity, MSAJob.Callbacks paramCallbacks, String paramString1, String paramString2)
  {
    super(paramActivity.getApplicationContext(), paramCallbacks);
    this.scope = paramString1;
    this.policy = paramString2;
    this.accountManager.setAccountCallback(this.accountCallback);
    this.accountManager.setTicketCallback(this.ticketCallback);
  }
  
  public MSAJob.Type getType()
  {
    return MSAJob.Type.SILENT_SIGN_IN;
  }
  
  public JobSilentSignIn start()
  {
    if (this.settings.hasSavedId())
    {
      this.accountManager.getAccountById(this.settings.getSavedId(), null);
      return this;
    }
    this.accountManager.getAccount(null);
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\interop\xbox\jobs\JobSilentSignIn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */