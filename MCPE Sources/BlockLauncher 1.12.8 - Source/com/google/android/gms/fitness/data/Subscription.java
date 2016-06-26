package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.common.internal.zzx;

public class Subscription
  implements SafeParcelable
{
  public static final Parcelable.Creator<Subscription> CREATOR = new zzs();
  private final int mVersionCode;
  private final DataType zzavT;
  private final DataSource zzavU;
  private final long zzaxo;
  private final int zzaxp;
  
  Subscription(int paramInt1, DataSource paramDataSource, DataType paramDataType, long paramLong, int paramInt2)
  {
    this.mVersionCode = paramInt1;
    this.zzavU = paramDataSource;
    this.zzavT = paramDataType;
    this.zzaxo = paramLong;
    this.zzaxp = paramInt2;
  }
  
  private Subscription(zza paramzza)
  {
    this.mVersionCode = 1;
    this.zzavT = zza.zza(paramzza);
    this.zzavU = zza.zzb(paramzza);
    this.zzaxo = zza.zzc(paramzza);
    this.zzaxp = zza.zzd(paramzza);
  }
  
  private boolean zza(Subscription paramSubscription)
  {
    return (zzw.equal(this.zzavU, paramSubscription.zzavU)) && (zzw.equal(this.zzavT, paramSubscription.zzavT)) && (this.zzaxo == paramSubscription.zzaxo) && (this.zzaxp == paramSubscription.zzaxp);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof Subscription)) && (zza((Subscription)paramObject)));
  }
  
  public int getAccuracyMode()
  {
    return this.zzaxp;
  }
  
  public DataSource getDataSource()
  {
    return this.zzavU;
  }
  
  public DataType getDataType()
  {
    return this.zzavT;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzavU, this.zzavU, Long.valueOf(this.zzaxo), Integer.valueOf(this.zzaxp) });
  }
  
  public String toDebugString()
  {
    if (this.zzavU == null) {}
    for (String str = this.zzavT.getName();; str = this.zzavU.toDebugString()) {
      return String.format("Subscription{%s}", new Object[] { str });
    }
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("dataSource", this.zzavU).zzg("dataType", this.zzavT).zzg("samplingIntervalMicros", Long.valueOf(this.zzaxo)).zzg("accuracyMode", Integer.valueOf(this.zzaxp)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzs.zza(this, paramParcel, paramInt);
  }
  
  public long zzux()
  {
    return this.zzaxo;
  }
  
  public DataType zzuy()
  {
    if (this.zzavT == null) {
      return this.zzavU.getDataType();
    }
    return this.zzavT;
  }
  
  public static class zza
  {
    private DataType zzavT;
    private DataSource zzavU;
    private long zzaxo = -1L;
    private int zzaxp = 2;
    
    public zza zzb(DataSource paramDataSource)
    {
      this.zzavU = paramDataSource;
      return this;
    }
    
    public zza zzb(DataType paramDataType)
    {
      this.zzavT = paramDataType;
      return this;
    }
    
    public Subscription zzuz()
    {
      boolean bool2 = false;
      if ((this.zzavU != null) || (this.zzavT != null)) {}
      for (boolean bool1 = true;; bool1 = false)
      {
        zzx.zza(bool1, "Must call setDataSource() or setDataType()");
        if ((this.zzavT != null) && (this.zzavU != null))
        {
          bool1 = bool2;
          if (!this.zzavT.equals(this.zzavU.getDataType())) {}
        }
        else
        {
          bool1 = true;
        }
        zzx.zza(bool1, "Specified data type is incompatible with specified data source");
        return new Subscription(this, null);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\data\Subscription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */