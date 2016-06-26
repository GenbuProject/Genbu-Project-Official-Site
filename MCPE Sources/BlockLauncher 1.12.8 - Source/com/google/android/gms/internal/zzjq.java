package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.internal.overlay.AdLauncherIntentInfoParcel;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.overlay.zzg;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzr;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

@zzhb
public class zzjq
  extends WebViewClient
{
  private static final String[] zzNy = { "UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS" };
  private static final String[] zzNz = { "NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID" };
  private zzft zzDk;
  private zza zzGm;
  private final HashMap<String, List<zzdf>> zzNA = new HashMap();
  private zzg zzNB;
  private zzb zzNC;
  private boolean zzND = false;
  private boolean zzNE;
  private zzp zzNF;
  private final zzfr zzNG;
  private boolean zzNH;
  private boolean zzNI;
  private boolean zzNJ;
  private int zzNK;
  protected zzjp zzpD;
  private final Object zzpV = new Object();
  private boolean zzsz;
  private zza zztz;
  private zzdb zzyW;
  private com.google.android.gms.ads.internal.zze zzzA;
  private zzfn zzzB;
  private zzdh zzzD;
  private zzdj zzzy;
  
  public zzjq(zzjp paramzzjp, boolean paramBoolean)
  {
    this(paramzzjp, paramBoolean, new zzfr(paramzzjp, paramzzjp.zzhQ(), new zzbl(paramzzjp.getContext())), null);
  }
  
  zzjq(zzjp paramzzjp, boolean paramBoolean, zzfr paramzzfr, zzfn paramzzfn)
  {
    this.zzpD = paramzzjp;
    this.zzsz = paramBoolean;
    this.zzNG = paramzzfr;
    this.zzzB = paramzzfn;
  }
  
  private void zza(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (!((Boolean)zzbt.zzwO.get()).booleanValue()) {
      return;
    }
    Bundle localBundle = new Bundle();
    localBundle.putString("err", paramString1);
    localBundle.putString("code", paramString2);
    localBundle.putString("host", zzaN(paramString3));
    zzr.zzbC().zza(paramContext, this.zzpD.zzhX().afmaVersion, "gmob-apps", localBundle, true);
  }
  
  private String zzaN(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    paramString = Uri.parse(paramString);
    if (paramString.getHost() != null) {
      return paramString.getHost();
    }
    return "";
  }
  
  private static boolean zzg(Uri paramUri)
  {
    paramUri = paramUri.getScheme();
    return ("http".equalsIgnoreCase(paramUri)) || ("https".equalsIgnoreCase(paramUri));
  }
  
  private void zzij()
  {
    synchronized (this.zzpV)
    {
      this.zzNE = true;
      this.zzNK += 1;
      zzim();
      return;
    }
  }
  
  private void zzik()
  {
    this.zzNK -= 1;
    zzim();
  }
  
  private void zzil()
  {
    this.zzNJ = true;
    zzim();
  }
  
  public final void onLoadResource(WebView paramWebView, String paramString)
  {
    zzin.v("Loading resource: " + paramString);
    paramWebView = Uri.parse(paramString);
    if (("gmsg".equalsIgnoreCase(paramWebView.getScheme())) && ("mobileads.google.com".equalsIgnoreCase(paramWebView.getHost()))) {
      zzh(paramWebView);
    }
  }
  
  public final void onPageFinished(WebView arg1, String paramString)
  {
    synchronized (this.zzpV)
    {
      if (this.zzNH)
      {
        zzin.v("Blank page loaded, 1...");
        this.zzpD.zzhZ();
        return;
      }
      this.zzNI = true;
      zzim();
      return;
    }
  }
  
  public final void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    if ((paramInt < 0) && (-paramInt - 1 < zzNy.length)) {}
    for (String str = zzNy[(-paramInt - 1)];; str = String.valueOf(paramInt))
    {
      zza(this.zzpD.getContext(), "http_err", str, paramString2);
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
      return;
    }
  }
  
  public final void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
  {
    int i;
    if (paramSslError != null)
    {
      i = paramSslError.getPrimaryError();
      if ((i < 0) || (i >= zzNz.length)) {
        break label65;
      }
    }
    label65:
    for (String str = zzNz[i];; str = String.valueOf(i))
    {
      zza(this.zzpD.getContext(), "ssl_err", str, zzr.zzbE().zza(paramSslError));
      super.onReceivedSslError(paramWebView, paramSslErrorHandler, paramSslError);
      return;
    }
  }
  
  public final void reset()
  {
    synchronized (this.zzpV)
    {
      this.zzNA.clear();
      this.zztz = null;
      this.zzNB = null;
      this.zzGm = null;
      this.zzyW = null;
      this.zzND = false;
      this.zzsz = false;
      this.zzNE = false;
      this.zzzD = null;
      this.zzNF = null;
      this.zzNC = null;
      if (this.zzzB != null)
      {
        this.zzzB.zzp(true);
        this.zzzB = null;
      }
      return;
    }
  }
  
  public boolean shouldOverrideKeyEvent(WebView paramWebView, KeyEvent paramKeyEvent)
  {
    switch (paramKeyEvent.getKeyCode())
    {
    default: 
      return false;
    }
    return true;
  }
  
  public final boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    zzin.v("AdWebView shouldOverrideUrlLoading: " + paramString);
    Uri localUri = Uri.parse(paramString);
    if (("gmsg".equalsIgnoreCase(localUri.getScheme())) && ("mobileads.google.com".equalsIgnoreCase(localUri.getHost()))) {
      zzh(localUri);
    }
    for (;;)
    {
      return true;
      if ((this.zzND) && (paramWebView == this.zzpD.getWebView()) && (zzg(localUri)))
      {
        if ((this.zztz != null) && (((Boolean)zzbt.zzww.get()).booleanValue()))
        {
          this.zztz.onAdClicked();
          this.zztz = null;
        }
        return super.shouldOverrideUrlLoading(paramWebView, paramString);
      }
      if (!this.zzpD.getWebView().willNotDraw())
      {
        try
        {
          zzan localzzan = this.zzpD.zzhW();
          paramWebView = localUri;
          if (localzzan != null)
          {
            paramWebView = localUri;
            if (localzzan.zzb(localUri)) {
              paramWebView = localzzan.zza(localUri, this.zzpD.getContext());
            }
          }
        }
        catch (zzao paramWebView)
        {
          for (;;)
          {
            zzin.zzaK("Unable to append parameter to URL: " + paramString);
            paramWebView = localUri;
          }
          this.zzzA.zzq(paramString);
        }
        if ((this.zzzA == null) || (this.zzzA.zzbh())) {
          zza(new AdLauncherIntentInfoParcel("android.intent.action.VIEW", paramWebView.toString(), null, null, null, null, null));
        }
      }
      else
      {
        zzin.zzaK("AdWebView unable to handle URL: " + paramString);
      }
    }
  }
  
  public void zzG(boolean paramBoolean)
  {
    this.zzND = paramBoolean;
  }
  
  public void zza(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    this.zzNG.zzf(paramInt1, paramInt2);
    if (this.zzzB != null) {
      this.zzzB.zza(paramInt1, paramInt2, paramBoolean);
    }
  }
  
  public final void zza(AdLauncherIntentInfoParcel paramAdLauncherIntentInfoParcel)
  {
    zzg localzzg = null;
    boolean bool = this.zzpD.zzhY();
    zza localzza;
    if ((bool) && (!this.zzpD.zzaN().zzui))
    {
      localzza = null;
      if (!bool) {
        break label75;
      }
    }
    for (;;)
    {
      zza(new AdOverlayInfoParcel(paramAdLauncherIntentInfoParcel, localzza, localzzg, this.zzNF, this.zzpD.zzhX()));
      return;
      localzza = this.zztz;
      break;
      label75:
      localzzg = this.zzNB;
    }
  }
  
  public void zza(AdOverlayInfoParcel paramAdOverlayInfoParcel)
  {
    boolean bool2 = false;
    if (this.zzzB != null) {}
    for (boolean bool1 = this.zzzB.zzeN();; bool1 = false)
    {
      com.google.android.gms.ads.internal.overlay.zze localzze = zzr.zzbA();
      Context localContext = this.zzpD.getContext();
      if (!bool1) {
        bool2 = true;
      }
      localzze.zza(localContext, paramAdOverlayInfoParcel, bool2);
      return;
    }
  }
  
  public void zza(zza paramzza)
  {
    this.zzGm = paramzza;
  }
  
  public void zza(zzb paramzzb)
  {
    this.zzNC = paramzzb;
  }
  
  public void zza(String paramString, zzdf paramzzdf)
  {
    synchronized (this.zzpV)
    {
      List localList = (List)this.zzNA.get(paramString);
      Object localObject1 = localList;
      if (localList == null)
      {
        localObject1 = new CopyOnWriteArrayList();
        this.zzNA.put(paramString, localObject1);
      }
      ((List)localObject1).add(paramzzdf);
      return;
    }
  }
  
  public final void zza(boolean paramBoolean, int paramInt)
  {
    if ((this.zzpD.zzhY()) && (!this.zzpD.zzaN().zzui)) {}
    for (zza localzza = null;; localzza = this.zztz)
    {
      zza(new AdOverlayInfoParcel(localzza, this.zzNB, this.zzNF, this.zzpD, paramBoolean, paramInt, this.zzpD.zzhX()));
      return;
    }
  }
  
  public final void zza(boolean paramBoolean, int paramInt, String paramString)
  {
    Object localObject = null;
    boolean bool = this.zzpD.zzhY();
    zza localzza;
    if ((bool) && (!this.zzpD.zzaN().zzui))
    {
      localzza = null;
      if (!bool) {
        break label95;
      }
    }
    for (;;)
    {
      zza(new AdOverlayInfoParcel(localzza, (zzg)localObject, this.zzyW, this.zzNF, this.zzpD, paramBoolean, paramInt, paramString, this.zzpD.zzhX(), this.zzzD));
      return;
      localzza = this.zztz;
      break;
      label95:
      localObject = new zzc(this.zzpD, this.zzNB);
    }
  }
  
  public final void zza(boolean paramBoolean, int paramInt, String paramString1, String paramString2)
  {
    boolean bool = this.zzpD.zzhY();
    zza localzza;
    if ((bool) && (!this.zzpD.zzaN().zzui))
    {
      localzza = null;
      if (!bool) {
        break label97;
      }
    }
    label97:
    for (Object localObject = null;; localObject = new zzc(this.zzpD, this.zzNB))
    {
      zza(new AdOverlayInfoParcel(localzza, (zzg)localObject, this.zzyW, this.zzNF, this.zzpD, paramBoolean, paramInt, paramString1, paramString2, this.zzpD.zzhX(), this.zzzD));
      return;
      localzza = this.zztz;
      break;
    }
  }
  
  public void zzb(zza paramzza, zzg paramzzg, zzdb paramzzdb, zzp paramzzp, boolean paramBoolean, zzdh paramzzdh, zzdj paramzzdj, com.google.android.gms.ads.internal.zze paramzze, zzft paramzzft)
  {
    com.google.android.gms.ads.internal.zze localzze = paramzze;
    if (paramzze == null) {
      localzze = new com.google.android.gms.ads.internal.zze(false);
    }
    this.zzzB = new zzfn(this.zzpD, paramzzft);
    zza("/appEvent", new zzda(paramzzdb));
    zza("/backButton", zzde.zzzh);
    zza("/canOpenURLs", zzde.zzyY);
    zza("/canOpenIntents", zzde.zzyZ);
    zza("/click", zzde.zzza);
    zza("/close", zzde.zzzb);
    zza("/customClose", zzde.zzzd);
    zza("/instrument", zzde.zzzk);
    zza("/delayPageLoaded", new zzd(null));
    zza("/httpTrack", zzde.zzze);
    zza("/log", zzde.zzzf);
    zza("/mraid", new zzdl(localzze, this.zzzB));
    zza("/mraidLoaded", this.zzNG);
    zza("/open", new zzdm(paramzzdh, localzze, this.zzzB));
    zza("/precache", zzde.zzzj);
    zza("/touch", zzde.zzzg);
    zza("/video", zzde.zzzi);
    zza("/appStreaming", zzde.zzzc);
    if (paramzzdj != null) {
      zza("/setInterstitialProperties", new zzdi(paramzzdj));
    }
    this.zztz = paramzza;
    this.zzNB = paramzzg;
    this.zzyW = paramzzdb;
    this.zzzD = paramzzdh;
    this.zzNF = paramzzp;
    this.zzzA = localzze;
    this.zzDk = paramzzft;
    this.zzzy = paramzzdj;
    zzG(paramBoolean);
  }
  
  public void zzb(String paramString, zzdf paramzzdf)
  {
    synchronized (this.zzpV)
    {
      paramString = (List)this.zzNA.get(paramString);
      if (paramString == null) {
        return;
      }
      paramString.remove(paramzzdf);
      return;
    }
  }
  
  public boolean zzcv()
  {
    synchronized (this.zzpV)
    {
      boolean bool = this.zzsz;
      return bool;
    }
  }
  
  public void zze(int paramInt1, int paramInt2)
  {
    if (this.zzzB != null) {
      this.zzzB.zze(paramInt1, paramInt2);
    }
  }
  
  public final void zzfo()
  {
    synchronized (this.zzpV)
    {
      this.zzND = false;
      this.zzsz = true;
      zzir.runOnUiThread(new Runnable()
      {
        public void run()
        {
          zzjq.this.zzpD.zzid();
          zzd localzzd = zzjq.this.zzpD.zzhS();
          if (localzzd != null) {
            localzzd.zzfo();
          }
          if (zzjq.zzd(zzjq.this) != null)
          {
            zzjq.zzd(zzjq.this).zzbi();
            zzjq.zza(zzjq.this, null);
          }
        }
      });
      return;
    }
  }
  
  public void zzh(Uri paramUri)
  {
    Object localObject2 = paramUri.getPath();
    Object localObject1 = (List)this.zzNA.get(localObject2);
    if (localObject1 != null)
    {
      paramUri = zzr.zzbC().zze(paramUri);
      if (zzin.zzQ(2))
      {
        zzin.v("Received GMSG: " + (String)localObject2);
        localObject2 = paramUri.keySet().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          String str = (String)((Iterator)localObject2).next();
          zzin.v("  " + str + ": " + (String)paramUri.get(str));
        }
      }
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext()) {
        ((zzdf)((Iterator)localObject1).next()).zza(this.zzpD, paramUri);
      }
    }
    zzin.v("No GMSG handler found for GMSG: " + paramUri);
  }
  
  public void zzh(zzjp paramzzjp)
  {
    this.zzpD = paramzzjp;
  }
  
  public com.google.android.gms.ads.internal.zze zzig()
  {
    return this.zzzA;
  }
  
  public boolean zzih()
  {
    synchronized (this.zzpV)
    {
      boolean bool = this.zzNE;
      return bool;
    }
  }
  
  public void zzii()
  {
    synchronized (this.zzpV)
    {
      zzin.v("Loading blank page in WebView, 2...");
      this.zzNH = true;
      this.zzpD.zzaL("about:blank");
      return;
    }
  }
  
  public final void zzim()
  {
    zza localzza;
    zzjp localzzjp;
    if ((this.zzGm != null) && (((this.zzNI) && (this.zzNK <= 0)) || (this.zzNJ)))
    {
      localzza = this.zzGm;
      localzzjp = this.zzpD;
      if (this.zzNJ) {
        break label70;
      }
    }
    label70:
    for (boolean bool = true;; bool = false)
    {
      localzza.zza(localzzjp, bool);
      this.zzGm = null;
      this.zzpD.zzie();
      return;
    }
  }
  
  public static abstract interface zza
  {
    public abstract void zza(zzjp paramzzjp, boolean paramBoolean);
  }
  
  public static abstract interface zzb
  {
    public abstract void zzbi();
  }
  
  private static class zzc
    implements zzg
  {
    private zzg zzNB;
    private zzjp zzNM;
    
    public zzc(zzjp paramzzjp, zzg paramzzg)
    {
      this.zzNM = paramzzjp;
      this.zzNB = paramzzg;
    }
    
    public void onPause() {}
    
    public void onResume() {}
    
    public void zzaW()
    {
      this.zzNB.zzaW();
      this.zzNM.zzhN();
    }
    
    public void zzaX()
    {
      this.zzNB.zzaX();
      this.zzNM.zzfr();
    }
  }
  
  private class zzd
    implements zzdf
  {
    private zzd() {}
    
    public void zza(zzjp paramzzjp, Map<String, String> paramMap)
    {
      if (paramMap.keySet().contains("start")) {
        zzjq.zza(zzjq.this);
      }
      do
      {
        return;
        if (paramMap.keySet().contains("stop"))
        {
          zzjq.zzb(zzjq.this);
          return;
        }
      } while (!paramMap.keySet().contains("cancel"));
      zzjq.zzc(zzjq.this);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzjq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */