package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzx;

class zza
{
  private String zzSF;
  private final zzw zzaTV;
  private final String zzaUa;
  private String zzaVc;
  private String zzaVd;
  private String zzaVe;
  private long zzaVf;
  private long zzaVg;
  private long zzaVh;
  private String zzaVi;
  private long zzaVj;
  private long zzaVk;
  private boolean zzaVl;
  private long zzaVm;
  private long zzaVn;
  private long zzaVo;
  private long zzaVp;
  private boolean zzaVq;
  private long zzaVr;
  private long zzaVs;
  
  zza(zzw paramzzw, String paramString)
  {
    zzx.zzz(paramzzw);
    zzx.zzcM(paramString);
    this.zzaTV = paramzzw;
    this.zzaUa = paramString;
    this.zzaTV.zzjk();
  }
  
  public void setAppVersion(String paramString)
  {
    this.zzaTV.zzjk();
    this.zzaVq |= zzaj.zzQ(this.zzSF, paramString);
    this.zzSF = paramString;
  }
  
  public void setMeasurementEnabled(boolean paramBoolean)
  {
    this.zzaTV.zzjk();
    boolean bool2 = this.zzaVq;
    if (this.zzaVl != paramBoolean) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      this.zzaVq = (bool1 | bool2);
      this.zzaVl = paramBoolean;
      return;
    }
  }
  
  public boolean zzAr()
  {
    this.zzaTV.zzjk();
    return this.zzaVl;
  }
  
  public void zzBi()
  {
    this.zzaTV.zzjk();
    this.zzaVq = false;
  }
  
  public String zzBj()
  {
    this.zzaTV.zzjk();
    return this.zzaVc;
  }
  
  public String zzBk()
  {
    this.zzaTV.zzjk();
    return this.zzaVd;
  }
  
  public String zzBl()
  {
    this.zzaTV.zzjk();
    return this.zzaVe;
  }
  
  public long zzBm()
  {
    this.zzaTV.zzjk();
    return this.zzaVg;
  }
  
  public long zzBn()
  {
    this.zzaTV.zzjk();
    return this.zzaVh;
  }
  
  public String zzBo()
  {
    this.zzaTV.zzjk();
    return this.zzaVi;
  }
  
  public long zzBp()
  {
    this.zzaTV.zzjk();
    return this.zzaVj;
  }
  
  public long zzBq()
  {
    this.zzaTV.zzjk();
    return this.zzaVk;
  }
  
  public long zzBr()
  {
    this.zzaTV.zzjk();
    return this.zzaVf;
  }
  
  public long zzBs()
  {
    this.zzaTV.zzjk();
    return this.zzaVr;
  }
  
  public long zzBt()
  {
    this.zzaTV.zzjk();
    return this.zzaVs;
  }
  
  public void zzBu()
  {
    this.zzaTV.zzjk();
    long l2 = this.zzaVf + 1L;
    long l1 = l2;
    if (l2 > 2147483647L)
    {
      this.zzaTV.zzAo().zzCF().zzfg("Bundle index overflow");
      l1 = 0L;
    }
    this.zzaVq = true;
    this.zzaVf = l1;
  }
  
  public long zzBv()
  {
    this.zzaTV.zzjk();
    return this.zzaVm;
  }
  
  public long zzBw()
  {
    this.zzaTV.zzjk();
    return this.zzaVn;
  }
  
  public long zzBx()
  {
    this.zzaTV.zzjk();
    return this.zzaVo;
  }
  
  public long zzBy()
  {
    this.zzaTV.zzjk();
    return this.zzaVp;
  }
  
  public void zzO(long paramLong)
  {
    this.zzaTV.zzjk();
    boolean bool2 = this.zzaVq;
    if (this.zzaVg != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      this.zzaVq = (bool1 | bool2);
      this.zzaVg = paramLong;
      return;
    }
  }
  
  public void zzP(long paramLong)
  {
    this.zzaTV.zzjk();
    boolean bool2 = this.zzaVq;
    if (this.zzaVh != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      this.zzaVq = (bool1 | bool2);
      this.zzaVh = paramLong;
      return;
    }
  }
  
  public void zzQ(long paramLong)
  {
    this.zzaTV.zzjk();
    boolean bool2 = this.zzaVq;
    if (this.zzaVj != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      this.zzaVq = (bool1 | bool2);
      this.zzaVj = paramLong;
      return;
    }
  }
  
  public void zzR(long paramLong)
  {
    this.zzaTV.zzjk();
    boolean bool2 = this.zzaVq;
    if (this.zzaVk != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      this.zzaVq = (bool1 | bool2);
      this.zzaVk = paramLong;
      return;
    }
  }
  
  public void zzS(long paramLong)
  {
    boolean bool1 = true;
    boolean bool2;
    if (paramLong >= 0L)
    {
      bool2 = true;
      zzx.zzac(bool2);
      this.zzaTV.zzjk();
      bool2 = this.zzaVq;
      if (this.zzaVf == paramLong) {
        break label58;
      }
    }
    for (;;)
    {
      this.zzaVq = (bool2 | bool1);
      this.zzaVf = paramLong;
      return;
      bool2 = false;
      break;
      label58:
      bool1 = false;
    }
  }
  
  public void zzT(long paramLong)
  {
    this.zzaTV.zzjk();
    boolean bool2 = this.zzaVq;
    if (this.zzaVr != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      this.zzaVq = (bool1 | bool2);
      this.zzaVr = paramLong;
      return;
    }
  }
  
  public void zzU(long paramLong)
  {
    this.zzaTV.zzjk();
    boolean bool2 = this.zzaVq;
    if (this.zzaVs != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      this.zzaVq = (bool1 | bool2);
      this.zzaVs = paramLong;
      return;
    }
  }
  
  public void zzV(long paramLong)
  {
    this.zzaTV.zzjk();
    boolean bool2 = this.zzaVq;
    if (this.zzaVm != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      this.zzaVq = (bool1 | bool2);
      this.zzaVm = paramLong;
      return;
    }
  }
  
  public void zzW(long paramLong)
  {
    this.zzaTV.zzjk();
    boolean bool2 = this.zzaVq;
    if (this.zzaVn != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      this.zzaVq = (bool1 | bool2);
      this.zzaVn = paramLong;
      return;
    }
  }
  
  public void zzX(long paramLong)
  {
    this.zzaTV.zzjk();
    boolean bool2 = this.zzaVq;
    if (this.zzaVo != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      this.zzaVq = (bool1 | bool2);
      this.zzaVo = paramLong;
      return;
    }
  }
  
  public void zzY(long paramLong)
  {
    this.zzaTV.zzjk();
    boolean bool2 = this.zzaVq;
    if (this.zzaVp != paramLong) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      this.zzaVq = (bool1 | bool2);
      this.zzaVp = paramLong;
      return;
    }
  }
  
  public void zzeM(String paramString)
  {
    this.zzaTV.zzjk();
    this.zzaVq |= zzaj.zzQ(this.zzaVc, paramString);
    this.zzaVc = paramString;
  }
  
  public void zzeN(String paramString)
  {
    this.zzaTV.zzjk();
    String str = paramString;
    if (TextUtils.isEmpty(paramString)) {
      str = null;
    }
    this.zzaVq |= zzaj.zzQ(this.zzaVd, str);
    this.zzaVd = str;
  }
  
  public void zzeO(String paramString)
  {
    this.zzaTV.zzjk();
    this.zzaVq |= zzaj.zzQ(this.zzaVe, paramString);
    this.zzaVe = paramString;
  }
  
  public void zzeP(String paramString)
  {
    this.zzaTV.zzjk();
    this.zzaVq |= zzaj.zzQ(this.zzaVi, paramString);
    this.zzaVi = paramString;
  }
  
  public String zzli()
  {
    this.zzaTV.zzjk();
    return this.zzSF;
  }
  
  public String zzwK()
  {
    this.zzaTV.zzjk();
    return this.zzaUa;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\measurement\internal\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */