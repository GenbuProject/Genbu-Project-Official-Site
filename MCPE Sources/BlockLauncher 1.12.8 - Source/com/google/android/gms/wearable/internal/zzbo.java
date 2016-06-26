package com.google.android.gms.wearable.internal;

import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.wearable.CapabilityApi.AddLocalCapabilityResult;
import com.google.android.gms.wearable.CapabilityApi.GetAllCapabilitiesResult;
import com.google.android.gms.wearable.CapabilityApi.GetCapabilityResult;
import com.google.android.gms.wearable.CapabilityApi.RemoveLocalCapabilityResult;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.Channel.GetInputStreamResult;
import com.google.android.gms.wearable.Channel.GetOutputStreamResult;
import com.google.android.gms.wearable.ChannelApi.OpenChannelResult;
import com.google.android.gms.wearable.DataApi.DataItemResult;
import com.google.android.gms.wearable.DataApi.DeleteDataItemsResult;
import com.google.android.gms.wearable.DataApi.GetFdForAssetResult;
import com.google.android.gms.wearable.DataItemBuffer;
import com.google.android.gms.wearable.MessageApi.SendMessageResult;
import com.google.android.gms.wearable.NodeApi.GetConnectedNodesResult;
import com.google.android.gms.wearable.NodeApi.GetLocalNodeResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

final class zzbo
{
  private static Map<String, CapabilityInfo> zzG(List<CapabilityInfoParcelable> paramList)
  {
    HashMap localHashMap = new HashMap(paramList.size() * 2);
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      CapabilityInfoParcelable localCapabilityInfoParcelable = (CapabilityInfoParcelable)paramList.next();
      localHashMap.put(localCapabilityInfoParcelable.getName(), new zzj.zzc(localCapabilityInfoParcelable));
    }
    return localHashMap;
  }
  
  static final class zza
    extends zzbo.zzb<CapabilityApi.AddLocalCapabilityResult>
  {
    public zza(zza.zzb<CapabilityApi.AddLocalCapabilityResult> paramzzb)
    {
      super();
    }
    
    public void zza(AddLocalCapabilityResponse paramAddLocalCapabilityResponse)
    {
      zzX(new zzj.zza(zzbk.zzgc(paramAddLocalCapabilityResponse.statusCode)));
    }
  }
  
  static abstract class zzb<T>
    extends zza
  {
    private zza.zzb<T> zzUz;
    
    public zzb(zza.zzb<T> paramzzb)
    {
      this.zzUz = paramzzb;
    }
    
    public void zzX(T paramT)
    {
      zza.zzb localzzb = this.zzUz;
      if (localzzb != null)
      {
        localzzb.zzs(paramT);
        this.zzUz = null;
      }
    }
  }
  
  static final class zzc
    extends zzbo.zzb<Status>
  {
    public zzc(zza.zzb<Status> paramzzb)
    {
      super();
    }
    
    public void zza(CloseChannelResponse paramCloseChannelResponse)
    {
      zzX(new Status(paramCloseChannelResponse.statusCode));
    }
  }
  
  static final class zzd
    extends zzbo.zzb<Status>
  {
    public zzd(zza.zzb<Status> paramzzb)
    {
      super();
    }
    
    public void zzb(CloseChannelResponse paramCloseChannelResponse)
    {
      zzX(new Status(paramCloseChannelResponse.statusCode));
    }
  }
  
  static final class zze
    extends zzbo.zzb<DataApi.DeleteDataItemsResult>
  {
    public zze(zza.zzb<DataApi.DeleteDataItemsResult> paramzzb)
    {
      super();
    }
    
    public void zza(DeleteDataItemsResponse paramDeleteDataItemsResponse)
    {
      zzX(new zzx.zzb(zzbk.zzgc(paramDeleteDataItemsResponse.statusCode), paramDeleteDataItemsResponse.zzbsz));
    }
  }
  
  static final class zzf
    extends zzbo.zzb<CapabilityApi.GetAllCapabilitiesResult>
  {
    public zzf(zza.zzb<CapabilityApi.GetAllCapabilitiesResult> paramzzb)
    {
      super();
    }
    
    public void zza(GetAllCapabilitiesResponse paramGetAllCapabilitiesResponse)
    {
      zzX(new zzj.zzd(zzbk.zzgc(paramGetAllCapabilitiesResponse.statusCode), zzbo.zzH(paramGetAllCapabilitiesResponse.zzbsA)));
    }
  }
  
  static final class zzg
    extends zzbo.zzb<CapabilityApi.GetCapabilityResult>
  {
    public zzg(zza.zzb<CapabilityApi.GetCapabilityResult> paramzzb)
    {
      super();
    }
    
    public void zza(GetCapabilityResponse paramGetCapabilityResponse)
    {
      zzX(new zzj.zze(zzbk.zzgc(paramGetCapabilityResponse.statusCode), new zzj.zzc(paramGetCapabilityResponse.zzbsB)));
    }
  }
  
  static final class zzh
    extends zzbo.zzb<Channel.GetInputStreamResult>
  {
    private final zzt zzbtd;
    
    public zzh(zza.zzb<Channel.GetInputStreamResult> paramzzb, zzt paramzzt)
    {
      super();
      this.zzbtd = ((zzt)zzx.zzz(paramzzt));
    }
    
    public void zza(GetChannelInputStreamResponse paramGetChannelInputStreamResponse)
    {
      zzp localzzp = null;
      if (paramGetChannelInputStreamResponse.zzbsC != null)
      {
        localzzp = new zzp(new ParcelFileDescriptor.AutoCloseInputStream(paramGetChannelInputStreamResponse.zzbsC));
        this.zzbtd.zza(localzzp.zzIJ());
      }
      zzX(new ChannelImpl.zza(new Status(paramGetChannelInputStreamResponse.statusCode), localzzp));
    }
  }
  
  static final class zzi
    extends zzbo.zzb<Channel.GetOutputStreamResult>
  {
    private final zzt zzbtd;
    
    public zzi(zza.zzb<Channel.GetOutputStreamResult> paramzzb, zzt paramzzt)
    {
      super();
      this.zzbtd = ((zzt)zzx.zzz(paramzzt));
    }
    
    public void zza(GetChannelOutputStreamResponse paramGetChannelOutputStreamResponse)
    {
      zzq localzzq = null;
      if (paramGetChannelOutputStreamResponse.zzbsC != null)
      {
        localzzq = new zzq(new ParcelFileDescriptor.AutoCloseOutputStream(paramGetChannelOutputStreamResponse.zzbsC));
        this.zzbtd.zza(localzzq.zzIJ());
      }
      zzX(new ChannelImpl.zzb(new Status(paramGetChannelOutputStreamResponse.statusCode), localzzq));
    }
  }
  
  static final class zzj
    extends zzbo.zzb<NodeApi.GetConnectedNodesResult>
  {
    public zzj(zza.zzb<NodeApi.GetConnectedNodesResult> paramzzb)
    {
      super();
    }
    
    public void zza(GetConnectedNodesResponse paramGetConnectedNodesResponse)
    {
      ArrayList localArrayList = new ArrayList();
      localArrayList.addAll(paramGetConnectedNodesResponse.zzbsI);
      zzX(new zzbb.zza(zzbk.zzgc(paramGetConnectedNodesResponse.statusCode), localArrayList));
    }
  }
  
  static final class zzk
    extends zzbo.zzb<DataApi.DataItemResult>
  {
    public zzk(zza.zzb<DataApi.DataItemResult> paramzzb)
    {
      super();
    }
    
    public void zza(GetDataItemResponse paramGetDataItemResponse)
    {
      zzX(new zzx.zza(zzbk.zzgc(paramGetDataItemResponse.statusCode), paramGetDataItemResponse.zzbsJ));
    }
  }
  
  static final class zzl
    extends zzbo.zzb<DataItemBuffer>
  {
    public zzl(zza.zzb<DataItemBuffer> paramzzb)
    {
      super();
    }
    
    public void zzah(DataHolder paramDataHolder)
    {
      zzX(new DataItemBuffer(paramDataHolder));
    }
  }
  
  static final class zzm
    extends zzbo.zzb<DataApi.GetFdForAssetResult>
  {
    public zzm(zza.zzb<DataApi.GetFdForAssetResult> paramzzb)
    {
      super();
    }
    
    public void zza(GetFdForAssetResponse paramGetFdForAssetResponse)
    {
      zzX(new zzx.zzc(zzbk.zzgc(paramGetFdForAssetResponse.statusCode), paramGetFdForAssetResponse.zzbsK));
    }
  }
  
  static final class zzn
    extends zzbo.zzb<NodeApi.GetLocalNodeResult>
  {
    public zzn(zza.zzb<NodeApi.GetLocalNodeResult> paramzzb)
    {
      super();
    }
    
    public void zza(GetLocalNodeResponse paramGetLocalNodeResponse)
    {
      zzX(new zzbb.zzb(zzbk.zzgc(paramGetLocalNodeResponse.statusCode), paramGetLocalNodeResponse.zzbsL));
    }
  }
  
  static final class zzo
    extends zza
  {
    public void zza(Status paramStatus) {}
  }
  
  static final class zzp
    extends zzbo.zzb<ChannelApi.OpenChannelResult>
  {
    public zzp(zza.zzb<ChannelApi.OpenChannelResult> paramzzb)
    {
      super();
    }
    
    public void zza(OpenChannelResponse paramOpenChannelResponse)
    {
      zzX(new zzl.zza(zzbk.zzgc(paramOpenChannelResponse.statusCode), paramOpenChannelResponse.zzbsc));
    }
  }
  
  static final class zzq
    extends zzbo.zzb<DataApi.DataItemResult>
  {
    private final List<FutureTask<Boolean>> zzzM;
    
    zzq(zza.zzb<DataApi.DataItemResult> paramzzb, List<FutureTask<Boolean>> paramList)
    {
      super();
      this.zzzM = paramList;
    }
    
    public void zza(PutDataResponse paramPutDataResponse)
    {
      zzX(new zzx.zza(zzbk.zzgc(paramPutDataResponse.statusCode), paramPutDataResponse.zzbsJ));
      if (paramPutDataResponse.statusCode != 0)
      {
        paramPutDataResponse = this.zzzM.iterator();
        while (paramPutDataResponse.hasNext()) {
          ((FutureTask)paramPutDataResponse.next()).cancel(true);
        }
      }
    }
  }
  
  static final class zzr
    extends zzbo.zzb<Status>
  {
    public zzr(zza.zzb<Status> paramzzb)
    {
      super();
    }
    
    public void zza(ChannelSendFileResponse paramChannelSendFileResponse)
    {
      zzX(new Status(paramChannelSendFileResponse.statusCode));
    }
  }
  
  static final class zzs
    extends zzbo.zzb<CapabilityApi.RemoveLocalCapabilityResult>
  {
    public zzs(zza.zzb<CapabilityApi.RemoveLocalCapabilityResult> paramzzb)
    {
      super();
    }
    
    public void zza(RemoveLocalCapabilityResponse paramRemoveLocalCapabilityResponse)
    {
      zzX(new zzj.zza(zzbk.zzgc(paramRemoveLocalCapabilityResponse.statusCode)));
    }
  }
  
  static final class zzt
    extends zzbo.zzb<MessageApi.SendMessageResult>
  {
    public zzt(zza.zzb<MessageApi.SendMessageResult> paramzzb)
    {
      super();
    }
    
    public void zza(SendMessageResponse paramSendMessageResponse)
    {
      zzX(new zzaz.zzb(zzbk.zzgc(paramSendMessageResponse.statusCode), paramSendMessageResponse.zzaNj));
    }
  }
  
  static final class zzu
    extends zzbo.zzb<Status>
  {
    public zzu(zza.zzb<Status> paramzzb)
    {
      super();
    }
    
    public void zza(ChannelReceiveFileResponse paramChannelReceiveFileResponse)
    {
      zzX(new Status(paramChannelReceiveFileResponse.statusCode));
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\zzbo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */