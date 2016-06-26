package com.google.android.gms.nearby.messages.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.Collections;
import java.util.List;

public class zzj
{
  public static <T extends Parcelable> T zzc(Intent paramIntent, String paramString)
  {
    paramIntent = paramIntent.getBundleExtra(paramString);
    if (paramIntent == null) {
      return null;
    }
    return paramIntent.getParcelable(paramString);
  }
  
  public static <T extends Parcelable> List<T> zzd(Intent paramIntent, String paramString)
  {
    paramIntent = paramIntent.getBundleExtra(paramString);
    if (paramIntent == null) {
      return Collections.emptyList();
    }
    return paramIntent.getParcelableArrayList(paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\messages\internal\zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */