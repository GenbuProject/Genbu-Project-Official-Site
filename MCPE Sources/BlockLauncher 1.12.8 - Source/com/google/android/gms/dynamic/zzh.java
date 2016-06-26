package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

public final class zzh
  extends zzc.zza
{
  private Fragment zzalg;
  
  private zzh(Fragment paramFragment)
  {
    this.zzalg = paramFragment;
  }
  
  public static zzh zza(Fragment paramFragment)
  {
    if (paramFragment != null) {
      return new zzh(paramFragment);
    }
    return null;
  }
  
  public Bundle getArguments()
  {
    return this.zzalg.getArguments();
  }
  
  public int getId()
  {
    return this.zzalg.getId();
  }
  
  public boolean getRetainInstance()
  {
    return this.zzalg.getRetainInstance();
  }
  
  public String getTag()
  {
    return this.zzalg.getTag();
  }
  
  public int getTargetRequestCode()
  {
    return this.zzalg.getTargetRequestCode();
  }
  
  public boolean getUserVisibleHint()
  {
    return this.zzalg.getUserVisibleHint();
  }
  
  public zzd getView()
  {
    return zze.zzC(this.zzalg.getView());
  }
  
  public boolean isAdded()
  {
    return this.zzalg.isAdded();
  }
  
  public boolean isDetached()
  {
    return this.zzalg.isDetached();
  }
  
  public boolean isHidden()
  {
    return this.zzalg.isHidden();
  }
  
  public boolean isInLayout()
  {
    return this.zzalg.isInLayout();
  }
  
  public boolean isRemoving()
  {
    return this.zzalg.isRemoving();
  }
  
  public boolean isResumed()
  {
    return this.zzalg.isResumed();
  }
  
  public boolean isVisible()
  {
    return this.zzalg.isVisible();
  }
  
  public void setHasOptionsMenu(boolean paramBoolean)
  {
    this.zzalg.setHasOptionsMenu(paramBoolean);
  }
  
  public void setMenuVisibility(boolean paramBoolean)
  {
    this.zzalg.setMenuVisibility(paramBoolean);
  }
  
  public void setRetainInstance(boolean paramBoolean)
  {
    this.zzalg.setRetainInstance(paramBoolean);
  }
  
  public void setUserVisibleHint(boolean paramBoolean)
  {
    this.zzalg.setUserVisibleHint(paramBoolean);
  }
  
  public void startActivity(Intent paramIntent)
  {
    this.zzalg.startActivity(paramIntent);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    this.zzalg.startActivityForResult(paramIntent, paramInt);
  }
  
  public void zzn(zzd paramzzd)
  {
    paramzzd = (View)zze.zzp(paramzzd);
    this.zzalg.registerForContextMenu(paramzzd);
  }
  
  public void zzo(zzd paramzzd)
  {
    paramzzd = (View)zze.zzp(paramzzd);
    this.zzalg.unregisterForContextMenu(paramzzd);
  }
  
  public zzd zztV()
  {
    return zze.zzC(this.zzalg.getActivity());
  }
  
  public zzc zztW()
  {
    return zza(this.zzalg.getParentFragment());
  }
  
  public zzd zztX()
  {
    return zze.zzC(this.zzalg.getResources());
  }
  
  public zzc zztY()
  {
    return zza(this.zzalg.getTargetFragment());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\dynamic\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */