package com.google.android.gms.internal;

import android.accounts.Account;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.appdatasearch.DocumentContents.zza;
import com.google.android.gms.appdatasearch.DocumentSection;
import com.google.android.gms.appdatasearch.RegisterSectionInfo.zza;
import com.google.android.gms.appdatasearch.UsageInfo;
import com.google.android.gms.appdatasearch.UsageInfo.zza;
import com.google.android.gms.appindexing.Action;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class zzkj
{
  private static DocumentSection zza(String paramString, zzpm.zzc paramzzc)
  {
    paramString = new RegisterSectionInfo.zza(paramString).zzM(true).zzbC(paramString).zzbB("blob").zzmh();
    return new DocumentSection(zzsu.toByteArray(paramzzc), paramString);
  }
  
  public static UsageInfo zza(Action paramAction, long paramLong, String paramString, int paramInt)
  {
    boolean bool = false;
    Bundle localBundle = new Bundle();
    localBundle.putAll(paramAction.zzmk());
    Object localObject = localBundle.getBundle("object");
    int i;
    if (((Bundle)localObject).containsKey("id"))
    {
      paramAction = Uri.parse(((Bundle)localObject).getString("id"));
      String str1 = ((Bundle)localObject).getString("name");
      String str2 = ((Bundle)localObject).getString("type");
      localObject = zzkk.zza(paramString, Uri.parse(((Bundle)localObject).getString("url")));
      paramAction = UsageInfo.zza((Intent)localObject, str1, paramAction, str2, null);
      if (localBundle.containsKey(".private:ssbContext"))
      {
        paramAction.zza(DocumentSection.zzh(localBundle.getByteArray(".private:ssbContext")));
        localBundle.remove(".private:ssbContext");
      }
      if (localBundle.containsKey(".private:accountName"))
      {
        paramAction.zzb(new Account(localBundle.getString(".private:accountName"), "com.google"));
        localBundle.remove(".private:accountName");
      }
      if ((!localBundle.containsKey(".private:isContextOnly")) || (!localBundle.getBoolean(".private:isContextOnly"))) {
        break label290;
      }
      i = 4;
      localBundle.remove(".private:isContextOnly");
    }
    for (;;)
    {
      if (localBundle.containsKey(".private:isDeviceOnly"))
      {
        bool = localBundle.getBoolean(".private:isDeviceOnly", false);
        localBundle.remove(".private:isDeviceOnly");
      }
      paramAction.zza(zza(".private:action", zzg(localBundle)));
      return new UsageInfo.zza().zza(UsageInfo.zza(paramString, (Intent)localObject)).zzw(paramLong).zzar(i).zza(paramAction.zzme()).zzO(bool).zzas(paramInt).zzmi();
      paramAction = null;
      break;
      label290:
      i = 0;
    }
  }
  
  private static zzpm.zzb zzb(String paramString, Bundle paramBundle)
  {
    zzpm.zzb localzzb = new zzpm.zzb();
    localzzb.name = paramString;
    localzzb.zzaMt = new zzpm.zzd();
    localzzb.zzaMt.zzaMy = zzg(paramBundle);
    return localzzb;
  }
  
  static zzpm.zzc zzg(Bundle paramBundle)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = paramBundle.keySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      String str1 = (String)((Iterator)localObject1).next();
      Object localObject2 = paramBundle.get(str1);
      if ((localObject2 instanceof String))
      {
        localArrayList.add(zzp(str1, (String)localObject2));
      }
      else if ((localObject2 instanceof Bundle))
      {
        localArrayList.add(zzb(str1, (Bundle)localObject2));
      }
      else
      {
        int j;
        int i;
        label129:
        String str2;
        if ((localObject2 instanceof String[]))
        {
          localObject2 = (String[])localObject2;
          j = localObject2.length;
          i = 0;
          if (i < j)
          {
            str2 = localObject2[i];
            if (str2 != null) {
              break label152;
            }
          }
          for (;;)
          {
            i += 1;
            break label129;
            break;
            label152:
            localArrayList.add(zzp(str1, str2));
          }
        }
        if ((localObject2 instanceof Bundle[]))
        {
          localObject2 = (Bundle[])localObject2;
          j = localObject2.length;
          i = 0;
          label193:
          if (i < j)
          {
            str2 = localObject2[i];
            if (str2 != null) {
              break label216;
            }
          }
          for (;;)
          {
            i += 1;
            break label193;
            break;
            label216:
            localArrayList.add(zzb(str1, str2));
          }
        }
        if ((localObject2 instanceof Boolean)) {
          localArrayList.add(zzi(str1, ((Boolean)localObject2).booleanValue()));
        } else {
          Log.e("SearchIndex", "Unsupported value: " + localObject2);
        }
      }
    }
    localObject1 = new zzpm.zzc();
    if (paramBundle.containsKey("type")) {
      ((zzpm.zzc)localObject1).type = paramBundle.getString("type");
    }
    ((zzpm.zzc)localObject1).zzaMu = ((zzpm.zzb[])localArrayList.toArray(new zzpm.zzb[localArrayList.size()]));
    return (zzpm.zzc)localObject1;
  }
  
  private static zzpm.zzb zzi(String paramString, boolean paramBoolean)
  {
    zzpm.zzb localzzb = new zzpm.zzb();
    localzzb.name = paramString;
    localzzb.zzaMt = new zzpm.zzd();
    localzzb.zzaMt.zzaMv = paramBoolean;
    return localzzb;
  }
  
  private static zzpm.zzb zzp(String paramString1, String paramString2)
  {
    zzpm.zzb localzzb = new zzpm.zzb();
    localzzb.name = paramString1;
    localzzb.zzaMt = new zzpm.zzd();
    localzzb.zzaMt.zzamJ = paramString2;
    return localzzb;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzkj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */