package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzlz;

public final class zzy
{
  public static zza<Boolean> zzRJ = zza.zzg("analytics.service_enabled", false);
  public static zza<Boolean> zzRK = zza.zzg("analytics.service_client_enabled", true);
  public static zza<String> zzRL = zza.zze("analytics.log_tag", "GAv4", "GAv4-SVC");
  public static zza<Long> zzRM = zza.zzb("analytics.max_tokens", 60L);
  public static zza<Float> zzRN = zza.zza("analytics.tokens_per_sec", 0.5F);
  public static zza<Integer> zzRO = zza.zza("analytics.max_stored_hits", 2000, 20000);
  public static zza<Integer> zzRP = zza.zzd("analytics.max_stored_hits_per_app", 2000);
  public static zza<Integer> zzRQ = zza.zzd("analytics.max_stored_properties_per_app", 100);
  public static zza<Long> zzRR = zza.zza("analytics.local_dispatch_millis", 1800000L, 120000L);
  public static zza<Long> zzRS = zza.zza("analytics.initial_local_dispatch_millis", 5000L, 5000L);
  public static zza<Long> zzRT = zza.zzb("analytics.min_local_dispatch_millis", 120000L);
  public static zza<Long> zzRU = zza.zzb("analytics.max_local_dispatch_millis", 7200000L);
  public static zza<Long> zzRV = zza.zzb("analytics.dispatch_alarm_millis", 7200000L);
  public static zza<Long> zzRW = zza.zzb("analytics.max_dispatch_alarm_millis", 32400000L);
  public static zza<Integer> zzRX = zza.zzd("analytics.max_hits_per_dispatch", 20);
  public static zza<Integer> zzRY = zza.zzd("analytics.max_hits_per_batch", 20);
  public static zza<String> zzRZ = zza.zzl("analytics.insecure_host", "http://www.google-analytics.com");
  public static zza<String> zzSa = zza.zzl("analytics.secure_host", "https://ssl.google-analytics.com");
  public static zza<String> zzSb = zza.zzl("analytics.simple_endpoint", "/collect");
  public static zza<String> zzSc = zza.zzl("analytics.batching_endpoint", "/batch");
  public static zza<Integer> zzSd = zza.zzd("analytics.max_get_length", 2036);
  public static zza<String> zzSe = zza.zze("analytics.batching_strategy.k", zzm.zzRo.name(), zzm.zzRo.name());
  public static zza<String> zzSf = zza.zzl("analytics.compression_strategy.k", zzo.zzRv.name());
  public static zza<Integer> zzSg = zza.zzd("analytics.max_hits_per_request.k", 20);
  public static zza<Integer> zzSh = zza.zzd("analytics.max_hit_length.k", 8192);
  public static zza<Integer> zzSi = zza.zzd("analytics.max_post_length.k", 8192);
  public static zza<Integer> zzSj = zza.zzd("analytics.max_batch_post_length", 8192);
  public static zza<String> zzSk = zza.zzl("analytics.fallback_responses.k", "404,502");
  public static zza<Integer> zzSl = zza.zzd("analytics.batch_retry_interval.seconds.k", 3600);
  public static zza<Long> zzSm = zza.zzb("analytics.service_monitor_interval", 86400000L);
  public static zza<Integer> zzSn = zza.zzd("analytics.http_connection.connect_timeout_millis", 60000);
  public static zza<Integer> zzSo = zza.zzd("analytics.http_connection.read_timeout_millis", 61000);
  public static zza<Long> zzSp = zza.zzb("analytics.campaigns.time_limit", 86400000L);
  public static zza<String> zzSq = zza.zzl("analytics.first_party_experiment_id", "");
  public static zza<Integer> zzSr = zza.zzd("analytics.first_party_experiment_variant", 0);
  public static zza<Boolean> zzSs = zza.zzg("analytics.test.disable_receiver", false);
  public static zza<Long> zzSt = zza.zza("analytics.service_client.idle_disconnect_millis", 10000L, 10000L);
  public static zza<Long> zzSu = zza.zzb("analytics.service_client.connect_timeout_millis", 5000L);
  public static zza<Long> zzSv = zza.zzb("analytics.service_client.second_connect_delay_millis", 5000L);
  public static zza<Long> zzSw = zza.zzb("analytics.service_client.unexpected_reconnect_millis", 60000L);
  public static zza<Long> zzSx = zza.zzb("analytics.service_client.reconnect_throttle_millis", 1800000L);
  public static zza<Long> zzSy = zza.zzb("analytics.monitoring.sample_period_millis", 86400000L);
  public static zza<Long> zzSz = zza.zzb("analytics.initialization_warning_threshold", 5000L);
  
  public static final class zza<V>
  {
    private final V zzSA;
    private final zzlz<V> zzSB;
    private V zzSC;
    
    private zza(zzlz<V> paramzzlz, V paramV)
    {
      zzx.zzz(paramzzlz);
      this.zzSB = paramzzlz;
      this.zzSA = paramV;
    }
    
    static zza<Float> zza(String paramString, float paramFloat)
    {
      return zza(paramString, paramFloat, paramFloat);
    }
    
    static zza<Float> zza(String paramString, float paramFloat1, float paramFloat2)
    {
      return new zza(zzlz.zza(paramString, Float.valueOf(paramFloat2)), Float.valueOf(paramFloat1));
    }
    
    static zza<Integer> zza(String paramString, int paramInt1, int paramInt2)
    {
      return new zza(zzlz.zza(paramString, Integer.valueOf(paramInt2)), Integer.valueOf(paramInt1));
    }
    
    static zza<Long> zza(String paramString, long paramLong1, long paramLong2)
    {
      return new zza(zzlz.zza(paramString, Long.valueOf(paramLong2)), Long.valueOf(paramLong1));
    }
    
    static zza<Boolean> zza(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    {
      return new zza(zzlz.zzk(paramString, paramBoolean2), Boolean.valueOf(paramBoolean1));
    }
    
    static zza<Long> zzb(String paramString, long paramLong)
    {
      return zza(paramString, paramLong, paramLong);
    }
    
    static zza<Integer> zzd(String paramString, int paramInt)
    {
      return zza(paramString, paramInt, paramInt);
    }
    
    static zza<String> zze(String paramString1, String paramString2, String paramString3)
    {
      return new zza(zzlz.zzv(paramString1, paramString3), paramString2);
    }
    
    static zza<Boolean> zzg(String paramString, boolean paramBoolean)
    {
      return zza(paramString, paramBoolean, paramBoolean);
    }
    
    static zza<String> zzl(String paramString1, String paramString2)
    {
      return zze(paramString1, paramString2, paramString2);
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
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\analytics\internal\zzy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */