package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.Path;
import android.net.Uri;
import android.widget.ImageView;

public final class zzmc
  extends ImageView
{
  private Uri zzakr;
  private int zzaks;
  private int zzakt;
  private zza zzaku;
  private int zzakv;
  private float zzakw;
  
  protected void onDraw(Canvas paramCanvas)
  {
    if (this.zzaku != null) {
      paramCanvas.clipPath(this.zzaku.zzl(getWidth(), getHeight()));
    }
    super.onDraw(paramCanvas);
    if (this.zzakt != 0) {
      paramCanvas.drawColor(this.zzakt);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    switch (this.zzakv)
    {
    default: 
      return;
    case 1: 
      paramInt1 = getMeasuredHeight();
      paramInt2 = (int)(paramInt1 * this.zzakw);
    }
    for (;;)
    {
      setMeasuredDimension(paramInt2, paramInt1);
      return;
      paramInt2 = getMeasuredWidth();
      paramInt1 = (int)(paramInt2 / this.zzakw);
    }
  }
  
  public void zzbO(int paramInt)
  {
    this.zzaks = paramInt;
  }
  
  public void zzm(Uri paramUri)
  {
    this.zzakr = paramUri;
  }
  
  public int zzqp()
  {
    return this.zzaks;
  }
  
  public static abstract interface zza
  {
    public abstract Path zzl(int paramInt1, int paramInt2);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzmc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */