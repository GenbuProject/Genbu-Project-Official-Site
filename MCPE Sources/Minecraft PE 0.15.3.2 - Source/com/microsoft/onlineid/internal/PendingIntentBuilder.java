package com.microsoft.onlineid.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class PendingIntentBuilder
{
  private Context _context;
  private final Intent _intent;
  
  public PendingIntentBuilder(Intent paramIntent)
  {
    this._intent = paramIntent;
  }
  
  public PendingIntentBuilder(ApiRequest paramApiRequest)
  {
    this(paramApiRequest.asIntent());
    setContext(paramApiRequest.getContext());
  }
  
  public PendingIntent buildActivity()
  {
    return buildActivity(134217728);
  }
  
  public PendingIntent buildActivity(int paramInt)
  {
    Objects.verifyArgumentNotNull(this._context, "context");
    Objects.verifyArgumentNotNull(this._intent, "intent");
    return PendingIntent.getActivity(this._context, 0, this._intent, paramInt);
  }
  
  public PendingIntentBuilder setContext(Context paramContext)
  {
    this._context = paramContext;
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\PendingIntentBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */