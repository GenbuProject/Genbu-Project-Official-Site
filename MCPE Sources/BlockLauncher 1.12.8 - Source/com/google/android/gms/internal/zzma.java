package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.SystemClock;

public final class zzma
  extends Drawable
  implements Drawable.Callback
{
  private int mFrom;
  private long zzRD;
  private boolean zzajT = true;
  private int zzaka = 0;
  private int zzakb;
  private int zzakc = 255;
  private int zzakd;
  private int zzake = 0;
  private boolean zzakf;
  private zzb zzakg;
  private Drawable zzakh;
  private Drawable zzaki;
  private boolean zzakj;
  private boolean zzakk;
  private boolean zzakl;
  private int zzakm;
  
  public zzma(Drawable paramDrawable1, Drawable paramDrawable2)
  {
    this(null);
    Object localObject = paramDrawable1;
    if (paramDrawable1 == null) {
      localObject = zza.zzqo();
    }
    this.zzakh = ((Drawable)localObject);
    ((Drawable)localObject).setCallback(this);
    paramDrawable1 = this.zzakg;
    paramDrawable1.zzakq |= ((Drawable)localObject).getChangingConfigurations();
    paramDrawable1 = paramDrawable2;
    if (paramDrawable2 == null) {
      paramDrawable1 = zza.zzqo();
    }
    this.zzaki = paramDrawable1;
    paramDrawable1.setCallback(this);
    paramDrawable2 = this.zzakg;
    paramDrawable2.zzakq |= paramDrawable1.getChangingConfigurations();
  }
  
  zzma(zzb paramzzb)
  {
    this.zzakg = new zzb(paramzzb);
  }
  
  public boolean canConstantState()
  {
    if (!this.zzakj) {
      if ((this.zzakh.getConstantState() == null) || (this.zzaki.getConstantState() == null)) {
        break label44;
      }
    }
    label44:
    for (boolean bool = true;; bool = false)
    {
      this.zzakk = bool;
      this.zzakj = true;
      return this.zzakk;
    }
  }
  
  public void draw(Canvas paramCanvas)
  {
    int j = 1;
    int i = 1;
    int k = 0;
    switch (this.zzaka)
    {
    }
    boolean bool;
    Drawable localDrawable1;
    Drawable localDrawable2;
    do
    {
      for (;;)
      {
        j = this.zzake;
        bool = this.zzajT;
        localDrawable1 = this.zzakh;
        localDrawable2 = this.zzaki;
        if (i == 0) {
          break;
        }
        if ((!bool) || (j == 0)) {
          localDrawable1.draw(paramCanvas);
        }
        if (j == this.zzakc)
        {
          localDrawable2.setAlpha(this.zzakc);
          localDrawable2.draw(paramCanvas);
        }
        return;
        this.zzRD = SystemClock.uptimeMillis();
        this.zzaka = 2;
        i = k;
      }
    } while (this.zzRD < 0L);
    float f1 = (float)(SystemClock.uptimeMillis() - this.zzRD) / this.zzakd;
    if (f1 >= 1.0F) {}
    for (i = j;; i = 0)
    {
      if (i != 0) {
        this.zzaka = 0;
      }
      f1 = Math.min(f1, 1.0F);
      float f2 = this.mFrom;
      this.zzake = ((int)(f1 * (this.zzakb - this.mFrom) + f2));
      break;
    }
    if (bool) {
      localDrawable1.setAlpha(this.zzakc - j);
    }
    localDrawable1.draw(paramCanvas);
    if (bool) {
      localDrawable1.setAlpha(this.zzakc);
    }
    if (j > 0)
    {
      localDrawable2.setAlpha(j);
      localDrawable2.draw(paramCanvas);
      localDrawable2.setAlpha(this.zzakc);
    }
    invalidateSelf();
  }
  
  public int getChangingConfigurations()
  {
    return super.getChangingConfigurations() | this.zzakg.zzakp | this.zzakg.zzakq;
  }
  
  public Drawable.ConstantState getConstantState()
  {
    if (canConstantState())
    {
      this.zzakg.zzakp = getChangingConfigurations();
      return this.zzakg;
    }
    return null;
  }
  
  public int getIntrinsicHeight()
  {
    return Math.max(this.zzakh.getIntrinsicHeight(), this.zzaki.getIntrinsicHeight());
  }
  
  public int getIntrinsicWidth()
  {
    return Math.max(this.zzakh.getIntrinsicWidth(), this.zzaki.getIntrinsicWidth());
  }
  
  public int getOpacity()
  {
    if (!this.zzakl)
    {
      this.zzakm = Drawable.resolveOpacity(this.zzakh.getOpacity(), this.zzaki.getOpacity());
      this.zzakl = true;
    }
    return this.zzakm;
  }
  
  @TargetApi(11)
  public void invalidateDrawable(Drawable paramDrawable)
  {
    if (zzne.zzsd())
    {
      paramDrawable = getCallback();
      if (paramDrawable != null) {
        paramDrawable.invalidateDrawable(this);
      }
    }
  }
  
  public Drawable mutate()
  {
    if ((!this.zzakf) && (super.mutate() == this))
    {
      if (!canConstantState()) {
        throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
      }
      this.zzakh.mutate();
      this.zzaki.mutate();
      this.zzakf = true;
    }
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    this.zzakh.setBounds(paramRect);
    this.zzaki.setBounds(paramRect);
  }
  
  @TargetApi(11)
  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong)
  {
    if (zzne.zzsd())
    {
      paramDrawable = getCallback();
      if (paramDrawable != null) {
        paramDrawable.scheduleDrawable(this, paramRunnable, paramLong);
      }
    }
  }
  
  public void setAlpha(int paramInt)
  {
    if (this.zzake == this.zzakc) {
      this.zzake = paramInt;
    }
    this.zzakc = paramInt;
    invalidateSelf();
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.zzakh.setColorFilter(paramColorFilter);
    this.zzaki.setColorFilter(paramColorFilter);
  }
  
  public void startTransition(int paramInt)
  {
    this.mFrom = 0;
    this.zzakb = this.zzakc;
    this.zzake = 0;
    this.zzakd = paramInt;
    this.zzaka = 1;
    invalidateSelf();
  }
  
  @TargetApi(11)
  public void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable)
  {
    if (zzne.zzsd())
    {
      paramDrawable = getCallback();
      if (paramDrawable != null) {
        paramDrawable.unscheduleDrawable(this, paramRunnable);
      }
    }
  }
  
  public Drawable zzqn()
  {
    return this.zzaki;
  }
  
  private static final class zza
    extends Drawable
  {
    private static final zza zzakn = new zza();
    private static final zza zzako = new zza(null);
    
    public void draw(Canvas paramCanvas) {}
    
    public Drawable.ConstantState getConstantState()
    {
      return zzako;
    }
    
    public int getOpacity()
    {
      return -2;
    }
    
    public void setAlpha(int paramInt) {}
    
    public void setColorFilter(ColorFilter paramColorFilter) {}
    
    private static final class zza
      extends Drawable.ConstantState
    {
      public int getChangingConfigurations()
      {
        return 0;
      }
      
      public Drawable newDrawable()
      {
        return zzma.zza.zzqo();
      }
    }
  }
  
  static final class zzb
    extends Drawable.ConstantState
  {
    int zzakp;
    int zzakq;
    
    zzb(zzb paramzzb)
    {
      if (paramzzb != null)
      {
        this.zzakp = paramzzb.zzakp;
        this.zzakq = paramzzb.zzakq;
      }
    }
    
    public int getChangingConfigurations()
    {
      return this.zzakp;
    }
    
    public Drawable newDrawable()
    {
      return new zzma(this);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzma.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */