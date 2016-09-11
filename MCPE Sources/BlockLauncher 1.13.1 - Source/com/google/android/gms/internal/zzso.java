package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzso<M extends zzso<M>>
  extends zzsu
{
  protected zzsq zzbuj;
  
  public void writeTo(zzsn paramzzsn)
    throws IOException
  {
    if (this.zzbuj == null) {}
    for (;;)
    {
      return;
      int i = 0;
      while (i < this.zzbuj.size())
      {
        this.zzbuj.zzmG(i).writeTo(paramzzsn);
        i += 1;
      }
    }
  }
  
  public M zzJp()
    throws CloneNotSupportedException
  {
    zzso localzzso = (zzso)super.clone();
    zzss.zza(this, localzzso);
    return localzzso;
  }
  
  public final <T> T zza(zzsp<M, T> paramzzsp)
  {
    if (this.zzbuj == null) {}
    zzsr localzzsr;
    do
    {
      return null;
      localzzsr = this.zzbuj.zzmF(zzsx.zzmJ(paramzzsp.tag));
    } while (localzzsr == null);
    return (T)localzzsr.zzb(paramzzsp);
  }
  
  protected final boolean zza(zzsm paramzzsm, int paramInt)
    throws IOException
  {
    int i = paramzzsm.getPosition();
    if (!paramzzsm.zzmo(paramInt)) {
      return false;
    }
    int j = zzsx.zzmJ(paramInt);
    zzsw localzzsw = new zzsw(paramInt, paramzzsm.zzz(i, paramzzsm.getPosition() - i));
    paramzzsm = null;
    if (this.zzbuj == null) {
      this.zzbuj = new zzsq();
    }
    for (;;)
    {
      Object localObject = paramzzsm;
      if (paramzzsm == null)
      {
        localObject = new zzsr();
        this.zzbuj.zza(j, (zzsr)localObject);
      }
      ((zzsr)localObject).zza(localzzsw);
      return true;
      paramzzsm = this.zzbuj.zzmF(j);
    }
  }
  
  protected int zzz()
  {
    int j = 0;
    if (this.zzbuj != null)
    {
      int i = 0;
      for (;;)
      {
        k = i;
        if (j >= this.zzbuj.size()) {
          break;
        }
        i += this.zzbuj.zzmG(j).zzz();
        j += 1;
      }
    }
    int k = 0;
    return k;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzso.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */