package com.microsoft.onlineid.internal.sso.client;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.sso.SignatureVerifier;
import com.microsoft.onlineid.internal.sso.SsoService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ServiceFinder
{
  public static final Comparator<SsoService> MasterPrecedenceComparator = new Comparator()
  {
    public int compare(SsoService paramAnonymousSsoService1, SsoService paramAnonymousSsoService2)
    {
      int i;
      if (paramAnonymousSsoService1.getPackageName().equals("com.microsoft.msa.authenticator")) {
        i = -1;
      }
      int j;
      do
      {
        return i;
        if (paramAnonymousSsoService2.getPackageName().equals("com.microsoft.msa.authenticator")) {
          return 1;
        }
        j = paramAnonymousSsoService2.getSsoVersion() - paramAnonymousSsoService1.getSsoVersion();
        i = j;
      } while (j != 0);
      return (int)(paramAnonymousSsoService1.getFirstInstallTime() - paramAnonymousSsoService2.getFirstInstallTime());
    }
  };
  public static final String SdkVersionMetaDataName = "com.microsoft.msa.service.sdk_version";
  public static final String SsoVersionMetaDataName = "com.microsoft.msa.service.sso_version";
  private final Context _applicationContext;
  private final SignatureVerifier _signatureVerifier;
  
  public ServiceFinder(Context paramContext)
  {
    this._applicationContext = paramContext;
    this._signatureVerifier = new SignatureVerifier(paramContext);
  }
  
  protected long getFirstInstallTime(String paramString)
    throws PackageManager.NameNotFoundException
  {
    return this._applicationContext.getPackageManager().getPackageInfo(paramString, 0).firstInstallTime;
  }
  
  public List<SsoService> getOrderedSsoServices()
  {
    List localList = getTrustedSsoServices();
    Collections.sort(localList, MasterPrecedenceComparator);
    Logger.info("Available trusted/ordered SSO services: " + Arrays.toString(localList.toArray()));
    return localList;
  }
  
  protected SsoService getSelfSsoService()
  {
    return new SsoService(this._applicationContext.getPackageName(), 0, "", 0L);
  }
  
  public SsoService getSsoService(String paramString)
  {
    if (paramString != null)
    {
      Iterator localIterator = getOrderedSsoServices().iterator();
      while (localIterator.hasNext())
      {
        SsoService localSsoService = (SsoService)localIterator.next();
        if (localSsoService.getPackageName().equalsIgnoreCase(paramString)) {
          return localSsoService;
        }
      }
    }
    return null;
  }
  
  protected List<SsoService> getTrustedSsoServices()
  {
    Object localObject1 = new Intent("com.microsoft.msa.action.SSO_SERVICE");
    Object localObject2 = this._applicationContext.getPackageManager().queryIntentServices((Intent)localObject1, 128);
    localObject1 = new ArrayList();
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      Object localObject3 = (ResolveInfo)((Iterator)localObject2).next();
      String str = ((ResolveInfo)localObject3).serviceInfo.applicationInfo.packageName;
      localObject3 = ((ResolveInfo)localObject3).serviceInfo.metaData;
      int i = ((Bundle)localObject3).getInt("com.microsoft.msa.service.sso_version");
      if (!this._signatureVerifier.isTrusted(str)) {
        Logger.warning("Disallowing SSO with " + str + " because it is not trusted.");
      } else if (i <= 1) {
        Logger.warning("Disallowing  SSO with " + str + " because its SSO version is " + i + ".");
      } else {
        try
        {
          ((List)localObject1).add(new SsoService(str, i, ((Bundle)localObject3).getString("com.microsoft.msa.service.sdk_version"), getFirstInstallTime(str)));
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          Logger.error("Could not find package when querying for first install time: " + str, localNameNotFoundException);
        }
      }
    }
    ClientAnalytics.get().logEvent("SDK", "Total trusted SSO services", String.valueOf(((List)localObject1).size()));
    return (List<SsoService>)localObject1;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\sso\client\ServiceFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */