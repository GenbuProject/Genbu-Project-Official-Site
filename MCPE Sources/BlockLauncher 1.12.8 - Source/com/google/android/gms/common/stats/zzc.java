package com.google.android.gms.common.stats;

import com.google.android.gms.internal.zzlz;

public final class zzc
{
  public static zzlz<Integer> zzanx = zzlz.zza("gms:common:stats:max_num_of_events", Integer.valueOf(100));
  public static zzlz<Integer> zzany = zzlz.zza("gms:common:stats:max_chunk_size", Integer.valueOf(100));
  
  public static final class zza
  {
    public static zzlz<String> zzanA = zzlz.zzv("gms:common:stats:connections:ignored_calling_processes", "");
    public static zzlz<String> zzanB = zzlz.zzv("gms:common:stats:connections:ignored_calling_services", "");
    public static zzlz<String> zzanC = zzlz.zzv("gms:common:stats:connections:ignored_target_processes", "");
    public static zzlz<String> zzanD = zzlz.zzv("gms:common:stats:connections:ignored_target_services", "com.google.android.gms.auth.GetToken");
    public static zzlz<Long> zzanE = zzlz.zza("gms:common:stats:connections:time_out_duration", Long.valueOf(600000L));
    public static zzlz<Integer> zzanz = zzlz.zza("gms:common:stats:connections:level", Integer.valueOf(zzd.LOG_LEVEL_OFF));
  }
  
  public static final class zzb
  {
    public static zzlz<Long> zzanE = zzlz.zza("gms:common:stats:wakelocks:time_out_duration", Long.valueOf(600000L));
    public static zzlz<Integer> zzanz = zzlz.zza("gms:common:stats:wakeLocks:level", Integer.valueOf(zzd.LOG_LEVEL_OFF));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\stats\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */