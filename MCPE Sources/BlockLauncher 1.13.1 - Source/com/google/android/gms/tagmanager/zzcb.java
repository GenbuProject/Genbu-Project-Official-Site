package com.google.android.gms.tagmanager;

import android.net.Uri;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

class zzcb
{
  private static zzcb zzbjQ;
  private volatile String zzbhM;
  private volatile zza zzbjR;
  private volatile String zzbjS;
  private volatile String zzbjT;
  
  zzcb()
  {
    clear();
  }
  
  static zzcb zzGU()
  {
    try
    {
      if (zzbjQ == null) {
        zzbjQ = new zzcb();
      }
      zzcb localzzcb = zzbjQ;
      return localzzcb;
    }
    finally {}
  }
  
  private String zzgk(String paramString)
  {
    return paramString.split("&")[0].split("=")[1];
  }
  
  private String zzq(Uri paramUri)
  {
    return paramUri.getQuery().replace("&gtm_debug=x", "");
  }
  
  void clear()
  {
    this.zzbjR = zza.zzbjU;
    this.zzbjS = null;
    this.zzbhM = null;
    this.zzbjT = null;
  }
  
  String getContainerId()
  {
    return this.zzbhM;
  }
  
  zza zzGV()
  {
    return this.zzbjR;
  }
  
  String zzGW()
  {
    return this.zzbjS;
  }
  
  boolean zzp(Uri paramUri)
  {
    boolean bool = true;
    String str;
    try
    {
      str = URLDecoder.decode(paramUri.toString(), "UTF-8");
      if (!str.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
        break label153;
      }
      zzbg.v("Container preview url: " + str);
      if (!str.matches(".*?&gtm_debug=x$")) {
        break label138;
      }
      this.zzbjR = zza.zzbjW;
    }
    catch (UnsupportedEncodingException paramUri)
    {
      for (;;)
      {
        bool = false;
        continue;
        this.zzbjR = zza.zzbjV;
      }
    }
    finally {}
    this.zzbjT = zzq(paramUri);
    if ((this.zzbjR == zza.zzbjV) || (this.zzbjR == zza.zzbjW)) {
      this.zzbjS = ("/r?" + this.zzbjT);
    }
    this.zzbhM = zzgk(this.zzbjT);
    for (;;)
    {
      return bool;
      label138:
      label153:
      if (str.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$"))
      {
        if (zzgk(paramUri.getQuery()).equals(this.zzbhM))
        {
          zzbg.v("Exit preview mode for container: " + this.zzbhM);
          this.zzbjR = zza.zzbjU;
          this.zzbjS = null;
        }
      }
      else
      {
        zzbg.zzaK("Invalid preview uri: " + str);
        bool = false;
        continue;
      }
      bool = false;
    }
  }
  
  static enum zza
  {
    private zza() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\tagmanager\zzcb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */