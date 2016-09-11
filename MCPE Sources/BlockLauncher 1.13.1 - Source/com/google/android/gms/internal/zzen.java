package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzr;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzhb
public final class zzen
{
  public final String zzBA;
  public final List<String> zzBB;
  public final String zzBC;
  public final String zzBD;
  public final List<String> zzBE;
  public final List<String> zzBF;
  public final String zzBG;
  public final List<String> zzBH;
  public final List<String> zzBI;
  public final String zzBJ;
  public final String zzBK;
  public final String zzBL;
  public final List<String> zzBM;
  public final String zzBN;
  public final String zzBz;
  
  public zzen(String paramString1, String paramString2, List<String> paramList1, String paramString3, String paramString4, List<String> paramList2, List<String> paramList3, String paramString5, String paramString6, List<String> paramList4, List<String> paramList5, String paramString7, String paramString8, String paramString9, List<String> paramList6, String paramString10)
  {
    this.zzBz = paramString1;
    this.zzBA = paramString2;
    this.zzBB = paramList1;
    this.zzBC = paramString3;
    this.zzBD = paramString4;
    this.zzBE = paramList2;
    this.zzBF = paramList3;
    this.zzBG = paramString5;
    this.zzBH = paramList4;
    this.zzBI = paramList5;
    this.zzBJ = paramString7;
    this.zzBK = paramString8;
    this.zzBL = paramString9;
    this.zzBM = paramList6;
    this.zzBN = paramString10;
  }
  
  public zzen(JSONObject paramJSONObject)
    throws JSONException
  {
    this.zzBA = paramJSONObject.getString("id");
    Object localObject1 = paramJSONObject.getJSONArray("adapters");
    Object localObject3 = new ArrayList(((JSONArray)localObject1).length());
    int i = 0;
    while (i < ((JSONArray)localObject1).length())
    {
      ((List)localObject3).add(((JSONArray)localObject1).getString(i));
      i += 1;
    }
    this.zzBB = Collections.unmodifiableList((List)localObject3);
    this.zzBC = paramJSONObject.optString("allocation_id", null);
    this.zzBE = zzr.zzbP().zza(paramJSONObject, "clickurl");
    this.zzBF = zzr.zzbP().zza(paramJSONObject, "imp_urls");
    this.zzBH = zzr.zzbP().zza(paramJSONObject, "video_start_urls");
    this.zzBI = zzr.zzbP().zza(paramJSONObject, "video_complete_urls");
    localObject1 = paramJSONObject.optJSONObject("ad");
    if (localObject1 != null)
    {
      localObject1 = ((JSONObject)localObject1).toString();
      this.zzBz = ((String)localObject1);
      localObject3 = paramJSONObject.optJSONObject("data");
      if (localObject3 == null) {
        break label288;
      }
      localObject1 = ((JSONObject)localObject3).toString();
      label179:
      this.zzBG = ((String)localObject1);
      if (localObject3 == null) {
        break label293;
      }
      localObject1 = ((JSONObject)localObject3).optString("class_name");
      label197:
      this.zzBD = ((String)localObject1);
      this.zzBJ = paramJSONObject.optString("html_template", null);
      this.zzBK = paramJSONObject.optString("ad_base_url", null);
      localObject1 = paramJSONObject.optJSONObject("assets");
      if (localObject1 == null) {
        break label298;
      }
    }
    label288:
    label293:
    label298:
    for (localObject1 = ((JSONObject)localObject1).toString();; localObject1 = null)
    {
      this.zzBL = ((String)localObject1);
      this.zzBM = zzr.zzbP().zza(paramJSONObject, "template_ids");
      localObject1 = paramJSONObject.optJSONObject("ad_loader_options");
      paramJSONObject = (JSONObject)localObject2;
      if (localObject1 != null) {
        paramJSONObject = ((JSONObject)localObject1).toString();
      }
      this.zzBN = paramJSONObject;
      return;
      localObject1 = null;
      break;
      localObject1 = null;
      break label179;
      localObject1 = null;
      break label197;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */