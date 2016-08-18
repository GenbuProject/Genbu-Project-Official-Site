package com.microsoft.onlineid.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.microsoft.onlineid.exception.InternalException;

public abstract class ApiRequestResultReceiver
  extends ResultReceiver
{
  public ApiRequestResultReceiver(Handler paramHandler)
  {
    super(paramHandler);
  }
  
  protected abstract void onFailure(Exception paramException);
  
  protected void onReceiveResult(int paramInt, Bundle paramBundle)
  {
    paramBundle = new ApiResult(paramBundle);
    switch (paramInt)
    {
    default: 
      onUnknownResult(paramBundle, paramInt);
      return;
    case -1: 
      onSuccess(paramBundle);
      return;
    case 0: 
      onUserCancel();
      return;
    case 1: 
      onFailure(paramBundle.getException());
      return;
    }
    onUINeeded(paramBundle.getUINeededIntent());
  }
  
  protected abstract void onSuccess(ApiResult paramApiResult);
  
  protected abstract void onUINeeded(PendingIntent paramPendingIntent);
  
  protected void onUnknownResult(ApiResult paramApiResult, int paramInt)
  {
    Assertion.check(false, "Unknown result code: " + paramInt);
    onFailure(new InternalException("Unknown result code: " + paramInt));
  }
  
  protected abstract void onUserCancel();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\ApiRequestResultReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */