package com.google.android.gms.appdatasearch;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.appindexing.AppIndexApi.AppIndexingLink;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.zzpm.zza;
import com.google.android.gms.internal.zzpm.zza.zza;
import com.google.android.gms.internal.zzsu;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.zip.CRC32;

public class UsageInfo
  implements SafeParcelable
{
  public static final zzj CREATOR = new zzj();
  final int mVersionCode;
  final DocumentId zzUs;
  final long zzUt;
  int zzUu;
  final DocumentContents zzUv;
  final boolean zzUw;
  int zzUx;
  int zzUy;
  public final String zzvp;
  
  UsageInfo(int paramInt1, DocumentId paramDocumentId, long paramLong, int paramInt2, String paramString, DocumentContents paramDocumentContents, boolean paramBoolean, int paramInt3, int paramInt4)
  {
    this.mVersionCode = paramInt1;
    this.zzUs = paramDocumentId;
    this.zzUt = paramLong;
    this.zzUu = paramInt2;
    this.zzvp = paramString;
    this.zzUv = paramDocumentContents;
    this.zzUw = paramBoolean;
    this.zzUx = paramInt3;
    this.zzUy = paramInt4;
  }
  
  private UsageInfo(DocumentId paramDocumentId, long paramLong, int paramInt1, String paramString, DocumentContents paramDocumentContents, boolean paramBoolean, int paramInt2, int paramInt3)
  {
    this(1, paramDocumentId, paramLong, paramInt1, paramString, paramDocumentContents, paramBoolean, paramInt2, paramInt3);
  }
  
  public UsageInfo(String paramString1, Intent paramIntent, String paramString2, Uri paramUri, String paramString3, List<AppIndexApi.AppIndexingLink> paramList, int paramInt)
  {
    this(1, zza(paramString1, paramIntent), System.currentTimeMillis(), 0, null, zza(paramIntent, paramString2, paramUri, paramString3, paramList).zzme(), false, -1, paramInt);
  }
  
  public static DocumentContents.zza zza(Intent paramIntent, String paramString1, Uri paramUri, String paramString2, List<AppIndexApi.AppIndexingLink> paramList)
  {
    DocumentContents.zza localzza = new DocumentContents.zza();
    localzza.zza(zzbD(paramString1));
    if (paramUri != null) {
      localzza.zza(zzi(paramUri));
    }
    if (paramList != null) {
      localzza.zza(zzs(paramList));
    }
    paramString1 = paramIntent.getAction();
    if (paramString1 != null) {
      localzza.zza(zzo("intent_action", paramString1));
    }
    paramString1 = paramIntent.getDataString();
    if (paramString1 != null) {
      localzza.zza(zzo("intent_data", paramString1));
    }
    paramString1 = paramIntent.getComponent();
    if (paramString1 != null) {
      localzza.zza(zzo("intent_activity", paramString1.getClassName()));
    }
    paramIntent = paramIntent.getExtras();
    if (paramIntent != null)
    {
      paramIntent = paramIntent.getString("intent_extra_data_key");
      if (paramIntent != null) {
        localzza.zza(zzo("intent_extra_data", paramIntent));
      }
    }
    return localzza.zzbz(paramString2).zzL(true);
  }
  
  public static DocumentId zza(String paramString, Intent paramIntent)
  {
    return zzn(paramString, zzg(paramIntent));
  }
  
  private static DocumentSection zzbD(String paramString)
  {
    return new DocumentSection(paramString, new RegisterSectionInfo.zza("title").zzap(1).zzN(true).zzbC("name").zzmh(), "text1");
  }
  
  private static String zzg(Intent paramIntent)
  {
    paramIntent = paramIntent.toUri(1);
    CRC32 localCRC32 = new CRC32();
    try
    {
      localCRC32.update(paramIntent.getBytes("UTF-8"));
      return Long.toHexString(localCRC32.getValue());
    }
    catch (UnsupportedEncodingException paramIntent)
    {
      throw new IllegalStateException(paramIntent);
    }
  }
  
  private static DocumentSection zzi(Uri paramUri)
  {
    return new DocumentSection(paramUri.toString(), new RegisterSectionInfo.zza("web_url").zzap(4).zzM(true).zzbC("url").zzmh());
  }
  
  private static DocumentId zzn(String paramString1, String paramString2)
  {
    return new DocumentId(paramString1, "", paramString2);
  }
  
  private static DocumentSection zzo(String paramString1, String paramString2)
  {
    return new DocumentSection(paramString2, new RegisterSectionInfo.zza(paramString1).zzM(true).zzmh(), paramString1);
  }
  
  private static DocumentSection zzs(List<AppIndexApi.AppIndexingLink> paramList)
  {
    zzpm.zza localzza = new zzpm.zza();
    zzpm.zza.zza[] arrayOfzza = new zzpm.zza.zza[paramList.size()];
    int i = 0;
    while (i < arrayOfzza.length)
    {
      arrayOfzza[i] = new zzpm.zza.zza();
      AppIndexApi.AppIndexingLink localAppIndexingLink = (AppIndexApi.AppIndexingLink)paramList.get(i);
      arrayOfzza[i].zzaMq = localAppIndexingLink.appIndexingUrl.toString();
      arrayOfzza[i].viewId = localAppIndexingLink.viewId;
      if (localAppIndexingLink.webUrl != null) {
        arrayOfzza[i].zzaMr = localAppIndexingLink.webUrl.toString();
      }
      i += 1;
    }
    localzza.zzaMo = arrayOfzza;
    return new DocumentSection(zzsu.toByteArray(localzza), new RegisterSectionInfo.zza("outlinks").zzM(true).zzbC(".private:outLinks").zzbB("blob").zzmh());
  }
  
  public int describeContents()
  {
    zzj localzzj = CREATOR;
    return 0;
  }
  
  public String toString()
  {
    return String.format("UsageInfo[documentId=%s, timestamp=%d, usageType=%d, status=%d]", new Object[] { this.zzUs, Long.valueOf(this.zzUt), Integer.valueOf(this.zzUu), Integer.valueOf(this.zzUy) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzj localzzj = CREATOR;
    zzj.zza(this, paramParcel, paramInt);
  }
  
  public static final class zza
  {
    private String zzOJ;
    private DocumentId zzUs;
    private long zzUt = -1L;
    private int zzUu = -1;
    private DocumentContents zzUv;
    private boolean zzUw = false;
    private int zzUx = -1;
    private int zzUy = 0;
    
    public zza zzO(boolean paramBoolean)
    {
      this.zzUw = paramBoolean;
      return this;
    }
    
    public zza zza(DocumentContents paramDocumentContents)
    {
      this.zzUv = paramDocumentContents;
      return this;
    }
    
    public zza zza(DocumentId paramDocumentId)
    {
      this.zzUs = paramDocumentId;
      return this;
    }
    
    public zza zzar(int paramInt)
    {
      this.zzUu = paramInt;
      return this;
    }
    
    public zza zzas(int paramInt)
    {
      this.zzUy = paramInt;
      return this;
    }
    
    public UsageInfo zzmi()
    {
      return new UsageInfo(this.zzUs, this.zzUt, this.zzUu, this.zzOJ, this.zzUv, this.zzUw, this.zzUx, this.zzUy, null);
    }
    
    public zza zzw(long paramLong)
    {
      this.zzUt = paramLong;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\appdatasearch\UsageInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */