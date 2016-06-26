package com.google.android.gms.location.places.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.util.TypedValue;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.internal.PlaceImpl;

abstract class zza
{
  public static final int RESULT_ERROR = 2;
  
  public static Place getPlace(Context paramContext, Intent paramIntent)
  {
    zzx.zzb(paramIntent, "intent must not be null");
    zzx.zzb(paramContext, "context must not be null");
    return (Place)zzc.zza(paramIntent, "selected_place", PlaceImpl.CREATOR);
  }
  
  public static Status getStatus(Context paramContext, Intent paramIntent)
  {
    zzx.zzb(paramIntent, "intent must not be null");
    zzx.zzb(paramContext, "context must not be null");
    return (Status)zzc.zza(paramIntent, "status", Status.CREATOR);
  }
  
  protected static abstract class zza
  {
    protected final Intent mIntent;
    
    public zza(String paramString)
    {
      this.mIntent = new Intent(paramString);
      this.mIntent.setPackage("com.google.android.gms");
    }
    
    protected Intent build(Activity paramActivity)
      throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
    {
      Resources.Theme localTheme = paramActivity.getTheme();
      TypedValue localTypedValue1 = new TypedValue();
      TypedValue localTypedValue2 = new TypedValue();
      if ((localTheme.resolveAttribute(16843827, localTypedValue1, true)) && (!this.mIntent.hasExtra("primary_color"))) {
        this.mIntent.putExtra("primary_color", localTypedValue1.data);
      }
      if ((localTheme.resolveAttribute(16843828, localTypedValue2, true)) && (!this.mIntent.hasExtra("primary_color_dark"))) {
        this.mIntent.putExtra("primary_color_dark", localTypedValue2.data);
      }
      GoogleApiAvailability.getInstance().zzak(paramActivity);
      return this.mIntent;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\location\places\ui\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */