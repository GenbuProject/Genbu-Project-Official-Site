package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.support.v4.util.LruCache;
import com.google.android.gms.common.internal.zzw;

public final class zzmd
  extends LruCache<zza, Drawable>
{
  public zzmd()
  {
    super(10);
  }
  
  public static final class zza
  {
    public final int zzakx;
    public final int zzaky;
    
    public zza(int paramInt1, int paramInt2)
    {
      this.zzakx = paramInt1;
      this.zzaky = paramInt2;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = true;
      boolean bool1;
      if (!(paramObject instanceof zza)) {
        bool1 = false;
      }
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (this == paramObject);
        paramObject = (zza)paramObject;
        if (((zza)paramObject).zzakx != this.zzakx) {
          break;
        }
        bool1 = bool2;
      } while (((zza)paramObject).zzaky == this.zzaky);
      return false;
    }
    
    public int hashCode()
    {
      return zzw.hashCode(new Object[] { Integer.valueOf(this.zzakx), Integer.valueOf(this.zzaky) });
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzmd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */