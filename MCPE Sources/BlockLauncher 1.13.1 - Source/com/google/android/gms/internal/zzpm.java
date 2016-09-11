package com.google.android.gms.internal;

import java.io.IOException;

public abstract interface zzpm
{
  public static final class zza
    extends zzso<zza>
  {
    public zza[] zzaMo;
    
    public zza()
    {
      zzyp();
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
          } while (!(paramObject instanceof zza));
          paramObject = (zza)paramObject;
          bool1 = bool2;
        } while (!zzss.equals(this.zzaMo, ((zza)paramObject).zzaMo));
        if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
          break label79;
        }
        if (((zza)paramObject).zzbuj == null) {
          break;
        }
        bool1 = bool2;
      } while (!((zza)paramObject).zzbuj.isEmpty());
      return true;
      label79:
      return this.zzbuj.equals(((zza)paramObject).zzbuj);
    }
    
    public int hashCode()
    {
      int j = getClass().getName().hashCode();
      int k = zzss.hashCode(this.zzaMo);
      if ((this.zzbuj == null) || (this.zzbuj.isEmpty())) {}
      for (int i = 0;; i = this.zzbuj.hashCode()) {
        return i + ((j + 527) * 31 + k) * 31;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if ((this.zzaMo != null) && (this.zzaMo.length > 0))
      {
        int i = 0;
        while (i < this.zzaMo.length)
        {
          zza localzza = this.zzaMo[i];
          if (localzza != null) {
            paramzzsn.zza(1, localzza);
          }
          i += 1;
        }
      }
      super.writeTo(paramzzsn);
    }
    
    public zza zzo(zzsm paramzzsm)
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
        case 10: 
          int j = zzsx.zzc(paramzzsm, 10);
          if (this.zzaMo == null) {}
          zza[] arrayOfzza;
          for (i = 0;; i = this.zzaMo.length)
          {
            arrayOfzza = new zza[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzaMo, 0, arrayOfzza, 0, i);
              j = i;
            }
            while (j < arrayOfzza.length - 1)
            {
              arrayOfzza[j] = new zza();
              paramzzsm.zza(arrayOfzza[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfzza[j] = new zza();
          paramzzsm.zza(arrayOfzza[j]);
          this.zzaMo = arrayOfzza;
        }
      }
    }
    
    public zza zzyp()
    {
      this.zzaMo = zza.zzyq();
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    protected int zzz()
    {
      int i = super.zzz();
      int k = i;
      if (this.zzaMo != null)
      {
        k = i;
        if (this.zzaMo.length > 0)
        {
          int j = 0;
          for (;;)
          {
            k = i;
            if (j >= this.zzaMo.length) {
              break;
            }
            zza localzza = this.zzaMo[j];
            k = i;
            if (localzza != null) {
              k = i + zzsn.zzc(1, localzza);
            }
            j += 1;
            i = k;
          }
        }
      }
      return k;
    }
    
    public static final class zza
      extends zzso<zza>
    {
      private static volatile zza[] zzaMp;
      public int viewId;
      public String zzaMq;
      public String zzaMr;
      
      public zza()
      {
        zzyr();
      }
      
      public static zza[] zzyq()
      {
        if (zzaMp == null) {}
        synchronized (zzss.zzbut)
        {
          if (zzaMp == null) {
            zzaMp = new zza[0];
          }
          return zzaMp;
        }
      }
      
      public boolean equals(Object paramObject)
      {
        boolean bool2 = false;
        boolean bool1;
        if (paramObject == this) {
          bool1 = true;
        }
        label41:
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
            if (this.zzaMq != null) {
              break;
            }
            bool1 = bool2;
          } while (((zza)paramObject).zzaMq != null);
          if (this.zzaMr != null) {
            break label124;
          }
          bool1 = bool2;
        } while (((zza)paramObject).zzaMr != null);
        label124:
        while (this.zzaMr.equals(((zza)paramObject).zzaMr))
        {
          bool1 = bool2;
          if (this.viewId != ((zza)paramObject).viewId) {
            break;
          }
          if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
            break label140;
          }
          if (((zza)paramObject).zzbuj != null)
          {
            bool1 = bool2;
            if (!((zza)paramObject).zzbuj.isEmpty()) {
              break;
            }
          }
          return true;
          if (this.zzaMq.equals(((zza)paramObject).zzaMq)) {
            break label41;
          }
          return false;
        }
        return false;
        label140:
        return this.zzbuj.equals(((zza)paramObject).zzbuj);
      }
      
      public int hashCode()
      {
        int m = 0;
        int n = getClass().getName().hashCode();
        int i;
        int j;
        label33:
        int i1;
        if (this.zzaMq == null)
        {
          i = 0;
          if (this.zzaMr != null) {
            break label101;
          }
          j = 0;
          i1 = this.viewId;
          k = m;
          if (this.zzbuj != null) {
            if (!this.zzbuj.isEmpty()) {
              break label112;
            }
          }
        }
        label101:
        label112:
        for (int k = m;; k = this.zzbuj.hashCode())
        {
          return ((j + (i + (n + 527) * 31) * 31) * 31 + i1) * 31 + k;
          i = this.zzaMq.hashCode();
          break;
          j = this.zzaMr.hashCode();
          break label33;
        }
      }
      
      public void writeTo(zzsn paramzzsn)
        throws IOException
      {
        if (!this.zzaMq.equals("")) {
          paramzzsn.zzn(1, this.zzaMq);
        }
        if (!this.zzaMr.equals("")) {
          paramzzsn.zzn(2, this.zzaMr);
        }
        if (this.viewId != 0) {
          paramzzsn.zzA(3, this.viewId);
        }
        super.writeTo(paramzzsn);
      }
      
      public zza zzp(zzsm paramzzsm)
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
          case 10: 
            this.zzaMq = paramzzsm.readString();
            break;
          case 18: 
            this.zzaMr = paramzzsm.readString();
            break;
          case 24: 
            this.viewId = paramzzsm.zzJb();
          }
        }
      }
      
      public zza zzyr()
      {
        this.zzaMq = "";
        this.zzaMr = "";
        this.viewId = 0;
        this.zzbuj = null;
        this.zzbuu = -1;
        return this;
      }
      
      protected int zzz()
      {
        int j = super.zzz();
        int i = j;
        if (!this.zzaMq.equals("")) {
          i = j + zzsn.zzo(1, this.zzaMq);
        }
        j = i;
        if (!this.zzaMr.equals("")) {
          j = i + zzsn.zzo(2, this.zzaMr);
        }
        i = j;
        if (this.viewId != 0) {
          i = j + zzsn.zzC(3, this.viewId);
        }
        return i;
      }
    }
  }
  
  public static final class zzb
    extends zzso<zzb>
  {
    private static volatile zzb[] zzaMs;
    public String name;
    public zzpm.zzd zzaMt;
    
    public zzb()
    {
      zzyt();
    }
    
    public static zzb[] zzys()
    {
      if (zzaMs == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzaMs == null) {
          zzaMs = new zzb[0];
        }
        return zzaMs;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      label41:
      do
      {
        do
        {
          do
          {
            return bool1;
            bool1 = bool2;
          } while (!(paramObject instanceof zzb));
          paramObject = (zzb)paramObject;
          if (this.name != null) {
            break;
          }
          bool1 = bool2;
        } while (((zzb)paramObject).name != null);
        if (this.zzaMt != null) {
          break label111;
        }
        bool1 = bool2;
      } while (((zzb)paramObject).zzaMt != null);
      for (;;)
      {
        if ((this.zzbuj == null) || (this.zzbuj.isEmpty()))
        {
          if (((zzb)paramObject).zzbuj != null)
          {
            bool1 = bool2;
            if (!((zzb)paramObject).zzbuj.isEmpty()) {
              break;
            }
          }
          return true;
          if (this.name.equals(((zzb)paramObject).name)) {
            break label41;
          }
          return false;
          label111:
          if (!this.zzaMt.equals(((zzb)paramObject).zzaMt)) {
            return false;
          }
        }
      }
      return this.zzbuj.equals(((zzb)paramObject).zzbuj);
    }
    
    public int hashCode()
    {
      int m = 0;
      int n = getClass().getName().hashCode();
      int i;
      int j;
      if (this.name == null)
      {
        i = 0;
        if (this.zzaMt != null) {
          break label89;
        }
        j = 0;
        label33:
        k = m;
        if (this.zzbuj != null) {
          if (!this.zzbuj.isEmpty()) {
            break label100;
          }
        }
      }
      label89:
      label100:
      for (int k = m;; k = this.zzbuj.hashCode())
      {
        return (j + (i + (n + 527) * 31) * 31) * 31 + k;
        i = this.name.hashCode();
        break;
        j = this.zzaMt.hashCode();
        break label33;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (!this.name.equals("")) {
        paramzzsn.zzn(1, this.name);
      }
      if (this.zzaMt != null) {
        paramzzsn.zza(2, this.zzaMt);
      }
      super.writeTo(paramzzsn);
    }
    
    public zzb zzq(zzsm paramzzsm)
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
        case 10: 
          this.name = paramzzsm.readString();
          break;
        case 18: 
          if (this.zzaMt == null) {
            this.zzaMt = new zzpm.zzd();
          }
          paramzzsm.zza(this.zzaMt);
        }
      }
    }
    
    public zzb zzyt()
    {
      this.name = "";
      this.zzaMt = null;
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (!this.name.equals("")) {
        i = j + zzsn.zzo(1, this.name);
      }
      j = i;
      if (this.zzaMt != null) {
        j = i + zzsn.zzc(2, this.zzaMt);
      }
      return j;
    }
  }
  
  public static final class zzc
    extends zzso<zzc>
  {
    public String type;
    public zzpm.zzb[] zzaMu;
    
    public zzc()
    {
      zzyu();
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
          return bool1;
          bool1 = bool2;
        } while (!(paramObject instanceof zzc));
        paramObject = (zzc)paramObject;
        if (this.type != null) {
          break;
        }
        bool1 = bool2;
      } while (((zzc)paramObject).type != null);
      while (this.type.equals(((zzc)paramObject).type))
      {
        bool1 = bool2;
        if (!zzss.equals(this.zzaMu, ((zzc)paramObject).zzaMu)) {
          break;
        }
        if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
          break label111;
        }
        if (((zzc)paramObject).zzbuj != null)
        {
          bool1 = bool2;
          if (!((zzc)paramObject).zzbuj.isEmpty()) {
            break;
          }
        }
        return true;
      }
      return false;
      label111:
      return this.zzbuj.equals(((zzc)paramObject).zzbuj);
    }
    
    public int hashCode()
    {
      int k = 0;
      int m = getClass().getName().hashCode();
      int i;
      int n;
      if (this.type == null)
      {
        i = 0;
        n = zzss.hashCode(this.zzaMu);
        j = k;
        if (this.zzbuj != null) {
          if (!this.zzbuj.isEmpty()) {
            break label87;
          }
        }
      }
      label87:
      for (int j = k;; j = this.zzbuj.hashCode())
      {
        return ((i + (m + 527) * 31) * 31 + n) * 31 + j;
        i = this.type.hashCode();
        break;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (!this.type.equals("")) {
        paramzzsn.zzn(1, this.type);
      }
      if ((this.zzaMu != null) && (this.zzaMu.length > 0))
      {
        int i = 0;
        while (i < this.zzaMu.length)
        {
          zzpm.zzb localzzb = this.zzaMu[i];
          if (localzzb != null) {
            paramzzsn.zza(2, localzzb);
          }
          i += 1;
        }
      }
      super.writeTo(paramzzsn);
    }
    
    public zzc zzr(zzsm paramzzsm)
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
        case 10: 
          this.type = paramzzsm.readString();
          break;
        case 18: 
          int j = zzsx.zzc(paramzzsm, 18);
          if (this.zzaMu == null) {}
          zzpm.zzb[] arrayOfzzb;
          for (i = 0;; i = this.zzaMu.length)
          {
            arrayOfzzb = new zzpm.zzb[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzaMu, 0, arrayOfzzb, 0, i);
              j = i;
            }
            while (j < arrayOfzzb.length - 1)
            {
              arrayOfzzb[j] = new zzpm.zzb();
              paramzzsm.zza(arrayOfzzb[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfzzb[j] = new zzpm.zzb();
          paramzzsm.zza(arrayOfzzb[j]);
          this.zzaMu = arrayOfzzb;
        }
      }
    }
    
    public zzc zzyu()
    {
      this.type = "";
      this.zzaMu = zzpm.zzb.zzys();
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (!this.type.equals("")) {
        i = j + zzsn.zzo(1, this.type);
      }
      j = i;
      if (this.zzaMu != null)
      {
        j = i;
        if (this.zzaMu.length > 0)
        {
          j = 0;
          while (j < this.zzaMu.length)
          {
            zzpm.zzb localzzb = this.zzaMu[j];
            int k = i;
            if (localzzb != null) {
              k = i + zzsn.zzc(2, localzzb);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      return j;
    }
  }
  
  public static final class zzd
    extends zzso<zzd>
  {
    public boolean zzaMv;
    public long zzaMw;
    public double zzaMx;
    public zzpm.zzc zzaMy;
    public String zzamJ;
    
    public zzd()
    {
      zzyv();
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      label54:
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
                } while (!(paramObject instanceof zzd));
                paramObject = (zzd)paramObject;
                bool1 = bool2;
              } while (this.zzaMv != ((zzd)paramObject).zzaMv);
              if (this.zzamJ != null) {
                break;
              }
              bool1 = bool2;
            } while (((zzd)paramObject).zzamJ != null);
            bool1 = bool2;
          } while (this.zzaMw != ((zzd)paramObject).zzaMw);
          bool1 = bool2;
        } while (Double.doubleToLongBits(this.zzaMx) != Double.doubleToLongBits(((zzd)paramObject).zzaMx));
        if (this.zzaMy != null) {
          break label158;
        }
        bool1 = bool2;
      } while (((zzd)paramObject).zzaMy != null);
      for (;;)
      {
        if ((this.zzbuj == null) || (this.zzbuj.isEmpty()))
        {
          if (((zzd)paramObject).zzbuj != null)
          {
            bool1 = bool2;
            if (!((zzd)paramObject).zzbuj.isEmpty()) {
              break;
            }
          }
          return true;
          if (this.zzamJ.equals(((zzd)paramObject).zzamJ)) {
            break label54;
          }
          return false;
          label158:
          if (!this.zzaMy.equals(((zzd)paramObject).zzaMy)) {
            return false;
          }
        }
      }
      return this.zzbuj.equals(((zzd)paramObject).zzbuj);
    }
    
    public int hashCode()
    {
      int n = 0;
      int i1 = getClass().getName().hashCode();
      int i;
      int j;
      label35:
      int i2;
      int i3;
      int k;
      if (this.zzaMv)
      {
        i = 1231;
        if (this.zzamJ != null) {
          break label151;
        }
        j = 0;
        i2 = (int)(this.zzaMw ^ this.zzaMw >>> 32);
        long l = Double.doubleToLongBits(this.zzaMx);
        i3 = (int)(l ^ l >>> 32);
        if (this.zzaMy != null) {
          break label162;
        }
        k = 0;
        label79:
        m = n;
        if (this.zzbuj != null) {
          if (!this.zzbuj.isEmpty()) {
            break label173;
          }
        }
      }
      label151:
      label162:
      label173:
      for (int m = n;; m = this.zzbuj.hashCode())
      {
        return (k + (((j + (i + (i1 + 527) * 31) * 31) * 31 + i2) * 31 + i3) * 31) * 31 + m;
        i = 1237;
        break;
        j = this.zzamJ.hashCode();
        break label35;
        k = this.zzaMy.hashCode();
        break label79;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (this.zzaMv) {
        paramzzsn.zze(1, this.zzaMv);
      }
      if (!this.zzamJ.equals("")) {
        paramzzsn.zzn(2, this.zzamJ);
      }
      if (this.zzaMw != 0L) {
        paramzzsn.zzb(3, this.zzaMw);
      }
      if (Double.doubleToLongBits(this.zzaMx) != Double.doubleToLongBits(0.0D)) {
        paramzzsn.zza(4, this.zzaMx);
      }
      if (this.zzaMy != null) {
        paramzzsn.zza(5, this.zzaMy);
      }
      super.writeTo(paramzzsn);
    }
    
    public zzd zzs(zzsm paramzzsm)
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
          this.zzaMv = paramzzsm.zzJc();
          break;
        case 18: 
          this.zzamJ = paramzzsm.readString();
          break;
        case 24: 
          this.zzaMw = paramzzsm.zzJa();
          break;
        case 33: 
          this.zzaMx = paramzzsm.readDouble();
          break;
        case 42: 
          if (this.zzaMy == null) {
            this.zzaMy = new zzpm.zzc();
          }
          paramzzsm.zza(this.zzaMy);
        }
      }
    }
    
    public zzd zzyv()
    {
      this.zzaMv = false;
      this.zzamJ = "";
      this.zzaMw = 0L;
      this.zzaMx = 0.0D;
      this.zzaMy = null;
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (this.zzaMv) {
        i = j + zzsn.zzf(1, this.zzaMv);
      }
      j = i;
      if (!this.zzamJ.equals("")) {
        j = i + zzsn.zzo(2, this.zzamJ);
      }
      i = j;
      if (this.zzaMw != 0L) {
        i = j + zzsn.zzd(3, this.zzaMw);
      }
      j = i;
      if (Double.doubleToLongBits(this.zzaMx) != Double.doubleToLongBits(0.0D)) {
        j = i + zzsn.zzb(4, this.zzaMx);
      }
      i = j;
      if (this.zzaMy != null) {
        i = j + zzsn.zzc(5, this.zzaMy);
      }
      return i;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzpm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */