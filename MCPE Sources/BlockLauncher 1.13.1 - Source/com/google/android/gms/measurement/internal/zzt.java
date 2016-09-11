package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.measurement.zza;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Locale;

class zzt
  extends zzz
{
  static final Pair<String, Long> zzaXh = new Pair("", Long.valueOf(0L));
  private SharedPreferences zzTh;
  public final zzc zzaXi = new zzc("health_monitor", zzCp().zzkX(), null);
  public final zzb zzaXj = new zzb("last_upload", 0L);
  public final zzb zzaXk = new zzb("last_upload_attempt", 0L);
  public final zzb zzaXl = new zzb("backoff", 0L);
  public final zzb zzaXm = new zzb("last_delete_stale", 0L);
  public final zzb zzaXn = new zzb("midnight_offset", 0L);
  private String zzaXo;
  private boolean zzaXp;
  private long zzaXq;
  private final SecureRandom zzaXr = new SecureRandom();
  public final zzb zzaXs = new zzb("time_before_start", 10000L);
  public final zzb zzaXt = new zzb("session_timeout", 1800000L);
  public final zza zzaXu = new zza("start_new_session", true);
  public final zzb zzaXv = new zzb("last_pause_time", 0L);
  public final zzb zzaXw = new zzb("time_active", 0L);
  public boolean zzaXx;
  
  zzt(zzw paramzzw)
  {
    super(paramzzw);
  }
  
  @WorkerThread
  private SharedPreferences zzCO()
  {
    zzjk();
    zzjv();
    return this.zzTh;
  }
  
  @WorkerThread
  void setMeasurementEnabled(boolean paramBoolean)
  {
    zzjk();
    zzAo().zzCK().zzj("Setting measurementEnabled", Boolean.valueOf(paramBoolean));
    SharedPreferences.Editor localEditor = zzCO().edit();
    localEditor.putBoolean("measurement_enabled", paramBoolean);
    localEditor.apply();
  }
  
  @WorkerThread
  boolean zzAr()
  {
    zzjk();
    SharedPreferences localSharedPreferences = zzCO();
    if (!zza.zzAs()) {}
    for (boolean bool = true;; bool = false) {
      return localSharedPreferences.getBoolean("measurement_enabled", bool);
    }
  }
  
  String zzCM()
  {
    byte[] arrayOfByte = new byte[16];
    this.zzaXr.nextBytes(arrayOfByte);
    return String.format(Locale.US, "%032x", new Object[] { new BigInteger(1, arrayOfByte) });
  }
  
  @WorkerThread
  long zzCN()
  {
    zzjv();
    zzjk();
    long l2 = this.zzaXn.get();
    long l1 = l2;
    if (l2 == 0L)
    {
      l1 = this.zzaXr.nextInt(86400000) + 1;
      this.zzaXn.set(l1);
    }
    return l1;
  }
  
  @WorkerThread
  Boolean zzCP()
  {
    zzjk();
    if (!zzCO().contains("use_service")) {
      return null;
    }
    return Boolean.valueOf(zzCO().getBoolean("use_service", false));
  }
  
  @WorkerThread
  protected String zzCQ()
  {
    zzjk();
    String str1 = zzCO().getString("previous_os_version", null);
    String str2 = zzCh().zzCy();
    if ((!TextUtils.isEmpty(str2)) && (!str2.equals(str1)))
    {
      SharedPreferences.Editor localEditor = zzCO().edit();
      localEditor.putString("previous_os_version", str2);
      localEditor.apply();
    }
    return str1;
  }
  
  @WorkerThread
  void zzar(boolean paramBoolean)
  {
    zzjk();
    zzAo().zzCK().zzj("Setting useService", Boolean.valueOf(paramBoolean));
    SharedPreferences.Editor localEditor = zzCO().edit();
    localEditor.putBoolean("use_service", paramBoolean);
    localEditor.apply();
  }
  
  @WorkerThread
  Pair<String, Boolean> zzfh(String paramString)
  {
    zzjk();
    long l = zzjl().elapsedRealtime();
    if ((this.zzaXo != null) && (l < this.zzaXq)) {
      return new Pair(this.zzaXo, Boolean.valueOf(this.zzaXp));
    }
    this.zzaXq = (l + zzCp().zzeS(paramString));
    AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
    try
    {
      paramString = AdvertisingIdClient.getAdvertisingIdInfo(getContext());
      this.zzaXo = paramString.getId();
      this.zzaXp = paramString.isLimitAdTrackingEnabled();
      AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
      return new Pair(this.zzaXo, Boolean.valueOf(this.zzaXp));
    }
    catch (Throwable paramString)
    {
      for (;;)
      {
        zzAo().zzCJ().zzj("Unable to get advertising id", paramString);
        this.zzaXo = "";
      }
    }
  }
  
  String zzfi(String paramString)
  {
    paramString = (String)zzfh(paramString).first;
    MessageDigest localMessageDigest = zzaj.zzbv("MD5");
    if (localMessageDigest == null) {
      return null;
    }
    return String.format(Locale.US, "%032X", new Object[] { new BigInteger(1, localMessageDigest.digest(paramString.getBytes())) });
  }
  
  protected void zziJ()
  {
    this.zzTh = getContext().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
    this.zzaXx = this.zzTh.getBoolean("has_been_opened", false);
    if (!this.zzaXx)
    {
      SharedPreferences.Editor localEditor = this.zzTh.edit();
      localEditor.putBoolean("has_been_opened", true);
      localEditor.apply();
    }
  }
  
  public final class zza
  {
    private final boolean zzaXy;
    private boolean zzaXz;
    private boolean zzagf;
    private final String zzvs;
    
    public zza(String paramString, boolean paramBoolean)
    {
      zzx.zzcM(paramString);
      this.zzvs = paramString;
      this.zzaXy = paramBoolean;
    }
    
    @WorkerThread
    private void zzCR()
    {
      if (this.zzaXz) {
        return;
      }
      this.zzaXz = true;
      this.zzagf = zzt.zza(zzt.this).getBoolean(this.zzvs, this.zzaXy);
    }
    
    @WorkerThread
    public boolean get()
    {
      zzCR();
      return this.zzagf;
    }
    
    @WorkerThread
    public void set(boolean paramBoolean)
    {
      SharedPreferences.Editor localEditor = zzt.zza(zzt.this).edit();
      localEditor.putBoolean(this.zzvs, paramBoolean);
      localEditor.apply();
      this.zzagf = paramBoolean;
    }
  }
  
  public final class zzb
  {
    private long zzaDV;
    private final long zzaXB;
    private boolean zzaXz;
    private final String zzvs;
    
    public zzb(String paramString, long paramLong)
    {
      zzx.zzcM(paramString);
      this.zzvs = paramString;
      this.zzaXB = paramLong;
    }
    
    @WorkerThread
    private void zzCR()
    {
      if (this.zzaXz) {
        return;
      }
      this.zzaXz = true;
      this.zzaDV = zzt.zza(zzt.this).getLong(this.zzvs, this.zzaXB);
    }
    
    @WorkerThread
    public long get()
    {
      zzCR();
      return this.zzaDV;
    }
    
    @WorkerThread
    public void set(long paramLong)
    {
      SharedPreferences.Editor localEditor = zzt.zza(zzt.this).edit();
      localEditor.putLong(this.zzvs, paramLong);
      localEditor.apply();
      this.zzaDV = paramLong;
    }
  }
  
  public final class zzc
  {
    private final long zzTl;
    final String zzaXC;
    private final String zzaXD;
    private final String zzaXE;
    
    private zzc(String paramString, long paramLong)
    {
      zzx.zzcM(paramString);
      if (paramLong > 0L) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zzac(bool);
        this.zzaXC = (paramString + ":start");
        this.zzaXD = (paramString + ":count");
        this.zzaXE = (paramString + ":value");
        this.zzTl = paramLong;
        return;
      }
    }
    
    @WorkerThread
    private void zzlL()
    {
      zzt.this.zzjk();
      long l = zzt.this.zzjl().currentTimeMillis();
      SharedPreferences.Editor localEditor = zzt.zza(zzt.this).edit();
      localEditor.remove(this.zzaXD);
      localEditor.remove(this.zzaXE);
      localEditor.putLong(this.zzaXC, l);
      localEditor.apply();
    }
    
    @WorkerThread
    private long zzlM()
    {
      zzt.this.zzjk();
      long l = zzlO();
      if (l == 0L)
      {
        zzlL();
        return 0L;
      }
      return Math.abs(l - zzt.this.zzjl().currentTimeMillis());
    }
    
    @WorkerThread
    private long zzlO()
    {
      return zzt.zzc(zzt.this).getLong(this.zzaXC, 0L);
    }
    
    @WorkerThread
    public void zzbq(String paramString)
    {
      zzf(paramString, 1L);
    }
    
    @WorkerThread
    public void zzf(String paramString, long paramLong)
    {
      zzt.this.zzjk();
      if (zzlO() == 0L) {
        zzlL();
      }
      String str = paramString;
      if (paramString == null) {
        str = "";
      }
      long l = zzt.zza(zzt.this).getLong(this.zzaXD, 0L);
      if (l <= 0L)
      {
        paramString = zzt.zza(zzt.this).edit();
        paramString.putString(this.zzaXE, str);
        paramString.putLong(this.zzaXD, paramLong);
        paramString.apply();
        return;
      }
      if ((zzt.zzb(zzt.this).nextLong() & 0x7FFFFFFFFFFFFFFF) < Long.MAX_VALUE / (l + paramLong) * paramLong) {}
      for (int i = 1;; i = 0)
      {
        paramString = zzt.zza(zzt.this).edit();
        if (i != 0) {
          paramString.putString(this.zzaXE, str);
        }
        paramString.putLong(this.zzaXD, l + paramLong);
        paramString.apply();
        return;
      }
    }
    
    @WorkerThread
    public Pair<String, Long> zzlN()
    {
      zzt.this.zzjk();
      long l = zzlM();
      if (l < this.zzTl) {
        return null;
      }
      if (l > this.zzTl * 2L)
      {
        zzlL();
        return null;
      }
      String str = zzt.zzc(zzt.this).getString(this.zzaXE, null);
      l = zzt.zzc(zzt.this).getLong(this.zzaXD, 0L);
      zzlL();
      if ((str == null) || (l <= 0L)) {
        return zzt.zzaXh;
      }
      return new Pair(str, Long.valueOf(l));
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\measurement\internal\zzt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */