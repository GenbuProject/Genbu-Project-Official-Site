package com.google.android.gms.location.places;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.internal.zzr;

public class PlaceBuffer
  extends AbstractDataBuffer<Place>
  implements Result
{
  private final Context mContext;
  private final Status zzUX;
  private final String zzaPy;
  
  public PlaceBuffer(DataHolder paramDataHolder, Context paramContext)
  {
    super(paramDataHolder);
    this.mContext = paramContext;
    this.zzUX = PlacesStatusCodes.zzhU(paramDataHolder.getStatusCode());
    if ((paramDataHolder != null) && (paramDataHolder.zzpZ() != null))
    {
      this.zzaPy = paramDataHolder.zzpZ().getString("com.google.android.gms.location.places.PlaceBuffer.ATTRIBUTIONS_EXTRA_KEY");
      return;
    }
    this.zzaPy = null;
  }
  
  public Place get(int paramInt)
  {
    return new zzr(this.zzahi, paramInt, this.mContext);
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
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\location\places\PlaceBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */