package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zza;
import com.google.android.gms.analytics.internal.zzab;
import com.google.android.gms.analytics.internal.zzad;
import com.google.android.gms.analytics.internal.zzaf;
import com.google.android.gms.analytics.internal.zzal;
import com.google.android.gms.analytics.internal.zzam;
import com.google.android.gms.analytics.internal.zzan;
import com.google.android.gms.analytics.internal.zzb;
import com.google.android.gms.analytics.internal.zzd;
import com.google.android.gms.analytics.internal.zze;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.analytics.internal.zzh;
import com.google.android.gms.analytics.internal.zzk;
import com.google.android.gms.analytics.internal.zzn;
import com.google.android.gms.analytics.internal.zzu;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.internal.zzpq;
import com.google.android.gms.internal.zzps;
import com.google.android.gms.measurement.zzg;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class Tracker
  extends zzd
{
  private boolean zzPs;
  private final Map<String, String> zzPt = new HashMap();
  private final zzad zzPu;
  private final zza zzPv;
  private ExceptionReporter zzPw;
  private zzal zzPx;
  private final Map<String, String> zzxA = new HashMap();
  
  Tracker(zzf paramzzf, String paramString, zzad paramzzad)
  {
    super(paramzzf);
    if (paramString != null) {
      this.zzxA.put("&tid", paramString);
    }
    this.zzxA.put("useSecure", "1");
    this.zzxA.put("&a", Integer.toString(new Random().nextInt(Integer.MAX_VALUE) + 1));
    if (paramzzad == null) {}
    for (this.zzPu = new zzad("tracking", zzjl());; this.zzPu = paramzzad)
    {
      this.zzPv = new zza(paramzzf);
      return;
    }
  }
  
  private static boolean zza(Map.Entry<String, String> paramEntry)
  {
    String str = (String)paramEntry.getKey();
    paramEntry = (String)paramEntry.getValue();
    return (str.startsWith("&")) && (str.length() >= 2);
  }
  
  private static String zzb(Map.Entry<String, String> paramEntry)
  {
    if (!zza(paramEntry)) {
      return null;
    }
    return ((String)paramEntry.getKey()).substring(1);
  }
  
  private static void zzb(Map<String, String> paramMap1, Map<String, String> paramMap2)
  {
    zzx.zzz(paramMap2);
    if (paramMap1 == null) {}
    for (;;)
    {
      return;
      paramMap1 = paramMap1.entrySet().iterator();
      while (paramMap1.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap1.next();
        String str = zzb(localEntry);
        if (str != null) {
          paramMap2.put(str, localEntry.getValue());
        }
      }
    }
  }
  
  private static void zzc(Map<String, String> paramMap1, Map<String, String> paramMap2)
  {
    zzx.zzz(paramMap2);
    if (paramMap1 == null) {}
    for (;;)
    {
      return;
      paramMap1 = paramMap1.entrySet().iterator();
      while (paramMap1.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap1.next();
        String str = zzb(localEntry);
        if ((str != null) && (!paramMap2.containsKey(str))) {
          paramMap2.put(str, localEntry.getValue());
        }
      }
    }
  }
  
  private boolean zziK()
  {
    return this.zzPw != null;
  }
  
  static String zzn(Activity paramActivity)
  {
    zzx.zzz(paramActivity);
    paramActivity = paramActivity.getIntent();
    if (paramActivity == null) {}
    do
    {
      return null;
      paramActivity = paramActivity.getStringExtra("android.intent.extra.REFERRER_NAME");
    } while (TextUtils.isEmpty(paramActivity));
    return paramActivity;
  }
  
  public void enableAdvertisingIdCollection(boolean paramBoolean)
  {
    this.zzPs = paramBoolean;
  }
  
  public void enableAutoActivityTracking(boolean paramBoolean)
  {
    this.zzPv.enableAutoActivityTracking(paramBoolean);
  }
  
  public void enableExceptionReporting(boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        if (zziK() == paramBoolean) {
          return;
        }
        if (paramBoolean)
        {
          Context localContext = getContext();
          this.zzPw = new ExceptionReporter(this, Thread.getDefaultUncaughtExceptionHandler(), localContext);
          Thread.setDefaultUncaughtExceptionHandler(this.zzPw);
          zzbd("Uncaught exceptions will be reported to Google Analytics");
          return;
        }
      }
      finally {}
      Thread.setDefaultUncaughtExceptionHandler(this.zzPw.zziD());
      zzbd("Uncaught exceptions will not be reported to Google Analytics");
    }
  }
  
  public String get(String paramString)
  {
    zzjv();
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return null;
      if (this.zzxA.containsKey(paramString)) {
        return (String)this.zzxA.get(paramString);
      }
      if (paramString.equals("&ul")) {
        return zzam.zza(Locale.getDefault());
      }
      if (paramString.equals("&cid")) {
        return zzjr().zzkk();
      }
      if (paramString.equals("&sr")) {
        return zzju().zzla();
      }
      if (paramString.equals("&aid")) {
        return zzjt().zzjS().zzwK();
      }
      if (paramString.equals("&an")) {
        return zzjt().zzjS().zzlg();
      }
      if (paramString.equals("&av")) {
        return zzjt().zzjS().zzli();
      }
    } while (!paramString.equals("&aiid"));
    return zzjt().zzjS().zzAJ();
  }
  
  public void send(final Map<String, String> paramMap)
  {
    final long l = zzjl().currentTimeMillis();
    if (zziC().getAppOptOut())
    {
      zzbe("AppOptOut is set to true. Not sending Google Analytics hit");
      return;
    }
    boolean bool1 = zziC().isDryRunEnabled();
    final HashMap localHashMap = new HashMap();
    zzb(this.zzxA, localHashMap);
    zzb(paramMap, localHashMap);
    final boolean bool2 = zzam.zzh((String)this.zzxA.get("useSecure"), true);
    zzc(this.zzPt, localHashMap);
    this.zzPt.clear();
    paramMap = (String)localHashMap.get("t");
    if (TextUtils.isEmpty(paramMap))
    {
      zzjm().zzh(localHashMap, "Missing hit type parameter");
      return;
    }
    final String str = (String)localHashMap.get("tid");
    if (TextUtils.isEmpty(str))
    {
      zzjm().zzh(localHashMap, "Missing tracking id parameter");
      return;
    }
    final boolean bool3 = zziL();
    try
    {
      if (("screenview".equalsIgnoreCase(paramMap)) || ("pageview".equalsIgnoreCase(paramMap)) || ("appview".equalsIgnoreCase(paramMap)) || (TextUtils.isEmpty(paramMap)))
      {
        int j = Integer.parseInt((String)this.zzxA.get("&a")) + 1;
        int i = j;
        if (j >= Integer.MAX_VALUE) {
          i = 1;
        }
        this.zzxA.put("&a", Integer.toString(i));
      }
      zzjo().zzf(new Runnable()
      {
        public void run()
        {
          boolean bool = true;
          if (Tracker.zza(Tracker.this).zziM()) {
            localHashMap.put("sc", "start");
          }
          zzam.zzd(localHashMap, "cid", Tracker.this.zziC().getClientId());
          Object localObject = (String)localHashMap.get("sf");
          if (localObject != null)
          {
            double d = zzam.zza((String)localObject, 100.0D);
            if (zzam.zza(d, (String)localHashMap.get("cid")))
            {
              Tracker.this.zzb("Sampling enabled. Hit sampled out. sample rate", Double.valueOf(d));
              return;
            }
          }
          localObject = Tracker.zzb(Tracker.this);
          if (bool3)
          {
            zzam.zzb(localHashMap, "ate", ((zza)localObject).zziU());
            zzam.zzc(localHashMap, "adid", ((zza)localObject).zziY());
            localObject = Tracker.zzc(Tracker.this).zzjS();
            zzam.zzc(localHashMap, "an", ((zzpq)localObject).zzlg());
            zzam.zzc(localHashMap, "av", ((zzpq)localObject).zzli());
            zzam.zzc(localHashMap, "aid", ((zzpq)localObject).zzwK());
            zzam.zzc(localHashMap, "aiid", ((zzpq)localObject).zzAJ());
            localHashMap.put("v", "1");
            localHashMap.put("_v", zze.zzQm);
            zzam.zzc(localHashMap, "ul", Tracker.zzd(Tracker.this).zzkZ().getLanguage());
            zzam.zzc(localHashMap, "sr", Tracker.zze(Tracker.this).zzla());
            if ((!paramMap.equals("transaction")) && (!paramMap.equals("item"))) {
              break label383;
            }
          }
          label383:
          for (int i = 1;; i = 0)
          {
            if ((i != 0) || (Tracker.zzf(Tracker.this).zzlw())) {
              break label388;
            }
            Tracker.zzg(Tracker.this).zzh(localHashMap, "Too many hits sent too quickly, rate limiting invoked");
            return;
            localHashMap.remove("ate");
            localHashMap.remove("adid");
            break;
          }
          label388:
          long l2 = zzam.zzbt((String)localHashMap.get("ht"));
          long l1 = l2;
          if (l2 == 0L) {
            l1 = l;
          }
          if (bool2)
          {
            localObject = new zzab(Tracker.this, localHashMap, l1, str);
            Tracker.zzh(Tracker.this).zzc("Dry run enabled. Would have sent hit", localObject);
            return;
          }
          localObject = (String)localHashMap.get("cid");
          HashMap localHashMap = new HashMap();
          zzam.zza(localHashMap, "uid", localHashMap);
          zzam.zza(localHashMap, "an", localHashMap);
          zzam.zza(localHashMap, "aid", localHashMap);
          zzam.zza(localHashMap, "av", localHashMap);
          zzam.zza(localHashMap, "aiid", localHashMap);
          String str = this.zzPE;
          if (!TextUtils.isEmpty((CharSequence)localHashMap.get("adid"))) {}
          for (;;)
          {
            localObject = new zzh(0L, (String)localObject, str, bool, 0L, localHashMap);
            l2 = Tracker.zzi(Tracker.this).zza((zzh)localObject);
            localHashMap.put("_s", String.valueOf(l2));
            localObject = new zzab(Tracker.this, localHashMap, l1, str);
            Tracker.zzj(Tracker.this).zza((zzab)localObject);
            return;
            bool = false;
          }
        }
      });
      return;
    }
    finally {}
  }
  
  public void set(String paramString1, String paramString2)
  {
    zzx.zzb(paramString1, "Key should be non-null");
    if (TextUtils.isEmpty(paramString1)) {
      return;
    }
    this.zzxA.put(paramString1, paramString2);
  }
  
  public void setAnonymizeIp(boolean paramBoolean)
  {
    set("&aip", zzam.zzK(paramBoolean));
  }
  
  public void setAppId(String paramString)
  {
    set("&aid", paramString);
  }
  
  public void setAppInstallerId(String paramString)
  {
    set("&aiid", paramString);
  }
  
  public void setAppName(String paramString)
  {
    set("&an", paramString);
  }
  
  public void setAppVersion(String paramString)
  {
    set("&av", paramString);
  }
  
  public void setCampaignParamsOnNextHit(Uri paramUri)
  {
    if ((paramUri == null) || (paramUri.isOpaque())) {}
    do
    {
      do
      {
        return;
        paramUri = paramUri.getQueryParameter("referrer");
      } while (TextUtils.isEmpty(paramUri));
      paramUri = Uri.parse("http://hostname/?" + paramUri);
      String str = paramUri.getQueryParameter("utm_id");
      if (str != null) {
        this.zzPt.put("&ci", str);
      }
      str = paramUri.getQueryParameter("anid");
      if (str != null) {
        this.zzPt.put("&anid", str);
      }
      str = paramUri.getQueryParameter("utm_campaign");
      if (str != null) {
        this.zzPt.put("&cn", str);
      }
      str = paramUri.getQueryParameter("utm_content");
      if (str != null) {
        this.zzPt.put("&cc", str);
      }
      str = paramUri.getQueryParameter("utm_medium");
      if (str != null) {
        this.zzPt.put("&cm", str);
      }
      str = paramUri.getQueryParameter("utm_source");
      if (str != null) {
        this.zzPt.put("&cs", str);
      }
      str = paramUri.getQueryParameter("utm_term");
      if (str != null) {
        this.zzPt.put("&ck", str);
      }
      str = paramUri.getQueryParameter("dclid");
      if (str != null) {
        this.zzPt.put("&dclid", str);
      }
      str = paramUri.getQueryParameter("gclid");
      if (str != null) {
        this.zzPt.put("&gclid", str);
      }
      paramUri = paramUri.getQueryParameter("aclid");
    } while (paramUri == null);
    this.zzPt.put("&aclid", paramUri);
  }
  
  public void setClientId(String paramString)
  {
    set("&cid", paramString);
  }
  
  public void setEncoding(String paramString)
  {
    set("&de", paramString);
  }
  
  public void setHostname(String paramString)
  {
    set("&dh", paramString);
  }
  
  public void setLanguage(String paramString)
  {
    set("&ul", paramString);
  }
  
  public void setLocation(String paramString)
  {
    set("&dl", paramString);
  }
  
  public void setPage(String paramString)
  {
    set("&dp", paramString);
  }
  
  public void setReferrer(String paramString)
  {
    set("&dr", paramString);
  }
  
  public void setSampleRate(double paramDouble)
  {
    set("&sf", Double.toString(paramDouble));
  }
  
  public void setScreenColors(String paramString)
  {
    set("&sd", paramString);
  }
  
  public void setScreenName(String paramString)
  {
    set("&cd", paramString);
  }
  
  public void setScreenResolution(int paramInt1, int paramInt2)
  {
    if ((paramInt1 < 0) && (paramInt2 < 0))
    {
      zzbg("Invalid width or height. The values should be non-negative.");
      return;
    }
    set("&sr", paramInt1 + "x" + paramInt2);
  }
  
  public void setSessionTimeout(long paramLong)
  {
    this.zzPv.setSessionTimeout(1000L * paramLong);
  }
  
  public void setTitle(String paramString)
  {
    set("&dt", paramString);
  }
  
  public void setUseSecure(boolean paramBoolean)
  {
    set("useSecure", zzam.zzK(paramBoolean));
  }
  
  public void setViewportSize(String paramString)
  {
    set("&vp", paramString);
  }
  
  void zza(zzal paramzzal)
  {
    zzbd("Loading Tracker config values");
    this.zzPx = paramzzal;
    if (this.zzPx.zzlT())
    {
      paramzzal = this.zzPx.getTrackingId();
      set("&tid", paramzzal);
      zza("trackingId loaded", paramzzal);
    }
    if (this.zzPx.zzlU())
    {
      paramzzal = Double.toString(this.zzPx.zzlV());
      set("&sf", paramzzal);
      zza("Sample frequency loaded", paramzzal);
    }
    if (this.zzPx.zzlW())
    {
      int i = this.zzPx.getSessionTimeout();
      setSessionTimeout(i);
      zza("Session timeout loaded", Integer.valueOf(i));
    }
    boolean bool;
    if (this.zzPx.zzlX())
    {
      bool = this.zzPx.zzlY();
      enableAutoActivityTracking(bool);
      zza("Auto activity tracking loaded", Boolean.valueOf(bool));
    }
    if (this.zzPx.zzlZ())
    {
      bool = this.zzPx.zzma();
      if (bool) {
        set("&aip", "1");
      }
      zza("Anonymize ip loaded", Boolean.valueOf(bool));
    }
    enableExceptionReporting(this.zzPx.zzmb());
  }
  
  protected void zziJ()
  {
    this.zzPv.zza();
    String str = zziI().zzlg();
    if (str != null) {
      set("&an", str);
    }
    str = zziI().zzli();
    if (str != null) {
      set("&av", str);
    }
  }
  
  boolean zziL()
  {
    return this.zzPs;
  }
  
  private class zza
    extends zzd
    implements GoogleAnalytics.zza
  {
    private boolean zzPG;
    private int zzPH;
    private long zzPI = -1L;
    private boolean zzPJ;
    private long zzPK;
    
    protected zza(zzf paramzzf)
    {
      super();
    }
    
    private void zziN()
    {
      if ((this.zzPI >= 0L) || (this.zzPG))
      {
        zziC().zza(Tracker.zza(Tracker.this));
        return;
      }
      zziC().zzb(Tracker.zza(Tracker.this));
    }
    
    public void enableAutoActivityTracking(boolean paramBoolean)
    {
      this.zzPG = paramBoolean;
      zziN();
    }
    
    public void setSessionTimeout(long paramLong)
    {
      this.zzPI = paramLong;
      zziN();
    }
    
    protected void zziJ() {}
    
    public boolean zziM()
    {
      try
      {
        boolean bool = this.zzPJ;
        this.zzPJ = false;
        return bool;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
    
    boolean zziO()
    {
      return zzjl().elapsedRealtime() >= this.zzPK + Math.max(1000L, this.zzPI);
    }
    
    public void zzl(Activity paramActivity)
    {
      if ((this.zzPH == 0) && (zziO())) {
        this.zzPJ = true;
      }
      this.zzPH += 1;
      HashMap localHashMap;
      Tracker localTracker;
      if (this.zzPG)
      {
        localObject = paramActivity.getIntent();
        if (localObject != null) {
          Tracker.this.setCampaignParamsOnNextHit(((Intent)localObject).getData());
        }
        localHashMap = new HashMap();
        localHashMap.put("&t", "screenview");
        localTracker = Tracker.this;
        if (Tracker.zzk(Tracker.this) == null) {
          break label159;
        }
      }
      label159:
      for (Object localObject = Tracker.zzk(Tracker.this).zzo(paramActivity);; localObject = paramActivity.getClass().getCanonicalName())
      {
        localTracker.set("&cd", (String)localObject);
        if (TextUtils.isEmpty((CharSequence)localHashMap.get("&dr")))
        {
          paramActivity = Tracker.zzn(paramActivity);
          if (!TextUtils.isEmpty(paramActivity)) {
            localHashMap.put("&dr", paramActivity);
          }
        }
        Tracker.this.send(localHashMap);
        return;
      }
    }
    
    public void zzm(Activity paramActivity)
    {
      this.zzPH -= 1;
      this.zzPH = Math.max(0, this.zzPH);
      if (this.zzPH == 0) {
        this.zzPK = zzjl().elapsedRealtime();
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\analytics\Tracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */