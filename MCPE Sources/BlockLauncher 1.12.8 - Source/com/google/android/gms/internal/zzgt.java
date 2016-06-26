package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

@zzhb
@TargetApi(19)
public class zzgt
  extends zzgs
{
  private Object zzGv = new Object();
  private PopupWindow zzGw;
  private boolean zzGx = false;
  
  zzgt(Context paramContext, zzif.zza paramzza, zzjp paramzzjp, zzgr.zza paramzza1)
  {
    super(paramContext, paramzza, paramzzjp, paramzza1);
  }
  
  private void zzgj()
  {
    synchronized (this.zzGv)
    {
      this.zzGx = true;
      if (((this.mContext instanceof Activity)) && (((Activity)this.mContext).isDestroyed())) {
        this.zzGw = null;
      }
      if (this.zzGw != null)
      {
        if (this.zzGw.isShowing()) {
          this.zzGw.dismiss();
        }
        this.zzGw = null;
      }
      return;
    }
  }
  
  public void cancel()
  {
    zzgj();
    super.cancel();
  }
  
  protected void zzC(int paramInt)
  {
    zzgj();
    super.zzC(paramInt);
  }
  
  protected void zzgi()
  {
    if ((this.mContext instanceof Activity)) {}
    Object localObject2;
    for (Window localWindow = ((Activity)this.mContext).getWindow();; localObject2 = null)
    {
      if ((localWindow == null) || (localWindow.getDecorView() == null)) {}
      while (((Activity)this.mContext).isDestroyed()) {
        return;
      }
      FrameLayout localFrameLayout = new FrameLayout(this.mContext);
      localFrameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
      localFrameLayout.addView(this.zzpD.getView(), -1, -1);
      synchronized (this.zzGv)
      {
        if (this.zzGx) {
          return;
        }
      }
      this.zzGw = new PopupWindow(localFrameLayout, 1, 1, false);
      this.zzGw.setOutsideTouchable(true);
      this.zzGw.setClippingEnabled(false);
      zzin.zzaI("Displaying the 1x1 popup off the screen.");
      try
      {
        this.zzGw.showAtLocation(((Window)localObject1).getDecorView(), 0, -1, -1);
        return;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          this.zzGw = null;
        }
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzgt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */