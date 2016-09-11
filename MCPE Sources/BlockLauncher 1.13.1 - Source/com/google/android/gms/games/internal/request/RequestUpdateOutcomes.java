package com.google.android.gms.games.internal.request;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.games.internal.constants.RequestUpdateResultOutcome;
import java.util.HashMap;
import java.util.Set;

public final class RequestUpdateOutcomes
{
  private static final String[] zzaIX = { "requestId", "outcome" };
  private final HashMap<String, Integer> zzaIY;
  private final int zzade;
  
  private RequestUpdateOutcomes(int paramInt, HashMap<String, Integer> paramHashMap)
  {
    this.zzade = paramInt;
    this.zzaIY = paramHashMap;
  }
  
  public static RequestUpdateOutcomes zzab(DataHolder paramDataHolder)
  {
    Builder localBuilder = new Builder();
    localBuilder.zzgG(paramDataHolder.getStatusCode());
    int j = paramDataHolder.getCount();
    int i = 0;
    while (i < j)
    {
      int k = paramDataHolder.zzbH(i);
      localBuilder.zzx(paramDataHolder.zzd("requestId", i, k), paramDataHolder.zzc("outcome", i, k));
      i += 1;
    }
    return localBuilder.zzxH();
  }
  
  public Set<String> getRequestIds()
  {
    return this.zzaIY.keySet();
  }
  
  public int getRequestOutcome(String paramString)
  {
    zzx.zzb(this.zzaIY.containsKey(paramString), "Request " + paramString + " was not part of the update operation!");
    return ((Integer)this.zzaIY.get(paramString)).intValue();
  }
  
  public static final class Builder
  {
    private HashMap<String, Integer> zzaIY = new HashMap();
    private int zzade = 0;
    
    public Builder zzgG(int paramInt)
    {
      this.zzade = paramInt;
      return this;
    }
    
    public Builder zzx(String paramString, int paramInt)
    {
      if (RequestUpdateResultOutcome.isValid(paramInt)) {
        this.zzaIY.put(paramString, Integer.valueOf(paramInt));
      }
      return this;
    }
    
    public RequestUpdateOutcomes zzxH()
    {
      return new RequestUpdateOutcomes(this.zzade, this.zzaIY, null);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\internal\request\RequestUpdateOutcomes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */