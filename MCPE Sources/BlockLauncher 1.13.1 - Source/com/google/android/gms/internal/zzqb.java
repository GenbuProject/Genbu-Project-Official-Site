package com.google.android.gms.internal;

import java.io.IOException;

public abstract interface zzqb
{
  public static final class zza
    extends zzsu
  {
    private static volatile zza[] zzaZZ;
    public Integer zzaZr;
    public zzqb.zzf zzbaa;
    public zzqb.zzf zzbab;
    public Boolean zzbac;
    
    public zza()
    {
      zzDQ();
    }
    
    public static zza[] zzDP()
    {
      if (zzaZZ == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzaZZ == null) {
          zzaZZ = new zza[0];
        }
        return zzaZZ;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {}
      do
      {
        do
        {
          return true;
          if (!(paramObject instanceof zza)) {
            return false;
          }
          paramObject = (zza)paramObject;
          if (this.zzaZr == null)
          {
            if (((zza)paramObject).zzaZr != null) {
              return false;
            }
          }
          else if (!this.zzaZr.equals(((zza)paramObject).zzaZr)) {
            return false;
          }
          if (this.zzbaa == null)
          {
            if (((zza)paramObject).zzbaa != null) {
              return false;
            }
          }
          else if (!this.zzbaa.equals(((zza)paramObject).zzbaa)) {
            return false;
          }
          if (this.zzbab == null)
          {
            if (((zza)paramObject).zzbab != null) {
              return false;
            }
          }
          else if (!this.zzbab.equals(((zza)paramObject).zzbab)) {
            return false;
          }
          if (this.zzbac != null) {
            break;
          }
        } while (((zza)paramObject).zzbac == null);
        return false;
      } while (this.zzbac.equals(((zza)paramObject).zzbac));
      return false;
    }
    
    public int hashCode()
    {
      int m = 0;
      int n = getClass().getName().hashCode();
      int i;
      int j;
      label33:
      int k;
      if (this.zzaZr == null)
      {
        i = 0;
        if (this.zzbaa != null) {
          break label88;
        }
        j = 0;
        if (this.zzbab != null) {
          break label99;
        }
        k = 0;
        label42:
        if (this.zzbac != null) {
          break label110;
        }
      }
      for (;;)
      {
        return (k + (j + (i + (n + 527) * 31) * 31) * 31) * 31 + m;
        i = this.zzaZr.hashCode();
        break;
        label88:
        j = this.zzbaa.hashCode();
        break label33;
        label99:
        k = this.zzbab.hashCode();
        break label42;
        label110:
        m = this.zzbac.hashCode();
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (this.zzaZr != null) {
        paramzzsn.zzA(1, this.zzaZr.intValue());
      }
      if (this.zzbaa != null) {
        paramzzsn.zza(2, this.zzbaa);
      }
      if (this.zzbab != null) {
        paramzzsn.zza(3, this.zzbab);
      }
      if (this.zzbac != null) {
        paramzzsn.zze(4, this.zzbac.booleanValue());
      }
      super.writeTo(paramzzsn);
    }
    
    public zza zzC(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        switch (i)
        {
        default: 
          if (zzsx.zzb(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          this.zzaZr = Integer.valueOf(paramzzsm.zzJb());
          break;
        case 18: 
          if (this.zzbaa == null) {
            this.zzbaa = new zzqb.zzf();
          }
          paramzzsm.zza(this.zzbaa);
          break;
        case 26: 
          if (this.zzbab == null) {
            this.zzbab = new zzqb.zzf();
          }
          paramzzsm.zza(this.zzbab);
          break;
        case 32: 
          this.zzbac = Boolean.valueOf(paramzzsm.zzJc());
        }
      }
    }
    
    public zza zzDQ()
    {
      this.zzaZr = null;
      this.zzbaa = null;
      this.zzbab = null;
      this.zzbac = null;
      this.zzbuu = -1;
      return this;
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (this.zzaZr != null) {
        i = j + zzsn.zzC(1, this.zzaZr.intValue());
      }
      j = i;
      if (this.zzbaa != null) {
        j = i + zzsn.zzc(2, this.zzbaa);
      }
      i = j;
      if (this.zzbab != null) {
        i = j + zzsn.zzc(3, this.zzbab);
      }
      j = i;
      if (this.zzbac != null) {
        j = i + zzsn.zzf(4, this.zzbac.booleanValue());
      }
      return j;
    }
  }
  
  public static final class zzb
    extends zzsu
  {
    private static volatile zzb[] zzbad;
    public Integer count;
    public String name;
    public zzqb.zzc[] zzbae;
    public Long zzbaf;
    public Long zzbag;
    
    public zzb()
    {
      zzDS();
    }
    
    public static zzb[] zzDR()
    {
      if (zzbad == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzbad == null) {
          zzbad = new zzb[0];
        }
        return zzbad;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {}
      do
      {
        do
        {
          return true;
          if (!(paramObject instanceof zzb)) {
            return false;
          }
          paramObject = (zzb)paramObject;
          if (!zzss.equals(this.zzbae, ((zzb)paramObject).zzbae)) {
            return false;
          }
          if (this.name == null)
          {
            if (((zzb)paramObject).name != null) {
              return false;
            }
          }
          else if (!this.name.equals(((zzb)paramObject).name)) {
            return false;
          }
          if (this.zzbaf == null)
          {
            if (((zzb)paramObject).zzbaf != null) {
              return false;
            }
          }
          else if (!this.zzbaf.equals(((zzb)paramObject).zzbaf)) {
            return false;
          }
          if (this.zzbag == null)
          {
            if (((zzb)paramObject).zzbag != null) {
              return false;
            }
          }
          else if (!this.zzbag.equals(((zzb)paramObject).zzbag)) {
            return false;
          }
          if (this.count != null) {
            break;
          }
        } while (((zzb)paramObject).count == null);
        return false;
      } while (this.count.equals(((zzb)paramObject).count));
      return false;
    }
    
    public int hashCode()
    {
      int m = 0;
      int n = getClass().getName().hashCode();
      int i1 = zzss.hashCode(this.zzbae);
      int i;
      int j;
      label42:
      int k;
      if (this.name == null)
      {
        i = 0;
        if (this.zzbaf != null) {
          break label103;
        }
        j = 0;
        if (this.zzbag != null) {
          break label114;
        }
        k = 0;
        label51:
        if (this.count != null) {
          break label125;
        }
      }
      for (;;)
      {
        return (k + (j + (i + ((n + 527) * 31 + i1) * 31) * 31) * 31) * 31 + m;
        i = this.name.hashCode();
        break;
        label103:
        j = this.zzbaf.hashCode();
        break label42;
        label114:
        k = this.zzbag.hashCode();
        break label51;
        label125:
        m = this.count.hashCode();
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if ((this.zzbae != null) && (this.zzbae.length > 0))
      {
        int i = 0;
        while (i < this.zzbae.length)
        {
          zzqb.zzc localzzc = this.zzbae[i];
          if (localzzc != null) {
            paramzzsn.zza(1, localzzc);
          }
          i += 1;
        }
      }
      if (this.name != null) {
        paramzzsn.zzn(2, this.name);
      }
      if (this.zzbaf != null) {
        paramzzsn.zzb(3, this.zzbaf.longValue());
      }
      if (this.zzbag != null) {
        paramzzsn.zzb(4, this.zzbag.longValue());
      }
      if (this.count != null) {
        paramzzsn.zzA(5, this.count.intValue());
      }
      super.writeTo(paramzzsn);
    }
    
    public zzb zzD(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        switch (i)
        {
        default: 
          if (zzsx.zzb(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          int j = zzsx.zzc(paramzzsm, 10);
          if (this.zzbae == null) {}
          zzqb.zzc[] arrayOfzzc;
          for (i = 0;; i = this.zzbae.length)
          {
            arrayOfzzc = new zzqb.zzc[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbae, 0, arrayOfzzc, 0, i);
              j = i;
            }
            while (j < arrayOfzzc.length - 1)
            {
              arrayOfzzc[j] = new zzqb.zzc();
              paramzzsm.zza(arrayOfzzc[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfzzc[j] = new zzqb.zzc();
          paramzzsm.zza(arrayOfzzc[j]);
          this.zzbae = arrayOfzzc;
          break;
        case 18: 
          this.name = paramzzsm.readString();
          break;
        case 24: 
          this.zzbaf = Long.valueOf(paramzzsm.zzJa());
          break;
        case 32: 
          this.zzbag = Long.valueOf(paramzzsm.zzJa());
          break;
        case 40: 
          this.count = Integer.valueOf(paramzzsm.zzJb());
        }
      }
    }
    
    public zzb zzDS()
    {
      this.zzbae = zzqb.zzc.zzDT();
      this.name = null;
      this.zzbaf = null;
      this.zzbag = null;
      this.count = null;
      this.zzbuu = -1;
      return this;
    }
    
    protected int zzz()
    {
      int i = super.zzz();
      int j = i;
      if (this.zzbae != null)
      {
        j = i;
        if (this.zzbae.length > 0)
        {
          int k = 0;
          for (;;)
          {
            j = i;
            if (k >= this.zzbae.length) {
              break;
            }
            zzqb.zzc localzzc = this.zzbae[k];
            j = i;
            if (localzzc != null) {
              j = i + zzsn.zzc(1, localzzc);
            }
            k += 1;
            i = j;
          }
        }
      }
      i = j;
      if (this.name != null) {
        i = j + zzsn.zzo(2, this.name);
      }
      j = i;
      if (this.zzbaf != null) {
        j = i + zzsn.zzd(3, this.zzbaf.longValue());
      }
      i = j;
      if (this.zzbag != null) {
        i = j + zzsn.zzd(4, this.zzbag.longValue());
      }
      j = i;
      if (this.count != null) {
        j = i + zzsn.zzC(5, this.count.intValue());
      }
      return j;
    }
  }
  
  public static final class zzc
    extends zzsu
  {
    private static volatile zzc[] zzbah;
    public String name;
    public Float zzaZo;
    public String zzamJ;
    public Long zzbai;
    
    public zzc()
    {
      zzDU();
    }
    
    public static zzc[] zzDT()
    {
      if (zzbah == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzbah == null) {
          zzbah = new zzc[0];
        }
        return zzbah;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {}
      do
      {
        do
        {
          return true;
          if (!(paramObject instanceof zzc)) {
            return false;
          }
          paramObject = (zzc)paramObject;
          if (this.name == null)
          {
            if (((zzc)paramObject).name != null) {
              return false;
            }
          }
          else if (!this.name.equals(((zzc)paramObject).name)) {
            return false;
          }
          if (this.zzamJ == null)
          {
            if (((zzc)paramObject).zzamJ != null) {
              return false;
            }
          }
          else if (!this.zzamJ.equals(((zzc)paramObject).zzamJ)) {
            return false;
          }
          if (this.zzbai == null)
          {
            if (((zzc)paramObject).zzbai != null) {
              return false;
            }
          }
          else if (!this.zzbai.equals(((zzc)paramObject).zzbai)) {
            return false;
          }
          if (this.zzaZo != null) {
            break;
          }
        } while (((zzc)paramObject).zzaZo == null);
        return false;
      } while (this.zzaZo.equals(((zzc)paramObject).zzaZo));
      return false;
    }
    
    public int hashCode()
    {
      int m = 0;
      int n = getClass().getName().hashCode();
      int i;
      int j;
      label33:
      int k;
      if (this.name == null)
      {
        i = 0;
        if (this.zzamJ != null) {
          break label88;
        }
        j = 0;
        if (this.zzbai != null) {
          break label99;
        }
        k = 0;
        label42:
        if (this.zzaZo != null) {
          break label110;
        }
      }
      for (;;)
      {
        return (k + (j + (i + (n + 527) * 31) * 31) * 31) * 31 + m;
        i = this.name.hashCode();
        break;
        label88:
        j = this.zzamJ.hashCode();
        break label33;
        label99:
        k = this.zzbai.hashCode();
        break label42;
        label110:
        m = this.zzaZo.hashCode();
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (this.name != null) {
        paramzzsn.zzn(1, this.name);
      }
      if (this.zzamJ != null) {
        paramzzsn.zzn(2, this.zzamJ);
      }
      if (this.zzbai != null) {
        paramzzsn.zzb(3, this.zzbai.longValue());
      }
      if (this.zzaZo != null) {
        paramzzsn.zzb(4, this.zzaZo.floatValue());
      }
      super.writeTo(paramzzsn);
    }
    
    public zzc zzDU()
    {
      this.name = null;
      this.zzamJ = null;
      this.zzbai = null;
      this.zzaZo = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zzc zzE(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        switch (i)
        {
        default: 
          if (zzsx.zzb(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          this.name = paramzzsm.readString();
          break;
        case 18: 
          this.zzamJ = paramzzsm.readString();
          break;
        case 24: 
          this.zzbai = Long.valueOf(paramzzsm.zzJa());
          break;
        case 37: 
          this.zzaZo = Float.valueOf(paramzzsm.readFloat());
        }
      }
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (this.name != null) {
        i = j + zzsn.zzo(1, this.name);
      }
      j = i;
      if (this.zzamJ != null) {
        j = i + zzsn.zzo(2, this.zzamJ);
      }
      i = j;
      if (this.zzbai != null) {
        i = j + zzsn.zzd(3, this.zzbai.longValue());
      }
      j = i;
      if (this.zzaZo != null) {
        j = i + zzsn.zzc(4, this.zzaZo.floatValue());
      }
      return j;
    }
  }
  
  public static final class zzd
    extends zzsu
  {
    public zzqb.zze[] zzbaj;
    
    public zzd()
    {
      zzDV();
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {}
      do
      {
        return true;
        if (!(paramObject instanceof zzd)) {
          return false;
        }
        paramObject = (zzd)paramObject;
      } while (zzss.equals(this.zzbaj, ((zzd)paramObject).zzbaj));
      return false;
    }
    
    public int hashCode()
    {
      return (getClass().getName().hashCode() + 527) * 31 + zzss.hashCode(this.zzbaj);
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if ((this.zzbaj != null) && (this.zzbaj.length > 0))
      {
        int i = 0;
        while (i < this.zzbaj.length)
        {
          zzqb.zze localzze = this.zzbaj[i];
          if (localzze != null) {
            paramzzsn.zza(1, localzze);
          }
          i += 1;
        }
      }
      super.writeTo(paramzzsn);
    }
    
    public zzd zzDV()
    {
      this.zzbaj = zzqb.zze.zzDW();
      this.zzbuu = -1;
      return this;
    }
    
    public zzd zzF(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        switch (i)
        {
        default: 
          if (zzsx.zzb(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          int j = zzsx.zzc(paramzzsm, 10);
          if (this.zzbaj == null) {}
          zzqb.zze[] arrayOfzze;
          for (i = 0;; i = this.zzbaj.length)
          {
            arrayOfzze = new zzqb.zze[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbaj, 0, arrayOfzze, 0, i);
              j = i;
            }
            while (j < arrayOfzze.length - 1)
            {
              arrayOfzze[j] = new zzqb.zze();
              paramzzsm.zza(arrayOfzze[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfzze[j] = new zzqb.zze();
          paramzzsm.zza(arrayOfzze[j]);
          this.zzbaj = arrayOfzze;
        }
      }
    }
    
    protected int zzz()
    {
      int i = super.zzz();
      int k = i;
      if (this.zzbaj != null)
      {
        k = i;
        if (this.zzbaj.length > 0)
        {
          int j = 0;
          for (;;)
          {
            k = i;
            if (j >= this.zzbaj.length) {
              break;
            }
            zzqb.zze localzze = this.zzbaj[j];
            k = i;
            if (localzze != null) {
              k = i + zzsn.zzc(1, localzze);
            }
            j += 1;
            i = k;
          }
        }
      }
      return k;
    }
  }
  
  public static final class zze
    extends zzsu
  {
    private static volatile zze[] zzbak;
    public String appId;
    public String osVersion;
    public String zzaMV;
    public String zzaVt;
    public String zzaVu;
    public String zzaVx;
    public Boolean zzbaA;
    public String zzbaB;
    public Long zzbaC;
    public Integer zzbaD;
    public Boolean zzbaE;
    public zzqb.zza[] zzbaF;
    public Integer zzbal;
    public zzqb.zzb[] zzbam;
    public zzqb.zzg[] zzban;
    public Long zzbao;
    public Long zzbap;
    public Long zzbaq;
    public Long zzbar;
    public Long zzbas;
    public String zzbat;
    public String zzbau;
    public String zzbav;
    public Integer zzbaw;
    public Long zzbax;
    public Long zzbay;
    public String zzbaz;
    
    public zze()
    {
      zzDX();
    }
    
    public static zze[] zzDW()
    {
      if (zzbak == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzbak == null) {
          zzbak = new zze[0];
        }
        return zzbak;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {}
      do
      {
        return true;
        if (!(paramObject instanceof zze)) {
          return false;
        }
        paramObject = (zze)paramObject;
        if (this.zzbal == null)
        {
          if (((zze)paramObject).zzbal != null) {
            return false;
          }
        }
        else if (!this.zzbal.equals(((zze)paramObject).zzbal)) {
          return false;
        }
        if (!zzss.equals(this.zzbam, ((zze)paramObject).zzbam)) {
          return false;
        }
        if (!zzss.equals(this.zzban, ((zze)paramObject).zzban)) {
          return false;
        }
        if (this.zzbao == null)
        {
          if (((zze)paramObject).zzbao != null) {
            return false;
          }
        }
        else if (!this.zzbao.equals(((zze)paramObject).zzbao)) {
          return false;
        }
        if (this.zzbap == null)
        {
          if (((zze)paramObject).zzbap != null) {
            return false;
          }
        }
        else if (!this.zzbap.equals(((zze)paramObject).zzbap)) {
          return false;
        }
        if (this.zzbaq == null)
        {
          if (((zze)paramObject).zzbaq != null) {
            return false;
          }
        }
        else if (!this.zzbaq.equals(((zze)paramObject).zzbaq)) {
          return false;
        }
        if (this.zzbar == null)
        {
          if (((zze)paramObject).zzbar != null) {
            return false;
          }
        }
        else if (!this.zzbar.equals(((zze)paramObject).zzbar)) {
          return false;
        }
        if (this.zzbas == null)
        {
          if (((zze)paramObject).zzbas != null) {
            return false;
          }
        }
        else if (!this.zzbas.equals(((zze)paramObject).zzbas)) {
          return false;
        }
        if (this.zzbat == null)
        {
          if (((zze)paramObject).zzbat != null) {
            return false;
          }
        }
        else if (!this.zzbat.equals(((zze)paramObject).zzbat)) {
          return false;
        }
        if (this.osVersion == null)
        {
          if (((zze)paramObject).osVersion != null) {
            return false;
          }
        }
        else if (!this.osVersion.equals(((zze)paramObject).osVersion)) {
          return false;
        }
        if (this.zzbau == null)
        {
          if (((zze)paramObject).zzbau != null) {
            return false;
          }
        }
        else if (!this.zzbau.equals(((zze)paramObject).zzbau)) {
          return false;
        }
        if (this.zzbav == null)
        {
          if (((zze)paramObject).zzbav != null) {
            return false;
          }
        }
        else if (!this.zzbav.equals(((zze)paramObject).zzbav)) {
          return false;
        }
        if (this.zzbaw == null)
        {
          if (((zze)paramObject).zzbaw != null) {
            return false;
          }
        }
        else if (!this.zzbaw.equals(((zze)paramObject).zzbaw)) {
          return false;
        }
        if (this.zzaVu == null)
        {
          if (((zze)paramObject).zzaVu != null) {
            return false;
          }
        }
        else if (!this.zzaVu.equals(((zze)paramObject).zzaVu)) {
          return false;
        }
        if (this.appId == null)
        {
          if (((zze)paramObject).appId != null) {
            return false;
          }
        }
        else if (!this.appId.equals(((zze)paramObject).appId)) {
          return false;
        }
        if (this.zzaMV == null)
        {
          if (((zze)paramObject).zzaMV != null) {
            return false;
          }
        }
        else if (!this.zzaMV.equals(((zze)paramObject).zzaMV)) {
          return false;
        }
        if (this.zzbax == null)
        {
          if (((zze)paramObject).zzbax != null) {
            return false;
          }
        }
        else if (!this.zzbax.equals(((zze)paramObject).zzbax)) {
          return false;
        }
        if (this.zzbay == null)
        {
          if (((zze)paramObject).zzbay != null) {
            return false;
          }
        }
        else if (!this.zzbay.equals(((zze)paramObject).zzbay)) {
          return false;
        }
        if (this.zzbaz == null)
        {
          if (((zze)paramObject).zzbaz != null) {
            return false;
          }
        }
        else if (!this.zzbaz.equals(((zze)paramObject).zzbaz)) {
          return false;
        }
        if (this.zzbaA == null)
        {
          if (((zze)paramObject).zzbaA != null) {
            return false;
          }
        }
        else if (!this.zzbaA.equals(((zze)paramObject).zzbaA)) {
          return false;
        }
        if (this.zzbaB == null)
        {
          if (((zze)paramObject).zzbaB != null) {
            return false;
          }
        }
        else if (!this.zzbaB.equals(((zze)paramObject).zzbaB)) {
          return false;
        }
        if (this.zzbaC == null)
        {
          if (((zze)paramObject).zzbaC != null) {
            return false;
          }
        }
        else if (!this.zzbaC.equals(((zze)paramObject).zzbaC)) {
          return false;
        }
        if (this.zzbaD == null)
        {
          if (((zze)paramObject).zzbaD != null) {
            return false;
          }
        }
        else if (!this.zzbaD.equals(((zze)paramObject).zzbaD)) {
          return false;
        }
        if (this.zzaVx == null)
        {
          if (((zze)paramObject).zzaVx != null) {
            return false;
          }
        }
        else if (!this.zzaVx.equals(((zze)paramObject).zzaVx)) {
          return false;
        }
        if (this.zzaVt == null)
        {
          if (((zze)paramObject).zzaVt != null) {
            return false;
          }
        }
        else if (!this.zzaVt.equals(((zze)paramObject).zzaVt)) {
          return false;
        }
        if (this.zzbaE == null)
        {
          if (((zze)paramObject).zzbaE != null) {
            return false;
          }
        }
        else if (!this.zzbaE.equals(((zze)paramObject).zzbaE)) {
          return false;
        }
      } while (zzss.equals(this.zzbaF, ((zze)paramObject).zzbaF));
      return false;
    }
    
    public int hashCode()
    {
      int i19 = 0;
      int i20 = getClass().getName().hashCode();
      int i;
      int i21;
      int i22;
      int j;
      label51:
      int k;
      label60:
      int m;
      label70:
      int n;
      label80:
      int i1;
      label90:
      int i2;
      label100:
      int i3;
      label110:
      int i4;
      label120:
      int i5;
      label130:
      int i6;
      label140:
      int i7;
      label150:
      int i8;
      label160:
      int i9;
      label170:
      int i10;
      label180:
      int i11;
      label190:
      int i12;
      label200:
      int i13;
      label210:
      int i14;
      label220:
      int i15;
      label230:
      int i16;
      label240:
      int i17;
      label250:
      int i18;
      if (this.zzbal == null)
      {
        i = 0;
        i21 = zzss.hashCode(this.zzbam);
        i22 = zzss.hashCode(this.zzban);
        if (this.zzbao != null) {
          break label449;
        }
        j = 0;
        if (this.zzbap != null) {
          break label460;
        }
        k = 0;
        if (this.zzbaq != null) {
          break label471;
        }
        m = 0;
        if (this.zzbar != null) {
          break label483;
        }
        n = 0;
        if (this.zzbas != null) {
          break label495;
        }
        i1 = 0;
        if (this.zzbat != null) {
          break label507;
        }
        i2 = 0;
        if (this.osVersion != null) {
          break label519;
        }
        i3 = 0;
        if (this.zzbau != null) {
          break label531;
        }
        i4 = 0;
        if (this.zzbav != null) {
          break label543;
        }
        i5 = 0;
        if (this.zzbaw != null) {
          break label555;
        }
        i6 = 0;
        if (this.zzaVu != null) {
          break label567;
        }
        i7 = 0;
        if (this.appId != null) {
          break label579;
        }
        i8 = 0;
        if (this.zzaMV != null) {
          break label591;
        }
        i9 = 0;
        if (this.zzbax != null) {
          break label603;
        }
        i10 = 0;
        if (this.zzbay != null) {
          break label615;
        }
        i11 = 0;
        if (this.zzbaz != null) {
          break label627;
        }
        i12 = 0;
        if (this.zzbaA != null) {
          break label639;
        }
        i13 = 0;
        if (this.zzbaB != null) {
          break label651;
        }
        i14 = 0;
        if (this.zzbaC != null) {
          break label663;
        }
        i15 = 0;
        if (this.zzbaD != null) {
          break label675;
        }
        i16 = 0;
        if (this.zzaVx != null) {
          break label687;
        }
        i17 = 0;
        if (this.zzaVt != null) {
          break label699;
        }
        i18 = 0;
        label260:
        if (this.zzbaE != null) {
          break label711;
        }
      }
      for (;;)
      {
        return ((i18 + (i17 + (i16 + (i15 + (i14 + (i13 + (i12 + (i11 + (i10 + (i9 + (i8 + (i7 + (i6 + (i5 + (i4 + (i3 + (i2 + (i1 + (n + (m + (k + (j + (((i + (i20 + 527) * 31) * 31 + i21) * 31 + i22) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31 + i19) * 31 + zzss.hashCode(this.zzbaF);
        i = this.zzbal.hashCode();
        break;
        label449:
        j = this.zzbao.hashCode();
        break label51;
        label460:
        k = this.zzbap.hashCode();
        break label60;
        label471:
        m = this.zzbaq.hashCode();
        break label70;
        label483:
        n = this.zzbar.hashCode();
        break label80;
        label495:
        i1 = this.zzbas.hashCode();
        break label90;
        label507:
        i2 = this.zzbat.hashCode();
        break label100;
        label519:
        i3 = this.osVersion.hashCode();
        break label110;
        label531:
        i4 = this.zzbau.hashCode();
        break label120;
        label543:
        i5 = this.zzbav.hashCode();
        break label130;
        label555:
        i6 = this.zzbaw.hashCode();
        break label140;
        label567:
        i7 = this.zzaVu.hashCode();
        break label150;
        label579:
        i8 = this.appId.hashCode();
        break label160;
        label591:
        i9 = this.zzaMV.hashCode();
        break label170;
        label603:
        i10 = this.zzbax.hashCode();
        break label180;
        label615:
        i11 = this.zzbay.hashCode();
        break label190;
        label627:
        i12 = this.zzbaz.hashCode();
        break label200;
        label639:
        i13 = this.zzbaA.hashCode();
        break label210;
        label651:
        i14 = this.zzbaB.hashCode();
        break label220;
        label663:
        i15 = this.zzbaC.hashCode();
        break label230;
        label675:
        i16 = this.zzbaD.hashCode();
        break label240;
        label687:
        i17 = this.zzaVx.hashCode();
        break label250;
        label699:
        i18 = this.zzaVt.hashCode();
        break label260;
        label711:
        i19 = this.zzbaE.hashCode();
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      int j = 0;
      if (this.zzbal != null) {
        paramzzsn.zzA(1, this.zzbal.intValue());
      }
      int i;
      Object localObject;
      if ((this.zzbam != null) && (this.zzbam.length > 0))
      {
        i = 0;
        while (i < this.zzbam.length)
        {
          localObject = this.zzbam[i];
          if (localObject != null) {
            paramzzsn.zza(2, (zzsu)localObject);
          }
          i += 1;
        }
      }
      if ((this.zzban != null) && (this.zzban.length > 0))
      {
        i = 0;
        while (i < this.zzban.length)
        {
          localObject = this.zzban[i];
          if (localObject != null) {
            paramzzsn.zza(3, (zzsu)localObject);
          }
          i += 1;
        }
      }
      if (this.zzbao != null) {
        paramzzsn.zzb(4, this.zzbao.longValue());
      }
      if (this.zzbap != null) {
        paramzzsn.zzb(5, this.zzbap.longValue());
      }
      if (this.zzbaq != null) {
        paramzzsn.zzb(6, this.zzbaq.longValue());
      }
      if (this.zzbas != null) {
        paramzzsn.zzb(7, this.zzbas.longValue());
      }
      if (this.zzbat != null) {
        paramzzsn.zzn(8, this.zzbat);
      }
      if (this.osVersion != null) {
        paramzzsn.zzn(9, this.osVersion);
      }
      if (this.zzbau != null) {
        paramzzsn.zzn(10, this.zzbau);
      }
      if (this.zzbav != null) {
        paramzzsn.zzn(11, this.zzbav);
      }
      if (this.zzbaw != null) {
        paramzzsn.zzA(12, this.zzbaw.intValue());
      }
      if (this.zzaVu != null) {
        paramzzsn.zzn(13, this.zzaVu);
      }
      if (this.appId != null) {
        paramzzsn.zzn(14, this.appId);
      }
      if (this.zzaMV != null) {
        paramzzsn.zzn(16, this.zzaMV);
      }
      if (this.zzbax != null) {
        paramzzsn.zzb(17, this.zzbax.longValue());
      }
      if (this.zzbay != null) {
        paramzzsn.zzb(18, this.zzbay.longValue());
      }
      if (this.zzbaz != null) {
        paramzzsn.zzn(19, this.zzbaz);
      }
      if (this.zzbaA != null) {
        paramzzsn.zze(20, this.zzbaA.booleanValue());
      }
      if (this.zzbaB != null) {
        paramzzsn.zzn(21, this.zzbaB);
      }
      if (this.zzbaC != null) {
        paramzzsn.zzb(22, this.zzbaC.longValue());
      }
      if (this.zzbaD != null) {
        paramzzsn.zzA(23, this.zzbaD.intValue());
      }
      if (this.zzaVx != null) {
        paramzzsn.zzn(24, this.zzaVx);
      }
      if (this.zzaVt != null) {
        paramzzsn.zzn(25, this.zzaVt);
      }
      if (this.zzbar != null) {
        paramzzsn.zzb(26, this.zzbar.longValue());
      }
      if (this.zzbaE != null) {
        paramzzsn.zze(28, this.zzbaE.booleanValue());
      }
      if ((this.zzbaF != null) && (this.zzbaF.length > 0))
      {
        i = j;
        while (i < this.zzbaF.length)
        {
          localObject = this.zzbaF[i];
          if (localObject != null) {
            paramzzsn.zza(29, (zzsu)localObject);
          }
          i += 1;
        }
      }
      super.writeTo(paramzzsn);
    }
    
    public zze zzDX()
    {
      this.zzbal = null;
      this.zzbam = zzqb.zzb.zzDR();
      this.zzban = zzqb.zzg.zzDZ();
      this.zzbao = null;
      this.zzbap = null;
      this.zzbaq = null;
      this.zzbar = null;
      this.zzbas = null;
      this.zzbat = null;
      this.osVersion = null;
      this.zzbau = null;
      this.zzbav = null;
      this.zzbaw = null;
      this.zzaVu = null;
      this.appId = null;
      this.zzaMV = null;
      this.zzbax = null;
      this.zzbay = null;
      this.zzbaz = null;
      this.zzbaA = null;
      this.zzbaB = null;
      this.zzbaC = null;
      this.zzbaD = null;
      this.zzaVx = null;
      this.zzaVt = null;
      this.zzbaE = null;
      this.zzbaF = zzqb.zza.zzDP();
      this.zzbuu = -1;
      return this;
    }
    
    public zze zzG(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        int j;
        Object localObject;
        switch (i)
        {
        default: 
          if (zzsx.zzb(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          this.zzbal = Integer.valueOf(paramzzsm.zzJb());
          break;
        case 18: 
          j = zzsx.zzc(paramzzsm, 18);
          if (this.zzbam == null) {}
          for (i = 0;; i = this.zzbam.length)
          {
            localObject = new zzqb.zzb[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbam, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzqb.zzb();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zzqb.zzb();
          paramzzsm.zza(localObject[j]);
          this.zzbam = ((zzqb.zzb[])localObject);
          break;
        case 26: 
          j = zzsx.zzc(paramzzsm, 26);
          if (this.zzban == null) {}
          for (i = 0;; i = this.zzban.length)
          {
            localObject = new zzqb.zzg[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzban, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzqb.zzg();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zzqb.zzg();
          paramzzsm.zza(localObject[j]);
          this.zzban = ((zzqb.zzg[])localObject);
          break;
        case 32: 
          this.zzbao = Long.valueOf(paramzzsm.zzJa());
          break;
        case 40: 
          this.zzbap = Long.valueOf(paramzzsm.zzJa());
          break;
        case 48: 
          this.zzbaq = Long.valueOf(paramzzsm.zzJa());
          break;
        case 56: 
          this.zzbas = Long.valueOf(paramzzsm.zzJa());
          break;
        case 66: 
          this.zzbat = paramzzsm.readString();
          break;
        case 74: 
          this.osVersion = paramzzsm.readString();
          break;
        case 82: 
          this.zzbau = paramzzsm.readString();
          break;
        case 90: 
          this.zzbav = paramzzsm.readString();
          break;
        case 96: 
          this.zzbaw = Integer.valueOf(paramzzsm.zzJb());
          break;
        case 106: 
          this.zzaVu = paramzzsm.readString();
          break;
        case 114: 
          this.appId = paramzzsm.readString();
          break;
        case 130: 
          this.zzaMV = paramzzsm.readString();
          break;
        case 136: 
          this.zzbax = Long.valueOf(paramzzsm.zzJa());
          break;
        case 144: 
          this.zzbay = Long.valueOf(paramzzsm.zzJa());
          break;
        case 154: 
          this.zzbaz = paramzzsm.readString();
          break;
        case 160: 
          this.zzbaA = Boolean.valueOf(paramzzsm.zzJc());
          break;
        case 170: 
          this.zzbaB = paramzzsm.readString();
          break;
        case 176: 
          this.zzbaC = Long.valueOf(paramzzsm.zzJa());
          break;
        case 184: 
          this.zzbaD = Integer.valueOf(paramzzsm.zzJb());
          break;
        case 194: 
          this.zzaVx = paramzzsm.readString();
          break;
        case 202: 
          this.zzaVt = paramzzsm.readString();
          break;
        case 208: 
          this.zzbar = Long.valueOf(paramzzsm.zzJa());
          break;
        case 224: 
          this.zzbaE = Boolean.valueOf(paramzzsm.zzJc());
          break;
        case 234: 
          j = zzsx.zzc(paramzzsm, 234);
          if (this.zzbaF == null) {}
          for (i = 0;; i = this.zzbaF.length)
          {
            localObject = new zzqb.zza[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbaF, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzqb.zza();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zzqb.zza();
          paramzzsm.zza(localObject[j]);
          this.zzbaF = ((zzqb.zza[])localObject);
        }
      }
    }
    
    protected int zzz()
    {
      int m = 0;
      int j = super.zzz();
      int i = j;
      if (this.zzbal != null) {
        i = j + zzsn.zzC(1, this.zzbal.intValue());
      }
      j = i;
      Object localObject;
      if (this.zzbam != null)
      {
        j = i;
        if (this.zzbam.length > 0)
        {
          j = 0;
          while (j < this.zzbam.length)
          {
            localObject = this.zzbam[j];
            k = i;
            if (localObject != null) {
              k = i + zzsn.zzc(2, (zzsu)localObject);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (this.zzban != null)
      {
        i = j;
        if (this.zzban.length > 0)
        {
          i = j;
          j = 0;
          while (j < this.zzban.length)
          {
            localObject = this.zzban[j];
            k = i;
            if (localObject != null) {
              k = i + zzsn.zzc(3, (zzsu)localObject);
            }
            j += 1;
            i = k;
          }
        }
      }
      j = i;
      if (this.zzbao != null) {
        j = i + zzsn.zzd(4, this.zzbao.longValue());
      }
      i = j;
      if (this.zzbap != null) {
        i = j + zzsn.zzd(5, this.zzbap.longValue());
      }
      j = i;
      if (this.zzbaq != null) {
        j = i + zzsn.zzd(6, this.zzbaq.longValue());
      }
      i = j;
      if (this.zzbas != null) {
        i = j + zzsn.zzd(7, this.zzbas.longValue());
      }
      j = i;
      if (this.zzbat != null) {
        j = i + zzsn.zzo(8, this.zzbat);
      }
      i = j;
      if (this.osVersion != null) {
        i = j + zzsn.zzo(9, this.osVersion);
      }
      j = i;
      if (this.zzbau != null) {
        j = i + zzsn.zzo(10, this.zzbau);
      }
      i = j;
      if (this.zzbav != null) {
        i = j + zzsn.zzo(11, this.zzbav);
      }
      j = i;
      if (this.zzbaw != null) {
        j = i + zzsn.zzC(12, this.zzbaw.intValue());
      }
      i = j;
      if (this.zzaVu != null) {
        i = j + zzsn.zzo(13, this.zzaVu);
      }
      j = i;
      if (this.appId != null) {
        j = i + zzsn.zzo(14, this.appId);
      }
      i = j;
      if (this.zzaMV != null) {
        i = j + zzsn.zzo(16, this.zzaMV);
      }
      j = i;
      if (this.zzbax != null) {
        j = i + zzsn.zzd(17, this.zzbax.longValue());
      }
      i = j;
      if (this.zzbay != null) {
        i = j + zzsn.zzd(18, this.zzbay.longValue());
      }
      j = i;
      if (this.zzbaz != null) {
        j = i + zzsn.zzo(19, this.zzbaz);
      }
      i = j;
      if (this.zzbaA != null) {
        i = j + zzsn.zzf(20, this.zzbaA.booleanValue());
      }
      j = i;
      if (this.zzbaB != null) {
        j = i + zzsn.zzo(21, this.zzbaB);
      }
      i = j;
      if (this.zzbaC != null) {
        i = j + zzsn.zzd(22, this.zzbaC.longValue());
      }
      j = i;
      if (this.zzbaD != null) {
        j = i + zzsn.zzC(23, this.zzbaD.intValue());
      }
      i = j;
      if (this.zzaVx != null) {
        i = j + zzsn.zzo(24, this.zzaVx);
      }
      j = i;
      if (this.zzaVt != null) {
        j = i + zzsn.zzo(25, this.zzaVt);
      }
      int k = j;
      if (this.zzbar != null) {
        k = j + zzsn.zzd(26, this.zzbar.longValue());
      }
      i = k;
      if (this.zzbaE != null) {
        i = k + zzsn.zzf(28, this.zzbaE.booleanValue());
      }
      k = i;
      if (this.zzbaF != null)
      {
        k = i;
        if (this.zzbaF.length > 0)
        {
          j = m;
          for (;;)
          {
            k = i;
            if (j >= this.zzbaF.length) {
              break;
            }
            localObject = this.zzbaF[j];
            k = i;
            if (localObject != null) {
              k = i + zzsn.zzc(29, (zzsu)localObject);
            }
            j += 1;
            i = k;
          }
        }
      }
      return k;
    }
  }
  
  public static final class zzf
    extends zzsu
  {
    public long[] zzbaG;
    public long[] zzbaH;
    
    public zzf()
    {
      zzDY();
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {}
      do
      {
        return true;
        if (!(paramObject instanceof zzf)) {
          return false;
        }
        paramObject = (zzf)paramObject;
        if (!zzss.equals(this.zzbaG, ((zzf)paramObject).zzbaG)) {
          return false;
        }
      } while (zzss.equals(this.zzbaH, ((zzf)paramObject).zzbaH));
      return false;
    }
    
    public int hashCode()
    {
      return ((getClass().getName().hashCode() + 527) * 31 + zzss.hashCode(this.zzbaG)) * 31 + zzss.hashCode(this.zzbaH);
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      int j = 0;
      int i;
      if ((this.zzbaG != null) && (this.zzbaG.length > 0))
      {
        i = 0;
        while (i < this.zzbaG.length)
        {
          paramzzsn.zza(1, this.zzbaG[i]);
          i += 1;
        }
      }
      if ((this.zzbaH != null) && (this.zzbaH.length > 0))
      {
        i = j;
        while (i < this.zzbaH.length)
        {
          paramzzsn.zza(2, this.zzbaH[i]);
          i += 1;
        }
      }
      super.writeTo(paramzzsn);
    }
    
    public zzf zzDY()
    {
      this.zzbaG = zzsx.zzbux;
      this.zzbaH = zzsx.zzbux;
      this.zzbuu = -1;
      return this;
    }
    
    public zzf zzH(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        int j;
        long[] arrayOfLong;
        int k;
        switch (i)
        {
        default: 
          if (zzsx.zzb(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          j = zzsx.zzc(paramzzsm, 8);
          if (this.zzbaG == null) {}
          for (i = 0;; i = this.zzbaG.length)
          {
            arrayOfLong = new long[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbaG, 0, arrayOfLong, 0, i);
              j = i;
            }
            while (j < arrayOfLong.length - 1)
            {
              arrayOfLong[j] = paramzzsm.zzIZ();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfLong[j] = paramzzsm.zzIZ();
          this.zzbaG = arrayOfLong;
          break;
        case 10: 
          k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0)
          {
            paramzzsm.zzIZ();
            j += 1;
          }
          paramzzsm.zzms(i);
          if (this.zzbaG == null) {}
          for (i = 0;; i = this.zzbaG.length)
          {
            arrayOfLong = new long[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbaG, 0, arrayOfLong, 0, i);
              j = i;
            }
            while (j < arrayOfLong.length)
            {
              arrayOfLong[j] = paramzzsm.zzIZ();
              j += 1;
            }
          }
          this.zzbaG = arrayOfLong;
          paramzzsm.zzmr(k);
          break;
        case 16: 
          j = zzsx.zzc(paramzzsm, 16);
          if (this.zzbaH == null) {}
          for (i = 0;; i = this.zzbaH.length)
          {
            arrayOfLong = new long[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbaH, 0, arrayOfLong, 0, i);
              j = i;
            }
            while (j < arrayOfLong.length - 1)
            {
              arrayOfLong[j] = paramzzsm.zzIZ();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfLong[j] = paramzzsm.zzIZ();
          this.zzbaH = arrayOfLong;
          break;
        case 18: 
          k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0)
          {
            paramzzsm.zzIZ();
            j += 1;
          }
          paramzzsm.zzms(i);
          if (this.zzbaH == null) {}
          for (i = 0;; i = this.zzbaH.length)
          {
            arrayOfLong = new long[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbaH, 0, arrayOfLong, 0, i);
              j = i;
            }
            while (j < arrayOfLong.length)
            {
              arrayOfLong[j] = paramzzsm.zzIZ();
              j += 1;
            }
          }
          this.zzbaH = arrayOfLong;
          paramzzsm.zzmr(k);
        }
      }
    }
    
    protected int zzz()
    {
      int m = 0;
      int k = super.zzz();
      int j;
      if ((this.zzbaG != null) && (this.zzbaG.length > 0))
      {
        i = 0;
        j = 0;
        while (i < this.zzbaG.length)
        {
          j += zzsn.zzar(this.zzbaG[i]);
          i += 1;
        }
      }
      for (int i = k + j + this.zzbaG.length * 1;; i = k)
      {
        j = i;
        if (this.zzbaH != null)
        {
          j = i;
          if (this.zzbaH.length > 0)
          {
            k = 0;
            j = m;
            while (j < this.zzbaH.length)
            {
              k += zzsn.zzar(this.zzbaH[j]);
              j += 1;
            }
            j = i + k + this.zzbaH.length * 1;
          }
        }
        return j;
      }
    }
  }
  
  public static final class zzg
    extends zzsu
  {
    private static volatile zzg[] zzbaI;
    public String name;
    public Float zzaZo;
    public String zzamJ;
    public Long zzbaJ;
    public Long zzbai;
    
    public zzg()
    {
      zzEa();
    }
    
    public static zzg[] zzDZ()
    {
      if (zzbaI == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzbaI == null) {
          zzbaI = new zzg[0];
        }
        return zzbaI;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      if (paramObject == this) {}
      do
      {
        do
        {
          return true;
          if (!(paramObject instanceof zzg)) {
            return false;
          }
          paramObject = (zzg)paramObject;
          if (this.zzbaJ == null)
          {
            if (((zzg)paramObject).zzbaJ != null) {
              return false;
            }
          }
          else if (!this.zzbaJ.equals(((zzg)paramObject).zzbaJ)) {
            return false;
          }
          if (this.name == null)
          {
            if (((zzg)paramObject).name != null) {
              return false;
            }
          }
          else if (!this.name.equals(((zzg)paramObject).name)) {
            return false;
          }
          if (this.zzamJ == null)
          {
            if (((zzg)paramObject).zzamJ != null) {
              return false;
            }
          }
          else if (!this.zzamJ.equals(((zzg)paramObject).zzamJ)) {
            return false;
          }
          if (this.zzbai == null)
          {
            if (((zzg)paramObject).zzbai != null) {
              return false;
            }
          }
          else if (!this.zzbai.equals(((zzg)paramObject).zzbai)) {
            return false;
          }
          if (this.zzaZo != null) {
            break;
          }
        } while (((zzg)paramObject).zzaZo == null);
        return false;
      } while (this.zzaZo.equals(((zzg)paramObject).zzaZo));
      return false;
    }
    
    public int hashCode()
    {
      int n = 0;
      int i1 = getClass().getName().hashCode();
      int i;
      int j;
      label33:
      int k;
      label42:
      int m;
      if (this.zzbaJ == null)
      {
        i = 0;
        if (this.name != null) {
          break label104;
        }
        j = 0;
        if (this.zzamJ != null) {
          break label115;
        }
        k = 0;
        if (this.zzbai != null) {
          break label126;
        }
        m = 0;
        label52:
        if (this.zzaZo != null) {
          break label138;
        }
      }
      for (;;)
      {
        return (m + (k + (j + (i + (i1 + 527) * 31) * 31) * 31) * 31) * 31 + n;
        i = this.zzbaJ.hashCode();
        break;
        label104:
        j = this.name.hashCode();
        break label33;
        label115:
        k = this.zzamJ.hashCode();
        break label42;
        label126:
        m = this.zzbai.hashCode();
        break label52;
        label138:
        n = this.zzaZo.hashCode();
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (this.zzbaJ != null) {
        paramzzsn.zzb(1, this.zzbaJ.longValue());
      }
      if (this.name != null) {
        paramzzsn.zzn(2, this.name);
      }
      if (this.zzamJ != null) {
        paramzzsn.zzn(3, this.zzamJ);
      }
      if (this.zzbai != null) {
        paramzzsn.zzb(4, this.zzbai.longValue());
      }
      if (this.zzaZo != null) {
        paramzzsn.zzb(5, this.zzaZo.floatValue());
      }
      super.writeTo(paramzzsn);
    }
    
    public zzg zzEa()
    {
      this.zzbaJ = null;
      this.name = null;
      this.zzamJ = null;
      this.zzbai = null;
      this.zzaZo = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zzg zzI(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        switch (i)
        {
        default: 
          if (zzsx.zzb(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          this.zzbaJ = Long.valueOf(paramzzsm.zzJa());
          break;
        case 18: 
          this.name = paramzzsm.readString();
          break;
        case 26: 
          this.zzamJ = paramzzsm.readString();
          break;
        case 32: 
          this.zzbai = Long.valueOf(paramzzsm.zzJa());
          break;
        case 45: 
          this.zzaZo = Float.valueOf(paramzzsm.readFloat());
        }
      }
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (this.zzbaJ != null) {
        i = j + zzsn.zzd(1, this.zzbaJ.longValue());
      }
      j = i;
      if (this.name != null) {
        j = i + zzsn.zzo(2, this.name);
      }
      i = j;
      if (this.zzamJ != null) {
        i = j + zzsn.zzo(3, this.zzamJ);
      }
      j = i;
      if (this.zzbai != null) {
        j = i + zzsn.zzd(4, this.zzbai.longValue());
      }
      i = j;
      if (this.zzaZo != null) {
        i = j + zzsn.zzc(5, this.zzaZo.floatValue());
      }
      return i;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzqb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */