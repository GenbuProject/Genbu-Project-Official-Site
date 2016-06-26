package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import com.google.android.gms.common.api.internal.zzq;
import com.google.android.gms.common.api.internal.zzq.zzb;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.wearable.CapabilityApi.CapabilityListener;
import com.google.android.gms.wearable.ChannelApi.ChannelListener;
import com.google.android.gms.wearable.DataApi.DataListener;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.MessageApi.MessageListener;
import com.google.android.gms.wearable.NodeApi.NodeListener;
import com.google.android.gms.wearable.NodeApi.zza;
import com.google.android.gms.wearable.zza.zza;
import com.google.android.gms.wearable.zzc.zza;
import java.util.List;

final class zzbq<T>
  extends zzaw.zza
{
  private zzq<MessageApi.MessageListener> zzbbb;
  private final IntentFilter[] zzbsT;
  private zzq<zza.zza> zzbtp;
  private zzq<zzc.zza> zzbtq;
  private zzq<DataApi.DataListener> zzbtr;
  private zzq<NodeApi.NodeListener> zzbts;
  private zzq<NodeApi.zza> zzbtt;
  private zzq<ChannelApi.ChannelListener> zzbtu;
  private zzq<CapabilityApi.CapabilityListener> zzbtv;
  private final String zzbtw;
  
  private zzbq(IntentFilter[] paramArrayOfIntentFilter, String paramString)
  {
    this.zzbsT = ((IntentFilter[])zzx.zzz(paramArrayOfIntentFilter));
    this.zzbtw = paramString;
  }
  
  private static zzq.zzb<NodeApi.zza> zzI(List<NodeParcelable> paramList)
  {
    new zzq.zzb()
    {
      public void zza(NodeApi.zza paramAnonymouszza)
      {
        paramAnonymouszza.onConnectedNodes(this.zzbrv);
      }
      
      public void zzpr() {}
    };
  }
  
  public static zzbq<ChannelApi.ChannelListener> zza(zzq<ChannelApi.ChannelListener> paramzzq, String paramString, IntentFilter[] paramArrayOfIntentFilter)
  {
    paramString = new zzbq(paramArrayOfIntentFilter, (String)zzx.zzz(paramString));
    paramString.zzbtu = ((zzq)zzx.zzz(paramzzq));
    return paramString;
  }
  
  public static zzbq<DataApi.DataListener> zza(zzq<DataApi.DataListener> paramzzq, IntentFilter[] paramArrayOfIntentFilter)
  {
    paramArrayOfIntentFilter = new zzbq(paramArrayOfIntentFilter, null);
    paramArrayOfIntentFilter.zzbtr = ((zzq)zzx.zzz(paramzzq));
    return paramArrayOfIntentFilter;
  }
  
  private static zzq.zzb<DataApi.DataListener> zzai(DataHolder paramDataHolder)
  {
    new zzq.zzb()
    {
      public void zza(DataApi.DataListener paramAnonymousDataListener)
      {
        try
        {
          paramAnonymousDataListener.onDataChanged(new DataEventBuffer(this.zzbrr));
          return;
        }
        finally
        {
          this.zzbrr.close();
        }
      }
      
      public void zzpr()
      {
        this.zzbrr.close();
      }
    };
  }
  
  private static zzq.zzb<zza.zza> zzb(AmsEntityUpdateParcelable paramAmsEntityUpdateParcelable)
  {
    new zzq.zzb()
    {
      public void zza(zza.zza paramAnonymouszza)
      {
        paramAnonymouszza.zza(this.zzbry);
      }
      
      public void zzpr() {}
    };
  }
  
  private static zzq.zzb<zzc.zza> zzb(AncsNotificationParcelable paramAncsNotificationParcelable)
  {
    new zzq.zzb()
    {
      public void zza(zzc.zza paramAnonymouszza)
      {
        paramAnonymouszza.zza(this.zzbrx);
      }
      
      public void zzpr() {}
    };
  }
  
  private static zzq.zzb<CapabilityApi.CapabilityListener> zzb(CapabilityInfoParcelable paramCapabilityInfoParcelable)
  {
    new zzq.zzb()
    {
      public void zza(CapabilityApi.CapabilityListener paramAnonymousCapabilityListener)
      {
        paramAnonymousCapabilityListener.onCapabilityChanged(this.zzbtx);
      }
      
      public void zzpr() {}
    };
  }
  
  private static zzq.zzb<ChannelApi.ChannelListener> zzb(ChannelEventParcelable paramChannelEventParcelable)
  {
    new zzq.zzb()
    {
      public void zzb(ChannelApi.ChannelListener paramAnonymousChannelListener)
      {
        this.zzbrz.zza(paramAnonymousChannelListener);
      }
      
      public void zzpr() {}
    };
  }
  
  private static zzq.zzb<MessageApi.MessageListener> zzb(MessageEventParcelable paramMessageEventParcelable)
  {
    new zzq.zzb()
    {
      public void zza(MessageApi.MessageListener paramAnonymousMessageListener)
      {
        paramAnonymousMessageListener.onMessageReceived(this.zzbrt);
      }
      
      public void zzpr() {}
    };
  }
  
  public static zzbq<MessageApi.MessageListener> zzb(zzq<MessageApi.MessageListener> paramzzq, IntentFilter[] paramArrayOfIntentFilter)
  {
    paramArrayOfIntentFilter = new zzbq(paramArrayOfIntentFilter, null);
    paramArrayOfIntentFilter.zzbbb = ((zzq)zzx.zzz(paramzzq));
    return paramArrayOfIntentFilter;
  }
  
  private static zzq.zzb<NodeApi.NodeListener> zzc(NodeParcelable paramNodeParcelable)
  {
    new zzq.zzb()
    {
      public void zza(NodeApi.NodeListener paramAnonymousNodeListener)
      {
        paramAnonymousNodeListener.onPeerConnected(this.zzbru);
      }
      
      public void zzpr() {}
    };
  }
  
  public static zzbq<NodeApi.NodeListener> zzc(zzq<NodeApi.NodeListener> paramzzq, IntentFilter[] paramArrayOfIntentFilter)
  {
    paramArrayOfIntentFilter = new zzbq(paramArrayOfIntentFilter, null);
    paramArrayOfIntentFilter.zzbts = ((zzq)zzx.zzz(paramzzq));
    return paramArrayOfIntentFilter;
  }
  
  private static zzq.zzb<NodeApi.NodeListener> zzd(NodeParcelable paramNodeParcelable)
  {
    new zzq.zzb()
    {
      public void zza(NodeApi.NodeListener paramAnonymousNodeListener)
      {
        paramAnonymousNodeListener.onPeerDisconnected(this.zzbru);
      }
      
      public void zzpr() {}
    };
  }
  
  public static zzbq<ChannelApi.ChannelListener> zzd(zzq<ChannelApi.ChannelListener> paramzzq, IntentFilter[] paramArrayOfIntentFilter)
  {
    paramArrayOfIntentFilter = new zzbq(paramArrayOfIntentFilter, null);
    paramArrayOfIntentFilter.zzbtu = ((zzq)zzx.zzz(paramzzq));
    return paramArrayOfIntentFilter;
  }
  
  public static zzbq<CapabilityApi.CapabilityListener> zze(zzq<CapabilityApi.CapabilityListener> paramzzq, IntentFilter[] paramArrayOfIntentFilter)
  {
    paramArrayOfIntentFilter = new zzbq(paramArrayOfIntentFilter, null);
    paramArrayOfIntentFilter.zzbtv = ((zzq)zzx.zzz(paramzzq));
    return paramArrayOfIntentFilter;
  }
  
  private static void zzh(zzq<?> paramzzq)
  {
    if (paramzzq != null) {
      paramzzq.clear();
    }
  }
  
  public void clear()
  {
    zzh(this.zzbtp);
    this.zzbtp = null;
    zzh(this.zzbtq);
    this.zzbtq = null;
    zzh(this.zzbtr);
    this.zzbtr = null;
    zzh(this.zzbbb);
    this.zzbbb = null;
    zzh(this.zzbts);
    this.zzbts = null;
    zzh(this.zzbtt);
    this.zzbtt = null;
    zzh(this.zzbtu);
    this.zzbtu = null;
    zzh(this.zzbtv);
    this.zzbtv = null;
  }
  
  public void onConnectedNodes(List<NodeParcelable> paramList)
  {
    if (this.zzbtt != null) {
      this.zzbtt.zza(zzI(paramList));
    }
  }
  
  public IntentFilter[] zzIO()
  {
    return this.zzbsT;
  }
  
  public String zzIP()
  {
    return this.zzbtw;
  }
  
  public void zza(AmsEntityUpdateParcelable paramAmsEntityUpdateParcelable)
  {
    if (this.zzbtp != null) {
      this.zzbtp.zza(zzb(paramAmsEntityUpdateParcelable));
    }
  }
  
  public void zza(AncsNotificationParcelable paramAncsNotificationParcelable)
  {
    if (this.zzbtq != null) {
      this.zzbtq.zza(zzb(paramAncsNotificationParcelable));
    }
  }
  
  public void zza(CapabilityInfoParcelable paramCapabilityInfoParcelable)
  {
    if (this.zzbtv != null) {
      this.zzbtv.zza(zzb(paramCapabilityInfoParcelable));
    }
  }
  
  public void zza(ChannelEventParcelable paramChannelEventParcelable)
  {
    if (this.zzbtu != null) {
      this.zzbtu.zza(zzb(paramChannelEventParcelable));
    }
  }
  
  public void zza(MessageEventParcelable paramMessageEventParcelable)
  {
    if (this.zzbbb != null) {
      this.zzbbb.zza(zzb(paramMessageEventParcelable));
    }
  }
  
  public void zza(NodeParcelable paramNodeParcelable)
  {
    if (this.zzbts != null) {
      this.zzbts.zza(zzc(paramNodeParcelable));
    }
  }
  
  public void zzag(DataHolder paramDataHolder)
  {
    if (this.zzbtr != null)
    {
      this.zzbtr.zza(zzai(paramDataHolder));
      return;
    }
    paramDataHolder.close();
  }
  
  public void zzb(NodeParcelable paramNodeParcelable)
  {
    if (this.zzbts != null) {
      this.zzbts.zza(zzd(paramNodeParcelable));
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\zzbq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */