package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.zzos;
import com.google.android.gms.internal.zzos.zza;
import java.util.List;

public class ReadRawRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<ReadRawRequest> CREATOR = new zzt();
  private final int mVersionCode;
  private final boolean zzaAP;
  private final boolean zzaAQ;
  private final zzos zzaBe;
  private final List<DataSourceQueryParams> zzaBf;
  
  ReadRawRequest(int paramInt, IBinder paramIBinder, List<DataSourceQueryParams> paramList, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mVersionCode = paramInt;
    this.zzaBe = zzos.zza.zzbN(paramIBinder);
    this.zzaBf = paramList;
    this.zzaAP = paramBoolean1;
    this.zzaAQ = paramBoolean2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public IBinder getCallbackBinder()
  {
    if (this.zzaBe != null) {
      return this.zzaBe.asBinder();
    }
    return null;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzt.zza(this, paramParcel, paramInt);
  }
  
  public boolean zzuP()
  {
    return this.zzaAQ;
  }
  
  public boolean zzuQ()
  {
    return this.zzaAP;
  }
  
  public List<DataSourceQueryParams> zzuW()
  {
    return this.zzaBf;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\request\ReadRawRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */