package com.google.android.gms.drive.internal;

import com.google.android.gms.internal.zzsm;
import com.google.android.gms.internal.zzsn;
import com.google.android.gms.internal.zzso;
import com.google.android.gms.internal.zzsq;
import java.io.IOException;

public final class zzau
  extends zzso<zzau>
{
  public long zzarW;
  public long zzarZ;
  
  public zzau()
  {
    zztm();
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramObject == this) {
      bool1 = true;
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return bool1;
            bool1 = bool2;
          } while (!(paramObject instanceof zzau));
          paramObject = (zzau)paramObject;
          bool1 = bool2;
        } while (this.zzarZ != ((zzau)paramObject).zzarZ);
        bool1 = bool2;
      } while (this.zzarW != ((zzau)paramObject).zzarW);
      if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
        break label91;
      }
      if (((zzau)paramObject).zzbuj == null) {
        break;
      }
      bool1 = bool2;
    } while (!((zzau)paramObject).zzbuj.isEmpty());
    return true;
    label91:
    return this.zzbuj.equals(((zzau)paramObject).zzbuj);
  }
  
  public int hashCode()
  {
    int j = getClass().getName().hashCode();
    int k = (int)(this.zzarZ ^ this.zzarZ >>> 32);
    int m = (int)(this.zzarW ^ this.zzarW >>> 32);
    if ((this.zzbuj == null) || (this.zzbuj.isEmpty())) {}
    for (int i = 0;; i = this.zzbuj.hashCode()) {
      return i + (((j + 527) * 31 + k) * 31 + m) * 31;
    }
  }
  
  public void writeTo(zzsn paramzzsn)
    throws IOException
  {
    paramzzsn.zzc(1, this.zzarZ);
    paramzzsn.zzc(2, this.zzarW);
    super.writeTo(paramzzsn);
  }
  
  public zzau zzn(zzsm paramzzsm)
    throws IOException
  {
    for (;;)
    {
      int i = paramzzsm.zzIX();
      switch (i)
      {
      default: 
        if (zza(paramzzsm, i)) {}
        break;
      case 0: 
        return this;
      case 8: 
        this.zzarZ = paramzzsm.zzJe();
        break;
      case 16: 
        this.zzarW = paramzzsm.zzJe();
      }
    }
  }
  
  public zzau zztm()
  {
    this.zzarZ = -1L;
    this.zzarW = -1L;
    this.zzbuj = null;
    this.zzbuu = -1;
    return this;
  }
  
  protected int zzz()
  {
    return super.zzz() + zzsn.zze(1, this.zzarZ) + zzsn.zze(2, this.zzarW);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzau.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */