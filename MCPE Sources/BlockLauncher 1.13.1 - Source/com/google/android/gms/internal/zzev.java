package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzhb
public class zzev
  implements zzem
{
  private final Context mContext;
  private zzer zzCD;
  private final zzeo zzCf;
  private final AdRequestInfoParcel zzCu;
  private final long zzCv;
  private final long zzCw;
  private boolean zzCy = false;
  private final Object zzpV = new Object();
  private final zzcb zzpe;
  private final zzex zzpn;
  private final boolean zzsA;
  private final boolean zzuS;
  
  public zzev(Context paramContext, AdRequestInfoParcel paramAdRequestInfoParcel, zzex paramzzex, zzeo paramzzeo, boolean paramBoolean1, boolean paramBoolean2, long paramLong1, long paramLong2, zzcb paramzzcb)
  {
    this.mContext = paramContext;
    this.zzCu = paramAdRequestInfoParcel;
    this.zzpn = paramzzex;
    this.zzCf = paramzzeo;
    this.zzsA = paramBoolean1;
    this.zzuS = paramBoolean2;
    this.zzCv = paramLong1;
    this.zzCw = paramLong2;
    this.zzpe = paramzzcb;
  }
  
  public void cancel()
  {
    synchronized (this.zzpV)
    {
      this.zzCy = true;
      if (this.zzCD != null) {
        this.zzCD.cancel();
      }
      return;
    }
  }
  
  public zzes zzc(List<zzen> arg1)
  {
    zzin.zzaI("Starting mediation.");
    Object localObject = new ArrayList();
    zzbz localzzbz1 = this.zzpe.zzdB();
    Iterator localIterator1 = ???.iterator();
    while (localIterator1.hasNext())
    {
      zzen localzzen = (zzen)localIterator1.next();
      zzin.zzaJ("Trying mediation network: " + localzzen.zzBA);
      Iterator localIterator2 = localzzen.zzBB.iterator();
      while (localIterator2.hasNext())
      {
        String str = (String)localIterator2.next();
        zzbz localzzbz2 = this.zzpe.zzdB();
        synchronized (this.zzpV)
        {
          if (this.zzCy)
          {
            localObject = new zzes(-1);
            return (zzes)localObject;
          }
          this.zzCD = new zzer(this.mContext, str, this.zzpn, this.zzCf, localzzen, this.zzCu.zzHt, this.zzCu.zzrp, this.zzCu.zzrl, this.zzsA, this.zzuS, this.zzCu.zzrD, this.zzCu.zzrH);
          ??? = this.zzCD.zza(this.zzCv, this.zzCw);
          if (???.zzCo == 0)
          {
            zzin.zzaI("Adapter succeeded.");
            this.zzpe.zzc("mediation_network_succeed", str);
            if (!((List)localObject).isEmpty()) {
              this.zzpe.zzc("mediation_networks_fail", TextUtils.join(",", (Iterable)localObject));
            }
            this.zzpe.zza(localzzbz2, new String[] { "mls" });
            this.zzpe.zza(localzzbz1, new String[] { "ttm" });
            return (zzes)???;
          }
        }
        localIterable.add(str);
        this.zzpe.zza(localzzbz2, new String[] { "mlf" });
        if (???.zzCq != null) {
          zzir.zzMc.post(new Runnable()
          {
            public void run()
            {
              try
              {
                paramList.zzCq.destroy();
                return;
              }
              catch (RemoteException localRemoteException)
              {
                zzin.zzd("Could not destroy mediation adapter.", localRemoteException);
              }
            }
          });
        }
      }
    }
    if (!localIterable.isEmpty()) {
      this.zzpe.zzc("mediation_networks_fail", TextUtils.join(",", localIterable));
    }
    return new zzes(1);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzev.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */