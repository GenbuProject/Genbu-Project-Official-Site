package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import java.util.Iterator;
import java.util.Set;

public class EventParams
  implements SafeParcelable, Iterable<String>
{
  public static final zzj CREATOR = new zzj();
  public final int versionCode;
  private final Bundle zzaVS;
  
  EventParams(int paramInt, Bundle paramBundle)
  {
    this.versionCode = paramInt;
    this.zzaVS = paramBundle;
  }
  
  EventParams(Bundle paramBundle)
  {
    zzx.zzz(paramBundle);
    this.zzaVS = paramBundle;
    this.versionCode = 1;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  Object get(String paramString)
  {
    return this.zzaVS.get(paramString);
  }
  
  public Iterator<String> iterator()
  {
    new Iterator()
    {
      Iterator<String> zzaVT = EventParams.zza(EventParams.this).keySet().iterator();
      
      public boolean hasNext()
      {
        return this.zzaVT.hasNext();
      }
      
      public String next()
      {
        return (String)this.zzaVT.next();
      }
      
      public void remove()
      {
        throw new UnsupportedOperationException("Remove not supported");
      }
    };
  }
  
  public int size()
  {
    return this.zzaVS.size();
  }
  
  public String toString()
  {
    return this.zzaVS.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzj.zza(this, paramParcel, paramInt);
  }
  
  public Bundle zzCC()
  {
    return new Bundle(this.zzaVS);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\measurement\internal\EventParams.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */