package com.google.android.gms.fitness.data;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.fitness.request.OnDataPointListener;
import java.util.HashMap;
import java.util.Map;

public class zzl
  extends zzk.zza
{
  private final OnDataPointListener zzaxb;
  
  private zzl(OnDataPointListener paramOnDataPointListener)
  {
    this.zzaxb = ((OnDataPointListener)zzx.zzz(paramOnDataPointListener));
  }
  
  public void zzc(DataPoint paramDataPoint)
    throws RemoteException
  {
    this.zzaxb.onDataPoint(paramDataPoint);
  }
  
  public static class zza
  {
    private static final zza zzaxc = new zza();
    private final Map<OnDataPointListener, zzl> zzaxd = new HashMap();
    
    public static zza zzuu()
    {
      return zzaxc;
    }
    
    public zzl zza(OnDataPointListener paramOnDataPointListener)
    {
      synchronized (this.zzaxd)
      {
        zzl localzzl2 = (zzl)this.zzaxd.get(paramOnDataPointListener);
        zzl localzzl1 = localzzl2;
        if (localzzl2 == null)
        {
          localzzl1 = new zzl(paramOnDataPointListener, null);
          this.zzaxd.put(paramOnDataPointListener, localzzl1);
        }
        return localzzl1;
      }
    }
    
    public zzl zzb(OnDataPointListener paramOnDataPointListener)
    {
      synchronized (this.zzaxd)
      {
        paramOnDataPointListener = (zzl)this.zzaxd.get(paramOnDataPointListener);
        return paramOnDataPointListener;
      }
    }
    
    public zzl zzc(OnDataPointListener paramOnDataPointListener)
    {
      synchronized (this.zzaxd)
      {
        zzl localzzl = (zzl)this.zzaxd.remove(paramOnDataPointListener);
        if (localzzl != null) {
          return localzzl;
        }
        paramOnDataPointListener = new zzl(paramOnDataPointListener, null);
        return paramOnDataPointListener;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\data\zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */