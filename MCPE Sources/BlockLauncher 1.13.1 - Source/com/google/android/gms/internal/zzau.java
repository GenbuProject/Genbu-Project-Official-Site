package com.google.android.gms.internal;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.zzh;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzr;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzhb
public abstract class zzau
  implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener
{
  protected final Object zzpV = new Object();
  private boolean zzqJ = false;
  private zziz zzrQ;
  private final WeakReference<zzif> zzrW;
  private WeakReference<ViewTreeObserver> zzrX;
  private final zzbb zzrY;
  protected final zzaw zzrZ;
  private final Context zzsa;
  private final WindowManager zzsb;
  private final PowerManager zzsc;
  private final KeyguardManager zzsd;
  private zzay zzse;
  private boolean zzsf;
  private boolean zzsg = false;
  private boolean zzsh;
  private boolean zzsi;
  private boolean zzsj;
  BroadcastReceiver zzsk;
  private final HashSet<zzav> zzsl = new HashSet();
  private final zzdf zzsm = new zzdf()
  {
    public void zza(zzjp paramAnonymouszzjp, Map<String, String> paramAnonymousMap)
    {
      if (!zzau.this.zzb(paramAnonymousMap)) {
        return;
      }
      zzau.this.zza(paramAnonymouszzjp.getView(), paramAnonymousMap);
    }
  };
  private final zzdf zzsn = new zzdf()
  {
    public void zza(zzjp paramAnonymouszzjp, Map<String, String> paramAnonymousMap)
    {
      if (!zzau.this.zzb(paramAnonymousMap)) {
        return;
      }
      zzin.zzaI("Received request to untrack: " + zzau.this.zzrZ.zzcu());
      zzau.this.destroy();
    }
  };
  private final zzdf zzso = new zzdf()
  {
    public void zza(zzjp paramAnonymouszzjp, Map<String, String> paramAnonymousMap)
    {
      if (!zzau.this.zzb(paramAnonymousMap)) {}
      while (!paramAnonymousMap.containsKey("isVisible")) {
        return;
      }
      if (("1".equals(paramAnonymousMap.get("isVisible"))) || ("true".equals(paramAnonymousMap.get("isVisible")))) {}
      for (boolean bool = true;; bool = false)
      {
        zzau.this.zzg(Boolean.valueOf(bool).booleanValue());
        return;
      }
    }
  };
  
  public zzau(Context paramContext, AdSizeParcel paramAdSizeParcel, zzif paramzzif, VersionInfoParcel paramVersionInfoParcel, zzbb paramzzbb)
  {
    this.zzrW = new WeakReference(paramzzif);
    this.zzrY = paramzzbb;
    this.zzrX = new WeakReference(null);
    this.zzsh = true;
    this.zzsj = false;
    this.zzrQ = new zziz(200L);
    this.zzrZ = new zzaw(UUID.randomUUID().toString(), paramVersionInfoParcel, paramAdSizeParcel.zzuh, paramzzif.zzKT, paramzzif.zzcv(), paramAdSizeParcel.zzuk);
    this.zzsb = ((WindowManager)paramContext.getSystemService("window"));
    this.zzsc = ((PowerManager)paramContext.getApplicationContext().getSystemService("power"));
    this.zzsd = ((KeyguardManager)paramContext.getSystemService("keyguard"));
    this.zzsa = paramContext;
  }
  
  protected void destroy()
  {
    synchronized (this.zzpV)
    {
      zzcj();
      zzce();
      this.zzsh = false;
      zzcg();
      return;
    }
  }
  
  boolean isScreenOn()
  {
    return this.zzsc.isScreenOn();
  }
  
  public void onGlobalLayout()
  {
    zzh(false);
  }
  
  public void onScrollChanged()
  {
    zzh(true);
  }
  
  public void pause()
  {
    synchronized (this.zzpV)
    {
      this.zzqJ = true;
      zzh(false);
      return;
    }
  }
  
  public void resume()
  {
    synchronized (this.zzpV)
    {
      this.zzqJ = false;
      zzh(false);
      return;
    }
  }
  
  public void stop()
  {
    synchronized (this.zzpV)
    {
      this.zzsg = true;
      zzh(false);
      return;
    }
  }
  
  protected int zza(int paramInt, DisplayMetrics paramDisplayMetrics)
  {
    float f = paramDisplayMetrics.density;
    return (int)(paramInt / f);
  }
  
  protected void zza(View paramView, Map<String, String> paramMap)
  {
    zzh(false);
  }
  
  public void zza(zzav paramzzav)
  {
    this.zzsl.add(paramzzav);
  }
  
  public void zza(zzay paramzzay)
  {
    synchronized (this.zzpV)
    {
      this.zzse = paramzzay;
      return;
    }
  }
  
  protected void zza(JSONObject paramJSONObject)
  {
    try
    {
      JSONArray localJSONArray = new JSONArray();
      JSONObject localJSONObject = new JSONObject();
      localJSONArray.put(paramJSONObject);
      localJSONObject.put("units", localJSONArray);
      zzb(localJSONObject);
      return;
    }
    catch (Throwable paramJSONObject)
    {
      zzin.zzb("Skipping active view message.", paramJSONObject);
    }
  }
  
  protected void zzb(zzeh paramzzeh)
  {
    paramzzeh.zza("/updateActiveView", this.zzsm);
    paramzzeh.zza("/untrackActiveViewUnit", this.zzsn);
    paramzzeh.zza("/visibilityChanged", this.zzso);
  }
  
  protected abstract void zzb(JSONObject paramJSONObject);
  
  protected boolean zzb(Map<String, String> paramMap)
  {
    if (paramMap == null) {
      return false;
    }
    paramMap = (String)paramMap.get("hashCode");
    if ((!TextUtils.isEmpty(paramMap)) && (paramMap.equals(this.zzrZ.zzcu()))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected void zzc(zzeh paramzzeh)
  {
    paramzzeh.zzb("/visibilityChanged", this.zzso);
    paramzzeh.zzb("/untrackActiveViewUnit", this.zzsn);
    paramzzeh.zzb("/updateActiveView", this.zzsm);
  }
  
  protected void zzcd()
  {
    synchronized (this.zzpV)
    {
      if (this.zzsk != null) {
        return;
      }
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.SCREEN_ON");
      localIntentFilter.addAction("android.intent.action.SCREEN_OFF");
      this.zzsk = new BroadcastReceiver()
      {
        public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
        {
          zzau.this.zzh(false);
        }
      };
      this.zzsa.registerReceiver(this.zzsk, localIntentFilter);
      return;
    }
  }
  
  protected void zzce()
  {
    synchronized (this.zzpV)
    {
      BroadcastReceiver localBroadcastReceiver = this.zzsk;
      if (localBroadcastReceiver == null) {}
    }
    try
    {
      this.zzsa.unregisterReceiver(this.zzsk);
      this.zzsk = null;
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;)
      {
        zzin.zzb("Failed trying to unregister the receiver", localIllegalStateException);
      }
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
    catch (Exception localException)
    {
      for (;;)
      {
        zzr.zzbF().zzb(localException, true);
      }
    }
  }
  
  public void zzcf()
  {
    synchronized (this.zzpV)
    {
      if (this.zzsh) {
        this.zzsi = true;
      }
    }
    try
    {
      zza(zzcn());
      zzin.zzaI("Untracking ad unit: " + this.zzrZ.zzcu());
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        zzin.zzb("JSON failure while processing active view data.", localJSONException);
      }
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
    catch (RuntimeException localRuntimeException)
    {
      for (;;)
      {
        zzin.zzb("Failure while processing active view data.", localRuntimeException);
      }
    }
  }
  
  protected void zzcg()
  {
    if (this.zzse != null) {
      this.zzse.zza(this);
    }
  }
  
  public boolean zzch()
  {
    synchronized (this.zzpV)
    {
      boolean bool = this.zzsh;
      return bool;
    }
  }
  
  protected void zzci()
  {
    Object localObject = this.zzrY.zzcq().zzco();
    if (localObject == null) {}
    ViewTreeObserver localViewTreeObserver;
    do
    {
      return;
      localViewTreeObserver = (ViewTreeObserver)this.zzrX.get();
      localObject = ((View)localObject).getViewTreeObserver();
    } while (localObject == localViewTreeObserver);
    zzcj();
    if ((!this.zzsf) || ((localViewTreeObserver != null) && (localViewTreeObserver.isAlive())))
    {
      this.zzsf = true;
      ((ViewTreeObserver)localObject).addOnScrollChangedListener(this);
      ((ViewTreeObserver)localObject).addOnGlobalLayoutListener(this);
    }
    this.zzrX = new WeakReference(localObject);
  }
  
  protected void zzcj()
  {
    ViewTreeObserver localViewTreeObserver = (ViewTreeObserver)this.zzrX.get();
    if ((localViewTreeObserver == null) || (!localViewTreeObserver.isAlive())) {
      return;
    }
    localViewTreeObserver.removeOnScrollChangedListener(this);
    localViewTreeObserver.removeGlobalOnLayoutListener(this);
  }
  
  protected JSONObject zzck()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("afmaVersion", this.zzrZ.zzcs()).put("activeViewJSON", this.zzrZ.zzct()).put("timestamp", zzr.zzbG().elapsedRealtime()).put("adFormat", this.zzrZ.zzcr()).put("hashCode", this.zzrZ.zzcu()).put("isMraid", this.zzrZ.zzcv()).put("isStopped", this.zzsg).put("isPaused", this.zzqJ).put("isScreenOn", isScreenOn()).put("isNative", this.zzrZ.zzcw());
    return localJSONObject;
  }
  
  protected abstract boolean zzcl();
  
  protected JSONObject zzcm()
    throws JSONException
  {
    return zzck().put("isAttachedToWindow", false).put("isScreenOn", isScreenOn()).put("isVisible", false);
  }
  
  protected JSONObject zzcn()
    throws JSONException
  {
    JSONObject localJSONObject = zzck();
    localJSONObject.put("doneReasonCode", "u");
    return localJSONObject;
  }
  
  protected JSONObject zzd(View paramView)
    throws JSONException
  {
    if (paramView == null) {
      return zzcm();
    }
    boolean bool1 = zzr.zzbE().isAttachedToWindow(paramView);
    Object localObject2 = new int[2];
    Object localObject1 = new int[2];
    try
    {
      paramView.getLocationOnScreen((int[])localObject2);
      paramView.getLocationInWindow((int[])localObject1);
      localObject1 = paramView.getContext().getResources().getDisplayMetrics();
      Rect localRect1 = new Rect();
      localRect1.left = localObject2[0];
      localRect1.top = localObject2[1];
      localRect1.right = (localRect1.left + paramView.getWidth());
      localRect1.bottom = (localRect1.top + paramView.getHeight());
      localObject2 = new Rect();
      ((Rect)localObject2).right = this.zzsb.getDefaultDisplay().getWidth();
      ((Rect)localObject2).bottom = this.zzsb.getDefaultDisplay().getHeight();
      Rect localRect2 = new Rect();
      boolean bool2 = paramView.getGlobalVisibleRect(localRect2, null);
      Rect localRect3 = new Rect();
      boolean bool3 = paramView.getLocalVisibleRect(localRect3);
      Rect localRect4 = new Rect();
      paramView.getHitRect(localRect4);
      JSONObject localJSONObject = zzck();
      localJSONObject.put("windowVisibility", paramView.getWindowVisibility()).put("isAttachedToWindow", bool1).put("viewBox", new JSONObject().put("top", zza(((Rect)localObject2).top, (DisplayMetrics)localObject1)).put("bottom", zza(((Rect)localObject2).bottom, (DisplayMetrics)localObject1)).put("left", zza(((Rect)localObject2).left, (DisplayMetrics)localObject1)).put("right", zza(((Rect)localObject2).right, (DisplayMetrics)localObject1))).put("adBox", new JSONObject().put("top", zza(localRect1.top, (DisplayMetrics)localObject1)).put("bottom", zza(localRect1.bottom, (DisplayMetrics)localObject1)).put("left", zza(localRect1.left, (DisplayMetrics)localObject1)).put("right", zza(localRect1.right, (DisplayMetrics)localObject1))).put("globalVisibleBox", new JSONObject().put("top", zza(localRect2.top, (DisplayMetrics)localObject1)).put("bottom", zza(localRect2.bottom, (DisplayMetrics)localObject1)).put("left", zza(localRect2.left, (DisplayMetrics)localObject1)).put("right", zza(localRect2.right, (DisplayMetrics)localObject1))).put("globalVisibleBoxVisible", bool2).put("localVisibleBox", new JSONObject().put("top", zza(localRect3.top, (DisplayMetrics)localObject1)).put("bottom", zza(localRect3.bottom, (DisplayMetrics)localObject1)).put("left", zza(localRect3.left, (DisplayMetrics)localObject1)).put("right", zza(localRect3.right, (DisplayMetrics)localObject1))).put("localVisibleBoxVisible", bool3).put("hitBox", new JSONObject().put("top", zza(localRect4.top, (DisplayMetrics)localObject1)).put("bottom", zza(localRect4.bottom, (DisplayMetrics)localObject1)).put("left", zza(localRect4.left, (DisplayMetrics)localObject1)).put("right", zza(localRect4.right, (DisplayMetrics)localObject1))).put("screenDensity", ((DisplayMetrics)localObject1).density).put("isVisible", zzr.zzbC().zza(paramView, this.zzsc, this.zzsd));
      return localJSONObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        zzin.zzb("Failure getting view location.", localException);
      }
    }
  }
  
  protected void zzg(boolean paramBoolean)
  {
    Iterator localIterator = this.zzsl.iterator();
    while (localIterator.hasNext()) {
      ((zzav)localIterator.next()).zza(this, paramBoolean);
    }
  }
  
  protected void zzh(boolean paramBoolean)
  {
    boolean bool;
    for (;;)
    {
      synchronized (this.zzpV)
      {
        if ((!zzcl()) || (!this.zzsh)) {
          return;
        }
        View localView1 = this.zzrY.zzco();
        if ((localView1 != null) && (zzr.zzbC().zza(localView1, this.zzsc, this.zzsd)) && (localView1.getGlobalVisibleRect(new Rect(), null)))
        {
          bool = true;
          if ((!paramBoolean) || (this.zzrQ.tryAcquire()) || (bool != this.zzsj)) {
            break;
          }
          return;
        }
      }
      bool = false;
    }
    this.zzsj = bool;
    if (this.zzrY.zzcp())
    {
      zzcf();
      return;
    }
    for (;;)
    {
      try
      {
        zza(zzd(localView2));
        zzci();
        zzcg();
        return;
      }
      catch (RuntimeException localRuntimeException)
      {
        continue;
      }
      catch (JSONException localJSONException)
      {
        continue;
      }
      zzin.zza("Active view update failed.", localView2);
    }
  }
  
  public static class zza
    implements zzbb
  {
    private WeakReference<zzh> zzsq;
    
    public zza(zzh paramzzh)
    {
      this.zzsq = new WeakReference(paramzzh);
    }
    
    public View zzco()
    {
      zzh localzzh = (zzh)this.zzsq.get();
      if (localzzh != null) {
        return localzzh.zzdS();
      }
      return null;
    }
    
    public boolean zzcp()
    {
      return this.zzsq.get() == null;
    }
    
    public zzbb zzcq()
    {
      return new zzau.zzb((zzh)this.zzsq.get());
    }
  }
  
  public static class zzb
    implements zzbb
  {
    private zzh zzsr;
    
    public zzb(zzh paramzzh)
    {
      this.zzsr = paramzzh;
    }
    
    public View zzco()
    {
      return this.zzsr.zzdS();
    }
    
    public boolean zzcp()
    {
      return this.zzsr == null;
    }
    
    public zzbb zzcq()
    {
      return this;
    }
  }
  
  public static class zzc
    implements zzbb
  {
    private final View mView;
    private final zzif zzss;
    
    public zzc(View paramView, zzif paramzzif)
    {
      this.mView = paramView;
      this.zzss = paramzzif;
    }
    
    public View zzco()
    {
      return this.mView;
    }
    
    public boolean zzcp()
    {
      return (this.zzss == null) || (this.mView == null);
    }
    
    public zzbb zzcq()
    {
      return this;
    }
  }
  
  public static class zzd
    implements zzbb
  {
    private final WeakReference<View> zzst;
    private final WeakReference<zzif> zzsu;
    
    public zzd(View paramView, zzif paramzzif)
    {
      this.zzst = new WeakReference(paramView);
      this.zzsu = new WeakReference(paramzzif);
    }
    
    public View zzco()
    {
      return (View)this.zzst.get();
    }
    
    public boolean zzcp()
    {
      return (this.zzst.get() == null) || (this.zzsu.get() == null);
    }
    
    public zzbb zzcq()
    {
      return new zzau.zzc((View)this.zzst.get(), (zzif)this.zzsu.get());
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzau.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */