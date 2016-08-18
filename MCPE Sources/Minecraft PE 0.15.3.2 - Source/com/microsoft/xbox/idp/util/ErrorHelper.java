package com.microsoft.xbox.idp.util;

import android.app.Activity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.microsoft.xbox.idp.interop.Interop.ErrorType;
import com.microsoft.xbox.idp.ui.ErrorActivity;
import com.microsoft.xbox.idp.ui.ErrorActivity.ErrorScreen;

public final class ErrorHelper
  implements Parcelable
{
  public static final Parcelable.Creator<ErrorHelper> CREATOR = new Parcelable.Creator()
  {
    public ErrorHelper createFromParcel(Parcel paramAnonymousParcel)
    {
      return new ErrorHelper(paramAnonymousParcel);
    }
    
    public ErrorHelper[] newArray(int paramAnonymousInt)
    {
      return new ErrorHelper[paramAnonymousInt];
    }
  };
  public static final String KEY_RESULT_KEY = "KEY_RESULT_KEY";
  public static final int LOADER_NONE = -1;
  public static final int RC_ERROR_SCREEN = 63;
  private static final String TAG = ErrorHelper.class.getSimpleName();
  private ActivityContext activityContext;
  public Bundle loaderArgs;
  public int loaderId;
  
  public ErrorHelper()
  {
    this.loaderId = -1;
    this.loaderArgs = null;
  }
  
  protected ErrorHelper(Parcel paramParcel)
  {
    this.loaderId = paramParcel.readInt();
    this.loaderArgs = paramParcel.readBundle();
  }
  
  private boolean isConnected()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.activityContext.getActivity().getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }
  
  public void deleteLoader()
  {
    if (this.loaderId != -1)
    {
      this.activityContext.getLoaderManager().destroyLoader(this.loaderId);
      if (this.loaderArgs != null) {
        break label67;
      }
    }
    label67:
    for (Object localObject = null;; localObject = this.loaderArgs.get("KEY_RESULT_KEY"))
    {
      if (localObject != null) {
        this.activityContext.getLoaderInfo(this.loaderId).clearCache(localObject);
      }
      this.loaderId = -1;
      this.loaderArgs = null;
      return;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public ActivityResult getActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    boolean bool = true;
    if (paramInt1 != 63) {
      return null;
    }
    if (paramInt2 == 1) {}
    for (;;)
    {
      return new ActivityResult(bool);
      bool = false;
    }
  }
  
  public <D> boolean initLoader(int paramInt, Bundle paramBundle)
  {
    return initLoader(paramInt, paramBundle, true);
  }
  
  public <D> boolean initLoader(int paramInt, Bundle paramBundle, boolean paramBoolean)
  {
    Log.d(TAG, "initLoader");
    if (paramInt != -1)
    {
      this.loaderId = paramInt;
      this.loaderArgs = paramBundle;
      LoaderManager localLoaderManager = this.activityContext.getLoaderManager();
      LoaderInfo localLoaderInfo = this.activityContext.getLoaderInfo(this.loaderId);
      Object localObject;
      if (this.loaderArgs == null)
      {
        localObject = null;
        if (localObject != null) {
          break label153;
        }
      }
      label153:
      for (boolean bool = false;; bool = localLoaderInfo.hasCachedData(localObject))
      {
        if ((!bool) && (localLoaderManager.getLoader(paramInt) == null) && (paramBoolean) && (!isConnected())) {
          break label167;
        }
        Log.d(TAG, "initializing loader #" + this.loaderId);
        localLoaderManager.initLoader(paramInt, paramBundle, localLoaderInfo.getLoaderCallbacks());
        return true;
        localObject = this.loaderArgs.get("KEY_RESULT_KEY");
        break;
      }
      label167:
      Log.e(TAG, "Starting error activity: OFFLINE");
      startErrorActivity(ErrorActivity.ErrorScreen.OFFLINE);
      return false;
    }
    Log.e(TAG, "LOADER_NONE");
    return false;
  }
  
  public <D> boolean restartLoader()
  {
    boolean bool = false;
    if (this.loaderId != -1)
    {
      if (isConnected())
      {
        this.activityContext.getLoaderManager().restartLoader(this.loaderId, this.loaderArgs, this.activityContext.getLoaderInfo(this.loaderId).getLoaderCallbacks());
        bool = true;
      }
    }
    else {
      return bool;
    }
    startErrorActivity(ErrorActivity.ErrorScreen.OFFLINE);
    return false;
  }
  
  public <D> boolean restartLoader(int paramInt, Bundle paramBundle)
  {
    boolean bool = false;
    if (paramInt != -1)
    {
      this.loaderId = paramInt;
      this.loaderArgs = paramBundle;
      if (isConnected())
      {
        this.activityContext.getLoaderManager().restartLoader(this.loaderId, this.loaderArgs, this.activityContext.getLoaderInfo(this.loaderId).getLoaderCallbacks());
        bool = true;
      }
    }
    else
    {
      return bool;
    }
    startErrorActivity(ErrorActivity.ErrorScreen.OFFLINE);
    return false;
  }
  
  public void setActivityContext(ActivityContext paramActivityContext)
  {
    this.activityContext = paramActivityContext;
  }
  
  public void startErrorActivity(ErrorActivity.ErrorScreen paramErrorScreen)
  {
    Intent localIntent = new Intent(this.activityContext.getActivity(), ErrorActivity.class);
    localIntent.putExtra("ARG_ERROR_TYPE", paramErrorScreen.type.getId());
    this.activityContext.startActivityForResult(localIntent, 63);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.loaderId);
    paramParcel.writeBundle(this.loaderArgs);
  }
  
  public static abstract interface ActivityContext
  {
    public abstract Activity getActivity();
    
    public abstract ErrorHelper.LoaderInfo getLoaderInfo(int paramInt);
    
    public abstract LoaderManager getLoaderManager();
    
    public abstract void startActivityForResult(Intent paramIntent, int paramInt);
  }
  
  public static class ActivityResult
  {
    private final boolean tryAgain;
    
    public ActivityResult(boolean paramBoolean)
    {
      this.tryAgain = paramBoolean;
    }
    
    public boolean isTryAgain()
    {
      return this.tryAgain;
    }
  }
  
  public static abstract interface LoaderInfo
  {
    public abstract void clearCache(Object paramObject);
    
    public abstract LoaderManager.LoaderCallbacks<?> getLoaderCallbacks();
    
    public abstract boolean hasCachedData(Object paramObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\util\ErrorHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */