package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.ViewSwitcher;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.client.zzx;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.purchase.zzk;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.internal.zzan;
import com.google.android.gms.internal.zzbt;
import com.google.android.gms.internal.zzbv;
import com.google.android.gms.internal.zzcf;
import com.google.android.gms.internal.zzcr;
import com.google.android.gms.internal.zzcs;
import com.google.android.gms.internal.zzct;
import com.google.android.gms.internal.zzcu;
import com.google.android.gms.internal.zzey;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzgh;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzif;
import com.google.android.gms.internal.zzif.zza;
import com.google.android.gms.internal.zzig;
import com.google.android.gms.internal.zzih;
import com.google.android.gms.internal.zzik;
import com.google.android.gms.internal.zzim;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzit;
import com.google.android.gms.internal.zziu;
import com.google.android.gms.internal.zziz;
import com.google.android.gms.internal.zzjc;
import com.google.android.gms.internal.zzjp;
import com.google.android.gms.internal.zzjq;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@zzhb
public final class zzs
  implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener
{
  public final Context context;
  boolean zzql = false;
  zzcs zzrA;
  SimpleArrayMap<String, zzct> zzrB;
  SimpleArrayMap<String, zzcu> zzrC;
  NativeAdOptionsParcel zzrD;
  zzcf zzrE;
  @Nullable
  zzd zzrF;
  @Nullable
  private String zzrG;
  List<String> zzrH;
  zzk zzrI;
  public zzik zzrJ = null;
  View zzrK = null;
  public int zzrL = 0;
  boolean zzrM = false;
  private HashSet<zzig> zzrN = null;
  private int zzrO = -1;
  private int zzrP = -1;
  private zziz zzrQ;
  private boolean zzrR = true;
  private boolean zzrS = true;
  private boolean zzrT = false;
  final String zzri;
  public String zzrj;
  final zzan zzrk;
  public final VersionInfoParcel zzrl;
  zza zzrm;
  public zzim zzrn;
  public zzit zzro;
  public AdSizeParcel zzrp;
  public zzif zzrq;
  public zzif.zza zzrr;
  public zzig zzrs;
  zzp zzrt;
  zzq zzru;
  zzw zzrv;
  zzx zzrw;
  zzgd zzrx;
  zzgh zzry;
  zzcr zzrz;
  
  public zzs(Context paramContext, AdSizeParcel paramAdSizeParcel, String paramString, VersionInfoParcel paramVersionInfoParcel)
  {
    this(paramContext, paramAdSizeParcel, paramString, paramVersionInfoParcel, null);
  }
  
  zzs(Context paramContext, AdSizeParcel paramAdSizeParcel, String paramString, VersionInfoParcel paramVersionInfoParcel, zzan paramzzan)
  {
    zzbt.initialize(paramContext);
    if (zzr.zzbF().zzhb() != null)
    {
      List localList = zzbt.zzds();
      if (paramVersionInfoParcel.zzMZ != 0) {
        localList.add(Integer.toString(paramVersionInfoParcel.zzMZ));
      }
      zzr.zzbF().zzhb().zzb(localList);
    }
    this.zzri = UUID.randomUUID().toString();
    if ((paramAdSizeParcel.zzui) || (paramAdSizeParcel.zzuk))
    {
      this.zzrm = null;
      this.zzrp = paramAdSizeParcel;
      this.zzrj = paramString;
      this.context = paramContext;
      this.zzrl = paramVersionInfoParcel;
      if (paramzzan == null) {
        break label246;
      }
    }
    for (;;)
    {
      this.zzrk = paramzzan;
      this.zzrQ = new zziz(200L);
      this.zzrC = new SimpleArrayMap();
      return;
      this.zzrm = new zza(paramContext, this, this);
      this.zzrm.setMinimumWidth(paramAdSizeParcel.widthPixels);
      this.zzrm.setMinimumHeight(paramAdSizeParcel.heightPixels);
      this.zzrm.setVisibility(4);
      break;
      label246:
      paramzzan = new zzan(new zzh(this));
    }
  }
  
  private void zzbZ()
  {
    View localView = this.zzrm.getRootView().findViewById(16908290);
    if (localView == null) {}
    Rect localRect1;
    Rect localRect2;
    do
    {
      return;
      localRect1 = new Rect();
      localRect2 = new Rect();
      this.zzrm.getGlobalVisibleRect(localRect1);
      localView.getGlobalVisibleRect(localRect2);
      if (localRect1.top != localRect2.top) {
        this.zzrR = false;
      }
    } while (localRect1.bottom == localRect2.bottom);
    this.zzrS = false;
  }
  
  private void zze(boolean paramBoolean)
  {
    boolean bool = true;
    if ((this.zzrm == null) || (this.zzrq == null) || (this.zzrq.zzED == null)) {}
    while ((paramBoolean) && (!this.zzrQ.tryAcquire())) {
      return;
    }
    Object localObject;
    int i;
    int j;
    if (this.zzrq.zzED.zzhU().zzcv())
    {
      localObject = new int[2];
      this.zzrm.getLocationOnScreen((int[])localObject);
      i = zzn.zzcS().zzc(this.context, localObject[0]);
      j = zzn.zzcS().zzc(this.context, localObject[1]);
      if ((i != this.zzrO) || (j != this.zzrP))
      {
        this.zzrO = i;
        this.zzrP = j;
        localObject = this.zzrq.zzED.zzhU();
        i = this.zzrO;
        j = this.zzrP;
        if (paramBoolean) {
          break label174;
        }
      }
    }
    label174:
    for (paramBoolean = bool;; paramBoolean = false)
    {
      ((zzjq)localObject).zza(i, j, paramBoolean);
      zzbZ();
      return;
    }
  }
  
  public void destroy()
  {
    zzbY();
    this.zzru = null;
    this.zzrv = null;
    this.zzry = null;
    this.zzrx = null;
    this.zzrE = null;
    this.zzrw = null;
    zzf(false);
    if (this.zzrm != null) {
      this.zzrm.removeAllViews();
    }
    zzbT();
    zzbV();
    this.zzrq = null;
  }
  
  public String getUserId()
  {
    return this.zzrG;
  }
  
  public void onGlobalLayout()
  {
    zze(false);
  }
  
  public void onScrollChanged()
  {
    zze(true);
    this.zzrT = true;
  }
  
  void setUserId(String paramString)
  {
    this.zzrG = paramString;
  }
  
  public void zza(HashSet<zzig> paramHashSet)
  {
    this.zzrN = paramHashSet;
  }
  
  public HashSet<zzig> zzbS()
  {
    return this.zzrN;
  }
  
  public void zzbT()
  {
    if ((this.zzrq != null) && (this.zzrq.zzED != null)) {
      this.zzrq.zzED.destroy();
    }
  }
  
  public void zzbU()
  {
    if ((this.zzrq != null) && (this.zzrq.zzED != null)) {
      this.zzrq.zzED.stopLoading();
    }
  }
  
  public void zzbV()
  {
    if ((this.zzrq != null) && (this.zzrq.zzCq != null)) {}
    try
    {
      this.zzrq.zzCq.destroy();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzin.zzaK("Could not destroy mediation adapter.");
    }
  }
  
  public boolean zzbW()
  {
    return this.zzrL == 0;
  }
  
  public boolean zzbX()
  {
    return this.zzrL == 1;
  }
  
  public void zzbY()
  {
    if (this.zzrm != null) {
      this.zzrm.zzbY();
    }
  }
  
  public String zzca()
  {
    if ((this.zzrR) && (this.zzrS)) {
      return "";
    }
    if (this.zzrR)
    {
      if (this.zzrT) {
        return "top-scrollable";
      }
      return "top-locked";
    }
    if (this.zzrS)
    {
      if (this.zzrT) {
        return "bottom-scrollable";
      }
      return "bottom-locked";
    }
    return "";
  }
  
  public void zzcb()
  {
    this.zzrs.zzl(this.zzrq.zzKY);
    this.zzrs.zzm(this.zzrq.zzKZ);
    this.zzrs.zzz(this.zzrp.zzui);
    this.zzrs.zzA(this.zzrq.zzHT);
  }
  
  public void zzf(boolean paramBoolean)
  {
    if (this.zzrL == 0) {
      zzbU();
    }
    if (this.zzrn != null) {
      this.zzrn.cancel();
    }
    if (this.zzro != null) {
      this.zzro.cancel();
    }
    if (paramBoolean) {
      this.zzrq = null;
    }
  }
  
  public static class zza
    extends ViewSwitcher
  {
    private final zziu zzrU;
    private final zzjc zzrV;
    
    public zza(Context paramContext, ViewTreeObserver.OnGlobalLayoutListener paramOnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener paramOnScrollChangedListener)
    {
      super();
      this.zzrU = new zziu(paramContext);
      if ((paramContext instanceof Activity))
      {
        this.zzrV = new zzjc((Activity)paramContext, paramOnGlobalLayoutListener, paramOnScrollChangedListener);
        this.zzrV.zzhE();
        return;
      }
      this.zzrV = null;
    }
    
    protected void onAttachedToWindow()
    {
      super.onAttachedToWindow();
      if (this.zzrV != null) {
        this.zzrV.onAttachedToWindow();
      }
    }
    
    protected void onDetachedFromWindow()
    {
      super.onDetachedFromWindow();
      if (this.zzrV != null) {
        this.zzrV.onDetachedFromWindow();
      }
    }
    
    public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
    {
      this.zzrU.zze(paramMotionEvent);
      return false;
    }
    
    public void removeAllViews()
    {
      Object localObject = new ArrayList();
      int i = 0;
      while (i < getChildCount())
      {
        View localView = getChildAt(i);
        if ((localView != null) && ((localView instanceof zzjp))) {
          ((List)localObject).add((zzjp)localView);
        }
        i += 1;
      }
      super.removeAllViews();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        ((zzjp)((Iterator)localObject).next()).destroy();
      }
    }
    
    public void zzbY()
    {
      zzin.v("Disable position monitoring on adFrame.");
      if (this.zzrV != null) {
        this.zzrV.zzhF();
      }
    }
    
    public zziu zzcc()
    {
      return this.zzrU;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\zzs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */