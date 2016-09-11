package com.microsoft.onlineid.sts;

import android.content.Context;
import com.microsoft.onlineid.internal.storage.TypedStorage;
import java.util.Date;

public class ClockSkewManager
{
  private final TypedStorage _storage;
  
  public ClockSkewManager(Context paramContext)
  {
    this._storage = new TypedStorage(paramContext);
  }
  
  protected ClockSkewManager(TypedStorage paramTypedStorage)
  {
    this._storage = paramTypedStorage;
  }
  
  private void setSkewMilliseconds(long paramLong)
  {
    this._storage.writeClockSkew(paramLong);
  }
  
  protected Date getCurrentClientTime()
  {
    return new Date();
  }
  
  public Date getCurrentServerTime()
  {
    return toServerTime(getCurrentClientTime());
  }
  
  public long getSkewMilliseconds()
  {
    return this._storage.readClockSkew();
  }
  
  public void onTimestampReceived(long paramLong)
  {
    setSkewMilliseconds(getCurrentClientTime().getTime() - paramLong);
  }
  
  public Date toClientTime(Date paramDate)
  {
    return new Date(paramDate.getTime() + getSkewMilliseconds());
  }
  
  public Date toServerTime(Date paramDate)
  {
    return new Date(paramDate.getTime() - getSkewMilliseconds());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\sts\ClockSkewManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */