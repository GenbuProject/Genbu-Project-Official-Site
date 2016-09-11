package com.google.android.gms.ads.internal.formats;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

public abstract interface zzh
{
  public abstract Context getContext();
  
  public abstract void recordImpression();
  
  public abstract void zza(View paramView, Map<String, WeakReference<View>> paramMap, JSONObject paramJSONObject1, JSONObject paramJSONObject2, JSONObject paramJSONObject3);
  
  public abstract void zza(String paramString, JSONObject paramJSONObject1, JSONObject paramJSONObject2, JSONObject paramJSONObject3);
  
  public abstract void zzb(MotionEvent paramMotionEvent);
  
  public abstract View zzdS();
  
  public abstract void zzh(View paramView);
  
  public abstract void zzi(View paramView);
  
  public static abstract interface zza
  {
    public abstract String getCustomTemplateId();
    
    public abstract void zzb(zzh paramzzh);
    
    public abstract String zzdM();
    
    public abstract zza zzdN();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\formats\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */