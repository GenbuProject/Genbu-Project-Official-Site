package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.zzr;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzhb
class zzjt
  extends WebView
  implements ViewTreeObserver.OnGlobalLayoutListener, DownloadListener, zzjp
{
  private AdSizeParcel zzCh;
  private int zzDC = -1;
  private int zzDD = -1;
  private int zzDF = -1;
  private int zzDG = -1;
  private String zzEY = "";
  private Boolean zzLB;
  private final zza zzNP;
  private zzjq zzNQ;
  private com.google.android.gms.ads.internal.overlay.zzd zzNR;
  private boolean zzNS;
  private boolean zzNT;
  private boolean zzNU;
  private boolean zzNV;
  private int zzNW;
  private boolean zzNX = true;
  private zzbz zzNY;
  private zzbz zzNZ;
  private zzbz zzOa;
  private zzca zzOb;
  private WeakReference<View.OnClickListener> zzOc;
  private com.google.android.gms.ads.internal.overlay.zzd zzOd;
  private Map<String, zzdr> zzOe;
  private final VersionInfoParcel zzpT;
  private final Object zzpV = new Object();
  private final com.google.android.gms.ads.internal.zzd zzpm;
  private zzjc zzrV;
  private final WindowManager zzsb;
  private final zzan zzyt;
  
  protected zzjt(zza paramzza, AdSizeParcel paramAdSizeParcel, boolean paramBoolean1, boolean paramBoolean2, zzan paramzzan, VersionInfoParcel paramVersionInfoParcel, zzcb paramzzcb, com.google.android.gms.ads.internal.zzd paramzzd)
  {
    super(paramzza);
    this.zzNP = paramzza;
    this.zzCh = paramAdSizeParcel;
    this.zzNU = paramBoolean1;
    this.zzNW = -1;
    this.zzyt = paramzzan;
    this.zzpT = paramVersionInfoParcel;
    this.zzpm = paramzzd;
    this.zzsb = ((WindowManager)getContext().getSystemService("window"));
    setBackgroundColor(0);
    paramAdSizeParcel = getSettings();
    paramAdSizeParcel.setAllowFileAccess(false);
    paramAdSizeParcel.setJavaScriptEnabled(true);
    paramAdSizeParcel.setSavePassword(false);
    paramAdSizeParcel.setSupportMultipleWindows(true);
    paramAdSizeParcel.setJavaScriptCanOpenWindowsAutomatically(true);
    if (Build.VERSION.SDK_INT >= 21) {
      paramAdSizeParcel.setMixedContentMode(0);
    }
    zzr.zzbC().zza(paramzza, paramVersionInfoParcel.afmaVersion, paramAdSizeParcel);
    zzr.zzbE().zza(getContext(), paramAdSizeParcel);
    setDownloadListener(this);
    zziq();
    if (zzne.zzsi()) {
      addJavascriptInterface(new zzju(this), "googleAdsJsInterface");
    }
    this.zzrV = new zzjc(this.zzNP.zzhP(), this, null);
    zzd(paramzzcb);
  }
  
  static zzjt zzb(Context paramContext, AdSizeParcel paramAdSizeParcel, boolean paramBoolean1, boolean paramBoolean2, zzan paramzzan, VersionInfoParcel paramVersionInfoParcel, zzcb paramzzcb, com.google.android.gms.ads.internal.zzd paramzzd)
  {
    return new zzjt(new zza(paramContext), paramAdSizeParcel, paramBoolean1, paramBoolean2, paramzzan, paramVersionInfoParcel, paramzzcb, paramzzd);
  }
  
  private void zzd(zzcb paramzzcb)
  {
    zziu();
    this.zzOb = new zzca(new zzcb(true, "make_wv", this.zzCh.zzuh));
    this.zzOb.zzdA().zzc(paramzzcb);
    this.zzNZ = zzbx.zzb(this.zzOb.zzdA());
    this.zzOb.zza("native:view_create", this.zzNZ);
    this.zzOa = null;
    this.zzNY = null;
  }
  
  private void zzio()
  {
    synchronized (this.zzpV)
    {
      this.zzLB = zzr.zzbF().zzhg();
      Boolean localBoolean = this.zzLB;
      if (localBoolean == null) {}
      try
      {
        evaluateJavascript("(function(){})()", null);
        zzb(Boolean.valueOf(true));
        return;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        for (;;)
        {
          zzb(Boolean.valueOf(false));
        }
      }
    }
  }
  
  private void zzip()
  {
    zzbx.zza(this.zzOb.zzdA(), this.zzNY, new String[] { "aeh" });
  }
  
  private void zziq()
  {
    for (;;)
    {
      synchronized (this.zzpV)
      {
        if ((this.zzNU) || (this.zzCh.zzui))
        {
          if (Build.VERSION.SDK_INT < 14)
          {
            zzin.zzaI("Disabling hardware acceleration on an overlay.");
            zzir();
            return;
          }
          zzin.zzaI("Enabling hardware acceleration on an overlay.");
          zzis();
        }
      }
      if (Build.VERSION.SDK_INT < 18)
      {
        zzin.zzaI("Disabling hardware acceleration on an AdView.");
        zzir();
      }
      else
      {
        zzin.zzaI("Enabling hardware acceleration on an AdView.");
        zzis();
      }
    }
  }
  
  private void zzir()
  {
    synchronized (this.zzpV)
    {
      if (!this.zzNV) {
        zzr.zzbE().zzn(this);
      }
      this.zzNV = true;
      return;
    }
  }
  
  private void zzis()
  {
    synchronized (this.zzpV)
    {
      if (this.zzNV) {
        zzr.zzbE().zzm(this);
      }
      this.zzNV = false;
      return;
    }
  }
  
  private void zzit()
  {
    synchronized (this.zzpV)
    {
      if (this.zzOe != null)
      {
        Iterator localIterator = this.zzOe.values().iterator();
        if (localIterator.hasNext()) {
          ((zzdr)localIterator.next()).release();
        }
      }
    }
  }
  
  private void zziu()
  {
    if (this.zzOb == null) {}
    zzcb localzzcb;
    do
    {
      return;
      localzzcb = this.zzOb.zzdA();
    } while ((localzzcb == null) || (zzr.zzbF().zzhb() == null));
    zzr.zzbF().zzhb().zza(localzzcb);
  }
  
  public void destroy()
  {
    synchronized (this.zzpV)
    {
      zziu();
      this.zzrV.zzhF();
      if (this.zzNR != null)
      {
        this.zzNR.close();
        this.zzNR.onDestroy();
        this.zzNR = null;
      }
      this.zzNQ.reset();
      if (this.zzNT) {
        return;
      }
      zzr.zzbR().zzd(this);
      zzit();
      this.zzNT = true;
      zzin.v("Initiating WebView self destruct sequence in 3...");
      this.zzNQ.zzii();
      return;
    }
  }
  
  @TargetApi(19)
  public void evaluateJavascript(String paramString, ValueCallback<String> paramValueCallback)
  {
    synchronized (this.zzpV)
    {
      if (isDestroyed())
      {
        zzin.zzaK("The webview is destroyed. Ignoring action.");
        if (paramValueCallback != null) {
          paramValueCallback.onReceiveValue(null);
        }
        return;
      }
      super.evaluateJavascript(paramString, paramValueCallback);
      return;
    }
  }
  
  protected void finalize()
    throws Throwable
  {
    synchronized (this.zzpV)
    {
      if (!this.zzNT)
      {
        zzr.zzbR().zzd(this);
        zzit();
      }
      super.finalize();
      return;
    }
  }
  
  public String getRequestId()
  {
    synchronized (this.zzpV)
    {
      String str = this.zzEY;
      return str;
    }
  }
  
  public int getRequestedOrientation()
  {
    synchronized (this.zzpV)
    {
      int i = this.zzNW;
      return i;
    }
  }
  
  public View getView()
  {
    return this;
  }
  
  public WebView getWebView()
  {
    return this;
  }
  
  public boolean isDestroyed()
  {
    synchronized (this.zzpV)
    {
      boolean bool = this.zzNT;
      return bool;
    }
  }
  
  public void loadData(String paramString1, String paramString2, String paramString3)
  {
    synchronized (this.zzpV)
    {
      if (!isDestroyed())
      {
        super.loadData(paramString1, paramString2, paramString3);
        return;
      }
      zzin.zzaK("The webview is destroyed. Ignoring action.");
    }
  }
  
  public void loadDataWithBaseURL(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    synchronized (this.zzpV)
    {
      if (!isDestroyed())
      {
        super.loadDataWithBaseURL(paramString1, paramString2, paramString3, paramString4, paramString5);
        return;
      }
      zzin.zzaK("The webview is destroyed. Ignoring action.");
    }
  }
  
  public void loadUrl(String paramString)
  {
    for (;;)
    {
      synchronized (this.zzpV)
      {
        boolean bool = isDestroyed();
        if (!bool) {
          try
          {
            super.loadUrl(paramString);
            return;
          }
          catch (Throwable paramString)
          {
            zzin.zzaK("Could not call loadUrl. " + paramString);
            continue;
          }
        }
      }
      zzin.zzaK("The webview is destroyed. Ignoring action.");
    }
  }
  
  protected void onAttachedToWindow()
  {
    synchronized (this.zzpV)
    {
      super.onAttachedToWindow();
      if (!isDestroyed()) {
        this.zzrV.onAttachedToWindow();
      }
      return;
    }
  }
  
  protected void onDetachedFromWindow()
  {
    synchronized (this.zzpV)
    {
      if (!isDestroyed()) {
        this.zzrV.onDetachedFromWindow();
      }
      super.onDetachedFromWindow();
      return;
    }
  }
  
  public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    try
    {
      paramString2 = new Intent("android.intent.action.VIEW");
      paramString2.setDataAndType(Uri.parse(paramString1), paramString4);
      zzr.zzbC().zzb(getContext(), paramString2);
      return;
    }
    catch (ActivityNotFoundException paramString2)
    {
      zzin.zzaI("Couldn't find an Activity to view url/mimetype: " + paramString1 + " / " + paramString4);
    }
  }
  
  @TargetApi(21)
  protected void onDraw(Canvas paramCanvas)
  {
    if (isDestroyed()) {}
    while ((Build.VERSION.SDK_INT == 21) && (paramCanvas.isHardwareAccelerated()) && (!isAttachedToWindow())) {
      return;
    }
    super.onDraw(paramCanvas);
  }
  
  public void onGlobalLayout()
  {
    boolean bool = zzin();
    com.google.android.gms.ads.internal.overlay.zzd localzzd = zzhS();
    if ((localzzd != null) && (bool)) {
      localzzd.zzfq();
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int j = Integer.MAX_VALUE;
    synchronized (this.zzpV)
    {
      if (isDestroyed())
      {
        setMeasuredDimension(0, 0);
        return;
      }
      if ((isInEditMode()) || (this.zzNU) || (this.zzCh.zzuk) || (this.zzCh.zzul))
      {
        super.onMeasure(paramInt1, paramInt2);
        return;
      }
    }
    if (this.zzCh.zzui)
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      this.zzsb.getDefaultDisplay().getMetrics(localDisplayMetrics);
      setMeasuredDimension(localDisplayMetrics.widthPixels, localDisplayMetrics.heightPixels);
      return;
    }
    int n = View.MeasureSpec.getMode(paramInt1);
    int i = View.MeasureSpec.getSize(paramInt1);
    int m = View.MeasureSpec.getMode(paramInt2);
    int k = View.MeasureSpec.getSize(paramInt2);
    if (n != Integer.MIN_VALUE) {
      if (n == 1073741824) {
        break label368;
      }
    }
    for (;;)
    {
      if ((this.zzCh.widthPixels > paramInt1) || (this.zzCh.heightPixels > paramInt2))
      {
        float f = this.zzNP.getResources().getDisplayMetrics().density;
        zzin.zzaK("Not enough space to show ad. Needs " + (int)(this.zzCh.widthPixels / f) + "x" + (int)(this.zzCh.heightPixels / f) + " dp, but only has " + (int)(i / f) + "x" + (int)(k / f) + " dp.");
        if (getVisibility() != 8) {
          setVisibility(4);
        }
        setMeasuredDimension(0, 0);
      }
      for (;;)
      {
        return;
        if (getVisibility() != 8) {
          setVisibility(0);
        }
        setMeasuredDimension(this.zzCh.widthPixels, this.zzCh.heightPixels);
      }
      paramInt1 = Integer.MAX_VALUE;
      break label371;
      label368:
      paramInt1 = i;
      label371:
      if (m != Integer.MIN_VALUE)
      {
        paramInt2 = j;
        if (m != 1073741824) {}
      }
      else
      {
        paramInt2 = k;
      }
    }
  }
  
  public void onPause()
  {
    if (isDestroyed()) {}
    for (;;)
    {
      return;
      try
      {
        if (zzne.zzsd())
        {
          super.onPause();
          return;
        }
      }
      catch (Exception localException)
      {
        zzin.zzb("Could not pause webview.", localException);
      }
    }
  }
  
  public void onResume()
  {
    if (isDestroyed()) {}
    for (;;)
    {
      return;
      try
      {
        if (zzne.zzsd())
        {
          super.onResume();
          return;
        }
      }
      catch (Exception localException)
      {
        zzin.zzb("Could not resume webview.", localException);
      }
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.zzyt != null) {
      this.zzyt.zza(paramMotionEvent);
    }
    if (isDestroyed()) {
      return false;
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public void setContext(Context paramContext)
  {
    this.zzNP.setBaseContext(paramContext);
    this.zzrV.zzi(this.zzNP.zzhP());
  }
  
  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    this.zzOc = new WeakReference(paramOnClickListener);
    super.setOnClickListener(paramOnClickListener);
  }
  
  public void setRequestedOrientation(int paramInt)
  {
    synchronized (this.zzpV)
    {
      this.zzNW = paramInt;
      if (this.zzNR != null) {
        this.zzNR.setRequestedOrientation(this.zzNW);
      }
      return;
    }
  }
  
  public void setWebViewClient(WebViewClient paramWebViewClient)
  {
    super.setWebViewClient(paramWebViewClient);
    if ((paramWebViewClient instanceof zzjq)) {
      this.zzNQ = ((zzjq)paramWebViewClient);
    }
  }
  
  public void stopLoading()
  {
    if (isDestroyed()) {
      return;
    }
    try
    {
      super.stopLoading();
      return;
    }
    catch (Exception localException)
    {
      zzin.zzb("Could not stop loading webview.", localException);
    }
  }
  
  public void zzD(boolean paramBoolean)
  {
    synchronized (this.zzpV)
    {
      this.zzNU = paramBoolean;
      zziq();
      return;
    }
  }
  
  public void zzE(boolean paramBoolean)
  {
    synchronized (this.zzpV)
    {
      if (this.zzNR != null)
      {
        this.zzNR.zza(this.zzNQ.zzcv(), paramBoolean);
        return;
      }
      this.zzNS = paramBoolean;
    }
  }
  
  public void zzF(boolean paramBoolean)
  {
    synchronized (this.zzpV)
    {
      this.zzNX = paramBoolean;
      return;
    }
  }
  
  public void zza(Context paramContext, AdSizeParcel paramAdSizeParcel, zzcb paramzzcb)
  {
    synchronized (this.zzpV)
    {
      this.zzrV.zzhF();
      setContext(paramContext);
      this.zzNR = null;
      this.zzCh = paramAdSizeParcel;
      this.zzNU = false;
      this.zzNS = false;
      this.zzEY = "";
      this.zzNW = -1;
      zzr.zzbE().zzj(this);
      loadUrl("about:blank");
      this.zzNQ.reset();
      setOnTouchListener(null);
      setOnClickListener(null);
      this.zzNX = true;
      zzd(paramzzcb);
      return;
    }
  }
  
  public void zza(AdSizeParcel paramAdSizeParcel)
  {
    synchronized (this.zzpV)
    {
      this.zzCh = paramAdSizeParcel;
      requestLayout();
      return;
    }
  }
  
  public void zza(zzau paramzzau, boolean paramBoolean)
  {
    HashMap localHashMap = new HashMap();
    if (paramBoolean) {}
    for (paramzzau = "1";; paramzzau = "0")
    {
      localHashMap.put("isVisible", paramzzau);
      zza("onAdVisibilityChanged", localHashMap);
      return;
    }
  }
  
  @TargetApi(19)
  protected void zza(String paramString, ValueCallback<String> paramValueCallback)
  {
    synchronized (this.zzpV)
    {
      if (!isDestroyed()) {
        evaluateJavascript(paramString, paramValueCallback);
      }
      do
      {
        return;
        zzin.zzaK("The webview is destroyed. Ignoring action.");
      } while (paramValueCallback == null);
      paramValueCallback.onReceiveValue(null);
    }
  }
  
  public void zza(String paramString, zzdf paramzzdf)
  {
    if (this.zzNQ != null) {
      this.zzNQ.zza(paramString, paramzzdf);
    }
  }
  
  public void zza(String paramString, Map<String, ?> paramMap)
  {
    try
    {
      paramMap = zzr.zzbC().zzG(paramMap);
      zzb(paramString, paramMap);
      return;
    }
    catch (JSONException paramString)
    {
      zzin.zzaK("Could not convert parameters to JSON.");
    }
  }
  
  public void zza(String paramString, JSONObject paramJSONObject)
  {
    JSONObject localJSONObject = paramJSONObject;
    if (paramJSONObject == null) {
      localJSONObject = new JSONObject();
    }
    zze(paramString, localJSONObject.toString());
  }
  
  public void zzaL(String paramString)
  {
    synchronized (this.zzpV)
    {
      try
      {
        super.loadUrl(paramString);
        return;
      }
      catch (Throwable paramString)
      {
        for (;;)
        {
          zzin.zzaK("Could not call loadUrl. " + paramString);
        }
      }
    }
  }
  
  public void zzaM(String paramString)
  {
    Object localObject = this.zzpV;
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    try
    {
      this.zzEY = str;
      return;
    }
    finally {}
  }
  
  public AdSizeParcel zzaN()
  {
    synchronized (this.zzpV)
    {
      AdSizeParcel localAdSizeParcel = this.zzCh;
      return localAdSizeParcel;
    }
  }
  
  protected void zzaO(String paramString)
  {
    synchronized (this.zzpV)
    {
      if (!isDestroyed())
      {
        loadUrl(paramString);
        return;
      }
      zzin.zzaK("The webview is destroyed. Ignoring action.");
    }
  }
  
  protected void zzaP(String paramString)
  {
    if (zzne.zzsk())
    {
      if (zzhg() == null) {
        zzio();
      }
      if (zzhg().booleanValue())
      {
        zza(paramString, null);
        return;
      }
      zzaO("javascript:" + paramString);
      return;
    }
    zzaO("javascript:" + paramString);
  }
  
  public void zzb(com.google.android.gms.ads.internal.overlay.zzd paramzzd)
  {
    synchronized (this.zzpV)
    {
      this.zzNR = paramzzd;
      return;
    }
  }
  
  void zzb(Boolean paramBoolean)
  {
    this.zzLB = paramBoolean;
    zzr.zzbF().zzb(paramBoolean);
  }
  
  public void zzb(String paramString, zzdf paramzzdf)
  {
    if (this.zzNQ != null) {
      this.zzNQ.zzb(paramString, paramzzdf);
    }
  }
  
  public void zzb(String paramString, JSONObject paramJSONObject)
  {
    Object localObject = paramJSONObject;
    if (paramJSONObject == null) {
      localObject = new JSONObject();
    }
    paramJSONObject = ((JSONObject)localObject).toString();
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("AFMA_ReceiveMessage('");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("'");
    ((StringBuilder)localObject).append(",");
    ((StringBuilder)localObject).append(paramJSONObject);
    ((StringBuilder)localObject).append(");");
    zzin.v("Dispatching AFMA event: " + ((StringBuilder)localObject).toString());
    zzaP(((StringBuilder)localObject).toString());
  }
  
  public void zzc(com.google.android.gms.ads.internal.overlay.zzd paramzzd)
  {
    synchronized (this.zzpV)
    {
      this.zzOd = paramzzd;
      return;
    }
  }
  
  public void zze(String paramString1, String paramString2)
  {
    zzaP(paramString1 + "(" + paramString2 + ");");
  }
  
  public boolean zzfL()
  {
    synchronized (this.zzpV)
    {
      zzbx.zza(this.zzOb.zzdA(), this.zzNY, new String[] { "aebb" });
      boolean bool = this.zzNX;
      return bool;
    }
  }
  
  public void zzfr()
  {
    if (this.zzNY == null)
    {
      zzbx.zza(this.zzOb.zzdA(), this.zzOa, new String[] { "aes" });
      this.zzNY = zzbx.zzb(this.zzOb.zzdA());
      this.zzOb.zza("native:view_show", this.zzNY);
    }
    HashMap localHashMap = new HashMap(1);
    localHashMap.put("version", this.zzpT.afmaVersion);
    zza("onshow", localHashMap);
  }
  
  public void zzhN()
  {
    zzip();
    HashMap localHashMap = new HashMap(1);
    localHashMap.put("version", this.zzpT.afmaVersion);
    zza("onhide", localHashMap);
  }
  
  public void zzhO()
  {
    HashMap localHashMap = new HashMap(2);
    localHashMap.put("app_volume", String.valueOf(zzr.zzbC().zzbt()));
    localHashMap.put("device_volume", String.valueOf(zzr.zzbC().zzQ(getContext())));
    zza("volume", localHashMap);
  }
  
  public Activity zzhP()
  {
    return this.zzNP.zzhP();
  }
  
  public Context zzhQ()
  {
    return this.zzNP.zzhQ();
  }
  
  public com.google.android.gms.ads.internal.zzd zzhR()
  {
    return this.zzpm;
  }
  
  public com.google.android.gms.ads.internal.overlay.zzd zzhS()
  {
    synchronized (this.zzpV)
    {
      com.google.android.gms.ads.internal.overlay.zzd localzzd = this.zzNR;
      return localzzd;
    }
  }
  
  public com.google.android.gms.ads.internal.overlay.zzd zzhT()
  {
    synchronized (this.zzpV)
    {
      com.google.android.gms.ads.internal.overlay.zzd localzzd = this.zzOd;
      return localzzd;
    }
  }
  
  public zzjq zzhU()
  {
    return this.zzNQ;
  }
  
  public boolean zzhV()
  {
    return this.zzNS;
  }
  
  public zzan zzhW()
  {
    return this.zzyt;
  }
  
  public VersionInfoParcel zzhX()
  {
    return this.zzpT;
  }
  
  public boolean zzhY()
  {
    synchronized (this.zzpV)
    {
      boolean bool = this.zzNU;
      return bool;
    }
  }
  
  public void zzhZ()
  {
    synchronized (this.zzpV)
    {
      zzin.v("Destroying WebView!");
      zzir.zzMc.post(new Runnable()
      {
        public void run()
        {
          zzjt.zza(zzjt.this);
        }
      });
      return;
    }
  }
  
  Boolean zzhg()
  {
    synchronized (this.zzpV)
    {
      Boolean localBoolean = this.zzLB;
      return localBoolean;
    }
  }
  
  public zzjo zzia()
  {
    return null;
  }
  
  public zzbz zzib()
  {
    return this.zzOa;
  }
  
  public zzca zzic()
  {
    return this.zzOb;
  }
  
  public void zzid()
  {
    this.zzrV.zzhE();
  }
  
  public void zzie()
  {
    if (this.zzOa == null)
    {
      this.zzOa = zzbx.zzb(this.zzOb.zzdA());
      this.zzOb.zza("native:view_load", this.zzOa);
    }
  }
  
  public View.OnClickListener zzif()
  {
    return (View.OnClickListener)this.zzOc.get();
  }
  
  public boolean zzin()
  {
    if (!zzhU().zzcv()) {
      return false;
    }
    DisplayMetrics localDisplayMetrics = zzr.zzbC().zza(this.zzsb);
    int k = zzn.zzcS().zzb(localDisplayMetrics, localDisplayMetrics.widthPixels);
    int m = zzn.zzcS().zzb(localDisplayMetrics, localDisplayMetrics.heightPixels);
    Object localObject = zzhP();
    int j;
    int i;
    if ((localObject == null) || (((Activity)localObject).getWindow() == null))
    {
      j = m;
      i = k;
      label77:
      if ((this.zzDC == k) && (this.zzDD == m) && (this.zzDF == i) && (this.zzDG == j)) {
        break label224;
      }
      if ((this.zzDC == k) && (this.zzDD == m)) {
        break label226;
      }
    }
    label224:
    label226:
    for (boolean bool = true;; bool = false)
    {
      this.zzDC = k;
      this.zzDD = m;
      this.zzDF = i;
      this.zzDG = j;
      new zzfs(this).zza(k, m, i, j, localDisplayMetrics.density, this.zzsb.getDefaultDisplay().getRotation());
      return bool;
      localObject = zzr.zzbC().zze((Activity)localObject);
      i = zzn.zzcS().zzb(localDisplayMetrics, localObject[0]);
      j = zzn.zzcS().zzb(localDisplayMetrics, localObject[1]);
      break label77;
      break;
    }
  }
  
  public void zzy(int paramInt)
  {
    zzip();
    HashMap localHashMap = new HashMap(2);
    localHashMap.put("closetype", String.valueOf(paramInt));
    localHashMap.put("version", this.zzpT.afmaVersion);
    zza("onhide", localHashMap);
  }
  
  @zzhb
  public static class zza
    extends MutableContextWrapper
  {
    private Activity zzMM;
    private Context zzOg;
    private Context zzsa;
    
    public zza(Context paramContext)
    {
      super();
      setBaseContext(paramContext);
    }
    
    public Object getSystemService(String paramString)
    {
      return this.zzOg.getSystemService(paramString);
    }
    
    public void setBaseContext(Context paramContext)
    {
      this.zzsa = paramContext.getApplicationContext();
      if ((paramContext instanceof Activity)) {}
      for (Activity localActivity = (Activity)paramContext;; localActivity = null)
      {
        this.zzMM = localActivity;
        this.zzOg = paramContext;
        super.setBaseContext(this.zzsa);
        return;
      }
    }
    
    public void startActivity(Intent paramIntent)
    {
      if ((this.zzMM != null) && (!zzne.isAtLeastL()))
      {
        this.zzMM.startActivity(paramIntent);
        return;
      }
      paramIntent.setFlags(268435456);
      this.zzsa.startActivity(paramIntent);
    }
    
    public Activity zzhP()
    {
      return this.zzMM;
    }
    
    public Context zzhQ()
    {
      return this.zzOg;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzjt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */