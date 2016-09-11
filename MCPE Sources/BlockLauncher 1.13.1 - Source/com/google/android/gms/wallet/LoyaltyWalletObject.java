package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.zzmn;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.LabelValueRow;
import com.google.android.gms.wallet.wobs.LoyaltyPoints;
import com.google.android.gms.wallet.wobs.TextModuleData;
import com.google.android.gms.wallet.wobs.TimeInterval;
import com.google.android.gms.wallet.wobs.UriData;
import com.google.android.gms.wallet.wobs.WalletObjectMessage;
import java.util.ArrayList;

public final class LoyaltyWalletObject
  implements SafeParcelable
{
  public static final Parcelable.Creator<LoyaltyWalletObject> CREATOR = new zzk();
  private final int mVersionCode;
  int state;
  String zzaQZ;
  String zzboP;
  String zzboQ;
  String zzboR;
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
  LoyaltyPoints zzbph;
  String zzio;
  
  LoyaltyWalletObject()
  {
    this.mVersionCode = 4;
    this.zzboX = zzmn.zzsa();
    this.zzboZ = zzmn.zzsa();
    this.zzbpc = zzmn.zzsa();
    this.zzbpe = zzmn.zzsa();
    this.zzbpf = zzmn.zzsa();
    this.zzbpg = zzmn.zzsa();
  }
  
  LoyaltyWalletObject(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, int paramInt2, ArrayList<WalletObjectMessage> paramArrayList, TimeInterval paramTimeInterval, ArrayList<LatLng> paramArrayList1, String paramString11, String paramString12, ArrayList<LabelValueRow> paramArrayList2, boolean paramBoolean, ArrayList<UriData> paramArrayList3, ArrayList<TextModuleData> paramArrayList4, ArrayList<UriData> paramArrayList5, LoyaltyPoints paramLoyaltyPoints)
  {
    this.mVersionCode = paramInt1;
    this.zzio = paramString1;
    this.zzboP = paramString2;
    this.zzboQ = paramString3;
    this.zzboR = paramString4;
    this.zzaQZ = paramString5;
    this.zzboS = paramString6;
    this.zzboT = paramString7;
    this.zzboU = paramString8;
    this.zzboV = paramString9;
    this.zzboW = paramString10;
    this.state = paramInt2;
    this.zzboX = paramArrayList;
    this.zzboY = paramTimeInterval;
    this.zzboZ = paramArrayList1;
    this.zzbpa = paramString11;
    this.zzbpb = paramString12;
    this.zzbpc = paramArrayList2;
    this.zzbpd = paramBoolean;
    this.zzbpe = paramArrayList3;
    this.zzbpf = paramArrayList4;
    this.zzbpg = paramArrayList5;
    this.zzbph = paramLoyaltyPoints;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getAccountId()
  {
    return this.zzboP;
  }
  
  public String getAccountName()
  {
    return this.zzaQZ;
  }
  
  public String getBarcodeAlternateText()
  {
    return this.zzboS;
  }
  
  public String getBarcodeType()
  {
    return this.zzboT;
  }
  
  public String getBarcodeValue()
  {
    return this.zzboU;
  }
  
  public String getId()
  {
    return this.zzio;
  }
  
  public String getIssuerName()
  {
    return this.zzboQ;
  }
  
  public String getProgramName()
  {
    return this.zzboR;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzk.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\wallet\LoyaltyWalletObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */