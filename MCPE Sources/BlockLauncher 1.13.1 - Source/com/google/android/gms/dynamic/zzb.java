package com.google.android.gms.dynamic;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

@SuppressLint({"NewApi"})
public final class zzb
  extends zzc.zza
{
  private Fragment zzavH;
  
  private zzb(Fragment paramFragment)
  {
    this.zzavH = paramFragment;
  }
  
  public static zzb zza(Fragment paramFragment)
  {
    if (paramFragment != null) {
      return new zzb(paramFragment);
    }
    return null;
  }
  
  public Bundle getArguments()
  {
    return this.zzavH.getArguments();
  }
  
  public int getId()
  {
    return this.zzavH.getId();
  }
  
  public boolean getRetainInstance()
  {
    return this.zzavH.getRetainInstance();
  }
  
  public String getTag()
  {
    return this.zzavH.getTag();
  }
  
  public int getTargetRequestCode()
  {
    return this.zzavH.getTargetRequestCode();
  }
  
  public boolean getUserVisibleHint()
  {
    return this.zzavH.getUserVisibleHint();
  }
  
  public zzd getView()
  {
    return zze.zzC(this.zzavH.getView());
  }
  
  public boolean isAdded()
  {
    return this.zzavH.isAdded();
  }
  
  public boolean isDetached()
  {
    return this.zzavH.isDetached();
  }
  
  public boolean isHidden()
  {
    return this.zzavH.isHidden();
  }
  
  public boolean isInLayout()
  {
    return this.zzavH.isInLayout();
  }
  
  public boolean isRemoving()
  {
    return this.zzavH.isRemoving();
  }
  
  public boolean isResumed()
  {
    return this.zzavH.isResumed();
  }
  
  public boolean isVisible()
  {
    return this.zzavH.isVisible();
  }
  
  public void setHasOptionsMenu(boolean paramBoolean)
  {
    this.zzavH.setHasOptionsMenu(paramBoolean);
  }
  
  public void setMenuVisibility(boolean paramBoolean)
  {
    this.zzavH.setMenuVisibility(paramBoolean);
  }
  
  public void setRetainInstance(boolean paramBoolean)
  {
    this.zzavH.setRetainInstance(paramBoolean);
  }
  
  public void setUserVisibleHint(boolean paramBoolean)
  {
    this.zzavH.setUserVisibleHint(paramBoolean);
  }
  
  public void startActivity(Intent paramIntent)
  {
    this.zzavH.startActivity(paramIntent);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    this.zzavH.startActivityForResult(paramIntent, paramInt);
  }
  
  public void zzn(zzd paramzzd)
  {
    paramzzd = (View)zze.zzp(paramzzd);
    this.zzavH.registerForContextMenu(paramzzd);
  }
  
  public void zzo(zzd paramzzd)
  {
    paramzzd = (View)zze.zzp(paramzzd);
    this.zzavH.unregisterForContextMenu(paramzzd);
  }
  
  public zzd zztV()
  {
    return zze.zzC(this.zzavH.getActivity());
  }
  
  public zzc zztW()
  {
    return zza(this.zzavH.getParentFragment());
  }
  
  public zzd zztX()
  {
    return zze.zzC(this.zzavH.getResources());
  }
  
  public zzc zztY()
  {
    return zza(this.zzavH.getTargetFragment());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\dynamic\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */