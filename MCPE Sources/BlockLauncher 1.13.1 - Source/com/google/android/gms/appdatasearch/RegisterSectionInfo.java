package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class RegisterSectionInfo
  implements SafeParcelable
{
  public static final zzi CREATOR = new zzi();
  final int mVersionCode;
  public final String name;
  public final int weight;
  public final String zzUd;
  public final boolean zzUe;
  public final boolean zzUf;
  public final String zzUg;
  public final Feature[] zzUh;
  final int[] zzUi;
  public final String zzUj;
  
  RegisterSectionInfo(int paramInt1, String paramString1, String paramString2, boolean paramBoolean1, int paramInt2, boolean paramBoolean2, String paramString3, Feature[] paramArrayOfFeature, int[] paramArrayOfInt, String paramString4)
  {
    this.mVersionCode = paramInt1;
    this.name = paramString1;
    this.zzUd = paramString2;
    this.zzUe = paramBoolean1;
    this.weight = paramInt2;
    this.zzUf = paramBoolean2;
    this.zzUg = paramString3;
    this.zzUh = paramArrayOfFeature;
    this.zzUi = paramArrayOfInt;
    this.zzUj = paramString4;
  }
  
  RegisterSectionInfo(String paramString1, String paramString2, boolean paramBoolean1, int paramInt, boolean paramBoolean2, String paramString3, Feature[] paramArrayOfFeature, int[] paramArrayOfInt, String paramString4)
  {
    this(2, paramString1, paramString2, paramBoolean1, paramInt, paramBoolean2, paramString3, paramArrayOfFeature, paramArrayOfInt, paramString4);
  }
  
  public int describeContents()
  {
    zzi localzzi = CREATOR;
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzi localzzi = CREATOR;
    zzi.zza(this, paramParcel, paramInt);
  }
  
  public static final class zza
  {
    private final String mName;
    private String zzUk;
    private boolean zzUl;
    private int zzUm;
    private boolean zzUn;
    private String zzUo;
    private final List<Feature> zzUp;
    private BitSet zzUq;
    private String zzUr;
    
    public zza(String paramString)
    {
      this.mName = paramString;
      this.zzUm = 1;
      this.zzUp = new ArrayList();
    }
    
    public zza zzM(boolean paramBoolean)
    {
      this.zzUl = paramBoolean;
      return this;
    }
    
    public zza zzN(boolean paramBoolean)
    {
      this.zzUn = paramBoolean;
      return this;
    }
    
    public zza zzap(int paramInt)
    {
      if (this.zzUq == null) {
        this.zzUq = new BitSet();
      }
      this.zzUq.set(paramInt);
      return this;
    }
    
    public zza zzbB(String paramString)
    {
      this.zzUk = paramString;
      return this;
    }
    
    public zza zzbC(String paramString)
    {
      this.zzUr = paramString;
      return this;
    }
    
    public RegisterSectionInfo zzmh()
    {
      int i = 0;
      Object localObject = null;
      if (this.zzUq != null)
      {
        int[] arrayOfInt = new int[this.zzUq.cardinality()];
        int j = this.zzUq.nextSetBit(0);
        for (;;)
        {
          localObject = arrayOfInt;
          if (j < 0) {
            break;
          }
          arrayOfInt[i] = j;
          j = this.zzUq.nextSetBit(j + 1);
          i += 1;
        }
      }
      return new RegisterSectionInfo(this.mName, this.zzUk, this.zzUl, this.zzUm, this.zzUn, this.zzUo, (Feature[])this.zzUp.toArray(new Feature[this.zzUp.size()]), (int[])localObject, this.zzUr);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\appdatasearch\RegisterSectionInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */