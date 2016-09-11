package com.google.android.gms.internal;

import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.measurement.zze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class zzpt
  extends zze<zzpt>
{
  private ProductAction zzPn;
  private final Map<String, List<Product>> zzPo = new HashMap();
  private final List<Promotion> zzPp = new ArrayList();
  private final List<Product> zzPq = new ArrayList();
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    if (!this.zzPq.isEmpty()) {
      localHashMap.put("products", this.zzPq);
    }
    if (!this.zzPp.isEmpty()) {
      localHashMap.put("promotions", this.zzPp);
    }
    if (!this.zzPo.isEmpty()) {
      localHashMap.put("impressions", this.zzPo);
    }
    localHashMap.put("productAction", this.zzPn);
    return zzF(localHashMap);
  }
  
  public ProductAction zzAV()
  {
    return this.zzPn;
  }
  
  public List<Product> zzAW()
  {
    return Collections.unmodifiableList(this.zzPq);
  }
  
  public Map<String, List<Product>> zzAX()
  {
    return this.zzPo;
  }
  
  public List<Promotion> zzAY()
  {
    return Collections.unmodifiableList(this.zzPp);
  }
  
  public void zza(Product paramProduct, String paramString)
  {
    if (paramProduct == null) {
      return;
    }
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    if (!this.zzPo.containsKey(str)) {
      this.zzPo.put(str, new ArrayList());
    }
    ((List)this.zzPo.get(str)).add(paramProduct);
  }
  
  public void zza(zzpt paramzzpt)
  {
    paramzzpt.zzPq.addAll(this.zzPq);
    paramzzpt.zzPp.addAll(this.zzPp);
    Iterator localIterator = this.zzPo.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (Map.Entry)localIterator.next();
      String str = (String)((Map.Entry)localObject).getKey();
      localObject = ((List)((Map.Entry)localObject).getValue()).iterator();
      while (((Iterator)localObject).hasNext()) {
        paramzzpt.zza((Product)((Iterator)localObject).next(), str);
      }
    }
    if (this.zzPn != null) {
      paramzzpt.zzPn = this.zzPn;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzpt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */