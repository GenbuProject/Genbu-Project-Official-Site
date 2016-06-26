package com.google.android.gms.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View.MeasureSpec;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.zzr;

@zzhb
public class zzgo
  implements Runnable
{
  private final Handler zzGj;
  private final long zzGk;
  private long zzGl;
  private zzjq.zza zzGm;
  protected boolean zzGn;
  protected boolean zzGo;
  private final int zzoG;
  private final int zzoH;
  protected final zzjp zzpD;
  
  public zzgo(zzjq.zza paramzza, zzjp paramzzjp, int paramInt1, int paramInt2)
  {
    this(paramzza, paramzzjp, paramInt1, paramInt2, 200L, 50L);
  }
  
  public zzgo(zzjq.zza paramzza, zzjp paramzzjp, int paramInt1, int paramInt2, long paramLong1, long paramLong2)
  {
    this.zzGk = paramLong1;
    this.zzGl = paramLong2;
    this.zzGj = new Handler(Looper.getMainLooper());
    this.zzpD = paramzzjp;
    this.zzGm = paramzza;
    this.zzGn = false;
    this.zzGo = false;
    this.zzoH = paramInt2;
    this.zzoG = paramInt1;
  }
  
  public void run()
  {
    if ((this.zzpD == null) || (zzgg()))
    {
      this.zzGm.zza(this.zzpD, true);
      return;
    }
    new zza(this.zzpD.getWebView()).execute(new Void[0]);
  }
  
  public void zza(AdResponseParcel paramAdResponseParcel)
  {
    zza(paramAdResponseParcel, new zzjy(this, this.zzpD, paramAdResponseParcel.zzIa));
  }
  
  public void zza(AdResponseParcel paramAdResponseParcel, zzjy paramzzjy)
  {
    this.zzpD.setWebViewClient(paramzzjy);
    zzjp localzzjp = this.zzpD;
    if (TextUtils.isEmpty(paramAdResponseParcel.zzEF)) {}
    for (paramzzjy = null;; paramzzjy = zzr.zzbC().zzaC(paramAdResponseParcel.zzEF))
    {
      localzzjp.loadDataWithBaseURL(paramzzjy, paramAdResponseParcel.body, "text/html", "UTF-8", null);
      return;
    }
  }
  
  public void zzge()
  {
    this.zzGj.postDelayed(this, this.zzGk);
  }
  
  public void zzgf()
  {
    try
    {
      this.zzGn = true;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean zzgg()
  {
    try
    {
      boolean bool = this.zzGn;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean zzgh()
  {
    return this.zzGo;
  }
  
  protected final class zza
    extends AsyncTask<Void, Void, Boolean>
  {
    private final WebView zzGp;
    private Bitmap zzGq;
    
    public zza(WebView paramWebView)
    {
      this.zzGp = paramWebView;
    }
    
    protected void onPreExecute()
    {
      try
      {
        this.zzGq = Bitmap.createBitmap(zzgo.zza(zzgo.this), zzgo.zzb(zzgo.this), Bitmap.Config.ARGB_8888);
        this.zzGp.setVisibility(0);
        this.zzGp.measure(View.MeasureSpec.makeMeasureSpec(zzgo.zza(zzgo.this), 0), View.MeasureSpec.makeMeasureSpec(zzgo.zzb(zzgo.this), 0));
        this.zzGp.layout(0, 0, zzgo.zza(zzgo.this), zzgo.zzb(zzgo.this));
        Canvas localCanvas = new Canvas(this.zzGq);
        this.zzGp.draw(localCanvas);
        this.zzGp.invalidate();
        return;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
    
    protected Boolean zza(Void... paramVarArgs)
    {
      for (;;)
      {
        int i;
        int m;
        try
        {
          int n = this.zzGq.getWidth();
          int i1 = this.zzGq.getHeight();
          if ((n == 0) || (i1 == 0))
          {
            paramVarArgs = Boolean.valueOf(false);
            return paramVarArgs;
          }
          i = 0;
          j = 0;
          int k;
          if (i < n)
          {
            k = 0;
            if (k >= i1) {
              break label139;
            }
            m = j;
            if (this.zzGq.getPixel(i, k) != 0) {
              m = j + 1;
            }
          }
          else
          {
            if (j / (n * i1 / 100.0D) > 0.1D)
            {
              bool = true;
              paramVarArgs = Boolean.valueOf(bool);
              continue;
            }
            boolean bool = false;
            continue;
          }
          k += 10;
        }
        finally {}
        int j = m;
        continue;
        label139:
        i += 10;
      }
    }
    
    protected void zza(Boolean paramBoolean)
    {
      zzgo.zzc(zzgo.this);
      if ((paramBoolean.booleanValue()) || (zzgo.this.zzgg()) || (zzgo.zzd(zzgo.this) <= 0L))
      {
        zzgo.this.zzGo = paramBoolean.booleanValue();
        zzgo.zze(zzgo.this).zza(zzgo.this.zzpD, true);
      }
      while (zzgo.zzd(zzgo.this) <= 0L) {
        return;
      }
      if (zzin.zzQ(2)) {
        zzin.zzaI("Ad not detected, scheduling another run.");
      }
      zzgo.zzg(zzgo.this).postDelayed(zzgo.this, zzgo.zzf(zzgo.this));
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzgo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */