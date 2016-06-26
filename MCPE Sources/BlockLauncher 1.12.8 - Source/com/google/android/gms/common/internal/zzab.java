package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;

public final class zzab
  extends zzg<zzu>
{
  private static final zzab zzamw = new zzab();
  
  private zzab()
  {
    super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
  }
  
  public static View zzb(Context paramContext, int paramInt1, int paramInt2, Scope[] paramArrayOfScope)
    throws zzg.zza
  {
    return zzamw.zzc(paramContext, paramInt1, paramInt2, paramArrayOfScope);
  }
  
  private View zzc(Context paramContext, int paramInt1, int paramInt2, Scope[] paramArrayOfScope)
    throws zzg.zza
  {
    try
    {
      paramArrayOfScope = new SignInButtonConfig(paramInt1, paramInt2, paramArrayOfScope);
      zzd localzzd = zze.zzC(paramContext);
      paramContext = (View)zze.zzp(((zzu)zzaB(paramContext)).zza(localzzd, paramArrayOfScope));
      return paramContext;
    }
    catch (Exception paramContext)
    {
      throw new zzg.zza("Could not get button with size " + paramInt1 + " and color " + paramInt2, paramContext);
    }
  }
  
  public zzu zzaV(IBinder paramIBinder)
  {
    return zzu.zza.zzaU(paramIBinder);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\internal\zzab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */