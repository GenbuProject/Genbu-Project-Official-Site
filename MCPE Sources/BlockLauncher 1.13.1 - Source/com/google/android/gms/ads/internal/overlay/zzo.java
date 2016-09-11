package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.internal.zzhb;

@zzhb
public class zzo
  extends FrameLayout
  implements View.OnClickListener
{
  private final ImageButton zzEW;
  private final zzs zzEX;
  
  public zzo(Context paramContext, int paramInt, zzs paramzzs)
  {
    super(paramContext);
    this.zzEX = paramzzs;
    setOnClickListener(this);
    this.zzEW = new ImageButton(paramContext);
    this.zzEW.setImageResource(17301527);
    this.zzEW.setBackgroundColor(0);
    this.zzEW.setOnClickListener(this);
    this.zzEW.setPadding(0, 0, 0, 0);
    this.zzEW.setContentDescription("Interstitial close button");
    paramInt = zzn.zzcS().zzb(paramContext, paramInt);
    addView(this.zzEW, new FrameLayout.LayoutParams(paramInt, paramInt, 17));
  }
  
  public void onClick(View paramView)
  {
    if (this.zzEX != null) {
      this.zzEX.zzfm();
    }
  }
  
  public void zza(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean2)
    {
      if (paramBoolean1)
      {
        this.zzEW.setVisibility(4);
        return;
      }
      this.zzEW.setVisibility(8);
      return;
    }
    this.zzEW.setVisibility(0);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\overlay\zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */