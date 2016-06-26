package com.google.android.gms.ads.internal.request;

import android.os.Parcel;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.zzhb;
import java.util.Collections;
import java.util.List;

@zzhb
public final class AdResponseParcel
  implements SafeParcelable
{
  public static final zzh CREATOR = new zzh();
  public String body;
  public final int errorCode;
  public final int orientation;
  public final int versionCode;
  public final List<String> zzBQ;
  public final List<String> zzBR;
  public final long zzBU;
  private AdRequestInfoParcel zzCu;
  public final String zzEF;
  public final boolean zzHB;
  public final long zzHS;
  public final boolean zzHT;
  public final long zzHU;
  public final List<String> zzHV;
  public final String zzHW;
  public final long zzHX;
  public final String zzHY;
  public final boolean zzHZ;
  public final String zzIa;
  public final String zzIb;
  public final boolean zzIc;
  public final boolean zzId;
  public final boolean zzIe;
  public final int zzIf;
  public LargeParcelTeleporter zzIg;
  public String zzIh;
  public String zzIi;
  @Nullable
  public RewardItemParcel zzIj;
  @Nullable
  public List<String> zzIk;
  @Nullable
  public List<String> zzIl;
  @Nullable
  public boolean zzIm;
  public final boolean zzuk;
  public boolean zzul;
  public boolean zzum;
  
  public AdResponseParcel(int paramInt)
  {
    this(16, null, null, null, paramInt, null, -1L, false, -1L, null, -1L, -1, null, -1L, null, false, null, null, false, false, false, true, false, 0, null, null, null, false, false, null, null, null, false);
  }
  
  public AdResponseParcel(int paramInt, long paramLong)
  {
    this(16, null, null, null, paramInt, null, -1L, false, -1L, null, paramLong, -1, null, -1L, null, false, null, null, false, false, false, true, false, 0, null, null, null, false, false, null, null, null, false);
  }
  
  AdResponseParcel(int paramInt1, String paramString1, String paramString2, List<String> paramList1, int paramInt2, List<String> paramList2, long paramLong1, boolean paramBoolean1, long paramLong2, List<String> paramList3, long paramLong3, int paramInt3, String paramString3, long paramLong4, String paramString4, boolean paramBoolean2, String paramString5, String paramString6, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, boolean paramBoolean7, int paramInt4, LargeParcelTeleporter paramLargeParcelTeleporter, String paramString7, String paramString8, boolean paramBoolean8, boolean paramBoolean9, RewardItemParcel paramRewardItemParcel, List<String> paramList4, List<String> paramList5, boolean paramBoolean10)
  {
    this.versionCode = paramInt1;
    this.zzEF = paramString1;
    this.body = paramString2;
    if (paramList1 != null)
    {
      paramString1 = Collections.unmodifiableList(paramList1);
      this.zzBQ = paramString1;
      this.errorCode = paramInt2;
      if (paramList2 == null) {
        break label285;
      }
      paramString1 = Collections.unmodifiableList(paramList2);
      label52:
      this.zzBR = paramString1;
      this.zzHS = paramLong1;
      this.zzHT = paramBoolean1;
      this.zzHU = paramLong2;
      if (paramList3 == null) {
        break label290;
      }
    }
    label285:
    label290:
    for (paramString1 = Collections.unmodifiableList(paramList3);; paramString1 = null)
    {
      this.zzHV = paramString1;
      this.zzBU = paramLong3;
      this.orientation = paramInt3;
      this.zzHW = paramString3;
      this.zzHX = paramLong4;
      this.zzHY = paramString4;
      this.zzHZ = paramBoolean2;
      this.zzIa = paramString5;
      this.zzIb = paramString6;
      this.zzIc = paramBoolean3;
      this.zzuk = paramBoolean4;
      this.zzHB = paramBoolean5;
      this.zzId = paramBoolean6;
      this.zzIe = paramBoolean7;
      this.zzIf = paramInt4;
      this.zzIg = paramLargeParcelTeleporter;
      this.zzIh = paramString7;
      this.zzIi = paramString8;
      if ((this.body == null) && (this.zzIg != null))
      {
        paramString1 = (StringParcel)this.zzIg.zza(StringParcel.CREATOR);
        if ((paramString1 != null) && (!TextUtils.isEmpty(paramString1.zzgz()))) {
          this.body = paramString1.zzgz();
        }
      }
      this.zzul = paramBoolean8;
      this.zzum = paramBoolean9;
      this.zzIj = paramRewardItemParcel;
      this.zzIk = paramList4;
      this.zzIl = paramList5;
      this.zzIm = paramBoolean10;
      return;
      paramString1 = null;
      break;
      paramString1 = null;
      break label52;
    }
  }
  
  public AdResponseParcel(AdRequestInfoParcel paramAdRequestInfoParcel, String paramString1, String paramString2, List<String> paramList1, List<String> paramList2, long paramLong1, boolean paramBoolean1, long paramLong2, List<String> paramList3, long paramLong3, int paramInt1, String paramString3, long paramLong4, String paramString4, String paramString5, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, int paramInt2, String paramString6, boolean paramBoolean7, boolean paramBoolean8, RewardItemParcel paramRewardItemParcel, List<String> paramList4, List<String> paramList5, boolean paramBoolean9)
  {
    this(16, paramString1, paramString2, paramList1, -2, paramList2, paramLong1, paramBoolean1, paramLong2, paramList3, paramLong3, paramInt1, paramString3, paramLong4, paramString4, false, null, paramString5, paramBoolean2, paramBoolean3, paramBoolean4, paramBoolean5, paramBoolean6, paramInt2, null, null, paramString6, paramBoolean7, paramBoolean8, paramRewardItemParcel, paramList4, paramList5, paramBoolean9);
    this.zzCu = paramAdRequestInfoParcel;
  }
  
  public AdResponseParcel(AdRequestInfoParcel paramAdRequestInfoParcel, String paramString1, String paramString2, List<String> paramList1, List<String> paramList2, long paramLong1, boolean paramBoolean1, long paramLong2, List<String> paramList3, long paramLong3, int paramInt1, String paramString3, long paramLong4, String paramString4, boolean paramBoolean2, String paramString5, String paramString6, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, boolean paramBoolean7, int paramInt2, String paramString7, boolean paramBoolean8, boolean paramBoolean9, RewardItemParcel paramRewardItemParcel, List<String> paramList4, List<String> paramList5, boolean paramBoolean10)
  {
    this(16, paramString1, paramString2, paramList1, -2, paramList2, paramLong1, paramBoolean1, paramLong2, paramList3, paramLong3, paramInt1, paramString3, paramLong4, paramString4, paramBoolean2, paramString5, paramString6, paramBoolean3, paramBoolean4, paramBoolean5, paramBoolean6, paramBoolean7, paramInt2, null, null, paramString7, paramBoolean8, paramBoolean9, paramRewardItemParcel, paramList4, paramList5, paramBoolean10);
    this.zzCu = paramAdRequestInfoParcel;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if ((this.zzCu != null) && (this.zzCu.versionCode >= 9) && (!TextUtils.isEmpty(this.body)))
    {
      this.zzIg = new LargeParcelTeleporter(new StringParcel(this.body));
      this.body = null;
    }
    zzh.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\request\AdResponseParcel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */