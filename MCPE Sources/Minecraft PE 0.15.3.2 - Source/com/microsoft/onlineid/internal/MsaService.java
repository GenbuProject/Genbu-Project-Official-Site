package com.microsoft.onlineid.internal;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.microsoft.onlineid.Ticket;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.exception.InternalException;
import com.microsoft.onlineid.internal.exception.PromptNeededException;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.profile.ProfileManager;
import com.microsoft.onlineid.internal.sso.client.BackupService;
import com.microsoft.onlineid.internal.storage.TypedStorage;
import com.microsoft.onlineid.internal.sts.TicketManager;

public class MsaService
  extends IntentService
{
  public static final String ActionGetTicket = "com.microsoft.onlineid.internal.GET_TICKET";
  public static final String ActionSignOut = "com.microsoft.onlineid.internal.SIGN_OUT";
  public static final String ActionSignOutAllApps = "com.microsoft.onlineid.internal.SIGN_OUT_ALL_APPS";
  public static final String ActionUpdateProfile = "com.microsoft.onlineid.internal.UPDATE_PROFILE";
  private ProfileManager _profileManager;
  private TicketManager _ticketManager;
  private TypedStorage _typedStorage;
  
  public MsaService()
  {
    super(MsaService.class.getName());
  }
  
  public void onCreate()
  {
    super.onCreate();
    Context localContext = getApplicationContext();
    this._profileManager = new ProfileManager(localContext);
    this._ticketManager = new TicketManager(localContext);
    this._typedStorage = new TypedStorage(localContext);
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    ApiRequest localApiRequest = new ApiRequest(getApplicationContext(), paramIntent);
    paramIntent = paramIntent.getAction();
    try
    {
      String str = localApiRequest.getAccountPuid();
      if ("com.microsoft.onlineid.internal.GET_TICKET".equals(paramIntent))
      {
        Ticket localTicket = this._ticketManager.getTicket(str, localApiRequest.getScope(), localApiRequest.getClientPackageName(), localApiRequest.getFlowToken(), false, localApiRequest.getCobrandingId(), localApiRequest.getIsWebFlowTelemetryRequested(), localApiRequest.getClientStateBundle());
        localApiRequest.sendSuccess(new ApiResult().setAccountPuid(str).addTicket(localTicket));
        return;
      }
      if ("com.microsoft.onlineid.internal.UPDATE_PROFILE".equals(paramIntent))
      {
        this._profileManager.updateProfile(localApiRequest.getAccountPuid(), localApiRequest.getFlowToken());
        localApiRequest.sendSuccess(new ApiResult().setAccountPuid(str));
        return;
      }
    }
    catch (PromptNeededException localPromptNeededException)
    {
      Logger.info("ApiRequest with action " + paramIntent + " requires UI to complete.");
      localApiRequest.sendUINeeded(new PendingIntentBuilder(localPromptNeededException.getRequest().setResultReceiver(localApiRequest.getResultReceiver()).setIsSdkRequest(localApiRequest.isSdkRequest()).setContinuation(localApiRequest)).buildActivity());
      return;
      if ("com.microsoft.onlineid.internal.SIGN_OUT".equals(paramIntent))
      {
        localApiRequest.sendSuccess(new ApiResult());
        return;
      }
    }
    catch (Exception localException)
    {
      ClientAnalytics.get().logException(localException);
      Logger.error("ApiRequest with action " + paramIntent + " failed.", localException);
      localApiRequest.sendFailure(localException);
      return;
    }
    if ("com.microsoft.onlineid.internal.SIGN_OUT_ALL_APPS".equals(paramIntent))
    {
      this._typedStorage.removeAccount(localException);
      BackupService.pushBackup(getApplicationContext());
      localApiRequest.sendSuccess(new ApiResult());
      return;
    }
    throw new InternalException("Unknown action: " + paramIntent);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\MsaService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */