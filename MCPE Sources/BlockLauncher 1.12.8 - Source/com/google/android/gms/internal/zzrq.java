package com.google.android.gms.internal;

import java.io.IOException;

public abstract interface zzrq
{
  public static final class zza
    extends zzso<zza>
  {
    public long zzbmd;
    public zzaf.zzj zzbme;
    public zzaf.zzf zzju;
    
    public zza()
    {
      zzHG();
    }
    
    public static zza zzy(byte[] paramArrayOfByte)
      throws zzst
    {
      return (zza)zzsu.mergeFrom(new zza(), paramArrayOfByte);
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      label55:
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
            } while (!(paramObject instanceof zza));
            paramObject = (zza)paramObject;
            bool1 = bool2;
          } while (this.zzbmd != ((zza)paramObject).zzbmd);
          if (this.zzju != null) {
            break;
          }
          bool1 = bool2;
        } while (((zza)paramObject).zzju != null);
        if (this.zzbme != null) {
          break label125;
        }
        bool1 = bool2;
      } while (((zza)paramObject).zzbme != null);
      for (;;)
      {
        if ((this.zzbuj == null) || (this.zzbuj.isEmpty()))
        {
          if (((zza)paramObject).zzbuj != null)
          {
            bool1 = bool2;
            if (!((zza)paramObject).zzbuj.isEmpty()) {
              break;
            }
          }
          return true;
          if (this.zzju.equals(((zza)paramObject).zzju)) {
            break label55;
          }
          return false;
          label125:
          if (!this.zzbme.equals(((zza)paramObject).zzbme)) {
            return false;
          }
        }
      }
      return this.zzbuj.equals(((zza)paramObject).zzbuj);
    }
    
    public int hashCode()
    {
      int m = 0;
      int n = getClass().getName().hashCode();
      int i1 = (int)(this.zzbmd ^ this.zzbmd >>> 32);
      int i;
      int j;
      if (this.zzju == null)
      {
        i = 0;
        if (this.zzbme != null) {
          break label110;
        }
        j = 0;
        label48:
        k = m;
        if (this.zzbuj != null) {
          if (!this.zzbuj.isEmpty()) {
            break label121;
          }
        }
      }
      label110:
      label121:
      for (int k = m;; k = this.zzbuj.hashCode())
      {
        return (j + (i + ((n + 527) * 31 + i1) * 31) * 31) * 31 + k;
        i = this.zzju.hashCode();
        break;
        j = this.zzbme.hashCode();
        break label48;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      paramzzsn.zzb(1, this.zzbmd);
      if (this.zzju != null) {
        paramzzsn.zza(2, this.zzju);
      }
      if (this.zzbme != null) {
        paramzzsn.zza(3, this.zzbme);
      }
      super.writeTo(paramzzsn);
    }
    
    public zza zzHG()
    {
      this.zzbmd = 0L;
      this.zzju = null;
      this.zzbme = null;
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zza zzJ(zzsm paramzzsm)
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
          this.zzbmd = paramzzsm.zzJa();
          break;
        case 18: 
          if (this.zzju == null) {
            this.zzju = new zzaf.zzf();
          }
          paramzzsm.zza(this.zzju);
          break;
        case 26: 
          if (this.zzbme == null) {
            this.zzbme = new zzaf.zzj();
          }
          paramzzsm.zza(this.zzbme);
        }
      }
    }
    
    protected int zzz()
    {
      int j = super.zzz() + zzsn.zzd(1, this.zzbmd);
      int i = j;
      if (this.zzju != null) {
        i = j + zzsn.zzc(2, this.zzju);
      }
      j = i;
      if (this.zzbme != null) {
        j = i + zzsn.zzc(3, this.zzbme);
      }
      return j;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzrq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */