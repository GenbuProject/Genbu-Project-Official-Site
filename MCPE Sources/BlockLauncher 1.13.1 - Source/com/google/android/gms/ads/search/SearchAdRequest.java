package com.google.android.gms.ads.search;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.internal.client.zzaa;
import com.google.android.gms.ads.internal.client.zzaa.zza;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;

public final class SearchAdRequest
{
  public static final int BORDER_TYPE_DASHED = 1;
  public static final int BORDER_TYPE_DOTTED = 2;
  public static final int BORDER_TYPE_NONE = 0;
  public static final int BORDER_TYPE_SOLID = 3;
  public static final int CALL_BUTTON_COLOR_DARK = 2;
  public static final int CALL_BUTTON_COLOR_LIGHT = 0;
  public static final int CALL_BUTTON_COLOR_MEDIUM = 1;
  public static final String DEVICE_ID_EMULATOR = zzaa.DEVICE_ID_EMULATOR;
  public static final int ERROR_CODE_INTERNAL_ERROR = 0;
  public static final int ERROR_CODE_INVALID_REQUEST = 1;
  public static final int ERROR_CODE_NETWORK_ERROR = 2;
  public static final int ERROR_CODE_NO_FILL = 3;
  private final int zzOA;
  private final int zzOB;
  private final int zzOC;
  private final int zzOD;
  private final String zzOE;
  private final int zzOF;
  private final String zzOG;
  private final int zzOH;
  private final int zzOI;
  private final String zzOJ;
  private final int zzOx;
  private final int zzOy;
  private final int zzOz;
  private final zzaa zzoE;
  private final int zzxO;
  
  private SearchAdRequest(Builder paramBuilder)
  {
    this.zzOx = Builder.zza(paramBuilder);
    this.zzxO = Builder.zzb(paramBuilder);
    this.zzOy = Builder.zzc(paramBuilder);
    this.zzOz = Builder.zzd(paramBuilder);
    this.zzOA = Builder.zze(paramBuilder);
    this.zzOB = Builder.zzf(paramBuilder);
    this.zzOC = Builder.zzg(paramBuilder);
    this.zzOD = Builder.zzh(paramBuilder);
    this.zzOE = Builder.zzi(paramBuilder);
    this.zzOF = Builder.zzj(paramBuilder);
    this.zzOG = Builder.zzk(paramBuilder);
    this.zzOH = Builder.zzl(paramBuilder);
    this.zzOI = Builder.zzm(paramBuilder);
    this.zzOJ = Builder.zzn(paramBuilder);
    this.zzoE = new zzaa(Builder.zzo(paramBuilder), this);
  }
  
  public int getAnchorTextColor()
  {
    return this.zzOx;
  }
  
  public int getBackgroundColor()
  {
    return this.zzxO;
  }
  
  public int getBackgroundGradientBottom()
  {
    return this.zzOy;
  }
  
  public int getBackgroundGradientTop()
  {
    return this.zzOz;
  }
  
  public int getBorderColor()
  {
    return this.zzOA;
  }
  
  public int getBorderThickness()
  {
    return this.zzOB;
  }
  
  public int getBorderType()
  {
    return this.zzOC;
  }
  
  public int getCallButtonColor()
  {
    return this.zzOD;
  }
  
  public String getCustomChannels()
  {
    return this.zzOE;
  }
  
  public <T extends CustomEvent> Bundle getCustomEventExtrasBundle(Class<T> paramClass)
  {
    return this.zzoE.getCustomEventExtrasBundle(paramClass);
  }
  
  public int getDescriptionTextColor()
  {
    return this.zzOF;
  }
  
  public String getFontFace()
  {
    return this.zzOG;
  }
  
  public int getHeaderTextColor()
  {
    return this.zzOH;
  }
  
  public int getHeaderTextSize()
  {
    return this.zzOI;
  }
  
  public Location getLocation()
  {
    return this.zzoE.getLocation();
  }
  
  @Deprecated
  public <T extends NetworkExtras> T getNetworkExtras(Class<T> paramClass)
  {
    return this.zzoE.getNetworkExtras(paramClass);
  }
  
  public <T extends MediationAdapter> Bundle getNetworkExtrasBundle(Class<T> paramClass)
  {
    return this.zzoE.getNetworkExtrasBundle(paramClass);
  }
  
  public String getQuery()
  {
    return this.zzOJ;
  }
  
  public boolean isTestDevice(Context paramContext)
  {
    return this.zzoE.isTestDevice(paramContext);
  }
  
  zzaa zzaE()
  {
    return this.zzoE;
  }
  
  public static final class Builder
  {
    private int zzOA;
    private int zzOB;
    private int zzOC = 0;
    private int zzOD;
    private String zzOE;
    private int zzOF;
    private String zzOG;
    private int zzOH;
    private int zzOI;
    private String zzOJ;
    private int zzOx;
    private int zzOy;
    private int zzOz;
    private final zzaa.zza zzoF = new zzaa.zza();
    private int zzxO;
    
    public Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> paramClass, Bundle paramBundle)
    {
      this.zzoF.zzb(paramClass, paramBundle);
      return this;
    }
    
    public Builder addNetworkExtras(NetworkExtras paramNetworkExtras)
    {
      this.zzoF.zza(paramNetworkExtras);
      return this;
    }
    
    public Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> paramClass, Bundle paramBundle)
    {
      this.zzoF.zza(paramClass, paramBundle);
      return this;
    }
    
    public Builder addTestDevice(String paramString)
    {
      this.zzoF.zzB(paramString);
      return this;
    }
    
    public SearchAdRequest build()
    {
      return new SearchAdRequest(this, null);
    }
    
    public Builder setAnchorTextColor(int paramInt)
    {
      this.zzOx = paramInt;
      return this;
    }
    
    public Builder setBackgroundColor(int paramInt)
    {
      this.zzxO = paramInt;
      this.zzOy = Color.argb(0, 0, 0, 0);
      this.zzOz = Color.argb(0, 0, 0, 0);
      return this;
    }
    
    public Builder setBackgroundGradient(int paramInt1, int paramInt2)
    {
      this.zzxO = Color.argb(0, 0, 0, 0);
      this.zzOy = paramInt2;
      this.zzOz = paramInt1;
      return this;
    }
    
    public Builder setBorderColor(int paramInt)
    {
      this.zzOA = paramInt;
      return this;
    }
    
    public Builder setBorderThickness(int paramInt)
    {
      this.zzOB = paramInt;
      return this;
    }
    
    public Builder setBorderType(int paramInt)
    {
      this.zzOC = paramInt;
      return this;
    }
    
    public Builder setCallButtonColor(int paramInt)
    {
      this.zzOD = paramInt;
      return this;
    }
    
    public Builder setCustomChannels(String paramString)
    {
      this.zzOE = paramString;
      return this;
    }
    
    public Builder setDescriptionTextColor(int paramInt)
    {
      this.zzOF = paramInt;
      return this;
    }
    
    public Builder setFontFace(String paramString)
    {
      this.zzOG = paramString;
      return this;
    }
    
    public Builder setHeaderTextColor(int paramInt)
    {
      this.zzOH = paramInt;
      return this;
    }
    
    public Builder setHeaderTextSize(int paramInt)
    {
      this.zzOI = paramInt;
      return this;
    }
    
    public Builder setLocation(Location paramLocation)
    {
      this.zzoF.zzb(paramLocation);
      return this;
    }
    
    public Builder setQuery(String paramString)
    {
      this.zzOJ = paramString;
      return this;
    }
    
    public Builder setRequestAgent(String paramString)
    {
      this.zzoF.zzF(paramString);
      return this;
    }
    
    public Builder tagForChildDirectedTreatment(boolean paramBoolean)
    {
      this.zzoF.zzk(paramBoolean);
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\search\SearchAdRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */