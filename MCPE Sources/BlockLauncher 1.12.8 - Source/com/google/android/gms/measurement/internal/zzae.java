package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzpz.zzf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class zzae
{
  final boolean zzaXg;
  final int zzaZh;
  final boolean zzaZi;
  final String zzaZj;
  final List<String> zzaZk;
  final String zzaZl;
  
  public zzae(zzpz.zzf paramzzf)
  {
    zzx.zzz(paramzzf);
    boolean bool1;
    if ((paramzzf.zzaZN == null) || (paramzzf.zzaZN.intValue() == 0)) {
      bool1 = false;
    }
    for (;;)
    {
      if (bool1)
      {
        this.zzaZh = paramzzf.zzaZN.intValue();
        boolean bool2 = bool3;
        if (paramzzf.zzaZP != null)
        {
          bool2 = bool3;
          if (paramzzf.zzaZP.booleanValue()) {
            bool2 = true;
          }
        }
        this.zzaZi = bool2;
        if ((this.zzaZi) || (this.zzaZh == 1) || (this.zzaZh == 6))
        {
          this.zzaZj = paramzzf.zzaZO;
          label108:
          if (paramzzf.zzaZQ != null) {
            break label205;
          }
          paramzzf = null;
          label117:
          this.zzaZk = paramzzf;
          if (this.zzaZh != 1) {
            break label221;
          }
          this.zzaZl = this.zzaZj;
        }
      }
      for (;;)
      {
        this.zzaXg = bool1;
        return;
        if (paramzzf.zzaZN.intValue() == 6)
        {
          if ((paramzzf.zzaZQ != null) && (paramzzf.zzaZQ.length != 0)) {
            break label257;
          }
          bool1 = false;
          break;
        }
        if (paramzzf.zzaZO != null) {
          break label257;
        }
        bool1 = false;
        break;
        this.zzaZj = paramzzf.zzaZO.toUpperCase(Locale.ENGLISH);
        break label108;
        label205:
        paramzzf = zza(paramzzf.zzaZQ, this.zzaZi);
        break label117;
        label221:
        this.zzaZl = null;
        continue;
        this.zzaZh = 0;
        this.zzaZi = false;
        this.zzaZj = null;
        this.zzaZk = null;
        this.zzaZl = null;
      }
      label257:
      bool1 = true;
    }
  }
  
  private List<String> zza(String[] paramArrayOfString, boolean paramBoolean)
  {
    Object localObject;
    if (paramBoolean)
    {
      localObject = Arrays.asList(paramArrayOfString);
      return (List<String>)localObject;
    }
    ArrayList localArrayList = new ArrayList();
    int j = paramArrayOfString.length;
    int i = 0;
    for (;;)
    {
      localObject = localArrayList;
      if (i >= j) {
        break;
      }
      localArrayList.add(paramArrayOfString[i].toUpperCase(Locale.ENGLISH));
      i += 1;
    }
  }
  
  public Boolean zzfp(String paramString)
  {
    if (!this.zzaXg) {
      return null;
    }
    String str = paramString;
    if (!this.zzaZi)
    {
      if (this.zzaZh != 1) {
        break label102;
      }
      str = paramString;
    }
    switch (this.zzaZh)
    {
    default: 
      return null;
    case 1: 
      if (this.zzaZi) {}
      for (int i = 0;; i = 66)
      {
        return Boolean.valueOf(Pattern.compile(this.zzaZl, i).matcher(str).matches());
        str = paramString.toUpperCase(Locale.ENGLISH);
        break;
      }
    case 2: 
      return Boolean.valueOf(str.startsWith(this.zzaZj));
    case 3: 
      return Boolean.valueOf(str.endsWith(this.zzaZj));
    case 4: 
      return Boolean.valueOf(str.contains(this.zzaZj));
    case 5: 
      label102:
      return Boolean.valueOf(str.equals(this.zzaZj));
    }
    return Boolean.valueOf(this.zzaZk.contains(str));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\measurement\internal\zzae.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */