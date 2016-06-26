package com.google.ads.mediation;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdLoader.Builder;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAdView;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationAdapter.zza;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzka;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

@zzhb
public abstract class AbstractAdViewAdapter
  implements MediationBannerAdapter, MediationNativeAdapter, MediationRewardedVideoAdAdapter, zzka
{
  public static final String AD_UNIT_ID_PARAMETER = "pubid";
  protected AdView zzaQ;
  protected InterstitialAd zzaR;
  private AdLoader zzaS;
  private Context zzaT;
  private InterstitialAd zzaU;
  private MediationRewardedVideoAdListener zzaV;
  private String zzaW;
  final RewardedVideoAdListener zzaX = new RewardedVideoAdListener()
  {
    public void onRewarded(RewardItem paramAnonymousRewardItem)
    {
      AbstractAdViewAdapter.zza(AbstractAdViewAdapter.this).onRewarded(AbstractAdViewAdapter.this, paramAnonymousRewardItem);
    }
    
    public void onRewardedVideoAdClosed()
    {
      AbstractAdViewAdapter.zza(AbstractAdViewAdapter.this).onAdClosed(AbstractAdViewAdapter.this);
      AbstractAdViewAdapter.zza(AbstractAdViewAdapter.this, null);
    }
    
    public void onRewardedVideoAdFailedToLoad(int paramAnonymousInt)
    {
      AbstractAdViewAdapter.zza(AbstractAdViewAdapter.this).onAdFailedToLoad(AbstractAdViewAdapter.this, paramAnonymousInt);
    }
    
    public void onRewardedVideoAdLeftApplication()
    {
      AbstractAdViewAdapter.zza(AbstractAdViewAdapter.this).onAdLeftApplication(AbstractAdViewAdapter.this);
    }
    
    public void onRewardedVideoAdLoaded()
    {
      AbstractAdViewAdapter.zza(AbstractAdViewAdapter.this).onAdLoaded(AbstractAdViewAdapter.this);
    }
    
    public void onRewardedVideoAdOpened()
    {
      AbstractAdViewAdapter.zza(AbstractAdViewAdapter.this).onAdOpened(AbstractAdViewAdapter.this);
    }
    
    public void onRewardedVideoStarted()
    {
      AbstractAdViewAdapter.zza(AbstractAdViewAdapter.this).onVideoStarted(AbstractAdViewAdapter.this);
    }
  };
  
  public String getAdUnitId(Bundle paramBundle)
  {
    return paramBundle.getString("pubid");
  }
  
  public View getBannerView()
  {
    return this.zzaQ;
  }
  
  public Bundle getInterstitialAdapterInfo()
  {
    return new MediationAdapter.zza().zzS(1).zziw();
  }
  
  public void initialize(Context paramContext, MediationAdRequest paramMediationAdRequest, String paramString, MediationRewardedVideoAdListener paramMediationRewardedVideoAdListener, Bundle paramBundle1, Bundle paramBundle2)
  {
    this.zzaT = paramContext.getApplicationContext();
    this.zzaW = paramString;
    this.zzaV = paramMediationRewardedVideoAdListener;
    this.zzaV.onInitializationSucceeded(this);
  }
  
  public boolean isInitialized()
  {
    return this.zzaV != null;
  }
  
  public void loadAd(MediationAdRequest paramMediationAdRequest, Bundle paramBundle1, Bundle paramBundle2)
  {
    if ((this.zzaT == null) || (this.zzaV == null))
    {
      zzin.e("AdMobAdapter.loadAd called before initialize.");
      return;
    }
    this.zzaU = new InterstitialAd(this.zzaT);
    this.zzaU.zza(true);
    this.zzaU.setAdUnitId(getAdUnitId(paramBundle1));
    this.zzaU.setRewardedVideoAdListener(this.zzaX);
    this.zzaU.zzm(this.zzaW);
    this.zzaU.loadAd(zza(this.zzaT, paramMediationAdRequest, paramBundle2, paramBundle1));
  }
  
  public void onDestroy()
  {
    if (this.zzaQ != null)
    {
      this.zzaQ.destroy();
      this.zzaQ = null;
    }
    if (this.zzaR != null) {
      this.zzaR = null;
    }
    if (this.zzaS != null) {
      this.zzaS = null;
    }
    if (this.zzaU != null) {
      this.zzaU = null;
    }
  }
  
  public void onPause()
  {
    if (this.zzaQ != null) {
      this.zzaQ.pause();
    }
  }
  
  public void onResume()
  {
    if (this.zzaQ != null) {
      this.zzaQ.resume();
    }
  }
  
  public void requestBannerAd(Context paramContext, MediationBannerListener paramMediationBannerListener, Bundle paramBundle1, AdSize paramAdSize, MediationAdRequest paramMediationAdRequest, Bundle paramBundle2)
  {
    this.zzaQ = new AdView(paramContext);
    this.zzaQ.setAdSize(new AdSize(paramAdSize.getWidth(), paramAdSize.getHeight()));
    this.zzaQ.setAdUnitId(getAdUnitId(paramBundle1));
    this.zzaQ.setAdListener(new zzc(this, paramMediationBannerListener));
    this.zzaQ.loadAd(zza(paramContext, paramMediationAdRequest, paramBundle2, paramBundle1));
  }
  
  public void requestInterstitialAd(Context paramContext, MediationInterstitialListener paramMediationInterstitialListener, Bundle paramBundle1, MediationAdRequest paramMediationAdRequest, Bundle paramBundle2)
  {
    this.zzaR = new InterstitialAd(paramContext);
    this.zzaR.setAdUnitId(getAdUnitId(paramBundle1));
    this.zzaR.setAdListener(new zzd(this, paramMediationInterstitialListener));
    this.zzaR.loadAd(zza(paramContext, paramMediationAdRequest, paramBundle2, paramBundle1));
  }
  
  public void requestNativeAd(Context paramContext, MediationNativeListener paramMediationNativeListener, Bundle paramBundle1, NativeMediationAdRequest paramNativeMediationAdRequest, Bundle paramBundle2)
  {
    paramMediationNativeListener = new zze(this, paramMediationNativeListener);
    AdLoader.Builder localBuilder = zza(paramContext, paramBundle1.getString("pubid")).withAdListener(paramMediationNativeListener);
    NativeAdOptions localNativeAdOptions = paramNativeMediationAdRequest.getNativeAdOptions();
    if (localNativeAdOptions != null) {
      localBuilder.withNativeAdOptions(localNativeAdOptions);
    }
    if (paramNativeMediationAdRequest.isAppInstallAdRequested()) {
      localBuilder.forAppInstallAd(paramMediationNativeListener);
    }
    if (paramNativeMediationAdRequest.isContentAdRequested()) {
      localBuilder.forContentAd(paramMediationNativeListener);
    }
    this.zzaS = localBuilder.build();
    this.zzaS.loadAd(zza(paramContext, paramNativeMediationAdRequest, paramBundle2, paramBundle1));
  }
  
  public void showInterstitial()
  {
    this.zzaR.show();
  }
  
  public void showVideo()
  {
    this.zzaU.show();
  }
  
  protected abstract Bundle zza(Bundle paramBundle1, Bundle paramBundle2);
  
  AdLoader.Builder zza(Context paramContext, String paramString)
  {
    return new AdLoader.Builder(paramContext, paramString);
  }
  
  AdRequest zza(Context paramContext, MediationAdRequest paramMediationAdRequest, Bundle paramBundle1, Bundle paramBundle2)
  {
    AdRequest.Builder localBuilder = new AdRequest.Builder();
    Object localObject = paramMediationAdRequest.getBirthday();
    if (localObject != null) {
      localBuilder.setBirthday((Date)localObject);
    }
    int i = paramMediationAdRequest.getGender();
    if (i != 0) {
      localBuilder.setGender(i);
    }
    localObject = paramMediationAdRequest.getKeywords();
    if (localObject != null)
    {
      localObject = ((Set)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        localBuilder.addKeyword((String)((Iterator)localObject).next());
      }
    }
    localObject = paramMediationAdRequest.getLocation();
    if (localObject != null) {
      localBuilder.setLocation((Location)localObject);
    }
    if (paramMediationAdRequest.isTesting()) {
      localBuilder.addTestDevice(zzn.zzcS().zzT(paramContext));
    }
    if (paramMediationAdRequest.taggedForChildDirectedTreatment() != -1) {
      if (paramMediationAdRequest.taggedForChildDirectedTreatment() != 1) {
        break label210;
      }
    }
    label210:
    for (boolean bool = true;; bool = false)
    {
      localBuilder.tagForChildDirectedTreatment(bool);
      localBuilder.setIsDesignedForFamilies(paramMediationAdRequest.isDesignedForFamilies());
      localBuilder.addNetworkExtrasBundle(AdMobAdapter.class, zza(paramBundle1, paramBundle2));
      return localBuilder.build();
    }
  }
  
  static class zza
    extends NativeAppInstallAdMapper
  {
    private final NativeAppInstallAd zzaZ;
    
    public zza(NativeAppInstallAd paramNativeAppInstallAd)
    {
      this.zzaZ = paramNativeAppInstallAd;
      setHeadline(paramNativeAppInstallAd.getHeadline().toString());
      setImages(paramNativeAppInstallAd.getImages());
      setBody(paramNativeAppInstallAd.getBody().toString());
      setIcon(paramNativeAppInstallAd.getIcon());
      setCallToAction(paramNativeAppInstallAd.getCallToAction().toString());
      setStarRating(paramNativeAppInstallAd.getStarRating().doubleValue());
      setStore(paramNativeAppInstallAd.getStore().toString());
      setPrice(paramNativeAppInstallAd.getPrice().toString());
      setOverrideImpressionRecording(true);
      setOverrideClickHandling(true);
    }
    
    public void trackView(View paramView)
    {
      if ((paramView instanceof NativeAdView)) {
        ((NativeAdView)paramView).setNativeAd(this.zzaZ);
      }
    }
  }
  
  static class zzb
    extends NativeContentAdMapper
  {
    private final NativeContentAd zzba;
    
    public zzb(NativeContentAd paramNativeContentAd)
    {
      this.zzba = paramNativeContentAd;
      setHeadline(paramNativeContentAd.getHeadline().toString());
      setImages(paramNativeContentAd.getImages());
      setBody(paramNativeContentAd.getBody().toString());
      setLogo(paramNativeContentAd.getLogo());
      setCallToAction(paramNativeContentAd.getCallToAction().toString());
      setAdvertiser(paramNativeContentAd.getAdvertiser().toString());
      setOverrideImpressionRecording(true);
      setOverrideClickHandling(true);
    }
    
    public void trackView(View paramView)
    {
      if ((paramView instanceof NativeAdView)) {
        ((NativeAdView)paramView).setNativeAd(this.zzba);
      }
    }
  }
  
  static final class zzc
    extends AdListener
    implements com.google.android.gms.ads.internal.client.zza
  {
    final AbstractAdViewAdapter zzbb;
    final MediationBannerListener zzbc;
    
    public zzc(AbstractAdViewAdapter paramAbstractAdViewAdapter, MediationBannerListener paramMediationBannerListener)
    {
      this.zzbb = paramAbstractAdViewAdapter;
      this.zzbc = paramMediationBannerListener;
    }
    
    public void onAdClicked()
    {
      this.zzbc.onAdClicked(this.zzbb);
    }
    
    public void onAdClosed()
    {
      this.zzbc.onAdClosed(this.zzbb);
    }
    
    public void onAdFailedToLoad(int paramInt)
    {
      this.zzbc.onAdFailedToLoad(this.zzbb, paramInt);
    }
    
    public void onAdLeftApplication()
    {
      this.zzbc.onAdLeftApplication(this.zzbb);
    }
    
    public void onAdLoaded()
    {
      this.zzbc.onAdLoaded(this.zzbb);
    }
    
    public void onAdOpened()
    {
      this.zzbc.onAdOpened(this.zzbb);
    }
  }
  
  static final class zzd
    extends AdListener
    implements com.google.android.gms.ads.internal.client.zza
  {
    final AbstractAdViewAdapter zzbb;
    final MediationInterstitialListener zzbd;
    
    public zzd(AbstractAdViewAdapter paramAbstractAdViewAdapter, MediationInterstitialListener paramMediationInterstitialListener)
    {
      this.zzbb = paramAbstractAdViewAdapter;
      this.zzbd = paramMediationInterstitialListener;
    }
    
    public void onAdClicked()
    {
      this.zzbd.onAdClicked(this.zzbb);
    }
    
    public void onAdClosed()
    {
      this.zzbd.onAdClosed(this.zzbb);
    }
    
    public void onAdFailedToLoad(int paramInt)
    {
      this.zzbd.onAdFailedToLoad(this.zzbb, paramInt);
    }
    
    public void onAdLeftApplication()
    {
      this.zzbd.onAdLeftApplication(this.zzbb);
    }
    
    public void onAdLoaded()
    {
      this.zzbd.onAdLoaded(this.zzbb);
    }
    
    public void onAdOpened()
    {
      this.zzbd.onAdOpened(this.zzbb);
    }
  }
  
  static final class zze
    extends AdListener
    implements NativeAppInstallAd.OnAppInstallAdLoadedListener, NativeContentAd.OnContentAdLoadedListener, com.google.android.gms.ads.internal.client.zza
  {
    final AbstractAdViewAdapter zzbb;
    final MediationNativeListener zzbe;
    
    public zze(AbstractAdViewAdapter paramAbstractAdViewAdapter, MediationNativeListener paramMediationNativeListener)
    {
      this.zzbb = paramAbstractAdViewAdapter;
      this.zzbe = paramMediationNativeListener;
    }
    
    public void onAdClicked()
    {
      this.zzbe.onAdClicked(this.zzbb);
    }
    
    public void onAdClosed()
    {
      this.zzbe.onAdClosed(this.zzbb);
    }
    
    public void onAdFailedToLoad(int paramInt)
    {
      this.zzbe.onAdFailedToLoad(this.zzbb, paramInt);
    }
    
    public void onAdLeftApplication()
    {
      this.zzbe.onAdLeftApplication(this.zzbb);
    }
    
    public void onAdLoaded() {}
    
    public void onAdOpened()
    {
      this.zzbe.onAdOpened(this.zzbb);
    }
    
    public void onAppInstallAdLoaded(NativeAppInstallAd paramNativeAppInstallAd)
    {
      this.zzbe.onAdLoaded(this.zzbb, new AbstractAdViewAdapter.zza(paramNativeAppInstallAd));
    }
    
    public void onContentAdLoaded(NativeContentAd paramNativeContentAd)
    {
      this.zzbe.onAdLoaded(this.zzbb, new AbstractAdViewAdapter.zzb(paramNativeContentAd));
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\ads\mediation\AbstractAdViewAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */