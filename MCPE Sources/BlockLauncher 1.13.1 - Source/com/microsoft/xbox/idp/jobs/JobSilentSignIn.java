package com.microsoft.xbox.idp.jobs;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.microsoft.onlineid.AccountManager;
import com.microsoft.onlineid.IAccountCallback;
import com.microsoft.onlineid.ITicketCallback;
import com.microsoft.onlineid.SecurityScope;
import com.microsoft.onlineid.Ticket;
import com.microsoft.onlineid.UserAccount;
import com.microsoft.onlineid.exception.AuthenticationException;
import com.microsoft.xbox.idp.telemetry.helpers.UTCError;
import com.microsoft.xbox.idp.telemetry.helpers.UTCSignin;
import com.microsoft.xbox.idp.telemetry.helpers.UTCTelemetry.CallBackSources;

public class JobSilentSignIn
  extends MSAJob
{
  private static final String TAG = JobSilentSignIn.class.getSimpleName();
  private final IAccountCallback accountCallback = new IAccountCallback()
  {
    public void onAccountAcquired(UserAccount paramAnonymousUserAccount, Bundle paramAnonymousBundle)
    {
      Log.d(JobSilentSignIn.TAG, "accountCallback.onAccountAcquired");
      UTCSignin.trackAccountAcquired(JobSilentSignIn.TAG, paramAnonymousUserAccount.getCid(), true);
      JobSilentSignIn.this.callbacks.onAccountAcquired(JobSilentSignIn.this, paramAnonymousUserAccount);
      paramAnonymousUserAccount.getTicket(new SecurityScope(JobSilentSignIn.this.scope, JobSilentSignIn.this.policy), paramAnonymousBundle);
    }
    
    public void onAccountSignedOut(String paramAnonymousString, boolean paramAnonymousBoolean, Bundle paramAnonymousBundle)
    {
      Log.d(JobSilentSignIn.TAG, "accountCallback.onAccountSignedOut");
      UTCError.trackSignedOut(JobSilentSignIn.TAG, true, UTCTelemetry.CallBackSources.Account);
      JobSilentSignIn.this.callbacks.onSignedOut(JobSilentSignIn.this);
    }
    
    public void onFailure(AuthenticationException paramAnonymousAuthenticationException, Bundle paramAnonymousBundle)
    {
      Log.d(JobSilentSignIn.TAG, "accountCallback.onFailure: " + paramAnonymousAuthenticationException.getMessage());
      UTCError.trackFailure(JobSilentSignIn.TAG, true, UTCTelemetry.CallBackSources.Account, paramAnonymousAuthenticationException);
      JobSilentSignIn.this.callbacks.onFailure(JobSilentSignIn.this, paramAnonymousAuthenticationException);
    }
    
    public void onUINeeded(PendingIntent paramAnonymousPendingIntent, Bundle paramAnonymousBundle)
    {
      Log.d(JobSilentSignIn.TAG, "accountCallback.onUINeeded");
      UTCError.trackUINeeded(JobSilentSignIn.TAG, true, UTCTelemetry.CallBackSources.Account);
      JobSilentSignIn.this.callbacks.onUiNeeded(JobSilentSignIn.this);
    }
    
    public void onUserCancel(Bundle paramAnonymousBundle)
    {
      Log.d(JobSilentSignIn.TAG, "accountCallback.onUserCancel");
      UTCError.trackUserCancel(JobSilentSignIn.TAG, true, UTCTelemetry.CallBackSources.Account);
      JobSilentSignIn.this.callbacks.onUserCancel(JobSilentSignIn.this);
    }
  };
  private final CharSequence activityTitle;
  private final String cid;
  private final String policy;
  private final String scope;
  private final ITicketCallback ticketCallback = new ITicketCallback()
  {
    public void onFailure(AuthenticationException paramAnonymousAuthenticationException, Bundle paramAnonymousBundle)
    {
      Log.d(JobSilentSignIn.TAG, "ticketCallback.onFailure: " + paramAnonymousAuthenticationException.getMessage());
      UTCError.trackFailure(JobSilentSignIn.TAG, true, UTCTelemetry.CallBackSources.Ticket, paramAnonymousAuthenticationException);
      JobSilentSignIn.this.callbacks.onFailure(JobSilentSignIn.this, paramAnonymousAuthenticationException);
    }
    
    public void onTicketAcquired(Ticket paramAnonymousTicket, UserAccount paramAnonymousUserAccount, Bundle paramAnonymousBundle)
    {
      Log.d(JobSilentSignIn.TAG, "ticketCallback.onTicketAcquired");
      UTCSignin.trackTicketAcquired(JobSilentSignIn.TAG, paramAnonymousUserAccount.getCid(), true);
      JobSilentSignIn.this.callbacks.onTicketAcquired(JobSilentSignIn.this, paramAnonymousTicket);
    }
    
    public void onUINeeded(PendingIntent paramAnonymousPendingIntent, Bundle paramAnonymousBundle)
    {
      Log.d(JobSilentSignIn.TAG, "ticketCallback.onUINeeded");
      UTCError.trackUINeeded(JobSilentSignIn.TAG, true, UTCTelemetry.CallBackSources.Ticket);
      JobSilentSignIn.this.callbacks.onUiNeeded(JobSilentSignIn.this);
    }
    
    public void onUserCancel(Bundle paramAnonymousBundle)
    {
      Log.d(JobSilentSignIn.TAG, "ticketCallback.onUserCancel");
      UTCError.trackUserCancel(JobSilentSignIn.TAG, true, UTCTelemetry.CallBackSources.Ticket);
      JobSilentSignIn.this.callbacks.onUserCancel(JobSilentSignIn.this);
    }
  };
  
  public JobSilentSignIn(Context paramContext, CharSequence paramCharSequence, MSAJob.Callbacks paramCallbacks, String paramString1, String paramString2, String paramString3)
  {
    super(paramContext.getApplicationContext(), paramCallbacks);
    this.activityTitle = paramCharSequence;
    this.scope = paramString1;
    this.policy = paramString2;
    this.cid = paramString3;
    this.accountManager.setAccountCallback(this.accountCallback);
    this.accountManager.setTicketCallback(this.ticketCallback);
  }
  
  public MSAJob.Type getType()
  {
    return MSAJob.Type.SILENT_SIGN_IN;
  }
  
  public JobSilentSignIn start()
  {
    this.accountManager.getAccountById(this.cid, null);
    UTCSignin.trackSignin(this.cid, true, this.activityTitle);
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\jobs\JobSilentSignIn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */