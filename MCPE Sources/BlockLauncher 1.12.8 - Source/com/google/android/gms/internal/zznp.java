package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.zzk;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class zznp
  extends zzk<DriveId>
{
  public static final zznp zzatS = new zznp();
  
  private zznp()
  {
    super("driveId", Arrays.asList(new String[] { "sqlId", "resourceId", "mimeType" }), Arrays.asList(new String[] { "dbInstanceId" }), 4100000);
  }
  
  protected boolean zzb(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    Iterator localIterator = zzty().iterator();
    while (localIterator.hasNext()) {
      if (!paramDataHolder.zzcz((String)localIterator.next())) {
        return false;
      }
    }
    return true;
  }
  
  protected DriveId zzm(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    long l1 = paramDataHolder.zzpZ().getLong("dbInstanceId");
    if ("application/vnd.google-apps.folder".equals(paramDataHolder.zzd(zznm.zzatr.getName(), paramInt1, paramInt2))) {}
    for (int i = 1;; i = 0)
    {
      String str = paramDataHolder.zzd("resourceId", paramInt1, paramInt2);
      long l2 = paramDataHolder.zzb("sqlId", paramInt1, paramInt2);
      paramDataHolder = str;
      if ("generated-android-null".equals(str)) {
        paramDataHolder = null;
      }
      return new DriveId(paramDataHolder, Long.valueOf(l2).longValue(), l1, i);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zznp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */