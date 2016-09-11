package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public class FACLConfig
  implements SafeParcelable
{
  public static final zza CREATOR = new zza();
  final int version;
  boolean zzYm;
  String zzYn;
  boolean zzYo;
  boolean zzYp;
  boolean zzYq;
  boolean zzYr;
  
  FACLConfig(int paramInt, boolean paramBoolean1, String paramString, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5)
  {
    this.version = paramInt;
    this.zzYm = paramBoolean1;
    this.zzYn = paramString;
    this.zzYo = paramBoolean2;
    this.zzYp = paramBoolean3;
    this.zzYq = paramBoolean4;
    this.zzYr = paramBoolean5;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof FACLConfig))
    {
      paramObject = (FACLConfig)paramObject;
      bool1 = bool2;
      if (this.zzYm == ((FACLConfig)paramObject).zzYm)
      {
        bool1 = bool2;
        if (TextUtils.equals(this.zzYn, ((FACLConfig)paramObject).zzYn))
        {
          bool1 = bool2;
          if (this.zzYo == ((FACLConfig)paramObject).zzYo)
          {
            bool1 = bool2;
            if (this.zzYp == ((FACLConfig)paramObject).zzYp)
            {
              bool1 = bool2;
              if (this.zzYq == ((FACLConfig)paramObject).zzYq)
              {
                bool1 = bool2;
                if (this.zzYr == ((FACLConfig)paramObject).zzYr) {
                  bool1 = true;
                }
              }
            }
          }
        }
      }
    }
    return bool1;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Boolean.valueOf(this.zzYm), this.zzYn, Boolean.valueOf(this.zzYo), Boolean.valueOf(this.zzYp), Boolean.valueOf(this.zzYq), Boolean.valueOf(this.zzYr) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\auth\firstparty\shared\FACLConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */