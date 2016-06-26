package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import java.util.Map;
import org.json.JSONObject;

@zzhb
class zzjs
  extends FrameLayout
  implements zzjp
{
  private final zzjp zzNN;
  private final zzjo zzNO;
  
  public zzjs(zzjp paramzzjp)
  {
    super(paramzzjp.getContext());
    this.zzNN = paramzzjp;
    this.zzNO = new zzjo(paramzzjp.zzhQ(), this, this);
    paramzzjp = this.zzNN.zzhU();
    if (paramzzjp != null) {
      paramzzjp.zzh(this);
    }
    addView(this.zzNN.getView());
  }
  
  public void clearCache(boolean paramBoolean)
  {
    this.zzNN.clearCache(paramBoolean);
  }
  
  public void destroy()
  {
    this.zzNN.destroy();
  }
  
  public String getRequestId()
  {
    return this.zzNN.getRequestId();
  }
  
  public int getRequestedOrientation()
  {
    return this.zzNN.getRequestedOrientation();
  }
  
  public View getView()
  {
    return this;
  }
  
  public WebView getWebView()
  {
    return this.zzNN.getWebView();
  }
  
  public boolean isDestroyed()
  {
    return this.zzNN.isDestroyed();
  }
  
  public void loadData(String paramString1, String paramString2, String paramString3)
  {
    this.zzNN.loadData(paramString1, paramString2, paramString3);
  }
  
  public void loadDataWithBaseURL(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    this.zzNN.loadDataWithBaseURL(paramString1, paramString2, paramString3, paramString4, paramString5);
  }
  
  public void loadUrl(String paramString)
  {
    this.zzNN.loadUrl(paramString);
  }
  
  public void onPause()
  {
    this.zzNO.onPause();
    this.zzNN.onPause();
  }
  
  public void onResume()
  {
    this.zzNN.onResume();
  }
  
  public void setBackgroundColor(int paramInt)
  {
    this.zzNN.setBackgroundColor(paramInt);
  }
  
  public void setContext(Context paramContext)
  {
    this.zzNN.setContext(paramContext);
  }
  
  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    this.zzNN.setOnClickListener(paramOnClickListener);
  }
  
  public void setOnTouchListener(View.OnTouchListener paramOnTouchListener)
  {
    this.zzNN.setOnTouchListener(paramOnTouchListener);
  }
  
  public void setRequestedOrientation(int paramInt)
  {
    this.zzNN.setRequestedOrientation(paramInt);
  }
  
  public void setWebChromeClient(WebChromeClient paramWebChromeClient)
  {
    this.zzNN.setWebChromeClient(paramWebChromeClient);
  }
  
  public void setWebViewClient(WebViewClient paramWebViewClient)
  {
    this.zzNN.setWebViewClient(paramWebViewClient);
  }
  
  public void stopLoading()
  {
    this.zzNN.stopLoading();
  }
  
  public void zzD(boolean paramBoolean)
  {
    this.zzNN.zzD(paramBoolean);
  }
  
  public void zzE(boolean paramBoolean)
  {
    this.zzNN.zzE(paramBoolean);
  }
  
  public void zzF(boolean paramBoolean)
  {
    this.zzNN.zzF(paramBoolean);
  }
  
  public void zza(Context paramContext, AdSizeParcel paramAdSizeParcel, zzcb paramzzcb)
  {
    this.zzNN.zza(paramContext, paramAdSizeParcel, paramzzcb);
  }
  
  public void zza(AdSizeParcel paramAdSizeParcel)
  {
    this.zzNN.zza(paramAdSizeParcel);
  }
  
  public void zza(zzau paramzzau, boolean paramBoolean)
  {
    this.zzNN.zza(paramzzau, paramBoolean);
  }
  
  public void zza(String paramString, zzdf paramzzdf)
  {
    this.zzNN.zza(paramString, paramzzdf);
  }
  
  public void zza(String paramString, Map<String, ?> paramMap)
  {
    this.zzNN.zza(paramString, paramMap);
  }
  
  public void zza(String paramString, JSONObject paramJSONObject)
  {
    this.zzNN.zza(paramString, paramJSONObject);
  }
  
  public void zzaL(String paramString)
  {
    this.zzNN.zzaL(paramString);
  }
  
  public void zzaM(String paramString)
  {
    this.zzNN.zzaM(paramString);
  }
  
  public AdSizeParcel zzaN()
  {
    return this.zzNN.zzaN();
  }
  
  public void zzb(com.google.android.gms.ads.internal.overlay.zzd paramzzd)
  {
    this.zzNN.zzb(paramzzd);
  }
  
  public void zzb(String paramString, zzdf paramzzdf)
  {
    this.zzNN.zzb(paramString, paramzzdf);
  }
  
  public void zzb(String paramString, JSONObject paramJSONObject)
  {
    this.zzNN.zzb(paramString, paramJSONObject);
  }
  
  public void zzc(com.google.android.gms.ads.internal.overlay.zzd paramzzd)
  {
    this.zzNN.zzc(paramzzd);
  }
  
  public void zze(String paramString1, String paramString2)
  {
    this.zzNN.zze(paramString1, paramString2);
  }
  
  public boolean zzfL()
  {
    return this.zzNN.zzfL();
  }
  
  public void zzfr()
  {
    this.zzNN.zzfr();
  }
  
  public void zzhN()
  {
    this.zzNN.zzhN();
  }
  
  public void zzhO()
  {
    this.zzNN.zzhO();
  }
  
  public Activity zzhP()
  {
    return this.zzNN.zzhP();
  }
  
  public Context zzhQ()
  {
    return this.zzNN.zzhQ();
  }
  
  public com.google.android.gms.ads.internal.zzd zzhR()
  {
    return this.zzNN.zzhR();
  }
  
  public com.google.android.gms.ads.internal.overlay.zzd zzhS()
  {
    return this.zzNN.zzhS();
  }
  
  public com.google.android.gms.ads.internal.overlay.zzd zzhT()
  {
    return this.zzNN.zzhT();
  }
  
  public zzjq zzhU()
  {
    return this.zzNN.zzhU();
  }
  
  public boolean zzhV()
  {
    return this.zzNN.zzhV();
  }
  
  public zzan zzhW()
  {
    return this.zzNN.zzhW();
  }
  
  public VersionInfoParcel zzhX()
  {
    return this.zzNN.zzhX();
  }
  
  public boolean zzhY()
  {
    return this.zzNN.zzhY();
  }
  
  public void zzhZ()
  {
    this.zzNO.onDestroy();
    this.zzNN.zzhZ();
  }
  
  public zzjo zzia()
  {
    return this.zzNO;
  }
  
  public zzbz zzib()
  {
    return this.zzNN.zzib();
  }
  
  public zzca zzic()
  {
    return this.zzNN.zzic();
  }
  
  public void zzid()
  {
    this.zzNN.zzid();
  }
  
  public void zzie()
  {
    this.zzNN.zzie();
  }
  
  public View.OnClickListener zzif()
  {
    return this.zzNN.zzif();
  }
  
  public void zzy(int paramInt)
  {
    this.zzNN.zzy(paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzjs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */