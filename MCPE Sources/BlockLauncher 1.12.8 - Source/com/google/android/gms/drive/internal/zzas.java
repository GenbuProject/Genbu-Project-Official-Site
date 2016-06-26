package com.google.android.gms.drive.internal;

import com.google.android.gms.internal.zzsm;
import com.google.android.gms.internal.zzsn;
import com.google.android.gms.internal.zzso;
import com.google.android.gms.internal.zzsq;
import java.io.IOException;

public final class zzas
  extends zzso<zzas>
{
  public int versionCode;
  public long zzarV;
  public long zzarW;
  public long zzarX;
  
  public zzas()
  {
    zztk();
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
            do
            {
              do
              {
                return bool1;
                bool1 = bool2;
              } while (!(paramObject instanceof zzas));
              paramObject = (zzas)paramObject;
              bool1 = bool2;
            } while (this.versionCode != ((zzas)paramObject).versionCode);
            bool1 = bool2;
          } while (this.zzarV != ((zzas)paramObject).zzarV);
          bool1 = bool2;
        } while (this.zzarW != ((zzas)paramObject).zzarW);
        bool1 = bool2;
      } while (this.zzarX != ((zzas)paramObject).zzarX);
      if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
        break label118;
      }
      if (((zzas)paramObject).zzbuj == null) {
        break;
      }
      bool1 = bool2;
    } while (!((zzas)paramObject).zzbuj.isEmpty());
    return true;
    label118:
    return this.zzbuj.equals(((zzas)paramObject).zzbuj);
  }
  
  public int hashCode()
  {
    int j = getClass().getName().hashCode();
    int k = this.versionCode;
    int m = (int)(this.zzarV ^ this.zzarV >>> 32);
    int n = (int)(this.zzarW ^ this.zzarW >>> 32);
    int i1 = (int)(this.zzarX ^ this.zzarX >>> 32);
    if ((this.zzbuj == null) || (this.zzbuj.isEmpty())) {}
    for (int i = 0;; i = this.zzbuj.hashCode()) {
      return i + (((((j + 527) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31;
    }
  }
  
  public void writeTo(zzsn paramzzsn)
    throws IOException
  {
    paramzzsn.zzA(1, this.versionCode);
    paramzzsn.zzc(2, this.zzarV);
    paramzzsn.zzc(3, this.zzarW);
    paramzzsn.zzc(4, this.zzarX);
    super.writeTo(paramzzsn);
  }
  
  public zzas zzl(zzsm paramzzsm)
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
        this.versionCode = paramzzsm.zzJb();
        break;
      case 16: 
        this.zzarV = paramzzsm.zzJe();
        break;
      case 24: 
        this.zzarW = paramzzsm.zzJe();
        break;
      case 32: 
        this.zzarX = paramzzsm.zzJe();
      }
    }
  }
  
  public zzas zztk()
  {
    this.versionCode = 1;
    this.zzarV = -1L;
    this.zzarW = -1L;
    this.zzarX = -1L;
    this.zzbuj = null;
    this.zzbuu = -1;
    return this;
  }
  
  protected int zzz()
  {
    return super.zzz() + zzsn.zzC(1, this.versionCode) + zzsn.zze(2, this.zzarV) + zzsn.zze(3, this.zzarW) + zzsn.zze(4, this.zzarX);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */