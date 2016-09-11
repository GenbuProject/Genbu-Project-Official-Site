package com.google.android.gms.internal;

import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveSpace;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.internal.zzg;
import com.google.android.gms.drive.metadata.internal.zzj;
import com.google.android.gms.drive.metadata.internal.zzk;
import com.google.android.gms.drive.metadata.internal.zzm;
import com.google.android.gms.drive.metadata.internal.zzo;
import com.google.android.gms.drive.metadata.internal.zzp;
import com.google.android.gms.drive.metadata.internal.zzq;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class zznm
{
  public static final MetadataField<DriveId> zzasU = zznp.zzatS;
  public static final MetadataField<String> zzasV = new zzp("alternateLink", 4300000);
  public static final zza zzasW = new zza(5000000);
  public static final MetadataField<String> zzasX = new zzp("description", 4300000);
  public static final MetadataField<String> zzasY = new zzp("embedLink", 4300000);
  public static final MetadataField<String> zzasZ = new zzp("fileExtension", 4300000);
  public static final zzg zzatA = new zzg("title", 4100000);
  public static final zzh zzatB = new zzh("trashed", 4100000);
  public static final MetadataField<String> zzatC = new zzp("webContentLink", 4300000);
  public static final MetadataField<String> zzatD = new zzp("webViewLink", 4300000);
  public static final MetadataField<String> zzatE = new zzp("uniqueIdentifier", 5000000);
  public static final com.google.android.gms.drive.metadata.internal.zzb zzatF = new com.google.android.gms.drive.metadata.internal.zzb("writersCanShare", 6000000);
  public static final MetadataField<String> zzatG = new zzp("role", 6000000);
  public static final MetadataField<String> zzatH = new zzp("md5Checksum", 7000000);
  public static final zze zzatI = new zze(7000000);
  public static final MetadataField<String> zzatJ = new zzp("recencyReason", 8000000);
  public static final MetadataField<Boolean> zzatK = new com.google.android.gms.drive.metadata.internal.zzb("subscribed", 8000000);
  public static final MetadataField<Long> zzata = new zzg("fileSize", 4300000);
  public static final MetadataField<String> zzatb = new zzp("folderColorRgb", 7500000);
  public static final MetadataField<Boolean> zzatc = new com.google.android.gms.drive.metadata.internal.zzb("hasThumbnail", 4300000);
  public static final MetadataField<String> zzatd = new zzp("indexableText", 4300000);
  public static final MetadataField<Boolean> zzate = new com.google.android.gms.drive.metadata.internal.zzb("isAppData", 4300000);
  public static final MetadataField<Boolean> zzatf = new com.google.android.gms.drive.metadata.internal.zzb("isCopyable", 4300000);
  public static final MetadataField<Boolean> zzatg = new com.google.android.gms.drive.metadata.internal.zzb("isEditable", 4100000);
  public static final MetadataField<Boolean> zzath = new com.google.android.gms.drive.metadata.internal.zzb("isExplicitlyTrashed", Collections.singleton("trashed"), Collections.emptySet(), 7000000)
  {
    protected Boolean zze(DataHolder paramAnonymousDataHolder, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      if (paramAnonymousDataHolder.zzc("trashed", paramAnonymousInt1, paramAnonymousInt2) == 2) {}
      for (boolean bool = true;; bool = false) {
        return Boolean.valueOf(bool);
      }
    }
  };
  public static final MetadataField<Boolean> zzati = new com.google.android.gms.drive.metadata.internal.zzb("isLocalContentUpToDate", 7800000);
  public static final zzb zzatj = new zzb("isPinned", 4100000);
  public static final MetadataField<Boolean> zzatk = new com.google.android.gms.drive.metadata.internal.zzb("isOpenable", 7200000);
  public static final MetadataField<Boolean> zzatl = new com.google.android.gms.drive.metadata.internal.zzb("isRestricted", 4300000);
  public static final MetadataField<Boolean> zzatm = new com.google.android.gms.drive.metadata.internal.zzb("isShared", 4300000);
  public static final MetadataField<Boolean> zzatn = new com.google.android.gms.drive.metadata.internal.zzb("isGooglePhotosFolder", 7000000);
  public static final MetadataField<Boolean> zzato = new com.google.android.gms.drive.metadata.internal.zzb("isGooglePhotosRootFolder", 7000000);
  public static final MetadataField<Boolean> zzatp = new com.google.android.gms.drive.metadata.internal.zzb("isTrashable", 4400000);
  public static final MetadataField<Boolean> zzatq = new com.google.android.gms.drive.metadata.internal.zzb("isViewed", 4300000);
  public static final zzc zzatr = new zzc(4100000);
  public static final MetadataField<String> zzats = new zzp("originalFilename", 4300000);
  public static final com.google.android.gms.drive.metadata.zzb<String> zzatt = new zzo("ownerNames", 4300000);
  public static final zzq zzatu = new zzq("lastModifyingUser", 6000000);
  public static final zzq zzatv = new zzq("sharingUser", 6000000);
  public static final zzm zzatw = new zzm(4100000);
  public static final zzd zzatx = new zzd("quotaBytesUsed", 4300000);
  public static final zzf zzaty = new zzf("starred", 4100000);
  public static final MetadataField<BitmapTeleporter> zzatz = new zzk("thumbnail", Collections.emptySet(), Collections.emptySet(), 4400000)
  {
    protected BitmapTeleporter zzk(DataHolder paramAnonymousDataHolder, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      throw new IllegalStateException("Thumbnail field is write only");
    }
  };
  
  public static class zza
    extends zznn
    implements SearchableMetadataField<AppVisibleCustomProperties>
  {
    public zza(int paramInt)
    {
      super();
    }
  }
  
  public static class zzb
    extends com.google.android.gms.drive.metadata.internal.zzb
    implements SearchableMetadataField<Boolean>
  {
    public zzb(String paramString, int paramInt)
    {
      super(paramInt);
    }
  }
  
  public static class zzc
    extends zzp
    implements SearchableMetadataField<String>
  {
    public zzc(int paramInt)
    {
      super(paramInt);
    }
  }
  
  public static class zzd
    extends zzg
    implements SortableMetadataField<Long>
  {
    public zzd(String paramString, int paramInt)
    {
      super(paramInt);
    }
  }
  
  public static class zze
    extends zzj<DriveSpace>
  {
    public zze(int paramInt)
    {
      super(Arrays.asList(new String[] { "inDriveSpace", "isAppData", "inGooglePhotosSpace" }), Collections.emptySet(), paramInt);
    }
    
    protected Collection<DriveSpace> zzd(DataHolder paramDataHolder, int paramInt1, int paramInt2)
    {
      ArrayList localArrayList = new ArrayList();
      if (paramDataHolder.zze("inDriveSpace", paramInt1, paramInt2)) {
        localArrayList.add(DriveSpace.zzaoP);
      }
      if (paramDataHolder.zze("isAppData", paramInt1, paramInt2)) {
        localArrayList.add(DriveSpace.zzaoQ);
      }
      if (paramDataHolder.zze("inGooglePhotosSpace", paramInt1, paramInt2)) {
        localArrayList.add(DriveSpace.zzaoR);
      }
      return localArrayList;
    }
  }
  
  public static class zzf
    extends com.google.android.gms.drive.metadata.internal.zzb
    implements SearchableMetadataField<Boolean>
  {
    public zzf(String paramString, int paramInt)
    {
      super(paramInt);
    }
  }
  
  public static class zzg
    extends zzp
    implements SearchableMetadataField<String>, SortableMetadataField<String>
  {
    public zzg(String paramString, int paramInt)
    {
      super(paramInt);
    }
  }
  
  public static class zzh
    extends com.google.android.gms.drive.metadata.internal.zzb
    implements SearchableMetadataField<Boolean>
  {
    public zzh(String paramString, int paramInt)
    {
      super(paramInt);
    }
    
    protected Boolean zze(DataHolder paramDataHolder, int paramInt1, int paramInt2)
    {
      if (paramDataHolder.zzc(getName(), paramInt1, paramInt2) != 0) {}
      for (boolean bool = true;; bool = false) {
        return Boolean.valueOf(bool);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zznm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */