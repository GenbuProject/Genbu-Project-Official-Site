package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public abstract interface zzsz
{
  public static final class zza
    extends zzso<zza>
  {
    public String[] zzbuI;
    public String[] zzbuJ;
    public int[] zzbuK;
    public long[] zzbuL;
    
    public zza()
    {
      zzJC();
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
                } while (!(paramObject instanceof zza));
                paramObject = (zza)paramObject;
                bool1 = bool2;
              } while (!zzss.equals(this.zzbuI, ((zza)paramObject).zzbuI));
              bool1 = bool2;
            } while (!zzss.equals(this.zzbuJ, ((zza)paramObject).zzbuJ));
            bool1 = bool2;
          } while (!zzss.equals(this.zzbuK, ((zza)paramObject).zzbuK));
          bool1 = bool2;
        } while (!zzss.equals(this.zzbuL, ((zza)paramObject).zzbuL));
        if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
          break label127;
        }
        if (((zza)paramObject).zzbuj == null) {
          break;
        }
        bool1 = bool2;
      } while (!((zza)paramObject).zzbuj.isEmpty());
      return true;
      label127:
      return this.zzbuj.equals(((zza)paramObject).zzbuj);
    }
    
    public int hashCode()
    {
      int j = getClass().getName().hashCode();
      int k = zzss.hashCode(this.zzbuI);
      int m = zzss.hashCode(this.zzbuJ);
      int n = zzss.hashCode(this.zzbuK);
      int i1 = zzss.hashCode(this.zzbuL);
      if ((this.zzbuj == null) || (this.zzbuj.isEmpty())) {}
      for (int i = 0;; i = this.zzbuj.hashCode()) {
        return i + (((((j + 527) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      int j = 0;
      int i;
      String str;
      if ((this.zzbuI != null) && (this.zzbuI.length > 0))
      {
        i = 0;
        while (i < this.zzbuI.length)
        {
          str = this.zzbuI[i];
          if (str != null) {
            paramzzsn.zzn(1, str);
          }
          i += 1;
        }
      }
      if ((this.zzbuJ != null) && (this.zzbuJ.length > 0))
      {
        i = 0;
        while (i < this.zzbuJ.length)
        {
          str = this.zzbuJ[i];
          if (str != null) {
            paramzzsn.zzn(2, str);
          }
          i += 1;
        }
      }
      if ((this.zzbuK != null) && (this.zzbuK.length > 0))
      {
        i = 0;
        while (i < this.zzbuK.length)
        {
          paramzzsn.zzA(3, this.zzbuK[i]);
          i += 1;
        }
      }
      if ((this.zzbuL != null) && (this.zzbuL.length > 0))
      {
        i = j;
        while (i < this.zzbuL.length)
        {
          paramzzsn.zzb(4, this.zzbuL[i]);
          i += 1;
        }
      }
      super.writeTo(paramzzsn);
    }
    
    public zza zzJC()
    {
      this.zzbuI = zzsx.zzbuB;
      this.zzbuJ = zzsx.zzbuB;
      this.zzbuK = zzsx.zzbuw;
      this.zzbuL = zzsx.zzbux;
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zza zzS(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        int j;
        Object localObject;
        int k;
        switch (i)
        {
        default: 
          if (zza(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          j = zzsx.zzc(paramzzsm, 10);
          if (this.zzbuI == null) {}
          for (i = 0;; i = this.zzbuI.length)
          {
            localObject = new String[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbuI, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = paramzzsm.readString();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = paramzzsm.readString();
          this.zzbuI = ((String[])localObject);
          break;
        case 18: 
          j = zzsx.zzc(paramzzsm, 18);
          if (this.zzbuJ == null) {}
          for (i = 0;; i = this.zzbuJ.length)
          {
            localObject = new String[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbuJ, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = paramzzsm.readString();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = paramzzsm.readString();
          this.zzbuJ = ((String[])localObject);
          break;
        case 24: 
          j = zzsx.zzc(paramzzsm, 24);
          if (this.zzbuK == null) {}
          for (i = 0;; i = this.zzbuK.length)
          {
            localObject = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbuK, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = paramzzsm.zzJb();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = paramzzsm.zzJb();
          this.zzbuK = ((int[])localObject);
          break;
        case 26: 
          k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0)
          {
            paramzzsm.zzJb();
            j += 1;
          }
          paramzzsm.zzms(i);
          if (this.zzbuK == null) {}
          for (i = 0;; i = this.zzbuK.length)
          {
            localObject = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbuK, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length)
            {
              localObject[j] = paramzzsm.zzJb();
              j += 1;
            }
          }
          this.zzbuK = ((int[])localObject);
          paramzzsm.zzmr(k);
          break;
        case 32: 
          j = zzsx.zzc(paramzzsm, 32);
          if (this.zzbuL == null) {}
          for (i = 0;; i = this.zzbuL.length)
          {
            localObject = new long[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbuL, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = paramzzsm.zzJa();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = paramzzsm.zzJa();
          this.zzbuL = ((long[])localObject);
          break;
        case 34: 
          k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0)
          {
            paramzzsm.zzJa();
            j += 1;
          }
          paramzzsm.zzms(i);
          if (this.zzbuL == null) {}
          for (i = 0;; i = this.zzbuL.length)
          {
            localObject = new long[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbuL, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length)
            {
              localObject[j] = paramzzsm.zzJa();
              j += 1;
            }
          }
          this.zzbuL = ((long[])localObject);
          paramzzsm.zzmr(k);
        }
      }
    }
    
    protected int zzz()
    {
      int i2 = 0;
      int i1 = super.zzz();
      int j;
      int k;
      String str;
      int n;
      int m;
      if ((this.zzbuI != null) && (this.zzbuI.length > 0))
      {
        i = 0;
        j = 0;
        for (k = 0; i < this.zzbuI.length; k = m)
        {
          str = this.zzbuI[i];
          n = j;
          m = k;
          if (str != null)
          {
            m = k + 1;
            n = j + zzsn.zzgO(str);
          }
          i += 1;
          j = n;
        }
      }
      for (int i = i1 + j + k * 1;; i = i1)
      {
        j = i;
        if (this.zzbuJ != null)
        {
          j = i;
          if (this.zzbuJ.length > 0)
          {
            j = 0;
            k = 0;
            for (m = 0; j < this.zzbuJ.length; m = n)
            {
              str = this.zzbuJ[j];
              i1 = k;
              n = m;
              if (str != null)
              {
                n = m + 1;
                i1 = k + zzsn.zzgO(str);
              }
              j += 1;
              k = i1;
            }
            j = i + k + m * 1;
          }
        }
        i = j;
        if (this.zzbuK != null)
        {
          i = j;
          if (this.zzbuK.length > 0)
          {
            i = 0;
            k = 0;
            while (i < this.zzbuK.length)
            {
              k += zzsn.zzmx(this.zzbuK[i]);
              i += 1;
            }
            i = j + k + this.zzbuK.length * 1;
          }
        }
        j = i;
        if (this.zzbuL != null)
        {
          j = i;
          if (this.zzbuL.length > 0)
          {
            k = 0;
            j = i2;
            while (j < this.zzbuL.length)
            {
              k += zzsn.zzas(this.zzbuL[j]);
              j += 1;
            }
            j = i + k + this.zzbuL.length * 1;
          }
        }
        return j;
      }
    }
  }
  
  public static final class zzb
    extends zzso<zzb>
  {
    public String version;
    public int zzbuM;
    public String zzbuN;
    
    public zzb()
    {
      zzJD();
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
              return bool1;
              bool1 = bool2;
            } while (!(paramObject instanceof zzb));
            paramObject = (zzb)paramObject;
            bool1 = bool2;
          } while (this.zzbuM != ((zzb)paramObject).zzbuM);
          if (this.zzbuN != null) {
            break;
          }
          bool1 = bool2;
        } while (((zzb)paramObject).zzbuN != null);
        if (this.version != null) {
          break label124;
        }
        bool1 = bool2;
      } while (((zzb)paramObject).version != null);
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
          if (this.zzbuN.equals(((zzb)paramObject).zzbuN)) {
            break label54;
          }
          return false;
          label124:
          if (!this.version.equals(((zzb)paramObject).version)) {
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
      int i1 = this.zzbuM;
      int i;
      int j;
      if (this.zzbuN == null)
      {
        i = 0;
        if (this.version != null) {
          break label101;
        }
        j = 0;
        label39:
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
        return (j + (i + ((n + 527) * 31 + i1) * 31) * 31) * 31 + k;
        i = this.zzbuN.hashCode();
        break;
        j = this.version.hashCode();
        break label39;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (this.zzbuM != 0) {
        paramzzsn.zzA(1, this.zzbuM);
      }
      if (!this.zzbuN.equals("")) {
        paramzzsn.zzn(2, this.zzbuN);
      }
      if (!this.version.equals("")) {
        paramzzsn.zzn(3, this.version);
      }
      super.writeTo(paramzzsn);
    }
    
    public zzb zzJD()
    {
      this.zzbuM = 0;
      this.zzbuN = "";
      this.version = "";
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zzb zzT(zzsm paramzzsm)
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
          i = paramzzsm.zzJb();
          switch (i)
          {
          default: 
            break;
          case 0: 
          case 1: 
          case 2: 
          case 3: 
          case 4: 
          case 5: 
          case 6: 
          case 7: 
          case 8: 
          case 9: 
          case 10: 
          case 11: 
          case 12: 
          case 13: 
          case 14: 
          case 15: 
          case 16: 
          case 17: 
          case 18: 
          case 19: 
          case 20: 
          case 21: 
          case 22: 
          case 23: 
          case 24: 
          case 25: 
          case 26: 
            this.zzbuM = i;
          }
          break;
        case 18: 
          this.zzbuN = paramzzsm.readString();
          break;
        case 26: 
          this.version = paramzzsm.readString();
        }
      }
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (this.zzbuM != 0) {
        i = j + zzsn.zzC(1, this.zzbuM);
      }
      j = i;
      if (!this.zzbuN.equals("")) {
        j = i + zzsn.zzo(2, this.zzbuN);
      }
      i = j;
      if (!this.version.equals("")) {
        i = j + zzsn.zzo(3, this.version);
      }
      return i;
    }
  }
  
  public static final class zzc
    extends zzso<zzc>
  {
    public byte[] zzbuO;
    public byte[][] zzbuP;
    public boolean zzbuQ;
    
    public zzc()
    {
      zzJE();
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
                return bool1;
                bool1 = bool2;
              } while (!(paramObject instanceof zzc));
              paramObject = (zzc)paramObject;
              bool1 = bool2;
            } while (!Arrays.equals(this.zzbuO, ((zzc)paramObject).zzbuO));
            bool1 = bool2;
          } while (!zzss.zza(this.zzbuP, ((zzc)paramObject).zzbuP));
          bool1 = bool2;
        } while (this.zzbuQ != ((zzc)paramObject).zzbuQ);
        if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
          break label108;
        }
        if (((zzc)paramObject).zzbuj == null) {
          break;
        }
        bool1 = bool2;
      } while (!((zzc)paramObject).zzbuj.isEmpty());
      return true;
      label108:
      return this.zzbuj.equals(((zzc)paramObject).zzbuj);
    }
    
    public int hashCode()
    {
      int k = getClass().getName().hashCode();
      int m = Arrays.hashCode(this.zzbuO);
      int n = zzss.zza(this.zzbuP);
      int i;
      if (this.zzbuQ)
      {
        i = 1231;
        if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
          break label94;
        }
      }
      label94:
      for (int j = 0;; j = this.zzbuj.hashCode())
      {
        return j + (i + (((k + 527) * 31 + m) * 31 + n) * 31) * 31;
        i = 1237;
        break;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (!Arrays.equals(this.zzbuO, zzsx.zzbuD)) {
        paramzzsn.zza(1, this.zzbuO);
      }
      if ((this.zzbuP != null) && (this.zzbuP.length > 0))
      {
        int i = 0;
        while (i < this.zzbuP.length)
        {
          byte[] arrayOfByte = this.zzbuP[i];
          if (arrayOfByte != null) {
            paramzzsn.zza(2, arrayOfByte);
          }
          i += 1;
        }
      }
      if (this.zzbuQ) {
        paramzzsn.zze(3, this.zzbuQ);
      }
      super.writeTo(paramzzsn);
    }
    
    public zzc zzJE()
    {
      this.zzbuO = zzsx.zzbuD;
      this.zzbuP = zzsx.zzbuC;
      this.zzbuQ = false;
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zzc zzU(zzsm paramzzsm)
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
          this.zzbuO = paramzzsm.readBytes();
          break;
        case 18: 
          int j = zzsx.zzc(paramzzsm, 18);
          if (this.zzbuP == null) {}
          byte[][] arrayOfByte;
          for (i = 0;; i = this.zzbuP.length)
          {
            arrayOfByte = new byte[j + i][];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbuP, 0, arrayOfByte, 0, i);
              j = i;
            }
            while (j < arrayOfByte.length - 1)
            {
              arrayOfByte[j] = paramzzsm.readBytes();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfByte[j] = paramzzsm.readBytes();
          this.zzbuP = arrayOfByte;
          break;
        case 24: 
          this.zzbuQ = paramzzsm.zzJc();
        }
      }
    }
    
    protected int zzz()
    {
      int n = 0;
      int j = super.zzz();
      int i = j;
      if (!Arrays.equals(this.zzbuO, zzsx.zzbuD)) {
        i = j + zzsn.zzb(1, this.zzbuO);
      }
      j = i;
      if (this.zzbuP != null)
      {
        j = i;
        if (this.zzbuP.length > 0)
        {
          int k = 0;
          int m = 0;
          j = n;
          while (j < this.zzbuP.length)
          {
            byte[] arrayOfByte = this.zzbuP[j];
            int i1 = k;
            n = m;
            if (arrayOfByte != null)
            {
              n = m + 1;
              i1 = k + zzsn.zzG(arrayOfByte);
            }
            j += 1;
            k = i1;
            m = n;
          }
          j = i + k + m * 1;
        }
      }
      i = j;
      if (this.zzbuQ) {
        i = j + zzsn.zzf(3, this.zzbuQ);
      }
      return i;
    }
  }
  
  public static final class zzd
    extends zzso<zzd>
  {
    public String tag;
    public long zzbuR;
    public long zzbuS;
    public long zzbuT;
    public int zzbuU;
    public boolean zzbuV;
    public zzsz.zze[] zzbuW;
    public zzsz.zzb zzbuX;
    public byte[] zzbuY;
    public byte[] zzbuZ;
    public byte[] zzbva;
    public zzsz.zza zzbvb;
    public String zzbvc;
    public long zzbvd;
    public zzsz.zzc zzbve;
    public byte[] zzbvf;
    public int zzbvg;
    public int[] zzbvh;
    public long zzbvi;
    public int zzob;
    
    public zzd()
    {
      zzJF();
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      label83:
      label154:
      label218:
      label234:
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
                                    } while (this.zzbuR != ((zzd)paramObject).zzbuR);
                                    bool1 = bool2;
                                  } while (this.zzbuS != ((zzd)paramObject).zzbuS);
                                  bool1 = bool2;
                                } while (this.zzbuT != ((zzd)paramObject).zzbuT);
                                if (this.tag != null) {
                                  break;
                                }
                                bool1 = bool2;
                              } while (((zzd)paramObject).tag != null);
                              bool1 = bool2;
                            } while (this.zzbuU != ((zzd)paramObject).zzbuU);
                            bool1 = bool2;
                          } while (this.zzob != ((zzd)paramObject).zzob);
                          bool1 = bool2;
                        } while (this.zzbuV != ((zzd)paramObject).zzbuV);
                        bool1 = bool2;
                      } while (!zzss.equals(this.zzbuW, ((zzd)paramObject).zzbuW));
                      if (this.zzbuX != null) {
                        break label377;
                      }
                      bool1 = bool2;
                    } while (((zzd)paramObject).zzbuX != null);
                    bool1 = bool2;
                  } while (!Arrays.equals(this.zzbuY, ((zzd)paramObject).zzbuY));
                  bool1 = bool2;
                } while (!Arrays.equals(this.zzbuZ, ((zzd)paramObject).zzbuZ));
                bool1 = bool2;
              } while (!Arrays.equals(this.zzbva, ((zzd)paramObject).zzbva));
              if (this.zzbvb != null) {
                break label393;
              }
              bool1 = bool2;
            } while (((zzd)paramObject).zzbvb != null);
            if (this.zzbvc != null) {
              break label409;
            }
            bool1 = bool2;
          } while (((zzd)paramObject).zzbvc != null);
          bool1 = bool2;
        } while (this.zzbvd != ((zzd)paramObject).zzbvd);
        if (this.zzbve != null) {
          break label425;
        }
        bool1 = bool2;
      } while (((zzd)paramObject).zzbve != null);
      label377:
      label393:
      label409:
      label425:
      while (this.zzbve.equals(((zzd)paramObject).zzbve))
      {
        bool1 = bool2;
        if (!Arrays.equals(this.zzbvf, ((zzd)paramObject).zzbvf)) {
          break;
        }
        bool1 = bool2;
        if (this.zzbvg != ((zzd)paramObject).zzbvg) {
          break;
        }
        bool1 = bool2;
        if (!zzss.equals(this.zzbvh, ((zzd)paramObject).zzbvh)) {
          break;
        }
        bool1 = bool2;
        if (this.zzbvi != ((zzd)paramObject).zzbvi) {
          break;
        }
        if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
          break label441;
        }
        if (((zzd)paramObject).zzbuj != null)
        {
          bool1 = bool2;
          if (!((zzd)paramObject).zzbuj.isEmpty()) {
            break;
          }
        }
        return true;
        if (this.tag.equals(((zzd)paramObject).tag)) {
          break label83;
        }
        return false;
        if (this.zzbuX.equals(((zzd)paramObject).zzbuX)) {
          break label154;
        }
        return false;
        if (this.zzbvb.equals(((zzd)paramObject).zzbvb)) {
          break label218;
        }
        return false;
        if (this.zzbvc.equals(((zzd)paramObject).zzbvc)) {
          break label234;
        }
        return false;
      }
      return false;
      label441:
      return this.zzbuj.equals(((zzd)paramObject).zzbuj);
    }
    
    public int hashCode()
    {
      int i3 = 0;
      int i4 = getClass().getName().hashCode();
      int i5 = (int)(this.zzbuR ^ this.zzbuR >>> 32);
      int i6 = (int)(this.zzbuS ^ this.zzbuS >>> 32);
      int i7 = (int)(this.zzbuT ^ this.zzbuT >>> 32);
      int i;
      int i8;
      int i9;
      int j;
      label92:
      int i10;
      int k;
      label110:
      int i11;
      int i12;
      int i13;
      int m;
      label147:
      int n;
      label157:
      int i14;
      int i1;
      label182:
      int i15;
      int i16;
      int i17;
      int i18;
      if (this.tag == null)
      {
        i = 0;
        i8 = this.zzbuU;
        i9 = this.zzob;
        if (!this.zzbuV) {
          break label387;
        }
        j = 1231;
        i10 = zzss.hashCode(this.zzbuW);
        if (this.zzbuX != null) {
          break label394;
        }
        k = 0;
        i11 = Arrays.hashCode(this.zzbuY);
        i12 = Arrays.hashCode(this.zzbuZ);
        i13 = Arrays.hashCode(this.zzbva);
        if (this.zzbvb != null) {
          break label405;
        }
        m = 0;
        if (this.zzbvc != null) {
          break label417;
        }
        n = 0;
        i14 = (int)(this.zzbvd ^ this.zzbvd >>> 32);
        if (this.zzbve != null) {
          break label429;
        }
        i1 = 0;
        i15 = Arrays.hashCode(this.zzbvf);
        i16 = this.zzbvg;
        i17 = zzss.hashCode(this.zzbvh);
        i18 = (int)(this.zzbvi ^ this.zzbvi >>> 32);
        i2 = i3;
        if (this.zzbuj != null) {
          if (!this.zzbuj.isEmpty()) {
            break label441;
          }
        }
      }
      label387:
      label394:
      label405:
      label417:
      label429:
      label441:
      for (int i2 = i3;; i2 = this.zzbuj.hashCode())
      {
        return (((((i1 + ((n + (m + ((((k + ((j + (((i + ((((i4 + 527) * 31 + i5) * 31 + i6) * 31 + i7) * 31) * 31 + i8) * 31 + i9) * 31) * 31 + i10) * 31) * 31 + i11) * 31 + i12) * 31 + i13) * 31) * 31) * 31 + i14) * 31) * 31 + i15) * 31 + i16) * 31 + i17) * 31 + i18) * 31 + i2;
        i = this.tag.hashCode();
        break;
        j = 1237;
        break label92;
        k = this.zzbuX.hashCode();
        break label110;
        m = this.zzbvb.hashCode();
        break label147;
        n = this.zzbvc.hashCode();
        break label157;
        i1 = this.zzbve.hashCode();
        break label182;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      int j = 0;
      if (this.zzbuR != 0L) {
        paramzzsn.zzb(1, this.zzbuR);
      }
      if (!this.tag.equals("")) {
        paramzzsn.zzn(2, this.tag);
      }
      int i;
      if ((this.zzbuW != null) && (this.zzbuW.length > 0))
      {
        i = 0;
        while (i < this.zzbuW.length)
        {
          zzsz.zze localzze = this.zzbuW[i];
          if (localzze != null) {
            paramzzsn.zza(3, localzze);
          }
          i += 1;
        }
      }
      if (!Arrays.equals(this.zzbuY, zzsx.zzbuD)) {
        paramzzsn.zza(6, this.zzbuY);
      }
      if (this.zzbvb != null) {
        paramzzsn.zza(7, this.zzbvb);
      }
      if (!Arrays.equals(this.zzbuZ, zzsx.zzbuD)) {
        paramzzsn.zza(8, this.zzbuZ);
      }
      if (this.zzbuX != null) {
        paramzzsn.zza(9, this.zzbuX);
      }
      if (this.zzbuV) {
        paramzzsn.zze(10, this.zzbuV);
      }
      if (this.zzbuU != 0) {
        paramzzsn.zzA(11, this.zzbuU);
      }
      if (this.zzob != 0) {
        paramzzsn.zzA(12, this.zzob);
      }
      if (!Arrays.equals(this.zzbva, zzsx.zzbuD)) {
        paramzzsn.zza(13, this.zzbva);
      }
      if (!this.zzbvc.equals("")) {
        paramzzsn.zzn(14, this.zzbvc);
      }
      if (this.zzbvd != 180000L) {
        paramzzsn.zzc(15, this.zzbvd);
      }
      if (this.zzbve != null) {
        paramzzsn.zza(16, this.zzbve);
      }
      if (this.zzbuS != 0L) {
        paramzzsn.zzb(17, this.zzbuS);
      }
      if (!Arrays.equals(this.zzbvf, zzsx.zzbuD)) {
        paramzzsn.zza(18, this.zzbvf);
      }
      if (this.zzbvg != 0) {
        paramzzsn.zzA(19, this.zzbvg);
      }
      if ((this.zzbvh != null) && (this.zzbvh.length > 0))
      {
        i = j;
        while (i < this.zzbvh.length)
        {
          paramzzsn.zzA(20, this.zzbvh[i]);
          i += 1;
        }
      }
      if (this.zzbuT != 0L) {
        paramzzsn.zzb(21, this.zzbuT);
      }
      if (this.zzbvi != 0L) {
        paramzzsn.zzb(22, this.zzbvi);
      }
      super.writeTo(paramzzsn);
    }
    
    public zzd zzJF()
    {
      this.zzbuR = 0L;
      this.zzbuS = 0L;
      this.zzbuT = 0L;
      this.tag = "";
      this.zzbuU = 0;
      this.zzob = 0;
      this.zzbuV = false;
      this.zzbuW = zzsz.zze.zzJG();
      this.zzbuX = null;
      this.zzbuY = zzsx.zzbuD;
      this.zzbuZ = zzsx.zzbuD;
      this.zzbva = zzsx.zzbuD;
      this.zzbvb = null;
      this.zzbvc = "";
      this.zzbvd = 180000L;
      this.zzbve = null;
      this.zzbvf = zzsx.zzbuD;
      this.zzbvg = 0;
      this.zzbvh = zzsx.zzbuw;
      this.zzbvi = 0L;
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zzd zzV(zzsm paramzzsm)
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
          if (zza(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          this.zzbuR = paramzzsm.zzJa();
          break;
        case 18: 
          this.tag = paramzzsm.readString();
          break;
        case 26: 
          j = zzsx.zzc(paramzzsm, 26);
          if (this.zzbuW == null) {}
          for (i = 0;; i = this.zzbuW.length)
          {
            localObject = new zzsz.zze[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbuW, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new zzsz.zze();
              paramzzsm.zza(localObject[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = new zzsz.zze();
          paramzzsm.zza(localObject[j]);
          this.zzbuW = ((zzsz.zze[])localObject);
          break;
        case 50: 
          this.zzbuY = paramzzsm.readBytes();
          break;
        case 58: 
          if (this.zzbvb == null) {
            this.zzbvb = new zzsz.zza();
          }
          paramzzsm.zza(this.zzbvb);
          break;
        case 66: 
          this.zzbuZ = paramzzsm.readBytes();
          break;
        case 74: 
          if (this.zzbuX == null) {
            this.zzbuX = new zzsz.zzb();
          }
          paramzzsm.zza(this.zzbuX);
          break;
        case 80: 
          this.zzbuV = paramzzsm.zzJc();
          break;
        case 88: 
          this.zzbuU = paramzzsm.zzJb();
          break;
        case 96: 
          this.zzob = paramzzsm.zzJb();
          break;
        case 106: 
          this.zzbva = paramzzsm.readBytes();
          break;
        case 114: 
          this.zzbvc = paramzzsm.readString();
          break;
        case 120: 
          this.zzbvd = paramzzsm.zzJe();
          break;
        case 130: 
          if (this.zzbve == null) {
            this.zzbve = new zzsz.zzc();
          }
          paramzzsm.zza(this.zzbve);
          break;
        case 136: 
          this.zzbuS = paramzzsm.zzJa();
          break;
        case 146: 
          this.zzbvf = paramzzsm.readBytes();
          break;
        case 152: 
          i = paramzzsm.zzJb();
          switch (i)
          {
          default: 
            break;
          case 0: 
          case 1: 
          case 2: 
            this.zzbvg = i;
          }
          break;
        case 160: 
          j = zzsx.zzc(paramzzsm, 160);
          if (this.zzbvh == null) {}
          for (i = 0;; i = this.zzbvh.length)
          {
            localObject = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbvh, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = paramzzsm.zzJb();
              paramzzsm.zzIX();
              j += 1;
            }
          }
          localObject[j] = paramzzsm.zzJb();
          this.zzbvh = ((int[])localObject);
          break;
        case 162: 
          int k = paramzzsm.zzmq(paramzzsm.zzJf());
          i = paramzzsm.getPosition();
          j = 0;
          while (paramzzsm.zzJk() > 0)
          {
            paramzzsm.zzJb();
            j += 1;
          }
          paramzzsm.zzms(i);
          if (this.zzbvh == null) {}
          for (i = 0;; i = this.zzbvh.length)
          {
            localObject = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzbvh, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length)
            {
              localObject[j] = paramzzsm.zzJb();
              j += 1;
            }
          }
          this.zzbvh = ((int[])localObject);
          paramzzsm.zzmr(k);
          break;
        case 168: 
          this.zzbuT = paramzzsm.zzJa();
          break;
        case 176: 
          this.zzbvi = paramzzsm.zzJa();
        }
      }
    }
    
    protected int zzz()
    {
      int m = 0;
      int i = super.zzz();
      int j = i;
      if (this.zzbuR != 0L) {
        j = i + zzsn.zzd(1, this.zzbuR);
      }
      i = j;
      if (!this.tag.equals("")) {
        i = j + zzsn.zzo(2, this.tag);
      }
      j = i;
      if (this.zzbuW != null)
      {
        j = i;
        if (this.zzbuW.length > 0)
        {
          j = 0;
          while (j < this.zzbuW.length)
          {
            zzsz.zze localzze = this.zzbuW[j];
            k = i;
            if (localzze != null) {
              k = i + zzsn.zzc(3, localzze);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (!Arrays.equals(this.zzbuY, zzsx.zzbuD)) {
        i = j + zzsn.zzb(6, this.zzbuY);
      }
      j = i;
      if (this.zzbvb != null) {
        j = i + zzsn.zzc(7, this.zzbvb);
      }
      i = j;
      if (!Arrays.equals(this.zzbuZ, zzsx.zzbuD)) {
        i = j + zzsn.zzb(8, this.zzbuZ);
      }
      j = i;
      if (this.zzbuX != null) {
        j = i + zzsn.zzc(9, this.zzbuX);
      }
      i = j;
      if (this.zzbuV) {
        i = j + zzsn.zzf(10, this.zzbuV);
      }
      j = i;
      if (this.zzbuU != 0) {
        j = i + zzsn.zzC(11, this.zzbuU);
      }
      i = j;
      if (this.zzob != 0) {
        i = j + zzsn.zzC(12, this.zzob);
      }
      j = i;
      if (!Arrays.equals(this.zzbva, zzsx.zzbuD)) {
        j = i + zzsn.zzb(13, this.zzbva);
      }
      i = j;
      if (!this.zzbvc.equals("")) {
        i = j + zzsn.zzo(14, this.zzbvc);
      }
      j = i;
      if (this.zzbvd != 180000L) {
        j = i + zzsn.zze(15, this.zzbvd);
      }
      i = j;
      if (this.zzbve != null) {
        i = j + zzsn.zzc(16, this.zzbve);
      }
      j = i;
      if (this.zzbuS != 0L) {
        j = i + zzsn.zzd(17, this.zzbuS);
      }
      int k = j;
      if (!Arrays.equals(this.zzbvf, zzsx.zzbuD)) {
        k = j + zzsn.zzb(18, this.zzbvf);
      }
      i = k;
      if (this.zzbvg != 0) {
        i = k + zzsn.zzC(19, this.zzbvg);
      }
      j = i;
      if (this.zzbvh != null)
      {
        j = i;
        if (this.zzbvh.length > 0)
        {
          k = 0;
          j = m;
          while (j < this.zzbvh.length)
          {
            k += zzsn.zzmx(this.zzbvh[j]);
            j += 1;
          }
          j = i + k + this.zzbvh.length * 2;
        }
      }
      i = j;
      if (this.zzbuT != 0L) {
        i = j + zzsn.zzd(21, this.zzbuT);
      }
      j = i;
      if (this.zzbvi != 0L) {
        j = i + zzsn.zzd(22, this.zzbvi);
      }
      return j;
    }
  }
  
  public static final class zze
    extends zzso<zze>
  {
    private static volatile zze[] zzbvj;
    public String key;
    public String value;
    
    public zze()
    {
      zzJH();
    }
    
    public static zze[] zzJG()
    {
      if (zzbvj == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzbvj == null) {
          zzbvj = new zze[0];
        }
        return zzbvj;
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
          } while (!(paramObject instanceof zze));
          paramObject = (zze)paramObject;
          if (this.key != null) {
            break;
          }
          bool1 = bool2;
        } while (((zze)paramObject).key != null);
        if (this.value != null) {
          break label111;
        }
        bool1 = bool2;
      } while (((zze)paramObject).value != null);
      for (;;)
      {
        if ((this.zzbuj == null) || (this.zzbuj.isEmpty()))
        {
          if (((zze)paramObject).zzbuj != null)
          {
            bool1 = bool2;
            if (!((zze)paramObject).zzbuj.isEmpty()) {
              break;
            }
          }
          return true;
          if (this.key.equals(((zze)paramObject).key)) {
            break label41;
          }
          return false;
          label111:
          if (!this.value.equals(((zze)paramObject).value)) {
            return false;
          }
        }
      }
      return this.zzbuj.equals(((zze)paramObject).zzbuj);
    }
    
    public int hashCode()
    {
      int m = 0;
      int n = getClass().getName().hashCode();
      int i;
      int j;
      if (this.key == null)
      {
        i = 0;
        if (this.value != null) {
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
        i = this.key.hashCode();
        break;
        j = this.value.hashCode();
        break label33;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (!this.key.equals("")) {
        paramzzsn.zzn(1, this.key);
      }
      if (!this.value.equals("")) {
        paramzzsn.zzn(2, this.value);
      }
      super.writeTo(paramzzsn);
    }
    
    public zze zzJH()
    {
      this.key = "";
      this.value = "";
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zze zzW(zzsm paramzzsm)
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
          this.key = paramzzsm.readString();
          break;
        case 18: 
          this.value = paramzzsm.readString();
        }
      }
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (!this.key.equals("")) {
        i = j + zzsn.zzo(1, this.key);
      }
      j = i;
      if (!this.value.equals("")) {
        j = i + zzsn.zzo(2, this.value);
      }
      return j;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzsz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */