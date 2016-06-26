package com.google.android.gms.ads.internal.formats;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzan;
import com.google.android.gms.internal.zzdf;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzjp;
import com.google.android.gms.internal.zzjq;
import com.google.android.gms.internal.zzjq.zza;
import com.google.android.gms.internal.zzjr;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@zzhb
public class zzi
  implements zzh
{
  private final Context mContext;
  private zzjp zzpD;
  private final VersionInfoParcel zzpT;
  private final Object zzpV = new Object();
  private final zzp zzyn;
  private final JSONObject zzyq;
  private final zzed zzyr;
  private final zzh.zza zzys;
  private final zzan zzyt;
  private boolean zzyu;
  private String zzyv;
  private WeakReference<View> zzyw = null;
  
  public zzi(Context paramContext, zzp paramzzp, zzed paramzzed, zzan paramzzan, JSONObject paramJSONObject, zzh.zza paramzza, VersionInfoParcel paramVersionInfoParcel)
  {
    this.mContext = paramContext;
    this.zzyn = paramzzp;
    this.zzyr = paramzzed;
    this.zzyt = paramzzan;
    this.zzyq = paramJSONObject;
    this.zzys = paramzza;
    this.zzpT = paramVersionInfoParcel;
  }
  
  public Context getContext()
  {
    return this.mContext;
  }
  
  public void recordImpression()
  {
    zzx.zzcD("recordImpression must be called on the main UI thread.");
    zzn(true);
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("ad", this.zzyq);
      this.zzyr.zza("google.afma.nativeAds.handleImpressionPing", localJSONObject);
      this.zzyn.zza(this);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        zzin.zzb("Unable to create impression JSON.", localJSONException);
      }
    }
  }
  
  public zzb zza(View.OnClickListener paramOnClickListener)
  {
    Object localObject = this.zzys.zzdN();
    if (localObject == null) {
      return null;
    }
    localObject = new zzb(this.mContext, (zza)localObject);
    ((zzb)localObject).setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    ((zzb)localObject).zzdI().setOnClickListener(paramOnClickListener);
    ((zzb)localObject).zzdI().setContentDescription("Ad attribution icon");
    return (zzb)localObject;
  }
  
  public void zza(View paramView, Map<String, WeakReference<View>> paramMap, JSONObject paramJSONObject1, JSONObject paramJSONObject2, JSONObject paramJSONObject3)
  {
    zzx.zzcD("performClick must be called on the main UI thread.");
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      if (paramView.equals((View)((WeakReference)localEntry.getValue()).get())) {
        zza((String)localEntry.getKey(), paramJSONObject1, paramJSONObject2, paramJSONObject3);
      }
    }
  }
  
  public void zza(String paramString, JSONObject paramJSONObject1, JSONObject paramJSONObject2, JSONObject paramJSONObject3)
  {
    zzx.zzcD("performClick must be called on the main UI thread.");
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("asset", paramString);
      localJSONObject.put("template", this.zzys.zzdM());
      paramString = new JSONObject();
      paramString.put("ad", this.zzyq);
      paramString.put("click", localJSONObject);
      if (this.zzyn.zzs(this.zzys.getCustomTemplateId()) != null) {}
      for (boolean bool = true;; bool = false)
      {
        paramString.put("has_custom_click_handler", bool);
        if (paramJSONObject1 != null) {
          paramString.put("view_rectangles", paramJSONObject1);
        }
        if (paramJSONObject2 != null) {
          paramString.put("click_point", paramJSONObject2);
        }
        if (paramJSONObject3 != null) {
          paramString.put("native_view_rectangle", paramJSONObject3);
        }
        this.zzyr.zza("google.afma.nativeAds.handleClickGmsg", paramString);
        return;
      }
      return;
    }
    catch (JSONException paramString)
    {
      zzin.zzb("Unable to create click JSON.", paramString);
    }
  }
  
  public void zzb(MotionEvent paramMotionEvent)
  {
    this.zzyt.zza(paramMotionEvent);
  }
  
  public zzjp zzdR()
  {
    this.zzpD = zzdT();
    this.zzpD.getView().setVisibility(8);
    this.zzyr.zza("/loadHtml", new zzdf()
    {
      public void zza(zzjp paramAnonymouszzjp, final Map<String, String> paramAnonymousMap)
      {
        zzi.zzc(zzi.this).zzhU().zza(new zzjq.zza()
        {
          public void zza(zzjp paramAnonymous2zzjp, boolean paramAnonymous2Boolean)
          {
            zzi.zza(zzi.this, (String)paramAnonymousMap.get("id"));
            paramAnonymous2zzjp = new JSONObject();
            try
            {
              paramAnonymous2zzjp.put("messageType", "htmlLoaded");
              paramAnonymous2zzjp.put("id", zzi.zza(zzi.this));
              zzi.zzb(zzi.this).zzb("sendMessageToNativeJs", paramAnonymous2zzjp);
              return;
            }
            catch (JSONException paramAnonymous2zzjp)
            {
              zzin.zzb("Unable to dispatch sendMessageToNativeJsevent", paramAnonymous2zzjp);
            }
          }
        });
        paramAnonymouszzjp = (String)paramAnonymousMap.get("overlayHtml");
        paramAnonymousMap = (String)paramAnonymousMap.get("baseUrl");
        if (TextUtils.isEmpty(paramAnonymousMap))
        {
          zzi.zzc(zzi.this).loadData(paramAnonymouszzjp, "text/html", "UTF-8");
          return;
        }
        zzi.zzc(zzi.this).loadDataWithBaseURL(paramAnonymousMap, paramAnonymouszzjp, "text/html", "UTF-8", null);
      }
    });
    this.zzyr.zza("/showOverlay", new zzdf()
    {
      public void zza(zzjp paramAnonymouszzjp, Map<String, String> paramAnonymousMap)
      {
        zzi.zzc(zzi.this).getView().setVisibility(0);
      }
    });
    this.zzyr.zza("/hideOverlay", new zzdf()
    {
      public void zza(zzjp paramAnonymouszzjp, Map<String, String> paramAnonymousMap)
      {
        zzi.zzc(zzi.this).getView().setVisibility(8);
      }
    });
    this.zzpD.zzhU().zza("/hideOverlay", new zzdf()
    {
      public void zza(zzjp paramAnonymouszzjp, Map<String, String> paramAnonymousMap)
      {
        zzi.zzc(zzi.this).getView().setVisibility(8);
      }
    });
    this.zzpD.zzhU().zza("/sendMessageToSdk", new zzdf()
    {
      public void zza(zzjp paramAnonymouszzjp, Map<String, String> paramAnonymousMap)
      {
        paramAnonymouszzjp = new JSONObject();
        try
        {
          Iterator localIterator = paramAnonymousMap.keySet().iterator();
          while (localIterator.hasNext())
          {
            String str = (String)localIterator.next();
            paramAnonymouszzjp.put(str, paramAnonymousMap.get(str));
          }
          paramAnonymouszzjp.put("id", zzi.zza(zzi.this));
        }
        catch (JSONException paramAnonymouszzjp)
        {
          zzin.zzb("Unable to dispatch sendMessageToNativeJs event", paramAnonymouszzjp);
          return;
        }
        zzi.zzb(zzi.this).zzb("sendMessageToNativeJs", paramAnonymouszzjp);
      }
    });
    return this.zzpD;
  }
  
  public View zzdS()
  {
    if (this.zzyw != null) {
      return (View)this.zzyw.get();
    }
    return null;
  }
  
  zzjp zzdT()
  {
    return zzr.zzbD().zza(this.mContext, AdSizeParcel.zzt(this.mContext), false, false, this.zzyt, this.zzpT);
  }
  
  public void zzg(View paramView) {}
  
  public void zzh(View paramView)
  {
    synchronized (this.zzpV)
    {
      if (this.zzyu) {
        return;
      }
      if (!paramView.isShown()) {
        return;
      }
    }
    if (!paramView.getGlobalVisibleRect(new Rect(), null)) {
      return;
    }
    recordImpression();
  }
  
  public void zzi(View paramView)
  {
    this.zzyw = new WeakReference(paramView);
  }
  
  protected void zzn(boolean paramBoolean)
  {
    this.zzyu = paramBoolean;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\formats\zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */