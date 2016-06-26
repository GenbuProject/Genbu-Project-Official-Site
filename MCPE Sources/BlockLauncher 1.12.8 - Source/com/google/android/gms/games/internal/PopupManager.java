package com.google.android.gms.games.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Display;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import com.google.android.gms.internal.zzne;
import java.lang.ref.WeakReference;

public class PopupManager
{
  protected GamesClientImpl zzaFJ;
  protected PopupLocationInfo zzaFK;
  
  private PopupManager(GamesClientImpl paramGamesClientImpl, int paramInt)
  {
    this.zzaFJ = paramGamesClientImpl;
    zzgv(paramInt);
  }
  
  public static PopupManager zza(GamesClientImpl paramGamesClientImpl, int paramInt)
  {
    if (zzne.zzse()) {
      return new PopupManagerHCMR1(paramGamesClientImpl, paramInt);
    }
    return new PopupManager(paramGamesClientImpl, paramInt);
  }
  
  public void setGravity(int paramInt)
  {
    this.zzaFK.gravity = paramInt;
  }
  
  protected void zzgv(int paramInt)
  {
    this.zzaFK = new PopupLocationInfo(paramInt, new Binder(), null);
  }
  
  public void zzp(View paramView) {}
  
  public void zzxh()
  {
    this.zzaFJ.zza(this.zzaFK.zzaFL, this.zzaFK.zzxg());
  }
  
  public Bundle zzxi()
  {
    return this.zzaFK.zzxg();
  }
  
  public IBinder zzxj()
  {
    return this.zzaFK.zzaFL;
  }
  
  public PopupLocationInfo zzxk()
  {
    return this.zzaFK;
  }
  
  public static final class PopupLocationInfo
  {
    public int bottom = 0;
    public int gravity;
    public int left = 0;
    public int right = 0;
    public int top = 0;
    public IBinder zzaFL;
    public int zzaFM = -1;
    
    private PopupLocationInfo(int paramInt, IBinder paramIBinder)
    {
      this.gravity = paramInt;
      this.zzaFL = paramIBinder;
    }
    
    public Bundle zzxg()
    {
      Bundle localBundle = new Bundle();
      localBundle.putInt("popupLocationInfo.gravity", this.gravity);
      localBundle.putInt("popupLocationInfo.displayId", this.zzaFM);
      localBundle.putInt("popupLocationInfo.left", this.left);
      localBundle.putInt("popupLocationInfo.top", this.top);
      localBundle.putInt("popupLocationInfo.right", this.right);
      localBundle.putInt("popupLocationInfo.bottom", this.bottom);
      return localBundle;
    }
  }
  
  @TargetApi(12)
  private static final class PopupManagerHCMR1
    extends PopupManager
    implements View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener
  {
    private boolean zzaEe = false;
    private WeakReference<View> zzaFN;
    
    protected PopupManagerHCMR1(GamesClientImpl paramGamesClientImpl, int paramInt)
    {
      super(paramInt, null);
    }
    
    @TargetApi(17)
    private void zzq(View paramView)
    {
      int j = -1;
      int i = j;
      if (zzne.zzsi())
      {
        localObject = paramView.getDisplay();
        i = j;
        if (localObject != null) {
          i = ((Display)localObject).getDisplayId();
        }
      }
      Object localObject = paramView.getWindowToken();
      int[] arrayOfInt = new int[2];
      paramView.getLocationInWindow(arrayOfInt);
      j = paramView.getWidth();
      int k = paramView.getHeight();
      this.zzaFK.zzaFM = i;
      this.zzaFK.zzaFL = ((IBinder)localObject);
      this.zzaFK.left = arrayOfInt[0];
      this.zzaFK.top = arrayOfInt[1];
      this.zzaFK.right = (arrayOfInt[0] + j);
      this.zzaFK.bottom = (arrayOfInt[1] + k);
      if (this.zzaEe)
      {
        zzxh();
        this.zzaEe = false;
      }
    }
    
    public void onGlobalLayout()
    {
      if (this.zzaFN == null) {}
      View localView;
      do
      {
        return;
        localView = (View)this.zzaFN.get();
      } while (localView == null);
      zzq(localView);
    }
    
    public void onViewAttachedToWindow(View paramView)
    {
      zzq(paramView);
    }
    
    public void onViewDetachedFromWindow(View paramView)
    {
      this.zzaFJ.zzwR();
      paramView.removeOnAttachStateChangeListener(this);
    }
    
    protected void zzgv(int paramInt)
    {
      this.zzaFK = new PopupManager.PopupLocationInfo(paramInt, null, null);
    }
    
    @TargetApi(16)
    public void zzp(View paramView)
    {
      this.zzaFJ.zzwR();
      Object localObject2;
      Object localObject1;
      if (this.zzaFN != null)
      {
        localObject2 = (View)this.zzaFN.get();
        Context localContext = this.zzaFJ.getContext();
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject1 = localObject2;
          if ((localContext instanceof Activity)) {
            localObject1 = ((Activity)localContext).getWindow().getDecorView();
          }
        }
        if (localObject1 != null)
        {
          ((View)localObject1).removeOnAttachStateChangeListener(this);
          localObject1 = ((View)localObject1).getViewTreeObserver();
          if (!zzne.zzsh()) {
            break label186;
          }
          ((ViewTreeObserver)localObject1).removeOnGlobalLayoutListener(this);
        }
      }
      for (;;)
      {
        this.zzaFN = null;
        localObject2 = this.zzaFJ.getContext();
        localObject1 = paramView;
        if (paramView == null)
        {
          localObject1 = paramView;
          if ((localObject2 instanceof Activity))
          {
            localObject1 = ((Activity)localObject2).findViewById(16908290);
            paramView = (View)localObject1;
            if (localObject1 == null) {
              paramView = ((Activity)localObject2).getWindow().getDecorView();
            }
            GamesLog.zzz("PopupManager", "You have not specified a View to use as content view for popups. Falling back to the Activity content view. Note that this may not work as expected in multi-screen environments");
            localObject1 = paramView;
          }
        }
        if (localObject1 == null) {
          break;
        }
        zzq((View)localObject1);
        this.zzaFN = new WeakReference(localObject1);
        ((View)localObject1).addOnAttachStateChangeListener(this);
        ((View)localObject1).getViewTreeObserver().addOnGlobalLayoutListener(this);
        return;
        label186:
        ((ViewTreeObserver)localObject1).removeGlobalOnLayoutListener(this);
      }
      GamesLog.zzA("PopupManager", "No content view usable to display popups. Popups will not be displayed in response to this client's calls. Use setViewForPopups() to set your content view.");
    }
    
    public void zzxh()
    {
      if (this.zzaFK.zzaFL != null)
      {
        super.zzxh();
        return;
      }
      if (this.zzaFN != null) {}
      for (boolean bool = true;; bool = false)
      {
        this.zzaEe = bool;
        return;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\internal\PopupManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */