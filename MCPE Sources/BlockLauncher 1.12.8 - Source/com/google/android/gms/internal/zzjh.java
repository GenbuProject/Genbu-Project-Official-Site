package com.google.android.gms.internal;

import android.os.Handler;
import com.google.android.gms.ads.internal.util.client.zza;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzhb
class zzjh
{
  private final Object zzNm = new Object();
  private final List<Runnable> zzNn = new ArrayList();
  private final List<Runnable> zzNo = new ArrayList();
  private boolean zzNp = false;
  
  private void zzd(Runnable paramRunnable)
  {
    zziq.zza(paramRunnable);
  }
  
  private void zze(Runnable paramRunnable)
  {
    zza.zzMS.post(paramRunnable);
  }
  
  public void zzb(Runnable paramRunnable)
  {
    synchronized (this.zzNm)
    {
      if (this.zzNp)
      {
        zzd(paramRunnable);
        return;
      }
      this.zzNn.add(paramRunnable);
    }
  }
  
  public void zzc(Runnable paramRunnable)
  {
    synchronized (this.zzNm)
    {
      if (this.zzNp)
      {
        zze(paramRunnable);
        return;
      }
      this.zzNo.add(paramRunnable);
    }
  }
  
  public void zzhK()
  {
    synchronized (this.zzNm)
    {
      if (this.zzNp) {
        return;
      }
      Iterator localIterator1 = this.zzNn.iterator();
      if (localIterator1.hasNext()) {
        zzd((Runnable)localIterator1.next());
      }
    }
    Iterator localIterator2 = this.zzNo.iterator();
    while (localIterator2.hasNext()) {
      zze((Runnable)localIterator2.next());
    }
    this.zzNn.clear();
    this.zzNo.clear();
    this.zzNp = true;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzjh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */