package com.google.android.gms.ads.internal.formats;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzcj.zza;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zzjk;
import com.google.android.gms.internal.zzjp;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@zzhb
public class zzk
  extends zzcj.zza
  implements View.OnClickListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener
{
  private FrameLayout zzoQ;
  private final Object zzpV = new Object();
  private final FrameLayout zzyD;
  private Map<String, WeakReference<View>> zzyE = new HashMap();
  private zzb zzyF;
  boolean zzyG = false;
  int zzyH;
  int zzyI;
  private zzh zzyf;
  
  public zzk(FrameLayout paramFrameLayout1, FrameLayout paramFrameLayout2)
  {
    this.zzyD = paramFrameLayout1;
    this.zzoQ = paramFrameLayout2;
    zzjk.zza(this.zzyD, this);
    zzjk.zza(this.zzyD, this);
    this.zzyD.setOnTouchListener(this);
  }
  
  public void destroy()
  {
    this.zzoQ.removeAllViews();
    this.zzoQ = null;
    this.zzyE = null;
    this.zzyF = null;
    this.zzyf = null;
  }
  
  int getMeasuredHeight()
  {
    return this.zzyD.getMeasuredHeight();
  }
  
  int getMeasuredWidth()
  {
    return this.zzyD.getMeasuredWidth();
  }
  
  public void onClick(View paramView)
  {
    JSONObject localJSONObject1;
    Object localObject3;
    synchronized (this.zzpV)
    {
      if (this.zzyf == null) {
        return;
      }
      localJSONObject1 = new JSONObject();
      localObject2 = this.zzyE.entrySet().iterator();
      for (;;)
      {
        if (((Iterator)localObject2).hasNext())
        {
          localObject3 = (Map.Entry)((Iterator)localObject2).next();
          View localView = (View)((WeakReference)((Map.Entry)localObject3).getValue()).get();
          Point localPoint = zzj(localView);
          JSONObject localJSONObject2 = new JSONObject();
          try
          {
            localJSONObject2.put("width", zzq(localView.getWidth()));
            localJSONObject2.put("height", zzq(localView.getHeight()));
            localJSONObject2.put("x", zzq(localPoint.x));
            localJSONObject2.put("y", zzq(localPoint.y));
            localJSONObject1.put((String)((Map.Entry)localObject3).getKey(), localJSONObject2);
          }
          catch (JSONException localJSONException2)
          {
            zzin.zzaK("Unable to get view rectangle for view " + (String)((Map.Entry)localObject3).getKey());
          }
        }
      }
    }
    Object localObject2 = new JSONObject();
    try
    {
      ((JSONObject)localObject2).put("x", zzq(this.zzyH));
      ((JSONObject)localObject2).put("y", zzq(this.zzyI));
      localObject3 = new JSONObject();
    }
    catch (JSONException localJSONException1)
    {
      try
      {
        ((JSONObject)localObject3).put("width", zzq(getMeasuredWidth()));
        ((JSONObject)localObject3).put("height", zzq(getMeasuredHeight()));
        if ((this.zzyF != null) && (this.zzyF.zzdI().equals(paramView)))
        {
          this.zzyf.zza("1007", localJSONObject1, (JSONObject)localObject2, (JSONObject)localObject3);
          return;
          localJSONException1 = localJSONException1;
          zzin.zzaK("Unable to get click location");
        }
      }
      catch (JSONException localJSONException3)
      {
        for (;;)
        {
          zzin.zzaK("Unable to get native ad view bounding box");
          continue;
          this.zzyf.zza(paramView, this.zzyE, localJSONObject1, (JSONObject)localObject2, localJSONException1);
        }
      }
    }
  }
  
  public void onGlobalLayout()
  {
    synchronized (this.zzpV)
    {
      if (this.zzyG)
      {
        int i = getMeasuredWidth();
        int j = getMeasuredHeight();
        if ((i != 0) && (j != 0))
        {
          this.zzoQ.setLayoutParams(new FrameLayout.LayoutParams(i, j));
          this.zzyG = false;
        }
      }
      if (this.zzyf != null) {
        this.zzyf.zzh(this.zzyD);
      }
      return;
    }
  }
  
  public void onScrollChanged()
  {
    synchronized (this.zzpV)
    {
      if (this.zzyf != null) {
        this.zzyf.zzh(this.zzyD);
      }
      return;
    }
  }
  
  public boolean onTouch(View arg1, MotionEvent paramMotionEvent)
  {
    synchronized (this.zzpV)
    {
      if (this.zzyf == null) {
        return false;
      }
      Point localPoint = zzc(paramMotionEvent);
      this.zzyH = localPoint.x;
      this.zzyI = localPoint.y;
      paramMotionEvent = MotionEvent.obtain(paramMotionEvent);
      paramMotionEvent.setLocation(localPoint.x, localPoint.y);
      this.zzyf.zzb(paramMotionEvent);
      paramMotionEvent.recycle();
      return false;
    }
  }
  
  public zzd zzK(String paramString)
  {
    synchronized (this.zzpV)
    {
      paramString = (WeakReference)this.zzyE.get(paramString);
      if (paramString == null)
      {
        paramString = null;
        paramString = zze.zzC(paramString);
        return paramString;
      }
      paramString = (View)paramString.get();
    }
  }
  
  public void zza(final zzd paramzzd)
  {
    for (;;)
    {
      synchronized (this.zzpV)
      {
        this.zzyG = true;
        zzi(null);
        paramzzd = zze.zzp(paramzzd);
        if (!(paramzzd instanceof zzi))
        {
          zzin.zzaK("Not an instance of native engine. This is most likely a transient error");
          return;
        }
        paramzzd = (zzi)paramzzd;
        if (((this.zzyf instanceof zzg)) && (((zzg)this.zzyf).zzdP()))
        {
          ((zzg)this.zzyf).zzc(paramzzd);
          this.zzoQ.removeAllViews();
          this.zzyF = zzd(paramzzd);
          if (this.zzyF != null)
          {
            this.zzyE.put("1007", new WeakReference(this.zzyF.zzdI()));
            this.zzoQ.addView(this.zzyF);
          }
          zzir.zzMc.post(new Runnable()
          {
            public void run()
            {
              zzjp localzzjp = paramzzd.zzdR();
              if (localzzjp != null) {
                zzk.zza(zzk.this).addView(localzzjp.getView());
              }
            }
          });
          paramzzd.zzg(this.zzyD);
          zzi(this.zzyD);
          return;
        }
      }
      this.zzyf = paramzzd;
      if ((this.zzyf instanceof zzg)) {
        ((zzg)this.zzyf).zzc(null);
      }
    }
  }
  
  public void zza(String paramString, zzd paramzzd)
  {
    View localView = (View)zze.zzp(paramzzd);
    paramzzd = this.zzpV;
    if (localView == null) {}
    for (;;)
    {
      try
      {
        this.zzyE.remove(paramString);
        return;
      }
      finally {}
      this.zzyE.put(paramString, new WeakReference(localView));
      localView.setOnTouchListener(this);
      localView.setOnClickListener(this);
    }
  }
  
  Point zzc(MotionEvent paramMotionEvent)
  {
    int[] arrayOfInt = new int[2];
    this.zzyD.getLocationOnScreen(arrayOfInt);
    float f1 = paramMotionEvent.getRawX();
    float f2 = arrayOfInt[0];
    float f3 = paramMotionEvent.getRawY();
    float f4 = arrayOfInt[1];
    return new Point((int)(f1 - f2), (int)(f3 - f4));
  }
  
  zzb zzd(zzi paramzzi)
  {
    return paramzzi.zza(this);
  }
  
  void zzi(View paramView)
  {
    if (this.zzyf != null) {
      if (!(this.zzyf instanceof zzg)) {
        break label40;
      }
    }
    label40:
    for (zzh localzzh = ((zzg)this.zzyf).zzdQ();; localzzh = this.zzyf)
    {
      if (localzzh != null) {
        localzzh.zzi(paramView);
      }
      return;
    }
  }
  
  Point zzj(View paramView)
  {
    if ((this.zzyF != null) && (this.zzyF.zzdI().equals(paramView)))
    {
      localPoint1 = new Point();
      this.zzyD.getGlobalVisibleRect(new Rect(), localPoint1);
      Point localPoint2 = new Point();
      paramView.getGlobalVisibleRect(new Rect(), localPoint2);
      return new Point(localPoint2.x - localPoint1.x, localPoint2.y - localPoint1.y);
    }
    Point localPoint1 = new Point();
    paramView.getGlobalVisibleRect(new Rect(), localPoint1);
    return localPoint1;
  }
  
  int zzq(int paramInt)
  {
    return zzn.zzcS().zzc(this.zzyf.getContext(), paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\formats\zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */