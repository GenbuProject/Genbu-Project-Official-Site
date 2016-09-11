package com.google.android.gms.appindexing;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzx;
import java.util.ArrayList;

public class Thing
{
  final Bundle zzUG;
  
  Thing(Bundle paramBundle)
  {
    this.zzUG = paramBundle;
  }
  
  public Bundle zzmk()
  {
    return this.zzUG;
  }
  
  public static class Builder
  {
    final Bundle zzUH = new Bundle();
    
    public Thing build()
    {
      return new Thing(this.zzUH);
    }
    
    public Builder put(String paramString, Thing paramThing)
    {
      zzx.zzz(paramString);
      if (paramThing != null) {
        this.zzUH.putParcelable(paramString, paramThing.zzUG);
      }
      return this;
    }
    
    public Builder put(String paramString1, String paramString2)
    {
      zzx.zzz(paramString1);
      if (paramString2 != null) {
        this.zzUH.putString(paramString1, paramString2);
      }
      return this;
    }
    
    public Builder put(String paramString, boolean paramBoolean)
    {
      zzx.zzz(paramString);
      this.zzUH.putBoolean(paramString, paramBoolean);
      return this;
    }
    
    public Builder put(String paramString, Thing[] paramArrayOfThing)
    {
      zzx.zzz(paramString);
      if (paramArrayOfThing != null)
      {
        ArrayList localArrayList = new ArrayList();
        int j = paramArrayOfThing.length;
        int i = 0;
        while (i < j)
        {
          Thing localThing = paramArrayOfThing[i];
          if (localThing != null) {
            localArrayList.add(localThing.zzUG);
          }
          i += 1;
        }
        this.zzUH.putParcelableArray(paramString, (Parcelable[])localArrayList.toArray(new Bundle[localArrayList.size()]));
      }
      return this;
    }
    
    public Builder put(String paramString, String[] paramArrayOfString)
    {
      zzx.zzz(paramString);
      if (paramArrayOfString != null) {
        this.zzUH.putStringArray(paramString, paramArrayOfString);
      }
      return this;
    }
    
    public Builder setDescription(String paramString)
    {
      put("description", paramString);
      return this;
    }
    
    public Builder setId(String paramString)
    {
      if (paramString != null) {
        put("id", paramString);
      }
      return this;
    }
    
    public Builder setName(String paramString)
    {
      zzx.zzz(paramString);
      put("name", paramString);
      return this;
    }
    
    public Builder setType(String paramString)
    {
      put("type", paramString);
      return this;
    }
    
    public Builder setUrl(Uri paramUri)
    {
      zzx.zzz(paramUri);
      put("url", paramUri.toString());
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\appindexing\Thing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */