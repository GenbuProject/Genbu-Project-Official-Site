package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzmn;
import com.google.android.gms.internal.zzow;
import com.google.android.gms.internal.zzow.zza;
import java.util.Collections;
import java.util.List;

public class StartBleScanRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<StartBleScanRequest> CREATOR = new zzad();
  private final int mVersionCode;
  private final zzow zzaAD;
  private final zzq zzaBA;
  private final int zzaBB;
  private final List<DataType> zzawe;
  
  StartBleScanRequest(int paramInt1, List<DataType> paramList, IBinder paramIBinder1, int paramInt2, IBinder paramIBinder2)
  {
    this.mVersionCode = paramInt1;
    this.zzawe = paramList;
    this.zzaBA = zzq.zza.zzbU(paramIBinder1);
    this.zzaBB = paramInt2;
    this.zzaAD = zzow.zza.zzbR(paramIBinder2);
  }
  
  private StartBleScanRequest(Builder paramBuilder)
  {
    this(zzmn.zzb(Builder.zza(paramBuilder)), Builder.zzb(paramBuilder), Builder.zzc(paramBuilder), null);
  }
  
  public StartBleScanRequest(StartBleScanRequest paramStartBleScanRequest, zzow paramzzow)
  {
    this(paramStartBleScanRequest.zzawe, paramStartBleScanRequest.zzaBA, paramStartBleScanRequest.zzaBB, paramzzow);
  }
  
  public StartBleScanRequest(List<DataType> paramList, zzq paramzzq, int paramInt, zzow paramzzow)
  {
    this.mVersionCode = 4;
    this.zzawe = paramList;
    this.zzaBA = paramzzq;
    this.zzaBB = paramInt;
    this.zzaAD = paramzzow;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public IBinder getCallbackBinder()
  {
    if (this.zzaAD == null) {
      return null;
    }
    return this.zzaAD.asBinder();
  }
  
  public List<DataType> getDataTypes()
  {
    return Collections.unmodifiableList(this.zzawe);
  }
  
  public int getTimeoutSecs()
  {
    return this.zzaBB;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("dataTypes", this.zzawe).zzg("timeoutSecs", Integer.valueOf(this.zzaBB)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzad.zza(this, paramParcel, paramInt);
  }
  
  public IBinder zzvg()
  {
    return this.zzaBA.asBinder();
  }
  
  public static class Builder
  {
    private DataType[] zzaAY = new DataType[0];
    private zzq zzaBA;
    private int zzaBB = 10;
    
    public StartBleScanRequest build()
    {
      if (this.zzaBA != null) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zza(bool, "Must set BleScanCallback");
        return new StartBleScanRequest(this, null);
      }
    }
    
    public Builder setBleScanCallback(BleScanCallback paramBleScanCallback)
    {
      zza(zza.zza.zzuJ().zza(paramBleScanCallback));
      return this;
    }
    
    public Builder setDataTypes(DataType... paramVarArgs)
    {
      this.zzaAY = paramVarArgs;
      return this;
    }
    
    public Builder setTimeoutSecs(int paramInt)
    {
      boolean bool2 = true;
      if (paramInt > 0)
      {
        bool1 = true;
        zzx.zzb(bool1, "Stop time must be greater than zero");
        if (paramInt > 60) {
          break label40;
        }
      }
      label40:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        zzx.zzb(bool1, "Stop time must be less than 1 minute");
        this.zzaBB = paramInt;
        return this;
        bool1 = false;
        break;
      }
    }
    
    public Builder zza(zzq paramzzq)
    {
      this.zzaBA = paramzzq;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\request\StartBleScanRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */