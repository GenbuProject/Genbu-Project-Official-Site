package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.dynamic.zza;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzad;
import com.google.android.gms.maps.internal.zzc;
import com.google.android.gms.maps.internal.zzo.zza;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MapView
  extends FrameLayout
{
  private GoogleMap zzaSd;
  private final zzb zzaSj;
  
  public MapView(Context paramContext)
  {
    super(paramContext);
    this.zzaSj = new zzb(this, paramContext, null);
    zzex();
  }
  
  public MapView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.zzaSj = new zzb(this, paramContext, GoogleMapOptions.createFromAttributes(paramContext, paramAttributeSet));
    zzex();
  }
  
  public MapView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.zzaSj = new zzb(this, paramContext, GoogleMapOptions.createFromAttributes(paramContext, paramAttributeSet));
    zzex();
  }
  
  public MapView(Context paramContext, GoogleMapOptions paramGoogleMapOptions)
  {
    super(paramContext);
    this.zzaSj = new zzb(this, paramContext, paramGoogleMapOptions);
    zzex();
  }
  
  private void zzex()
  {
    setClickable(true);
  }
  
  @Deprecated
  public final GoogleMap getMap()
  {
    if (this.zzaSd != null) {
      return this.zzaSd;
    }
    this.zzaSj.zzzW();
    if (this.zzaSj.zztU() == null) {
      return null;
    }
    try
    {
      this.zzaSd = new GoogleMap(((zza)this.zzaSj.zztU()).zzzX().getMap());
      return this.zzaSd;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void getMapAsync(OnMapReadyCallback paramOnMapReadyCallback)
  {
    zzx.zzcD("getMapAsync() must be called on the main thread");
    this.zzaSj.getMapAsync(paramOnMapReadyCallback);
  }
  
  public final void onCreate(Bundle paramBundle)
  {
    this.zzaSj.onCreate(paramBundle);
    if (this.zzaSj.zztU() == null) {
      zza.zzb(this);
    }
  }
  
  public final void onDestroy()
  {
    this.zzaSj.onDestroy();
  }
  
  public final void onEnterAmbient(Bundle paramBundle)
  {
    zzx.zzcD("onEnterAmbient() must be called on the main thread");
    this.zzaSj.onEnterAmbient(paramBundle);
  }
  
  public final void onExitAmbient()
  {
    zzx.zzcD("onExitAmbient() must be called on the main thread");
    this.zzaSj.onExitAmbient();
  }
  
  public final void onLowMemory()
  {
    this.zzaSj.onLowMemory();
  }
  
  public final void onPause()
  {
    this.zzaSj.onPause();
  }
  
  public final void onResume()
  {
    this.zzaSj.onResume();
  }
  
  public final void onSaveInstanceState(Bundle paramBundle)
  {
    this.zzaSj.onSaveInstanceState(paramBundle);
  }
  
  static class zza
    implements MapLifecycleDelegate
  {
    private final ViewGroup zzaSk;
    private final IMapViewDelegate zzaSl;
    private View zzaSm;
    
    public zza(ViewGroup paramViewGroup, IMapViewDelegate paramIMapViewDelegate)
    {
      this.zzaSl = ((IMapViewDelegate)zzx.zzz(paramIMapViewDelegate));
      this.zzaSk = ((ViewGroup)zzx.zzz(paramViewGroup));
    }
    
    public void getMapAsync(final OnMapReadyCallback paramOnMapReadyCallback)
    {
      try
      {
        this.zzaSl.getMapAsync(new zzo.zza()
        {
          public void zza(IGoogleMapDelegate paramAnonymousIGoogleMapDelegate)
            throws RemoteException
          {
            paramOnMapReadyCallback.onMapReady(new GoogleMap(paramAnonymousIGoogleMapDelegate));
          }
        });
        return;
      }
      catch (RemoteException paramOnMapReadyCallback)
      {
        throw new RuntimeRemoteException(paramOnMapReadyCallback);
      }
    }
    
    public void onCreate(Bundle paramBundle)
    {
      try
      {
        this.zzaSl.onCreate(paramBundle);
        this.zzaSm = ((View)zze.zzp(this.zzaSl.getView()));
        this.zzaSk.removeAllViews();
        this.zzaSk.addView(this.zzaSm);
        return;
      }
      catch (RemoteException paramBundle)
      {
        throw new RuntimeRemoteException(paramBundle);
      }
    }
    
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      throw new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
    }
    
    public void onDestroy()
    {
      try
      {
        this.zzaSl.onDestroy();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onDestroyView()
    {
      throw new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
    }
    
    public void onEnterAmbient(Bundle paramBundle)
    {
      try
      {
        this.zzaSl.onEnterAmbient(paramBundle);
        return;
      }
      catch (RemoteException paramBundle)
      {
        throw new RuntimeRemoteException(paramBundle);
      }
    }
    
    public void onExitAmbient()
    {
      try
      {
        this.zzaSl.onExitAmbient();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onInflate(Activity paramActivity, Bundle paramBundle1, Bundle paramBundle2)
    {
      throw new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
    }
    
    public void onLowMemory()
    {
      try
      {
        this.zzaSl.onLowMemory();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onPause()
    {
      try
      {
        this.zzaSl.onPause();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onResume()
    {
      try
      {
        this.zzaSl.onResume();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onSaveInstanceState(Bundle paramBundle)
    {
      try
      {
        this.zzaSl.onSaveInstanceState(paramBundle);
        return;
      }
      catch (RemoteException paramBundle)
      {
        throw new RuntimeRemoteException(paramBundle);
      }
    }
    
    public void onStart() {}
    
    public void onStop() {}
    
    public IMapViewDelegate zzzX()
    {
      return this.zzaSl;
    }
  }
  
  static class zzb
    extends zza<MapView.zza>
  {
    private final Context mContext;
    protected zzf<MapView.zza> zzaSh;
    private final List<OnMapReadyCallback> zzaSi = new ArrayList();
    private final ViewGroup zzaSo;
    private final GoogleMapOptions zzaSp;
    
    zzb(ViewGroup paramViewGroup, Context paramContext, GoogleMapOptions paramGoogleMapOptions)
    {
      this.zzaSo = paramViewGroup;
      this.mContext = paramContext;
      this.zzaSp = paramGoogleMapOptions;
    }
    
    public void getMapAsync(OnMapReadyCallback paramOnMapReadyCallback)
    {
      if (zztU() != null)
      {
        ((MapView.zza)zztU()).getMapAsync(paramOnMapReadyCallback);
        return;
      }
      this.zzaSi.add(paramOnMapReadyCallback);
    }
    
    public void onEnterAmbient(Bundle paramBundle)
    {
      if (zztU() != null) {
        ((MapView.zza)zztU()).onEnterAmbient(paramBundle);
      }
    }
    
    public void onExitAmbient()
    {
      if (zztU() != null) {
        ((MapView.zza)zztU()).onExitAmbient();
      }
    }
    
    protected void zza(zzf<MapView.zza> paramzzf)
    {
      this.zzaSh = paramzzf;
      zzzW();
    }
    
    public void zzzW()
    {
      if ((this.zzaSh != null) && (zztU() == null)) {
        try
        {
          MapsInitializer.initialize(this.mContext);
          Object localObject = zzad.zzaO(this.mContext).zza(zze.zzC(this.mContext), this.zzaSp);
          if (localObject == null) {
            return;
          }
          this.zzaSh.zza(new MapView.zza(this.zzaSo, (IMapViewDelegate)localObject));
          localObject = this.zzaSi.iterator();
          while (((Iterator)localObject).hasNext())
          {
            OnMapReadyCallback localOnMapReadyCallback = (OnMapReadyCallback)((Iterator)localObject).next();
            ((MapView.zza)zztU()).getMapAsync(localOnMapReadyCallback);
          }
          return;
        }
        catch (RemoteException localRemoteException)
        {
          throw new RuntimeRemoteException(localRemoteException);
          this.zzaSi.clear();
          return;
        }
        catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException) {}
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\maps\MapView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */