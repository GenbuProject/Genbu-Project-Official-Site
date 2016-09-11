package com.google.android.gms.ads.internal.client;

import com.google.android.gms.internal.zzhb;
import java.util.Random;

@zzhb
public class zzo
  extends zzx.zza
{
  private Object zzpV = new Object();
  private final Random zzuy = new Random();
  private long zzuz;
  
  public zzo()
  {
    zzcY();
  }
  
  public long getValue()
  {
    return this.zzuz;
  }
  
  public void zzcY()
  {
    Object localObject1 = this.zzpV;
    int i = 3;
    long l1 = 0L;
    for (;;)
    {
      int j = i - 1;
      if (j > 0) {}
      try
      {
        long l2 = this.zzuy.nextInt() + 2147483648L;
        l1 = l2;
        i = j;
        if (l2 == this.zzuz) {
          continue;
        }
        l1 = l2;
        i = j;
        if (l2 == 0L) {
          continue;
        }
        l1 = l2;
        this.zzuz = l1;
        return;
      }
      finally {}
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\client\zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */