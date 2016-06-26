package com.google.android.gms.wearable;

import android.net.Uri;
import android.util.Log;
import com.google.android.gms.internal.zzsi;
import com.google.android.gms.internal.zzsi.zza;
import com.google.android.gms.internal.zzsu;
import java.util.List;

public class PutDataMapRequest
{
  private final DataMap zzbrd;
  private final PutDataRequest zzbre;
  
  private PutDataMapRequest(PutDataRequest paramPutDataRequest, DataMap paramDataMap)
  {
    this.zzbre = paramPutDataRequest;
    this.zzbrd = new DataMap();
    if (paramDataMap != null) {
      this.zzbrd.putAll(paramDataMap);
    }
  }
  
  public static PutDataMapRequest create(String paramString)
  {
    return new PutDataMapRequest(PutDataRequest.create(paramString), null);
  }
  
  public static PutDataMapRequest createFromDataMapItem(DataMapItem paramDataMapItem)
  {
    return new PutDataMapRequest(PutDataRequest.zzr(paramDataMapItem.getUri()), paramDataMapItem.getDataMap());
  }
  
  public static PutDataMapRequest createWithAutoAppendedId(String paramString)
  {
    return new PutDataMapRequest(PutDataRequest.createWithAutoAppendedId(paramString), null);
  }
  
  public PutDataRequest asPutDataRequest()
  {
    zzsi.zza localzza = zzsi.zza(this.zzbrd);
    this.zzbre.setData(zzsu.toByteArray(localzza.zzbty));
    int j = localzza.zzbtz.size();
    int i = 0;
    while (i < j)
    {
      String str = Integer.toString(i);
      Asset localAsset = (Asset)localzza.zzbtz.get(i);
      if (str == null) {
        throw new IllegalStateException("asset key cannot be null: " + localAsset);
      }
      if (localAsset == null) {
        throw new IllegalStateException("asset cannot be null: key=" + str);
      }
      if (Log.isLoggable("DataMap", 3)) {
        Log.d("DataMap", "asPutDataRequest: adding asset: " + str + " " + localAsset);
      }
      this.zzbre.putAsset(str, localAsset);
      i += 1;
    }
    return this.zzbre;
  }
  
  public DataMap getDataMap()
  {
    return this.zzbrd;
  }
  
  public Uri getUri()
  {
    return this.zzbre.getUri();
  }
  
  public boolean isUrgent()
  {
    return this.zzbre.isUrgent();
  }
  
  public PutDataMapRequest setUrgent()
  {
    this.zzbre.setUrgent();
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\PutDataMapRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */