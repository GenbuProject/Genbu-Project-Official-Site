package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.internal.client.zzab;

public final class PublisherAdView
  extends ViewGroup
{
  private final zzab zzoJ;
  
  public PublisherAdView(Context paramContext)
  {
    super(paramContext);
    this.zzoJ = new zzab(this);
  }
  
  public PublisherAdView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.zzoJ = new zzab(this, paramAttributeSet, true);
  }
  
  public PublisherAdView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.zzoJ = new zzab(this, paramAttributeSet, true);
  }
  
  public void destroy()
  {
    this.zzoJ.destroy();
  }
  
  public AdListener getAdListener()
  {
    return this.zzoJ.getAdListener();
  }
  
  public AdSize getAdSize()
  {
    return this.zzoJ.getAdSize();
  }
  
  public AdSize[] getAdSizes()
  {
    return this.zzoJ.getAdSizes();
  }
  
  public String getAdUnitId()
  {
    return this.zzoJ.getAdUnitId();
  }
  
  public AppEventListener getAppEventListener()
  {
    return this.zzoJ.getAppEventListener();
  }
  
  public String getMediationAdapterClassName()
  {
    return this.zzoJ.getMediationAdapterClassName();
  }
  
  public OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener()
  {
    return this.zzoJ.getOnCustomRenderedAdLoadedListener();
  }
  
  public boolean isLoading()
  {
    return this.zzoJ.isLoading();
  }
  
  @RequiresPermission("android.permission.INTERNET")
  public void loadAd(PublisherAdRequest paramPublisherAdRequest)
  {
    this.zzoJ.zza(paramPublisherAdRequest.zzaE());
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    View localView = getChildAt(0);
    if ((localView != null) && (localView.getVisibility() != 8))
    {
      int i = localView.getMeasuredWidth();
      int j = localView.getMeasuredHeight();
      paramInt1 = (paramInt3 - paramInt1 - i) / 2;
      paramInt2 = (paramInt4 - paramInt2 - j) / 2;
      localView.layout(paramInt1, paramInt2, i + paramInt1, j + paramInt2);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = 0;
    Object localObject = getChildAt(0);
    int j;
    if ((localObject != null) && (((View)localObject).getVisibility() != 8))
    {
      measureChild((View)localObject, paramInt1, paramInt2);
      j = ((View)localObject).getMeasuredWidth();
      i = ((View)localObject).getMeasuredHeight();
    }
    for (;;)
    {
      j = Math.max(j, getSuggestedMinimumWidth());
      i = Math.max(i, getSuggestedMinimumHeight());
      setMeasuredDimension(View.resolveSize(j, paramInt1), View.resolveSize(i, paramInt2));
      return;
      localObject = getAdSize();
      if (localObject != null)
      {
        Context localContext = getContext();
        j = ((AdSize)localObject).getWidthInPixels(localContext);
        i = ((AdSize)localObject).getHeightInPixels(localContext);
      }
      else
      {
        j = 0;
      }
    }
  }
  
  public void pause()
  {
    this.zzoJ.pause();
  }
  
  public void recordManualImpression()
  {
    this.zzoJ.recordManualImpression();
  }
  
  public void resume()
  {
    this.zzoJ.resume();
  }
  
  public void setAdListener(AdListener paramAdListener)
  {
    this.zzoJ.setAdListener(paramAdListener);
  }
  
  public void setAdSizes(AdSize... paramVarArgs)
  {
    if ((paramVarArgs == null) || (paramVarArgs.length < 1)) {
      throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
    }
    this.zzoJ.zza(paramVarArgs);
  }
  
  public void setAdUnitId(String paramString)
  {
    this.zzoJ.setAdUnitId(paramString);
  }
  
  public void setAppEventListener(AppEventListener paramAppEventListener)
  {
    this.zzoJ.setAppEventListener(paramAppEventListener);
  }
  
  public void setCorrelator(Correlator paramCorrelator)
  {
    this.zzoJ.setCorrelator(paramCorrelator);
  }
  
  public void setManualImpressionsEnabled(boolean paramBoolean)
  {
    this.zzoJ.setManualImpressionsEnabled(paramBoolean);
  }
  
  public void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener paramOnCustomRenderedAdLoadedListener)
  {
    this.zzoJ.setOnCustomRenderedAdLoadedListener(paramOnCustomRenderedAdLoadedListener);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\doubleclick\PublisherAdView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */