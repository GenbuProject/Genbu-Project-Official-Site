package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.internal.zzqa.zzb;
import com.google.android.gms.internal.zzqb.zza;
import com.google.android.gms.internal.zzqb.zzb;
import com.google.android.gms.internal.zzqb.zzd;
import com.google.android.gms.internal.zzqb.zze;
import com.google.android.gms.internal.zzqb.zzg;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.AppMeasurementReceiver;
import com.google.android.gms.measurement.AppMeasurementService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class zzw
{
  private static zzaa zzaXV;
  private static volatile zzw zzaXW;
  private final Context mContext;
  private final boolean zzQk;
  private final zzd zzaXX;
  private final zzt zzaXY;
  private final zzp zzaXZ;
  private final zzv zzaYa;
  private final zzad zzaYb;
  private final zzu zzaYc;
  private final AppMeasurement zzaYd;
  private final zzaj zzaYe;
  private final zze zzaYf;
  private final zzq zzaYg;
  private final zzac zzaYh;
  private final zzg zzaYi;
  private final zzab zzaYj;
  private final zzn zzaYk;
  private final zzr zzaYl;
  private final zzag zzaYm;
  private final zzc zzaYn;
  private Boolean zzaYo;
  private List<Long> zzaYp;
  private int zzaYq;
  private int zzaYr;
  private final zzmq zzqW;
  
  zzw(zzaa paramzzaa)
  {
    zzx.zzz(paramzzaa);
    this.mContext = paramzzaa.mContext;
    this.zzqW = paramzzaa.zzl(this);
    this.zzaXX = paramzzaa.zza(this);
    Object localObject = paramzzaa.zzb(this);
    ((zzt)localObject).zza();
    this.zzaXY = ((zzt)localObject);
    localObject = paramzzaa.zzc(this);
    ((zzp)localObject).zza();
    this.zzaXZ = ((zzp)localObject);
    zzAo().zzCI().zzj("App measurement is starting up, version", Long.valueOf(zzCp().zzBp()));
    zzAo().zzCI().zzfg("To enable debug logging run: adb shell setprop log.tag.GMPM VERBOSE");
    zzAo().zzCJ().zzfg("Debug logging enabled");
    this.zzaYe = paramzzaa.zzi(this);
    localObject = paramzzaa.zzn(this);
    ((zzg)localObject).zza();
    this.zzaYi = ((zzg)localObject);
    localObject = paramzzaa.zzo(this);
    ((zzn)localObject).zza();
    this.zzaYk = ((zzn)localObject);
    localObject = paramzzaa.zzj(this);
    ((zze)localObject).zza();
    this.zzaYf = ((zze)localObject);
    localObject = paramzzaa.zzr(this);
    ((zzc)localObject).zza();
    this.zzaYn = ((zzc)localObject);
    localObject = paramzzaa.zzk(this);
    ((zzq)localObject).zza();
    this.zzaYg = ((zzq)localObject);
    localObject = paramzzaa.zzm(this);
    ((zzac)localObject).zza();
    this.zzaYh = ((zzac)localObject);
    localObject = paramzzaa.zzh(this);
    ((zzab)localObject).zza();
    this.zzaYj = ((zzab)localObject);
    localObject = paramzzaa.zzq(this);
    ((zzag)localObject).zza();
    this.zzaYm = ((zzag)localObject);
    this.zzaYl = paramzzaa.zzp(this);
    this.zzaYd = paramzzaa.zzg(this);
    localObject = paramzzaa.zze(this);
    ((zzad)localObject).zza();
    this.zzaYb = ((zzad)localObject);
    localObject = paramzzaa.zzf(this);
    ((zzu)localObject).zza();
    this.zzaYc = ((zzu)localObject);
    paramzzaa = paramzzaa.zzd(this);
    paramzzaa.zza();
    this.zzaYa = paramzzaa;
    if (this.zzaYq != this.zzaYr) {
      zzAo().zzCE().zze("Not all components initialized", Integer.valueOf(this.zzaYq), Integer.valueOf(this.zzaYr));
    }
    this.zzQk = true;
    if ((!this.zzaXX.zzkr()) && (!zzCZ()))
    {
      if (!(this.mContext.getApplicationContext() instanceof Application)) {
        break label423;
      }
      if (Build.VERSION.SDK_INT < 14) {
        break label407;
      }
      zzCf().zzDk();
    }
    for (;;)
    {
      this.zzaYa.zzg(new Runnable()
      {
        public void run()
        {
          zzw.this.start();
        }
      });
      return;
      label407:
      zzAo().zzCJ().zzfg("Not tracking deep linking pre-ICS");
      continue;
      label423:
      zzAo().zzCF().zzfg("Application context is not an Application");
    }
  }
  
  private void zzA(List<Long> paramList)
  {
    if (!paramList.isEmpty()) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzac(bool);
      if (this.zzaYp == null) {
        break;
      }
      zzAo().zzCE().zzfg("Set uploading progress before finishing the previous upload");
      return;
    }
    this.zzaYp = new ArrayList(paramList);
  }
  
  @WorkerThread
  private boolean zzDb()
  {
    zzjk();
    return this.zzaYp != null;
  }
  
  private boolean zzDd()
  {
    zzjk();
    zzjv();
    return (zzCj().zzCv()) || (!TextUtils.isEmpty(zzCj().zzCq()));
  }
  
  @WorkerThread
  private void zzDe()
  {
    zzjk();
    zzjv();
    if ((!zzCS()) || (!zzDd()))
    {
      zzCX().unregister();
      zzCY().cancel();
      return;
    }
    long l2 = zzDf();
    if (l2 == 0L)
    {
      zzCX().unregister();
      zzCY().cancel();
      return;
    }
    if (!zzCW().zzlB())
    {
      zzCX().zzly();
      zzCY().cancel();
      return;
    }
    long l3 = zzCo().zzaXl.get();
    long l4 = zzCp().zzBX();
    long l1 = l2;
    if (!zzCk().zzc(l3, l4)) {
      l1 = Math.max(l2, l3 + l4);
    }
    zzCX().unregister();
    l1 -= zzjl().currentTimeMillis();
    if (l1 <= 0L)
    {
      zzCY().zzt(1L);
      return;
    }
    zzAo().zzCK().zzj("Upload scheduled in approximately ms", Long.valueOf(l1));
    zzCY().zzt(l1);
  }
  
  private long zzDf()
  {
    long l5 = zzjl().currentTimeMillis();
    long l1 = zzCp().zzCa();
    long l2 = zzCp().zzBY();
    long l6 = zzCo().zzaXj.get();
    long l4 = zzCo().zzaXk.get();
    long l3 = Math.max(zzCj().zzCt(), zzCj().zzCu());
    if (l3 == 0L) {
      l2 = 0L;
    }
    do
    {
      do
      {
        return l2;
        l3 = l5 - Math.abs(l3 - l5);
        l6 = Math.abs(l6 - l5);
        l4 = l5 - Math.abs(l4 - l5);
        l5 = Math.max(l5 - l6, l4);
        l1 += l3;
        if (!zzCk().zzc(l5, l2)) {
          l1 = l5 + l2;
        }
        l2 = l1;
      } while (l4 == 0L);
      l2 = l1;
    } while (l4 < l3);
    int i = 0;
    for (;;)
    {
      if (i >= zzCp().zzCc()) {
        break label223;
      }
      l1 += (1 << i) * zzCp().zzCb();
      l2 = l1;
      if (l1 > l4) {
        break;
      }
      i += 1;
    }
    label223:
    return 0L;
  }
  
  @WorkerThread
  private void zza(int paramInt, Throwable paramThrowable, byte[] paramArrayOfByte)
  {
    int i = 0;
    zzjk();
    zzjv();
    byte[] arrayOfByte = paramArrayOfByte;
    if (paramArrayOfByte == null) {
      arrayOfByte = new byte[0];
    }
    paramArrayOfByte = this.zzaYp;
    this.zzaYp = null;
    if (((paramInt == 200) || (paramInt == 204)) && (paramThrowable == null))
    {
      zzCo().zzaXj.set(zzjl().currentTimeMillis());
      zzCo().zzaXk.set(0L);
      zzDe();
      zzAo().zzCK().zze("Successful upload. Got network response. code, size", Integer.valueOf(paramInt), Integer.valueOf(arrayOfByte.length));
      zzCj().beginTransaction();
      try
      {
        paramThrowable = paramArrayOfByte.iterator();
        while (paramThrowable.hasNext())
        {
          paramArrayOfByte = (Long)paramThrowable.next();
          zzCj().zzZ(paramArrayOfByte.longValue());
        }
      }
      finally
      {
        zzCj().endTransaction();
      }
      zzCj().endTransaction();
      if ((zzCW().zzlB()) && (zzDd()))
      {
        zzDc();
        return;
      }
      zzDe();
      return;
    }
    zzAo().zzCK().zze("Network upload failed. Will retry later. code, error", Integer.valueOf(paramInt), paramThrowable);
    zzCo().zzaXk.set(zzjl().currentTimeMillis());
    if ((paramInt == 503) || (paramInt == 429)) {
      i = 1;
    }
    if (i != 0) {
      zzCo().zzaXl.set(zzjl().currentTimeMillis());
    }
    zzDe();
  }
  
  private void zza(zzy paramzzy)
  {
    if (paramzzy == null) {
      throw new IllegalStateException("Component not created");
    }
  }
  
  private void zza(zzz paramzzz)
  {
    if (paramzzz == null) {
      throw new IllegalStateException("Component not created");
    }
    if (!paramzzz.isInitialized()) {
      throw new IllegalStateException("Component not initialized");
    }
  }
  
  private zzqb.zza[] zza(String paramString, zzqb.zzg[] paramArrayOfzzg, zzqb.zzb[] paramArrayOfzzb)
  {
    zzx.zzcM(paramString);
    return zzCe().zza(paramString, paramArrayOfzzb, paramArrayOfzzg);
  }
  
  /* Error */
  public static zzw zzaT(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 76	com/google/android/gms/common/internal/zzx:zzz	(Ljava/lang/Object;)Ljava/lang/Object;
    //   4: pop
    //   5: aload_0
    //   6: invokevirtual 302	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   9: invokestatic 76	com/google/android/gms/common/internal/zzx:zzz	(Ljava/lang/Object;)Ljava/lang/Object;
    //   12: pop
    //   13: getstatic 570	com/google/android/gms/measurement/internal/zzw:zzaXW	Lcom/google/android/gms/measurement/internal/zzw;
    //   16: ifnonnull +32 -> 48
    //   19: ldc 2
    //   21: monitorenter
    //   22: getstatic 570	com/google/android/gms/measurement/internal/zzw:zzaXW	Lcom/google/android/gms/measurement/internal/zzw;
    //   25: ifnonnull +20 -> 45
    //   28: getstatic 572	com/google/android/gms/measurement/internal/zzw:zzaXV	Lcom/google/android/gms/measurement/internal/zzaa;
    //   31: ifnull +21 -> 52
    //   34: getstatic 572	com/google/android/gms/measurement/internal/zzw:zzaXV	Lcom/google/android/gms/measurement/internal/zzaa;
    //   37: astore_0
    //   38: aload_0
    //   39: invokevirtual 576	com/google/android/gms/measurement/internal/zzaa:zzDj	()Lcom/google/android/gms/measurement/internal/zzw;
    //   42: putstatic 570	com/google/android/gms/measurement/internal/zzw:zzaXW	Lcom/google/android/gms/measurement/internal/zzw;
    //   45: ldc 2
    //   47: monitorexit
    //   48: getstatic 570	com/google/android/gms/measurement/internal/zzw:zzaXW	Lcom/google/android/gms/measurement/internal/zzw;
    //   51: areturn
    //   52: new 78	com/google/android/gms/measurement/internal/zzaa
    //   55: dup
    //   56: aload_0
    //   57: invokespecial 579	com/google/android/gms/measurement/internal/zzaa:<init>	(Landroid/content/Context;)V
    //   60: astore_0
    //   61: goto -23 -> 38
    //   64: astore_0
    //   65: ldc 2
    //   67: monitorexit
    //   68: aload_0
    //   69: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	70	0	paramContext	Context
    // Exception table:
    //   from	to	target	type
    //   22	38	64	finally
    //   38	45	64	finally
    //   45	48	64	finally
    //   52	61	64	finally
    //   65	68	64	finally
  }
  
  private void zzb(Bundle paramBundle, int paramInt)
  {
    if (paramBundle.getLong("_err") == 0L) {
      paramBundle.putLong("_err", paramInt);
    }
  }
  
  @WorkerThread
  private void zzb(String paramString, int paramInt, Throwable paramThrowable, byte[] paramArrayOfByte)
  {
    int j = 0;
    zzjk();
    zzjv();
    zzx.zzcM(paramString);
    byte[] arrayOfByte = paramArrayOfByte;
    if (paramArrayOfByte == null) {
      arrayOfByte = new byte[0];
    }
    zzCj().beginTransaction();
    label106:
    int i;
    for (;;)
    {
      try
      {
        paramArrayOfByte = zzCj().zzeY(paramString);
        if ((paramInt == 200) || (paramInt == 204)) {
          break;
        }
        boolean bool;
        if (paramInt == 304)
        {
          break;
          if (zzCl().zzfk(paramString) == null)
          {
            bool = zzCl().zze(paramString, null);
            if (bool) {}
          }
        }
        else
        {
          i = 0;
          break label378;
          label112:
          bool = zzCl().zze(paramString, arrayOfByte);
          if (!bool) {
            return;
          }
        }
        paramArrayOfByte.zzT(zzjl().currentTimeMillis());
        zzCj().zza(paramArrayOfByte);
        if (paramInt == 404)
        {
          zzAo().zzCF().zzfg("Config not found. Using empty config");
          if ((zzCW().zzlB()) && (zzDd()))
          {
            zzDc();
            label201:
            zzCj().setTransactionSuccessful();
          }
        }
        else
        {
          zzAo().zzCK().zze("Successfully fetched config. Got network response. code, size", Integer.valueOf(paramInt), Integer.valueOf(arrayOfByte.length));
          continue;
        }
        zzDe();
      }
      finally
      {
        zzCj().endTransaction();
      }
    }
    label259:
    paramArrayOfByte.zzU(zzjl().currentTimeMillis());
    zzCj().zza(paramArrayOfByte);
    zzAo().zzCK().zze("Fetching config failed. code, error", Integer.valueOf(paramInt), paramThrowable);
    zzCo().zzaXk.set(zzjl().currentTimeMillis());
    if (paramInt != 503)
    {
      i = j;
      if (paramInt == 429) {}
    }
    for (;;)
    {
      if (i != 0) {
        zzCo().zzaXl.set(zzjl().currentTimeMillis());
      }
      zzDe();
      break label201;
      if (paramThrowable != null) {
        break label106;
      }
      i = 1;
      label378:
      if ((i == 0) && (paramInt != 404)) {
        break label259;
      }
      if (paramInt == 404) {
        break;
      }
      if (paramInt != 304) {
        break label112;
      }
      break;
      i = 1;
    }
  }
  
  @WorkerThread
  private void zze(AppMetadata paramAppMetadata)
  {
    int k = 1;
    zzjk();
    zzjv();
    zzx.zzz(paramAppMetadata);
    zzx.zzcM(paramAppMetadata.packageName);
    zza localzza2 = zzCj().zzeY(paramAppMetadata.packageName);
    String str = zzCo().zzfi(paramAppMetadata.packageName);
    int i = 0;
    zza localzza1;
    if (localzza2 == null)
    {
      localzza1 = new zza(this, paramAppMetadata.packageName);
      localzza1.zzeM(zzCo().zzCM());
      localzza1.zzeO(str);
      i = 1;
      int j = i;
      if (!TextUtils.isEmpty(paramAppMetadata.zzaVt))
      {
        j = i;
        if (!paramAppMetadata.zzaVt.equals(localzza1.zzBk()))
        {
          localzza1.zzeN(paramAppMetadata.zzaVt);
          j = 1;
        }
      }
      i = j;
      if (paramAppMetadata.zzaVv != 0L)
      {
        i = j;
        if (paramAppMetadata.zzaVv != localzza1.zzBp())
        {
          localzza1.zzQ(paramAppMetadata.zzaVv);
          i = 1;
        }
      }
      j = i;
      if (!TextUtils.isEmpty(paramAppMetadata.zzaMV))
      {
        j = i;
        if (!paramAppMetadata.zzaMV.equals(localzza1.zzli()))
        {
          localzza1.setAppVersion(paramAppMetadata.zzaMV);
          j = 1;
        }
      }
      i = j;
      if (!TextUtils.isEmpty(paramAppMetadata.zzaVu))
      {
        i = j;
        if (!paramAppMetadata.zzaVu.equals(localzza1.zzBo()))
        {
          localzza1.zzeP(paramAppMetadata.zzaVu);
          i = 1;
        }
      }
      if (paramAppMetadata.zzaVw != localzza1.zzBq())
      {
        localzza1.zzR(paramAppMetadata.zzaVw);
        i = 1;
      }
      if (paramAppMetadata.zzaVy == localzza1.zzAr()) {
        break label356;
      }
      localzza1.setMeasurementEnabled(paramAppMetadata.zzaVy);
      i = k;
    }
    label356:
    for (;;)
    {
      if (i != 0) {
        zzCj().zza(localzza1);
      }
      return;
      localzza1 = localzza2;
      if (str.equals(localzza2.zzBl())) {
        break;
      }
      localzza2.zzeO(str);
      localzza2.zzeM(zzCo().zzCM());
      i = 1;
      localzza1 = localzza2;
      break;
    }
  }
  
  private boolean zzg(String paramString, long paramLong)
  {
    zzCj().beginTransaction();
    for (;;)
    {
      int i;
      int j;
      long l;
      try
      {
        zza localzza = new zza(null);
        zzCj().zza(paramString, paramLong, localzza);
        if (localzza.isEmpty()) {
          break label588;
        }
        zzqb.zze localzze = localzza.zzaYt;
        localzze.zzbam = new zzqb.zzb[localzza.zzpH.size()];
        i = 0;
        j = 0;
        if (j < localzza.zzpH.size())
        {
          if (zzCl().zzP(localzza.zzaYt.appId, ((zzqb.zzb)localzza.zzpH.get(j)).name))
          {
            zzAo().zzCK().zzj("Dropping blacklisted raw event", ((zzqb.zzb)localzza.zzpH.get(j)).name);
            break label610;
          }
          localzze.zzbam[i] = ((zzqb.zzb)localzza.zzpH.get(j));
          i += 1;
          break label610;
        }
        if (i < localzza.zzpH.size()) {
          localzze.zzbam = ((zzqb.zzb[])Arrays.copyOf(localzze.zzbam, i));
        }
        localzze.zzbaF = zza(localzza.zzaYt.appId, localzza.zzaYt.zzban, localzze.zzbam);
        localzze.zzbap = localzze.zzbam[0].zzbaf;
        localzze.zzbaq = localzze.zzbam[0].zzbaf;
        i = 1;
        if (i < localzze.zzbam.length)
        {
          paramString = localzze.zzbam[i];
          if (paramString.zzbaf.longValue() < localzze.zzbap.longValue()) {
            localzze.zzbap = paramString.zzbaf;
          }
          if (paramString.zzbaf.longValue() <= localzze.zzbaq.longValue()) {
            break label619;
          }
          localzze.zzbaq = paramString.zzbaf;
          break label619;
        }
        String str = localzza.zzaYt.appId;
        zza localzza1 = zzCj().zzeY(str);
        if (localzza1 == null)
        {
          zzAo().zzCE().zzfg("Bundling raw events w/o app info");
          localzze.zzaVx = zzAo().zzCL();
          zzCj().zza(localzze);
          zzCj().zzz(localzza.zzaYu);
          zzCj().zzfc(str);
          zzCj().setTransactionSuccessful();
          return true;
        }
        paramLong = localzza1.zzBn();
        if (paramLong != 0L)
        {
          paramString = Long.valueOf(paramLong);
          localzze.zzbas = paramString;
          l = localzza1.zzBm();
          if (l != 0L) {
            break label604;
          }
          if (paramLong == 0L) {
            break label583;
          }
          paramString = Long.valueOf(paramLong);
          localzze.zzbar = paramString;
          localzza1.zzBu();
          localzze.zzbaD = Integer.valueOf((int)localzza1.zzBr());
          localzza1.zzO(localzze.zzbap.longValue());
          localzza1.zzP(localzze.zzbaq.longValue());
          zzCj().zza(localzza1);
          continue;
        }
        paramString = null;
      }
      finally
      {
        zzCj().endTransaction();
      }
      continue;
      label583:
      paramString = null;
      continue;
      label588:
      zzCj().setTransactionSuccessful();
      zzCj().endTransaction();
      return false;
      label604:
      paramLong = l;
      continue;
      label610:
      j += 1;
      continue;
      label619:
      i += 1;
    }
  }
  
  public Context getContext()
  {
    return this.mContext;
  }
  
  @WorkerThread
  protected void start()
  {
    zzjk();
    if ((zzCZ()) && ((!this.zzaYa.isInitialized()) || (this.zzaYa.zzDi())))
    {
      zzAo().zzCE().zzfg("Scheduler shutting down before Scion.start() called");
      return;
    }
    zzCj().zzCr();
    if (!zzCS()) {
      if (zzCo().zzAr())
      {
        if (!zzCk().zzbk("android.permission.INTERNET")) {
          zzAo().zzCE().zzfg("App is missing INTERNET permission");
        }
        if (!zzCk().zzbk("android.permission.ACCESS_NETWORK_STATE")) {
          zzAo().zzCE().zzfg("App is missing ACCESS_NETWORK_STATE permission");
        }
        if (!AppMeasurementReceiver.zzY(getContext())) {
          zzAo().zzCE().zzfg("AppMeasurementReceiver not registered/enabled");
        }
        if (!AppMeasurementService.zzZ(getContext())) {
          zzAo().zzCE().zzfg("AppMeasurementService not registered/enabled");
        }
        zzAo().zzCE().zzfg("Uploading is not possible. App measurement disabled");
      }
    }
    for (;;)
    {
      zzDe();
      return;
      if ((!zzCp().zzkr()) && (!zzCZ()) && (!TextUtils.isEmpty(zzCg().zzBk()))) {
        zzCf().zzDl();
      }
    }
  }
  
  public zzp zzAo()
  {
    zza(this.zzaXZ);
    return this.zzaXZ;
  }
  
  @WorkerThread
  protected boolean zzCS()
  {
    boolean bool2 = true;
    zzjv();
    zzjk();
    if (this.zzaYo == null)
    {
      if ((!zzCk().zzbk("android.permission.INTERNET")) || (!zzCk().zzbk("android.permission.ACCESS_NETWORK_STATE")) || (!AppMeasurementReceiver.zzY(getContext())) || (!AppMeasurementService.zzZ(getContext()))) {
        break label124;
      }
      bool1 = true;
      this.zzaYo = Boolean.valueOf(bool1);
      if ((this.zzaYo.booleanValue()) && (!zzCp().zzkr())) {
        if (TextUtils.isEmpty(zzCg().zzBk())) {
          break label129;
        }
      }
    }
    label124:
    label129:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      this.zzaYo = Boolean.valueOf(bool1);
      return this.zzaYo.booleanValue();
      bool1 = false;
      break;
    }
  }
  
  public zzp zzCT()
  {
    if ((this.zzaXZ != null) && (this.zzaXZ.isInitialized())) {
      return this.zzaXZ;
    }
    return null;
  }
  
  zzv zzCU()
  {
    return this.zzaYa;
  }
  
  public AppMeasurement zzCV()
  {
    return this.zzaYd;
  }
  
  public zzq zzCW()
  {
    zza(this.zzaYg);
    return this.zzaYg;
  }
  
  public zzr zzCX()
  {
    if (this.zzaYl == null) {
      throw new IllegalStateException("Network broadcast receiver not created");
    }
    return this.zzaYl;
  }
  
  public zzag zzCY()
  {
    zza(this.zzaYm);
    return this.zzaYm;
  }
  
  protected boolean zzCZ()
  {
    return false;
  }
  
  public zzc zzCe()
  {
    zza(this.zzaYn);
    return this.zzaYn;
  }
  
  public zzab zzCf()
  {
    zza(this.zzaYj);
    return this.zzaYj;
  }
  
  public zzn zzCg()
  {
    zza(this.zzaYk);
    return this.zzaYk;
  }
  
  public zzg zzCh()
  {
    zza(this.zzaYi);
    return this.zzaYi;
  }
  
  public zzac zzCi()
  {
    zza(this.zzaYh);
    return this.zzaYh;
  }
  
  public zze zzCj()
  {
    zza(this.zzaYf);
    return this.zzaYf;
  }
  
  public zzaj zzCk()
  {
    zza(this.zzaYe);
    return this.zzaYe;
  }
  
  public zzu zzCl()
  {
    zza(this.zzaYc);
    return this.zzaYc;
  }
  
  public zzad zzCm()
  {
    zza(this.zzaYb);
    return this.zzaYb;
  }
  
  public zzv zzCn()
  {
    zza(this.zzaYa);
    return this.zzaYa;
  }
  
  public zzt zzCo()
  {
    zza(this.zzaXY);
    return this.zzaXY;
  }
  
  public zzd zzCp()
  {
    return this.zzaXX;
  }
  
  long zzDa()
  {
    return (zzjl().currentTimeMillis() + zzCo().zzCN()) / 1000L / 60L / 60L / 24L;
  }
  
  @WorkerThread
  public void zzDc()
  {
    Object localObject4 = null;
    int j = 0;
    zzjk();
    zzjv();
    if (!zzCp().zzkr())
    {
      localObject1 = zzCo().zzCP();
      if (localObject1 == null) {
        zzAo().zzCF().zzfg("Upload data called on the client side before use of service was decided");
      }
    }
    long l1;
    String str;
    int i;
    do
    {
      return;
      if (((Boolean)localObject1).booleanValue())
      {
        zzAo().zzCE().zzfg("Upload called in the client side when service should be used");
        return;
      }
      if (zzDb())
      {
        zzAo().zzCF().zzfg("Uploading requested multiple times");
        return;
      }
      if (!zzCW().zzlB())
      {
        zzAo().zzCF().zzfg("Network not connected, ignoring upload request");
        zzDe();
        return;
      }
      l1 = zzjl().currentTimeMillis();
      zzad(l1 - zzCp().zzBW());
      long l2 = zzCo().zzaXj.get();
      if (l2 != 0L) {
        zzAo().zzCJ().zzj("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(l1 - l2)));
      }
      str = zzCj().zzCq();
      if (TextUtils.isEmpty(str)) {
        break;
      }
      i = zzCp().zzeU(str);
      int k = zzCp().zzeV(str);
      localObject4 = zzCj().zzn(str, i, k);
    } while (((List)localObject4).isEmpty());
    Object localObject1 = ((List)localObject4).iterator();
    Object localObject5;
    do
    {
      if (!((Iterator)localObject1).hasNext()) {
        break;
      }
      localObject5 = (zzqb.zze)((Pair)((Iterator)localObject1).next()).first;
    } while (TextUtils.isEmpty(((zzqb.zze)localObject5).zzbaz));
    Object localObject3;
    for (localObject1 = ((zzqb.zze)localObject5).zzbaz;; localObject3 = null)
    {
      if (localObject1 != null)
      {
        i = 0;
        if (i < ((List)localObject4).size())
        {
          localObject5 = (zzqb.zze)((Pair)((List)localObject4).get(i)).first;
          if (TextUtils.isEmpty(((zzqb.zze)localObject5).zzbaz)) {}
          while (((zzqb.zze)localObject5).zzbaz.equals(localObject1))
          {
            i += 1;
            break;
          }
        }
      }
      for (localObject1 = ((List)localObject4).subList(0, i);; localObject3 = localObject4)
      {
        localObject5 = new zzqb.zzd();
        ((zzqb.zzd)localObject5).zzbaj = new zzqb.zze[((List)localObject1).size()];
        localObject4 = new ArrayList(((List)localObject1).size());
        i = j;
        while (i < ((zzqb.zzd)localObject5).zzbaj.length)
        {
          ((zzqb.zzd)localObject5).zzbaj[i] = ((zzqb.zze)((Pair)((List)localObject1).get(i)).first);
          ((List)localObject4).add(((Pair)((List)localObject1).get(i)).second);
          localObject5.zzbaj[i].zzbay = Long.valueOf(zzCp().zzBp());
          localObject5.zzbaj[i].zzbao = Long.valueOf(l1);
          localObject5.zzbaj[i].zzbaE = Boolean.valueOf(zzCp().zzkr());
          i += 1;
        }
        if (zzAo().zzQ(2)) {}
        for (localObject1 = zzaj.zzb((zzqb.zzd)localObject5);; localObject3 = null)
        {
          Object localObject7 = zzCk().zza((zzqb.zzd)localObject5);
          Object localObject6 = zzCp().zzBV();
          try
          {
            URL localURL = new URL((String)localObject6);
            zzA((List)localObject4);
            zzCo().zzaXk.set(l1);
            localObject4 = "?";
            if (((zzqb.zzd)localObject5).zzbaj.length > 0) {
              localObject4 = localObject5.zzbaj[0].appId;
            }
            zzAo().zzCK().zzd("Uploading data. app, uncompressed size, data", localObject4, Integer.valueOf(localObject7.length), localObject1);
            zzCW().zza(str, localURL, (byte[])localObject7, null, new zzq.zza()
            {
              public void zza(String paramAnonymousString, int paramAnonymousInt, Throwable paramAnonymousThrowable, byte[] paramAnonymousArrayOfByte)
              {
                zzw.zza(zzw.this, paramAnonymousInt, paramAnonymousThrowable, paramAnonymousArrayOfByte);
              }
            });
            return;
          }
          catch (MalformedURLException localMalformedURLException1)
          {
            zzAo().zzCE().zzj("Failed to parse upload URL. Not uploading", localObject6);
            return;
          }
          localObject5 = zzCj().zzaa(l1 - zzCp().zzBW());
          if (TextUtils.isEmpty((CharSequence)localObject5)) {
            break;
          }
          Object localObject2 = zzCj().zzeY((String)localObject5);
          if (localObject2 == null) {
            break;
          }
          str = zzCp().zzH(((zza)localObject2).zzBk(), ((zza)localObject2).zzBj());
          try
          {
            localObject6 = new URL(str);
            zzAo().zzCK().zzj("Fetching remote configuration", ((zza)localObject2).zzwK());
            localObject7 = zzCl().zzfk(((zza)localObject2).zzwK());
            localObject2 = localObject4;
            if (localObject7 != null)
            {
              localObject2 = localObject4;
              if (((zzqa.zzb)localObject7).zzaZT != null)
              {
                localObject2 = new ArrayMap();
                ((Map)localObject2).put("Config-Version", String.valueOf(((zzqa.zzb)localObject7).zzaZT));
              }
            }
            zzCW().zza((String)localObject5, (URL)localObject6, (Map)localObject2, new zzq.zza()
            {
              public void zza(String paramAnonymousString, int paramAnonymousInt, Throwable paramAnonymousThrowable, byte[] paramAnonymousArrayOfByte)
              {
                zzw.zza(zzw.this, paramAnonymousString, paramAnonymousInt, paramAnonymousThrowable, paramAnonymousArrayOfByte);
              }
            });
            return;
          }
          catch (MalformedURLException localMalformedURLException2)
          {
            zzAo().zzCE().zzj("Failed to parse config URL. Not fetching", str);
            return;
          }
        }
      }
    }
  }
  
  void zzDg()
  {
    this.zzaYr += 1;
  }
  
  void zzE(String paramString, int paramInt) {}
  
  public void zzJ(boolean paramBoolean)
  {
    zzDe();
  }
  
  void zza(EventParcel paramEventParcel, String paramString)
  {
    zza localzza = zzCj().zzeY(paramString);
    if ((localzza == null) || (TextUtils.isEmpty(localzza.zzli())))
    {
      zzAo().zzCJ().zzj("No app data available; dropping event", paramString);
      return;
    }
    try
    {
      String str = getContext().getPackageManager().getPackageInfo(paramString, 0).versionName;
      if ((localzza.zzli() != null) && (!localzza.zzli().equals(str)))
      {
        zzAo().zzCF().zzj("App version does not match; dropping event", paramString);
        return;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      zzAo().zzCF().zzj("Could not find package", paramString);
      zzb(paramEventParcel, new AppMetadata(paramString, localzza.zzBk(), localzza.zzli(), localzza.zzBo(), localzza.zzBp(), localzza.zzBq(), null, localzza.zzAr(), false));
    }
  }
  
  void zza(zzh paramzzh, AppMetadata paramAppMetadata)
  {
    zzjk();
    zzjv();
    zzx.zzz(paramzzh);
    zzx.zzz(paramAppMetadata);
    zzx.zzcM(paramzzh.zzaUa);
    zzx.zzac(paramzzh.zzaUa.equals(paramAppMetadata.packageName));
    zzqb.zze localzze = new zzqb.zze();
    localzze.zzbal = Integer.valueOf(1);
    localzze.zzbat = "android";
    localzze.appId = paramAppMetadata.packageName;
    localzze.zzaVu = paramAppMetadata.zzaVu;
    localzze.zzaMV = paramAppMetadata.zzaMV;
    localzze.zzbax = Long.valueOf(paramAppMetadata.zzaVv);
    localzze.zzaVt = paramAppMetadata.zzaVt;
    if (paramAppMetadata.zzaVw == 0L) {}
    for (Object localObject = null;; localObject = Long.valueOf(paramAppMetadata.zzaVw))
    {
      localzze.zzbaC = ((Long)localObject);
      localObject = zzCo().zzfh(paramAppMetadata.packageName);
      if ((localObject != null) && (((Pair)localObject).first != null) && (((Pair)localObject).second != null))
      {
        localzze.zzbaz = ((String)((Pair)localObject).first);
        localzze.zzbaA = ((Boolean)((Pair)localObject).second);
      }
      localzze.zzbau = zzCh().zzht();
      localzze.osVersion = zzCh().zzCy();
      localzze.zzbaw = Integer.valueOf((int)zzCh().zzCz());
      localzze.zzbav = zzCh().zzCA();
      localzze.zzbay = null;
      localzze.zzbao = null;
      localzze.zzbap = null;
      localzze.zzbaq = null;
      zza localzza = zzCj().zzeY(paramAppMetadata.packageName);
      localObject = localzza;
      if (localzza == null)
      {
        localObject = new zza(this, paramAppMetadata.packageName);
        ((zza)localObject).zzeM(zzCo().zzCM());
        ((zza)localObject).zzeN(paramAppMetadata.zzaVt);
        ((zza)localObject).zzeO(zzCo().zzfi(paramAppMetadata.packageName));
        ((zza)localObject).zzS(0L);
        ((zza)localObject).zzO(0L);
        ((zza)localObject).zzP(0L);
        ((zza)localObject).setAppVersion(paramAppMetadata.zzaMV);
        ((zza)localObject).zzeP(paramAppMetadata.zzaVu);
        ((zza)localObject).zzQ(paramAppMetadata.zzaVv);
        ((zza)localObject).zzR(paramAppMetadata.zzaVw);
        ((zza)localObject).setMeasurementEnabled(paramAppMetadata.zzaVy);
        zzCj().zza((zza)localObject);
      }
      localzze.zzbaB = ((zza)localObject).zzBj();
      paramAppMetadata = zzCj().zzeX(paramAppMetadata.packageName);
      localzze.zzban = new zzqb.zzg[paramAppMetadata.size()];
      int i = 0;
      while (i < paramAppMetadata.size())
      {
        localObject = new zzqb.zzg();
        localzze.zzban[i] = localObject;
        ((zzqb.zzg)localObject).name = ((zzai)paramAppMetadata.get(i)).mName;
        ((zzqb.zzg)localObject).zzbaJ = Long.valueOf(((zzai)paramAppMetadata.get(i)).zzaZp);
        zzCk().zza((zzqb.zzg)localObject, ((zzai)paramAppMetadata.get(i)).zzNc);
        i += 1;
      }
    }
    try
    {
      long l = zzCj().zzb(localzze);
      zzCj().zza(paramzzh, l);
      return;
    }
    catch (IOException paramzzh)
    {
      zzAo().zzCE().zzj("Data loss. Failed to insert raw event metadata", paramzzh);
    }
  }
  
  boolean zzad(long paramLong)
  {
    return zzg(null, paramLong);
  }
  
  void zzb(EventParcel paramEventParcel, AppMetadata paramAppMetadata)
  {
    long l1 = System.nanoTime();
    zzjk();
    zzjv();
    String str = paramAppMetadata.packageName;
    zzx.zzcM(str);
    if (TextUtils.isEmpty(paramAppMetadata.zzaVt)) {
      return;
    }
    if (!paramAppMetadata.zzaVy)
    {
      zze(paramAppMetadata);
      return;
    }
    if (zzCl().zzP(str, paramEventParcel.name))
    {
      zzAo().zzCK().zzj("Dropping blacklisted event", paramEventParcel.name);
      return;
    }
    if (zzAo().zzQ(2)) {
      zzAo().zzCK().zzj("Logging event", paramEventParcel);
    }
    zzCj().beginTransaction();
    Bundle localBundle;
    boolean bool2;
    boolean bool3;
    for (;;)
    {
      try
      {
        localBundle = paramEventParcel.zzaVV.zzCC();
        zze(paramAppMetadata);
        zzai localzzai;
        if (("_iap".equals(paramEventParcel.name)) || ("ecommerce_purchase".equals(paramEventParcel.name)))
        {
          localObject = localBundle.getString("currency");
          l2 = localBundle.getLong("value");
          if ((!TextUtils.isEmpty((CharSequence)localObject)) && (l2 > 0L))
          {
            localObject = ((String)localObject).toUpperCase(Locale.US);
            if (((String)localObject).matches("[A-Z]{3}"))
            {
              localObject = "_ltv_" + (String)localObject;
              localzzai = zzCj().zzK(str, (String)localObject);
              if ((localzzai != null) && ((localzzai.zzNc instanceof Long))) {
                continue;
              }
              zzCj().zzA(str, zzCp().zzeT(str) - 1);
              localObject = new zzai(str, (String)localObject, zzjl().currentTimeMillis(), Long.valueOf(l2));
              zzCj().zza((zzai)localObject);
            }
          }
        }
        bool2 = zzaj.zzfq(paramEventParcel.name);
        bool3 = zzaj.zzI(localBundle);
        localObject = zzCj();
        l2 = zzDa();
        boolean bool1;
        if ((bool2) && (bool3))
        {
          bool1 = true;
          localObject = ((zze)localObject).zza(l2, str, bool2, bool1);
          l2 = ((zze.zza)localObject).zzaVF - zzCp().zzBI();
          if (l2 <= 0L) {
            break;
          }
          if (l2 % 1000L == 1L) {
            zzAo().zzCF().zzj("Data loss. Too many events logged. count", Long.valueOf(((zze.zza)localObject).zzaVF));
          }
          zzCj().setTransactionSuccessful();
          return;
          long l3 = ((Long)localzzai.zzNc).longValue();
          localObject = new zzai(str, (String)localObject, zzjl().currentTimeMillis(), Long.valueOf(l2 + l3));
        }
        else
        {
          bool1 = false;
        }
      }
      finally
      {
        zzCj().endTransaction();
      }
    }
    if (bool2)
    {
      l2 = ((zze.zza)localObject).zzaVE - zzCp().zzBJ();
      if (l2 > 0L)
      {
        zzE(str, 2);
        if (l2 % 1000L == 1L) {
          zzAo().zzCF().zzj("Data loss. Too many public events logged. count", Long.valueOf(((zze.zza)localObject).zzaVE));
        }
        zzCj().setTransactionSuccessful();
        zzCj().endTransaction();
        return;
      }
    }
    if ((bool2) && (bool3) && (((zze.zza)localObject).zzaVG - zzCp().zzBK() > 0L))
    {
      localBundle.remove("_c");
      zzb(localBundle, 4);
    }
    long l2 = zzCj().zzeZ(str);
    if (l2 > 0L) {
      zzAo().zzCF().zzj("Data lost. Too many events stored on disk, deleted", Long.valueOf(l2));
    }
    paramEventParcel = new zzh(this, paramEventParcel.zzaVW, str, paramEventParcel.name, paramEventParcel.zzaVX, 0L, localBundle);
    Object localObject = zzCj().zzI(str, paramEventParcel.mName);
    if (localObject == null) {
      if (zzCj().zzfd(str) >= zzCp().zzBH())
      {
        zzAo().zzCF().zze("Too many event names used, ignoring event. name, supported count", paramEventParcel.mName, Integer.valueOf(zzCp().zzBH()));
        zzE(str, 1);
        zzCj().endTransaction();
        return;
      }
    }
    for (localObject = new zzi(str, paramEventParcel.mName, 0L, 0L, paramEventParcel.zzaez);; localObject = ((zzi)localObject).zzab(paramEventParcel.zzaez))
    {
      zzCj().zza((zzi)localObject);
      zza(paramEventParcel, paramAppMetadata);
      zzCj().setTransactionSuccessful();
      if (zzAo().zzQ(2)) {
        zzAo().zzCK().zzj("Event recorded", paramEventParcel);
      }
      zzCj().endTransaction();
      zzDe();
      zzAo().zzCK().zzj("Background event processing time, ms", Long.valueOf((System.nanoTime() - l1 + 500000L) / 1000000L));
      return;
      paramEventParcel = paramEventParcel.zza(this, ((zzi)localObject).zzaVR);
    }
  }
  
  /* Error */
  @WorkerThread
  void zzb(UserAttributeParcel paramUserAttributeParcel, AppMetadata paramAppMetadata)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 357	com/google/android/gms/measurement/internal/zzw:zzjk	()V
    //   4: aload_0
    //   5: invokevirtual 362	com/google/android/gms/measurement/internal/zzw:zzjv	()V
    //   8: aload_2
    //   9: getfield 649	com/google/android/gms/measurement/internal/AppMetadata:zzaVt	Ljava/lang/String;
    //   12: invokestatic 378	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   15: ifeq +4 -> 19
    //   18: return
    //   19: aload_2
    //   20: getfield 699	com/google/android/gms/measurement/internal/AppMetadata:zzaVy	Z
    //   23: ifne +9 -> 32
    //   26: aload_0
    //   27: aload_2
    //   28: invokespecial 1196	com/google/android/gms/measurement/internal/zzw:zze	(Lcom/google/android/gms/measurement/internal/AppMetadata;)V
    //   31: return
    //   32: aload_0
    //   33: invokevirtual 433	com/google/android/gms/measurement/internal/zzw:zzCk	()Lcom/google/android/gms/measurement/internal/zzaj;
    //   36: aload_1
    //   37: getfield 1372	com/google/android/gms/measurement/internal/UserAttributeParcel:name	Ljava/lang/String;
    //   40: invokevirtual 1375	com/google/android/gms/measurement/internal/zzaj:zzfs	(Ljava/lang/String;)V
    //   43: aload_0
    //   44: invokevirtual 433	com/google/android/gms/measurement/internal/zzw:zzCk	()Lcom/google/android/gms/measurement/internal/zzaj;
    //   47: aload_1
    //   48: getfield 1372	com/google/android/gms/measurement/internal/UserAttributeParcel:name	Ljava/lang/String;
    //   51: aload_1
    //   52: invokevirtual 1378	com/google/android/gms/measurement/internal/UserAttributeParcel:getValue	()Ljava/lang/Object;
    //   55: invokevirtual 1381	com/google/android/gms/measurement/internal/zzaj:zzm	(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;
    //   58: astore 4
    //   60: aload 4
    //   62: ifnull -44 -> 18
    //   65: new 1163	com/google/android/gms/measurement/internal/zzai
    //   68: dup
    //   69: aload_2
    //   70: getfield 631	com/google/android/gms/measurement/internal/AppMetadata:packageName	Ljava/lang/String;
    //   73: aload_1
    //   74: getfield 1372	com/google/android/gms/measurement/internal/UserAttributeParcel:name	Ljava/lang/String;
    //   77: aload_1
    //   78: getfield 1384	com/google/android/gms/measurement/internal/UserAttributeParcel:zzaZm	J
    //   81: aload 4
    //   83: invokespecial 1263	com/google/android/gms/measurement/internal/zzai:<init>	(Ljava/lang/String;Ljava/lang/String;JLjava/lang/Object;)V
    //   86: astore_1
    //   87: aload_0
    //   88: invokevirtual 115	com/google/android/gms/measurement/internal/zzw:zzAo	()Lcom/google/android/gms/measurement/internal/zzp;
    //   91: invokevirtual 152	com/google/android/gms/measurement/internal/zzp:zzCJ	()Lcom/google/android/gms/measurement/internal/zzp$zza;
    //   94: ldc_w 1386
    //   97: aload_1
    //   98: getfield 1166	com/google/android/gms/measurement/internal/zzai:mName	Ljava/lang/String;
    //   101: aload 4
    //   103: invokevirtual 287	com/google/android/gms/measurement/internal/zzp$zza:zze	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   106: aload_0
    //   107: invokevirtual 366	com/google/android/gms/measurement/internal/zzw:zzCj	()Lcom/google/android/gms/measurement/internal/zze;
    //   110: invokevirtual 502	com/google/android/gms/measurement/internal/zze:beginTransaction	()V
    //   113: aload_0
    //   114: aload_2
    //   115: invokespecial 1196	com/google/android/gms/measurement/internal/zzw:zze	(Lcom/google/android/gms/measurement/internal/AppMetadata;)V
    //   118: aload_0
    //   119: invokevirtual 366	com/google/android/gms/measurement/internal/zzw:zzCj	()Lcom/google/android/gms/measurement/internal/zze;
    //   122: aload_1
    //   123: invokevirtual 1266	com/google/android/gms/measurement/internal/zze:zza	(Lcom/google/android/gms/measurement/internal/zzai;)Z
    //   126: istore_3
    //   127: aload_0
    //   128: invokevirtual 366	com/google/android/gms/measurement/internal/zzw:zzCj	()Lcom/google/android/gms/measurement/internal/zze;
    //   131: invokevirtual 527	com/google/android/gms/measurement/internal/zze:setTransactionSuccessful	()V
    //   134: iload_3
    //   135: ifeq +32 -> 167
    //   138: aload_0
    //   139: invokevirtual 115	com/google/android/gms/measurement/internal/zzw:zzAo	()Lcom/google/android/gms/measurement/internal/zzp;
    //   142: invokevirtual 152	com/google/android/gms/measurement/internal/zzp:zzCJ	()Lcom/google/android/gms/measurement/internal/zzp$zza;
    //   145: ldc_w 1388
    //   148: aload_1
    //   149: getfield 1166	com/google/android/gms/measurement/internal/zzai:mName	Ljava/lang/String;
    //   152: aload_1
    //   153: getfield 1176	com/google/android/gms/measurement/internal/zzai:zzNc	Ljava/lang/Object;
    //   156: invokevirtual 287	com/google/android/gms/measurement/internal/zzp$zza:zze	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   159: aload_0
    //   160: invokevirtual 366	com/google/android/gms/measurement/internal/zzw:zzCj	()Lcom/google/android/gms/measurement/internal/zze;
    //   163: invokevirtual 524	com/google/android/gms/measurement/internal/zze:endTransaction	()V
    //   166: return
    //   167: aload_0
    //   168: invokevirtual 115	com/google/android/gms/measurement/internal/zzw:zzAo	()Lcom/google/android/gms/measurement/internal/zzp;
    //   171: invokevirtual 1391	com/google/android/gms/measurement/internal/zzp:zzCH	()Lcom/google/android/gms/measurement/internal/zzp$zza;
    //   174: ldc_w 1393
    //   177: aload_1
    //   178: getfield 1166	com/google/android/gms/measurement/internal/zzai:mName	Ljava/lang/String;
    //   181: aload_1
    //   182: getfield 1176	com/google/android/gms/measurement/internal/zzai:zzNc	Ljava/lang/Object;
    //   185: invokevirtual 287	com/google/android/gms/measurement/internal/zzp$zza:zze	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   188: goto -29 -> 159
    //   191: astore_1
    //   192: aload_0
    //   193: invokevirtual 366	com/google/android/gms/measurement/internal/zzw:zzCj	()Lcom/google/android/gms/measurement/internal/zze;
    //   196: invokevirtual 524	com/google/android/gms/measurement/internal/zze:endTransaction	()V
    //   199: aload_1
    //   200: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	201	0	this	zzw
    //   0	201	1	paramUserAttributeParcel	UserAttributeParcel
    //   0	201	2	paramAppMetadata	AppMetadata
    //   126	9	3	bool	boolean
    //   58	44	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   113	134	191	finally
    //   138	159	191	finally
    //   167	188	191	finally
  }
  
  void zzb(zzz paramzzz)
  {
    this.zzaYq += 1;
  }
  
  void zzc(AppMetadata paramAppMetadata)
  {
    zzjk();
    zzjv();
    zzx.zzcM(paramAppMetadata.packageName);
    zze(paramAppMetadata);
  }
  
  @WorkerThread
  void zzc(UserAttributeParcel paramUserAttributeParcel, AppMetadata paramAppMetadata)
  {
    zzjk();
    zzjv();
    if (TextUtils.isEmpty(paramAppMetadata.zzaVt)) {
      return;
    }
    if (!paramAppMetadata.zzaVy)
    {
      zze(paramAppMetadata);
      return;
    }
    zzAo().zzCJ().zzj("Removing user property", paramUserAttributeParcel.name);
    zzCj().beginTransaction();
    try
    {
      zze(paramAppMetadata);
      zzCj().zzJ(paramAppMetadata.packageName, paramUserAttributeParcel.name);
      zzCj().setTransactionSuccessful();
      zzAo().zzCJ().zzj("User property removed", paramUserAttributeParcel.name);
      return;
    }
    finally
    {
      zzCj().endTransaction();
    }
  }
  
  /* Error */
  @WorkerThread
  public void zzd(AppMetadata paramAppMetadata)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 357	com/google/android/gms/measurement/internal/zzw:zzjk	()V
    //   4: aload_0
    //   5: invokevirtual 362	com/google/android/gms/measurement/internal/zzw:zzjv	()V
    //   8: aload_1
    //   9: invokestatic 76	com/google/android/gms/common/internal/zzx:zzz	(Ljava/lang/Object;)Ljava/lang/Object;
    //   12: pop
    //   13: aload_1
    //   14: getfield 631	com/google/android/gms/measurement/internal/AppMetadata:packageName	Ljava/lang/String;
    //   17: invokestatic 559	com/google/android/gms/common/internal/zzx:zzcM	(Ljava/lang/String;)Ljava/lang/String;
    //   20: pop
    //   21: aload_1
    //   22: getfield 649	com/google/android/gms/measurement/internal/AppMetadata:zzaVt	Ljava/lang/String;
    //   25: invokestatic 378	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   28: ifeq +4 -> 32
    //   31: return
    //   32: aload_1
    //   33: getfield 699	com/google/android/gms/measurement/internal/AppMetadata:zzaVy	Z
    //   36: ifne +9 -> 45
    //   39: aload_0
    //   40: aload_1
    //   41: invokespecial 1196	com/google/android/gms/measurement/internal/zzw:zze	(Lcom/google/android/gms/measurement/internal/AppMetadata;)V
    //   44: return
    //   45: aload_0
    //   46: invokevirtual 448	com/google/android/gms/measurement/internal/zzw:zzjl	()Lcom/google/android/gms/internal/zzmq;
    //   49: invokeinterface 453 1 0
    //   54: lstore_2
    //   55: aload_0
    //   56: invokevirtual 366	com/google/android/gms/measurement/internal/zzw:zzCj	()Lcom/google/android/gms/measurement/internal/zze;
    //   59: invokevirtual 502	com/google/android/gms/measurement/internal/zze:beginTransaction	()V
    //   62: aload_0
    //   63: invokevirtual 366	com/google/android/gms/measurement/internal/zzw:zzCj	()Lcom/google/android/gms/measurement/internal/zze;
    //   66: aload_1
    //   67: getfield 631	com/google/android/gms/measurement/internal/AppMetadata:packageName	Ljava/lang/String;
    //   70: invokevirtual 596	com/google/android/gms/measurement/internal/zze:zzeY	(Ljava/lang/String;)Lcom/google/android/gms/measurement/internal/zza;
    //   73: astore 4
    //   75: aload 4
    //   77: ifnull +76 -> 153
    //   80: aload 4
    //   82: invokevirtual 675	com/google/android/gms/measurement/internal/zza:zzli	()Ljava/lang/String;
    //   85: ifnull +68 -> 153
    //   88: aload 4
    //   90: invokevirtual 675	com/google/android/gms/measurement/internal/zza:zzli	()Ljava/lang/String;
    //   93: aload_1
    //   94: getfield 672	com/google/android/gms/measurement/internal/AppMetadata:zzaMV	Ljava/lang/String;
    //   97: invokevirtual 658	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   100: ifne +53 -> 153
    //   103: new 584	android/os/Bundle
    //   106: dup
    //   107: invokespecial 1401	android/os/Bundle:<init>	()V
    //   110: astore 5
    //   112: aload 5
    //   114: ldc_w 1403
    //   117: aload 4
    //   119: invokevirtual 675	com/google/android/gms/measurement/internal/zza:zzli	()Ljava/lang/String;
    //   122: invokevirtual 1406	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   125: aload_0
    //   126: new 1198	com/google/android/gms/measurement/internal/EventParcel
    //   129: dup
    //   130: ldc_w 1408
    //   133: new 1209	com/google/android/gms/measurement/internal/EventParams
    //   136: dup
    //   137: aload 5
    //   139: invokespecial 1411	com/google/android/gms/measurement/internal/EventParams:<init>	(Landroid/os/Bundle;)V
    //   142: ldc_w 1413
    //   145: lload_2
    //   146: invokespecial 1416	com/google/android/gms/measurement/internal/EventParcel:<init>	(Ljava/lang/String;Lcom/google/android/gms/measurement/internal/EventParams;Ljava/lang/String;J)V
    //   149: aload_1
    //   150: invokevirtual 1089	com/google/android/gms/measurement/internal/zzw:zzb	(Lcom/google/android/gms/measurement/internal/EventParcel;Lcom/google/android/gms/measurement/internal/AppMetadata;)V
    //   153: aload_0
    //   154: aload_1
    //   155: invokespecial 1196	com/google/android/gms/measurement/internal/zzw:zze	(Lcom/google/android/gms/measurement/internal/AppMetadata;)V
    //   158: aload_0
    //   159: invokevirtual 366	com/google/android/gms/measurement/internal/zzw:zzCj	()Lcom/google/android/gms/measurement/internal/zze;
    //   162: aload_1
    //   163: getfield 631	com/google/android/gms/measurement/internal/AppMetadata:packageName	Ljava/lang/String;
    //   166: ldc_w 1418
    //   169: invokevirtual 1329	com/google/android/gms/measurement/internal/zze:zzI	(Ljava/lang/String;Ljava/lang/String;)Lcom/google/android/gms/measurement/internal/zzi;
    //   172: ifnonnull +97 -> 269
    //   175: aload_0
    //   176: new 1371	com/google/android/gms/measurement/internal/UserAttributeParcel
    //   179: dup
    //   180: ldc_w 1420
    //   183: lload_2
    //   184: lload_2
    //   185: ldc2_w 1421
    //   188: ldiv
    //   189: lconst_1
    //   190: ladd
    //   191: ldc2_w 1421
    //   194: lmul
    //   195: invokestatic 137	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   198: ldc_w 1413
    //   201: invokespecial 1425	com/google/android/gms/measurement/internal/UserAttributeParcel:<init>	(Ljava/lang/String;JLjava/lang/Object;Ljava/lang/String;)V
    //   204: aload_1
    //   205: invokevirtual 1427	com/google/android/gms/measurement/internal/zzw:zzb	(Lcom/google/android/gms/measurement/internal/UserAttributeParcel;Lcom/google/android/gms/measurement/internal/AppMetadata;)V
    //   208: new 584	android/os/Bundle
    //   211: dup
    //   212: invokespecial 1401	android/os/Bundle:<init>	()V
    //   215: astore 4
    //   217: aload 4
    //   219: ldc_w 1306
    //   222: lconst_1
    //   223: invokevirtual 592	android/os/Bundle:putLong	(Ljava/lang/String;J)V
    //   226: aload_0
    //   227: new 1198	com/google/android/gms/measurement/internal/EventParcel
    //   230: dup
    //   231: ldc_w 1418
    //   234: new 1209	com/google/android/gms/measurement/internal/EventParams
    //   237: dup
    //   238: aload 4
    //   240: invokespecial 1411	com/google/android/gms/measurement/internal/EventParams:<init>	(Landroid/os/Bundle;)V
    //   243: ldc_w 1413
    //   246: lload_2
    //   247: invokespecial 1416	com/google/android/gms/measurement/internal/EventParcel:<init>	(Ljava/lang/String;Lcom/google/android/gms/measurement/internal/EventParams;Ljava/lang/String;J)V
    //   250: aload_1
    //   251: invokevirtual 1089	com/google/android/gms/measurement/internal/zzw:zzb	(Lcom/google/android/gms/measurement/internal/EventParcel;Lcom/google/android/gms/measurement/internal/AppMetadata;)V
    //   254: aload_0
    //   255: invokevirtual 366	com/google/android/gms/measurement/internal/zzw:zzCj	()Lcom/google/android/gms/measurement/internal/zze;
    //   258: invokevirtual 527	com/google/android/gms/measurement/internal/zze:setTransactionSuccessful	()V
    //   261: aload_0
    //   262: invokevirtual 366	com/google/android/gms/measurement/internal/zzw:zzCj	()Lcom/google/android/gms/measurement/internal/zze;
    //   265: invokevirtual 524	com/google/android/gms/measurement/internal/zze:endTransaction	()V
    //   268: return
    //   269: aload_1
    //   270: getfield 1430	com/google/android/gms/measurement/internal/AppMetadata:zzaVz	Z
    //   273: ifeq -19 -> 254
    //   276: aload_0
    //   277: new 1198	com/google/android/gms/measurement/internal/EventParcel
    //   280: dup
    //   281: ldc_w 1432
    //   284: new 1209	com/google/android/gms/measurement/internal/EventParams
    //   287: dup
    //   288: new 584	android/os/Bundle
    //   291: dup
    //   292: invokespecial 1401	android/os/Bundle:<init>	()V
    //   295: invokespecial 1411	com/google/android/gms/measurement/internal/EventParams:<init>	(Landroid/os/Bundle;)V
    //   298: ldc_w 1413
    //   301: lload_2
    //   302: invokespecial 1416	com/google/android/gms/measurement/internal/EventParcel:<init>	(Ljava/lang/String;Lcom/google/android/gms/measurement/internal/EventParams;Ljava/lang/String;J)V
    //   305: aload_1
    //   306: invokevirtual 1089	com/google/android/gms/measurement/internal/zzw:zzb	(Lcom/google/android/gms/measurement/internal/EventParcel;Lcom/google/android/gms/measurement/internal/AppMetadata;)V
    //   309: goto -55 -> 254
    //   312: astore_1
    //   313: aload_0
    //   314: invokevirtual 366	com/google/android/gms/measurement/internal/zzw:zzCj	()Lcom/google/android/gms/measurement/internal/zze;
    //   317: invokevirtual 524	com/google/android/gms/measurement/internal/zze:endTransaction	()V
    //   320: aload_1
    //   321: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	322	0	this	zzw
    //   0	322	1	paramAppMetadata	AppMetadata
    //   54	248	2	l	long
    //   73	166	4	localObject	Object
    //   110	28	5	localBundle	Bundle
    // Exception table:
    //   from	to	target	type
    //   62	75	312	finally
    //   80	153	312	finally
    //   153	254	312	finally
    //   254	261	312	finally
    //   269	309	312	finally
  }
  
  void zzjj()
  {
    if (zzCp().zzkr()) {
      throw new IllegalStateException("Unexpected call on package side");
    }
  }
  
  @WorkerThread
  public void zzjk()
  {
    zzCn().zzjk();
  }
  
  public zzmq zzjl()
  {
    return this.zzqW;
  }
  
  void zzjv()
  {
    if (!this.zzQk) {
      throw new IllegalStateException("AppMeasurement is not initialized");
    }
  }
  
  private class zza
    implements zze.zzb
  {
    zzqb.zze zzaYt;
    List<Long> zzaYu;
    long zzaYv;
    List<zzqb.zzb> zzpH;
    
    private zza() {}
    
    private long zza(zzqb.zzb paramzzb)
    {
      return paramzzb.zzbaf.longValue() / 1000L / 60L / 60L;
    }
    
    boolean isEmpty()
    {
      return (this.zzpH == null) || (this.zzpH.isEmpty());
    }
    
    public boolean zza(long paramLong, zzqb.zzb paramzzb)
    {
      zzx.zzz(paramzzb);
      if (this.zzpH == null) {
        this.zzpH = new ArrayList();
      }
      if (this.zzaYu == null) {
        this.zzaYu = new ArrayList();
      }
      if ((this.zzpH.size() > 0) && (zza((zzqb.zzb)this.zzpH.get(0)) != zza(paramzzb))) {
        return false;
      }
      long l = this.zzaYv + paramzzb.getSerializedSize();
      if (l >= zzw.this.zzCp().zzBT()) {
        return false;
      }
      this.zzaYv = l;
      this.zzpH.add(paramzzb);
      this.zzaYu.add(Long.valueOf(paramLong));
      return this.zzpH.size() < zzw.this.zzCp().zzBU();
    }
    
    public void zzc(zzqb.zze paramzze)
    {
      zzx.zzz(paramzze);
      this.zzaYt = paramzze;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\measurement\internal\zzw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */