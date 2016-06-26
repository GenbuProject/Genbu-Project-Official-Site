package com.google.android.gms.ads.internal.client;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.internal.zzhb;
import java.util.List;

@zzhb
public final class AdRequestParcel
  implements SafeParcelable
{
  public static final zzg CREATOR = new zzg();
  public final Bundle extras;
  public final int versionCode;
  public final long zztC;
  public final int zztD;
  public final List<String> zztE;
  public final boolean zztF;
  public final int zztG;
  public final boolean zztH;
  public final String zztI;
  public final SearchAdRequestParcel zztJ;
  public final Location zztK;
  public final String zztL;
  public final Bundle zztM;
  public final Bundle zztN;
  public final List<String> zztO;
  public final String zztP;
  public final String zztQ;
  public final boolean zztR;
  
  public AdRequestParcel(int paramInt1, long paramLong, Bundle paramBundle1, int paramInt2, List<String> paramList1, boolean paramBoolean1, int paramInt3, boolean paramBoolean2, String paramString1, SearchAdRequestParcel paramSearchAdRequestParcel, Location paramLocation, String paramString2, Bundle paramBundle2, Bundle paramBundle3, List<String> paramList2, String paramString3, String paramString4, boolean paramBoolean3)
  {
    this.versionCode = paramInt1;
    this.zztC = paramLong;
    Bundle localBundle = paramBundle1;
    if (paramBundle1 == null) {
      localBundle = new Bundle();
    }
    this.extras = localBundle;
    this.zztD = paramInt2;
    this.zztE = paramList1;
    this.zztF = paramBoolean1;
    this.zztG = paramInt3;
    this.zztH = paramBoolean2;
    this.zztI = paramString1;
    this.zztJ = paramSearchAdRequestParcel;
    this.zztK = paramLocation;
    this.zztL = paramString2;
    this.zztM = paramBundle2;
    this.zztN = paramBundle3;
    this.zztO = paramList2;
    this.zztP = paramString3;
    this.zztQ = paramString4;
    this.zztR = paramBoolean3;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof AdRequestParcel)) {}
    do
    {
      return false;
      paramObject = (AdRequestParcel)paramObject;
    } while ((this.versionCode != ((AdRequestParcel)paramObject).versionCode) || (this.zztC != ((AdRequestParcel)paramObject).zztC) || (!zzw.equal(this.extras, ((AdRequestParcel)paramObject).extras)) || (this.zztD != ((AdRequestParcel)paramObject).zztD) || (!zzw.equal(this.zztE, ((AdRequestParcel)paramObject).zztE)) || (this.zztF != ((AdRequestParcel)paramObject).zztF) || (this.zztG != ((AdRequestParcel)paramObject).zztG) || (this.zztH != ((AdRequestParcel)paramObject).zztH) || (!zzw.equal(this.zztI, ((AdRequestParcel)paramObject).zztI)) || (!zzw.equal(this.zztJ, ((AdRequestParcel)paramObject).zztJ)) || (!zzw.equal(this.zztK, ((AdRequestParcel)paramObject).zztK)) || (!zzw.equal(this.zztL, ((AdRequestParcel)paramObject).zztL)) || (!zzw.equal(this.zztM, ((AdRequestParcel)paramObject).zztM)) || (!zzw.equal(this.zztN, ((AdRequestParcel)paramObject).zztN)) || (!zzw.equal(this.zztO, ((AdRequestParcel)paramObject).zztO)) || (!zzw.equal(this.zztP, ((AdRequestParcel)paramObject).zztP)) || (!zzw.equal(this.zztQ, ((AdRequestParcel)paramObject).zztQ)) || (this.zztR != ((AdRequestParcel)paramObject).zztR));
    return true;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Integer.valueOf(this.versionCode), Long.valueOf(this.zztC), this.extras, Integer.valueOf(this.zztD), this.zztE, Boolean.valueOf(this.zztF), Integer.valueOf(this.zztG), Boolean.valueOf(this.zztH), this.zztI, this.zztJ, this.zztK, this.zztL, this.zztM, this.zztN, this.zztO, this.zztP, this.zztQ, Boolean.valueOf(this.zztR) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzg.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\client\AdRequestParcel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */