package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public class Permission
  implements SafeParcelable
{
  public static final Parcelable.Creator<Permission> CREATOR = new zzj();
  final int mVersionCode;
  private String zzapk;
  private int zzapl;
  private String zzapm;
  private String zzapn;
  private int zzapo;
  private boolean zzapp;
  
  Permission(int paramInt1, String paramString1, int paramInt2, String paramString2, String paramString3, int paramInt3, boolean paramBoolean)
  {
    this.mVersionCode = paramInt1;
    this.zzapk = paramString1;
    this.zzapl = paramInt2;
    this.zzapm = paramString2;
    this.zzapn = paramString3;
    this.zzapo = paramInt3;
    this.zzapp = paramBoolean;
  }
  
  public static boolean zzcA(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return false;
    }
    return true;
  }
  
  public static boolean zzcz(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return false;
    }
    return true;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if ((paramObject == null) || (paramObject.getClass() != getClass())) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramObject == this);
      paramObject = (Permission)paramObject;
      if ((!zzw.equal(this.zzapk, ((Permission)paramObject).zzapk)) || (this.zzapl != ((Permission)paramObject).zzapl) || (this.zzapo != ((Permission)paramObject).zzapo)) {
        break;
      }
      bool1 = bool2;
    } while (this.zzapp == ((Permission)paramObject).zzapp);
    return false;
  }
  
  public int getRole()
  {
    if (!zzcA(this.zzapo)) {
      return -1;
    }
    return this.zzapo;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzapk, Integer.valueOf(this.zzapl), Integer.valueOf(this.zzapo), Boolean.valueOf(this.zzapp) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzj.zza(this, paramParcel, paramInt);
  }
  
  public String zzsO()
  {
    if (!zzcz(this.zzapl)) {
      return null;
    }
    return this.zzapk;
  }
  
  public int zzsP()
  {
    if (!zzcz(this.zzapl)) {
      return -1;
    }
    return this.zzapl;
  }
  
  public String zzsQ()
  {
    return this.zzapm;
  }
  
  public String zzsR()
  {
    return this.zzapn;
  }
  
  public boolean zzsS()
  {
    return this.zzapp;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\Permission.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */