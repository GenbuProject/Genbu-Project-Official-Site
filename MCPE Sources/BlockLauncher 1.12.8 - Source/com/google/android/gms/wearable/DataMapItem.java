package com.google.android.gms.wearable;

import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.internal.zzsi;
import com.google.android.gms.internal.zzsi.zza;
import com.google.android.gms.internal.zzsj;
import com.google.android.gms.internal.zzst;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataMapItem
{
  private final Uri mUri;
  private final DataMap zzbrd;
  
  private DataMapItem(DataItem paramDataItem)
  {
    this.mUri = paramDataItem.getUri();
    this.zzbrd = zza((DataItem)paramDataItem.freeze());
  }
  
  public static DataMapItem fromDataItem(DataItem paramDataItem)
  {
    if (paramDataItem == null) {
      throw new IllegalStateException("provided dataItem is null");
    }
    return new DataMapItem(paramDataItem);
  }
  
  private DataMap zza(DataItem paramDataItem)
  {
    if ((paramDataItem.getData() == null) && (paramDataItem.getAssets().size() > 0)) {
      throw new IllegalArgumentException("Cannot create DataMapItem from a DataItem  that wasn't made with DataMapItem.");
    }
    if (paramDataItem.getData() == null) {
      return new DataMap();
    }
    try
    {
      ArrayList localArrayList = new ArrayList();
      j = paramDataItem.getAssets().size();
      i = 0;
    }
    catch (zzst localzzst)
    {
      for (;;)
      {
        int j;
        int i;
        DataItemAsset localDataItemAsset;
        Log.w("DataItem", "Unable to parse datamap from dataItem. uri=" + paramDataItem.getUri() + ", data=" + Base64.encodeToString(paramDataItem.getData(), 0));
        throw new IllegalStateException("Unable to parse datamap from dataItem.  uri=" + paramDataItem.getUri(), localzzst);
        localzzst.add(Asset.createFromRef(localDataItemAsset.getId()));
        i += 1;
      }
      DataMap localDataMap = zzsi.zza(new zzsi.zza(zzsj.zzA(paramDataItem.getData()), localzzst));
      return localDataMap;
    }
    catch (NullPointerException localNullPointerException)
    {
      for (;;) {}
    }
    if (i < j)
    {
      localDataItemAsset = (DataItemAsset)paramDataItem.getAssets().get(Integer.toString(i));
      if (localDataItemAsset == null) {
        throw new IllegalStateException("Cannot find DataItemAsset referenced in data at " + i + " for " + paramDataItem);
      }
    }
  }
  
  public DataMap getDataMap()
  {
    return this.zzbrd;
  }
  
  public Uri getUri()
  {
    return this.mUri;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\DataMapItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */