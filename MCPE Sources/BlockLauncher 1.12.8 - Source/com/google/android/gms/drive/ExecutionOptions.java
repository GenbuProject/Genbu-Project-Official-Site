package com.google.android.gms.drive;

import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.drive.internal.zzu;

public class ExecutionOptions
{
  public static final int CONFLICT_STRATEGY_KEEP_REMOTE = 1;
  public static final int CONFLICT_STRATEGY_OVERWRITE_REMOTE = 0;
  public static final int MAX_TRACKING_TAG_STRING_LENGTH = 65536;
  private final String zzaoV;
  private final boolean zzaoW;
  private final int zzaoX;
  
  public ExecutionOptions(String paramString, boolean paramBoolean, int paramInt)
  {
    this.zzaoV = paramString;
    this.zzaoW = paramBoolean;
    this.zzaoX = paramInt;
  }
  
  public static boolean zzcX(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (paramString.length() <= 65536);
  }
  
  public static boolean zzcv(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return false;
    }
    return true;
  }
  
  public static boolean zzcw(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return false;
    }
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if ((paramObject == null) || (paramObject.getClass() != getClass())) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramObject == this);
      paramObject = (ExecutionOptions)paramObject;
      if ((!zzw.equal(this.zzaoV, ((ExecutionOptions)paramObject).zzaoV)) || (this.zzaoX != ((ExecutionOptions)paramObject).zzaoX)) {
        break;
      }
      bool1 = bool2;
    } while (this.zzaoW == ((ExecutionOptions)paramObject).zzaoW);
    return false;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzaoV, Integer.valueOf(this.zzaoX), Boolean.valueOf(this.zzaoW) });
  }
  
  public void zzg(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient = (zzu)paramGoogleApiClient.zza(Drive.zzUI);
    if ((zzsC()) && (!paramGoogleApiClient.zzti())) {
      throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to be notified on completion");
    }
  }
  
  public String zzsB()
  {
    return this.zzaoV;
  }
  
  public boolean zzsC()
  {
    return this.zzaoW;
  }
  
  public int zzsD()
  {
    return this.zzaoX;
  }
  
  public static class Builder
  {
    protected String zzaoV;
    protected boolean zzaoW;
    protected int zzaoX = 0;
    
    public ExecutionOptions build()
    {
      zzsE();
      return new ExecutionOptions(this.zzaoV, this.zzaoW, this.zzaoX);
    }
    
    public Builder setConflictStrategy(int paramInt)
    {
      if (!ExecutionOptions.zzcw(paramInt)) {
        throw new IllegalArgumentException("Unrecognized value for conflict strategy: " + paramInt);
      }
      this.zzaoX = paramInt;
      return this;
    }
    
    public Builder setNotifyOnCompletion(boolean paramBoolean)
    {
      this.zzaoW = paramBoolean;
      return this;
    }
    
    public Builder setTrackingTag(String paramString)
    {
      if (!ExecutionOptions.zzcX(paramString)) {
        throw new IllegalArgumentException(String.format("trackingTag must not be null nor empty, and the length must be <= the maximum length (%s)", new Object[] { Integer.valueOf(65536) }));
      }
      this.zzaoV = paramString;
      return this;
    }
    
    protected void zzsE()
    {
      if ((this.zzaoX == 1) && (!this.zzaoW)) {
        throw new IllegalStateException("Cannot use CONFLICT_STRATEGY_KEEP_REMOTE without requesting completion notifications");
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\ExecutionOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */