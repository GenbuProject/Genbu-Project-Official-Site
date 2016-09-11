package com.google.android.gms.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.internal.zzma;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzmc;
import com.google.android.gms.internal.zzmd;
import com.google.android.gms.internal.zzmd.zza;
import java.lang.ref.WeakReference;

public abstract class zza
{
  final zza zzajO;
  protected int zzajP = 0;
  protected int zzajQ = 0;
  protected boolean zzajR = false;
  protected ImageManager.OnImageLoadedListener zzajS;
  private boolean zzajT = true;
  private boolean zzajU = false;
  private boolean zzajV = true;
  protected int zzajW;
  
  public zza(Uri paramUri, int paramInt)
  {
    this.zzajO = new zza(paramUri);
    this.zzajQ = paramInt;
  }
  
  private Drawable zza(Context paramContext, zzmd paramzzmd, int paramInt)
  {
    Resources localResources = paramContext.getResources();
    if (this.zzajW > 0)
    {
      zzmd.zza localzza = new zzmd.zza(paramInt, this.zzajW);
      Drawable localDrawable = (Drawable)paramzzmd.get(localzza);
      paramContext = localDrawable;
      if (localDrawable == null)
      {
        localDrawable = localResources.getDrawable(paramInt);
        paramContext = localDrawable;
        if ((this.zzajW & 0x1) != 0) {
          paramContext = zza(localResources, localDrawable);
        }
        paramzzmd.put(localzza, paramContext);
      }
      return paramContext;
    }
    return localResources.getDrawable(paramInt);
  }
  
  protected Drawable zza(Resources paramResources, Drawable paramDrawable)
  {
    return zzmb.zza(paramResources, paramDrawable);
  }
  
  protected zzma zza(Drawable paramDrawable1, Drawable paramDrawable2)
  {
    if (paramDrawable1 != null)
    {
      localDrawable = paramDrawable1;
      if (!(paramDrawable1 instanceof zzma)) {}
    }
    for (Drawable localDrawable = ((zzma)paramDrawable1).zzqn();; localDrawable = null) {
      return new zzma(localDrawable, paramDrawable2);
    }
  }
  
  void zza(Context paramContext, Bitmap paramBitmap, boolean paramBoolean)
  {
    zzb.zzv(paramBitmap);
    Bitmap localBitmap = paramBitmap;
    if ((this.zzajW & 0x1) != 0) {
      localBitmap = zzmb.zzb(paramBitmap);
    }
    paramContext = new BitmapDrawable(paramContext.getResources(), localBitmap);
    if (this.zzajS != null) {
      this.zzajS.onImageLoaded(this.zzajO.uri, paramContext, true);
    }
    zza(paramContext, paramBoolean, false, true);
  }
  
  void zza(Context paramContext, zzmd paramzzmd)
  {
    if (this.zzajV)
    {
      Drawable localDrawable = null;
      if (this.zzajP != 0) {
        localDrawable = zza(paramContext, paramzzmd, this.zzajP);
      }
      zza(localDrawable, false, true, false);
    }
  }
  
  void zza(Context paramContext, zzmd paramzzmd, boolean paramBoolean)
  {
    Drawable localDrawable = null;
    if (this.zzajQ != 0) {
      localDrawable = zza(paramContext, paramzzmd, this.zzajQ);
    }
    if (this.zzajS != null) {
      this.zzajS.onImageLoaded(this.zzajO.uri, localDrawable, false);
    }
    zza(localDrawable, paramBoolean, false, false);
  }
  
  protected abstract void zza(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3);
  
  protected boolean zzb(boolean paramBoolean1, boolean paramBoolean2)
  {
    return (this.zzajT) && (!paramBoolean2) && ((!paramBoolean1) || (this.zzajU));
  }
  
  public void zzbM(int paramInt)
  {
    this.zzajQ = paramInt;
  }
  
  static final class zza
  {
    public final Uri uri;
    
    public zza(Uri paramUri)
    {
      this.uri = paramUri;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof zza)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      return zzw.equal(((zza)paramObject).uri, this.uri);
    }
    
    public int hashCode()
    {
      return zzw.hashCode(new Object[] { this.uri });
    }
  }
  
  public static final class zzb
    extends zza
  {
    private WeakReference<ImageView> zzajX;
    
    public zzb(ImageView paramImageView, int paramInt)
    {
      super(paramInt);
      zzb.zzv(paramImageView);
      this.zzajX = new WeakReference(paramImageView);
    }
    
    public zzb(ImageView paramImageView, Uri paramUri)
    {
      super(0);
      zzb.zzv(paramImageView);
      this.zzajX = new WeakReference(paramImageView);
    }
    
    private void zza(ImageView paramImageView, Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
    {
      if ((!paramBoolean2) && (!paramBoolean3)) {}
      for (int i = 1; (i != 0) && ((paramImageView instanceof zzmc)); i = 0)
      {
        int j = ((zzmc)paramImageView).zzqp();
        if ((this.zzajQ == 0) || (j != this.zzajQ)) {
          break;
        }
        return;
      }
      paramBoolean1 = zzb(paramBoolean1, paramBoolean2);
      if ((this.zzajR) && (paramDrawable != null)) {
        paramDrawable = paramDrawable.getConstantState().newDrawable();
      }
      for (;;)
      {
        Object localObject = paramDrawable;
        if (paramBoolean1) {
          localObject = zza(paramImageView.getDrawable(), paramDrawable);
        }
        paramImageView.setImageDrawable((Drawable)localObject);
        if ((paramImageView instanceof zzmc))
        {
          paramDrawable = (zzmc)paramImageView;
          if (!paramBoolean3) {
            break label171;
          }
          paramImageView = this.zzajO.uri;
          label133:
          paramDrawable.zzm(paramImageView);
          if (i == 0) {
            break label176;
          }
        }
        label171:
        label176:
        for (i = this.zzajQ;; i = 0)
        {
          paramDrawable.zzbO(i);
          if (!paramBoolean1) {
            break;
          }
          ((zzma)localObject).startTransition(250);
          return;
          paramImageView = null;
          break label133;
        }
      }
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof zzb)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      Object localObject = (zzb)paramObject;
      paramObject = (ImageView)this.zzajX.get();
      localObject = (ImageView)((zzb)localObject).zzajX.get();
      if ((localObject != null) && (paramObject != null) && (zzw.equal(localObject, paramObject))) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public int hashCode()
    {
      return 0;
    }
    
    protected void zza(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
    {
      ImageView localImageView = (ImageView)this.zzajX.get();
      if (localImageView != null) {
        zza(localImageView, paramDrawable, paramBoolean1, paramBoolean2, paramBoolean3);
      }
    }
  }
  
  public static final class zzc
    extends zza
  {
    private WeakReference<ImageManager.OnImageLoadedListener> zzajY;
    
    public zzc(ImageManager.OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri)
    {
      super(0);
      zzb.zzv(paramOnImageLoadedListener);
      this.zzajY = new WeakReference(paramOnImageLoadedListener);
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof zzc)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      paramObject = (zzc)paramObject;
      ImageManager.OnImageLoadedListener localOnImageLoadedListener1 = (ImageManager.OnImageLoadedListener)this.zzajY.get();
      ImageManager.OnImageLoadedListener localOnImageLoadedListener2 = (ImageManager.OnImageLoadedListener)((zzc)paramObject).zzajY.get();
      if ((localOnImageLoadedListener2 != null) && (localOnImageLoadedListener1 != null) && (zzw.equal(localOnImageLoadedListener2, localOnImageLoadedListener1)) && (zzw.equal(((zzc)paramObject).zzajO, this.zzajO))) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public int hashCode()
    {
      return zzw.hashCode(new Object[] { this.zzajO });
    }
    
    protected void zza(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
    {
      if (!paramBoolean2)
      {
        ImageManager.OnImageLoadedListener localOnImageLoadedListener = (ImageManager.OnImageLoadedListener)this.zzajY.get();
        if (localOnImageLoadedListener != null) {
          localOnImageLoadedListener.onImageLoaded(this.zzajO.uri, paramDrawable, paramBoolean3);
        }
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\common\images\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */