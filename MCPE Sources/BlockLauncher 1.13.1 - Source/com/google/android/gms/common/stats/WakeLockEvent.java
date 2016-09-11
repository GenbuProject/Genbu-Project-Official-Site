package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public final class WakeLockEvent
  extends zzf
  implements SafeParcelable
{
  public static final Parcelable.Creator<WakeLockEvent> CREATOR = new zzh();
  private final long mTimeout;
  final int mVersionCode;
  private final String zzanQ;
  private final int zzanR;
  private final List<String> zzanS;
  private final String zzanT;
  private int zzanU;
  private final String zzanV;
  private final String zzanW;
  private final float zzanX;
  private final long zzane;
  private int zzanf;
  private final long zzanm;
  private long zzano;
  
  WakeLockEvent(int paramInt1, long paramLong1, int paramInt2, String paramString1, int paramInt3, List<String> paramList, String paramString2, long paramLong2, int paramInt4, String paramString3, String paramString4, float paramFloat, long paramLong3)
  {
    this.mVersionCode = paramInt1;
    this.zzane = paramLong1;
    this.zzanf = paramInt2;
    this.zzanQ = paramString1;
    this.zzanV = paramString3;
    this.zzanR = paramInt3;
    this.zzano = -1L;
    this.zzanS = paramList;
    this.zzanT = paramString2;
    this.zzanm = paramLong2;
    this.zzanU = paramInt4;
    this.zzanW = paramString4;
    this.zzanX = paramFloat;
    this.mTimeout = paramLong3;
  }
  
  public WakeLockEvent(long paramLong1, int paramInt1, String paramString1, int paramInt2, List<String> paramList, String paramString2, long paramLong2, int paramInt3, String paramString3, String paramString4, float paramFloat, long paramLong3)
  {
    this(1, paramLong1, paramInt1, paramString1, paramInt2, paramList, paramString2, paramLong2, paramInt3, paramString3, paramString4, paramFloat, paramLong3);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getEventType()
  {
    return this.zzanf;
  }
  
  public long getTimeMillis()
  {
    return this.zzane;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzh.zza(this, paramParcel, paramInt);
  }
  
  public String zzrK()
  {
    return this.zzanT;
  }
  
  public long zzrL()
  {
    return this.zzano;
  }
  
  public long zzrN()
  {
    return this.zzanm;
  }
  
  public String zzrO()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("\t").append(zzrR()).append("\t").append(zzrT()).append("\t");
    if (zzrU() == null)
    {
      str = "";
      localStringBuilder = localStringBuilder.append(str).append("\t").append(zzrV()).append("\t");
      if (zzrS() != null) {
        break label135;
      }
      str = "";
      label80:
      localStringBuilder = localStringBuilder.append(str).append("\t");
      if (zzrW() != null) {
        break label143;
      }
    }
    label135:
    label143:
    for (String str = "";; str = zzrW())
    {
      return str + "\t" + zzrX();
      str = TextUtils.join(",", zzrU());
      break;
      str = zzrS();
      break label80;
    }
  }
  
  public String zzrR()
  {
    return this.zzanQ;
  }
  
  public String zzrS()
  {
    return this.zzanV;
  }
  
  public int zzrT()
  {
    return this.zzanR;
  }
  
  public List<String> zzrU()
  {
    return this.zzanS;
  }
  
  public int zzrV()
  {
    return this.zzanU;
  }
  
  public String zzrW()
  {
    return this.zzanW;
  }
  
  public float zzrX()
  {
    return this.zzanX;
  }
  
  public long zzrY()
  {
    return this.mTimeout;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\common\stats\WakeLockEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */