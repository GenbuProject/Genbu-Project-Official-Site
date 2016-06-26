package com.google.android.gms.common.api.internal;

import android.app.Dialog;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.annotation.KeepName;

@KeepName
public class SupportLifecycleFragmentImpl
  extends zzw
{
  protected void zzb(int paramInt, ConnectionResult paramConnectionResult)
  {
    GooglePlayServicesUtil.showErrorDialogFragment(paramConnectionResult.getErrorCode(), getActivity(), this, 2, this);
  }
  
  protected void zzc(int paramInt, final ConnectionResult paramConnectionResult)
  {
    paramConnectionResult = zzpS().zza(getActivity(), this);
    this.zzaiD = zzn.zza(getActivity().getApplicationContext(), new zzn()
    {
      protected void zzpJ()
      {
        SupportLifecycleFragmentImpl.this.zzpP();
        paramConnectionResult.dismiss();
      }
    });
  }
  
  protected GoogleApiAvailability zzpS()
  {
    return GoogleApiAvailability.getInstance();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\api\internal\SupportLifecycleFragmentImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */