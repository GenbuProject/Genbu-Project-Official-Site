package com.microsoft.onlineid;

import android.app.PendingIntent;
import android.os.Bundle;
import com.microsoft.onlineid.exception.AuthenticationException;
import com.microsoft.onlineid.internal.IFailureCallback;
import com.microsoft.onlineid.internal.IUserInteractionCallback;

public abstract interface ITicketCallback
  extends IFailureCallback, IUserInteractionCallback
{
  public abstract void onFailure(AuthenticationException paramAuthenticationException, Bundle paramBundle);
  
  public abstract void onTicketAcquired(Ticket paramTicket, UserAccount paramUserAccount, Bundle paramBundle);
  
  public abstract void onUINeeded(PendingIntent paramPendingIntent, Bundle paramBundle);
  
  public abstract void onUserCancel(Bundle paramBundle);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\ITicketCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */