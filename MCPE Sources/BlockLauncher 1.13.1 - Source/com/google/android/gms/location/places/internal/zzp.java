package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.zzf;
import com.google.android.gms.location.places.zzf.zza;

public class zzp
  implements PlacePhotoMetadata
{
  private int mIndex;
  private final int zzDF;
  private final int zzDG;
  private final String zzaQR;
  private final CharSequence zzaQS;
  
  public zzp(String paramString, int paramInt1, int paramInt2, CharSequence paramCharSequence, int paramInt3)
  {
    this.zzaQR = paramString;
    this.zzDF = paramInt1;
    this.zzDG = paramInt2;
    this.zzaQS = paramCharSequence;
    this.mIndex = paramInt3;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof zzp)) {
        return false;
      }
      paramObject = (zzp)paramObject;
    } while ((((zzp)paramObject).zzDF == this.zzDF) && (((zzp)paramObject).zzDG == this.zzDG) && (zzw.equal(((zzp)paramObject).zzaQR, this.zzaQR)) && (zzw.equal(((zzp)paramObject).zzaQS, this.zzaQS)));
    return false;
  }
  
  public CharSequence getAttributions()
  {
    return this.zzaQS;
  }
  
  public int getMaxHeight()
  {
    return this.zzDG;
  }
  
  public int getMaxWidth()
  {
    return this.zzDF;
  }
  
  public PendingResult<PlacePhotoResult> getPhoto(GoogleApiClient paramGoogleApiClient)
  {
    return getScaledPhoto(paramGoogleApiClient, getMaxWidth(), getMaxHeight());
  }
  
  public PendingResult<PlacePhotoResult> getScaledPhoto(GoogleApiClient paramGoogleApiClient, final int paramInt1, final int paramInt2)
  {
    paramGoogleApiClient.zza(new zzf.zza(Places.zzaPN, paramGoogleApiClient)
    {
      protected void zza(zze paramAnonymouszze)
        throws RemoteException
      {
        paramAnonymouszze.zza(new zzf(this), zzp.zza(zzp.this), paramInt1, paramInt2, zzp.zzb(zzp.this));
      }
    });
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Integer.valueOf(this.zzDF), Integer.valueOf(this.zzDG), this.zzaQR, this.zzaQS });
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public PlacePhotoMetadata zzzz()
  {
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\places\internal\zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */