package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.zzmn;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

@KeepName
public class CommonWalletObject
  implements SafeParcelable
{
  public static final Parcelable.Creator<CommonWalletObject> CREATOR = new zza();
  private final int mVersionCode;
  String name;
  int state;
  String zzboQ;
  String zzboS;
  String zzboT;
  String zzboU;
  String zzboV;
  String zzboW;
  ArrayList<WalletObjectMessage> zzboX;
  TimeInterval zzboY;
  ArrayList<LatLng> zzboZ;
  String zzbpa;
  String zzbpb;
  ArrayList<LabelValueRow> zzbpc;
  boolean zzbpd;
  ArrayList<UriData> zzbpe;
  ArrayList<TextModuleData> zzbpf;
  ArrayList<UriData> zzbpg;
  String zzio;
  
  CommonWalletObject()
  {
    this.mVersionCode = 1;
    this.zzboX = zzmn.zzsa();
    this.zzboZ = zzmn.zzsa();
    this.zzbpc = zzmn.zzsa();
    this.zzbpe = zzmn.zzsa();
    this.zzbpf = zzmn.zzsa();
    this.zzbpg = zzmn.zzsa();
  }
  
  CommonWalletObject(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, int paramInt2, ArrayList<WalletObjectMessage> paramArrayList, TimeInterval paramTimeInterval, ArrayList<LatLng> paramArrayList1, String paramString9, String paramString10, ArrayList<LabelValueRow> paramArrayList2, boolean paramBoolean, ArrayList<UriData> paramArrayList3, ArrayList<TextModuleData> paramArrayList4, ArrayList<UriData> paramArrayList5)
  {
    this.mVersionCode = paramInt1;
    this.zzio = paramString1;
    this.zzboW = paramString2;
    this.name = paramString3;
    this.zzboQ = paramString4;
    this.zzboS = paramString5;
    this.zzboT = paramString6;
    this.zzboU = paramString7;
    this.zzboV = paramString8;
    this.state = paramInt2;
    this.zzboX = paramArrayList;
    this.zzboY = paramTimeInterval;
    this.zzboZ = paramArrayList1;
    this.zzbpa = paramString9;
    this.zzbpb = paramString10;
    this.zzbpc = paramArrayList2;
    this.zzbpd = paramBoolean;
    this.zzbpe = paramArrayList3;
    this.zzbpf = paramArrayList4;
    this.zzbpg = paramArrayList5;
  }
  
  public static zza zzIr()
  {
    CommonWalletObject localCommonWalletObject = new CommonWalletObject();
    localCommonWalletObject.getClass();
    return new zza(null);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getId()
  {
    return this.zzio;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public final class zza
  {
    private zza() {}
    
    public CommonWalletObject zzIs()
    {
      return CommonWalletObject.this;
    }
    
    public zza zzgK(String paramString)
    {
      CommonWalletObject.this.zzio = paramString;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\wallet\wobs\CommonWalletObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */