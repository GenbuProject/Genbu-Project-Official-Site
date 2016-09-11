package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RawRes;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.PendingResult;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TagManager
{
  private static TagManager zzblm;
  private final Context mContext;
  private final DataLayer zzbhN;
  private final zzs zzbkh;
  private final zza zzblj;
  private final zzct zzblk;
  private final ConcurrentMap<zzo, Boolean> zzbll;
  
  TagManager(Context paramContext, zza paramzza, DataLayer paramDataLayer, zzct paramzzct)
  {
    if (paramContext == null) {
      throw new NullPointerException("context cannot be null");
    }
    this.mContext = paramContext.getApplicationContext();
    this.zzblk = paramzzct;
    this.zzblj = paramzza;
    this.zzbll = new ConcurrentHashMap();
    this.zzbhN = paramDataLayer;
    this.zzbhN.zza(new DataLayer.zzb()
    {
      public void zzQ(Map<String, Object> paramAnonymousMap)
      {
        paramAnonymousMap = paramAnonymousMap.get("event");
        if (paramAnonymousMap != null) {
          TagManager.zza(TagManager.this, paramAnonymousMap.toString());
        }
      }
    });
    this.zzbhN.zza(new zzd(this.mContext));
    this.zzbkh = new zzs();
    zzHt();
  }
  
  @RequiresPermission(allOf={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
  public static TagManager getInstance(Context paramContext)
  {
    try
    {
      if (zzblm != null) {
        break label68;
      }
      if (paramContext == null)
      {
        zzbg.e("TagManager.getInstance requires non-null context.");
        throw new NullPointerException();
      }
    }
    finally {}
    zzblm = new TagManager(paramContext, new zza()new DataLayernew zzw
    {
      public zzp zza(Context paramAnonymousContext, TagManager paramAnonymousTagManager, Looper paramAnonymousLooper, String paramAnonymousString, int paramAnonymousInt, zzs paramAnonymouszzs)
      {
        return new zzp(paramAnonymousContext, paramAnonymousTagManager, paramAnonymousLooper, paramAnonymousString, paramAnonymousInt, paramAnonymouszzs);
      }
    }, new DataLayer(new zzw(paramContext)), zzcu.zzHo());
    label68:
    paramContext = zzblm;
    return paramContext;
  }
  
  @TargetApi(14)
  private void zzHt()
  {
    if (Build.VERSION.SDK_INT >= 14) {
      this.mContext.registerComponentCallbacks(new ComponentCallbacks2()
      {
        public void onConfigurationChanged(Configuration paramAnonymousConfiguration) {}
        
        public void onLowMemory() {}
        
        public void onTrimMemory(int paramAnonymousInt)
        {
          if (paramAnonymousInt == 20) {
            TagManager.this.dispatch();
          }
        }
      });
    }
  }
  
  private void zzgp(String paramString)
  {
    Iterator localIterator = this.zzbll.keySet().iterator();
    while (localIterator.hasNext()) {
      ((zzo)localIterator.next()).zzfR(paramString);
    }
  }
  
  public void dispatch()
  {
    this.zzblk.dispatch();
  }
  
  public DataLayer getDataLayer()
  {
    return this.zzbhN;
  }
  
  public PendingResult<ContainerHolder> loadContainerDefaultOnly(String paramString, @RawRes int paramInt)
  {
    paramString = this.zzblj.zza(this.mContext, this, null, paramString, paramInt, this.zzbkh);
    paramString.zzGg();
    return paramString;
  }
  
  public PendingResult<ContainerHolder> loadContainerDefaultOnly(String paramString, @RawRes int paramInt, Handler paramHandler)
  {
    paramString = this.zzblj.zza(this.mContext, this, paramHandler.getLooper(), paramString, paramInt, this.zzbkh);
    paramString.zzGg();
    return paramString;
  }
  
  public PendingResult<ContainerHolder> loadContainerPreferFresh(String paramString, @RawRes int paramInt)
  {
    paramString = this.zzblj.zza(this.mContext, this, null, paramString, paramInt, this.zzbkh);
    paramString.zzGi();
    return paramString;
  }
  
  public PendingResult<ContainerHolder> loadContainerPreferFresh(String paramString, @RawRes int paramInt, Handler paramHandler)
  {
    paramString = this.zzblj.zza(this.mContext, this, paramHandler.getLooper(), paramString, paramInt, this.zzbkh);
    paramString.zzGi();
    return paramString;
  }
  
  public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String paramString, @RawRes int paramInt)
  {
    paramString = this.zzblj.zza(this.mContext, this, null, paramString, paramInt, this.zzbkh);
    paramString.zzGh();
    return paramString;
  }
  
  public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String paramString, @RawRes int paramInt, Handler paramHandler)
  {
    paramString = this.zzblj.zza(this.mContext, this, paramHandler.getLooper(), paramString, paramInt, this.zzbkh);
    paramString.zzGh();
    return paramString;
  }
  
  public void setVerboseLoggingEnabled(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 2;; i = 5)
    {
      zzbg.setLogLevel(i);
      return;
    }
  }
  
  public void zza(zzo paramzzo)
  {
    this.zzbll.put(paramzzo, Boolean.valueOf(true));
  }
  
  public boolean zzb(zzo paramzzo)
  {
    return this.zzbll.remove(paramzzo) != null;
  }
  
  boolean zzp(Uri paramUri)
  {
    for (;;)
    {
      boolean bool;
      Object localObject2;
      try
      {
        localObject1 = zzcb.zzGU();
        if (!((zzcb)localObject1).zzp(paramUri)) {
          break label229;
        }
        paramUri = ((zzcb)localObject1).getContainerId();
        int i = 4.zzblo[localObject1.zzGV().ordinal()];
        switch (i)
        {
        default: 
          bool = true;
          return bool;
        }
      }
      finally {}
      Object localObject1 = this.zzbll.keySet().iterator();
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = (zzo)((Iterator)localObject1).next();
        if (((zzo)localObject2).getContainerId().equals(paramUri))
        {
          ((zzo)localObject2).zzfT(null);
          ((zzo)localObject2).refresh();
        }
      }
      else
      {
        continue;
        localObject2 = this.zzbll.keySet().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          zzo localzzo = (zzo)((Iterator)localObject2).next();
          if (localzzo.getContainerId().equals(paramUri))
          {
            localzzo.zzfT(((zzcb)localObject1).zzGW());
            localzzo.refresh();
          }
          else if (localzzo.zzGd() != null)
          {
            localzzo.zzfT(null);
            localzzo.refresh();
          }
        }
        continue;
        label229:
        bool = false;
      }
    }
  }
  
  public static abstract interface zza
  {
    public abstract zzp zza(Context paramContext, TagManager paramTagManager, Looper paramLooper, String paramString, int paramInt, zzs paramzzs);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\tagmanager\TagManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */