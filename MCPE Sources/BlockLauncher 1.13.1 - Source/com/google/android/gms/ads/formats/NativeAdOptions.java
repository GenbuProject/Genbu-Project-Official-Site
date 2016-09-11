package com.google.android.gms.ads.formats;

public final class NativeAdOptions
{
  public static final int ORIENTATION_ANY = 0;
  public static final int ORIENTATION_LANDSCAPE = 2;
  public static final int ORIENTATION_PORTRAIT = 1;
  private final boolean zzoN;
  private final int zzoO;
  private final boolean zzoP;
  
  private NativeAdOptions(Builder paramBuilder)
  {
    this.zzoN = Builder.zza(paramBuilder);
    this.zzoO = Builder.zzb(paramBuilder);
    this.zzoP = Builder.zzc(paramBuilder);
  }
  
  public int getImageOrientation()
  {
    return this.zzoO;
  }
  
  public boolean shouldRequestMultipleImages()
  {
    return this.zzoP;
  }
  
  public boolean shouldReturnUrlsForImageAssets()
  {
    return this.zzoN;
  }
  
  public static final class Builder
  {
    private boolean zzoN = false;
    private int zzoO = 0;
    private boolean zzoP = false;
    
    public NativeAdOptions build()
    {
      return new NativeAdOptions(this, null);
    }
    
    public Builder setImageOrientation(int paramInt)
    {
      this.zzoO = paramInt;
      return this;
    }
    
    public Builder setRequestMultipleImages(boolean paramBoolean)
    {
      this.zzoP = paramBoolean;
      return this;
    }
    
    public Builder setReturnUrlsForImageAssets(boolean paramBoolean)
    {
      this.zzoN = paramBoolean;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\formats\NativeAdOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */