package com.google.android.gms.ads.mediation;

import android.os.Bundle;
import android.view.View;

public abstract class NativeAdMapper
{
  protected Bundle mExtras = new Bundle();
  protected boolean mOverrideClickHandling;
  protected boolean mOverrideImpressionRecording;
  
  public final Bundle getExtras()
  {
    return this.mExtras;
  }
  
  public final boolean getOverrideClickHandling()
  {
    return this.mOverrideClickHandling;
  }
  
  public final boolean getOverrideImpressionRecording()
  {
    return this.mOverrideImpressionRecording;
  }
  
  public void handleClick(View paramView) {}
  
  public void recordImpression() {}
  
  public final void setExtras(Bundle paramBundle)
  {
    this.mExtras = paramBundle;
  }
  
  public final void setOverrideClickHandling(boolean paramBoolean)
  {
    this.mOverrideClickHandling = paramBoolean;
  }
  
  public final void setOverrideImpressionRecording(boolean paramBoolean)
  {
    this.mOverrideImpressionRecording = paramBoolean;
  }
  
  public void trackView(View paramView) {}
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\mediation\NativeAdMapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */