package com.google.android.gms.nearby.messages;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzx;

public final class PublishOptions
{
  public static final PublishOptions DEFAULT = new Builder().build();
  private final Strategy zzbbI;
  @Nullable
  private final PublishCallback zzbbJ;
  
  private PublishOptions(Strategy paramStrategy, @Nullable PublishCallback paramPublishCallback)
  {
    this.zzbbI = paramStrategy;
    this.zzbbJ = paramPublishCallback;
  }
  
  @Nullable
  public PublishCallback getCallback()
  {
    return this.zzbbJ;
  }
  
  public Strategy getStrategy()
  {
    return this.zzbbI;
  }
  
  public static class Builder
  {
    private Strategy zzbbI = Strategy.DEFAULT;
    @Nullable
    private PublishCallback zzbbJ;
    
    public PublishOptions build()
    {
      return new PublishOptions(this.zzbbI, this.zzbbJ, null);
    }
    
    public Builder setCallback(PublishCallback paramPublishCallback)
    {
      this.zzbbJ = ((PublishCallback)zzx.zzz(paramPublishCallback));
      return this;
    }
    
    public Builder setStrategy(Strategy paramStrategy)
    {
      this.zzbbI = ((Strategy)zzx.zzz(paramStrategy));
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\nearby\messages\PublishOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */