package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.zzr;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzhb
public final class zzeo
{
  public final List<zzen> zzBO;
  public final long zzBP;
  public final List<String> zzBQ;
  public final List<String> zzBR;
  public final List<String> zzBS;
  public final String zzBT;
  public final long zzBU;
  public final String zzBV;
  public final int zzBW;
  public final int zzBX;
  public final long zzBY;
  public int zzBZ;
  public int zzCa;
  
  public zzeo(String paramString)
    throws JSONException
  {
    paramString = new JSONObject(paramString);
    if (zzin.zzQ(2)) {
      zzin.v("Mediation Response JSON: " + paramString.toString(2));
    }
    JSONArray localJSONArray = paramString.getJSONArray("ad_networks");
    ArrayList localArrayList = new ArrayList(localJSONArray.length());
    int i = 0;
    int k;
    for (int j = -1; i < localJSONArray.length(); j = k)
    {
      zzen localzzen = new zzen(localJSONArray.getJSONObject(i));
      localArrayList.add(localzzen);
      k = j;
      if (j < 0)
      {
        k = j;
        if (zza(localzzen)) {
          k = i;
        }
      }
      i += 1;
    }
    this.zzBZ = j;
    this.zzCa = localJSONArray.length();
    this.zzBO = Collections.unmodifiableList(localArrayList);
    this.zzBT = paramString.getString("qdata");
    this.zzBX = paramString.optInt("fs_model_type", -1);
    this.zzBY = paramString.optLong("timeout_ms", -1L);
    paramString = paramString.optJSONObject("settings");
    if (paramString != null)
    {
      this.zzBP = paramString.optLong("ad_network_timeout_millis", -1L);
      this.zzBQ = zzr.zzbP().zza(paramString, "click_urls");
      this.zzBR = zzr.zzbP().zza(paramString, "imp_urls");
      this.zzBS = zzr.zzbP().zza(paramString, "nofill_urls");
      long l = paramString.optLong("refresh", -1L);
      if (l > 0L) {}
      for (l *= 1000L;; l = -1L)
      {
        this.zzBU = l;
        paramString = RewardItemParcel.zza(paramString.optJSONArray("rewards"));
        if (paramString != null) {
          break;
        }
        this.zzBV = null;
        this.zzBW = 0;
        return;
      }
      this.zzBV = paramString.type;
      this.zzBW = paramString.zzKS;
      return;
    }
    this.zzBP = -1L;
    this.zzBQ = null;
    this.zzBR = null;
    this.zzBS = null;
    this.zzBU = -1L;
    this.zzBV = null;
    this.zzBW = 0;
  }
  
  public zzeo(List<zzen> paramList, long paramLong1, List<String> paramList1, List<String> paramList2, List<String> paramList3, String paramString1, long paramLong2, int paramInt1, int paramInt2, String paramString2, int paramInt3, int paramInt4, long paramLong3)
  {
    this.zzBO = paramList;
    this.zzBP = paramLong1;
    this.zzBQ = paramList1;
    this.zzBR = paramList2;
    this.zzBS = paramList3;
    this.zzBT = paramString1;
    this.zzBU = paramLong2;
    this.zzBZ = paramInt1;
    this.zzCa = paramInt2;
    this.zzBV = paramString2;
    this.zzBW = paramInt3;
    this.zzBX = paramInt4;
    this.zzBY = paramLong3;
  }
  
  private boolean zza(zzen paramzzen)
  {
    paramzzen = paramzzen.zzBB.iterator();
    while (paramzzen.hasNext()) {
      if (((String)paramzzen.next()).equals("com.google.ads.mediation.admob.AdMobAdapter")) {
        return true;
      }
    }
    return false;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzeo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */