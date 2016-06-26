package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzlz;

public final class zzl
{
  public static zza<Boolean> zzaVY = zza.zzm("measurement.service_enabled", true);
  public static zza<Boolean> zzaVZ = zza.zzm("measurement.service_client_enabled", true);
  public static zza<String> zzaWa = zza.zzl("measurement.log_tag", "GMPM", "GMPM-SVC");
  public static zza<Long> zzaWb = zza.zze("measurement.ad_id_cache_time", 10000L);
  public static zza<Long> zzaWc = zza.zze("measurement.monitoring.sample_period_millis", 86400000L);
  public static zza<Long> zzaWd = zza.zze("measurement.config.cache_time", 86400000L);
  public static zza<String> zzaWe = zza.zzN("measurement.config.url_scheme", "https");
  public static zza<String> zzaWf = zza.zzN("measurement.config.url_authority", "app-measurement.com");
  public static zza<Integer> zzaWg = zza.zzD("measurement.upload.max_bundles", 100);
  public static zza<Integer> zzaWh = zza.zzD("measurement.upload.max_batch_size", 65536);
  public static zza<Integer> zzaWi = zza.zzD("measurement.upload.max_bundle_size", 65536);
  public static zza<Integer> zzaWj = zza.zzD("measurement.upload.max_events_per_bundle", 1000);
  public static zza<Integer> zzaWk = zza.zzD("measurement.upload.max_events_per_day", 100000);
  public static zza<Integer> zzaWl = zza.zzD("measurement.upload.max_public_events_per_day", 50000);
  public static zza<Integer> zzaWm = zza.zzD("measurement.upload.max_conversions_per_day", 500);
  public static zza<Integer> zzaWn = zza.zzD("measurement.store.max_stored_events_per_app", 100000);
  public static zza<String> zzaWo = zza.zzN("measurement.upload.url", "https://app-measurement.com/a");
  public static zza<Long> zzaWp = zza.zze("measurement.upload.backoff_period", 43200000L);
  public static zza<Long> zzaWq = zza.zze("measurement.upload.window_interval", 3600000L);
  public static zza<Long> zzaWr = zza.zze("measurement.upload.interval", 3600000L);
  public static zza<Long> zzaWs = zza.zze("measurement.upload.stale_data_deletion_interval", 86400000L);
  public static zza<Long> zzaWt = zza.zze("measurement.upload.initial_upload_delay_time", 15000L);
  public static zza<Long> zzaWu = zza.zze("measurement.upload.retry_time", 1800000L);
  public static zza<Integer> zzaWv = zza.zzD("measurement.upload.retry_count", 6);
  public static zza<Long> zzaWw = zza.zze("measurement.upload.max_queue_time", 2419200000L);
  public static zza<Integer> zzaWx = zza.zzD("measurement.lifetimevalue.max_currency_tracked", 4);
  public static zza<Long> zzaWy = zza.zze("measurement.service_client.idle_disconnect_millis", 5000L);
  
  public static final class zza<V>
  {
    private final V zzSA;
    private final zzlz<V> zzSB;
    private V zzSC;
    private final String zzvs;
    
    private zza(String paramString, zzlz<V> paramzzlz, V paramV)
    {
      zzx.zzz(paramzzlz);
      this.zzSB = paramzzlz;
      this.zzSA = paramV;
      this.zzvs = paramString;
    }
    
    static zza<Integer> zzD(String paramString, int paramInt)
    {
      return zzo(paramString, paramInt, paramInt);
    }
    
    static zza<String> zzN(String paramString1, String paramString2)
    {
      return zzl(paramString1, paramString2, paramString2);
    }
    
    static zza<Long> zzb(String paramString, long paramLong1, long paramLong2)
    {
      return new zza(paramString, zzlz.zza(paramString, Long.valueOf(paramLong2)), Long.valueOf(paramLong1));
    }
    
    static zza<Boolean> zzb(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    {
      return new zza(paramString, zzlz.zzk(paramString, paramBoolean2), Boolean.valueOf(paramBoolean1));
    }
    
    static zza<Long> zze(String paramString, long paramLong)
    {
      return zzb(paramString, paramLong, paramLong);
    }
    
    static zza<String> zzl(String paramString1, String paramString2, String paramString3)
    {
      return new zza(paramString1, zzlz.zzv(paramString1, paramString3), paramString2);
    }
    
    static zza<Boolean> zzm(String paramString, boolean paramBoolean)
    {
      return zzb(paramString, paramBoolean, paramBoolean);
    }
    
    static zza<Integer> zzo(String paramString, int paramInt1, int paramInt2)
    {
      return new zza(paramString, zzlz.zza(paramString, Integer.valueOf(paramInt2)), Integer.valueOf(paramInt1));
    }
    
    public V get()
    {
      if (this.zzSC != null) {
        return (V)this.zzSC;
      }
      if ((zzd.zzakE) && (zzlz.isInitialized())) {
        return (V)this.zzSB.zzpX();
      }
      return (V)this.zzSA;
    }
    
    public V get(V paramV)
    {
      Object localObject;
      if (this.zzSC != null) {
        localObject = this.zzSC;
      }
      do
      {
        return (V)localObject;
        localObject = paramV;
      } while (paramV != null);
      if ((zzd.zzakE) && (zzlz.isInitialized())) {
        return (V)this.zzSB.zzpX();
      }
      return (V)this.zzSA;
    }
    
    public String getKey()
    {
      return this.zzvs;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\measurement\internal\zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */