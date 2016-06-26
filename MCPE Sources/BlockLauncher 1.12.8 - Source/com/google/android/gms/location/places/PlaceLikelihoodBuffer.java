package com.google.android.gms.location.places;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.location.places.internal.zzn;

public class PlaceLikelihoodBuffer
  extends AbstractDataBuffer<PlaceLikelihood>
  implements Result
{
  private final Context mContext;
  private final Status zzUX;
  private final String zzaPy;
  private final int zzvr;
  
  public PlaceLikelihoodBuffer(DataHolder paramDataHolder, int paramInt, Context paramContext)
  {
    super(paramDataHolder);
    this.mContext = paramContext;
    this.zzUX = PlacesStatusCodes.zzhU(paramDataHolder.getStatusCode());
    this.zzvr = zza.zzhP(paramInt);
    if ((paramDataHolder != null) && (paramDataHolder.zzpZ() != null))
    {
      this.zzaPy = paramDataHolder.zzpZ().getString("com.google.android.gms.location.places.PlaceLikelihoodBuffer.ATTRIBUTIONS_EXTRA_KEY");
      return;
    }
    this.zzaPy = null;
  }
  
  public static int zzH(Bundle paramBundle)
  {
    return paramBundle.getInt("com.google.android.gms.location.places.PlaceLikelihoodBuffer.SOURCE_EXTRA_KEY");
  }
  
  public PlaceLikelihood get(int paramInt)
  {
    return new zzn(this.zzahi, paramInt, this.mContext);
  }
  
  @Nullable
  public CharSequence getAttributions()
  {
    return this.zzaPy;
  }
  
  public Status getStatus()
  {
    return this.zzUX;
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("status", getStatus()).zzg("attributions", this.zzaPy).toString();
  }
  
  public static class zza
  {
    static int zzhP(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        throw new IllegalArgumentException("invalid source: " + paramInt);
      }
      return paramInt;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\location\places\PlaceLikelihoodBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */