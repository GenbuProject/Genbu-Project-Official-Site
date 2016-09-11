package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import com.google.android.gms.internal.zzrs.zza;
import com.google.android.gms.internal.zzrs.zzb;
import com.google.android.gms.internal.zzrs.zzc;
import com.google.android.gms.internal.zzrs.zzd;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class zzaz
{
  private static zzag.zza zzK(Object paramObject)
    throws JSONException
  {
    return zzdf.zzR(zzL(paramObject));
  }
  
  static Object zzL(Object paramObject)
    throws JSONException
  {
    if ((paramObject instanceof JSONArray)) {
      throw new RuntimeException("JSONArrays are not supported");
    }
    if (JSONObject.NULL.equals(paramObject)) {
      throw new RuntimeException("JSON nulls are not supported");
    }
    Object localObject = paramObject;
    if ((paramObject instanceof JSONObject))
    {
      paramObject = (JSONObject)paramObject;
      localObject = new HashMap();
      Iterator localIterator = ((JSONObject)paramObject).keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        ((Map)localObject).put(str, zzL(((JSONObject)paramObject).get(str)));
      }
    }
    return localObject;
  }
  
  public static zzrs.zzc zzgi(String paramString)
    throws JSONException
  {
    paramString = zzK(new JSONObject(paramString));
    zzrs.zzd localzzd = zzrs.zzc.zzHK();
    int i = 0;
    while (i < paramString.zzjz.length)
    {
      localzzd.zzc(zzrs.zza.zzHH().zzb(zzae.zzfR.toString(), paramString.zzjz[i]).zzb(zzae.zzfG.toString(), zzdf.zzgt(zzn.zzFZ())).zzb(zzn.zzGa(), paramString.zzjA[i]).zzHJ());
      i += 1;
    }
    return localzzd.zzHN();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\tagmanager\zzaz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */