package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmq;
import java.util.UUID;

public class zzai
  extends zzd
{
  private SharedPreferences zzTh;
  private long zzTi;
  private long zzTj = -1L;
  private final zza zzTk = new zza("monitoring", zzjn().zzkX(), null);
  
  protected zzai(zzf paramzzf)
  {
    super(paramzzf);
  }
  
  public void zzbp(String paramString)
  {
    zzjk();
    zzjv();
    SharedPreferences.Editor localEditor = this.zzTh.edit();
    if (TextUtils.isEmpty(paramString)) {
      localEditor.remove("installation_campaign");
    }
    for (;;)
    {
      if (!localEditor.commit()) {
        zzbg("Failed to commit campaign data");
      }
      return;
      localEditor.putString("installation_campaign", paramString);
    }
  }
  
  protected void zziJ()
  {
    this.zzTh = getContext().getSharedPreferences("com.google.android.gms.analytics.prefs", 0);
  }
  
  public long zzlF()
  {
    zzjk();
    zzjv();
    long l;
    if (this.zzTi == 0L)
    {
      l = this.zzTh.getLong("first_run", 0L);
      if (l == 0L) {
        break label46;
      }
    }
    for (this.zzTi = l;; this.zzTi = l)
    {
      return this.zzTi;
      label46:
      l = zzjl().currentTimeMillis();
      SharedPreferences.Editor localEditor = this.zzTh.edit();
      localEditor.putLong("first_run", l);
      if (!localEditor.commit()) {
        zzbg("Failed to commit first run time");
      }
    }
  }
  
  public zzaj zzlG()
  {
    return new zzaj(zzjl(), zzlF());
  }
  
  public long zzlH()
  {
    zzjk();
    zzjv();
    if (this.zzTj == -1L) {
      this.zzTj = this.zzTh.getLong("last_dispatch", 0L);
    }
    return this.zzTj;
  }
  
  public void zzlI()
  {
    zzjk();
    zzjv();
    long l = zzjl().currentTimeMillis();
    SharedPreferences.Editor localEditor = this.zzTh.edit();
    localEditor.putLong("last_dispatch", l);
    localEditor.apply();
    this.zzTj = l;
  }
  
  public String zzlJ()
  {
    zzjk();
    zzjv();
    String str = this.zzTh.getString("installation_campaign", null);
    if (TextUtils.isEmpty(str)) {
      return null;
    }
    return str;
  }
  
  public zza zzlK()
  {
    return this.zzTk;
  }
  
  public final class zza
  {
    private final String mName;
    private final long zzTl;
    
    private zza(String paramString, long paramLong)
    {
      zzx.zzcM(paramString);
      if (paramLong > 0L) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zzac(bool);
        this.mName = paramString;
        this.zzTl = paramLong;
        return;
      }
    }
    
    private void zzlL()
    {
      long l = zzai.this.zzjl().currentTimeMillis();
      SharedPreferences.Editor localEditor = zzai.zza(zzai.this).edit();
      localEditor.remove(zzlQ());
      localEditor.remove(zzlR());
      localEditor.putLong(zzlP(), l);
      localEditor.commit();
    }
    
    private long zzlM()
    {
      long l = zzlO();
      if (l == 0L) {
        return 0L;
      }
      return Math.abs(l - zzai.this.zzjl().currentTimeMillis());
    }
    
    private long zzlO()
    {
      return zzai.zza(zzai.this).getLong(zzlP(), 0L);
    }
    
    private String zzlP()
    {
      return this.mName + ":start";
    }
    
    private String zzlQ()
    {
      return this.mName + ":count";
    }
    
    public void zzbq(String paramString)
    {
      if (zzlO() == 0L) {
        zzlL();
      }
      String str = paramString;
      if (paramString == null) {
        str = "";
      }
      for (;;)
      {
        try
        {
          long l = zzai.zza(zzai.this).getLong(zzlQ(), 0L);
          if (l <= 0L)
          {
            paramString = zzai.zza(zzai.this).edit();
            paramString.putString(zzlR(), str);
            paramString.putLong(zzlQ(), 1L);
            paramString.apply();
            return;
          }
          if ((UUID.randomUUID().getLeastSignificantBits() & 0x7FFFFFFFFFFFFFFF) < Long.MAX_VALUE / (l + 1L))
          {
            i = 1;
            paramString = zzai.zza(zzai.this).edit();
            if (i != 0) {
              paramString.putString(zzlR(), str);
            }
            paramString.putLong(zzlQ(), l + 1L);
            paramString.apply();
            return;
          }
        }
        finally {}
        int i = 0;
      }
    }
    
    public Pair<String, Long> zzlN()
    {
      long l = zzlM();
      if (l < this.zzTl) {}
      String str;
      do
      {
        return null;
        if (l > this.zzTl * 2L)
        {
          zzlL();
          return null;
        }
        str = zzai.zza(zzai.this).getString(zzlR(), null);
        l = zzai.zza(zzai.this).getLong(zzlQ(), 0L);
        zzlL();
      } while ((str == null) || (l <= 0L));
      return new Pair(str, Long.valueOf(l));
    }
    
    protected String zzlR()
    {
      return this.mName + ":value";
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\analytics\internal\zzai.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */