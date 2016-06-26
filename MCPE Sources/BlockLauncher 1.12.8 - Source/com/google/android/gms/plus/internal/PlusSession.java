package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import java.util.Arrays;

public class PlusSession
  implements SafeParcelable
{
  public static final zzh CREATOR = new zzh();
  private final int mVersionCode;
  private final String zzVa;
  private final String zzada;
  private final String[] zzber;
  private final String[] zzbes;
  private final String[] zzbet;
  private final String zzbeu;
  private final String zzbev;
  private final String zzbew;
  private final PlusCommonExtras zzbex;
  
  PlusSession(int paramInt, String paramString1, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String paramString2, String paramString3, String paramString4, String paramString5, PlusCommonExtras paramPlusCommonExtras)
  {
    this.mVersionCode = paramInt;
    this.zzVa = paramString1;
    this.zzber = paramArrayOfString1;
    this.zzbes = paramArrayOfString2;
    this.zzbet = paramArrayOfString3;
    this.zzbeu = paramString2;
    this.zzbev = paramString3;
    this.zzada = paramString4;
    this.zzbew = paramString5;
    this.zzbex = paramPlusCommonExtras;
  }
  
  public PlusSession(String paramString1, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String paramString2, String paramString3, String paramString4, PlusCommonExtras paramPlusCommonExtras)
  {
    this.mVersionCode = 1;
    this.zzVa = paramString1;
    this.zzber = paramArrayOfString1;
    this.zzbes = paramArrayOfString2;
    this.zzbet = paramArrayOfString3;
    this.zzbeu = paramString2;
    this.zzbev = paramString3;
    this.zzada = paramString4;
    this.zzbew = null;
    this.zzbex = paramPlusCommonExtras;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof PlusSession)) {}
    do
    {
      return false;
      paramObject = (PlusSession)paramObject;
    } while ((this.mVersionCode != ((PlusSession)paramObject).mVersionCode) || (!zzw.equal(this.zzVa, ((PlusSession)paramObject).zzVa)) || (!Arrays.equals(this.zzber, ((PlusSession)paramObject).zzber)) || (!Arrays.equals(this.zzbes, ((PlusSession)paramObject).zzbes)) || (!Arrays.equals(this.zzbet, ((PlusSession)paramObject).zzbet)) || (!zzw.equal(this.zzbeu, ((PlusSession)paramObject).zzbeu)) || (!zzw.equal(this.zzbev, ((PlusSession)paramObject).zzbev)) || (!zzw.equal(this.zzada, ((PlusSession)paramObject).zzada)) || (!zzw.equal(this.zzbew, ((PlusSession)paramObject).zzbew)) || (!zzw.equal(this.zzbex, ((PlusSession)paramObject).zzbex)));
    return true;
  }
  
  public String getAccountName()
  {
    return this.zzVa;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Integer.valueOf(this.mVersionCode), this.zzVa, this.zzber, this.zzbes, this.zzbet, this.zzbeu, this.zzbev, this.zzada, this.zzbew, this.zzbex });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("versionCode", Integer.valueOf(this.mVersionCode)).zzg("accountName", this.zzVa).zzg("requestedScopes", this.zzber).zzg("visibleActivities", this.zzbes).zzg("requiredFeatures", this.zzbet).zzg("packageNameForAuth", this.zzbeu).zzg("callingPackageName", this.zzbev).zzg("applicationName", this.zzada).zzg("extra", this.zzbex.toString()).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzh.zza(this, paramParcel, paramInt);
  }
  
  public String[] zzFd()
  {
    return this.zzber;
  }
  
  public String[] zzFe()
  {
    return this.zzbes;
  }
  
  public String[] zzFf()
  {
    return this.zzbet;
  }
  
  public String zzFg()
  {
    return this.zzbeu;
  }
  
  public String zzFh()
  {
    return this.zzbev;
  }
  
  public String zzFi()
  {
    return this.zzbew;
  }
  
  public PlusCommonExtras zzFj()
  {
    return this.zzbex;
  }
  
  public Bundle zzFk()
  {
    Bundle localBundle = new Bundle();
    localBundle.setClassLoader(PlusCommonExtras.class.getClassLoader());
    this.zzbex.zzJ(localBundle);
    return localBundle;
  }
  
  public String zznX()
  {
    return this.zzada;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\plus\internal\PlusSession.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */