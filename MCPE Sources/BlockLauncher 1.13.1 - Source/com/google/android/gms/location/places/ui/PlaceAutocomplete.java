package com.google.android.gms.location.places.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLngBounds;

public class PlaceAutocomplete
  extends zza
{
  public static final int MODE_FULLSCREEN = 1;
  public static final int MODE_OVERLAY = 2;
  public static final int RESULT_ERROR = 2;
  
  public static Place getPlace(Context paramContext, Intent paramIntent)
  {
    return zza.getPlace(paramContext, paramIntent);
  }
  
  public static Status getStatus(Context paramContext, Intent paramIntent)
  {
    return zza.getStatus(paramContext, paramIntent);
  }
  
  public static class IntentBuilder
    extends zza.zza
  {
    public IntentBuilder(int paramInt)
    {
      super();
      this.mIntent.putExtra("gmscore_client_jar_version", GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE);
      this.mIntent.putExtra("mode", paramInt);
      this.mIntent.putExtra("origin", 2);
    }
    
    public Intent build(Activity paramActivity)
      throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
    {
      return super.build(paramActivity);
    }
    
    public IntentBuilder setBoundsBias(@Nullable LatLngBounds paramLatLngBounds)
    {
      if (paramLatLngBounds != null)
      {
        this.mIntent.putExtra("bounds", paramLatLngBounds);
        return this;
      }
      this.mIntent.removeExtra("bounds");
      return this;
    }
    
    public IntentBuilder setFilter(@Nullable AutocompleteFilter paramAutocompleteFilter)
    {
      if (paramAutocompleteFilter != null)
      {
        this.mIntent.putExtra("filter", paramAutocompleteFilter);
        return this;
      }
      this.mIntent.removeExtra("filter");
      return this;
    }
    
    public IntentBuilder zzeq(@Nullable String paramString)
    {
      if (paramString != null)
      {
        this.mIntent.putExtra("initial_query", paramString);
        return this;
      }
      this.mIntent.removeExtra("initial_query");
      return this;
    }
    
    public IntentBuilder zzig(int paramInt)
    {
      this.mIntent.putExtra("origin", paramInt);
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\places\ui\PlaceAutocomplete.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */