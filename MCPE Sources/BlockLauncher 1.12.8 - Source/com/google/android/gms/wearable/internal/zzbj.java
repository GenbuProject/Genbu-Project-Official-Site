package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.ChannelApi.ChannelListener;

final class zzbj
  implements ChannelApi.ChannelListener
{
  private final String zzVo;
  private final ChannelApi.ChannelListener zzbtb;
  
  zzbj(String paramString, ChannelApi.ChannelListener paramChannelListener)
  {
    this.zzVo = ((String)zzx.zzz(paramString));
    this.zzbtb = ((ChannelApi.ChannelListener)zzx.zzz(paramChannelListener));
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof zzbj)) {
        return false;
      }
      paramObject = (zzbj)paramObject;
    } while ((this.zzbtb.equals(((zzbj)paramObject).zzbtb)) && (this.zzVo.equals(((zzbj)paramObject).zzVo)));
    return false;
  }
  
  public int hashCode()
  {
    return this.zzVo.hashCode() * 31 + this.zzbtb.hashCode();
  }
  
  public void onChannelClosed(Channel paramChannel, int paramInt1, int paramInt2)
  {
    this.zzbtb.onChannelClosed(paramChannel, paramInt1, paramInt2);
  }
  
  public void onChannelOpened(Channel paramChannel)
  {
    this.zzbtb.onChannelOpened(paramChannel);
  }
  
  public void onInputClosed(Channel paramChannel, int paramInt1, int paramInt2)
  {
    this.zzbtb.onInputClosed(paramChannel, paramInt1, paramInt2);
  }
  
  public void onOutputClosed(Channel paramChannel, int paramInt1, int paramInt2)
  {
    this.zzbtb.onOutputClosed(paramChannel, paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\zzbj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */