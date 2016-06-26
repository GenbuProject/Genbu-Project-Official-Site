package com.google.android.gms.nearby.messages;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import java.util.ArrayList;
import java.util.List;

public class Strategy
  implements SafeParcelable
{
  public static final Strategy BLE_ONLY = new Builder().zzjg(2).setTtlSeconds(Integer.MAX_VALUE).build();
  public static final Parcelable.Creator<Strategy> CREATOR = new zzd();
  public static final Strategy DEFAULT = new Builder().build();
  public static final int DISCOVERY_MODE_BROADCAST = 1;
  public static final int DISCOVERY_MODE_DEFAULT = 3;
  public static final int DISCOVERY_MODE_SCAN = 2;
  public static final int DISTANCE_TYPE_DEFAULT = 0;
  public static final int DISTANCE_TYPE_EARSHOT = 1;
  public static final int TTL_SECONDS_DEFAULT = 300;
  public static final int TTL_SECONDS_INFINITE = Integer.MAX_VALUE;
  public static final int TTL_SECONDS_MAX = 86400;
  @Deprecated
  public static final Strategy zzbbK = BLE_ONLY;
  final int mVersionCode;
  @Deprecated
  final int zzbbL;
  final int zzbbM;
  final int zzbbN;
  @Deprecated
  final boolean zzbbO;
  final int zzbbP;
  final int zzbbQ;
  
  Strategy(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean, int paramInt5, int paramInt6)
  {
    this.mVersionCode = paramInt1;
    this.zzbbL = paramInt2;
    if (paramInt2 == 0) {
      this.zzbbQ = paramInt6;
    }
    for (;;)
    {
      this.zzbbN = paramInt4;
      this.zzbbO = paramBoolean;
      if (!paramBoolean) {
        break;
      }
      this.zzbbP = 2;
      this.zzbbM = Integer.MAX_VALUE;
      return;
      switch (paramInt2)
      {
      default: 
        this.zzbbQ = 3;
        break;
      case 2: 
        this.zzbbQ = 1;
        break;
      case 3: 
        this.zzbbQ = 2;
      }
    }
    this.zzbbM = paramInt3;
    switch (paramInt5)
    {
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    default: 
      this.zzbbP = paramInt5;
      return;
    }
    this.zzbbP = -1;
  }
  
  private static String zzjd(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "UNKNOWN:" + paramInt;
    case 0: 
      return "DEFAULT";
    }
    return "EARSHOT";
  }
  
  private static String zzje(int paramInt)
  {
    if (paramInt == -1) {
      return "DEFAULT";
    }
    ArrayList localArrayList = new ArrayList();
    if ((paramInt & 0x4) > 0) {
      localArrayList.add("AUDIO");
    }
    if ((paramInt & 0x2) > 0) {
      localArrayList.add("BLE");
    }
    if (localArrayList.isEmpty()) {
      return "UNKNOWN:" + paramInt;
    }
    return localArrayList.toString();
  }
  
  private static String zzjf(int paramInt)
  {
    if (paramInt == 3) {
      return "DEFAULT";
    }
    ArrayList localArrayList = new ArrayList();
    if ((paramInt & 0x1) > 0) {
      localArrayList.add("BROADCAST");
    }
    if ((paramInt & 0x2) > 0) {
      localArrayList.add("SCAN");
    }
    if (localArrayList.isEmpty()) {
      return "UNKNOWN:" + paramInt;
    }
    return localArrayList.toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof Strategy)) {
        return false;
      }
      paramObject = (Strategy)paramObject;
    } while ((this.mVersionCode == ((Strategy)paramObject).mVersionCode) && (this.zzbbQ == ((Strategy)paramObject).zzbbQ) && (this.zzbbM == ((Strategy)paramObject).zzbbM) && (this.zzbbN == ((Strategy)paramObject).zzbbN) && (this.zzbbP == ((Strategy)paramObject).zzbbP));
    return false;
  }
  
  public int hashCode()
  {
    return (((this.mVersionCode * 31 + this.zzbbQ) * 31 + this.zzbbM) * 31 + this.zzbbN) * 31 + this.zzbbP;
  }
  
  public String toString()
  {
    return "Strategy{ttlSeconds=" + this.zzbbM + ", distanceType=" + zzjd(this.zzbbN) + ", discoveryMedium=" + zzje(this.zzbbP) + ", discoveryMode=" + zzjf(this.zzbbQ) + '}';
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzd.zza(this, paramParcel, paramInt);
  }
  
  public int zzEr()
  {
    return this.zzbbP;
  }
  
  public int zzEs()
  {
    return this.zzbbQ;
  }
  
  public static class Builder
  {
    private int zzbbR = 3;
    private int zzbbS = 300;
    private int zzbbT = 0;
    private int zzbbU = -1;
    
    public Strategy build()
    {
      if ((this.zzbbU == 2) && (this.zzbbT == 1)) {
        throw new IllegalStateException("Cannot set EARSHOT with BLE only mode.");
      }
      return new Strategy(2, 0, this.zzbbS, this.zzbbT, false, this.zzbbU, this.zzbbR);
    }
    
    public Builder setDiscoveryMode(int paramInt)
    {
      this.zzbbR = paramInt;
      return this;
    }
    
    public Builder setDistanceType(int paramInt)
    {
      this.zzbbT = paramInt;
      return this;
    }
    
    public Builder setTtlSeconds(int paramInt)
    {
      if ((paramInt == Integer.MAX_VALUE) || ((paramInt > 0) && (paramInt <= 86400))) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "mTtlSeconds(%d) must either be TTL_SECONDS_INFINITE, or it must be between 1 and TTL_SECONDS_MAX(%d) inclusive", new Object[] { Integer.valueOf(paramInt), Integer.valueOf(86400) });
        this.zzbbS = paramInt;
        return this;
      }
    }
    
    public Builder zzjg(int paramInt)
    {
      this.zzbbU = paramInt;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\messages\Strategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */