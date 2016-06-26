package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;

public final class Application
  implements SafeParcelable
{
  public static final Parcelable.Creator<Application> CREATOR = new zza();
  public static final Application zzawa = new Application("com.google.android.gms", String.valueOf(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE), null);
  private final int mVersionCode;
  private final String zzTJ;
  private final String zzadc;
  private final String zzawb;
  
  Application(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    this.mVersionCode = paramInt;
    this.zzTJ = ((String)zzx.zzz(paramString1));
    this.zzadc = "";
    this.zzawb = paramString3;
  }
  
  public Application(String paramString1, String paramString2, String paramString3)
  {
    this(1, paramString1, "", paramString3);
  }
  
  private boolean zza(Application paramApplication)
  {
    return (this.zzTJ.equals(paramApplication.zzTJ)) && (zzw.equal(this.zzadc, paramApplication.zzadc)) && (zzw.equal(this.zzawb, paramApplication.zzawb));
  }
  
  public static Application zzdn(String paramString)
  {
    return zzg(paramString, null, null);
  }
  
  public static Application zzg(String paramString1, String paramString2, String paramString3)
  {
    if ("com.google.android.gms".equals(paramString1)) {
      return zzawa;
    }
    return new Application(paramString1, paramString2, paramString3);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof Application)) && (zza((Application)paramObject)));
  }
  
  public String getPackageName()
  {
    return this.zzTJ;
  }
  
  public String getVersion()
  {
    return this.zzadc;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzTJ, this.zzadc, this.zzawb });
  }
  
  public String toString()
  {
    return String.format("Application{%s:%s:%s}", new Object[] { this.zzTJ, this.zzadc, this.zzawb });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public String zzua()
  {
    return this.zzawb;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\data\Application.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */