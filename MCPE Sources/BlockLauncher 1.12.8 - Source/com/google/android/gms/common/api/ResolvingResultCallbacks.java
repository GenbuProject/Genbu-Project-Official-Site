package com.google.android.gms.common.api;

import android.app.Activity;
import android.content.IntentSender.SendIntentException;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.internal.zzx;

public abstract class ResolvingResultCallbacks<R extends Result>
  extends ResultCallbacks<R>
{
  private final Activity mActivity;
  private final int zzagz;
  
  protected ResolvingResultCallbacks(@NonNull Activity paramActivity, int paramInt)
  {
    this.mActivity = ((Activity)zzx.zzb(paramActivity, "Activity must not be null"));
    this.zzagz = paramInt;
  }
  
  public final void onFailure(@NonNull Status paramStatus)
  {
    if (paramStatus.hasResolution()) {
      try
      {
        paramStatus.startResolutionForResult(this.mActivity, this.zzagz);
        return;
      }
      catch (IntentSender.SendIntentException paramStatus)
      {
        Log.e("ResolvingResultCallback", "Failed to start resolution", paramStatus);
        onUnresolvableFailure(new Status(8));
        return;
      }
    }
    onUnresolvableFailure(paramStatus);
  }
  
  public abstract void onSuccess(@NonNull R paramR);
  
  public abstract void onUnresolvableFailure(@NonNull Status paramStatus);
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\api\ResolvingResultCallbacks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */