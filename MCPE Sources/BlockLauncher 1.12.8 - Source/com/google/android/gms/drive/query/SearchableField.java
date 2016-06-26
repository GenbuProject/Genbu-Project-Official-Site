package com.google.android.gms.drive.query;

import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.SearchableOrderedMetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.internal.zznm;
import com.google.android.gms.internal.zzno;
import java.util.Date;

public class SearchableField
{
  public static final SearchableMetadataField<Boolean> IS_PINNED = zznm.zzatj;
  public static final SearchableOrderedMetadataField<Date> LAST_VIEWED_BY_ME;
  public static final SearchableMetadataField<String> MIME_TYPE;
  public static final SearchableOrderedMetadataField<Date> MODIFIED_DATE;
  public static final SearchableCollectionMetadataField<DriveId> PARENTS;
  public static final SearchableMetadataField<Boolean> STARRED;
  public static final SearchableMetadataField<String> TITLE = zznm.zzatA;
  public static final SearchableMetadataField<Boolean> TRASHED;
  public static final SearchableOrderedMetadataField<Date> zzaub;
  public static final SearchableMetadataField<AppVisibleCustomProperties> zzauc = zznm.zzasW;
  
  static
  {
    MIME_TYPE = zznm.zzatr;
    TRASHED = zznm.zzatB;
    PARENTS = zznm.zzatw;
    zzaub = zzno.zzatQ;
    STARRED = zznm.zzaty;
    MODIFIED_DATE = zzno.zzatO;
    LAST_VIEWED_BY_ME = zzno.zzatN;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\query\SearchableField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */