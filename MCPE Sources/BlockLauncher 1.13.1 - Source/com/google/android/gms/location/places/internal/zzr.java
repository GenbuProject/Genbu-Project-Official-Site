package com.google.android.gms.location.places.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class zzr
  extends zzt
  implements Place
{
  private final String zzaPH = zzG("place_id", "");
  
  public zzr(DataHolder paramDataHolder, int paramInt, Context paramContext)
  {
    super(paramDataHolder, paramInt);
  }
  
  private PlaceImpl zzzA()
  {
    PlaceImpl localPlaceImpl = new PlaceImpl.zza().zzeo(getAddress().toString()).zzy(zzzq()).zzem(getId()).zzan(zzzr()).zza(getLatLng()).zzf(zzzo()).zzen(getName().toString()).zzep(getPhoneNumber().toString()).zzhX(getPriceLevel()).zzg(getRating()).zzx(getPlaceTypes()).zza(getViewport()).zzo(getWebsiteUri()).zzzx();
    localPlaceImpl.setLocale(getLocale());
    return localPlaceImpl;
  }
  
  private List<String> zzzq()
  {
    return zzb("place_attributions", Collections.emptyList());
  }
  
  public CharSequence getAddress()
  {
    return zzG("place_address", "");
  }
  
  public CharSequence getAttributions()
  {
    return zzc.zzj(zzzq());
  }
  
  public String getId()
  {
    return this.zzaPH;
  }
  
  public LatLng getLatLng()
  {
    return (LatLng)zza("place_lat_lng", LatLng.CREATOR);
  }
  
  public Locale getLocale()
  {
    String str = zzG("place_locale", "");
    if (!TextUtils.isEmpty(str)) {
      return new Locale(str);
    }
    return Locale.getDefault();
  }
  
  public CharSequence getName()
  {
    return zzG("place_name", "");
  }
  
  public CharSequence getPhoneNumber()
  {
    return zzG("place_phone_number", "");
  }
  
  public List<Integer> getPlaceTypes()
  {
    return zza("place_types", Collections.emptyList());
  }
  
  public int getPriceLevel()
  {
    return zzz("place_price_level", -1);
  }
  
  public float getRating()
  {
    return zzb("place_rating", -1.0F);
  }
  
  public LatLngBounds getViewport()
  {
    return (LatLngBounds)zza("place_viewport", LatLngBounds.CREATOR);
  }
  
  public Uri getWebsiteUri()
  {
    String str = zzG("place_website_uri", null);
    if (str == null) {
      return null;
    }
    return Uri.parse(str);
  }
  
  public float zzzo()
  {
    return zzb("place_level_number", 0.0F);
  }
  
  public boolean zzzr()
  {
    return zzl("place_is_permanently_closed", false);
  }
  
  public Place zzzw()
  {
    return zzzA();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\places\internal\zzr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */