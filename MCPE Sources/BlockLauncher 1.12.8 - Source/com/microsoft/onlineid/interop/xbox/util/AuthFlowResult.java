package com.microsoft.onlineid.interop.xbox.util;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class AuthFlowResult
  implements Parcelable
{
  public static final Parcelable.Creator<AuthFlowResult> CREATOR = new Parcelable.Creator()
  {
    public AuthFlowResult createFromParcel(Parcel paramAnonymousParcel)
    {
      return new AuthFlowResult(paramAnonymousParcel);
    }
    
    public AuthFlowResult[] newArray(int paramAnonymousInt)
    {
      return new AuthFlowResult[paramAnonymousInt];
    }
  };
  private final boolean deleteOnFinalize;
  private final long id;
  
  public AuthFlowResult(long paramLong)
  {
    this(paramLong, false);
  }
  
  public AuthFlowResult(long paramLong, boolean paramBoolean)
  {
    this.id = paramLong;
    this.deleteOnFinalize = paramBoolean;
  }
  
  protected AuthFlowResult(Parcel paramParcel)
  {
    this.id = paramParcel.readLong();
    if (paramParcel.readByte() != 0) {}
    for (boolean bool = true;; bool = false)
    {
      this.deleteOnFinalize = bool;
      return;
    }
  }
  
  private static native void delete(long paramLong);
  
  private static native String getAgeGroup(long paramLong);
  
  private static native String getGamerTag(long paramLong);
  
  private static native String getPrivileges(long paramLong);
  
  private static native String getRpsTicket(long paramLong);
  
  private static native String getUserId(long paramLong);
  
  private static native String getXToken(long paramLong);
  
  public int describeContents()
  {
    return 0;
  }
  
  protected void finalize()
    throws Throwable
  {
    if (this.deleteOnFinalize) {
      delete(this.id);
    }
    super.finalize();
  }
  
  public String getAgeGroup()
  {
    return getAgeGroup(this.id);
  }
  
  public String getGamerTag()
  {
    return getGamerTag(this.id);
  }
  
  public String getPrivileges()
  {
    return getPrivileges(this.id);
  }
  
  public String getRpsTicket()
  {
    return getRpsTicket(this.id);
  }
  
  public String getUserId()
  {
    return getUserId(this.id);
  }
  
  public String getXToken()
  {
    return getXToken(this.id);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeLong(this.id);
    if (this.deleteOnFinalize) {}
    for (paramInt = 1;; paramInt = 0)
    {
      paramParcel.writeByte((byte)paramInt);
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\interop\xbox\util\AuthFlowResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */