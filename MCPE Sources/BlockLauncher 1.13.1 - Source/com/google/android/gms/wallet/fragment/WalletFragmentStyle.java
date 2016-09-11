package com.google.android.gms.wallet.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.google.android.gms.R.style;
import com.google.android.gms.R.styleable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class WalletFragmentStyle
  implements SafeParcelable
{
  public static final Parcelable.Creator<WalletFragmentStyle> CREATOR = new zzc();
  final int mVersionCode;
  Bundle zzbqu;
  int zzbqv;
  
  public WalletFragmentStyle()
  {
    this.mVersionCode = 1;
    this.zzbqu = new Bundle();
    this.zzbqu.putInt("buyButtonAppearanceDefault", 4);
    this.zzbqu.putInt("maskedWalletDetailsLogoImageTypeDefault", 3);
  }
  
  WalletFragmentStyle(int paramInt1, Bundle paramBundle, int paramInt2)
  {
    this.mVersionCode = paramInt1;
    this.zzbqu = paramBundle;
    this.zzbqv = paramInt2;
  }
  
  private static int zza(long paramLong, DisplayMetrics paramDisplayMetrics)
  {
    int i = (int)(paramLong >>> 32);
    int j = (int)paramLong;
    switch (i)
    {
    default: 
      throw new IllegalStateException("Unexpected unit or type: " + i);
    case 129: 
      return j;
    case 128: 
      return TypedValue.complexToDimensionPixelSize(j, paramDisplayMetrics);
    case 0: 
      i = 0;
    }
    for (;;)
    {
      return Math.round(TypedValue.applyDimension(i, Float.intBitsToFloat(j), paramDisplayMetrics));
      i = 1;
      continue;
      i = 2;
      continue;
      i = 3;
      continue;
      i = 4;
      continue;
      i = 5;
    }
  }
  
  private static long zza(int paramInt, float paramFloat)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("Unrecognized unit: " + paramInt);
    }
    return zzv(paramInt, Float.floatToIntBits(paramFloat));
  }
  
  private static long zza(TypedValue paramTypedValue)
  {
    switch (paramTypedValue.type)
    {
    default: 
      throw new IllegalArgumentException("Unexpected dimension type: " + paramTypedValue.type);
    case 16: 
      return zzll(paramTypedValue.data);
    }
    return zzv(128, paramTypedValue.data);
  }
  
  private void zza(TypedArray paramTypedArray, int paramInt, String paramString)
  {
    if (this.zzbqu.containsKey(paramString)) {}
    do
    {
      return;
      paramTypedArray = paramTypedArray.peekValue(paramInt);
    } while (paramTypedArray == null);
    this.zzbqu.putLong(paramString, zza(paramTypedArray));
  }
  
  private void zza(TypedArray paramTypedArray, int paramInt, String paramString1, String paramString2)
  {
    if ((this.zzbqu.containsKey(paramString1)) || (this.zzbqu.containsKey(paramString2))) {}
    do
    {
      return;
      paramTypedArray = paramTypedArray.peekValue(paramInt);
    } while (paramTypedArray == null);
    if ((paramTypedArray.type >= 28) && (paramTypedArray.type <= 31))
    {
      this.zzbqu.putInt(paramString1, paramTypedArray.data);
      return;
    }
    this.zzbqu.putInt(paramString2, paramTypedArray.resourceId);
  }
  
  private void zzb(TypedArray paramTypedArray, int paramInt, String paramString)
  {
    if (this.zzbqu.containsKey(paramString)) {}
    do
    {
      return;
      paramTypedArray = paramTypedArray.peekValue(paramInt);
    } while (paramTypedArray == null);
    this.zzbqu.putInt(paramString, paramTypedArray.data);
  }
  
  private static long zzll(int paramInt)
  {
    if (paramInt < 0)
    {
      if ((paramInt == -1) || (paramInt == -2)) {
        return zzv(129, paramInt);
      }
      throw new IllegalArgumentException("Unexpected dimension value: " + paramInt);
    }
    return zza(0, paramInt);
  }
  
  private static long zzv(int paramInt1, int paramInt2)
  {
    return paramInt1 << 32 | paramInt2 & 0xFFFFFFFF;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public WalletFragmentStyle setBuyButtonAppearance(int paramInt)
  {
    this.zzbqu.putInt("buyButtonAppearance", paramInt);
    return this;
  }
  
  public WalletFragmentStyle setBuyButtonHeight(int paramInt)
  {
    this.zzbqu.putLong("buyButtonHeight", zzll(paramInt));
    return this;
  }
  
  public WalletFragmentStyle setBuyButtonHeight(int paramInt, float paramFloat)
  {
    this.zzbqu.putLong("buyButtonHeight", zza(paramInt, paramFloat));
    return this;
  }
  
  public WalletFragmentStyle setBuyButtonText(int paramInt)
  {
    this.zzbqu.putInt("buyButtonText", paramInt);
    return this;
  }
  
  public WalletFragmentStyle setBuyButtonWidth(int paramInt)
  {
    this.zzbqu.putLong("buyButtonWidth", zzll(paramInt));
    return this;
  }
  
  public WalletFragmentStyle setBuyButtonWidth(int paramInt, float paramFloat)
  {
    this.zzbqu.putLong("buyButtonWidth", zza(paramInt, paramFloat));
    return this;
  }
  
  public WalletFragmentStyle setMaskedWalletDetailsBackgroundColor(int paramInt)
  {
    this.zzbqu.remove("maskedWalletDetailsBackgroundResource");
    this.zzbqu.putInt("maskedWalletDetailsBackgroundColor", paramInt);
    return this;
  }
  
  public WalletFragmentStyle setMaskedWalletDetailsBackgroundResource(int paramInt)
  {
    this.zzbqu.remove("maskedWalletDetailsBackgroundColor");
    this.zzbqu.putInt("maskedWalletDetailsBackgroundResource", paramInt);
    return this;
  }
  
  public WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundColor(int paramInt)
  {
    this.zzbqu.remove("maskedWalletDetailsButtonBackgroundResource");
    this.zzbqu.putInt("maskedWalletDetailsButtonBackgroundColor", paramInt);
    return this;
  }
  
  public WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundResource(int paramInt)
  {
    this.zzbqu.remove("maskedWalletDetailsButtonBackgroundColor");
    this.zzbqu.putInt("maskedWalletDetailsButtonBackgroundResource", paramInt);
    return this;
  }
  
  public WalletFragmentStyle setMaskedWalletDetailsButtonTextAppearance(int paramInt)
  {
    this.zzbqu.putInt("maskedWalletDetailsButtonTextAppearance", paramInt);
    return this;
  }
  
  public WalletFragmentStyle setMaskedWalletDetailsHeaderTextAppearance(int paramInt)
  {
    this.zzbqu.putInt("maskedWalletDetailsHeaderTextAppearance", paramInt);
    return this;
  }
  
  public WalletFragmentStyle setMaskedWalletDetailsLogoImageType(int paramInt)
  {
    this.zzbqu.putInt("maskedWalletDetailsLogoImageType", paramInt);
    return this;
  }
  
  @Deprecated
  public WalletFragmentStyle setMaskedWalletDetailsLogoTextColor(int paramInt)
  {
    this.zzbqu.putInt("maskedWalletDetailsLogoTextColor", paramInt);
    return this;
  }
  
  public WalletFragmentStyle setMaskedWalletDetailsTextAppearance(int paramInt)
  {
    this.zzbqu.putInt("maskedWalletDetailsTextAppearance", paramInt);
    return this;
  }
  
  public WalletFragmentStyle setStyleResourceId(int paramInt)
  {
    this.zzbqv = paramInt;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
  
  public int zza(String paramString, DisplayMetrics paramDisplayMetrics, int paramInt)
  {
    if (this.zzbqu.containsKey(paramString)) {
      paramInt = zza(this.zzbqu.getLong(paramString), paramDisplayMetrics);
    }
    return paramInt;
  }
  
  public void zzbc(Context paramContext)
  {
    if (this.zzbqv <= 0) {}
    for (int i = R.style.WalletFragmentDefaultStyle;; i = this.zzbqv)
    {
      paramContext = paramContext.obtainStyledAttributes(i, R.styleable.WalletFragmentStyle);
      zza(paramContext, R.styleable.WalletFragmentStyle_buyButtonWidth, "buyButtonWidth");
      zza(paramContext, R.styleable.WalletFragmentStyle_buyButtonHeight, "buyButtonHeight");
      zzb(paramContext, R.styleable.WalletFragmentStyle_buyButtonText, "buyButtonText");
      zzb(paramContext, R.styleable.WalletFragmentStyle_buyButtonAppearance, "buyButtonAppearance");
      zzb(paramContext, R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance, "maskedWalletDetailsTextAppearance");
      zzb(paramContext, R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance, "maskedWalletDetailsHeaderTextAppearance");
      zza(paramContext, R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground, "maskedWalletDetailsBackgroundColor", "maskedWalletDetailsBackgroundResource");
      zzb(paramContext, R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance, "maskedWalletDetailsButtonTextAppearance");
      zza(paramContext, R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground, "maskedWalletDetailsButtonBackgroundColor", "maskedWalletDetailsButtonBackgroundResource");
      zzb(paramContext, R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor, "maskedWalletDetailsLogoTextColor");
      zzb(paramContext, R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType, "maskedWalletDetailsLogoImageType");
      paramContext.recycle();
      return;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface BuyButtonAppearance
  {
    public static final int ANDROID_PAY_DARK = 4;
    public static final int ANDROID_PAY_LIGHT = 5;
    public static final int ANDROID_PAY_LIGHT_WITH_BORDER = 6;
    @Deprecated
    public static final int GOOGLE_WALLET_CLASSIC = 1;
    @Deprecated
    public static final int GOOGLE_WALLET_GRAYSCALE = 2;
    @Deprecated
    public static final int GOOGLE_WALLET_MONOCHROME = 3;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface BuyButtonText
  {
    public static final int BUY_WITH = 5;
    public static final int DONATE_WITH = 7;
    public static final int LOGO_ONLY = 6;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Dimension
  {
    public static final int MATCH_PARENT = -1;
    public static final int UNIT_DIP = 1;
    public static final int UNIT_IN = 4;
    public static final int UNIT_MM = 5;
    public static final int UNIT_PT = 3;
    public static final int UNIT_PX = 0;
    public static final int UNIT_SP = 2;
    public static final int WRAP_CONTENT = -2;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface LogoImageType
  {
    public static final int ANDROID_PAY = 3;
    @Deprecated
    public static final int GOOGLE_WALLET_CLASSIC = 1;
    @Deprecated
    public static final int GOOGLE_WALLET_MONOCHROME = 2;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\wallet\fragment\WalletFragmentStyle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */