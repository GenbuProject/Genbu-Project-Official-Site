package com.google.android.gms.wallet.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.android.gms.R.string;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.zza;
import com.google.android.gms.dynamic.zzb;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.internal.zzrx;
import com.google.android.gms.internal.zzry.zza;
import com.google.android.gms.internal.zzsf;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

@TargetApi(12)
public final class WalletFragment
  extends Fragment
{
  private boolean mCreated = false;
  private final Fragment zzavH = this;
  private WalletFragmentOptions zzbqa;
  private WalletFragmentInitParams zzbqb;
  private MaskedWalletRequest zzbqc;
  private MaskedWallet zzbqd;
  private Boolean zzbqe;
  private zzb zzbqj;
  private final zzb zzbqk = zzb.zza(this);
  private final zzc zzbql = new zzc(null);
  private zza zzbqm = new zza(this);
  
  public static WalletFragment newInstance(WalletFragmentOptions paramWalletFragmentOptions)
  {
    WalletFragment localWalletFragment = new WalletFragment();
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("extraWalletFragmentOptions", paramWalletFragmentOptions);
    localWalletFragment.zzavH.setArguments(localBundle);
    return localWalletFragment;
  }
  
  public int getState()
  {
    if (this.zzbqj != null) {
      return zzb.zza(this.zzbqj);
    }
    return 0;
  }
  
  public void initialize(WalletFragmentInitParams paramWalletFragmentInitParams)
  {
    if (this.zzbqj != null)
    {
      zzb.zza(this.zzbqj, paramWalletFragmentInitParams);
      this.zzbqb = null;
    }
    do
    {
      return;
      if (this.zzbqb != null) {
        break;
      }
      this.zzbqb = paramWalletFragmentInitParams;
      if (this.zzbqc != null) {
        Log.w("WalletFragment", "updateMaskedWalletRequest() was called before initialize()");
      }
    } while (this.zzbqd == null);
    Log.w("WalletFragment", "updateMaskedWallet() was called before initialize()");
    return;
    Log.w("WalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (this.zzbqj != null) {
      zzb.zza(this.zzbqj, paramInt1, paramInt2, paramIntent);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Object localObject;
    if (paramBundle != null)
    {
      paramBundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
      localObject = (WalletFragmentInitParams)paramBundle.getParcelable("walletFragmentInitParams");
      if (localObject != null)
      {
        if (this.zzbqb != null) {
          Log.w("WalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
        }
        this.zzbqb = ((WalletFragmentInitParams)localObject);
      }
      if (this.zzbqc == null) {
        this.zzbqc = ((MaskedWalletRequest)paramBundle.getParcelable("maskedWalletRequest"));
      }
      if (this.zzbqd == null) {
        this.zzbqd = ((MaskedWallet)paramBundle.getParcelable("maskedWallet"));
      }
      if (paramBundle.containsKey("walletFragmentOptions")) {
        this.zzbqa = ((WalletFragmentOptions)paramBundle.getParcelable("walletFragmentOptions"));
      }
      if (paramBundle.containsKey("enabled")) {
        this.zzbqe = Boolean.valueOf(paramBundle.getBoolean("enabled"));
      }
    }
    for (;;)
    {
      this.mCreated = true;
      this.zzbql.onCreate(paramBundle);
      return;
      if (this.zzavH.getArguments() != null)
      {
        localObject = (WalletFragmentOptions)this.zzavH.getArguments().getParcelable("extraWalletFragmentOptions");
        if (localObject != null)
        {
          ((WalletFragmentOptions)localObject).zzbc(this.zzavH.getActivity());
          this.zzbqa = ((WalletFragmentOptions)localObject);
        }
      }
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return this.zzbql.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    this.mCreated = false;
  }
  
  public void onInflate(Activity paramActivity, AttributeSet paramAttributeSet, Bundle paramBundle)
  {
    super.onInflate(paramActivity, paramAttributeSet, paramBundle);
    if (this.zzbqa == null) {
      this.zzbqa = WalletFragmentOptions.zzb(paramActivity, paramAttributeSet);
    }
    paramAttributeSet = new Bundle();
    paramAttributeSet.putParcelable("attrKeyWalletFragmentOptions", this.zzbqa);
    this.zzbql.onInflate(paramActivity, paramAttributeSet, paramBundle);
  }
  
  public void onPause()
  {
    super.onPause();
    this.zzbql.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    this.zzbql.onResume();
    FragmentManager localFragmentManager = this.zzavH.getActivity().getFragmentManager();
    Fragment localFragment = localFragmentManager.findFragmentByTag("GooglePlayServicesErrorDialog");
    if (localFragment != null)
    {
      localFragmentManager.beginTransaction().remove(localFragment).commit();
      GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.zzavH.getActivity()), this.zzavH.getActivity(), -1);
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
    this.zzbql.onSaveInstanceState(paramBundle);
    if (this.zzbqb != null)
    {
      paramBundle.putParcelable("walletFragmentInitParams", this.zzbqb);
      this.zzbqb = null;
    }
    if (this.zzbqc != null)
    {
      paramBundle.putParcelable("maskedWalletRequest", this.zzbqc);
      this.zzbqc = null;
    }
    if (this.zzbqd != null)
    {
      paramBundle.putParcelable("maskedWallet", this.zzbqd);
      this.zzbqd = null;
    }
    if (this.zzbqa != null)
    {
      paramBundle.putParcelable("walletFragmentOptions", this.zzbqa);
      this.zzbqa = null;
    }
    if (this.zzbqe != null)
    {
      paramBundle.putBoolean("enabled", this.zzbqe.booleanValue());
      this.zzbqe = null;
    }
  }
  
  public void onStart()
  {
    super.onStart();
    this.zzbql.onStart();
  }
  
  public void onStop()
  {
    super.onStop();
    this.zzbql.onStop();
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    if (this.zzbqj != null)
    {
      zzb.zza(this.zzbqj, paramBoolean);
      this.zzbqe = null;
      return;
    }
    this.zzbqe = Boolean.valueOf(paramBoolean);
  }
  
  public void setOnStateChangedListener(OnStateChangedListener paramOnStateChangedListener)
  {
    this.zzbqm.zza(paramOnStateChangedListener);
  }
  
  public void updateMaskedWallet(MaskedWallet paramMaskedWallet)
  {
    if (this.zzbqj != null)
    {
      zzb.zza(this.zzbqj, paramMaskedWallet);
      this.zzbqd = null;
      return;
    }
    this.zzbqd = paramMaskedWallet;
  }
  
  public void updateMaskedWalletRequest(MaskedWalletRequest paramMaskedWalletRequest)
  {
    if (this.zzbqj != null)
    {
      zzb.zza(this.zzbqj, paramMaskedWalletRequest);
      this.zzbqc = null;
      return;
    }
    this.zzbqc = paramMaskedWalletRequest;
  }
  
  public static abstract interface OnStateChangedListener
  {
    public abstract void onStateChanged(WalletFragment paramWalletFragment, int paramInt1, int paramInt2, Bundle paramBundle);
  }
  
  static class zza
    extends zzry.zza
  {
    private WalletFragment.OnStateChangedListener zzbqn;
    private final WalletFragment zzbqo;
    
    zza(WalletFragment paramWalletFragment)
    {
      this.zzbqo = paramWalletFragment;
    }
    
    public void zza(int paramInt1, int paramInt2, Bundle paramBundle)
    {
      if (this.zzbqn != null) {
        this.zzbqn.onStateChanged(this.zzbqo, paramInt1, paramInt2, paramBundle);
      }
    }
    
    public void zza(WalletFragment.OnStateChangedListener paramOnStateChangedListener)
    {
      this.zzbqn = paramOnStateChangedListener;
    }
  }
  
  private static class zzb
    implements LifecycleDelegate
  {
    private final zzrx zzbqh;
    
    private zzb(zzrx paramzzrx)
    {
      this.zzbqh = paramzzrx;
    }
    
    private int getState()
    {
      try
      {
        int i = this.zzbqh.getState();
        return i;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeException(localRemoteException);
      }
    }
    
    private void initialize(WalletFragmentInitParams paramWalletFragmentInitParams)
    {
      try
      {
        this.zzbqh.initialize(paramWalletFragmentInitParams);
        return;
      }
      catch (RemoteException paramWalletFragmentInitParams)
      {
        throw new RuntimeException(paramWalletFragmentInitParams);
      }
    }
    
    private void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
      try
      {
        this.zzbqh.onActivityResult(paramInt1, paramInt2, paramIntent);
        return;
      }
      catch (RemoteException paramIntent)
      {
        throw new RuntimeException(paramIntent);
      }
    }
    
    private void setEnabled(boolean paramBoolean)
    {
      try
      {
        this.zzbqh.setEnabled(paramBoolean);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeException(localRemoteException);
      }
    }
    
    private void updateMaskedWallet(MaskedWallet paramMaskedWallet)
    {
      try
      {
        this.zzbqh.updateMaskedWallet(paramMaskedWallet);
        return;
      }
      catch (RemoteException paramMaskedWallet)
      {
        throw new RuntimeException(paramMaskedWallet);
      }
    }
    
    private void updateMaskedWalletRequest(MaskedWalletRequest paramMaskedWalletRequest)
    {
      try
      {
        this.zzbqh.updateMaskedWalletRequest(paramMaskedWalletRequest);
        return;
      }
      catch (RemoteException paramMaskedWalletRequest)
      {
        throw new RuntimeException(paramMaskedWalletRequest);
      }
    }
    
    public void onCreate(Bundle paramBundle)
    {
      try
      {
        this.zzbqh.onCreate(paramBundle);
        return;
      }
      catch (RemoteException paramBundle)
      {
        throw new RuntimeException(paramBundle);
      }
    }
    
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      try
      {
        paramLayoutInflater = (View)zze.zzp(this.zzbqh.onCreateView(zze.zzC(paramLayoutInflater), zze.zzC(paramViewGroup), paramBundle));
        return paramLayoutInflater;
      }
      catch (RemoteException paramLayoutInflater)
      {
        throw new RuntimeException(paramLayoutInflater);
      }
    }
    
    public void onDestroy() {}
    
    public void onDestroyView() {}
    
    public void onInflate(Activity paramActivity, Bundle paramBundle1, Bundle paramBundle2)
    {
      paramBundle1 = (WalletFragmentOptions)paramBundle1.getParcelable("extraWalletFragmentOptions");
      try
      {
        this.zzbqh.zza(zze.zzC(paramActivity), paramBundle1, paramBundle2);
        return;
      }
      catch (RemoteException paramActivity)
      {
        throw new RuntimeException(paramActivity);
      }
    }
    
    public void onLowMemory() {}
    
    public void onPause()
    {
      try
      {
        this.zzbqh.onPause();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeException(localRemoteException);
      }
    }
    
    public void onResume()
    {
      try
      {
        this.zzbqh.onResume();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeException(localRemoteException);
      }
    }
    
    public void onSaveInstanceState(Bundle paramBundle)
    {
      try
      {
        this.zzbqh.onSaveInstanceState(paramBundle);
        return;
      }
      catch (RemoteException paramBundle)
      {
        throw new RuntimeException(paramBundle);
      }
    }
    
    public void onStart()
    {
      try
      {
        this.zzbqh.onStart();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeException(localRemoteException);
      }
    }
    
    public void onStop()
    {
      try
      {
        this.zzbqh.onStop();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeException(localRemoteException);
      }
    }
  }
  
  private class zzc
    extends zza<WalletFragment.zzb>
    implements View.OnClickListener
  {
    private zzc() {}
    
    public void onClick(View paramView)
    {
      paramView = WalletFragment.zza(WalletFragment.this).getActivity();
      GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramView), paramView, -1);
    }
    
    protected void zza(FrameLayout paramFrameLayout)
    {
      Button localButton = new Button(WalletFragment.zza(WalletFragment.this).getActivity());
      localButton.setText(R.string.wallet_buy_button_place_holder);
      int k = -1;
      int m = -2;
      int j = m;
      int i = k;
      if (WalletFragment.zze(WalletFragment.this) != null)
      {
        WalletFragmentStyle localWalletFragmentStyle = WalletFragment.zze(WalletFragment.this).getFragmentStyle();
        j = m;
        i = k;
        if (localWalletFragmentStyle != null)
        {
          DisplayMetrics localDisplayMetrics = WalletFragment.zza(WalletFragment.this).getResources().getDisplayMetrics();
          i = localWalletFragmentStyle.zza("buyButtonWidth", localDisplayMetrics, -1);
          j = localWalletFragmentStyle.zza("buyButtonHeight", localDisplayMetrics, -2);
        }
      }
      localButton.setLayoutParams(new ViewGroup.LayoutParams(i, j));
      localButton.setOnClickListener(this);
      paramFrameLayout.addView(localButton);
    }
    
    protected void zza(zzf<WalletFragment.zzb> paramzzf)
    {
      Object localObject = WalletFragment.zza(WalletFragment.this).getActivity();
      if ((WalletFragment.zzb(WalletFragment.this) == null) && (WalletFragment.zzc(WalletFragment.this)) && (localObject != null)) {}
      try
      {
        localObject = zzsf.zza((Activity)localObject, WalletFragment.zzd(WalletFragment.this), WalletFragment.zze(WalletFragment.this), WalletFragment.zzf(WalletFragment.this));
        WalletFragment.zza(WalletFragment.this, new WalletFragment.zzb((zzrx)localObject, null));
        WalletFragment.zza(WalletFragment.this, null);
        paramzzf.zza(WalletFragment.zzb(WalletFragment.this));
        if (WalletFragment.zzg(WalletFragment.this) != null)
        {
          WalletFragment.zzb.zza(WalletFragment.zzb(WalletFragment.this), WalletFragment.zzg(WalletFragment.this));
          WalletFragment.zza(WalletFragment.this, null);
        }
        if (WalletFragment.zzh(WalletFragment.this) != null)
        {
          WalletFragment.zzb.zza(WalletFragment.zzb(WalletFragment.this), WalletFragment.zzh(WalletFragment.this));
          WalletFragment.zza(WalletFragment.this, null);
        }
        if (WalletFragment.zzi(WalletFragment.this) != null)
        {
          WalletFragment.zzb.zza(WalletFragment.zzb(WalletFragment.this), WalletFragment.zzi(WalletFragment.this));
          WalletFragment.zza(WalletFragment.this, null);
        }
        if (WalletFragment.zzj(WalletFragment.this) != null)
        {
          WalletFragment.zzb.zza(WalletFragment.zzb(WalletFragment.this), WalletFragment.zzj(WalletFragment.this).booleanValue());
          WalletFragment.zza(WalletFragment.this, null);
        }
        return;
      }
      catch (GooglePlayServicesNotAvailableException paramzzf) {}
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\wallet\fragment\WalletFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */