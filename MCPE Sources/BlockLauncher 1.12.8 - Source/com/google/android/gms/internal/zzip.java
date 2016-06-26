package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import java.util.concurrent.Future;

@zzhb
public final class zzip
{
  public static Future zza(Context paramContext, final int paramInt)
  {
    new zza(paramContext)
    {
      public void zzbr()
      {
        SharedPreferences.Editor localEditor = zzip.zzw(this.zzxh).edit();
        localEditor.putInt("webview_cache_version", paramInt);
        localEditor.apply();
      }
    }.zzhn();
  }
  
  public static Future zza(Context paramContext, final zzb paramzzb)
  {
    new zza(paramContext)
    {
      public void zzbr()
      {
        SharedPreferences localSharedPreferences = zzip.zzw(this.zzxh);
        Bundle localBundle = new Bundle();
        localBundle.putBoolean("use_https", localSharedPreferences.getBoolean("use_https", true));
        if (paramzzb != null) {
          paramzzb.zze(localBundle);
        }
      }
    }.zzhn();
  }
  
  public static Future zza(Context paramContext, final boolean paramBoolean)
  {
    new zza(paramContext)
    {
      public void zzbr()
      {
        SharedPreferences.Editor localEditor = zzip.zzw(this.zzxh).edit();
        localEditor.putBoolean("use_https", paramBoolean);
        localEditor.apply();
      }
    }.zzhn();
  }
  
  public static Future zzb(Context paramContext, final zzb paramzzb)
  {
    new zza(paramContext)
    {
      public void zzbr()
      {
        SharedPreferences localSharedPreferences = zzip.zzw(this.zzxh);
        Bundle localBundle = new Bundle();
        localBundle.putInt("webview_cache_version", localSharedPreferences.getInt("webview_cache_version", 0));
        if (paramzzb != null) {
          paramzzb.zze(localBundle);
        }
      }
    }.zzhn();
  }
  
  public static Future zzb(Context paramContext, final boolean paramBoolean)
  {
    new zza(paramContext)
    {
      public void zzbr()
      {
        SharedPreferences.Editor localEditor = zzip.zzw(this.zzxh).edit();
        localEditor.putBoolean("content_url_opted_out", paramBoolean);
        localEditor.apply();
      }
    }.zzhn();
  }
  
  public static Future zzc(Context paramContext, final zzb paramzzb)
  {
    new zza(paramContext)
    {
      public void zzbr()
      {
        SharedPreferences localSharedPreferences = zzip.zzw(this.zzxh);
        Bundle localBundle = new Bundle();
        localBundle.putBoolean("content_url_opted_out", localSharedPreferences.getBoolean("content_url_opted_out", true));
        if (paramzzb != null) {
          paramzzb.zze(localBundle);
        }
      }
    }.zzhn();
  }
  
  public static Future zzd(Context paramContext, final zzb paramzzb)
  {
    new zza(paramContext)
    {
      public void zzbr()
      {
        SharedPreferences localSharedPreferences = zzip.zzw(this.zzxh);
        Bundle localBundle = new Bundle();
        localBundle.putString("content_url_hashes", localSharedPreferences.getString("content_url_hashes", ""));
        if (paramzzb != null) {
          paramzzb.zze(localBundle);
        }
      }
    }.zzhn();
  }
  
  public static Future zzd(Context paramContext, final String paramString)
  {
    new zza(paramContext)
    {
      public void zzbr()
      {
        SharedPreferences.Editor localEditor = zzip.zzw(this.zzxh).edit();
        localEditor.putString("content_url_hashes", paramString);
        localEditor.apply();
      }
    }.zzhn();
  }
  
  public static SharedPreferences zzw(Context paramContext)
  {
    return paramContext.getSharedPreferences("admob", 0);
  }
  
  private static abstract class zza
    extends zzim
  {
    public void onStop() {}
  }
  
  public static abstract interface zzb
  {
    public abstract void zze(Bundle paramBundle);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzip.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */