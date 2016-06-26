package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzu;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class zzb
  extends AsyncTaskLoader<Void>
  implements zzu
{
  private Semaphore zzXp = new Semaphore(0);
  private Set<GoogleApiClient> zzXq;
  
  public zzb(Context paramContext, Set<GoogleApiClient> paramSet)
  {
    super(paramContext);
    this.zzXq = paramSet;
  }
  
  protected void onStartLoading()
  {
    this.zzXp.drainPermits();
    forceLoad();
  }
  
  public Void zzmZ()
  {
    Iterator localIterator = this.zzXq.iterator();
    int i = 0;
    if (localIterator.hasNext())
    {
      if (!((GoogleApiClient)localIterator.next()).zza(this)) {
        break label80;
      }
      i += 1;
    }
    label80:
    for (;;)
    {
      break;
      try
      {
        this.zzXp.tryAcquire(i, 5L, TimeUnit.SECONDS);
        return null;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;)
        {
          Log.i("GACSignInLoader", "Unexpected InterruptedException", localInterruptedException);
          Thread.currentThread().interrupt();
        }
      }
    }
  }
  
  public void zzna()
  {
    this.zzXp.release();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\auth\api\signin\internal\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */