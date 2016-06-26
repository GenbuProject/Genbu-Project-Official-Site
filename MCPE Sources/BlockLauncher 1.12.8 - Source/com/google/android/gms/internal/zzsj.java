package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public final class zzsj
  extends zzso<zzsj>
{
  public zza[] zzbtA;
  
  public zzsj()
  {
    zzIQ();
  }
  
  public static zzsj zzA(byte[] paramArrayOfByte)
    throws zzst
  {
    return (zzsj)zzsu.mergeFrom(new zzsj(), paramArrayOfByte);
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
        } while (!(paramObject instanceof zzsj));
        paramObject = (zzsj)paramObject;
        bool1 = bool2;
      } while (!zzss.equals(this.zzbtA, ((zzsj)paramObject).zzbtA));
      if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
        break label79;
      }
      if (((zzsj)paramObject).zzbuj == null) {
        break;
      }
      bool1 = bool2;
    } while (!((zzsj)paramObject).zzbuj.isEmpty());
    return true;
    label79:
    return this.zzbuj.equals(((zzsj)paramObject).zzbuj);
  }
  
  public int hashCode()
  {
    int j = getClass().getName().hashCode();
    int k = zzss.hashCode(this.zzbtA);
    if ((this.zzbuj == null) || (this.zzbuj.isEmpty())) {}
    for (int i = 0;; i = this.zzbuj.hashCode()) {
      return i + ((j + 527) * 31 + k) * 31;
    }
  }
  
  public void writeTo(zzsn paramzzsn)
    throws IOException
  {
    if ((this.zzbtA != null) && (this.zzbtA.length > 0))
    {
      int i = 0;
      while (i < this.zzbtA.length)
      {
        zza localzza = this.zzbtA[i];
        if (localzza != null) {
          paramzzsn.zza(1, localzza);
        }
        i += 1;
      }
    }
    super.writeTo(paramzzsn);
  }
  
  public zzsj zzIQ()
  {
    this.zzbtA = zza.zzIR();
    this.zzbuj = null;
    this.zzbuu = -1;
    return this;
  }
  
  public zzsj zzK(zzsm paramzzsm)
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
        if (this.zzbtA == null) {}
        zza[] arrayOfzza;
        for (i = 0;; i = this.zzbtA.length)
        {
          arrayOfzza = new zza[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(this.zzbtA, 0, arrayOfzza, 0, i);
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
        this.zzbtA = arrayOfzza;
      }
    }
  }
  
  protected int zzz()
  {
    int i = super.zzz();
    int k = i;
    if (this.zzbtA != null)
    {
      k = i;
      if (this.zzbtA.length > 0)
      {
        int j = 0;
        for (;;)
        {
          k = i;
          if (j >= this.zzbtA.length) {
            break;
          }
          zza localzza = this.zzbtA[j];
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
    private static volatile zza[] zzbtB;
    public String name;
    public zza zzbtC;
    
    public zza()
    {
      zzIS();
    }
    
    public static zza[] zzIR()
    {
      if (zzbtB == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzbtB == null) {
          zzbtB = new zza[0];
        }
        return zzbtB;
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
          if (this.name != null) {
            break;
          }
          bool1 = bool2;
        } while (((zza)paramObject).name != null);
        if (this.zzbtC != null) {
          break label111;
        }
        bool1 = bool2;
      } while (((zza)paramObject).zzbtC != null);
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
          if (this.name.equals(((zza)paramObject).name)) {
            break label41;
          }
          return false;
          label111:
          if (!this.zzbtC.equals(((zza)paramObject).zzbtC)) {
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
      int i;
      int j;
      if (this.name == null)
      {
        i = 0;
        if (this.zzbtC != null) {
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
        j = this.zzbtC.hashCode();
        break label33;
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      paramzzsn.zzn(1, this.name);
      if (this.zzbtC != null) {
        paramzzsn.zza(2, this.zzbtC);
      }
      super.writeTo(paramzzsn);
    }
    
    public zza zzIS()
    {
      this.name = "";
      this.zzbtC = null;
      this.zzbuj = null;
      this.zzbuu = -1;
      return this;
    }
    
    public zza zzL(zzsm paramzzsm)
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
          if (this.zzbtC == null) {
            this.zzbtC = new zza();
          }
          paramzzsm.zza(this.zzbtC);
        }
      }
    }
    
    protected int zzz()
    {
      int j = super.zzz() + zzsn.zzo(1, this.name);
      int i = j;
      if (this.zzbtC != null) {
        i = j + zzsn.zzc(2, this.zzbtC);
      }
      return i;
    }
    
    public static final class zza
      extends zzso<zza>
    {
      private static volatile zza[] zzbtD;
      public int type;
      public zza zzbtE;
      
      public zza()
      {
        zzIU();
      }
      
      public static zza[] zzIT()
      {
        if (zzbtD == null) {}
        synchronized (zzss.zzbut)
        {
          if (zzbtD == null) {
            zzbtD = new zza[0];
          }
          return zzbtD;
        }
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
          } while (this.type != ((zza)paramObject).type);
          if (this.zzbtE != null) {
            break;
          }
          bool1 = bool2;
        } while (((zza)paramObject).zzbtE != null);
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
            if (!this.zzbtE.equals(((zza)paramObject).zzbtE)) {
              return false;
            }
          }
        }
        return this.zzbuj.equals(((zza)paramObject).zzbuj);
      }
      
      public int hashCode()
      {
        int k = 0;
        int m = getClass().getName().hashCode();
        int n = this.type;
        int i;
        if (this.zzbtE == null)
        {
          i = 0;
          j = k;
          if (this.zzbuj != null) {
            if (!this.zzbuj.isEmpty()) {
              break label84;
            }
          }
        }
        label84:
        for (int j = k;; j = this.zzbuj.hashCode())
        {
          return (i + ((m + 527) * 31 + n) * 31) * 31 + j;
          i = this.zzbtE.hashCode();
          break;
        }
      }
      
      public void writeTo(zzsn paramzzsn)
        throws IOException
      {
        paramzzsn.zzA(1, this.type);
        if (this.zzbtE != null) {
          paramzzsn.zza(2, this.zzbtE);
        }
        super.writeTo(paramzzsn);
      }
      
      public zza zzIU()
      {
        this.type = 1;
        this.zzbtE = null;
        this.zzbuj = null;
        this.zzbuu = -1;
        return this;
      }
      
      public zza zzM(zzsm paramzzsm)
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
              this.type = i;
            }
            break;
          case 18: 
            if (this.zzbtE == null) {
              this.zzbtE = new zza();
            }
            paramzzsm.zza(this.zzbtE);
          }
        }
      }
      
      protected int zzz()
      {
        int j = super.zzz() + zzsn.zzC(1, this.type);
        int i = j;
        if (this.zzbtE != null) {
          i = j + zzsn.zzc(2, this.zzbtE);
        }
        return i;
      }
      
      public static final class zza
        extends zzso<zza>
      {
        public byte[] zzbtF;
        public String zzbtG;
        public double zzbtH;
        public float zzbtI;
        public long zzbtJ;
        public int zzbtK;
        public int zzbtL;
        public boolean zzbtM;
        public zzsj.zza[] zzbtN;
        public zzsj.zza.zza[] zzbtO;
        public String[] zzbtP;
        public long[] zzbtQ;
        public float[] zzbtR;
        public long zzbtS;
        
        public zza()
        {
          zzIV();
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
            } while (!Arrays.equals(this.zzbtF, ((zza)paramObject).zzbtF));
            if (this.zzbtG != null) {
              break;
            }
            bool1 = bool2;
          } while (((zza)paramObject).zzbtG != null);
          while (this.zzbtG.equals(((zza)paramObject).zzbtG))
          {
            bool1 = bool2;
            if (Double.doubleToLongBits(this.zzbtH) != Double.doubleToLongBits(((zza)paramObject).zzbtH)) {
              break;
            }
            bool1 = bool2;
            if (Float.floatToIntBits(this.zzbtI) != Float.floatToIntBits(((zza)paramObject).zzbtI)) {
              break;
            }
            bool1 = bool2;
            if (this.zzbtJ != ((zza)paramObject).zzbtJ) {
              break;
            }
            bool1 = bool2;
            if (this.zzbtK != ((zza)paramObject).zzbtK) {
              break;
            }
            bool1 = bool2;
            if (this.zzbtL != ((zza)paramObject).zzbtL) {
              break;
            }
            bool1 = bool2;
            if (this.zzbtM != ((zza)paramObject).zzbtM) {
              break;
            }
            bool1 = bool2;
            if (!zzss.equals(this.zzbtN, ((zza)paramObject).zzbtN)) {
              break;
            }
            bool1 = bool2;
            if (!zzss.equals(this.zzbtO, ((zza)paramObject).zzbtO)) {
              break;
            }
            bool1 = bool2;
            if (!zzss.equals(this.zzbtP, ((zza)paramObject).zzbtP)) {
              break;
            }
            bool1 = bool2;
            if (!zzss.equals(this.zzbtQ, ((zza)paramObject).zzbtQ)) {
              break;
            }
            bool1 = bool2;
            if (!zzss.equals(this.zzbtR, ((zza)paramObject).zzbtR)) {
              break;
            }
            bool1 = bool2;
            if (this.zzbtS != ((zza)paramObject).zzbtS) {
              break;
            }
            if ((this.zzbuj != null) && (!this.zzbuj.isEmpty())) {
              break label297;
            }
            if (((zza)paramObject).zzbuj != null)
            {
              bool1 = bool2;
              if (!((zza)paramObject).zzbuj.isEmpty()) {
                break;
              }
            }
            return true;
          }
          return false;
          label297:
          return this.zzbuj.equals(((zza)paramObject).zzbuj);
        }
        
        public int hashCode()
        {
          int m = 0;
          int n = getClass().getName().hashCode();
          int i1 = Arrays.hashCode(this.zzbtF);
          int i;
          int i2;
          int i3;
          int i4;
          int i5;
          int i6;
          int j;
          label100:
          int i7;
          int i8;
          int i9;
          int i10;
          int i11;
          int i12;
          if (this.zzbtG == null)
          {
            i = 0;
            long l = Double.doubleToLongBits(this.zzbtH);
            i2 = (int)(l ^ l >>> 32);
            i3 = Float.floatToIntBits(this.zzbtI);
            i4 = (int)(this.zzbtJ ^ this.zzbtJ >>> 32);
            i5 = this.zzbtK;
            i6 = this.zzbtL;
            if (!this.zzbtM) {
              break label288;
            }
            j = 1231;
            i7 = zzss.hashCode(this.zzbtN);
            i8 = zzss.hashCode(this.zzbtO);
            i9 = zzss.hashCode(this.zzbtP);
            i10 = zzss.hashCode(this.zzbtQ);
            i11 = zzss.hashCode(this.zzbtR);
            i12 = (int)(this.zzbtS ^ this.zzbtS >>> 32);
            k = m;
            if (this.zzbuj != null) {
              if (!this.zzbuj.isEmpty()) {
                break label295;
              }
            }
          }
          label288:
          label295:
          for (int k = m;; k = this.zzbuj.hashCode())
          {
            return (((((((j + ((((((i + ((n + 527) * 31 + i1) * 31) * 31 + i2) * 31 + i3) * 31 + i4) * 31 + i5) * 31 + i6) * 31) * 31 + i7) * 31 + i8) * 31 + i9) * 31 + i10) * 31 + i11) * 31 + i12) * 31 + k;
            i = this.zzbtG.hashCode();
            break;
            j = 1237;
            break label100;
          }
        }
        
        public void writeTo(zzsn paramzzsn)
          throws IOException
        {
          int j = 0;
          if (!Arrays.equals(this.zzbtF, zzsx.zzbuD)) {
            paramzzsn.zza(1, this.zzbtF);
          }
          if (!this.zzbtG.equals("")) {
            paramzzsn.zzn(2, this.zzbtG);
          }
          if (Double.doubleToLongBits(this.zzbtH) != Double.doubleToLongBits(0.0D)) {
            paramzzsn.zza(3, this.zzbtH);
          }
          if (Float.floatToIntBits(this.zzbtI) != Float.floatToIntBits(0.0F)) {
            paramzzsn.zzb(4, this.zzbtI);
          }
          if (this.zzbtJ != 0L) {
            paramzzsn.zzb(5, this.zzbtJ);
          }
          if (this.zzbtK != 0) {
            paramzzsn.zzA(6, this.zzbtK);
          }
          if (this.zzbtL != 0) {
            paramzzsn.zzB(7, this.zzbtL);
          }
          if (this.zzbtM) {
            paramzzsn.zze(8, this.zzbtM);
          }
          int i;
          Object localObject;
          if ((this.zzbtN != null) && (this.zzbtN.length > 0))
          {
            i = 0;
            while (i < this.zzbtN.length)
            {
              localObject = this.zzbtN[i];
              if (localObject != null) {
                paramzzsn.zza(9, (zzsu)localObject);
              }
              i += 1;
            }
          }
          if ((this.zzbtO != null) && (this.zzbtO.length > 0))
          {
            i = 0;
            while (i < this.zzbtO.length)
            {
              localObject = this.zzbtO[i];
              if (localObject != null) {
                paramzzsn.zza(10, (zzsu)localObject);
              }
              i += 1;
            }
          }
          if ((this.zzbtP != null) && (this.zzbtP.length > 0))
          {
            i = 0;
            while (i < this.zzbtP.length)
            {
              localObject = this.zzbtP[i];
              if (localObject != null) {
                paramzzsn.zzn(11, (String)localObject);
              }
              i += 1;
            }
          }
          if ((this.zzbtQ != null) && (this.zzbtQ.length > 0))
          {
            i = 0;
            while (i < this.zzbtQ.length)
            {
              paramzzsn.zzb(12, this.zzbtQ[i]);
              i += 1;
            }
          }
          if (this.zzbtS != 0L) {
            paramzzsn.zzb(13, this.zzbtS);
          }
          if ((this.zzbtR != null) && (this.zzbtR.length > 0))
          {
            i = j;
            while (i < this.zzbtR.length)
            {
              paramzzsn.zzb(14, this.zzbtR[i]);
              i += 1;
            }
          }
          super.writeTo(paramzzsn);
        }
        
        public zza zzIV()
        {
          this.zzbtF = zzsx.zzbuD;
          this.zzbtG = "";
          this.zzbtH = 0.0D;
          this.zzbtI = 0.0F;
          this.zzbtJ = 0L;
          this.zzbtK = 0;
          this.zzbtL = 0;
          this.zzbtM = false;
          this.zzbtN = zzsj.zza.zzIR();
          this.zzbtO = zzsj.zza.zza.zzIT();
          this.zzbtP = zzsx.zzbuB;
          this.zzbtQ = zzsx.zzbux;
          this.zzbtR = zzsx.zzbuy;
          this.zzbtS = 0L;
          this.zzbuj = null;
          this.zzbuu = -1;
          return this;
        }
        
        public zza zzN(zzsm paramzzsm)
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
              this.zzbtF = paramzzsm.readBytes();
              break;
            case 18: 
              this.zzbtG = paramzzsm.readString();
              break;
            case 25: 
              this.zzbtH = paramzzsm.readDouble();
              break;
            case 37: 
              this.zzbtI = paramzzsm.readFloat();
              break;
            case 40: 
              this.zzbtJ = paramzzsm.zzJa();
              break;
            case 48: 
              this.zzbtK = paramzzsm.zzJb();
              break;
            case 56: 
              this.zzbtL = paramzzsm.zzJd();
              break;
            case 64: 
              this.zzbtM = paramzzsm.zzJc();
              break;
            case 74: 
              j = zzsx.zzc(paramzzsm, 74);
              if (this.zzbtN == null) {}
              for (i = 0;; i = this.zzbtN.length)
              {
                localObject = new zzsj.zza[j + i];
                j = i;
                if (i != 0)
                {
                  System.arraycopy(this.zzbtN, 0, localObject, 0, i);
                  j = i;
                }
                while (j < localObject.length - 1)
                {
                  localObject[j] = new zzsj.zza();
                  paramzzsm.zza(localObject[j]);
                  paramzzsm.zzIX();
                  j += 1;
                }
              }
              localObject[j] = new zzsj.zza();
              paramzzsm.zza(localObject[j]);
              this.zzbtN = ((zzsj.zza[])localObject);
              break;
            case 82: 
              j = zzsx.zzc(paramzzsm, 82);
              if (this.zzbtO == null) {}
              for (i = 0;; i = this.zzbtO.length)
              {
                localObject = new zzsj.zza.zza[j + i];
                j = i;
                if (i != 0)
                {
                  System.arraycopy(this.zzbtO, 0, localObject, 0, i);
                  j = i;
                }
                while (j < localObject.length - 1)
                {
                  localObject[j] = new zzsj.zza.zza();
                  paramzzsm.zza(localObject[j]);
                  paramzzsm.zzIX();
                  j += 1;
                }
              }
              localObject[j] = new zzsj.zza.zza();
              paramzzsm.zza(localObject[j]);
              this.zzbtO = ((zzsj.zza.zza[])localObject);
              break;
            case 90: 
              j = zzsx.zzc(paramzzsm, 90);
              if (this.zzbtP == null) {}
              for (i = 0;; i = this.zzbtP.length)
              {
                localObject = new String[j + i];
                j = i;
                if (i != 0)
                {
                  System.arraycopy(this.zzbtP, 0, localObject, 0, i);
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
              this.zzbtP = ((String[])localObject);
              break;
            case 96: 
              j = zzsx.zzc(paramzzsm, 96);
              if (this.zzbtQ == null) {}
              for (i = 0;; i = this.zzbtQ.length)
              {
                localObject = new long[j + i];
                j = i;
                if (i != 0)
                {
                  System.arraycopy(this.zzbtQ, 0, localObject, 0, i);
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
              this.zzbtQ = ((long[])localObject);
              break;
            case 98: 
              k = paramzzsm.zzmq(paramzzsm.zzJf());
              i = paramzzsm.getPosition();
              j = 0;
              while (paramzzsm.zzJk() > 0)
              {
                paramzzsm.zzJa();
                j += 1;
              }
              paramzzsm.zzms(i);
              if (this.zzbtQ == null) {}
              for (i = 0;; i = this.zzbtQ.length)
              {
                localObject = new long[j + i];
                j = i;
                if (i != 0)
                {
                  System.arraycopy(this.zzbtQ, 0, localObject, 0, i);
                  j = i;
                }
                while (j < localObject.length)
                {
                  localObject[j] = paramzzsm.zzJa();
                  j += 1;
                }
              }
              this.zzbtQ = ((long[])localObject);
              paramzzsm.zzmr(k);
              break;
            case 104: 
              this.zzbtS = paramzzsm.zzJa();
              break;
            case 117: 
              j = zzsx.zzc(paramzzsm, 117);
              if (this.zzbtR == null) {}
              for (i = 0;; i = this.zzbtR.length)
              {
                localObject = new float[j + i];
                j = i;
                if (i != 0)
                {
                  System.arraycopy(this.zzbtR, 0, localObject, 0, i);
                  j = i;
                }
                while (j < localObject.length - 1)
                {
                  localObject[j] = paramzzsm.readFloat();
                  paramzzsm.zzIX();
                  j += 1;
                }
              }
              localObject[j] = paramzzsm.readFloat();
              this.zzbtR = ((float[])localObject);
              break;
            case 114: 
              i = paramzzsm.zzJf();
              k = paramzzsm.zzmq(i);
              j = i / 4;
              if (this.zzbtR == null) {}
              for (i = 0;; i = this.zzbtR.length)
              {
                localObject = new float[j + i];
                j = i;
                if (i != 0)
                {
                  System.arraycopy(this.zzbtR, 0, localObject, 0, i);
                  j = i;
                }
                while (j < localObject.length)
                {
                  localObject[j] = paramzzsm.readFloat();
                  j += 1;
                }
              }
              this.zzbtR = ((float[])localObject);
              paramzzsm.zzmr(k);
            }
          }
        }
        
        protected int zzz()
        {
          int i2 = 0;
          int j = super.zzz();
          int i = j;
          if (!Arrays.equals(this.zzbtF, zzsx.zzbuD)) {
            i = j + zzsn.zzb(1, this.zzbtF);
          }
          j = i;
          if (!this.zzbtG.equals("")) {
            j = i + zzsn.zzo(2, this.zzbtG);
          }
          i = j;
          if (Double.doubleToLongBits(this.zzbtH) != Double.doubleToLongBits(0.0D)) {
            i = j + zzsn.zzb(3, this.zzbtH);
          }
          j = i;
          if (Float.floatToIntBits(this.zzbtI) != Float.floatToIntBits(0.0F)) {
            j = i + zzsn.zzc(4, this.zzbtI);
          }
          i = j;
          if (this.zzbtJ != 0L) {
            i = j + zzsn.zzd(5, this.zzbtJ);
          }
          j = i;
          if (this.zzbtK != 0) {
            j = i + zzsn.zzC(6, this.zzbtK);
          }
          int k = j;
          if (this.zzbtL != 0) {
            k = j + zzsn.zzD(7, this.zzbtL);
          }
          i = k;
          if (this.zzbtM) {
            i = k + zzsn.zzf(8, this.zzbtM);
          }
          j = i;
          Object localObject;
          if (this.zzbtN != null)
          {
            j = i;
            if (this.zzbtN.length > 0)
            {
              j = 0;
              while (j < this.zzbtN.length)
              {
                localObject = this.zzbtN[j];
                k = i;
                if (localObject != null) {
                  k = i + zzsn.zzc(9, (zzsu)localObject);
                }
                j += 1;
                i = k;
              }
              j = i;
            }
          }
          i = j;
          if (this.zzbtO != null)
          {
            i = j;
            if (this.zzbtO.length > 0)
            {
              i = j;
              j = 0;
              while (j < this.zzbtO.length)
              {
                localObject = this.zzbtO[j];
                k = i;
                if (localObject != null) {
                  k = i + zzsn.zzc(10, (zzsu)localObject);
                }
                j += 1;
                i = k;
              }
            }
          }
          j = i;
          if (this.zzbtP != null)
          {
            j = i;
            if (this.zzbtP.length > 0)
            {
              j = 0;
              k = 0;
              int n;
              for (int m = 0; j < this.zzbtP.length; m = n)
              {
                localObject = this.zzbtP[j];
                int i1 = k;
                n = m;
                if (localObject != null)
                {
                  n = m + 1;
                  i1 = k + zzsn.zzgO((String)localObject);
                }
                j += 1;
                k = i1;
              }
              j = i + k + m * 1;
            }
          }
          i = j;
          if (this.zzbtQ != null)
          {
            i = j;
            if (this.zzbtQ.length > 0)
            {
              k = 0;
              i = i2;
              while (i < this.zzbtQ.length)
              {
                k += zzsn.zzas(this.zzbtQ[i]);
                i += 1;
              }
              i = j + k + this.zzbtQ.length * 1;
            }
          }
          j = i;
          if (this.zzbtS != 0L) {
            j = i + zzsn.zzd(13, this.zzbtS);
          }
          i = j;
          if (this.zzbtR != null)
          {
            i = j;
            if (this.zzbtR.length > 0) {
              i = j + this.zzbtR.length * 4 + this.zzbtR.length * 1;
            }
          }
          return i;
        }
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzsj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */