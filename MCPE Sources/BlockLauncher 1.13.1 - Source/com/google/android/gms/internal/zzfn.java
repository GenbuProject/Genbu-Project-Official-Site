package com.google.android.gms.internal;

import android.app.Activity;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.zzr;
import java.util.Map;
import java.util.Set;

@zzhb
public class zzfn
  extends zzfs
{
  static final Set<String> zzDa = zzmr.zzc(new String[] { "top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center" });
  private AdSizeParcel zzCh;
  private String zzDb = "top-right";
  private boolean zzDc = true;
  private int zzDd = 0;
  private int zzDe = 0;
  private int zzDf = 0;
  private int zzDg = 0;
  private final Activity zzDh;
  private ImageView zzDi;
  private LinearLayout zzDj;
  private zzft zzDk;
  private PopupWindow zzDl;
  private RelativeLayout zzDm;
  private ViewGroup zzDn;
  private int zzoG = -1;
  private int zzoH = -1;
  private final zzjp zzpD;
  private final Object zzpV = new Object();
  
  public zzfn(zzjp paramzzjp, zzft paramzzft)
  {
    super(paramzzjp, "resize");
    this.zzpD = paramzzjp;
    this.zzDh = paramzzjp.zzhP();
    this.zzDk = paramzzft;
  }
  
  private int[] zzeM()
  {
    if (!zzeO()) {
      return null;
    }
    if (this.zzDc) {
      return new int[] { this.zzDd + this.zzDf, this.zzDe + this.zzDg };
    }
    int[] arrayOfInt1 = zzr.zzbC().zzf(this.zzDh);
    int[] arrayOfInt2 = zzr.zzbC().zzh(this.zzDh);
    int m = arrayOfInt1[0];
    int j = this.zzDd + this.zzDf;
    int k = this.zzDe + this.zzDg;
    int i;
    if (j < 0)
    {
      i = 0;
      if (k >= arrayOfInt2[0]) {
        break label149;
      }
      j = arrayOfInt2[0];
    }
    for (;;)
    {
      return new int[] { i, j };
      i = j;
      if (this.zzoG + j <= m) {
        break;
      }
      i = m - this.zzoG;
      break;
      label149:
      j = k;
      if (this.zzoH + k > arrayOfInt2[1]) {
        j = arrayOfInt2[1] - this.zzoH;
      }
    }
  }
  
  private void zzh(Map<String, String> paramMap)
  {
    if (!TextUtils.isEmpty((CharSequence)paramMap.get("width"))) {
      this.zzoG = zzr.zzbC().zzaD((String)paramMap.get("width"));
    }
    if (!TextUtils.isEmpty((CharSequence)paramMap.get("height"))) {
      this.zzoH = zzr.zzbC().zzaD((String)paramMap.get("height"));
    }
    if (!TextUtils.isEmpty((CharSequence)paramMap.get("offsetX"))) {
      this.zzDf = zzr.zzbC().zzaD((String)paramMap.get("offsetX"));
    }
    if (!TextUtils.isEmpty((CharSequence)paramMap.get("offsetY"))) {
      this.zzDg = zzr.zzbC().zzaD((String)paramMap.get("offsetY"));
    }
    if (!TextUtils.isEmpty((CharSequence)paramMap.get("allowOffscreen"))) {
      this.zzDc = Boolean.parseBoolean((String)paramMap.get("allowOffscreen"));
    }
    paramMap = (String)paramMap.get("customClosePosition");
    if (!TextUtils.isEmpty(paramMap)) {
      this.zzDb = paramMap;
    }
  }
  
  public void zza(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    synchronized (this.zzpV)
    {
      this.zzDd = paramInt1;
      this.zzDe = paramInt2;
      if ((this.zzDl != null) && (paramBoolean))
      {
        int[] arrayOfInt = zzeM();
        if (arrayOfInt != null)
        {
          this.zzDl.update(zzn.zzcS().zzb(this.zzDh, arrayOfInt[0]), zzn.zzcS().zzb(this.zzDh, arrayOfInt[1]), this.zzDl.getWidth(), this.zzDl.getHeight());
          zzd(arrayOfInt[0], arrayOfInt[1]);
        }
      }
      else
      {
        return;
      }
      zzp(true);
    }
  }
  
  void zzc(int paramInt1, int paramInt2)
  {
    if (this.zzDk != null) {
      this.zzDk.zza(paramInt1, paramInt2, this.zzoG, this.zzoH);
    }
  }
  
  void zzd(int paramInt1, int paramInt2)
  {
    zzb(paramInt1, paramInt2 - zzr.zzbC().zzh(this.zzDh)[0], this.zzoG, this.zzoH);
  }
  
  public void zze(int paramInt1, int paramInt2)
  {
    this.zzDd = paramInt1;
    this.zzDe = paramInt2;
  }
  
  boolean zzeL()
  {
    return (this.zzoG > -1) && (this.zzoH > -1);
  }
  
  public boolean zzeN()
  {
    for (;;)
    {
      synchronized (this.zzpV)
      {
        if (this.zzDl != null)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  boolean zzeO()
  {
    Object localObject = zzr.zzbC().zzf(this.zzDh);
    int[] arrayOfInt = zzr.zzbC().zzh(this.zzDh);
    int k = localObject[0];
    int i = localObject[1];
    if ((this.zzoG < 50) || (this.zzoG > k))
    {
      zzin.zzaK("Width is too small or too large.");
      return false;
    }
    if ((this.zzoH < 50) || (this.zzoH > i))
    {
      zzin.zzaK("Height is too small or too large.");
      return false;
    }
    if ((this.zzoH == i) && (this.zzoG == k))
    {
      zzin.zzaK("Cannot resize to a full-screen ad.");
      return false;
    }
    label184:
    int j;
    if (this.zzDc)
    {
      localObject = this.zzDb;
      i = -1;
      switch (((String)localObject).hashCode())
      {
      default: 
        switch (i)
        {
        default: 
          j = this.zzDd + this.zzDf + this.zzoG - 50;
          i = this.zzDe + this.zzDg;
        }
        break;
      }
    }
    while ((j >= 0) && (j + 50 <= k) && (i >= arrayOfInt[0]) && (i + 50 <= arrayOfInt[1]))
    {
      return true;
      if (!((String)localObject).equals("top-left")) {
        break label184;
      }
      i = 0;
      break label184;
      if (!((String)localObject).equals("top-center")) {
        break label184;
      }
      i = 1;
      break label184;
      if (!((String)localObject).equals("center")) {
        break label184;
      }
      i = 2;
      break label184;
      if (!((String)localObject).equals("bottom-left")) {
        break label184;
      }
      i = 3;
      break label184;
      if (!((String)localObject).equals("bottom-center")) {
        break label184;
      }
      i = 4;
      break label184;
      if (!((String)localObject).equals("bottom-right")) {
        break label184;
      }
      i = 5;
      break label184;
      i = this.zzDd;
      j = this.zzDf + i;
      i = this.zzDe + this.zzDg;
      continue;
      j = this.zzDd + this.zzDf + this.zzoG / 2 - 25;
      i = this.zzDe + this.zzDg;
      continue;
      j = this.zzDd + this.zzDf + this.zzoG / 2 - 25;
      i = this.zzDe + this.zzDg + this.zzoH / 2 - 25;
      continue;
      i = this.zzDd;
      j = this.zzDf + i;
      i = this.zzDe + this.zzDg + this.zzoH - 50;
      continue;
      j = this.zzDd + this.zzDf + this.zzoG / 2 - 25;
      i = this.zzDe + this.zzDg + this.zzoH - 50;
      continue;
      j = this.zzDd + this.zzDf + this.zzoG - 50;
      i = this.zzDe + this.zzDg + this.zzoH - 50;
    }
  }
  
  public void zzi(Map<String, String> paramMap)
  {
    synchronized (this.zzpV)
    {
      if (this.zzDh == null)
      {
        zzam("Not an activity context. Cannot resize.");
        return;
      }
      if (this.zzpD.zzaN() == null)
      {
        zzam("Webview is not yet available, size is not set.");
        return;
      }
    }
    if (this.zzpD.zzaN().zzui)
    {
      zzam("Is interstitial. Cannot resize an interstitial.");
      return;
    }
    if (this.zzpD.zzhY())
    {
      zzam("Cannot resize an expanded banner.");
      return;
    }
    zzh(paramMap);
    if (!zzeL())
    {
      zzam("Invalid width and height options. Cannot resize.");
      return;
    }
    paramMap = this.zzDh.getWindow();
    if ((paramMap == null) || (paramMap.getDecorView() == null))
    {
      zzam("Activity context is not ready, cannot get window or decor view.");
      return;
    }
    int[] arrayOfInt = zzeM();
    if (arrayOfInt == null)
    {
      zzam("Resize location out of screen or close button is not visible.");
      return;
    }
    int i = zzn.zzcS().zzb(this.zzDh, this.zzoG);
    int j = zzn.zzcS().zzb(this.zzDh, this.zzoH);
    Object localObject2 = this.zzpD.getView().getParent();
    if ((localObject2 != null) && ((localObject2 instanceof ViewGroup)))
    {
      ((ViewGroup)localObject2).removeView(this.zzpD.getView());
      if (this.zzDl == null)
      {
        this.zzDn = ((ViewGroup)localObject2);
        localObject2 = zzr.zzbC().zzk(this.zzpD.getView());
        this.zzDi = new ImageView(this.zzDh);
        this.zzDi.setImageBitmap((Bitmap)localObject2);
        this.zzCh = this.zzpD.zzaN();
        this.zzDn.addView(this.zzDi);
        this.zzDm = new RelativeLayout(this.zzDh);
        this.zzDm.setBackgroundColor(0);
        this.zzDm.setLayoutParams(new ViewGroup.LayoutParams(i, j));
        this.zzDl = zzr.zzbC().zza(this.zzDm, i, j, false);
        this.zzDl.setOutsideTouchable(true);
        this.zzDl.setTouchable(true);
        localObject2 = this.zzDl;
        if (this.zzDc) {
          break label1071;
        }
      }
    }
    label1023:
    label1027:
    label1029:
    label1071:
    for (boolean bool = true;; bool = false)
    {
      ((PopupWindow)localObject2).setClippingEnabled(bool);
      this.zzDm.addView(this.zzpD.getView(), -1, -1);
      this.zzDj = new LinearLayout(this.zzDh);
      localObject2 = new RelativeLayout.LayoutParams(zzn.zzcS().zzb(this.zzDh, 50), zzn.zzcS().zzb(this.zzDh, 50));
      String str = this.zzDb;
      switch (str.hashCode())
      {
      }
      for (;;)
      {
        ((RelativeLayout.LayoutParams)localObject2).addRule(10);
        ((RelativeLayout.LayoutParams)localObject2).addRule(11);
        for (;;)
        {
          this.zzDj.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              zzfn.this.zzp(true);
            }
          });
          this.zzDj.setContentDescription("Close button");
          this.zzDm.addView(this.zzDj, (ViewGroup.LayoutParams)localObject2);
          try
          {
            this.zzDl.showAtLocation(paramMap.getDecorView(), 0, zzn.zzcS().zzb(this.zzDh, arrayOfInt[0]), zzn.zzcS().zzb(this.zzDh, arrayOfInt[1]));
            zzc(arrayOfInt[0], arrayOfInt[1]);
            this.zzpD.zza(new AdSizeParcel(this.zzDh, new AdSize(this.zzoG, this.zzoH)));
            zzd(arrayOfInt[0], arrayOfInt[1]);
            zzao("resized");
            return;
          }
          catch (RuntimeException paramMap)
          {
            zzam("Cannot show popup window: " + paramMap.getMessage());
            this.zzDm.removeView(this.zzpD.getView());
            if (this.zzDn == null) {
              break label1023;
            }
            this.zzDn.removeView(this.zzDi);
            this.zzDn.addView(this.zzpD.getView());
            this.zzpD.zza(this.zzCh);
            return;
          }
          this.zzDl.dismiss();
          break;
          zzam("Webview is detached, probably in the middle of a resize or expand.");
          return;
          if (!str.equals("top-left")) {
            break label1027;
          }
          i = 0;
          break label1029;
          if (!str.equals("top-center")) {
            break label1027;
          }
          i = 1;
          break label1029;
          if (!str.equals("center")) {
            break label1027;
          }
          i = 2;
          break label1029;
          if (!str.equals("bottom-left")) {
            break label1027;
          }
          i = 3;
          break label1029;
          if (!str.equals("bottom-center")) {
            break label1027;
          }
          i = 4;
          break label1029;
          if (!str.equals("bottom-right")) {
            break label1027;
          }
          i = 5;
          break label1029;
          ((RelativeLayout.LayoutParams)localObject2).addRule(10);
          ((RelativeLayout.LayoutParams)localObject2).addRule(9);
          continue;
          ((RelativeLayout.LayoutParams)localObject2).addRule(10);
          ((RelativeLayout.LayoutParams)localObject2).addRule(14);
          continue;
          ((RelativeLayout.LayoutParams)localObject2).addRule(13);
          continue;
          ((RelativeLayout.LayoutParams)localObject2).addRule(12);
          ((RelativeLayout.LayoutParams)localObject2).addRule(9);
          continue;
          ((RelativeLayout.LayoutParams)localObject2).addRule(12);
          ((RelativeLayout.LayoutParams)localObject2).addRule(14);
          continue;
          ((RelativeLayout.LayoutParams)localObject2).addRule(12);
          ((RelativeLayout.LayoutParams)localObject2).addRule(11);
        }
        i = -1;
        switch (i)
        {
        }
      }
    }
  }
  
  public void zzp(boolean paramBoolean)
  {
    synchronized (this.zzpV)
    {
      if (this.zzDl != null)
      {
        this.zzDl.dismiss();
        this.zzDm.removeView(this.zzpD.getView());
        if (this.zzDn != null)
        {
          this.zzDn.removeView(this.zzDi);
          this.zzDn.addView(this.zzpD.getView());
          this.zzpD.zza(this.zzCh);
        }
        if (paramBoolean)
        {
          zzao("default");
          if (this.zzDk != null) {
            this.zzDk.zzbf();
          }
        }
        this.zzDl = null;
        this.zzDm = null;
        this.zzDn = null;
        this.zzDj = null;
      }
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzfn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */