package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.analytics.AnalyticsReceiver;
import com.google.android.gms.analytics.AnalyticsService;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.CampaignTrackingService;
import com.google.android.gms.analytics.zza;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzkd;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.internal.zzpq;
import com.google.android.gms.internal.zzpr;
import com.google.android.gms.measurement.zzc;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzl
  extends zzd
{
  private boolean mStarted;
  private final zzj zzQY;
  private final zzah zzQZ;
  private final zzag zzRa;
  private final zzi zzRb;
  private long zzRc;
  private final zzt zzRd;
  private final zzt zzRe;
  private final zzaj zzRf;
  private long zzRg;
  private boolean zzRh;
  
  protected zzl(zzf paramzzf, zzg paramzzg)
  {
    super(paramzzf);
    zzx.zzz(paramzzg);
    this.zzRc = Long.MIN_VALUE;
    this.zzRa = paramzzg.zzk(paramzzf);
    this.zzQY = paramzzg.zzm(paramzzf);
    this.zzQZ = paramzzg.zzn(paramzzf);
    this.zzRb = paramzzg.zzo(paramzzf);
    this.zzRf = new zzaj(zzjl());
    this.zzRd = new zzt(paramzzf)
    {
      public void run()
      {
        zzl.zza(zzl.this);
      }
    };
    this.zzRe = new zzt(paramzzf)
    {
      public void run()
      {
        zzl.zzb(zzl.this);
      }
    };
  }
  
  private void zza(zzh paramzzh, zzpr paramzzpr)
  {
    zzx.zzz(paramzzh);
    zzx.zzz(paramzzpr);
    Object localObject1 = new zza(zzji());
    ((zza)localObject1).zzaS(paramzzh.zzjE());
    ((zza)localObject1).enableAdvertisingIdCollection(paramzzh.zzjF());
    localObject1 = ((zza)localObject1).zziy();
    zzke localzzke = (zzke)((zzc)localObject1).zzf(zzke.class);
    localzzke.zzaX("data");
    localzzke.zzI(true);
    ((zzc)localObject1).zzb(paramzzpr);
    zzkd localzzkd = (zzkd)((zzc)localObject1).zzf(zzkd.class);
    zzpq localzzpq = (zzpq)((zzc)localObject1).zzf(zzpq.class);
    Iterator localIterator = paramzzh.zzn().entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = (Map.Entry)localIterator.next();
      String str = (String)((Map.Entry)localObject2).getKey();
      localObject2 = (String)((Map.Entry)localObject2).getValue();
      if ("an".equals(str)) {
        localzzpq.setAppName((String)localObject2);
      } else if ("av".equals(str)) {
        localzzpq.setAppVersion((String)localObject2);
      } else if ("aid".equals(str)) {
        localzzpq.setAppId((String)localObject2);
      } else if ("aiid".equals(str)) {
        localzzpq.setAppInstallerId((String)localObject2);
      } else if ("uid".equals(str)) {
        localzzke.setUserId((String)localObject2);
      } else {
        localzzkd.set(str, (String)localObject2);
      }
    }
    zzb("Sending installation campaign to", paramzzh.zzjE(), paramzzpr);
    ((zzc)localObject1).zzM(zzjq().zzlF());
    ((zzc)localObject1).zzAy();
  }
  
  private boolean zzbk(String paramString)
  {
    return getContext().checkCallingOrSelfPermission(paramString) == 0;
  }
  
  private void zzjT()
  {
    Context localContext = zzji().getContext();
    if (!AnalyticsReceiver.zzY(localContext)) {
      zzbg("AnalyticsReceiver is not registered or is disabled. Register the receiver for reliable dispatching on non-Google Play devices. See http://goo.gl/8Rd3yj for instructions.");
    }
    do
    {
      while (!CampaignTrackingReceiver.zzY(localContext))
      {
        zzbg("CampaignTrackingReceiver is not registered, not exported or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        return;
        if (!AnalyticsService.zzZ(localContext)) {
          zzbh("AnalyticsService is not registered or is disabled. Analytics service at risk of not starting. See http://goo.gl/8Rd3yj for instructions.");
        }
      }
    } while (CampaignTrackingService.zzZ(localContext));
    zzbg("CampaignTrackingService is not registered or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
  }
  
  private void zzjV()
  {
    zzb(new zzw()
    {
      public void zzc(Throwable paramAnonymousThrowable)
      {
        zzl.this.zzkb();
      }
    });
  }
  
  private void zzjW()
  {
    try
    {
      this.zzQY.zzjN();
      zzkb();
      this.zzRe.zzt(zzjn().zzkT());
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      for (;;)
      {
        zzd("Failed to delete stale hits", localSQLiteException);
      }
    }
  }
  
  private boolean zzkc()
  {
    if (this.zzRh) {}
    while (((zzjn().zzkr()) && (!zzjn().zzks())) || (zzki() <= 0L)) {
      return false;
    }
    return true;
  }
  
  private void zzkd()
  {
    zzv localzzv = zzjp();
    if (!localzzv.zzlb()) {}
    long l;
    do
    {
      do
      {
        return;
      } while (localzzv.zzbw());
      l = zzjO();
    } while ((l == 0L) || (Math.abs(zzjl().currentTimeMillis() - l) > zzjn().zzkB()));
    zza("Dispatch alarm scheduled (ms)", Long.valueOf(zzjn().zzkA()));
    localzzv.zzlc();
  }
  
  private void zzke()
  {
    zzkd();
    long l2 = zzki();
    long l1 = zzjq().zzlH();
    if (l1 != 0L)
    {
      l1 = l2 - Math.abs(zzjl().currentTimeMillis() - l1);
      if (l1 <= 0L) {}
    }
    for (;;)
    {
      zza("Dispatch scheduled (ms)", Long.valueOf(l1));
      if (!this.zzRd.zzbw()) {
        break;
      }
      l1 = Math.max(1L, l1 + this.zzRd.zzkY());
      this.zzRd.zzu(l1);
      return;
      l1 = Math.min(zzjn().zzky(), l2);
      continue;
      l1 = Math.min(zzjn().zzky(), l2);
    }
    this.zzRd.zzt(l1);
  }
  
  private void zzkf()
  {
    zzkg();
    zzkh();
  }
  
  private void zzkg()
  {
    if (this.zzRd.zzbw()) {
      zzbd("All hits dispatched or no network/service. Going to power save mode");
    }
    this.zzRd.cancel();
  }
  
  private void zzkh()
  {
    zzv localzzv = zzjp();
    if (localzzv.zzbw()) {
      localzzv.cancel();
    }
  }
  
  protected void onServiceConnected()
  {
    zzjk();
    if (!zzjn().zzkr()) {
      zzjY();
    }
  }
  
  void start()
  {
    zzjv();
    if (!this.mStarted) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "Analytics backend already started");
      this.mStarted = true;
      if (!zzjn().zzkr()) {
        zzjT();
      }
      zzjo().zzf(new Runnable()
      {
        public void run()
        {
          zzl.this.zzjU();
        }
      });
      return;
    }
  }
  
  public void zzJ(boolean paramBoolean)
  {
    zzkb();
  }
  
  public long zza(zzh paramzzh, boolean paramBoolean)
  {
    zzx.zzz(paramzzh);
    zzjv();
    zzjk();
    long l;
    for (;;)
    {
      try
      {
        this.zzQY.beginTransaction();
        this.zzQY.zza(paramzzh.zzjD(), paramzzh.getClientId());
        l = this.zzQY.zza(paramzzh.zzjD(), paramzzh.getClientId(), paramzzh.zzjE());
        if (!paramBoolean)
        {
          paramzzh.zzn(l);
          this.zzQY.zzb(paramzzh);
          this.zzQY.setTransactionSuccessful();
        }
      }
      catch (SQLiteException paramzzh)
      {
        paramzzh = paramzzh;
        zze("Failed to update Analytics property", paramzzh);
        try
        {
          this.zzQY.endTransaction();
          return -1L;
        }
        catch (SQLiteException paramzzh)
        {
          zze("Failed to end transaction", paramzzh);
          return -1L;
        }
      }
      finally {}
      try
      {
        this.zzQY.endTransaction();
        return l;
      }
      catch (SQLiteException paramzzh)
      {
        zze("Failed to end transaction", paramzzh);
        return l;
      }
      paramzzh.zzn(1L + l);
    }
    try
    {
      this.zzQY.endTransaction();
      throw paramzzh;
    }
    catch (SQLiteException localSQLiteException)
    {
      for (;;)
      {
        zze("Failed to end transaction", localSQLiteException);
      }
    }
  }
  
  public void zza(zzab paramzzab)
  {
    zzx.zzz(paramzzab);
    com.google.android.gms.measurement.zzg.zzjk();
    zzjv();
    if (this.zzRh) {
      zzbe("Hit delivery not possible. Missing network permissions. See http://goo.gl/8Rd3yj for instructions");
    }
    for (;;)
    {
      paramzzab = zzf(paramzzab);
      zzjX();
      if (!this.zzRb.zzb(paramzzab)) {
        break;
      }
      zzbe("Hit sent to the device AnalyticsService for delivery");
      return;
      zza("Delivering hit", paramzzab);
    }
    if (zzjn().zzkr())
    {
      zzjm().zza(paramzzab, "Service unavailable on package side");
      return;
    }
    try
    {
      this.zzQY.zzc(paramzzab);
      zzkb();
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      zze("Delivery failed to save hit to a database", localSQLiteException);
      zzjm().zza(paramzzab, "deliver: failed to insert hit to database");
    }
  }
  
  public void zza(final zzw paramzzw, final long paramLong)
  {
    com.google.android.gms.measurement.zzg.zzjk();
    zzjv();
    long l1 = -1L;
    long l2 = zzjq().zzlH();
    if (l2 != 0L) {
      l1 = Math.abs(zzjl().currentTimeMillis() - l2);
    }
    zzb("Dispatching local hits. Elapsed time since last dispatch (ms)", Long.valueOf(l1));
    if (!zzjn().zzkr()) {
      zzjX();
    }
    try
    {
      if (zzjZ())
      {
        zzjo().zzf(new Runnable()
        {
          public void run()
          {
            zzl.this.zza(paramzzw, paramLong);
          }
        });
        return;
      }
      zzjq().zzlI();
      zzkb();
      if (paramzzw != null) {
        paramzzw.zzc(null);
      }
      if (this.zzRg != paramLong)
      {
        this.zzRa.zzlA();
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      zze("Local dispatch failed", localThrowable);
      zzjq().zzlI();
      zzkb();
      if (paramzzw != null) {
        paramzzw.zzc(localThrowable);
      }
    }
  }
  
  public void zzb(zzw paramzzw)
  {
    zza(paramzzw, this.zzRg);
  }
  
  public void zzbl(String paramString)
  {
    zzx.zzcM(paramString);
    zzjk();
    zzjj();
    zzpr localzzpr = zzam.zza(zzjm(), paramString);
    if (localzzpr == null) {
      zzd("Parsing failed. Ignoring invalid campaign data", paramString);
    }
    for (;;)
    {
      return;
      String str = zzjq().zzlJ();
      if (paramString.equals(str))
      {
        zzbg("Ignoring duplicate install campaign");
        return;
      }
      if (!TextUtils.isEmpty(str))
      {
        zzd("Ignoring multiple install campaigns. original, new", str, paramString);
        return;
      }
      zzjq().zzbp(paramString);
      if (zzjq().zzlG().zzv(zzjn().zzkW()))
      {
        zzd("Campaign received too late, ignoring", localzzpr);
        return;
      }
      zzb("Received installation campaign", localzzpr);
      paramString = this.zzQY.zzr(0L).iterator();
      while (paramString.hasNext()) {
        zza((zzh)paramString.next(), localzzpr);
      }
    }
  }
  
  protected void zzc(zzh paramzzh)
  {
    zzjk();
    zzb("Sending first hit to property", paramzzh.zzjE());
    if (zzjq().zzlG().zzv(zzjn().zzkW())) {}
    do
    {
      return;
      localObject = zzjq().zzlJ();
    } while (TextUtils.isEmpty((CharSequence)localObject));
    Object localObject = zzam.zza(zzjm(), (String)localObject);
    zzb("Found relevant installation campaign", localObject);
    zza(paramzzh, (zzpr)localObject);
  }
  
  zzab zzf(zzab paramzzab)
  {
    if (!TextUtils.isEmpty(paramzzab.zzlv())) {}
    do
    {
      return paramzzab;
      localObject2 = zzjq().zzlK().zzlN();
    } while (localObject2 == null);
    Object localObject1 = (Long)((Pair)localObject2).second;
    Object localObject2 = (String)((Pair)localObject2).first;
    localObject1 = localObject1 + ":" + (String)localObject2;
    localObject2 = new HashMap(paramzzab.zzn());
    ((Map)localObject2).put("_m", localObject1);
    return zzab.zza(this, paramzzab, (Map)localObject2);
  }
  
  protected void zziJ()
  {
    this.zzQY.zza();
    this.zzQZ.zza();
    this.zzRb.zza();
  }
  
  public long zzjO()
  {
    com.google.android.gms.measurement.zzg.zzjk();
    zzjv();
    try
    {
      long l = this.zzQY.zzjO();
      return l;
    }
    catch (SQLiteException localSQLiteException)
    {
      zze("Failed to get min/max hit times from local store", localSQLiteException);
    }
    return 0L;
  }
  
  protected void zzjU()
  {
    zzjv();
    zzjq().zzlF();
    if (!zzbk("android.permission.ACCESS_NETWORK_STATE"))
    {
      zzbh("Missing required android.permission.ACCESS_NETWORK_STATE. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
      zzkj();
    }
    if (!zzbk("android.permission.INTERNET"))
    {
      zzbh("Missing required android.permission.INTERNET. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
      zzkj();
    }
    if (AnalyticsService.zzZ(getContext())) {
      zzbd("AnalyticsService registered in the app manifest and enabled");
    }
    for (;;)
    {
      if ((!this.zzRh) && (!zzjn().zzkr()) && (!this.zzQY.isEmpty())) {
        zzjX();
      }
      zzkb();
      return;
      if (zzjn().zzkr()) {
        zzbh("Device AnalyticsService not registered! Hits will not be delivered reliably.");
      } else {
        zzbg("AnalyticsService not registered in the app manifest. Hits might not be delivered reliably. See http://goo.gl/8Rd3yj for instructions.");
      }
    }
  }
  
  protected void zzjX()
  {
    if (this.zzRh) {}
    do
    {
      long l;
      do
      {
        do
        {
          return;
        } while ((!zzjn().zzkt()) || (this.zzRb.isConnected()));
        l = zzjn().zzkO();
      } while (!this.zzRf.zzv(l));
      this.zzRf.start();
      zzbd("Connecting to service");
    } while (!this.zzRb.connect());
    zzbd("Connected to service");
    this.zzRf.clear();
    onServiceConnected();
  }
  
  public void zzjY()
  {
    com.google.android.gms.measurement.zzg.zzjk();
    zzjv();
    zzjj();
    if (!zzjn().zzkt()) {
      zzbg("Service client disabled. Can't dispatch local hits to device AnalyticsService");
    }
    if (!this.zzRb.isConnected()) {
      zzbd("Service not connected");
    }
    while (this.zzQY.isEmpty()) {
      return;
    }
    zzbd("Dispatching local hits to device AnalyticsService");
    for (;;)
    {
      try
      {
        List localList = this.zzQY.zzp(zzjn().zzkC());
        if (!localList.isEmpty()) {
          break label126;
        }
        zzkb();
        return;
      }
      catch (SQLiteException localSQLiteException1)
      {
        zze("Failed to read hits from store", localSQLiteException1);
        zzkf();
        return;
      }
      label107:
      Object localObject;
      localSQLiteException1.remove(localObject);
      try
      {
        this.zzQY.zzq(((zzab)localObject).zzlq());
        label126:
        if (!localSQLiteException1.isEmpty())
        {
          localObject = (zzab)localSQLiteException1.get(0);
          if (this.zzRb.zzb((zzab)localObject)) {
            break label107;
          }
          zzkb();
          return;
        }
      }
      catch (SQLiteException localSQLiteException2)
      {
        zze("Failed to remove hit that was send for delivery", localSQLiteException2);
        zzkf();
      }
    }
  }
  
  protected boolean zzjZ()
  {
    int j = 1;
    com.google.android.gms.measurement.zzg.zzjk();
    zzjv();
    zzbd("Dispatching a batch of local hits");
    int i;
    if ((!this.zzRb.isConnected()) && (!zzjn().zzkr()))
    {
      i = 1;
      if (this.zzQZ.zzlB()) {
        break label70;
      }
    }
    for (;;)
    {
      if ((i == 0) || (j == 0)) {
        break label75;
      }
      zzbd("No network or service available. Will retry later");
      return false;
      i = 0;
      break;
      label70:
      j = 0;
    }
    label75:
    long l3 = Math.max(zzjn().zzkC(), zzjn().zzkD());
    ArrayList localArrayList = new ArrayList();
    l1 = 0L;
    for (;;)
    {
      try
      {
        this.zzQY.beginTransaction();
        localArrayList.clear();
        try
        {
          localList = this.zzQY.zzp(l3);
          if (localList.isEmpty())
          {
            zzbd("Store is empty, nothing to dispatch");
            zzkf();
            try
            {
              this.zzQY.setTransactionSuccessful();
              this.zzQY.endTransaction();
              return false;
            }
            catch (SQLiteException localSQLiteException1)
            {
              zze("Failed to commit local dispatch transaction", localSQLiteException1);
              zzkf();
              return false;
            }
          }
          zza("Hits loaded from store. count", Integer.valueOf(localList.size()));
          localObject2 = localList.iterator();
          if (((Iterator)localObject2).hasNext())
          {
            if (((zzab)((Iterator)localObject2).next()).zzlq() != l1) {
              continue;
            }
            zzd("Database contains successfully uploaded hit", Long.valueOf(l1), Integer.valueOf(localList.size()));
            zzkf();
            try
            {
              this.zzQY.setTransactionSuccessful();
              this.zzQY.endTransaction();
              return false;
            }
            catch (SQLiteException localSQLiteException2)
            {
              zze("Failed to commit local dispatch transaction", localSQLiteException2);
              zzkf();
              return false;
            }
          }
          l2 = l1;
        }
        catch (SQLiteException localSQLiteException3)
        {
          zzd("Failed to read hits from persisted store", localSQLiteException3);
          zzkf();
          try
          {
            this.zzQY.setTransactionSuccessful();
            this.zzQY.endTransaction();
            return false;
          }
          catch (SQLiteException localSQLiteException4)
          {
            zze("Failed to commit local dispatch transaction", localSQLiteException4);
            zzkf();
            return false;
          }
          l2 = l1;
          if (!this.zzRb.isConnected()) {
            continue;
          }
        }
        if (zzjn().zzkr()) {
          continue;
        }
        zzbd("Service connected, sending hits to the service");
        l2 = l1;
        if (localList.isEmpty()) {
          continue;
        }
        localObject2 = (zzab)localList.get(0);
        if (this.zzRb.zzb((zzab)localObject2)) {
          continue;
        }
      }
      finally
      {
        long l2;
        try
        {
          List localList;
          Object localObject2;
          Iterator localIterator;
          this.zzQY.setTransactionSuccessful();
          this.zzQY.endTransaction();
          throw ((Throwable)localObject1);
        }
        catch (SQLiteException localSQLiteException11)
        {
          zze("Failed to commit local dispatch transaction", localSQLiteException11);
          zzkf();
          return false;
        }
        l1 = l2;
        continue;
      }
      l2 = l1;
      if (this.zzQZ.zzlB())
      {
        localObject2 = this.zzQZ.zzq(localList);
        localIterator = ((List)localObject2).iterator();
        if (localIterator.hasNext())
        {
          l1 = Math.max(l1, ((Long)localIterator.next()).longValue());
          continue;
          l1 = Math.max(l1, ((zzab)localObject2).zzlq());
          localList.remove(localObject2);
          zzb("Hit sent do device AnalyticsService for delivery", localObject2);
          try
          {
            this.zzQY.zzq(((zzab)localObject2).zzlq());
            localSQLiteException4.add(Long.valueOf(((zzab)localObject2).zzlq()));
          }
          catch (SQLiteException localSQLiteException5)
          {
            zze("Failed to remove hit that was send for delivery", localSQLiteException5);
            zzkf();
            try
            {
              this.zzQY.setTransactionSuccessful();
              this.zzQY.endTransaction();
              return false;
            }
            catch (SQLiteException localSQLiteException6)
            {
              zze("Failed to commit local dispatch transaction", localSQLiteException6);
              zzkf();
              return false;
            }
          }
        }
        localList.removeAll((Collection)localObject2);
      }
      try
      {
        this.zzQY.zzo((List)localObject2);
        localSQLiteException6.addAll((Collection)localObject2);
        l2 = l1;
        boolean bool = localSQLiteException6.isEmpty();
        if (bool) {
          try
          {
            this.zzQY.setTransactionSuccessful();
            this.zzQY.endTransaction();
            return false;
          }
          catch (SQLiteException localSQLiteException7)
          {
            zze("Failed to commit local dispatch transaction", localSQLiteException7);
            zzkf();
            return false;
          }
        }
      }
      catch (SQLiteException localSQLiteException8)
      {
        zze("Failed to remove successfully uploaded hits", localSQLiteException8);
        zzkf();
        try
        {
          this.zzQY.setTransactionSuccessful();
          this.zzQY.endTransaction();
          return false;
        }
        catch (SQLiteException localSQLiteException9)
        {
          zze("Failed to commit local dispatch transaction", localSQLiteException9);
          zzkf();
          return false;
        }
        try
        {
          this.zzQY.setTransactionSuccessful();
          this.zzQY.endTransaction();
          l1 = l2;
        }
        catch (SQLiteException localSQLiteException10)
        {
          zze("Failed to commit local dispatch transaction", localSQLiteException10);
          zzkf();
          return false;
        }
      }
    }
  }
  
  public void zzjc()
  {
    com.google.android.gms.measurement.zzg.zzjk();
    zzjv();
    if (!zzjn().zzkr()) {
      zzbd("Delete all hits from local store");
    }
    try
    {
      this.zzQY.zzjL();
      this.zzQY.zzjM();
      zzkb();
      zzjX();
      if (this.zzRb.zzjH()) {
        zzbd("Device service unavailable. Can't clear hits stored on the device service.");
      }
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      for (;;)
      {
        zzd("Failed to delete hits from store", localSQLiteException);
      }
    }
  }
  
  public void zzjf()
  {
    com.google.android.gms.measurement.zzg.zzjk();
    zzjv();
    zzbd("Service disconnected");
  }
  
  void zzjh()
  {
    zzjk();
    this.zzRg = zzjl().currentTimeMillis();
  }
  
  public void zzka()
  {
    com.google.android.gms.measurement.zzg.zzjk();
    zzjv();
    zzbe("Sync dispatching local hits");
    long l = this.zzRg;
    if (!zzjn().zzkr()) {
      zzjX();
    }
    try
    {
      while (zzjZ()) {}
      zzjq().zzlI();
      zzkb();
      if (this.zzRg != l) {
        this.zzRa.zzlA();
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      zze("Sync local dispatch failed", localThrowable);
      zzkb();
    }
  }
  
  public void zzkb()
  {
    zzji().zzjk();
    zzjv();
    if (!zzkc())
    {
      this.zzRa.unregister();
      zzkf();
      return;
    }
    if (this.zzQY.isEmpty())
    {
      this.zzRa.unregister();
      zzkf();
      return;
    }
    if (!((Boolean)zzy.zzSs.get()).booleanValue()) {
      this.zzRa.zzly();
    }
    for (boolean bool = this.zzRa.isConnected(); bool; bool = true)
    {
      zzke();
      return;
    }
    zzkf();
    zzkd();
  }
  
  public long zzki()
  {
    long l;
    if (this.zzRc != Long.MIN_VALUE) {
      l = this.zzRc;
    }
    do
    {
      return l;
      l = zzjn().zzkz();
    } while (!zziI().zzll());
    return zziI().zzmc() * 1000L;
  }
  
  public void zzkj()
  {
    zzjv();
    zzjk();
    this.zzRh = true;
    this.zzRb.disconnect();
    zzkb();
  }
  
  public void zzs(long paramLong)
  {
    com.google.android.gms.measurement.zzg.zzjk();
    zzjv();
    long l = paramLong;
    if (paramLong < 0L) {
      l = 0L;
    }
    this.zzRc = l;
    zzkb();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\analytics\internal\zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */