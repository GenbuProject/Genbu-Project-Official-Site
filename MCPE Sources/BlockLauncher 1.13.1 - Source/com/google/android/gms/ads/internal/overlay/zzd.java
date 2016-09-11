package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.gms.ads.internal.InterstitialAdParameterParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.internal.zzbp;
import com.google.android.gms.internal.zzbt;
import com.google.android.gms.internal.zzfv.zza;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzim;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zzis;
import com.google.android.gms.internal.zziu;
import com.google.android.gms.internal.zzjp;
import com.google.android.gms.internal.zzjq;
import com.google.android.gms.internal.zzjq.zza;
import com.google.android.gms.internal.zzjr;
import java.util.Collections;
import java.util.Map;

@zzhb
public class zzd
  extends zzfv.zza
  implements zzs
{
  static final int zzEg = Color.argb(0, 0, 0, 0);
  private final Activity mActivity;
  RelativeLayout zzDm;
  AdOverlayInfoParcel zzEh;
  zzc zzEi;
  zzo zzEj;
  boolean zzEk = false;
  FrameLayout zzEl;
  WebChromeClient.CustomViewCallback zzEm;
  boolean zzEn = false;
  boolean zzEo = false;
  boolean zzEp = false;
  int zzEq = 0;
  zzl zzEr;
  private boolean zzEs;
  private boolean zzEt = false;
  private boolean zzEu = true;
  zzjp zzpD;
  
  public zzd(Activity paramActivity)
  {
    this.mActivity = paramActivity;
    this.zzEr = new zzq();
  }
  
  public void close()
  {
    this.zzEq = 2;
    this.mActivity.finish();
  }
  
  public void onBackPressed()
  {
    this.zzEq = 0;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    boolean bool = false;
    if (paramBundle != null) {
      bool = paramBundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
    }
    this.zzEn = bool;
    try
    {
      this.zzEh = AdOverlayInfoParcel.zzb(this.mActivity.getIntent());
      if (this.zzEh != null) {
        break label71;
      }
      throw new zza("Could not get info for ad overlay.");
    }
    catch (zza paramBundle)
    {
      zzin.zzaK(paramBundle.getMessage());
      this.zzEq = 3;
      this.mActivity.finish();
    }
    return;
    label71:
    if (this.zzEh.zzrl.zzNa > 7500000) {
      this.zzEq = 3;
    }
    if (this.mActivity.getIntent() != null) {
      this.zzEu = this.mActivity.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
    }
    if (this.zzEh.zzEM != null)
    {
      this.zzEo = this.zzEh.zzEM.zzql;
      label142:
      if ((((Boolean)zzbt.zzxe.get()).booleanValue()) && (this.zzEo) && (this.zzEh.zzEM.zzqn != null)) {
        new zzd(null).zzhn();
      }
      if (paramBundle == null)
      {
        if ((this.zzEh.zzEC != null) && (this.zzEu)) {
          this.zzEh.zzEC.zzaX();
        }
        if ((this.zzEh.zzEJ != 1) && (this.zzEh.zzEB != null)) {
          this.zzEh.zzEB.onAdClicked();
        }
      }
      this.zzDm = new zzb(this.mActivity, this.zzEh.zzEL);
      this.zzDm.setId(1000);
      switch (this.zzEh.zzEJ)
      {
      }
    }
    for (;;)
    {
      throw new zza("Could not determine ad overlay type.");
      this.zzEo = false;
      break label142;
      zzx(false);
      return;
      this.zzEi = new zzc(this.zzEh.zzED);
      zzx(false);
      return;
      zzx(true);
      return;
      if (this.zzEn)
      {
        this.zzEq = 3;
        this.mActivity.finish();
        return;
      }
      if (zzr.zzbz().zza(this.mActivity, this.zzEh.zzEA, this.zzEh.zzEI)) {
        break;
      }
      this.zzEq = 3;
      this.mActivity.finish();
      return;
    }
  }
  
  public void onDestroy()
  {
    if (this.zzpD != null) {
      this.zzDm.removeView(this.zzpD.getView());
    }
    zzfp();
  }
  
  public void onPause()
  {
    this.zzEr.pause();
    zzfl();
    if (this.zzEh.zzEC != null) {
      this.zzEh.zzEC.onPause();
    }
    if ((this.zzpD != null) && ((!this.mActivity.isFinishing()) || (this.zzEi == null))) {
      zzr.zzbE().zzi(this.zzpD);
    }
    zzfp();
  }
  
  public void onRestart() {}
  
  public void onResume()
  {
    if ((this.zzEh != null) && (this.zzEh.zzEJ == 4))
    {
      if (this.zzEn)
      {
        this.zzEq = 3;
        this.mActivity.finish();
      }
    }
    else
    {
      if (this.zzEh.zzEC != null) {
        this.zzEh.zzEC.onResume();
      }
      if ((this.zzpD == null) || (this.zzpD.isDestroyed())) {
        break label107;
      }
      zzr.zzbE().zzj(this.zzpD);
    }
    for (;;)
    {
      this.zzEr.resume();
      return;
      this.zzEn = true;
      break;
      label107:
      zzin.zzaK("The webview does not exit. Ignoring action.");
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzEn);
  }
  
  public void onStart() {}
  
  public void onStop()
  {
    zzfp();
  }
  
  public void setRequestedOrientation(int paramInt)
  {
    this.mActivity.setRequestedOrientation(paramInt);
  }
  
  public void zza(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
  {
    this.zzEl = new FrameLayout(this.mActivity);
    this.zzEl.setBackgroundColor(-16777216);
    this.zzEl.addView(paramView, -1, -1);
    this.mActivity.setContentView(this.zzEl);
    zzaD();
    this.zzEm = paramCustomViewCallback;
    this.zzEk = true;
  }
  
  public void zza(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (this.zzEj != null) {
      this.zzEj.zza(paramBoolean1, paramBoolean2);
    }
  }
  
  public void zzaD()
  {
    this.zzEs = true;
  }
  
  public void zzfl()
  {
    if ((this.zzEh != null) && (this.zzEk)) {
      setRequestedOrientation(this.zzEh.orientation);
    }
    if (this.zzEl != null)
    {
      this.mActivity.setContentView(this.zzDm);
      zzaD();
      this.zzEl.removeAllViews();
      this.zzEl = null;
    }
    if (this.zzEm != null)
    {
      this.zzEm.onCustomViewHidden();
      this.zzEm = null;
    }
    this.zzEk = false;
  }
  
  public void zzfm()
  {
    this.zzEq = 1;
    this.mActivity.finish();
  }
  
  public boolean zzfn()
  {
    boolean bool1 = true;
    boolean bool2 = true;
    this.zzEq = 0;
    if (this.zzpD == null) {
      return bool2;
    }
    if ((this.zzpD.zzfL()) && (this.zzEr.zzfL())) {}
    for (;;)
    {
      bool2 = bool1;
      if (bool1) {
        break;
      }
      this.zzpD.zza("onbackblocked", Collections.emptyMap());
      return bool1;
      bool1 = false;
    }
  }
  
  public void zzfo()
  {
    this.zzDm.removeView(this.zzEj);
    zzw(true);
  }
  
  protected void zzfp()
  {
    if ((!this.mActivity.isFinishing()) || (this.zzEt)) {
      return;
    }
    this.zzEt = true;
    if (this.zzpD != null)
    {
      zzy(this.zzEq);
      this.zzDm.removeView(this.zzpD.getView());
      if (this.zzEi == null) {
        break label169;
      }
      this.zzpD.setContext(this.zzEi.context);
      this.zzpD.zzD(false);
      this.zzEi.zzEx.addView(this.zzpD.getView(), this.zzEi.index, this.zzEi.zzEw);
      this.zzEi = null;
    }
    for (;;)
    {
      this.zzpD = null;
      if ((this.zzEh != null) && (this.zzEh.zzEC != null)) {
        this.zzEh.zzEC.zzaW();
      }
      this.zzEr.destroy();
      return;
      label169:
      if (this.mActivity.getApplicationContext() != null) {
        this.zzpD.setContext(this.mActivity.getApplicationContext());
      }
    }
  }
  
  public void zzfq()
  {
    if (this.zzEp)
    {
      this.zzEp = false;
      zzfr();
    }
  }
  
  protected void zzfr()
  {
    this.zzpD.zzfr();
  }
  
  public void zzg(zzjp paramzzjp, Map<String, String> paramMap)
  {
    this.zzEr.zzg(paramzzjp, paramMap);
  }
  
  public void zzw(boolean paramBoolean)
  {
    RelativeLayout.LayoutParams localLayoutParams;
    if (paramBoolean)
    {
      i = 50;
      this.zzEj = new zzo(this.mActivity, i, this);
      localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams.addRule(10);
      if (!paramBoolean) {
        break label88;
      }
    }
    label88:
    for (int i = 11;; i = 9)
    {
      localLayoutParams.addRule(i);
      this.zzEj.zza(paramBoolean, this.zzEh.zzEG);
      this.zzDm.addView(this.zzEj, localLayoutParams);
      return;
      i = 32;
      break;
    }
  }
  
  protected void zzx(boolean paramBoolean)
    throws zzd.zza
  {
    if (!this.zzEs) {
      this.mActivity.requestWindowFeature(1);
    }
    Object localObject = this.mActivity.getWindow();
    if (localObject == null) {
      throw new zza("Invalid activity, no window available.");
    }
    if ((!this.zzEo) || ((this.zzEh.zzEM != null) && (this.zzEh.zzEM.zzqm))) {
      ((Window)localObject).setFlags(1024, 1024);
    }
    boolean bool2 = this.zzEh.zzED.zzhU().zzcv();
    this.zzEp = false;
    boolean bool1;
    if (bool2)
    {
      if (this.zzEh.orientation != zzr.zzbE().zzhv()) {
        break label591;
      }
      if (this.mActivity.getResources().getConfiguration().orientation == 1)
      {
        bool1 = true;
        this.zzEp = bool1;
      }
    }
    else
    {
      label147:
      zzin.zzaI("Delay onShow to next orientation change: " + this.zzEp);
      setRequestedOrientation(this.zzEh.orientation);
      if (zzr.zzbE().zza((Window)localObject)) {
        zzin.zzaI("Hardware acceleration on the AdActivity window enabled.");
      }
      if (this.zzEo) {
        break label639;
      }
      this.zzDm.setBackgroundColor(-16777216);
      label218:
      this.mActivity.setContentView(this.zzDm);
      zzaD();
      if (!paramBoolean) {
        break label706;
      }
      this.zzpD = zzr.zzbD().zza(this.mActivity, this.zzEh.zzED.zzaN(), true, bool2, null, this.zzEh.zzrl, null, this.zzEh.zzED.zzhR());
      this.zzpD.zzhU().zzb(null, null, this.zzEh.zzEE, this.zzEh.zzEI, true, this.zzEh.zzEK, null, this.zzEh.zzED.zzhU().zzig(), null);
      this.zzpD.zzhU().zza(new zzjq.zza()
      {
        public void zza(zzjp paramAnonymouszzjp, boolean paramAnonymousBoolean)
        {
          paramAnonymouszzjp.zzfr();
        }
      });
      if (this.zzEh.url == null) {
        break label652;
      }
      this.zzpD.loadUrl(this.zzEh.url);
      label385:
      if (this.zzEh.zzED != null) {
        this.zzEh.zzED.zzc(this);
      }
      label408:
      this.zzpD.zzb(this);
      localObject = this.zzpD.getParent();
      if ((localObject != null) && ((localObject instanceof ViewGroup))) {
        ((ViewGroup)localObject).removeView(this.zzpD.getView());
      }
      if (this.zzEo) {
        this.zzpD.setBackgroundColor(zzEg);
      }
      this.zzDm.addView(this.zzpD.getView(), -1, -1);
      if ((!paramBoolean) && (!this.zzEp)) {
        zzfr();
      }
      zzw(bool2);
      if (this.zzpD.zzhV()) {
        zza(bool2, true);
      }
      localObject = this.zzpD.zzhR();
      if (localObject == null) {
        break label733;
      }
    }
    label591:
    label639:
    label652:
    label706:
    label733:
    for (localObject = ((com.google.android.gms.ads.internal.zzd)localObject).zzpy;; localObject = null)
    {
      if (localObject == null) {
        break label739;
      }
      this.zzEr = ((zzm)localObject).zza(this.mActivity, this.zzpD, this.zzDm);
      return;
      bool1 = false;
      break;
      if (this.zzEh.orientation != zzr.zzbE().zzhw()) {
        break label147;
      }
      if (this.mActivity.getResources().getConfiguration().orientation == 2) {}
      for (bool1 = true;; bool1 = false)
      {
        this.zzEp = bool1;
        break;
      }
      this.zzDm.setBackgroundColor(zzEg);
      break label218;
      if (this.zzEh.zzEH != null)
      {
        this.zzpD.loadDataWithBaseURL(this.zzEh.zzEF, this.zzEh.zzEH, "text/html", "UTF-8", null);
        break label385;
      }
      throw new zza("No URL or HTML to display in ad overlay.");
      this.zzpD = this.zzEh.zzED;
      this.zzpD.setContext(this.mActivity);
      break label408;
    }
    label739:
    zzin.zzaK("Appstreaming controller is null.");
  }
  
  protected void zzy(int paramInt)
  {
    this.zzpD.zzy(paramInt);
  }
  
  @zzhb
  private static final class zza
    extends Exception
  {
    public zza(String paramString)
    {
      super();
    }
  }
  
  @zzhb
  static final class zzb
    extends RelativeLayout
  {
    zziu zzrU;
    
    public zzb(Context paramContext, String paramString)
    {
      super();
      this.zzrU = new zziu(paramContext, paramString);
    }
    
    public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
    {
      this.zzrU.zze(paramMotionEvent);
      return false;
    }
  }
  
  @zzhb
  public static class zzc
  {
    public final Context context;
    public final int index;
    public final ViewGroup.LayoutParams zzEw;
    public final ViewGroup zzEx;
    
    public zzc(zzjp paramzzjp)
      throws zzd.zza
    {
      this.zzEw = paramzzjp.getLayoutParams();
      ViewParent localViewParent = paramzzjp.getParent();
      this.context = paramzzjp.zzhQ();
      if ((localViewParent != null) && ((localViewParent instanceof ViewGroup)))
      {
        this.zzEx = ((ViewGroup)localViewParent);
        this.index = this.zzEx.indexOfChild(paramzzjp.getView());
        this.zzEx.removeView(paramzzjp.getView());
        paramzzjp.zzD(true);
        return;
      }
      throw new zzd.zza("Could not get the parent of the WebView for an overlay.");
    }
  }
  
  @zzhb
  private class zzd
    extends zzim
  {
    private zzd() {}
    
    public void onStop() {}
    
    public void zzbr()
    {
      final Object localObject = zzr.zzbC().zzf(zzd.zza(zzd.this), zzd.this.zzEh.zzEM.zzqn);
      if (localObject != null)
      {
        localObject = zzr.zzbE().zza(zzd.zza(zzd.this), (Bitmap)localObject, zzd.this.zzEh.zzEM.zzqo, zzd.this.zzEh.zzEM.zzqp);
        zzir.zzMc.post(new Runnable()
        {
          public void run()
          {
            zzd.zza(zzd.this).getWindow().setBackgroundDrawable(localObject);
          }
        });
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\overlay\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */