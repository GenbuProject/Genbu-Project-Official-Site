package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Api.ApiOptions.Optional;

public final class PlacesOptions
  implements Api.ApiOptions.Optional
{
  public final String zzaPU;
  public final int zzaPV;
  
  private PlacesOptions(Builder paramBuilder)
  {
    this.zzaPU = Builder.zza(paramBuilder);
    this.zzaPV = Builder.zzb(paramBuilder);
  }
  
  public static class Builder
  {
    private int zzaPV = 0;
    private String zzaPW;
    
    public PlacesOptions build()
    {
      return new PlacesOptions(this, null);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\location\places\PlacesOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */