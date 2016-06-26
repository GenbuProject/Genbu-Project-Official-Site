package com.microsoft.onlineid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.BadParcelableException;
import android.os.Bundle;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.exception.AuthenticationException;
import com.microsoft.onlineid.exception.InternalException;
import com.microsoft.onlineid.internal.ActivityResultSender.ResultType;
import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.internal.Bundles;
import com.microsoft.onlineid.internal.IFailureCallback;
import com.microsoft.onlineid.internal.IUserInteractionCallback;
import com.microsoft.onlineid.internal.Strings;
import com.microsoft.onlineid.internal.configuration.Settings;
import com.microsoft.onlineid.internal.exception.AccountNotFoundException;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.sso.BundleMarshaller;
import com.microsoft.onlineid.internal.sso.BundleMarshallerException;
import com.microsoft.onlineid.internal.sso.client.MsaSsoClient;
import com.microsoft.onlineid.internal.sso.client.SsoResponse;
import com.microsoft.onlineid.internal.sso.client.SsoRunnable;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AccountManager
{
  private IAccountCallback _accountCallback;
  private IAccountCollectionCallback _accountCollectionCallback;
  private final Context _applicationContext;
  private final OnlineIdConfiguration _onlineIdConfiguration;
  private final MsaSsoClient _ssoClient;
  private ITicketCallback _ticketCallback;
  
  public AccountManager(Context paramContext)
  {
    this(paramContext, new OnlineIdConfiguration());
  }
  
  public AccountManager(Context paramContext, OnlineIdConfiguration paramOnlineIdConfiguration)
  {
    Context localContext = paramContext;
    if (paramContext.getApplicationContext() != null) {
      localContext = paramContext.getApplicationContext();
    }
    this._applicationContext = localContext;
    paramContext = paramOnlineIdConfiguration;
    if (paramOnlineIdConfiguration == null) {
      paramContext = new OnlineIdConfiguration();
    }
    this._onlineIdConfiguration = paramContext;
    this._ssoClient = new MsaSsoClient(this._applicationContext);
    ClientAnalytics.initialize(this._applicationContext);
    Logger.initialize(this._applicationContext);
  }
  
  private AccountManager getAccountManager()
  {
    return this;
  }
  
  private void verifyCallback(Object paramObject, String paramString)
  {
    if (paramObject == null) {
      throw new IllegalStateException("You must specify an " + paramString + " before invoking this method.");
    }
  }
  
  public void getAccount(Bundle paramBundle)
  {
    verifyCallback(this._accountCallback, IAccountCallback.class.getSimpleName());
    new Thread(getAccountRunnable(paramBundle)).start();
  }
  
  public void getAccountById(String paramString, Bundle paramBundle)
  {
    verifyCallback(this._accountCallback, IAccountCallback.class.getSimpleName());
    new Thread(getAccountByIdRunnable(paramString, paramBundle)).start();
  }
  
  protected SsoRunnable getAccountByIdRunnable(final String paramString, final Bundle paramBundle)
  {
    new SsoRunnable(this._accountCallback, paramBundle)
    {
      public void performRequest()
        throws AuthenticationException
      {
        try
        {
          Strings.verifyArgumentNotNullOrEmpty(paramString, "cid");
          AuthenticatorUserAccount localAuthenticatorUserAccount = AccountManager.this._ssoClient.getAccountById(paramString, paramBundle);
          AccountManager.this._accountCallback.onAccountAcquired(new UserAccount(AccountManager.this.getAccountManager(), localAuthenticatorUserAccount), paramBundle);
          return;
        }
        catch (AccountNotFoundException localAccountNotFoundException)
        {
          AccountManager.this._accountCallback.onAccountSignedOut(paramString, false, paramBundle);
        }
      }
    };
  }
  
  public void getAccountPickerIntent(Iterable<String> paramIterable, Bundle paramBundle)
  {
    verifyCallback(this._accountCallback, IAccountCallback.class.getSimpleName());
    new Thread(getAccountPickerIntentRunnable(paramIterable, paramBundle)).start();
  }
  
  protected SsoRunnable getAccountPickerIntentRunnable(final Iterable<String> paramIterable, final Bundle paramBundle)
  {
    new SsoRunnable(this._accountCallback, paramBundle)
    {
      public void performRequest()
        throws AuthenticationException
      {
        Object localObject = new ArrayList();
        if (paramIterable != null)
        {
          Iterator localIterator = paramIterable.iterator();
          while (localIterator.hasNext()) {
            ((ArrayList)localObject).add((String)localIterator.next());
          }
        }
        localObject = AccountManager.this._ssoClient.getAccountPickerIntent((ArrayList)localObject, AccountManager.this._onlineIdConfiguration, paramBundle);
        AccountManager.this._accountCallback.onUINeeded((PendingIntent)localObject, paramBundle);
      }
    };
  }
  
  protected SsoRunnable getAccountRunnable(final Bundle paramBundle)
  {
    new SsoRunnable(this._accountCallback, paramBundle)
    {
      public void performRequest()
        throws AuthenticationException
      {
        SsoResponse localSsoResponse = AccountManager.this._ssoClient.getAccount(AccountManager.this._onlineIdConfiguration, paramBundle);
        if (localSsoResponse.hasData())
        {
          AccountManager.this._accountCallback.onAccountAcquired(new UserAccount(AccountManager.this.getAccountManager(), (AuthenticatorUserAccount)localSsoResponse.getData()), paramBundle);
          return;
        }
        AccountManager.this._accountCallback.onUINeeded(localSsoResponse.getPendingIntent(), paramBundle);
      }
    };
  }
  
  public void getAllAccounts(Bundle paramBundle)
  {
    verifyCallback(this._accountCollectionCallback, IAccountCollectionCallback.class.getSimpleName());
    new Thread(getAllAccountsRunnable(paramBundle)).start();
  }
  
  protected SsoRunnable getAllAccountsRunnable(final Bundle paramBundle)
  {
    new SsoRunnable(this._accountCollectionCallback, paramBundle)
    {
      public void performRequest()
        throws AuthenticationException
      {
        Object localObject = AccountManager.this._ssoClient.getAllAccounts(paramBundle);
        HashSet localHashSet = new HashSet();
        localObject = ((Set)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          AuthenticatorUserAccount localAuthenticatorUserAccount = (AuthenticatorUserAccount)((Iterator)localObject).next();
          localHashSet.add(new UserAccount(AccountManager.this.getAccountManager(), localAuthenticatorUserAccount));
        }
        AccountManager.this._accountCollectionCallback.onAccountCollectionAcquired(localHashSet, paramBundle);
      }
    };
  }
  
  public void getSignInIntent(SignInOptions paramSignInOptions, Bundle paramBundle)
  {
    verifyCallback(this._accountCallback, IAccountCallback.class.getSimpleName());
    new Thread(getSignInIntentRunnable(paramSignInOptions, paramBundle)).start();
  }
  
  protected SsoRunnable getSignInIntentRunnable(final SignInOptions paramSignInOptions, final Bundle paramBundle)
  {
    new SsoRunnable(this._accountCallback, paramBundle)
    {
      public void performRequest()
        throws AuthenticationException
      {
        PendingIntent localPendingIntent = AccountManager.this._ssoClient.getSignInIntent(paramSignInOptions, AccountManager.this._onlineIdConfiguration, paramBundle);
        AccountManager.this._accountCallback.onUINeeded(localPendingIntent, paramBundle);
      }
    };
  }
  
  public void getSignOutIntent(UserAccount paramUserAccount, Bundle paramBundle)
  {
    verifyCallback(this._accountCallback, IAccountCallback.class.getSimpleName());
    new Thread(getSignOutIntentRunnable(paramUserAccount, paramBundle)).start();
  }
  
  protected SsoRunnable getSignOutIntentRunnable(final UserAccount paramUserAccount, final Bundle paramBundle)
  {
    new SsoRunnable(this._accountCallback, paramBundle)
    {
      public void performRequest()
        throws AuthenticationException
      {
        try
        {
          PendingIntent localPendingIntent = AccountManager.this._ssoClient.getSignOutIntent(paramUserAccount.getCid(), paramBundle);
          AccountManager.this._accountCallback.onUINeeded(localPendingIntent, paramBundle);
          return;
        }
        catch (AccountNotFoundException localAccountNotFoundException)
        {
          AccountManager.this._accountCallback.onAccountSignedOut(paramUserAccount.getCid(), false, paramBundle);
        }
      }
    };
  }
  
  public void getSignUpIntent(Bundle paramBundle)
  {
    verifyCallback(this._accountCallback, IAccountCallback.class.getSimpleName());
    new Thread(getSignUpIntentRunnable(paramBundle)).start();
  }
  
  protected SsoRunnable getSignUpIntentRunnable(final Bundle paramBundle)
  {
    new SsoRunnable(this._accountCallback, paramBundle)
    {
      public void performRequest()
        throws AuthenticationException
      {
        PendingIntent localPendingIntent = AccountManager.this._ssoClient.getSignUpIntent(AccountManager.this._onlineIdConfiguration, paramBundle);
        AccountManager.this._accountCallback.onUINeeded(localPendingIntent, paramBundle);
      }
    };
  }
  
  void getTicket(UserAccount paramUserAccount, ISecurityScope paramISecurityScope, Bundle paramBundle)
  {
    verifyCallback(this._accountCallback, IAccountCallback.class.getSimpleName());
    verifyCallback(this._ticketCallback, ITicketCallback.class.getSimpleName());
    new Thread(getTicketRunnable(paramUserAccount, paramISecurityScope, paramBundle)).start();
  }
  
  protected SsoRunnable getTicketRunnable(final UserAccount paramUserAccount, final ISecurityScope paramISecurityScope, final Bundle paramBundle)
  {
    new SsoRunnable(this._ticketCallback, paramBundle)
    {
      public void performRequest()
        throws AuthenticationException
      {
        try
        {
          SsoResponse localSsoResponse = AccountManager.this._ssoClient.getTicket(paramUserAccount.getCid(), paramISecurityScope, AccountManager.this._onlineIdConfiguration, paramBundle);
          if (localSsoResponse.hasData())
          {
            AccountManager.this._ticketCallback.onTicketAcquired((Ticket)localSsoResponse.getData(), paramUserAccount, paramBundle);
            return;
          }
          AccountManager.this._ticketCallback.onUINeeded(localSsoResponse.getPendingIntent(), paramBundle);
          return;
        }
        catch (AccountNotFoundException localAccountNotFoundException)
        {
          AccountManager.this._accountCallback.onAccountSignedOut(paramUserAccount.getCid(), false, paramBundle);
        }
      }
    };
  }
  
  public boolean onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    boolean bool = false;
    Object localObject3 = null;
    if (paramIntent != null) {}
    for (;;)
    {
      Intent localIntent;
      try
      {
        paramIntent = paramIntent.getExtras();
        if (paramIntent == null) {
          break label481;
        }
        localObject3 = paramIntent.getBundle("com.microsoft.onlineid.client_state");
        localObject4 = paramIntent.getString("com.microsoft.onlineid.result_type");
        localObject2 = ActivityResultSender.ResultType.fromString((String)localObject4);
        localIntent = paramIntent;
        paramIntent = (Intent)localObject3;
        localObject3 = localObject4;
        if (Settings.isDebugBuild())
        {
          Logger.info("Activity result: request: " + paramInt1 + ", result: " + paramInt2);
          Bundles.log("With extras:", localIntent);
        }
        if (localObject2 == null)
        {
          Logger.info("Unknown result type (" + (String)localObject3 + ") encountered, ignoring.");
          return false;
        }
      }
      catch (BadParcelableException paramIntent)
      {
        Logger.info("Caught BadParcelableException when checking extras, ignoring: " + paramIntent);
        return false;
      }
      if (localObject2 == ActivityResultSender.ResultType.Ticket)
      {
        localObject4 = this._ticketCallback;
        if (localObject2 != ActivityResultSender.ResultType.Ticket) {
          break label210;
        }
        localObject3 = this._ticketCallback;
        label187:
        if (paramInt2 != 0) {
          break label219;
        }
        ((IUserInteractionCallback)localObject4).onUserCancel(paramIntent);
      }
      for (;;)
      {
        return true;
        localObject4 = this._accountCallback;
        break;
        label210:
        localObject3 = this._accountCallback;
        break label187;
        label219:
        if (paramInt2 == -1)
        {
          try
          {
            if (!BundleMarshaller.hasError(localIntent)) {
              break label335;
            }
            localObject2 = BundleMarshaller.exceptionFromBundle(localIntent);
            if (!(localObject2 instanceof AccountNotFoundException)) {
              break label322;
            }
            localObject2 = localIntent.getString("com.microsoft.onlineid.user_cid");
            if (localObject2 != null) {
              bool = true;
            }
            Assertion.check(bool, "Expect to find a CID for sign-out notification.");
            bool = localIntent.getBoolean("com.microsoft.onlineid.signed_out_this_app_only");
            this._accountCallback.onAccountSignedOut((String)localObject2, bool, paramIntent);
          }
          catch (BundleMarshallerException localBundleMarshallerException)
          {
            ((IFailureCallback)localObject3).onFailure(new InternalException(localBundleMarshallerException), paramIntent);
          }
          continue;
          label322:
          ((IFailureCallback)localObject3).onFailure((AuthenticationException)localObject2, paramIntent);
          continue;
          label335:
          if (BundleMarshaller.hasPendingIntent(localBundleMarshallerException))
          {
            ((IUserInteractionCallback)localObject4).onUINeeded(BundleMarshaller.pendingIntentFromBundle(localBundleMarshallerException), paramIntent);
          }
          else if ((localObject2 == ActivityResultSender.ResultType.Ticket) && (BundleMarshaller.hasTicket(localBundleMarshallerException)))
          {
            localObject2 = BundleMarshaller.limitedUserAccountFromBundle(localBundleMarshallerException);
            localObject1 = BundleMarshaller.ticketFromBundle(localBundleMarshallerException);
            this._ticketCallback.onTicketAcquired((Ticket)localObject1, new UserAccount(this, (AuthenticatorUserAccount)localObject2), paramIntent);
          }
          else if ((localObject2 == ActivityResultSender.ResultType.Account) && (BundleMarshaller.hasLimitedUserAccount((Bundle)localObject1)))
          {
            localObject1 = BundleMarshaller.limitedUserAccountFromBundle((Bundle)localObject1);
            this._accountCallback.onAccountAcquired(new UserAccount(this, (AuthenticatorUserAccount)localObject1), paramIntent);
          }
          else
          {
            ((IFailureCallback)localObject3).onFailure(new InternalException("Unexpected onActivityResult found."), paramIntent);
          }
        }
      }
      label481:
      Object localObject4 = null;
      Object localObject1 = paramIntent;
      Object localObject2 = null;
      paramIntent = (Intent)localObject4;
      continue;
      paramIntent = null;
      localObject1 = null;
      localObject2 = null;
    }
  }
  
  public AccountManager setAccountCallback(IAccountCallback paramIAccountCallback)
  {
    this._accountCallback = paramIAccountCallback;
    return this;
  }
  
  public AccountManager setAccountCollectionCallback(IAccountCollectionCallback paramIAccountCollectionCallback)
  {
    this._accountCollectionCallback = paramIAccountCollectionCallback;
    return this;
  }
  
  public AccountManager setTicketCallback(ITicketCallback paramITicketCallback)
  {
    this._ticketCallback = paramITicketCallback;
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\AccountManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */