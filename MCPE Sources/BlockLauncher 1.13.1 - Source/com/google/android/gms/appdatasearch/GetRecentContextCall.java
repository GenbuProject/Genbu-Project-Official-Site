package com.google.android.gms.appdatasearch;

import android.accounts.Account;
import android.os.Parcel;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class GetRecentContextCall
{
  public static class Request
    implements SafeParcelable
  {
    public static final zzf CREATOR = new zzf();
    final int mVersionCode;
    public final Account zzTT;
    public final boolean zzTU;
    public final boolean zzTV;
    public final boolean zzTW;
    public final String zzTX;
    
    public Request()
    {
      this(null, false, false, false, null);
    }
    
    Request(int paramInt, Account paramAccount, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString)
    {
      this.mVersionCode = paramInt;
      this.zzTT = paramAccount;
      this.zzTU = paramBoolean1;
      this.zzTV = paramBoolean2;
      this.zzTW = paramBoolean3;
      this.zzTX = paramString;
    }
    
    public Request(Account paramAccount, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString)
    {
      this(1, paramAccount, paramBoolean1, paramBoolean2, paramBoolean3, paramString);
    }
    
    public int describeContents()
    {
      zzf localzzf = CREATOR;
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzf localzzf = CREATOR;
      zzf.zza(this, paramParcel, paramInt);
    }
  }
  
  public static class Response
    implements Result, SafeParcelable
  {
    public static final zzg CREATOR = new zzg();
    final int mVersionCode;
    public Status zzTY;
    public List<UsageInfo> zzTZ;
    @Deprecated
    public String[] zzUa;
    
    public Response()
    {
      this.mVersionCode = 1;
    }
    
    Response(int paramInt, Status paramStatus, List<UsageInfo> paramList, String[] paramArrayOfString)
    {
      this.mVersionCode = paramInt;
      this.zzTY = paramStatus;
      this.zzTZ = paramList;
      this.zzUa = paramArrayOfString;
    }
    
    public int describeContents()
    {
      zzg localzzg = CREATOR;
      return 0;
    }
    
    public Status getStatus()
    {
      return this.zzTY;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzg localzzg = CREATOR;
      zzg.zza(this, paramParcel, paramInt);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\appdatasearch\GetRecentContextCall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */