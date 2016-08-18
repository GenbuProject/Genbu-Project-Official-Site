package com.microsoft.onlineid.internal;

import android.app.PendingIntent;
import android.content.Intent;
import com.microsoft.onlineid.exception.InternalException;

public abstract class ActivityResultHandler
{
  public void onActivityResult(int paramInt, Intent paramIntent)
  {
    if (paramIntent != null) {}
    for (paramIntent = paramIntent.getExtras();; paramIntent = null)
    {
      paramIntent = new ApiResult(paramIntent);
      switch (paramInt)
      {
      default: 
        onUnknownResult(paramIntent, paramInt);
        return;
      }
    }
    onSuccess(paramIntent);
    return;
    onUserCancel();
    return;
    onFailure(paramIntent.getException());
    return;
    onUINeeded(paramIntent.getUINeededIntent());
  }
  
  protected abstract void onFailure(Exception paramException);
  
  protected abstract void onSuccess(ApiResult paramApiResult);
  
  protected abstract void onUINeeded(PendingIntent paramPendingIntent);
  
  protected void onUnknownResult(ApiResult paramApiResult, int paramInt)
  {
    Assertion.check(false, "Unknown result code: " + paramInt);
    onFailure(new InternalException("Unknown result code: " + paramInt));
  }
  
  protected abstract void onUserCancel();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\ActivityResultHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */