package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzaf.zzf;
import com.google.android.gms.internal.zzaf.zzi;
import com.google.android.gms.internal.zzaf.zzj;
import com.google.android.gms.internal.zzag.zza;
import com.google.android.gms.internal.zzrs;
import com.google.android.gms.internal.zzrs.zzc;
import com.google.android.gms.internal.zzrs.zzg;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container
{
  private final Context mContext;
  private final String zzbhM;
  private final DataLayer zzbhN;
  private zzcp zzbhO;
  private Map<String, FunctionCallMacroCallback> zzbhP = new HashMap();
  private Map<String, FunctionCallTagCallback> zzbhQ = new HashMap();
  private volatile long zzbhR;
  private volatile String zzbhS = "";
  
  Container(Context paramContext, DataLayer paramDataLayer, String paramString, long paramLong, zzaf.zzj paramzzj)
  {
    this.mContext = paramContext;
    this.zzbhN = paramDataLayer;
    this.zzbhM = paramString;
    this.zzbhR = paramLong;
    zza(paramzzj.zzju);
    if (paramzzj.zzjt != null) {
      zza(paramzzj.zzjt);
    }
  }
  
  Container(Context paramContext, DataLayer paramDataLayer, String paramString, long paramLong, zzrs.zzc paramzzc)
  {
    this.mContext = paramContext;
    this.zzbhN = paramDataLayer;
    this.zzbhM = paramString;
    this.zzbhR = paramLong;
    zza(paramzzc);
  }
  
  private zzcp zzGc()
  {
    try
    {
      zzcp localzzcp = this.zzbhO;
      return localzzcp;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private void zza(zzaf.zzf paramzzf)
  {
    if (paramzzf == null) {
      throw new NullPointerException();
    }
    try
    {
      zzrs.zzc localzzc = zzrs.zzb(paramzzf);
      zza(localzzc);
      return;
    }
    catch (zzrs.zzg localzzg)
    {
      zzbg.e("Not loading resource: " + paramzzf + " because it is invalid: " + localzzg.toString());
    }
  }
  
  private void zza(zzrs.zzc paramzzc)
  {
    this.zzbhS = paramzzc.getVersion();
    zzah localzzah = zzfS(this.zzbhS);
    zza(new zzcp(this.mContext, paramzzc, this.zzbhN, new zza(null), new zzb(null), localzzah));
    if (getBoolean("_gtm.loadEventEnabled")) {
      this.zzbhN.pushEvent("gtm.load", DataLayer.mapOf(new Object[] { "gtm.id", this.zzbhM }));
    }
  }
  
  private void zza(zzcp paramzzcp)
  {
    try
    {
      this.zzbhO = paramzzcp;
      return;
    }
    finally
    {
      paramzzcp = finally;
      throw paramzzcp;
    }
  }
  
  private void zza(zzaf.zzi[] paramArrayOfzzi)
  {
    ArrayList localArrayList = new ArrayList();
    int j = paramArrayOfzzi.length;
    int i = 0;
    while (i < j)
    {
      localArrayList.add(paramArrayOfzzi[i]);
      i += 1;
    }
    zzGc().zzF(localArrayList);
  }
  
  public boolean getBoolean(String paramString)
  {
    zzcp localzzcp = zzGc();
    if (localzzcp == null)
    {
      zzbg.e("getBoolean called for closed container.");
      return zzdf.zzHC().booleanValue();
    }
    try
    {
      boolean bool = zzdf.zzk((zzag.zza)localzzcp.zzgn(paramString).getObject()).booleanValue();
      return bool;
    }
    catch (Exception paramString)
    {
      zzbg.e("Calling getBoolean() threw an exception: " + paramString.getMessage() + " Returning default value.");
    }
    return zzdf.zzHC().booleanValue();
  }
  
  public String getContainerId()
  {
    return this.zzbhM;
  }
  
  public double getDouble(String paramString)
  {
    zzcp localzzcp = zzGc();
    if (localzzcp == null)
    {
      zzbg.e("getDouble called for closed container.");
      return zzdf.zzHB().doubleValue();
    }
    try
    {
      double d = zzdf.zzj((zzag.zza)localzzcp.zzgn(paramString).getObject()).doubleValue();
      return d;
    }
    catch (Exception paramString)
    {
      zzbg.e("Calling getDouble() threw an exception: " + paramString.getMessage() + " Returning default value.");
    }
    return zzdf.zzHB().doubleValue();
  }
  
  public long getLastRefreshTime()
  {
    return this.zzbhR;
  }
  
  public long getLong(String paramString)
  {
    zzcp localzzcp = zzGc();
    if (localzzcp == null)
    {
      zzbg.e("getLong called for closed container.");
      return zzdf.zzHA().longValue();
    }
    try
    {
      long l = zzdf.zzi((zzag.zza)localzzcp.zzgn(paramString).getObject()).longValue();
      return l;
    }
    catch (Exception paramString)
    {
      zzbg.e("Calling getLong() threw an exception: " + paramString.getMessage() + " Returning default value.");
    }
    return zzdf.zzHA().longValue();
  }
  
  public String getString(String paramString)
  {
    zzcp localzzcp = zzGc();
    if (localzzcp == null)
    {
      zzbg.e("getString called for closed container.");
      return zzdf.zzHE();
    }
    try
    {
      paramString = zzdf.zzg((zzag.zza)localzzcp.zzgn(paramString).getObject());
      return paramString;
    }
    catch (Exception paramString)
    {
      zzbg.e("Calling getString() threw an exception: " + paramString.getMessage() + " Returning default value.");
    }
    return zzdf.zzHE();
  }
  
  public boolean isDefault()
  {
    return getLastRefreshTime() == 0L;
  }
  
  public void registerFunctionCallMacroCallback(String paramString, FunctionCallMacroCallback paramFunctionCallMacroCallback)
  {
    if (paramFunctionCallMacroCallback == null) {
      throw new NullPointerException("Macro handler must be non-null");
    }
    synchronized (this.zzbhP)
    {
      this.zzbhP.put(paramString, paramFunctionCallMacroCallback);
      return;
    }
  }
  
  public void registerFunctionCallTagCallback(String paramString, FunctionCallTagCallback paramFunctionCallTagCallback)
  {
    if (paramFunctionCallTagCallback == null) {
      throw new NullPointerException("Tag callback must be non-null");
    }
    synchronized (this.zzbhQ)
    {
      this.zzbhQ.put(paramString, paramFunctionCallTagCallback);
      return;
    }
  }
  
  void release()
  {
    this.zzbhO = null;
  }
  
  public void unregisterFunctionCallMacroCallback(String paramString)
  {
    synchronized (this.zzbhP)
    {
      this.zzbhP.remove(paramString);
      return;
    }
  }
  
  public void unregisterFunctionCallTagCallback(String paramString)
  {
    synchronized (this.zzbhQ)
    {
      this.zzbhQ.remove(paramString);
      return;
    }
  }
  
  public String zzGb()
  {
    return this.zzbhS;
  }
  
  FunctionCallMacroCallback zzfP(String paramString)
  {
    synchronized (this.zzbhP)
    {
      paramString = (FunctionCallMacroCallback)this.zzbhP.get(paramString);
      return paramString;
    }
  }
  
  public FunctionCallTagCallback zzfQ(String paramString)
  {
    synchronized (this.zzbhQ)
    {
      paramString = (FunctionCallTagCallback)this.zzbhQ.get(paramString);
      return paramString;
    }
  }
  
  public void zzfR(String paramString)
  {
    zzGc().zzfR(paramString);
  }
  
  zzah zzfS(String paramString)
  {
    if (zzcb.zzGU().zzGV().equals(zzcb.zza.zzbjW)) {}
    return new zzbo();
  }
  
  public static abstract interface FunctionCallMacroCallback
  {
    public abstract Object getValue(String paramString, Map<String, Object> paramMap);
  }
  
  public static abstract interface FunctionCallTagCallback
  {
    public abstract void execute(String paramString, Map<String, Object> paramMap);
  }
  
  private class zza
    implements zzt.zza
  {
    private zza() {}
    
    public Object zzc(String paramString, Map<String, Object> paramMap)
    {
      Container.FunctionCallMacroCallback localFunctionCallMacroCallback = Container.this.zzfP(paramString);
      if (localFunctionCallMacroCallback == null) {
        return null;
      }
      return localFunctionCallMacroCallback.getValue(paramString, paramMap);
    }
  }
  
  private class zzb
    implements zzt.zza
  {
    private zzb() {}
    
    public Object zzc(String paramString, Map<String, Object> paramMap)
    {
      Container.FunctionCallTagCallback localFunctionCallTagCallback = Container.this.zzfQ(paramString);
      if (localFunctionCallTagCallback != null) {
        localFunctionCallTagCallback.execute(paramString, paramMap);
      }
      return zzdf.zzHE();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\tagmanager\Container.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */