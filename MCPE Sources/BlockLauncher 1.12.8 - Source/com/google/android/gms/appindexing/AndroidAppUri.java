package com.google.android.gms.appindexing;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzw;
import java.util.Iterator;
import java.util.List;

public final class AndroidAppUri
{
  private final Uri mUri;
  
  private AndroidAppUri(Uri paramUri)
  {
    this.mUri = paramUri;
  }
  
  public static AndroidAppUri newAndroidAppUri(Uri paramUri)
  {
    paramUri = new AndroidAppUri(paramUri);
    if (zza(paramUri)) {
      return paramUri;
    }
    throw new IllegalArgumentException("AndroidAppUri validation failed.");
  }
  
  public static AndroidAppUri newAndroidAppUri(String paramString, Uri paramUri)
  {
    paramString = new Uri.Builder().scheme("android-app").authority(paramString);
    if (paramUri != null)
    {
      paramString.appendPath(paramUri.getScheme());
      if (paramUri.getAuthority() != null) {
        paramString.appendPath(paramUri.getAuthority());
      }
      Iterator localIterator = paramUri.getPathSegments().iterator();
      while (localIterator.hasNext()) {
        paramString.appendPath((String)localIterator.next());
      }
      paramString.encodedQuery(paramUri.getEncodedQuery()).encodedFragment(paramUri.getEncodedFragment());
    }
    return new AndroidAppUri(paramString.build());
  }
  
  private static boolean zza(AndroidAppUri paramAndroidAppUri)
  {
    if (!"android-app".equals(paramAndroidAppUri.mUri.getScheme())) {
      throw new IllegalArgumentException("android-app scheme is required.");
    }
    if (TextUtils.isEmpty(paramAndroidAppUri.getPackageName())) {
      throw new IllegalArgumentException("Package name is empty.");
    }
    Uri localUri = newAndroidAppUri(paramAndroidAppUri.getPackageName(), paramAndroidAppUri.getDeepLinkUri()).toUri();
    if (!paramAndroidAppUri.mUri.equals(localUri)) {
      throw new IllegalArgumentException("URI is not canonical.");
    }
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof AndroidAppUri)) {
      return this.mUri.equals(((AndroidAppUri)paramObject).mUri);
    }
    return false;
  }
  
  public Uri getDeepLinkUri()
  {
    List localList = this.mUri.getPathSegments();
    if (localList.size() > 0)
    {
      String str = (String)localList.get(0);
      Uri.Builder localBuilder = new Uri.Builder();
      localBuilder.scheme(str);
      if (localList.size() > 1)
      {
        localBuilder.authority((String)localList.get(1));
        int i = 2;
        while (i < localList.size())
        {
          localBuilder.appendPath((String)localList.get(i));
          i += 1;
        }
      }
      localBuilder.encodedQuery(this.mUri.getEncodedQuery());
      localBuilder.encodedFragment(this.mUri.getEncodedFragment());
      return localBuilder.build();
    }
    return null;
  }
  
  public String getPackageName()
  {
    return this.mUri.getAuthority();
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.mUri });
  }
  
  public String toString()
  {
    return this.mUri.toString();
  }
  
  public Uri toUri()
  {
    return this.mUri;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\appindexing\AndroidAppUri.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */