package com.microsoft.onlineid.internal;

import android.app.PendingIntent;
import android.os.Bundle;

public abstract interface IUserInteractionCallback
{
  public abstract void onUINeeded(PendingIntent paramPendingIntent, Bundle paramBundle);
  
  public abstract void onUserCancel(Bundle paramBundle);
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\IUserInteractionCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */