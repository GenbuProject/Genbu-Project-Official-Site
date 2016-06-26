package com.google.android.gms.drive.internal;

import com.google.android.gms.internal.zzsm;
import com.google.android.gms.internal.zzsn;
import com.google.android.gms.internal.zzso;
import com.google.android.gms.internal.zzsq;
import com.google.android.gms.internal.zzst;
import com.google.android.gms.internal.zzsu;
import java.io.IOException;

public final class zzat
  extends zzso<zzat>
{
  public int versionCode;
  public long zzarW;
  public String zzarY;
  public long zzarZ;
  public int zzasa;
  
  public zzat()
  {
    zztl();
  }
  
  public static zzat zzm(byte[] paramArrayOfByte)
    throws zzst
  {
    return (zzat)zzsu.mergeFrom(new zzat(), paramArrayOfByte);
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
          return bool1;
          bool1 = bool2;
        } while (!(paramObject instanceof zzat));
        paramObject = (zzat)paramObject;
        bool1 = bool2;
      } while (this.versionCode != ((zzat)paramObject).versionCode);
      if (this.zzarY != null) {
        break;
      }
      bool1 = bool2;
    } while (((zzat)paramObject).zzarY != null);
    while (this.zzarY.equals(((zzat)paramObject).zzarY))
    {
      bool1 = bool2;
      if (this.zzarZ != ((zzat)paramObject).zzarZ) {
        break;
      }
      bool1 = bool2;
      if (this.zzarW != ((zzat)paramObject).zzarW) {
        break;
      }
      bool1 = bool2;
      if (this.zzasa != ((zzat)paramObject).zzasa) {
        break;
      }
      if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
        break label149;
      }
      if (((zzat)paramObject).zzbuj != null)
      {
        bool1 = bool2;
        if (!((zzat)paramObject).zzbuj.isEmpty()) {
          break;
        }
      }
      return true;
    }
    return false;
    label149:
    return this.zzbuj.equals(((zzat)paramObject).zzbuj);
  }
  
  public int hashCode()
  {
    int k = 0;
    int m = getClass().getName().hashCode();
    int n = this.versionCode;
    int i;
    int i1;
    int i2;
    int i3;
    if (this.zzarY == null)
    {
      i = 0;
      i1 = (int)(this.zzarZ ^ this.zzarZ >>> 32);
      i2 = (int)(this.zzarW ^ this.zzarW >>> 32);
      i3 = this.zzasa;
      j = k;
      if (this.zzbuj != null) {
        if (!this.zzbuj.isEmpty()) {
          break label138;
        }
      }
    }
    label138:
    for (int j = k;; j = this.zzbuj.hashCode())
    {
      return ((((i + ((m + 527) * 31 + n) * 31) * 31 + i1) * 31 + i2) * 31 + i3) * 31 + j;
      i = this.zzarY.hashCode();
      break;
    }
  }
  
  public void writeTo(zzsn paramzzsn)
    throws IOException
  {
    paramzzsn.zzA(1, this.versionCode);
    paramzzsn.zzn(2, this.zzarY);
    paramzzsn.zzc(3, this.zzarZ);
    paramzzsn.zzc(4, this.zzarW);
    if (this.zzasa != -1) {
      paramzzsn.zzA(5, this.zzasa);
    }
    super.writeTo(paramzzsn);
  }
  
  public zzat zzm(zzsm paramzzsm)
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
      case 18: 
        this.zzarY = paramzzsm.readString();
        break;
      case 24: 
        this.zzarZ = paramzzsm.zzJe();
        break;
      case 32: 
        this.zzarW = paramzzsm.zzJe();
        break;
      case 40: 
        this.zzasa = paramzzsm.zzJb();
      }
    }
  }
  
  public zzat zztl()
  {
    this.versionCode = 1;
    this.zzarY = "";
    this.zzarZ = -1L;
    this.zzarW = -1L;
    this.zzasa = -1;
    this.zzbuj = null;
    this.zzbuu = -1;
    return this;
  }
  
  protected int zzz()
  {
    int j = super.zzz() + zzsn.zzC(1, this.versionCode) + zzsn.zzo(2, this.zzarY) + zzsn.zze(3, this.zzarZ) + zzsn.zze(4, this.zzarW);
    int i = j;
    if (this.zzasa != -1) {
      i = j + zzsn.zzC(5, this.zzasa);
    }
    return i;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */