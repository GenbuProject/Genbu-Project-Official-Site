package com.microsoft.xbox.idp.jobs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.microsoft.xbox.authenticate.DelegateRPSTicketResult;
import com.microsoft.xbox.authenticate.IDelegateKeyService;
import com.microsoft.xbox.authenticate.IDelegateKeyService.Stub;
import com.microsoft.xbox.idp.telemetry.helpers.UTCError;
import com.microsoft.xbox.idp.telemetry.helpers.UTCPageAction;
import com.microsoft.xbox.idp.telemetry.helpers.UTCTelemetry.CallBackSources;
import com.microsoft.xbox.idp.telemetry.utc.model.UTCAdditionalInfoModel;
import com.microsoft.xbox.idp.util.XboxAppLinker;

public class DelegatedAuthJob
{
  private static final int RESULT_INVALID_APPURI = 6;
  private static final int RESULT_INVALID_PACKAGE = 4;
  private static final int RESULT_INVALID_SIGNATURE = 5;
  private static final int RESULT_NOCID = 1;
  private static final int RESULT_SUCCESS = 0;
  private static final int RESULT_UNEXPECTED = 2;
  private static final int RESULT_UNKNOWN_PACKAGE = 3;
  private static final String TAG = DelegatedAuthJob.class.getSimpleName();
  private static Intent launchIntent = null;
  private final String XBOX_BROKER_SERVICE_NAME = "com.microsoft.xbox.authenticate.DelegateKeyService";
  private final Callbacks callbacks;
  private final ServiceConnection connection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      Log.i(DelegatedAuthJob.TAG, "Service connected");
      DelegatedAuthJob.this.keyService = IDelegateKeyService.Stub.asInterface(paramAnonymousIBinder);
      for (;;)
      {
        int i;
        try
        {
          paramAnonymousComponentName = DelegatedAuthJob.this.keyService.requestDelegateRPSTicketSilently();
          if (XboxAppLinker.xboxAppIsInstalled(DelegatedAuthJob.this.context))
          {
            DelegatedAuthJob.access$202(XboxAppLinker.getXboxAppLaunchIntent(DelegatedAuthJob.this.context));
            DelegatedAuthJob.launchIntent.setAction("com.microsoft.xbox.action.ACTION_SIGNIN");
          }
          i = paramAnonymousComponentName.getErrorCode();
          if (i == 0) {
            break label195;
          }
          Log.i(DelegatedAuthJob.TAG, "Error getting RPS ticket");
          if ((i != 1) && (i != 2)) {
            break label218;
          }
          DelegatedAuthJob.this.callbacks.onUiNeeded(DelegatedAuthJob.this);
          return;
        }
        catch (RemoteException paramAnonymousComponentName)
        {
          Log.i(DelegatedAuthJob.TAG, "Callback failure");
          paramAnonymousComponentName.printStackTrace();
          DelegatedAuthJob.this.callbacks.onFailure(DelegatedAuthJob.this, paramAnonymousComponentName);
          return;
        }
        UTCError.trackFailure(DelegatedAuthJob.TAG, true, UTCTelemetry.CallBackSources.Ticket, new Exception(paramAnonymousComponentName));
        DelegatedAuthJob.this.callbacks.onFailure(DelegatedAuthJob.this, new Exception(paramAnonymousComponentName));
        return;
        paramAnonymousComponentName = "RESULT_UNKNOWN_PACKAGE";
        continue;
        label195:
        paramAnonymousComponentName = paramAnonymousComponentName.getTicket();
        DelegatedAuthJob.this.callbacks.onTicketAcquired(DelegatedAuthJob.this, paramAnonymousComponentName);
        return;
        switch (i)
        {
        case 3: 
        default: 
          paramAnonymousComponentName = "UNKNOWN_ERROR";
          break;
        case 4: 
          paramAnonymousComponentName = "RESULT_INVALID_PACKAGE";
          break;
        case 5: 
          paramAnonymousComponentName = "RESULT_INVALID_SIGNATURE";
          break;
        case 6: 
          label218:
          paramAnonymousComponentName = "RESULT_INVALID_APPURI";
        }
      }
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      Log.i(DelegatedAuthJob.TAG, "Service disconnected");
    }
  };
  private final Context context;
  IDelegateKeyService keyService;
  private final String packageName;
  
  public DelegatedAuthJob(Context paramContext, Callbacks paramCallbacks)
  {
    this.context = paramContext;
    this.callbacks = paramCallbacks;
    this.packageName = paramContext.getPackageName();
  }
  
  public static void clearXboxAppLaunchIntent()
  {
    launchIntent = null;
  }
  
  public static Intent getXboxAppLaunchIntent()
  {
    return launchIntent;
  }
  
  void launchXboxApp()
  {
    Log.i(TAG, "check service exists");
    if (XboxAppLinker.isServiceInstalled("com.microsoft.xboxone.smartglass.beta", this.context, "com.microsoft.xbox.authenticate.DelegateKeyService"))
    {
      localObject = new UTCAdditionalInfoModel();
      ((UTCAdditionalInfoModel)localObject).addValue("launchType", "BETA");
      UTCPageAction.track("SignIn - DelegateRPSTicket", "DelegatedAuthJob", (UTCAdditionalInfoModel)localObject);
      localObject = new Intent();
      ((Intent)localObject).setComponent(new ComponentName("com.microsoft.xboxone.smartglass.beta", "com.microsoft.xbox.authenticate.DelegateKeyService"));
      this.context.bindService((Intent)localObject, this.connection, 1);
      return;
    }
    if (XboxAppLinker.isServiceInstalled("com.microsoft.xboxone.smartglass", this.context, "com.microsoft.xbox.authenticate.DelegateKeyService"))
    {
      localObject = new UTCAdditionalInfoModel();
      ((UTCAdditionalInfoModel)localObject).addValue("launchType", "RETAIL");
      UTCPageAction.track("SignIn - DelegateRPSTicket", "DelegatedAuthJob", (UTCAdditionalInfoModel)localObject);
      localObject = new Intent();
      ((Intent)localObject).setComponent(new ComponentName("com.microsoft.xboxone.smartglass", "com.microsoft.xbox.authenticate.DelegateKeyService"));
      this.context.bindService((Intent)localObject, this.connection, 1);
      return;
    }
    Object localObject = new UTCAdditionalInfoModel();
    ((UTCAdditionalInfoModel)localObject).addValue("launchType", "STORE");
    UTCPageAction.track("SignIn - DelegateRPSTicket", "DelegatedAuthJob", (UTCAdditionalInfoModel)localObject);
    launchIntent = XboxAppLinker.getXboxAppInOculusMarketIntent(this.context);
    this.callbacks.onUiNeeded(this);
  }
  
  public DelegatedAuthJob start()
  {
    launchXboxApp();
    return this;
  }
  
  public static abstract interface Callbacks
  {
    public abstract void onFailure(DelegatedAuthJob paramDelegatedAuthJob, Exception paramException);
    
    public abstract void onTicketAcquired(DelegatedAuthJob paramDelegatedAuthJob, String paramString);
    
    public abstract void onUiNeeded(DelegatedAuthJob paramDelegatedAuthJob);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\jobs\DelegatedAuthJob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */