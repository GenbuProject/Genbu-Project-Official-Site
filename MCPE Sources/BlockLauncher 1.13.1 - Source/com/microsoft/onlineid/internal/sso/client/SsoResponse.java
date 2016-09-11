package com.microsoft.onlineid.internal.sso.client;

import android.app.PendingIntent;
import junit.framework.Assert;

public class SsoResponse<T>
{
  private T _data;
  private PendingIntent _pendingIntent;
  
  public T getData()
  {
    return (T)this._data;
  }
  
  public PendingIntent getPendingIntent()
  {
    return this._pendingIntent;
  }
  
  public boolean hasData()
  {
    return this._pendingIntent == null;
  }
  
  public boolean hasPendingIntent()
  {
    return this._pendingIntent != null;
  }
  
  public SsoResponse<T> setData(T paramT)
  {
    this._data = paramT;
    Assert.assertNull(this._pendingIntent);
    return this;
  }
  
  public SsoResponse<T> setPendingIntent(PendingIntent paramPendingIntent)
  {
    this._pendingIntent = paramPendingIntent;
    Assert.assertNull(this._data);
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\internal\sso\client\SsoResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */