package com.google.android.gms.nearby.messages;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzx;

public final class SubscribeOptions
{
  public static final SubscribeOptions DEFAULT = new Builder().build();
  private final Strategy zzbbI;
  private final MessageFilter zzbbV;
  @Nullable
  private final SubscribeCallback zzbbW;
  
  private SubscribeOptions(Strategy paramStrategy, MessageFilter paramMessageFilter, @Nullable SubscribeCallback paramSubscribeCallback)
  {
    this.zzbbI = paramStrategy;
    this.zzbbV = paramMessageFilter;
    this.zzbbW = paramSubscribeCallback;
  }
  
  @Nullable
  public SubscribeCallback getCallback()
  {
    return this.zzbbW;
  }
  
  public MessageFilter getFilter()
  {
    return this.zzbbV;
  }
  
  public Strategy getStrategy()
  {
    return this.zzbbI;
  }
  
  public static class Builder
  {
    private Strategy zzbbI = Strategy.DEFAULT;
    private MessageFilter zzbbV = MessageFilter.INCLUDE_ALL_MY_TYPES;
    @Nullable
    private SubscribeCallback zzbbW;
    
    public SubscribeOptions build()
    {
      return new SubscribeOptions(this.zzbbI, this.zzbbV, this.zzbbW, null);
    }
    
    public Builder setCallback(SubscribeCallback paramSubscribeCallback)
    {
      this.zzbbW = ((SubscribeCallback)zzx.zzz(paramSubscribeCallback));
      return this;
    }
    
    public Builder setFilter(MessageFilter paramMessageFilter)
    {
      this.zzbbV = paramMessageFilter;
      return this;
    }
    
    public Builder setStrategy(Strategy paramStrategy)
    {
      this.zzbbI = paramStrategy;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\nearby\messages\SubscribeOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */